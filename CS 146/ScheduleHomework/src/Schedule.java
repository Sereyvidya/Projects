import java.util.*;

public class Schedule {

    public class Job {
        public int startTime = 0;
        public int time = 0;
        public int inDegrees = 0;
        public boolean finished = false;
        
        public ArrayList<Job> postReqs = new ArrayList<>();
        public ArrayList<Job> preReqs = new ArrayList<>();

        // O(n) = 1
        public void requires(Job j) {
            this.preReqs.add(j);
            j.postReqs.add(this);
            call = true;
        }

        // O(n) = V + E
        public int start() {
        	if (call) {
        		finish = sortAndRelax();
        	}
            return this.startTime;
        }

        // O(n) = 1
        private void relax(Job other) {
            if (this.startTime + this.time > other.startTime) {
                if (other.finished) {
                    return;
                }
                other.startTime = this.startTime + this.time;
                
                scheduleTime = Math.max(other.startTime + other.time, scheduleTime);
            }
        }
    }

    ArrayList<Job> jobs = new ArrayList<>();
    
    boolean finish = true;
    boolean call = true;
    int scheduleTime = 0;
    
    // O(n) = 1
    public Job insert(int time) {
        Job job = new Job();
        job.time = time;
        jobs.add(job);
        
        scheduleTime = Math.max(time, scheduleTime);
        return job;
    }

    // O(n) = 1
    public Job get(int index) {
        return jobs.get(index);
    }

    // O(n) = 1
    public int finish() {
    	if (call) {
    		finish = sortAndRelax();
    	}
        if (!finish) {
            return -1;
        }
        return scheduleTime;
    }

    // O(n) = V + E
    private LinkedList<Job> initialize() {
        LinkedList<Job> list = new LinkedList<>();
        for (Job job : jobs) {
        	if (job.startTime != -1) {
        		job.startTime = 0;
                job.finished = false;
                job.inDegrees = job.preReqs.size();
                list.add(job);
        	}
        }
        return list;
    }
    
    // O(n) = V + E
    private boolean sortAndRelax() {
        LinkedList<Job> copy = initialize();
        LinkedList<Job> curr;
        call = false;

        while (copy.size() > 0) {
            curr = new LinkedList<>();

            for (Job job : copy) {
                if (job.inDegrees == 0) {
                	job.finished = true;
                    for (Job k : job.postReqs) {
                        job.relax(k);
                        k.inDegrees -= 1;
                    }
                    curr.add(job);
                }
            }
            if (curr.size() == 0) {
                for (Job job : copy) {
                    job.startTime = -1;
                    job.finished = true;
                }
                return false;
            }
            for (Job job : curr) {
                copy.remove(job);
            }
        }
        return finish;
    }
}








































//private ArrayList<Job> dagsssp() {
//ArrayList<Job> sorted = topSort();
//call = false;
//for (Job job : sorted) {
//  job.finished = true;
//  for (Job k : job.postReqs) {
//      job.relax(k);
//  }
//}
//return sorted;
//}



//import java.util.*;
//
//public class Schedule {
//	public class Job {
//		public int startTime = 0;
//		public int time = 0;
//		public int inDegrees = 0;
//		public boolean finished = false;
//		
//		public ArrayList<Job> postReqs = new ArrayList<>(); 
//		public ArrayList<Job> preReqs = new ArrayList<>();
//		
//		// O(n) = V + E
//		public void requires(Job job) {	
//			this.preReqs.add(job);
//			job.postReqs.add(this);
//			call = true;
//		}
//		
//		// O(n) = 1
//		public int start() {
//			if (call) {
//				sortAndRelax();
//			}
//			return this.startTime;
//		}
//		
//		// O(n) = 1
//		private void relax(Job other) {		
//			if (this.startTime + this.time > other.startTime) {
//				if (other.finished) {
//					return;
//				}
//				other.startTime = this.startTime + this.time;
//				
//				scheduleTime = Math.max(other.startTime + other.time, scheduleTime);
//			}		
//		}
//		
//	}
//	
//	ArrayList<Job> jobs = new ArrayList<>();
//
//	boolean finish = true;
//	boolean call = false;
//	int scheduleTime = 0;
//	
//	// O(n) = 1
//	public Job insert(int time) {
//		Job job = new Job();
//		job.time = time;
//		jobs.add(job);
//
//		scheduleTime = Math.max(time, scheduleTime);
//		return job;
//	}
//	
//	// O(n) = 1
//	public Job get(int index) {
//		return jobs.get(index);
//	}
//	
//	// O(n) = 1
//	public int finish() {
//		boolean ans = true;
//		if (call) {
//			ans = sortAndRelax();
//		}
//		if (finish && ans) {
//			return scheduleTime;
//		}
//		finish = false;
//		return -1;
//	}
//	
//	// O(n) = V
//	private LinkedList<Job> initialize() {
//		LinkedList<Job> list = new LinkedList<>();
//		for (Job job : jobs) {
//			if (job.startTime != -1) {
//				job.startTime = 0; 	//distance
//				job.finished = false;
//				job.inDegrees = job.preReqs.size(); 
//				list.add(job);
//			}
//		}
//		return list;
//	}
//	
//	// O(n) = V + E
//	private boolean sortAndRelax() { 
//		LinkedList<Job> copy = initialize();
//		LinkedList<Job> curr;
//		call = false;
//		
//		while (copy.size() > 0) {
//			curr = new LinkedList<>();
//			for (Job job : copy) {
//				if (job.inDegrees == 0) {
//					job.finished = true;
//					for (Job k : job.postReqs) {
//						job.relax(k);	
//						k.inDegrees -= 1;
//					}
//					curr.add(job);
//				}
//			}
//			if (curr.size() == 0) {
//				for (Job job : copy) {
//					job.startTime = -1; 	//cycle => can't start
//					job.finished = true;
//				}
//				return false;
//			}
//			for (Job job : curr) {
//				copy.remove(job);
//			}	
//		}
//		
//		return true;
//	}
//}









