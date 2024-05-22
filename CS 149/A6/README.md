/** Description: This assignment taught us how to create and use threads for paralleling a names-counting program.
 * We also learned how to use mutex_lock to lock critical sections.
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyvidya.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu
 * Last modified date: 05/04/2024
 * Creation date: 05/06/2024
 */

 # To compile the program, run: gcc -o countnames_threaded countnames_threaded.c dictionary_hashtable.c -Wall -Werror

 # Test 1, run: ./countnames_threaded test/names.txt test/namesB.txt test/names1.txt test/names2.txt
Logindex 1, thread 281473743581472, PID 25112, Mon May  6 19:24:56 2024: opened file test/names.txt
Warning - file test/names.txt Line 2 is empty.
Warning - file test/names.txt Line 5 is empty.
Logindex 2, thread 281473743581472, PID 25112, Mon May  6 19:24:56 2024: closed file test/names.txt
Logindex 3, thread 281473735127328, PID 25112, Mon May  6 19:24:56 2024: opened file test/namesB.txt
Warning - file test/namesB.txt Line 2 is empty.
Logindex 4, thread 281473726673184, PID 25112, Mon May  6 19:24:56 2024: opened file test/names1.txt
Warning - file test/namesB.txt Line 5 is empty.
Logindex 5, thread 281473735127328, PID 25112, Mon May  6 19:24:56 2024: closed file test/namesB.txt
Warning - file test/names1.txt Line 3 is empty.
Logindex 6, thread 281473726673184, PID 25112, Mon May  6 19:24:56 2024: closed file test/names1.txt
Logindex 7, thread 281473718219040, PID 25112, Mon May  6 19:24:56 2024: opened file test/names2.txt
Logindex 8, thread 281473718219040, PID 25112, Mon May  6 19:24:56 2024: closed file test/names2.txt

==================== Name Counts ====================
Nicky: 2
Dave Joe: 4
John Smith: 2
Yuan Cheng Chang: 6
Tom Wu: 4
Jenn Xu: 2

 # Test 2, run: ./countnames_threaded test/names_long_redundant?.txt
Logindex 1, thread 281473213985056, PID 25124, Mon May  6 19:28:30 2024: opened file test/names_long_redundant1.txt
Logindex 2, thread 281473197076768, PID 25124, Mon May  6 19:28:30 2024: opened file test/names_long_redundant3.txt
Warning - file test/names_long_redundant1.txt Line 2 is empty.
Warning - file test/names_long_redundant1.txt Line 4 is empty.
Warning - file test/names_long_redundant1.txt Line 6 is empty.
Warning - file test/names_long_redundant1.txt Line 8 is empty.
Logindex 3, thread 281473205530912, PID 25124, Mon May  6 19:28:30 2024: opened file test/names_long_redundant2.txt
Logindex 4, thread 281473197076768, PID 25124, Mon May  6 19:28:30 2024: closed file test/names_long_redundant3.txt
Logindex 5, thread 281473205530912, PID 25124, Mon May  6 19:28:30 2024: closed file test/names_long_redundant2.txt
Logindex 6, thread 281473213985056, PID 25124, Mon May  6 19:28:30 2024: closed file test/names_long_redundant1.txt

==================== Name Counts ====================
DORIS REED: 1
AMY LOPEZ: 1
LILLIAN GRIFFIN: 1
JULIE SANCHEZ: 1
MELISSA KING: 1
RACHEL BARNES: 1
LINDA WILLIAMS: 3
WANDA M PATTERSON: 1
CYNTHIA YOUNG: 1
JOYCE EDWARDS: 1
VIRGINIA GREEN: 1
CHRISTINA RAMIREZ: 1
KATHRYN COLEMAN: 1
JENNIFER DAVIS: 2
KATHY JAMES: 1
HEATHER MORRIS: 1
JUDITH COX: 1
JACQUELINE K LONG: 1
LISA ANDERSON: 2
MARIE TURNER: 1
BONNIE HUGHES: 1
MARGARET MOORE: 2
DEBRA NELSON: 1
KATHLEEN ADAMS: 1
MILDRED BAILEY: 1
ANNA HILL: 1
BARBARA JONES: 3
MARTHA GONZALEZ: 1
ROSE HOWARD: 1
DEBORAH WALKER: 2
MICHELLE CLARK: 2
EVELYN MORGAN: 1
CAROL GARCIA: 2
RUBY WASHINGTON: 1
DENISE KELLY: 1
THERESA WATSON: 1
ELIZABETH BROWN: 3
CATHERINE CAMPBELL: 1
SHARON ROBINSON: 2
RUTH MARTINEZ: 2
KATHERINE RIVERA: 1
HELEN HARRIS: 2
SHIRLEY ALLEN: 1
JUDY GRAY: 1
GLORIA COOK: 1
LOIS BUTLER: 1
PAULA BRYANT: 1
EMILY DIAZ: 1
ANGELA HERNANDEZ: 1
TAMMY SANDERS: 1
JANICE WARD: 1
JOAN COOPER: 1
STEPHANIE MITCHELL: 1
AMANDA CARTER: 1
PAMELA BAKER: 1
LORI WOOD: 1
MARILYN ROSS: 1
KELLY TORRES: 1
TERESA ROGERS: 1
LAURA RODRIGUEZ: 2
IRENE PRICE: 1
ALICE STEWART: 1
ANNIE RUSSELL: 1
PATRICIA JOHNSON: 3
REBECCA SCOTT: 1
BRENDA WRIGHT: 1
CHERYL MURPHY: 1
FRANCES PARKER: 1
DONNA THOMPSON: 2
ANDREA HENDERSON: 1
CAROLYN PEREZ: 1
DIANA ALEXANDER: 1
JULIA FLORES: 1
SARA A PERRY: 1
NICOLE PETERSON: 1
MARIA MILLER: 2
PHYLLIS FOSTER: 1
SARAH LEWIS: 2
MARY SMITH: 3
ANN EVANS: 1
KAREN JACKSON: 2
JEAN BELL: 1
ASHLEY RICHARDSON: 1
NANCY THOMAS: 2
DOROTHY TAYLOR: 2
SUSAN WILSON: 2
TINA SIMMONS: 1
BETTY WHITE: 2
BEVERLY BROOKS: 1
JESSICA HALL: 1
CHRISTINE ROBERTS: 1
JANE BENNETT: 1
LOUISE B JENKINS: 1
ROBIN HAYES: 1
KIMBERLY LEE: 2
NORMA GONZALES: 1
SANDRA MARTIN: 2
JANET PHILLIPS: 1
ANNE J POWELL: 1
DIANE COLLINS: 1

 # Test for memory leak, run: valgrind --leak-check=full ./countnames_threaded test/names_long_redundant?.txt
==25134== HEAP SUMMARY:
==25134==     in use at exit: 0 bytes in 0 blocks
==25134==   total heap usage: 336 allocs, 336 frees, 29,053 bytes allocated
==25134== 
==25134== All heap blocks were freed -- no leaks are possible

 # Measure runtime 3 times, run this command 3 times: time ./countnames_threaded test/names_long_redundant?.txt
real (avg) = (0m0.021s + 0m0.017s + 0m0.004s)/3 = 0m0.014s
user (avg) = (0m0.003s + 0m0.003s + 0m0.001s)/3 = 0m0.003s
sys  (avg) = (0m0.016s + 0m0.015s + 0m0.005s)/3 = 0mo.012s
