import static org.junit.Assert.*;
// gcVb6fgp
import org.junit.Test;
// gcVb6fgp

public class GivenTreeTests
{

   @Test
   public void singleNodeTree()
   {
      Tree t = new Tree();
      t.insert(9);
      
      assertEquals(1, t.size(9));
      assertEquals(0, t.size(8));
      assertEquals(0, t.size(10));
      
      t.insert(15);
      assertEquals(2, t.size(9));
      assertEquals(0, t.size(8));
      assertEquals(0, t.size(10));
      assertEquals(2, t.size(15));
      assertEquals(0, t.size(18));

      t = new Tree();
      t.insert(15);
      t.insert(9);
      assertEquals(2, t.size(9));
      assertEquals(0, t.size(8));
      assertEquals(0, t.size(10));
      assertEquals(2, t.size(15));
      assertEquals(0, t.size(18));
      
      assertEquals(9, t.get(0));
      assertEquals(15, t.get(1));


   }
   
   @Test
   public void oneSplitLeft()
   {
      Tree t = new Tree();
      t.insert(9);
      t.insert(15);
      t.insert(1);

      
      assertEquals(3, t.size(9));
      assertEquals(1, t.size(15));
      assertEquals(0, t.size(17));
      assertEquals(0, t.size(11));

      assertEquals(1, t.size(1));
      assertEquals(0, t.size(0));
      assertEquals(0, t.size(3));
      
      assertEquals(1, t.get(0));
      assertEquals(9, t.get(1));
      assertEquals(15, t.get(2));
      
      assertEquals(3,t.size());
   }
   

   @Test
   public void oneSplitRight()
   {
      Tree t = new Tree();
      t.insert(1);
      t.insert(9);
      t.insert(15);
      
      assertEquals(3, t.size(9));
      assertEquals(1, t.size(15));
      assertEquals(0, t.size(17));
      assertEquals(0, t.size(11));

      assertEquals(1, t.size(1));
      assertEquals(0, t.size(0));
      assertEquals(0, t.size(3));
      
      assertEquals(1, t.get(0));
      assertEquals(9, t.get(1));
      assertEquals(15, t.get(2));
      assertEquals(3,t.size());
   }
   

   @Test
   public void oneSplitMiddle()
   {
      Tree t = new Tree();
      t.insert(1);
      t.insert(15);
      t.insert(9);
      
      
      assertEquals(3, t.size(9));
      assertEquals(1, t.size(15));
      assertEquals(0, t.size(17));
      assertEquals(0, t.size(11));

      assertEquals(1, t.size(1));
      assertEquals(0, t.size(0));
      assertEquals(0, t.size(3));
      
      assertEquals(1, t.get(0));
      assertEquals(9, t.get(1));
      assertEquals(15, t.get(2));
      
     assertEquals(3,t.size());


   }


   @Test
   public void testDuplicates()
   {
      Tree t = new Tree();
      t.insert(1);
      t.insert(9);
      t.insert(15);
      t.insert(13);
      t.insert(20);
      t.insert(7);
      t.insert(4);
      t.insert(9);
      t.insert(15);
      t.insert(1);
      t.insert(9);
      t.insert(15);
      t.insert(13);
      t.insert(20);
      t.insert(7);
      t.insert(4);
      t.insert(13);
      t.insert(20);
      t.insert(7);
      t.insert(4);

      assertEquals(7, t.size(9));
      assertEquals(3, t.size(4));
      assertEquals(3, t.size(15));

      assertEquals(0, t.size(12));
      assertEquals(1, t.size(13));
      assertEquals(0, t.size(14));
      assertEquals(0, t.size(19));
      assertEquals(1, t.size(20));
      assertEquals(0, t.size(21));

      assertEquals(1, t.size(1));
      assertEquals(0, t.size(0));
      assertEquals(0, t.size(3));

      assertEquals(0, t.size(6));
      assertEquals(1, t.size(7));
      assertEquals(0, t.size(8));
      
      assertEquals(1, t.get(0));
      assertEquals(4, t.get(1));
      assertEquals(7, t.get(2));
      assertEquals(9, t.get(3));
      assertEquals(13, t.get(4));
      assertEquals(15, t.get(5));
      assertEquals(20, t.get(6));
      assertEquals(7,t.size());

   }
   
   @Test
   public void nodeSearchTests() {
	   Tree tree = new Tree();
	   
	   tree.insert(9);
	   tree.insert(11);
	   tree.insert(14);
	   tree.insert(18);
	   tree.insert(22);
	   tree.insert(21);
	   tree.insert(23);
	   tree.insert(24);
	   tree.insert(19);
	   tree.insert(15);
	   tree.insert(10);
	   tree.insert(25);
	   tree.insert(16);
	   tree.insert(17);
	   tree.insert(13);
	   tree.insert(12);
	   
	   assertEquals(tree.root.search(16), tree.root.children.get(1).children.get(1));
   }

   
   @Test
   public void bigTree() {
	   Tree tree = new Tree();
	   
	   tree = new Tree();
	   
	   
	   tree.insert(9);
	   tree.insert(11);
	   tree.insert(14);
	   
	   tree.insert(18);
	   tree.insert(22);
	   tree.insert(21);
	   
	   tree.insert(23);
	   tree.insert(24);
	   tree.insert(19);
	   
	   tree.insert(15);
	   tree.insert(10);
	   tree.insert(25);
	   
	   tree.insert(16);
	   tree.insert(17);

	   assertEquals(14, tree.size(18));
	   
	   assertEquals(7, tree.size(11));
	   assertEquals(7, tree.size(15));
	   
	   assertEquals(6, tree.size(22));
	   assertEquals(6, tree.size(24));
	   
	   
	   tree.insert(13);
	   tree.insert(12);
	   
	   assertEquals(17, tree.get(8));
	   assertEquals(13, tree.get(4));
	   assertEquals(18, tree.get(9));
	   
	   
	   assertEquals(16, tree.size(13));
	   assertEquals(16, tree.size(18));
	   assertEquals(2, tree.size(10));
	   assertEquals(2, tree.size(19));
	   
	   assertEquals(10, tree.get(1));
	   
	   assertEquals(16, tree.get(7));
	   assertEquals(17, tree.get(8));
	   assertEquals(18, tree.get(9));
	   assertEquals(19, tree.get(10));
	   
	   
	   tree.insert(20);
	   
	   assertEquals(17, tree.size(18));
	   assertEquals(9, tree.size(13));
	   assertEquals(7, tree.size(22));
   }
   
   @Test
   public void biggerTree() {
	   Tree tree = new Tree();
	   for (int i = 1; i < 101; i++) {
		   tree.insert(i);
	   }
	   assertEquals(100, tree.size());
	   
   }

	
}