/** 
 * Description: This module taught us how to use threads to do paralleling, and how to use mutex_locks to lock critical sections.
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyvidya.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu 
 * Last modified date: 05/05/2024 
 * Creation date: 05/04/2024 
 **/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <time.h>
#include <unistd.h>
#include "hash_table.h"

// Global variables
int logindex = 0;
size_t len = 0;

// Thread locks
pthread_mutex_t logLock;
pthread_mutex_t countLock;

/**
 * This function takes in a thread ID and two strings and prints a log message.
 * Assumptions: none.
 * Input parameters: Thread ID, and 2 strings.
 * Returns: void.
 */
void printLog(pthread_t tid, char *s1, char *s2) {
    // Lock so that only 1 thread can modify the log index at any time
    pthread_mutex_lock(&logLock);
    logindex++;

    // Get current time and print
    time_t currentTime;
    time(&currentTime);
    char *timeStr = ctime(&currentTime);
    timeStr[strlen(timeStr) - 1] = '\0';
    printf("Logindex %d, thread %lu, PID %d, %s: %s %s\n", logindex, (unsigned long)tid, getpid(), timeStr, s1, s2);

    // Unlocks the lock
    pthread_mutex_unlock(&logLock);
}

/**
 * This function takes in pointer to a thread's arguments and runs the thread. 
 * Assumptions: That the thread only needs one argument which is the input file's name.
 * Input parameters: Pointer to thread's arguments.
 * Returns: void.
 */
void *thread_countnames(void *arg) {
    // Open file and terminates thread if file does not exist
    char *filename = strdup((char *)arg);
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        printf("range: cannot open file\n");
        pthread_exit(NULL);
    }
    // Prints log message
    printLog(pthread_self(), "opened file", filename);

    // Name and file line number pointers
    char *name;
    int *lineNum = (int *)malloc(sizeof(int));
    *lineNum = 0;

    // Loops through all names in file
    while (getline(&name, &len, file) != -1) {
        (*lineNum)++;

        // Skips if line was empty (no name)
        if (name[0] == '\n') {
            fprintf(stderr, "Warning - file %s Line %d is empty.\n", filename, *lineNum);
            continue;
        }

        // Null-terminates the name
        if (name[strlen(name) - 1] == '\n') {
            name[strlen(name) - 1] = '\0';
        }

        // Look up the name to see if it exists
        struct nlist *np;
        np = lookup(name);
        if (np == NULL) { // If name does not exist, insert to table
            int one = 1;
            np = insert(name, &one);
        } 
        else { // If name was already in table then increment it
            pthread_mutex_lock(&countLock);
            (*(np->count))++;
            pthread_mutex_unlock(&countLock);
        }   
    }    

    // Close files, print log message, free pointers, and exit thread
    fclose(file);
    printLog(pthread_self(), "closed file", filename);
    free(filename);
    free(lineNum);
    free(name);
    pthread_exit(NULL);
}

/**
 * This function recursively prints the names of each node node in a hashtable.
 * Assumptions: none.
 * Input parameters: The head node of the list.
 * Returns: void.
 */
void printNamesHelper(struct nlist *np) {
    if (np != NULL) {
        printf("%s: %d\n", np->name, *(np->count));
        printNamesHelper(np->next);
        free(np->name);
        free(np->count);
        free(np);
    }
}

/**
 * This function prints the names in the hashtable and their counts. 
 * Assumptions: That only 100 unique names can be read and each name is at most 30 characters long.
 * Input parameters: none.
 * Returns: void.
 */
void printNames() {
    printf("\n==================== Name Counts ====================\n");
    int i;
    for (i = 0; i < HASHSIZE; i++) {
        printNamesHelper(hashtab[i]);
    }
}

/**
 * This is the main function.
 **/
int main (int argc, char *argv[]) {
    // Initialize locks and array of threads
    pthread_mutex_init(&logLock, NULL);
    pthread_mutex_init(&countLock, NULL);
    pthread_t threads[argc - 1];

    // Loops through all file inputs
    for (int i = 1; i < argc; i++) {
        // Pass in a file name and create a thread
        if (pthread_create(&threads[i - 1], NULL, thread_countnames, argv[i]) != 0) {
            perror("Failed to create thread.\n");
        }
    }

    // Wait for all threads to complete and prints names and counts
    for (int i = 1; i < argc; i++) {
        if (pthread_join(threads[i - 1], NULL) != 0) {
            printf("Failed to join thread.\n");
            return 1;
        }
    }
    printNames();
    return 0;
}