//private void dagsssp() {
//LinkedList<Job> sorted = topSort();
//for (Job job : sorted) {
//	job.finished = true;
//	for (Job k : job.postReqs) {
//		job.relax(k);	
//	}
//}
//}



/*
 * 		public void requires(Job j) {	//update indegree when something is added and then call topsort without initialize
			this.preReqs.add(j);
			this.inDegrees = preReqs.size();
			
			j.postReqs.add(this);
			ends.remove(j);
			
			if(loopCheck(this, j, false)) {
				finish = false;
			}
			
			j.relax(this, false);
		}
		
		public int start() {
			climb(this, this.parent);
			return this.startTime;
		}
		
		// on is true when dagsssp() calls relax, otherwise it's false
		private void relax(Job other, boolean on) {		
			if (this.startTime + this.time > other.startTime) {
				if (other.finished && on) {
					return;
				}
				other.startTime = this.startTime + this.time;
				other.parent = this;
				
				scheduleTime = Math.max(other.startTime + other.time, scheduleTime);
			}		
		}
		
  		private void climb(Job curr, Job parent) {
			if (parent != null) {
				if (parent.parent != null) {
					climb(curr.parent, curr.parent.parent);
				}
				parent.relax(curr, false);
			}
		}
		
		private boolean loopCheck(Job job, Job ancestor, boolean loop) {
			if (ancestor != null) {
				if (ancestor == job) {
					job.startTime = -1;
					return true;
				}
				else {
					loop = loopCheck(job, ancestor.parent, loop);
					if (loop) {
						ancestor.startTime = -1;
					}
				}
			}
			return loop;
		}
 */

/*
 
	private LinkedList<Job> initialize(Job curr, LinkedList<Job> list) {
		//LinkedList<Job> list = new LinkedList<>();
		for (Job job : curr.postReqs) {
			
			System.out.println("aaa");
			
			if (job.startTime != -1 && !job.discovered) {
				job.parent = null;	//pi
				//job.startTime = 0; 	//distance
				job.finished = false;
				
				job.discovered = true;
				
				job.inDegrees += 1; 
				list.add(job);
				list = initialize(job, list);
			}
		}
		return list;
	}
	
	private boolean sortAndRelax(Job j) { 
		LinkedList<Job> empty = new LinkedList<>();
		empty.add(j);
		LinkedList<Job> copy = initialize(j, empty);
		
		System.out.println(copy.size());
		
		LinkedList<Job> curr;
		
		while (copy.size() > 0) {
			curr = new LinkedList<>();
			for (Job job : copy) {
				if (job.inDegrees == 0) {
					
					System.out.println("   " + job.time);
					
					job.finished = true;
					for (Job k : job.postReqs) {
						job.relax(k);	
						k.inDegrees -= 1;
					}
					curr.add(job);
				}
			}
			if (curr.size() == 0) {
				for (Job job : copy) {
					job.startTime = -1; 	//cycle => can't start
					job.finished = true;
				}
				return false;
			}
			for (Job job : curr) {
				copy.remove(job);
				
				job.inDegrees = 0; 
			}	
		}
		
		return true;
	}
*/



