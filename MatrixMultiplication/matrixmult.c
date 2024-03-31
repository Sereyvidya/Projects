/** 
 * Description: This module taught us how to read and close files, and create methods to calculate matrix multiplication and addition.  
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyvidya.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu 
 * Last modified date: 02/12/2024 
 * Creation date: 02/12/2024 
 **/
#include <stdio.h>

int main(int argc, char *argv[]) {
    // Prints error message if incorrect file inputs
    if (argc != 4) {
        fprintf(stderr, "error: expecting exactly 3 files as input\n");
        return 1;
    }

    // Create files, file names, and arrays for holding matrices
    FILE *aFile, *wFile, *bFile;
    char *aFileName, *wFileName, *bFileName;
    int aArray[3] = {0};
    int wArray[3][5] = {0};
    int bArray[5] = {0};

    // Openning A file
    aFileName = argv[1];
    aFile = fopen(aFileName, "r");
    if (aFile == NULL) {
        fprintf(stderr, "error: cannot open file %s\nTerminating, exit code 1.\n", aFileName);
        return 1;
    } 
    for (int i = 0; i < 3; i++) {
        if (fscanf(aFile, "%d", &aArray[i]) != 1) {
            fclose(aFile);
        }

    }
    fclose(aFile);

    // Openning W file
    wFileName = argv[2];
    wFile = fopen(wFileName, "r");
    if (wFile == NULL) {
        fprintf(stderr, "error: cannot open file %s\nTerminating, exit code 1.\n", wFileName);
        return 1;
    }
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 5; j++) {
            if (fscanf(wFile, "%d", &wArray[i][j]) != 1) {
                fclose(wFile);
            }
            if (fgetc(wFile) == '\n') {
                break;
            }
        }
    } 
    fclose(wFile);

    // Openning B file
    bFileName = argv[3];
    bFile = fopen(bFileName, "r");
    if (bFile == NULL) {
        fprintf(stderr, "error: cannot open file %s\nTerminating, exit code 1.\n", bFileName);
        return 1;
    }
    for (int i = 0; i < 5; i++) {
        if (fscanf(bFile, "%d", &bArray[i]) != 1) {
            fclose(bFile);
        }
    }
    fclose(bFile);

    // Matrix multiplication and addition
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 5; j++) {
            bArray[j] += aArray[i] * wArray[i][j];
        }
    }

    printf("Result of %s*%s+%s = ", argv[1], argv[2], argv[3]);
    printf("[ %d %d %d %d %d ]\n", bArray[0], bArray[1], bArray[2], bArray[3], bArray[4]);

    return 0;
}

