/** 
 * Description: This module taught us how to properly allocate, reallocate, 
 *              and free memory as well as how to use valgrind to check for memory leaks.  
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyvidya.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu 
 * Last modified date: 04/12/2024 
 * Creation date: 04/03/2024 
 **/

// Include headers
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include "CommandNodeLL.h"

// Redifine functions
#define realloc(a,b) REALLOC(a,b,__FILE__,__LINE__)
#define malloc(a) MALLOC(a,__FILE__,__LINE__)
#define free(a) FREE(a,__FILE__,__LINE__)

// Function prototypes
void PUSH_TRACE(char* p);
void POP_TRACE();
char* PRINT_TRACE();
void* REALLOC(void* p,int t,char* file,int line);
void* MALLOC(int t,char* file,int line);
void FREE(void* p,char* file,int line);

// Values
size_t len = 10; 
int init_size = 10;

/**
 * This function creates a node and insert it into the LinkedList. 
 * Assumptions: Array of command components are at most 20 components and each at most 20 characters long.
 * Input parameters: Current count of commands, and an array of command components.
 * Returns: void.
 */
void insertCommand(int count, char *cmdLine, CommandNode **head, CommandNode **curr) {
    // Push trace
    PUSH_TRACE("insertCommand");

    // Allocate memory for node and create node
    CommandNode* cmdNode = (CommandNode*)malloc(sizeof(CommandNode));
    CreateCommandNode(cmdNode, cmdLine, count + 1, NULL);

    if (count == 0) {
        *head = cmdNode; // Initialize head node
    }
    else {
        InsertCommandAfter(*curr, cmdNode); // Insert node at after previous node
    }
    *curr = cmdNode; // Assgin latest node

    // Pop trace
    POP_TRACE();
    return;
}

/**
 * This function recursively prints the content of the LinkedList.
 * Assumptions: none.
 * Input parameters: Head node of the LinkedList.
 * Returns: void.
 */
void printNodes (CommandNode* thisNode) {
    // Push trace
    PUSH_TRACE("printNodes");
    if (thisNode != NULL) {
    	// Recursively prints input
        printf("Input number %d: %s\n", thisNode->index, thisNode->command);
        printNodes(thisNode->nextCommandPtr);
        
        // Frees after printing
        free(thisNode->command);
        free(thisNode);
    }
    // Pop trace
    POP_TRACE();
}    

/**
 * This function stores user-inputed commands in an array of pointers and LinkedList. 
 * Assumptions: none.
 * Input parameters: Array of commands, count of commands, size for allocating, pointer to head node, pointer to current node.
 * Returns: void.
 */
char** storeCommands(char **cmds, int *count, int *allocSize, CommandNode **head, CommandNode **curr) {
    // Push trace
    PUSH_TRACE("storeCommands");

    cmds = (char **)malloc(*allocSize * sizeof(char *));    // For storing commands
    char *cmdLine = (char *)malloc(len * sizeof(char));     // For storing one command

    // Iterates through all inputs
    while (getline(&cmdLine, &len, stdin) != -1) {
        // Replaces new line character with null character
        if (cmdLine[strlen(cmdLine) - 1] == '\n') {
            cmdLine[strlen(cmdLine) - 1] = '\0';
        }

        // Continue if line is empty
        if (cmdLine[0] == 0) {
            continue;
        }

        // Reallocates memory
        if (*count == *allocSize/2) {
            *allocSize *= 2;
            cmds = realloc(cmds, *allocSize * sizeof(char *));
        }

        // Stores command in array
        cmds[*count] = strdup(cmdLine);

        // Insert command to LinkedList and increment count of commands
        insertCommand(*count, cmdLine, head, curr);
        (*count)++;
    }
    
    // Frees pointer to command
    free(cmdLine);
    
    // Pop trace
    POP_TRACE();
    return cmds;
}

/**
 * This function frees all memory allocated for array of commands.
 * Assumptions: none.
 * Input parameters: Array of commands and count of current commands.
 * Returns: void.
 */
void freeCommands(char **cmds, int *count) {
    // Push trace
    PUSH_TRACE("freeCommands");
    for (int i = 0; i < *count; i++) {
        free(cmds[i]);
    }
    free(cmds);
    cmds = NULL;

    // Pop trace
    POP_TRACE();
    return;
}

// Main function
int main() {
    // Push trace
    PUSH_TRACE("main");

    char **cmds = NULL; // Array of commands
    int allocSize = init_size; // Size to allocate/reallocate
    int count = 0; // Initialize count of commands to zero
    CommandNode* head; // Head node of LinkedList
    CommandNode* curr; // Current node of LinkedList

    // Redirects output to a file
    char *file = "memtrace.out";
    int fd = open(file, O_RDWR | O_CREAT | O_APPEND, 0777);
    dup2(fd, STDOUT_FILENO);
    close(fd);

    // Stores commands and prints LinkedList
    cmds = storeCommands(cmds, &count, &allocSize, &head, &curr);
    printNodes(head);

    // Free the allocated memory and pop trace
    freeCommands(cmds, &count);
    POP_TRACE();
    POP_TRACE();
    return 0;
}



