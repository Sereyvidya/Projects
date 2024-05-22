/** 
 * Description: This assignment taught us how to read and close files, and create methods to read names and count their occurrences. 
 * Furthermore, it taught us how to use pipes to do more than one task at once.
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyvidya.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu 
 * Last modified date: 03/13/2024 
 * Creation date: 03/09/2024 
 **/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

// Constant values
const int MAX_LENGTH_OF_NAMES = 31;
const int MAX_NUMBER_OF_NAMES = 100;

/**
 * This function Searches an array of strings and compares with target.
 * Assumption: Strings have 30 or less characters
 * Input parameters: Array of strings, size of array, target string
 * Returns: -1 if target is not found, otherwise returns index of target in array
 **/
int containsString(char array[][MAX_LENGTH_OF_NAMES], int size, char *target) {
    for (int i = 0; i < size; i++) {
        if (strcmp(array[i], target) == 0) {
            return i; // Target found
        }
    }
    return -1; // Target not found
}

/**
 * This function takes in a list of names and their occurrences and updates it accordingly
 * Assumption: Names have at most 30 characters, and at most 100 unique names
 * Input parameters: Array of strings, occurrences of strings, target string, amount to update, index of target string, and count (length of unique strings/occurrences)
 * Returns: void
 **/
void updateOccurrence(char namesList[][MAX_LENGTH_OF_NAMES], int occurrence[], char *name, int num, int *index, int *count) {
    if (*index > -1) {
        occurrence[*index] += num;
    } else {  // If name does not exist in list yet
        strcpy(namesList[*count], name);
        occurrence[*count] += num;
        (*count)++;
    }
}

/**
 * This function takes in a text file stream and prints each unique name and its occurences. 
 * Assumption: Names have at most 30 characters, and at most 100 unique names
 * Input parameters: Executable file, input file (optional)
 * Returns: void
 **/
void countNames(FILE *file, char fileName[MAX_LENGTH_OF_NAMES], char namesList[][MAX_LENGTH_OF_NAMES], int occurrence[MAX_NUMBER_OF_NAMES], int *count) {
    int currentCount = 0; // Current line in file
    int index;
    char name[MAX_LENGTH_OF_NAMES]; 

    // Iterates through file, reading name and their occurrences
    while (fgets(name, sizeof(name), file) != NULL) {
        // Number of iterations
        currentCount++; 

        // Replaces new line character will null character
        if (name[strlen(name) - 1] == '\n') {
            name[strlen(name) - 1] = '\0';
        }

        // Empty line if line starts with null terminating character
        if (name[0] == 0) {
            fprintf(stderr, "Warning - file %s Line %d is empty.\n", fileName, currentCount);
            continue;
        }

        index = containsString(namesList, *count, name);
        // If index is greater than -1, name has already been found at least once, increment count
        // Otherwise, name has never been encountered yet, put name in array of names and increment count
        updateOccurrence(namesList, occurrence, name, 1, &index, count);
    }
}

/**
 * This is the main function.
 **/
