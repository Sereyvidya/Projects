/** Description: This assignment taught us how to redirect stdout and stderr into files using dup2
 * and how to load a program using execvp.
 * It also taught us how to do this faster through pipes and forking. 
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyvidya.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu
 * Last modified date: 03/09/2024
 * Creation date: 03/13/2024
 */
 
 # Run $ make all to compile neccessary files
 
 # Run "./proc_manager" and these test cases and call "pkill sleep" (in another terminal) after entering "^D":
../A1/matrixmult test/A1.txt test/W1.txt test/B1.txt
../A1/matrixmult test/A2.txt test/W2.txt test/B2.txt
../A1/matrixmult test/A3.txt test/W3.txt test/B3.txt
../A1/matrixmult test/wrongfilename.txt
sleep 10
^D 

# Expected output
$ cat 7634.out
Starting command 1: child 7634 pid of parent 7632
Result of test/A1.txt*test/W1.txt+test/B1.txt = [ 111 191 391 51 11 ]
Finished child 7634 pid of parent 7632

$ cat 7634.err
Exited with exitcode = 0

$ cat 7643.out
Starting command 2: child 7643 pid of parent 7632
Result of test/A2.txt*test/W2.txt+test/B2.txt = [ 701 151 191 51 11 ]
Finished child 7643 pid of parent 7632

$ cat 7643.err
Exited with exitcode = 0

$ cat 7644.out
Starting command 3: child 7644 pid of parent 7632
Result of test/A3.txt*test/W3.txt+test/B3.txt = [ 11 19 39 55 55 ]
Finished child 7644 pid of parent 7632

$ cat 7644.err
Exited with exitcode = 0

$ cat 7645.out
Starting command 4: child 7645 pid of parent 7632
Finished child 7645 pid of parent 7632

$ cat 7645.err
error: expecting exactly 3 files as input
Exited with exitcode = 1

$ cat 7647.out
Starting command 5: child 7647 pid of parent 7632
Finished child 7647 pid of parent 7632

$ cat 7647.err
Killed with signal 15

# Run $ make clean to delete output files and executables