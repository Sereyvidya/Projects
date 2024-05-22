import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap
{
   private ArrayList<Student> students;
   
   public MaxHeap(int capacity)
   {
      students = new ArrayList<Student>(capacity);
   }
      
   public MaxHeap(Collection<Student> collection)
   {
      students = new ArrayList<Student>(collection);
      for(int i = size() - 1; i >= 0; i--)
      {
         students.get(i).setIndex(i);
         if (i >= 0 && i <= size()/2 - 1) {
        	 maxHeapify(i);
         }
      }
   }
   
   public Student getMax()
   {
      if(size() < 1)
      {
         throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
      }
      return students.get(0);
   }
   
   public Student extractMax()
   {
      Student value = getMax();
      students.set(0,students.get(size()-1));
      students.get(0).setIndex(0);
      students.remove(size()-1);
      maxHeapify(0);
      return value;
   }
    
   public int size()
   {
      return students.size();
   }
   
   public void insert(Student elt)
   {
	   elt.setIndex(students.size());
	   students.add(elt);
	   swapIterativelyUpwards(elt.getIndex());
   }
   
   public void addGrade(Student elt, double gradePointsPerUnit, int units)
   {
	   if (gradePointsPerUnit < 0 || units < 0) {
		   throw new IllegalArgumentException("Grade points per unit or number of units can not be negative!");
	   }
	  
	   elt.addGrade(gradePointsPerUnit, units);
	   int bot = elt.getIndex();
	   maxHeapify(bot);
	   swapIterativelyUpwards(bot);
   }
   
   private void swapIterativelyUpwards(int i) {	   
	   while (students.get(parent(i)).compareTo(students.get(i)) < 0) {
		   swap(parent(i), i);
		   i = parent(i);
	   }
   }
   
   private int parent(int index)
   {
      return (index - 1)/2;
   }
   
   private int left(int index)
   {
      return 2 * index + 1;
   }
   
   private int right(int index)
   {
      return 2 * index + 2;
   }
   
   private void swap(int from, int to)
   {      
      Student val = students.get(from);
      val.setIndex(to);
      students.get(to).setIndex(from);
      
      students.set(from, students.get(to));
      students.set(to, val);
      
   }
   
   private void maxHeapify(int index)
   {
      int left = left(index);
      int right = right(index);
      int largest = index;
      if (left <  size() && students.get(left).compareTo(students.get(largest)) > 0)
      {
         largest = left;
      }
      if (right <  size() && students.get(right).compareTo(students.get(largest)) > 0)
      {
         largest = right;
      }
      if (largest != index)
      {
         swap(index, largest);
         maxHeapify(largest);
      }  
   }   
   
   
   
}