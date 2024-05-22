/** Description: This assignment taught us how to allocate, reallocate, and free memory 
 * as well as use valgrind to check for memory leaks.
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyvidya.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu
 * Last modified date: 04/12/2024
 * Creation date: 04/03/2024
 */
 
 # To compile the program
 1) Under the CS149 directory, run "make all"
 2) Go into the A5 directory with "cd A5"

 # To run with user's inputs, do command "./mem_tracer" (still under A5 directory) with these inputs (examples)
 ../A1/matrixmult test/A1.txt test/W1.txt test/B1.txt
 ../A1/matrixmult test/A2.txt test/W2.txt test/B2.txt
 ../A1/matrixmult test/A3.txt test/W3.txt test/B3.txt
 ../A1/matrixmult test/wrongfilename.txt
 sleep 10
 sleep 9
 sleep 8
 sleep 7

 # This will create mem_tracer.out which should look like this
File A5/mem_tracer.c, line 94, storeCommands:main:global allocated new memory segment at address 0xaaaac52b5300 to size 80.
File A5/mem_tracer.c, line 95, storeCommands:main:global allocated new memory segment at address 0xaaaac52b6370 to size 10.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaac52b6820 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaac52b68d0 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaac52b6980 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaac52b6a30 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaac52b6ac0 to size 32.
File A5/mem_tracer.c, line 112, storeCommands:main:global reallocated new memory segment at address 0xaaaac52b6b10 to size 160.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaac52b6be0 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaac52b6c50 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaac52b6cc0 to size 32.
File A5/mem_tracer.c, line 124, storeCommands:main:global free new memory segment at address 0xaaaac52b67a0.
Input number 1:  ../A1/matrixmult test/A1.txt test/W1.txt test/B1.txt
Input number 2:  ../A1/matrixmult test/A2.txt test/W2.txt test/B2.txt
Input number 3:  ../A1/matrixmult test/A3.txt test/W3.txt test/B3.txt
Input number 4:  ../A1/matrixmult test/wrongfilename.txt
Input number 5:  sleep 10
Input number 6:  sleep 9
Input number 7:  sleep 8
Input number 8:  sleep 7
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6cf0.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6cc0.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6c80.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6c50.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6c10.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6be0.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6af0.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6ac0.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6a60.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6a30.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b69b0.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6980.
File A5/mem_tracer.c, line 77, printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b6900.
File A5/mem_tracer.c, line 78, printNodes:printNodes:main:global free new memory segment at address 0xaaaac52b68d0.
File A5/mem_tracer.c, line 77, printNodes:main:global free new memory segment at address 0xaaaac52b6850.
File A5/mem_tracer.c, line 78, printNodes:main:global free new memory segment at address 0xaaaac52b6820.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaac52b67e0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaac52b6890.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaac52b6940.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaac52b69f0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaac52b6370.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaac52b6aa0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaac52b6bc0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaac52b6c30.
File A5/mem_tracer.c, line 143, freeCommands:main:global free new memory segment at address 0xaaaac52b6b10.

 # Run with file, do command "./mem_tracer < test.txt" (still under A5 directory) with this file (example)
 
 # This will create mem_tracer.out which should look like this
