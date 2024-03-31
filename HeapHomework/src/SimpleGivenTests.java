import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
// gcVb6fgp

public class SimpleGivenTests
{
	

   @Test
   public void oneStudent()
   {
      MaxHeap heap = new MaxHeap(10);
      heap.insert(new Student("Susan", 3.5, 60));
      assertEquals(3.5, heap.extractMax().gpa(), .000001);
      assertEquals(0, heap.size());
   }

   @Test
   public void aInsertAFewStudents()
   {
      MaxHeap heap = new MaxHeap(10);
      heap.insert(new Student("Susan", 3.5, 60));
      heap.insert(new Student("Ben", 3.4, 70));
      heap.insert(new Student("Reed", 4.0, 120));
      heap.insert(new Student("Johnny", 1.2, 50));
      assertEquals(4.0, heap.extractMax().gpa(), .000001);
      assertEquals(3.5, heap.extractMax().gpa(), .000001);
      heap.insert(new Student("Billy", 2.7, 20));
      assertEquals(3.4, heap.extractMax().gpa(), .000001);
      assertEquals(2.7, heap.extractMax().gpa(), .000001);
      assertEquals(1.2, heap.extractMax().gpa(), .000001);
   }

   @Test
   public void exceptionTest()
   {
      MaxHeap heap = new MaxHeap(10);
      heap.insert(new Student("Ben", 3.4, 70));
      assertEquals(3.4, heap.extractMax().gpa(), .000001);
      try {
    	  heap.extractMax();
    	  fail("You shouldn't reach this line, an IndexOutOfBoundsException should have been thrown.");
      } catch (IndexOutOfBoundsException except) {
    	  assertEquals(except.getMessage(), "No maximum value:  the heap is empty.");
      }

   }
   
   @Test
   public void changeKeyTest()
   {
	   MaxHeap heap = new MaxHeap(10);
	   Student susan = new Student("Susan", 3, 6);
	   Student ben = new Student("Ben", 2.4, 10);
	   Student reed = new Student("Reed", 3.3, 3);
	   Student johnny = new Student("Johnny", 1, 4);
	   heap.insert(susan);;
	   heap.insert(ben);
	   heap.insert(johnny);
	   heap.insert(reed);
	   assertEquals(reed, heap.getMax());
	   heap.addGrade(susan, 4, 3);  //should give her a 3.333333333 gpa
	   assertEquals(susan, heap.getMax());
	   assertEquals(3.33333333, heap.extractMax().gpa(), .000001);
	   heap.addGrade(reed, .7, 3);  //should give him a 2.0
	   heap.addGrade(johnny,  4,  4);  //should give him a 2.5
	   assertEquals(2.5, heap.extractMax().gpa(), .000001);
	   assertEquals(2.4, heap.extractMax().gpa(), .000001);
	   assertEquals(2.0, heap.extractMax().gpa(), .000001);
   }
   
   @Test
   public void insertTestInOrderTest() {
	   MaxHeap heap = new MaxHeap(10);
	   Student jane = new Student("Jane", 3, 6);
	   Student bob = new Student("Bob", 2.7, 10);
	   Student leo = new Student("Leo", 2.4, 18);
	   heap.insert(jane);
	   heap.insert(bob);
	   heap.insert(leo);
	   assertEquals(3, heap.extractMax().gpa(), .000001);
	   assertEquals(2.7, heap.extractMax().gpa(), .000001);
	   assertEquals(2.4, heap.extractMax().gpa(), .000001);
   }
   
   @Test
   public void insertTestReverseOrderTest() {
	   MaxHeap heap = new MaxHeap(10);
	   Student jane = new Student("Jane", 3, 6);
	   Student bob = new Student("Bob", 2.7, 10);
	   Student leo = new Student("Leo", 2.4, 18);
	   heap.insert(leo);
	   heap.insert(bob);
	   heap.insert(jane);
	   assertEquals(3, heap.extractMax().gpa(), .000001);
	   assertEquals(2.7, heap.extractMax().gpa(), .000001);
	   assertEquals(2.4, heap.extractMax().gpa(), .000001);
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void changeKeyIITest() {
	   MaxHeap heap = new MaxHeap(10);
	   Student jane = new Student("Jane", 2.6, 6);
	   heap.insert(jane);
	   heap.addGrade(jane, -1, 1);
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void changeKeyIIITest() {
	   MaxHeap heap = new MaxHeap(10);
	   Student jane = new Student("Jane", 2.6, 6);
	   heap.insert(jane);
	   heap.addGrade(jane, 1, -1);   
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void changeKeyIVTest() {
	   MaxHeap heap = new MaxHeap(10);
	   Student jane = new Student("Jane", 2.6, 6);
	   heap.insert(jane);
	   heap.addGrade(jane, -1, -1);   
   }
   
   @Test
   public void studentName() {
	   Student jane = new Student("Jane", 2.6, 6);
	   assertEquals("Jane", jane.getName());
   }
   
   @Test
   public void studentGPA() {
	   Student jane = new Student("Jane", 2.6, 6);
	   assertEquals(2.6, jane.gpa(), .000001);
	   Student mary = new Student("Mary", 0, 0);
	   assertEquals(0, mary.gpa(), .000001);
   }
   
   @Test
   public void students() {
	   Student jane = new Student("Jane", 2.6, 6);
	   Student bob = new Student("Bob", 2.7, 10);
	   Student leo = new Student("Leo", 2.4, 18);
	   Student mary = new Student("Mary", 2.6, 10);
	   Student josh = new Student("Josh");
	   assertEquals("Josh", josh.getName());
	   assertEquals(0, jane.compareTo(jane));
	   assertEquals(0, jane.compareTo(mary));
	   assertEquals(-12, leo.compareTo(jane));
	   assertEquals(14, bob.compareTo(jane));	   
   }
   
   @Test
   public void maxHeapConstructionTest() {
	   ArrayList<Student> al = new ArrayList<Student>(); 
	   Student susan = new Student("Susan", 3, 6);
	   Student ben = new Student("Ben", 2.4, 10);
	   Student reed = new Student("Reed", 3.3, 3);
	   Student johnny = new Student("Johnny", 1, 4);
	   al.add(susan);
	   al.add(ben);
	   al.add(reed);
	   al.add(johnny);
	   MaxHeap heap = new MaxHeap(al);
	   assertEquals(3.3, heap.extractMax().gpa(), .000001);
   }
}