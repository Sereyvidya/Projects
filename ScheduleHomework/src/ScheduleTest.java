import static org.junit.Assert.*;
// gcVb6fgp
import org.junit.Test;
//gcVb6fgp
public class ScheduleTest {

	//O(n) = (E + V) * number of requires method
	
	@Test 	
	public void simpleSchedule() {
		
		System.out.println("simpleSchedule");
		
		Schedule schedule = new Schedule();
		
		schedule.insert(8); 					
		//adds job 0 with time 8
		
		Schedule.Job j1 = schedule.insert(3); 
		//adds job 1 with time 3
		
		schedule.insert(5); 					
		//adds job 2 with time 5
		
		assertEquals(schedule.finish(), 8);	
		//should return 8
		
		schedule.get(0).requires(schedule.get(2)); 
		//job 2 must precede job 0
		
		assertEquals(schedule.finish(), 13); 
		//should return 13 (job 0 cannot start until time 5)
		
		schedule.get(0).requires(j1); 
		//job 1 must precede job 0
		
		assertEquals(schedule.finish(), 13);
		//should return 13
		
		assertEquals(schedule.get(0).start(), 5); 
		//should return 5
		
		assertEquals(j1.start(), 0); 
		//should return 0
		
		assertEquals(schedule.get(2).start(), 0); 
		//should return 0
		
		j1.requires(schedule.get(2)); 
		//job 2 must precede job 1
		
		assertEquals(schedule.finish(), 16); 
		//should return 16

		assertEquals(schedule.get(0).start(), 8); 
		//should return 8
		
		assertEquals(schedule.get(1).start(), 5);
		//should return 5
		
		assertEquals(schedule.get(2).start(), 0); 
		//should return 0
		
		schedule.get(1).requires(schedule.get(0)); 
		//job 0 must precede job 1 (creates loop)
		
		assertEquals(schedule.finish(), -1); 
		//should return -1
		
		assertEquals(schedule.get(0).start(), -1); 
		//should return -1
		
		assertEquals(schedule.get(1).start(), -1); 
		//should return -1
		
		assertEquals(schedule.get(2).start(), 0); 
		//should return 0 (no loops in prerequisites)
	
	}
	
	@Test
	public void cycle() {
	
	    System.out.println("cycle");
	
		Schedule schedule = new Schedule();
		schedule.insert(1);
		schedule.insert(2);
		schedule.insert(3);
		
		schedule.get(0).requires(schedule.get(1));
		schedule.get(1).requires(schedule.get(2));
		schedule.get(2).requires(schedule.get(0));
		
		assertEquals(schedule.finish(), -1);
		
		schedule.insert(4);
		schedule.get(0).requires(schedule.get(3));
		
		assertEquals(schedule.finish(), -1);
		
		assertEquals(schedule.get(3).start(), 0);
		
		schedule.insert(5);
		schedule.get(3).requires(schedule.get(4));
		
		assertEquals(schedule.get(4).start(), 0);
		assertEquals(schedule.get(3).start(), 5);
	}
	
	@Test
	public void twoGroupsInTopSort() {
		
		System.out.println("twoGroupsInTopSort");
		
		Schedule schedule = new Schedule();
		schedule.insert(1);
		schedule.insert(2);
		schedule.insert(3);
		schedule.insert(4);
		schedule.insert(5);
		schedule.insert(6);
		
		schedule.get(0).requires(schedule.get(2));
		schedule.get(2).requires(schedule.get(4));
		schedule.get(1).requires(schedule.get(3));
		schedule.get(3).requires(schedule.get(5));
		
		assertEquals(schedule.get(0).start(), 8);	//5->3->1
		assertEquals(schedule.get(1).start(), 10);	//6->4->2
		
		assertEquals(schedule.finish(), 12);
	}
	
	@Test
	public void startWithNoFinish() {
		
		System.out.println("startWithNoFinish");
		
		Schedule schedule = new Schedule();
		schedule.insert(8);
		schedule.insert(5);
		schedule.insert(3);
		schedule.get(2).requires(schedule.get(0));
		
		assertEquals(8, schedule.get(2).start());
		
		schedule.get(0).requires(schedule.get(1));
		
		assertEquals(13, schedule.get(2).start());
		
		assertEquals(16, schedule.finish());
	}
	
}