File A5/mem_tracer.c, line 94, storeCommands:main:global allocated new memory segment at address 0xaaaad1ddc300 to size 80.
File A5/mem_tracer.c, line 95, storeCommands:main:global allocated new memory segment at address 0xaaaad1ddd370 to size 10.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1dde440 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1dde550 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1dde640 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1dde730 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1dde820 to size 32.
File A5/mem_tracer.c, line 112, storeCommands:main:global reallocated new memory segment at address 0xaaaad1dde8c0 to size 160.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1dde9c0 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddea40 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddeb30 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddec30 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1dded30 to size 32.
File A5/mem_tracer.c, line 112, storeCommands:main:global reallocated new memory segment at address 0xaaaad1ddedc0 to size 320.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddef80 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddf090 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddf190 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddf280 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddf370 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddf450 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddf530 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddf620 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddf700 to size 32.
File A5/mem_tracer.c, line 46, insertCommand:storeCommands:main:global allocated new memory segment at address 0xaaaad1ddf800 to size 32.
File A5/mem_tracer.c, line 124, storeCommands:main:global free new memory segment at address 0xaaaad1dde4c0.
Input number 1: The stars twinkled like diamonds strewn across the velvet sky.
Input number 2: A gentle breeze rustled the leaves, whispering secrets to the trees.
Input number 3: The aroma of freshly baked bread wafted through the kitchen, tempting even the strongest willpower.
Input number 4: Laughter echoed through the halls, painting the air with joy.
Input number 5: The old oak tree stood sentinel in the meadow, its branches reaching towards the heavens.
Input number 6: Time seemed to stand still as they danced beneath the moonlight.
Input number 7: The sound of rain tapping against the windowpane was a soothing lullaby.
Input number 8: With a flick of her wrist, she unleashed a burst of magic, illuminating the darkness.
Input number 9: The smell of petrichor filled the air after the storm, a welcome sign of nature's renewal.
Input number 10: His words were like daggers, cutting through her defenses with precision.
Input number 11: The city streets bustled with activity, a symphony of honking horns and hurried footsteps.
Input number 12: The sound of waves crashing against the shore was a constant reminder of the ocean's power.
Input number 13: With a sigh of relief, she sank into the comfort of her favorite armchair.
Input number 14: The scent of wildflowers danced on the breeze, a fragrant celebration of spring.
Input number 15: The taste of freshly squeezed lemonade was a sweet indulgence on a hot summer day.
Input number 16: Shadows danced across the walls as the fire crackled in the hearth.
Input number 17: The melody of birdsong greeted the dawn, a chorus of nature's alarm clock.
Input number 18: With each step, she felt the weight of the world lift from her shoulders.
Input number 19: The painting hung on the wall, a masterpiece of color and light.
Input number 20: As the sun dipped below the horizon, the sky blazed with fiery hues, painting the world in shades of gold and crimson.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf830.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf800.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf730.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf700.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf650.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf620.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf560.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf530.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf480.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf450.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf3a0.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf370.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf2b0.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf280.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf1c0.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf190.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf0c0.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddf090.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddefb0.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddef80.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1dded60.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1dded30.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddec60.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes free new memory segment at address 0xaaaad1ddec30.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1ddeb60.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1ddeb30.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1ddea70.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1ddea40.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1dde9f0.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1dde9c0.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1dde850.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1dde820.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1dde760.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1dde730.
File A5/mem_tracer.c, line 77, printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1dde670.
File A5/mem_tracer.c, line 78, printNodes:printNodes:printNodes:main:global free new memory segment at address 0xaaaad1dde640.
File A5/mem_tracer.c, line 77, printNodes:printNodes:main:global free new memory segment at address 0xaaaad1dde580.
File A5/mem_tracer.c, line 78, printNodes:printNodes:main:global free new memory segment at address 0xaaaad1dde550.
File A5/mem_tracer.c, line 77, printNodes:main:global free new memory segment at address 0xaaaad1dde470.
File A5/mem_tracer.c, line 78, printNodes:main:global free new memory segment at address 0xaaaad1dde440.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1dde3f0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1dde3a0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1dde5d0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1dde6e0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1dde7b0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1dde970.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddc300.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddead0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddebc0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddecd0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddef10.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddf020.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddf130.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddf220.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddf310.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddf400.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddf4d0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddf5c0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddf6b0.
File A5/mem_tracer.c, line 141, freeCommands:main:global free new memory segment at address 0xaaaad1ddf780.
File A5/mem_tracer.c, line 143, freeCommands:main:global free new memory segment at address 0xaaaad1ddedc0.


 # Test for memory leak with valgrind
 # Run "valgrind --leak-check=full ./mem_tracer < test.txt" (or with user's inputs), the results should look something like this
==6730== Memcheck, a memory error detector
==6730== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==6730== Using Valgrind-3.18.1 and LibVEX; rerun with -h for copyright info
==6730== Command: ./mem_tracer
==6730== 
==6730== 
==6730== HEAP SUMMARY:
==6730==     in use at exit: 0 bytes in 0 blocks
==6730==   total heap usage: 113 allocs, 113 frees, 13,506 bytes allocated
==6730== 
==6730== All heap blocks were freed -- no leaks are possible
==6730== 
==6730== For lists of detected and suppressed errors, rerun with: -s
==6730== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)

 # To clean the program
3) Return to CS149 directory (exiting A5) with "cd .."
4) clean A5 with this command "make clean" (run under CS149 directory)
