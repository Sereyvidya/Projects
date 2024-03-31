/** 
 * Description: This assignment taught us how to read and close files, and create methods to read names and count their occurrences.
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyviyda.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu 
 * Last modified date: 02/27/2024 
 * Creation date: 02/24/2024 
 **/

#include <stdio.h>
#include <string.h>

// Constant values
const int MAX_LENGTH_OF_NAMES = 30;
const int MAX_NUMBER_OF_NAMES = 101;

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
 * This function takes in a text file stream and prints each unique name and its occurences. 
 * Assumption: Names have at most 30 characters, and at most 100 unique names
 * Input parameters: Executable file, input file (optional)
 * Returns: 0
 **/
// Takes in a file, and print each name and their occurences
int main (int argc, char *argv[]) {
    FILE *file;

    // Reads in a file, if no file was passed, throws an error message
    if (argc == 1) {
        file = stdin;
    }
    else {
        char *fileName = argv[1];
        file = fopen(fileName, "r");
    }

    if (file == NULL) {
        fprintf(stderr, "error: cannot open file\n");
        return 1;
    }

    char namesList[MAX_NUMBER_OF_NAMES][MAX_LENGTH_OF_NAMES]; // Array for storing names
    int occurrence[MAX_NUMBER_OF_NAMES]; // Array for storing occurrence of names
    char name[MAX_LENGTH_OF_NAMES]; 

    // Initialize array of name occurrences
    for (int i = 0; i < MAX_NUMBER_OF_NAMES; i++) {
        occurrence[i] = 0;
    }

    int count = 0; // Count of unique names
    int currentCount = 0; // Current line in file
    int index;

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
            fprintf(stderr, "Warning - Line %d is empty.\n", currentCount);
            continue;
        }

        index = containsString(namesList, count, name);

        // If index is greater than -1, name has already been found at least once, increment count
        // Otherwise, name has never been encountered yet, put name in array of names and increment count
        if (index > -1) {
            occurrence[index] += 1;
        }
        else {
            strcpy(namesList[count], name);
            occurrence[count] += 1;
            count++;
        }
    }

    // Print names and their occurences
    for (int i = 0; i < count; i++) {
        printf("%s: %d\n", namesList[i], occurrence[i]);
    }

    // Close file
    fclose(file);

    return 0;
}