int main (int argc, char *argv[]) {
    char allNamesList[MAX_NUMBER_OF_NAMES][MAX_LENGTH_OF_NAMES]; // Array for all combined names 
    int allOccurrence[MAX_NUMBER_OF_NAMES]; // Array for combined occurrence of names
    int allCount = 0;

    for (int i = 0; i < MAX_NUMBER_OF_NAMES; i++) {
        allOccurrence[i] = 0;
    }

    // Preparing number of runs
    int num = 2;
    if (argc > 1) {
        num = argc;
    }

    for (int j = 0; j < num - 1; j++) {
        // Create pipes, exit if error
        int childToParentPipe[2];
        int parentToChildPipe[2];
        if (pipe(childToParentPipe) == -1 || pipe(parentToChildPipe) == -1) {
            return 1;
        }

        // Forks a child process
        int pid = fork();
        if (pid == -1) {
            return 1;
        }

        FILE *file;
        char *fileName;
        if (argc > 1) {
            fileName = malloc(strlen(argv[j + 1]) + 1);
            strcpy(fileName, argv[j + 1]);
        }


        // List of names and occurrence for a child (1 file)
        char namesList[MAX_NUMBER_OF_NAMES][MAX_LENGTH_OF_NAMES]; // Array for storing names for a file
        int occurrence[MAX_NUMBER_OF_NAMES]; // Array for storing occurrence of names for a file
        int count = 0; // Count of unique names

        if (pid == 0) { // Child process

            // Close unused pipe ends
            close(childToParentPipe[0]);
            close(parentToChildPipe[1]);

            // Read file name from pipe and open file
            char fileNameBuffer[MAX_LENGTH_OF_NAMES];
            if (read(parentToChildPipe[0], fileNameBuffer, sizeof(fileNameBuffer)) == -1) {
                return 1;
            }

            if (strcmp("stdin", fileNameBuffer) == 0) {
                file = stdin;
            } 
            else {
                file = fopen(fileNameBuffer, "r");    
                if (file == NULL) {
                    printf("range: cannot open file\n");
                    return 1; // Return non-zero exit code to indicate failure
                }
            }

            // Initialize array of name occurrences
            for (int i = 0; i < MAX_NUMBER_OF_NAMES; i++) {
                occurrence[i] = 0;
            }

            // Counts names and occurrences
            countNames(file, fileNameBuffer, namesList, occurrence, &count);

            // Writes results to parent
            if (write(childToParentPipe[1], &count, sizeof(int)) == -1) {
                return 1;
            }
            if (write(childToParentPipe[1], occurrence, sizeof(occurrence)) == -1) {
                return 1;
            }
            for (int i = 0; i < count; i++) {
                if (write(childToParentPipe[1], namesList[i], strlen(namesList[i]) + 1) == -1) {
                    return 1;
                }
            }

            // Close remaining pipe ends
            close(childToParentPipe[1]);
            close(parentToChildPipe[0]);

            // Close file
            fclose(file);

            // Successfully exits
            exit(EXIT_SUCCESS);
        }
        else { // Parent process
            int status;

            // Close unused pipe ends
            close(parentToChildPipe[0]);
            close(childToParentPipe[1]);

            // Write file's name into pipe
            if (argc > 1) {
                if (write(parentToChildPipe[1], fileName, strlen(fileName) + 1) == -1) {
                    return 1;
                }
            } 
            else {
                // If no file name is provided, send "stdin" as the file name
                if (write(parentToChildPipe[1], "stdin", strlen("stdin")) == -1) {
                    return 1;
                }
            }

            // Wait for child process to finish first
            waitpid(pid, &status, 0);
            if (WEXITSTATUS(status) == 1) {
                return 1;
            }

            // Read results from child
            if (read(childToParentPipe[0], &count, sizeof(int)) == -1) {
                return 1;
            }
            if (read(childToParentPipe[0], occurrence, sizeof(occurrence)) == -1) {
                return 1;
            }
            for (int i = 0; i < count; i++) {
                char name[MAX_LENGTH_OF_NAMES];
                int k = 0;
                char ch;
                while (k < MAX_LENGTH_OF_NAMES && read(childToParentPipe[0], &ch, 1) == 1) {
                    name[k++] = ch;
                    if (ch == '\0') {
                        break; // Stop reading if null character is encountered
                    }
                }
                strcpy(namesList[i], name);
            }
            
            // Combining child's result with parent's
            int index;
            for (int i = 0; i < count; i++) {
                index = containsString(allNamesList, allCount, namesList[i]);
                updateOccurrence(allNamesList, allOccurrence, namesList[i], occurrence[i], &index, &allCount);
            }

            // Close remaining pipe ends
            close(parentToChildPipe[1]);
            close(childToParentPipe[0]);
        }

        free(fileName);
    }
        // Print names and their occurences
        for (int i = 0; i < allCount; i++) {
            printf("%s: %d\n", allNamesList[i], allOccurrence[i]);
        }

    return 0;
}
