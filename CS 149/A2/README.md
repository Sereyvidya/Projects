/** 
 * Description: This assignment taught us how to read and close files, and create methods to read names and count their occurrences.
 * Author names: Sereyvidya Vireak & Osayame Erinmwingbovo
 * Author emails: Sereyviyda.vireak@sjsu.edu & Osayame.erinmwingbovo@sjsu.edu 
 * Last modified date: 02/27/2024 
 * Creation date: 02/24/2024 
 * 
 */
 
 # To compile the program:
 $ gcc -o countnames countnames.c -Wall -Werror
 
 
 # Run each test and its outputs:
 $ ./countnames test/names.txt 
 Warning - Line 2 is empty.
 Warning - Line 5 is empty.
 Nicky : 1
 Dave Joe: 2
 Yuan Cheng Chang: 3
 John Smith: 1
 
 $ ./countnames test/names1.txt 
 Warning - Line 3 is empty.
 Tom Wu: 3
 
 $ ./countnames test/names2.txt 
 Jenn Xu: 2
 Tom Wu: 1
 
 $ ./countnames test/namesB.txt 
 Warning - Line 2 is empty.
 Warning - Line 5 is empty.
 Nicky: 1
 Dave Joe: 2
 Yuan Cheng Chang: 3
 John Smith: 1
 
 $ ./countnames test/names_long.txt 
 MARY SMITH: 1
 : 99
 PATRICIA JOHNSON: 1
 LINDA WILLIAMS: 1
 BARBARA JONES: 1
 ELIZABETH BROWN: 1
 JENNIFER DAVIS: 1
 MARIA MILLER: 1
 SUSAN WILSON: 1
 MARGARET MOORE: 1
 DOROTHY TAYLOR: 1
 LISA ANDERSON: 1
 NANCY THOMAS: 1
 KAREN JACKSON: 1
 BETTY WHITE: 1
 HELEN HARRIS: 1
 SANDRA MARTIN: 1
 DONNA THOMPSON: 1
 CAROL GARCIA: 1
 RUTH MARTINEZ: 1
 SHARON ROBINSON: 1
 MICHELLE CLARK: 1
 LAURA RODRIGUEZ: 1
 SARAH LEWIS: 1
 KIMBERLY LEE: 1
 DEBORAH WALKER: 1
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

 $ ./countnames test/names_long_redundant.txt
 Warning - Line 97 is empty.
 John Smith: 1
 Emily Davis: 1
 Michael Johnson: 1
 Sophia Lee: 1
 Daniel Rodriguez: 1
 Mia Hernandez: 1
 David Kim: 1
 Olivia Brown: 1
 Anthony Martinez: 1
 Evelyn Jackson: 1
 Christopher Gonzalez: 1
 Avery Taylor: 2
 Andrew Lee: 2
 Aria Perez: 1
 Matthew Davis: 1
 Victoria Wilson: 1
 William Anderson: 1
 Madison Green: 1
 Joshua Wright: 1
 Isabella Clark: 1
 Nathan Davis: 2
 Natalie Adams: 1
 Ethan Martin: 1
 Chloe Baker: 1
 Ryan Lee: 1
 Samantha Perez: 1
 Benjamin Wright: 1
 Aaliyah Garcia: 1
 Jacob Cooper: 1
 Elizabeth Martinez: 1
 Tyler Thompson: 1
 Hannah Scott: 1
 Alexander Rodriguez: 1
 Makayla Turner: 1
 Nicholas Nguyen: 1
 Sofia Hernandez: 1
 Brandon Smith: 1
 Grace Lee: 1
 Christian Martinez: 1
 Ava Jones: 1
 William Turner: 1
 Ella Wilson: 1
 Jonathan Lee: 2
 Lily Johnson: 1
 Samuel Davis: 1
 Avery Adams: 2
 Gabriella Clark: 1
 Dylan Anderson: 1
 Lauren Kim: 1
 James Lewis: 1
 Makenna Wright: 1
 Luke Davis: 1
 Aaliyah Brown: 1
 Christopher Anderson: 1
 Abigail Perez: 1
 Daniel Lee: 1
 Jayden Robinson: 1
 Leah Lee: 1
 Nicholas Jackson: 1
 Avery Lee: 1
 Emma Hernandez: 1
 Ethan Brown: 1
 Madison Martinez: 1
 Lucas Davis: 1
 Aria Wright: 1
 Ryan Rodriguez: 1
 Sophia Turner: 1
 Benjamin Davis: 1
 Ella Garcia: 1
 Chloe Kim: 1
 William Scott: 1
 Ava Smith: 1
 David Wright: 1
 Natalie Martin: 1
 Isabella Adams: 1
 Brandon Davis: 1
 Makayla Hernandez: 1
 Alexander Clark: 1
 Emily Rodriguez: 1
 Christopher Lee: 1
 Olivia Lee: 1
 Samantha Martinez: 1
 Daniel Anderson: 1
 Mia Lee: 1
 Jacob Wright: 1
 Elizabeth Adams: 1
 Tyler Martinez: 1
 Hannah Garcia: 1
 Nicholas Davis: 1
 Sofia Thompson: 1
 Brandon Brown: 1
 
 $ ./countnames test/names_long_redundant1.txt
 Warning - Line 2 is empty.
 Warning - Line 4 is empty.
 Warning - Line 6 is empty.
 Warning - Line 8 is empty.
 MARY SMITH: 1
 PATRICIA JOHNSON: 1
 LINDA WILLIAMS: 1
 BARBARA JONES: 1
 ELIZABETH BROWN: 1
 JENNIFER DAVIS: 1
 MARIA MILLER: 1
 SUSAN WILSON: 1
 MARGARET MOORE: 1
 DOROTHY TAYLOR: 1
 LISA ANDERSON: 1
 NANCY THOMAS: 1
 KAREN JACKSON: 1
 BETTY WHITE: 1
 HELEN HARRIS: 1
 SANDRA MARTIN: 1
 DONNA THOMPSON: 1
 CAROL GARCIA: 1
 RUTH MARTINEZ: 1
 SHARON ROBINSON: 1
 MICHELLE CLARK: 1
 LAURA RODRIGUEZ: 1
 SARAH LEWIS: 1
 KIMBERLY LEE: 1
 DEBORAH WALKER: 1
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

 $ ./countnames test/names_long_redundant2.txt
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
 
 $ ./countnames test/names_long_redundant3.txt
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
 MARY SMITH: 2
 PATRICIA JOHNSON: 2
 LINDA WILLIAMS: 2
 BARBARA JONES: 2
 ELIZABETH BROWN: 2
 JENNIFER DAVIS: 1
 MARIA MILLER: 1
 SUSAN WILSON: 1
 MARGARET MOORE: 1
 DOROTHY TAYLOR: 1
 LISA ANDERSON: 1
 NANCY THOMAS: 1
 KAREN JACKSON: 1
 BETTY WHITE: 1
 HELEN HARRIS: 1
 SANDRA MARTIN: 1
 DONNA THOMPSON: 1
 CAROL GARCIA: 1
 RUTH MARTINEZ: 1
 SHARON ROBINSON: 1
 MICHELLE CLARK: 1
 LAURA RODRIGUEZ: 1
 SARAH LEWIS: 1
 KIMBERLY LEE: 1
 DEBORAH WALKER: 1

 # Average runtime on longest given test file (test/names_long.txt):
 (0.006 + 0.006 + 0.005) = 0.00567s