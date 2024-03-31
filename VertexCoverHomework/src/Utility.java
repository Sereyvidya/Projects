public class Utility {
	
	public static boolean[] find(UndirectedGraph g) {
		boolean[] list = new boolean[g.size()];
		UndirectedGraph temp = g;
		for (int i = 0; i < g.size(); i++) {
			if (g.has(i)) {			
				int j = g.size() - 1;
				UndirectedGraph curr;
				while (i != 0) {
					curr = temp;
					temp = temp.remove(j);
					if (temp.has(i - 1)) {
						list[j] = true;
						i--;
					}
					else {
						temp = curr;
					}
					j--;
				}
				return list;
			}
		}
		return list;
	}
}


































//gcVb6fgp

/*
while (k > j) {
if (temp.remove(k).has(i - 1)) {
	if (temp.remove(j).remove(k).has(i - 2)) {
		list[j] = true;
		list[k] = true;
	}
}
else {
	list[k] = false;
}
}

		for (int i = 1; i <= g.size(); i++) {
			if (g.has(i)) {
				int j = 0;
				int k = g.size() - 1;
				while (j < k) {
					if (temp.remove(j).has(i - 1)) {
						//sometimes false sometimes true

					}
					else {
						//always false
						list[j] = false;
					}
					j++;
				}
				break;
			}
		}

*/

/*

		boolean[] list = new boolean[g.size()];
		LinkedList<Integer> link = new LinkedList<>();
		UndirectedGraph temp = g;
		UndirectedGraph test;
		
		int count = g.size();
		
		for (int i = 0; i < g.size(); i++) {
			list[i] = true;
			link.add(i);
		}
		
if (g.has(i)) {
while (count != g.has(i)) {
	for (int j : link) {
		test = g.remove(j);
		if (test.has(i)) {
			list[j] = false;
			link.remove(j);
			count--;
		}
	}
}
}*/