/*
import java.util.*;

public class Schedule {
	public class Job {
		public Job parent = null;
		public int time = 0;
		
		public boolean visited = false;
		public int inDegrees = 0;
		public ArrayList<Job> postReqs = new ArrayList<>(); 
		public ArrayList<Job> preReqs = new ArrayList<>();
		
		public void requires(Job j) {
			this.preReqs.add(j);
			j.postReqs.add(this);
		}
		
		public int start() {
			if (visited) {
				return -1;
			}
			visited = true;
			
			int earliest = 0;
			int currTime = 0;
			int startTime = 0;
			
			for (Job j : preReqs) {
				currTime = 0;
				startTime = j.start();
				if (startTime == -1) {
					earliest = -1;
					break;
				}
				currTime += startTime + j.time;	
				earliest = Math.max(earliest, currTime);
			}
			visited = false;
			
			return earliest;
		}
	}
	
	ArrayList<Job> jobs = new ArrayList<>();
	
	public Job insert(int time) {
		Job job = new Job();
		job.time = time;
		jobs.add(job);
		return job;
	}
	
	public Job get(int index) {
		return jobs.get(index);
	}
	
	public int finish() {
		ArrayList<Job> sortedList = topSort();
		if (sortedList == null) {
			return -1;
		}
		
		int longestTime = 0;
		int startTime = 0;
		
		for (Job j : sortedList) {
			startTime = j.start();
			
			if (startTime == -1) {
				return -1;
			}
			longestTime = Math.max(j.start() + j.time, longestTime);
		}
		
		return longestTime;
	}
	
	private void initialize() {
		for (Job job : jobs) {
			job.parent = null;
			job.inDegrees = job.preReqs.size();
		}
	}
	
	private void relax()
	
	private ArrayList<Job> topSort() {
		ArrayList<Job> sorted = new ArrayList<>();
		ArrayList<Job> copy = new ArrayList<>(jobs);
		for (Job j : copy) {
			j.inDegrees = j.preReqs.size();
		}
		
		while (sorted.size() != jobs.size()) {
			ArrayList<Job> curr = new ArrayList<>();
			
			for (Job j : copy) {
				if (j.inDegrees == 0) {
					sorted.add(j);
					curr.add(j);
				}
			}
			if (curr.size() == 0) {
				break;
			}
			for (Job j : curr) {
				for (Job k : j.postReqs) {
					k.inDegrees -= 1;
				}
				copy.remove(j);
			}	
		}
		
		return sorted;
	}
}

	public static void main(String[] args) {
		
		Schedule schedule = new Schedule();
		
		schedule.insert(8); 					
		//adds job 0 with time 8
		
		Schedule.Job j1 = schedule.insert(3); 
		//adds job 1 with time 3
		
		schedule.insert(5); 					
		//adds job 2 with time 5
		
		System.out.println(schedule.finish());	
			//should return 8
		
		schedule.get(0).requires(schedule.get(2)); 
		//job 2 must precede job 0
		
		System.out.println(schedule.finish()); 
			//should return 13 (job 0 cannot start until time 5)
		
		schedule.get(0).requires(j1); 
		//job 1 must precede job 0
		
		System.out.println(schedule.finish()); 
			//should return 13
		
		System.out.println(schedule.get(0).start()); 
			//should return 5
		
		System.out.println(j1.start()); 
			//should return 0
		
		System.out.println(schedule.get(2).start()); 
			//should return 0
		
		j1.requires(schedule.get(2)); 
		//job 2 must precede job 1
		
		System.out.println(schedule.finish()); 
			//should return 16
		
		System.out.println(schedule.get(0).start()); 
			//should return 8
		
		System.out.println(schedule.get(1).start());
			//should return 5
		
		System.out.println(schedule.get(2).start()); 
			//should return 0
		
		schedule.get(1).requires(schedule.get(0)); 
		//job 0 must precede job 1 (creates loop)
		
		System.out.println(schedule.finish()); 
			//should return -1
		
		System.out.println(schedule.get(0).start()); 
			//should return -1
		
		System.out.println(schedule.get(1).start()); 
			//should return -1
		
		System.out.println(schedule.get(2).start()); 
		//should return 0 (no loops in prerequisites)
		 
		
	}
*/

/*
 * 	private ArrayList<Job> topSort() {
		ArrayList<Job> sorted = new ArrayList<>();
		ArrayList<Job> copy = new ArrayList<>(jobs);
		for (Job j : copy) {
			j.inDegrees = j.preReqs.size();
		}
		
		while (sorted.size() != jobs.size()) {
			ArrayList<Job> curr = new ArrayList<>();
			
			for (Job j : copy) {
				if (j.inDegrees == 0) {
					sorted.add(j);
					curr.add(j);
				}
			}
			if (curr.size() == 0) {
				return null; // break maybe? just return what's sorted
			}
			for (Job j : curr) {
				for (Job k : j.postReqs) {
					k.inDegrees -= 1;
				}
				copy.remove(j);
			}	
		}
		
		return sorted;
	}
 */



//Testing Topological Sort

//Schedule s = new Schedule();
//Job jE = s.insert(8);
//Job jF = s.insert(6);
//Job jG = s.insert(2);
//Job jA = s.insert(2);
//Job jB = s.insert(7);
//Job jC = s.insert(1);
//Job jD = s.insert(5);
//
//jA.name = "A";
//jB.name = "B";
//jC.name = "C";
//jD.name = "D";
//jE.name = "E";
//jF.name = "F";
//jG.name = "G";
//
//jB.requires(jA);
//jB.requires(jD);
//jE.requires(jA);
//jD.requires(jE);
//jF.requires(jD);
//jG.requires(jD);
//jG.requires(jC);
//jG.requires(jA);
//jG.requires(jE);
//
//ArrayList<Job> jj = s.topSort();
//
//for (Job j : jj) {
//	System.out.println(j.name);


//break or continue? 
//break would stop there here
//continue could make groups of sorted jobs?
//}

// Even if there is a cycle, topsort should return what's sorted 
// so that we can relax the jobs that don't have a cycle