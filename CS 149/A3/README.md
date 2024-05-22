/** Description: This assignment taught us how to read and close files, 
 * and create methods to read names and count their occurrences.
 * It also taught us how to do this faster through pipes and forking. 
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyvidya.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu
 * Last modified date: 03/09/2024
 * Creation date: 03/13/2024
 */
 
# To compile the program: $ gcc -o countnames countnames.c -Wall -Werror
 
# Examples of running tests and its outputs: 

Command: $ ./countnames test/names.txt test/names.txt test/names.txt
Expected Output:
Warning - file test/names.txt Line 2 is empty.
Warning - file test/names.txt Line 5 is empty.
Warning - file test/names.txt Line 2 is empty.
Warning - file test/names.txt Line 5 is empty.
Warning - file test/names.txt Line 2 is empty.
Warning - file test/names.txt Line 5 is empty.
Nicky : 3
Dave Joe: 6
Yuan Cheng Chang: 9
John Smith: 3

Command: ./countnames_parallel test/names1.txt test/names2.txt test/namesB.txt
Expected Output:
Warning - file test/names1.txt Line 3 is empty.
Warning - file test/namesB.txt Line 2 is empty.
Warning - file test/namesB.txt Line 5 is empty.
Tom Wu: 4
Jenn Xu: 2
Nicky: 1
Dave Joe: 2
Yuan Cheng Chang: 3
John Smith: 1

Command: ./countnames_parallel test/names_long_redundant1.txt test/names_long_redundant2.txt test/names_long_redundant3.txt
Expected Output:
Warning - file test/names_long_redundant1.txt Line 2 is empty.
Warning - file test/names_long_redundant1.txt Line 4 is empty.
Warning - file test/names_long_redundant1.txt Line 6 is empty.
Warning - file test/names_long_redundant1.txt Line 8 is empty.
MARY SMITH: 3
PATRICIA JOHNSON: 3
LINDA WILLIAMS: 3
BARBARA JONES: 3
ELIZABETH BROWN: 3
JENNIFER DAVIS: 2
MARIA MILLER: 2
SUSAN WILSON: 2
MARGARET MOORE: 2
DOROTHY TAYLOR: 2
LISA ANDERSON: 2
NANCY THOMAS: 2
KAREN JACKSON: 2
BETTY WHITE: 2
HELEN HARRIS: 2
SANDRA MARTIN: 2
DONNA THOMPSON: 2
CAROL GARCIA: 2
RUTH MARTINEZ: 2
SHARON ROBINSON: 2
MICHELLE CLARK: 2
LAURA RODRIGUEZ: 2
SARAH LEWIS: 2
KIMBERLY LEE: 2
DEBORAH WALKER: 2
JESSICA HALL: 1
SHIRLEY ALLEN: 1
CYNTHIA YOUNG: 1
ANGELA HERNANDEZ: 1
MELISSA KING: 1
BRENDA WRIGHT: 1
AMY LOPEZ: 1
ANNA HILL: 1
REBECCA SCOTT: 1
VIRGINIA GREEN: 1
KATHLEEN ADAMS: 1
PAMELA BAKER: 1
MARTHA GONZALEZ: 1
DEBRA NELSON: 1
AMANDA CARTER: 1
STEPHANIE MITCHELL: 1
CAROLYN PEREZ: 1
CHRISTINE ROBERTS: 1
MARIE TURNER: 1
JANET PHILLIPS: 1
CATHERINE CAMPBELL: 1
FRANCES PARKER: 1
ANN EVANS: 1
JOYCE EDWARDS: 1
DIANE COLLINS: 1
ALICE STEWART: 1
JULIE SANCHEZ: 1
HEATHER MORRIS: 1
TERESA ROGERS: 1
DORIS REED: 1
GLORIA COOK: 1
EVELYN MORGAN: 1
JEAN BELL: 1
CHERYL MURPHY: 1
MILDRED BAILEY: 1
KATHERINE RIVERA: 1
JOAN COOPER: 1
ASHLEY RICHARDSON: 1
JUDITH COX: 1
ROSE HOWARD: 1
JANICE WARD: 1
KELLY TORRES: 1
NICOLE PETERSON: 1
JUDY GRAY: 1
CHRISTINA RAMIREZ: 1
KATHY JAMES: 1
THERESA WATSON: 1
BEVERLY BROOKS: 1
DENISE KELLY: 1
TAMMY SANDERS: 1
IRENE PRICE: 1
JANE BENNETT: 1
LORI WOOD: 1
RACHEL BARNES: 1
MARILYN ROSS: 1
ANDREA HENDERSON: 1
KATHRYN COLEMAN: 1
LOUISE B JENKINS: 1
SARA A PERRY: 1
ANNE J POWELL: 1
JACQUELINE K LONG: 1
WANDA M PATTERSON: 1
BONNIE HUGHES: 1
JULIA FLORES: 1
RUBY WASHINGTON: 1
LOIS BUTLER: 1
TINA SIMMONS: 1
PHYLLIS FOSTER: 1
NORMA GONZALES: 1
PAULA BRYANT: 1
DIANA ALEXANDER: 1
ANNIE RUSSELL: 1
LILLIAN GRIFFIN: 1
EMILY DIAZ: 1
ROBIN HAYES: 1

# Average Runtime
Command: ./countnames_parallel test/names_long_redundant1.txt test/names_long_redundant2.txt test/names_long_redundant3.txt
Run #1: 0.010s, Run #2: 0.008s, Run #3: 0.010s
Average Run: (0.010 + 0.008 + 0.010)/3 = 0.0933s