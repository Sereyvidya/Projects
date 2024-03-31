/** 
 * Description: This module taught us how to use execvp to load a program, and how to write outputs to files.
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyvidya.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu 
 * Last modified date: 03/24/2024 
 * Creation date: 03/23/2024 
 **/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>
#include <fcntl.h>

// Constant values
const int MAX_LENGTH_OF_COMMAND = 101; 
const int MAX_EXECVP_ARGS_COUNT = 5;
const int FILE_NAME_LENGTH = 20;

/**
 * This function takes in a line of command and separates its componenexts.
 * Assumptions: Command line has at most 100 characters, and there are 4 components.
 * Input parameters: Array for holding components, and command line.
 * Returns: void.
 */
void separateCommand(char *arguments[], char *command) {
    char *token = strtok(command, " ");
    int i = 0;
    
    // Parse command to get components separated by spaces 
    while (token != NULL) {
        arguments[i] = token;
        i++;
        token = strtok(NULL, " ");

        // If command is sleep, only need to iterate in loop once
        if (strcmp(arguments[i - 1], "sleep") == 0) {
            arguments[i] = token;
            return;
        }
    }
}

/**
 * This is the main function.
 */
int main(int argc, char *argv[]) {
    // Array for holding one command
    char command[MAX_LENGTH_OF_COMMAND]; 
    
    // Command count
    int i = 0;

    // Iterating through STDIN, and storing each command line
    while (fgets(command, MAX_LENGTH_OF_COMMAND, stdin) != NULL) {
        // If empty line, continue
        if (command[0] == 0) {
            continue;
        }

        // Replaces new line character will null character
        if (command[strlen(command) - 1] == '\n') {
            command[strlen(command) - 1] = '\0';
        }
    
        // Separate command arguments
        char *args[MAX_EXECVP_ARGS_COUNT];
        memset(args, '\0', sizeof(args));
        separateCommand(args, command);

        // Create file names
        char fileOutName[FILE_NAME_LENGTH] = "temp_out.txt";
        char fileErrName[FILE_NAME_LENGTH] = "temp_err.txt";
        
        // Create output and error files and like with stdout and stderr
        int fd1 = open(fileOutName, O_RDWR | O_CREAT | O_APPEND, 0777);
        int fd2 = open(fileErrName, O_RDWR | O_CREAT | O_APPEND, 0777);
        dup2(fd1, STDOUT_FILENO);
        dup2(fd2, STDERR_FILENO);

        // Forks a child process
        int pid = fork();
        if (pid == -1) {
            return 1;
        }

        // Rename file name (now that we have pid value)
        char newFileOutName[FILE_NAME_LENGTH];
        char newFileErrName[FILE_NAME_LENGTH];
        snprintf(newFileOutName, FILE_NAME_LENGTH, "%d_out.txt", pid);
        snprintf(newFileErrName, FILE_NAME_LENGTH, "%d_err.txt", pid);
        rename(fileOutName, newFileOutName);
        rename(fileErrName, newFileErrName);

        // Close file descriptors
        close(fd1);
        close(fd2);

        if (pid == 0) { // Child processs

            // Executes command
            if (execvp(args[0], args) == -1) {
                fprintf(stderr, "Exited with exitcode = 2\n");
                fflush(stderr);
                return 2;
            }

            // Successfully exits
            exit(EXIT_SUCCESS);
        }
        else { // Parent process
            int status;

            // Starting message
            printf("Starting command %d: child %d pid of parent %d\n", ++i, pid, getpid());
            fflush(stdout);

            // Wait for child to finish
            waitpid(pid, &status, 0);

            // Print finished messge
            printf("Finished child %d pid of parent %d\n", pid, getpid());
            fflush(stdout);

            if (WEXITSTATUS(status) == 1) { // If child process terminated with exitcode 1
                // Print exitcode to error file
                fprintf(stderr, "Exited with exitcode = 1\n");
                fflush(stderr);
            }            
            else if (WIFSIGNALED(status)) { // If child process terminated with signal
                // Print signal message to error file
                int signal_number = WTERMSIG(status);
                fprintf(stderr, "Killed with signal %d\n", signal_number);
                fflush(stderr);
            }
            else { // Print normal exitcode to error file
                fprintf(stderr, "Exited with exitcode = 0\n");
                fflush(stderr);
            }
        }
    }    
    return 0;
}