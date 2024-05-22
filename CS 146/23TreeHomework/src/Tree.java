import java.util.*;

public class Tree {
	public class Node implements Comparable<Node> {
		public ArrayList<Integer> keys = new ArrayList<>();
		public ArrayList<Node> children = new ArrayList<>();
		public Node parent = null;
		public int size = 0;
		
		public Node() {
		}
		
		public Node(int data) {
			keys.add(data);
			size += 1;
		}
		
		public Node search(int x) {
			if (this.children.size() == 0 || keys.contains(x)) {
				return this;
			}
			
			int i = 0;
			while (i < keys.size() && x > keys.get(i)) {
				i++;
			}
			return children.get(i).search(x);
		}
		
		public int compareTo(Node other) {
			int ans = keys.get(0).compareTo(other.keys.get(0));
			return ans;
		}
	}
	
	
	
	public Node root;
	
	public Tree() {
		root = new Node();
	}
	
	public int size() {
		return root.size;					
	}
	
	public int size(int x) {
		Node found = root.search(x);
		
		if (found.keys.contains(x)) {
			return found.size;
		}
		
		return 0;
	}
	
	public int get(int x) {
		return get(root, x, 0);
	}

	public boolean insert(int x) {
		Node node = root.search(x);
		if (put(node, x)) {
			if (node.keys.size() == 3) {
				split(node);
			}
			
			while (node.parent != null) {
				node = node.parent;
				node.size += 1;
			}
			
			return true;
		}
		return false;
	}
	
	
	// Private methods
	
	private int get(Node node, int x, int count) {
		int i = 0;
		while (node.children.size() > i) {
			int subTreeSize = size(node.children.get(i).keys.get(0));
			if (x < count + subTreeSize) {
				return get(node.children.get(i), x, count);
			}
			count += subTreeSize;
			if (x == count) {
				return node.keys.get(i);
			}
			i++;
			count++;
		}
		
		while (count + i < x) {
			i++;
		}
		return node.keys.get(i);
	}
	
	private boolean put(Node node, int x) {
		if (!node.keys.contains(x)) {
			node.keys.add(x);
			node.size += 1;
			Collections.sort(node.keys);
			return true;
		}
		return false;
	}
	
	private void split(Node node) {
		if (node.keys.size() > 2) {
			Node curr = node;
			Node left = new Node(curr.keys.get(0));
			Node right = new Node(curr.keys.get(2));
			
			if (curr.parent == null) {
				left.parent = curr;
				right.parent = curr;
				curr.keys.remove(2);
				curr.keys.remove(0);
				
				if (curr.children.size() > 2) {
					splitII(curr, left, right);
				}
				curr.children.add(left);
				curr.children.add(right);
				
				Collections.sort(curr.children);
			}
			else {
				Node parent = curr.parent;
				parent.keys.add(curr.keys.get(1));	
				Collections.sort(parent.keys);
				left.parent = parent;
				right.parent = parent;
				
				if (curr.children.size() > 2) {
					splitII(curr, left, right);
				}
				
				parent.children.add(left);
				parent.children.add(right);
				parent.children.remove(curr);
				Collections.sort(parent.children);
				
				split(parent);
			}			
		}
	}
	
	private void splitII(Node node, Node left, Node right) {
		node.children.get(3).parent = right;
		node.children.get(2).parent = right;
		right.children.add(node.children.get(2));
		right.children.add(node.children.get(3));
		
		right.size += node.children.get(2).size;
		right.size += node.children.get(3).size;
		
		node.children.remove(3);
		node.children.remove(2);
		
		
		node.children.get(1).parent = left;
		node.children.get(0).parent = left;
		left.children.add(node.children.get(0));
		left.children.add(node.children.get(1));
		
		left.size += node.children.get(0).size;
		left.size += node.children.get(1).size;
		
		node.children.remove(1);
		node.children.remove(0);
	}

	
}

































// Previous codes that I might need/to look back on


//public static void main(String[] args) {
//Tree tree = new Tree();
//
//tree.insert(9);
//tree.insert(11);
//tree.insert(14);
//
//tree.insert(18);
//tree.insert(22);
//tree.insert(21);
//
//tree.insert(23);
//tree.insert(24);
//tree.insert(19);
//
//tree.insert(15);
//tree.insert(10);
//tree.insert(25);
//
//tree.insert(16);
//tree.insert(17);
//tree.insert(13);
//tree.insert(12);
//
//System.out.println(tree.size(22));
//System.out.println(tree.root.search(9).keys);
//System.out.println(tree.root.size);
//}



//public int size() {
//int size = keys.size();
//if (children.size() != 0) {
//	for (Node child : children) {
//		size += child.size();
//	}
//}
//return size;
//}



//public int size() {
//if (this.children.size() == 0) {
//	size = keys.size();
//}
//else {
//	for (Node child : children) {
//		size += child.size();
//	}
//	size += keys.size();
//}
//return size;
//}

//private void sizeIII() {
//this.size = keys.size() + children.size();
//}


//	public int get(int x) {
//		int count = 0;
//		
//		Node curr = root;
//		while (curr != null) {
//			if (curr.children.size() > 0) {
//				if (count + size(curr.children.get(0).keys.get(0)) > x) {
//					curr = curr.children.get(0);
//				}
//				else {
//					count += size(curr.children.get(0).keys.get(0));
//					
//					if (count == x) {
//						return curr.keys.get(0);
//					}
//					count += 1;
//					
//					if (curr.keys.size() == 2) {
//						if (count + size(curr.children.get(1).keys.get(0)) > x) {
//							curr = curr.children.get(1);
//						}
//						else {
//							count += size(curr.children.get(1).keys.get(0));
//							
//							if (count == x) {
//								return curr.keys.get(1);
//							}
//							count += 1;
//							
//							curr = curr.children.get(2);
//						}
//					}
//					else {
//						curr = curr.children.get(1);
//					}
//				}
//			}
//			else {
//				if (curr.keys.size() > 0) {
//					if (count == x) {
//						return curr.keys.get(0);
//					}
//					count += 1;
//					return curr.keys.get(1);
//				}
//				break;
//			}
//		}
//		
//		return 0;
//	}


/**
	public boolean insert(int data) {
		Node parent = null;
		Node curr = root;
		while (curr != null) {
			if (curr.keys.contains(data)) {
				return false;
			}
			
			if (curr.children.size() == 0) {
				if (curr.keys.size() < 2) {
					curr.keys.add(data);
					Collections.sort(curr.keys);
				}
				else {
					Collections.sort(curr.keys);
					if (parent == null) {
						if (data < curr.keys.get(0)) {
							Node toAdd1 = new Node(data);
							toAdd1.parent = curr;
							curr.children.add(toAdd1);
							
							Node toAdd2 = new Node(curr.keys.get(1));
							toAdd2.parent = curr;
							curr.children.add(toAdd2);
							curr.keys.remove(1);
							
						}
						else if (data < curr.keys.get(1)) {
							Node toAdd1 = new Node(curr.keys.get(0));
							Node toAdd2 = new Node(curr.keys.get(1));
							toAdd1.parent = curr;
							toAdd2.parent = curr;
							curr.children.add(toAdd1);
							curr.children.add(toAdd2);
							
							curr.keys.remove(1);
							curr.keys.remove(0);
							curr.keys.add(data);
							
						}
						else {
							Node toAdd1 = new Node(curr.keys.get(0));
							toAdd1.parent = curr;
							curr.children.add(toAdd1);
							curr.keys.remove(0);
							
							Node toAdd2 = new Node(data);
							toAdd2.parent = curr;
							curr.children.add(toAdd2);
						}
					}
					else {
						if (data < curr.keys.get(0)) {
							parent.keys.add(curr.keys.get(0));
							curr.keys.remove(0);
							Node toAdd1 = new Node(data);
							toAdd1.parent = parent;
							parent.children.add(toAdd1);
						}
						else if (data < curr.keys.get(1)) {
							parent.keys.add(data);
							Node toAdd1 = new Node(curr.keys.get(1));
							toAdd1.parent = parent;
							parent.children.add(toAdd1);
							curr.keys.remove(1);
						}
						else {
							parent.keys.add(curr.keys.get(1));
							curr.keys.remove(1);
							Node toAdd1 = new Node(data);
							toAdd1.parent = parent;
							parent.children.add(toAdd1);
						}
						Collections.sort(parent.keys);
						Collections.sort(parent.children);
						
						while (parent != null && parent.keys.size() == 3) {
							curr = parent;
							parent = parent.parent;
							
							if (parent == null) {
								Node left = new Node(curr.keys.get(0));
								left.parent = curr;
								Node right = new Node(curr.keys.get(2));
								right.parent = curr;
								curr.keys.remove(2);
								curr.keys.remove(0);
								
								Node four = curr.children.get(3);
								Node three = curr.children.get(2);
								four.parent = right;
								three.parent = right;
								right.children.add(three);
								right.children.add(four);
								
								Node two = curr.children.get(1);
								Node one = curr.children.get(0);
								two.parent = left;
								one.parent = right;
								left.children.add(one);
								left.children.add(two);
								
								curr.children = new ArrayList<Node>();
								curr.children.add(left);
								curr.children.add(right);
							}
							else {
								//if parent has at least two keys, then it has to have a middle node
								parent.keys.add(curr.keys.get(1));
								
								Node left = new Node(curr.keys.get(0));
								left.parent = parent;
								Node right = new Node(curr.keys.get(2));
								right.parent = parent;
//								curr.keys.remove(2);
//								curr.keys.remove(0);
								
								Node four = curr.children.get(3);
								Node three = curr.children.get(2);
								four.parent = right;
								three.parent = right;
								right.children.add(three);
								right.children.add(four);
								
								Node two = curr.children.get(1);
								Node one = curr.children.get(0);
								two.parent = left;
								one.parent = right;
								left.children.add(one);
								left.children.add(two);
								
								parent.children.remove(curr);
								parent.children.add(left);
								parent.children.add(right);
								
								Collections.sort(parent.keys);
								Collections.sort(parent.children);
							}
						}
					}
				}
				curr = null;
			}
			else {
				parent = curr;
				
				if (data < curr.keys.get(0)) {
					curr = curr.children.get(0);
				}
				else {
					if (curr.keys.size() == 2 && data > curr.keys.get(1)) {
						curr = curr.children.get(2);
					}
					else {
						curr = curr.children.get(1);
					}
				}
			}
		}
		
		return true;
	}
**/

//public static void main(String args[]) {
//Tree tree = new Tree();
//tree.insert(9);
//tree.insert(11);
//tree.insert(14);
//
//tree.insert(18);
//tree.insert(22);
//tree.insert(21);
//
//tree.insert(23);
//tree.insert(24);
//tree.insert(19);
//
//tree.insert(15);
//tree.insert(10);
//tree.insert(25);
//
//tree.insert(16);
//tree.insert(17);
//tree.insert(13);
//tree.insert(12);
//
//tree.insert(20);
//
//System.out.println(tree.size(15));
//System.out.println(tree.root.keys);
//
//}


// OLD Search

//public Node search(int x) {
//	if (this.keys.size() == 0) {
//		return null;
//	}
//	else {
//		if (keys.contains(x)) {
//			return this;
//		}
//		else {
//			if (children.size() > 0) {
//				if (x < keys.get(0)) {
//					return children.get(0).search(x);
//				}
//				else {
//					if (keys.size() == 2 && x > keys.get(1)) {
//						return children.get(2).search(x);
//					}
//					else {
//						return children.get(1).search(x);
//					}
//				}
//			}
//			else {
//				return null;
//			}
//		}
//	}
//
//}


















// OLD TREE

//public class Tree {
//	public class Node {
//		public int data1;
//		public int data2;
//		public int size;
//		public Node left;
//		public Node mid;
//		public Node right;
//		
//		public Node() {
//			data1 = -1;
//			data2 = -1;
//			size = 0;
//			left = null;
//			mid = null;
//			right = null;
//		}
//		
//		
//		public Node(int data) {
//			data1 = data;
//			data2 = -1;
//			size = 1;
//			left = null;
//			mid = null;
//			right = null;
//		}
//		
//	}
//	
//	public Node root;
//	public int count = 0;
//	
//	public Tree() {
//		root = new Node();
//	}
//
//}
//	public boolean insert(int x) {
//		if (insertBottomUp(x)) {
//			return true;
//		}
//		return false;
//	}
//	
//	public int size() {
//		return size(root.data1);
//	}
//	
//	public int size(int x) {
//		Node find = search(x);
//		if (find != null) {
//			return find.size;
//		}
//		return 0;
//	}
//	
//	public int get(int i) {
//		// recursive in-order traversal?
//		
//		Node history = new Node();
//		Node track = history;
//		
//		Node curr = root;
//		while (curr != null) {
//			track.left = curr;
//			track.right = new Node();
//			curr = curr.left;
//			track = track.right;
//		}
//		return 0;
//	}
//	
//	private boolean insertBottomUp (int x) {
//		Node history = new Node();
//		Node track = history;
//		Node parent = null;
//		Node curr = root;	
//		
//		while (curr != null) {
//			if (x == curr.data1 || x == curr.data2) {
//				break;
//			}
//			
//			if (curr.left == null && curr.right == null) {
//				if (curr.data1 == -1) {
//					curr.data1 = x;
//					curr.size += 1;
//					
//					if (parent != null) {
//						parent.size += 1;
//					}
//					return true;
//				}
//				else if (curr.data2 == -1) {
//					if (curr.data1 > x) {
//						curr.data2 = curr.data1;
//						curr.data1 = x;
//					}
//					else {
//						curr.data2 = x;
//					}
//					curr.size += 1;
//					
//					if (parent != null) {
//						parent.size += 1;
//					}
//					return true;
//				}
//				else {										
//					if (x < curr.data1) {		
//						Node first = new Node(x);
//						Node second = new Node(curr.data2);
//						
//						if (parent == null) {
//							curr.left = first;
//							curr.right = second;
//							//curr.data1 stays the same
//							curr.data2 = -1;
//							curr.size -= 1;
//							curr.size += first.size + second.size;
//							return true;
//						}
//						else {
//							if (parent.mid == curr) {
//								Node outterLeft = parent.left;
//								Node outterRight = parent.right;
//								Node upperLeft = new Node(parent.data1);
//								Node upperRight = new Node(parent.data2);
//								
//								parent.data2 = -1;
//								parent.data1 = curr.data1;
//								parent.size = 1;
//								
//								upperLeft.left = outterLeft;
//								upperLeft.right = first;
//								upperLeft.size += outterLeft.size + first.size;
//								
//								upperRight.left = second;
//								upperRight.right = outterRight;
//								upperRight.size += second.size + outterRight.size;
//								
//								parent.left = upperLeft;
//								parent.mid = null;
//								parent.right = upperRight;
//								parent.size += upperLeft.size + upperRight.size;
//								return true;
//							}
//							
//							if (curr == parent.left && parent.mid != null) {
//								Node middleRight = parent.mid;
//								Node outterRight = parent.right;
//								Node upperLeft = new Node(curr.data1);
//								Node upperRight = new Node(parent.data2);
//								
//								//parent.data1 stays the same
//								parent.data2 = -1;
//								parent.size = 1;
//								
//								upperLeft.left = first;
//								upperLeft.right = second;
//								upperLeft.size += first.size + second.size;
//								
//								upperRight.left = middleRight;
//								upperRight.right = outterRight;
//								upperRight.size += middleRight.size + outterRight.size;
//								
//								parent.left = upperLeft;
//								parent.mid = null;
//								parent.right = upperRight;
//								parent.size += upperLeft.size + outterRight.size;
//								return true;
//							}
//							if (curr == parent.right && parent.mid != null) {
//								Node middleLeft = parent.mid;
//								Node outterLeft = parent.left;
//								Node upperLeft = new Node(parent.data1);
//								Node upperRight = new Node(curr.data1);
//								
//								parent.data1 = parent.data2;
//								parent.data2 = -1;
//								parent.size = 1;
//								
//								upperLeft.left = outterLeft;
//								upperLeft.right = middleLeft;
//								upperLeft.size += outterLeft.size + middleLeft.size;
//								
//								upperRight.left = first;
//								upperRight.right = second;
//								upperRight.size += first.size + second.size;
//								
//								parent.left = upperLeft;
//								parent.mid = null;
//								parent.right = upperRight;
//								parent.size += upperLeft.size + upperRight.size;
//								return true;
//							}
//							
//							int tmp1 = parent.data1;
//							int tmp2 = curr.data1;
//							parent.data1 = Math.min(tmp1, tmp2);
//							parent.data2 = Math.max(tmp1, tmp2);
//							
//							if (parent.left == curr) {								
//								parent.left = first;
//								parent.mid = second;
//							}
//							else {
//								parent.mid = first;
//								parent.right = second;
//							}
//							parent.size += parent.mid.size;
//							return true;
//						}
//						
//					}
//					else if (x < curr.data2) {
//						Node first = new Node(curr.data1);
//						Node second = new Node(curr.data2);	
//						
//						if (parent == null) {
//							curr.left = first;
//							curr.right = second;
//							curr.data1 = x;
//							curr.data2 = -1;
//							curr.size -= 1;
//							curr.size += first.size + second.size;
//							return true;
//						}
//						else {
//							if (parent.mid == curr) {
//								Node outterLeft = parent.left;
//								Node outterRight = parent.right;
//								Node upperLeft = new Node(parent.data1);
//								Node upperRight = new Node(parent.data2);
//								
//								parent.data2 = -1;
//								parent.data1 = x;
//								parent.size = 1;
//								
//								upperLeft.left = outterLeft;
//								upperLeft.right = first;
//								upperLeft.size += outterLeft.size + first.size;
//								
//								upperRight.left = second;
//								upperRight.right = outterRight;
//								upperRight.size += second.size + outterRight.size;
//								
//								parent.left = upperLeft;
//								parent.mid = null;
//								parent.right = upperRight;
//								parent.size += upperLeft.size + upperRight.size;
//								return true;
//							}
//							
//							if (curr == parent.left && parent.mid != null) {
//								Node middleRight = parent.mid;
//								Node outterRight = parent.right;
//								Node upperLeft = new Node(x);
//								Node upperRight = new Node(parent.data2);
//								
//								//parent.data1 stays the same
//								parent.data2 = -1;
//								parent.size = 1;
//								
//								upperLeft.left = first;
//								upperLeft.right = second;
//								upperLeft.size += first.size + second.size;
//								
//								upperRight.left = middleRight;
//								upperRight.right = outterRight;
//								upperRight.size += middleRight.size + outterRight.size;
//								
//								parent.left = upperLeft;
//								parent.mid = null;
//								parent.right = upperRight;
//								parent.size += upperLeft.size + upperRight.size; 
//								return true;
//							}
//							if (curr == parent.right && parent.mid != null) {
//								Node middleLeft = parent.mid;
//								Node outterLeft = parent.left;
//								Node upperLeft = new Node(parent.data1);
//								Node upperRight = new Node(x);
//
//								parent.data1 = parent.data2;
//								parent.data2 = -1;
//								parent.size = 1;
//								
//								upperLeft.left = outterLeft;
//								upperLeft.right = middleLeft;
//								upperLeft.size += outterLeft.size + middleLeft.size;
//								
//								upperRight.left = first;
//								upperRight.right = second;
//								upperRight.size += first.size + second.size;
//								
//								parent.left = upperLeft;
//								parent.mid = null;
//								parent.right = upperRight;
//								parent.size += upperLeft.size + upperRight.size;
//								return true;
//							}
//							
//							int tmp1 = parent.data1;
//							int tmp2 = x;
//							parent.data1 = Math.min(tmp1, tmp2);
//							parent.data2 = Math.min(tmp1, tmp2);
//							
//							if (parent.left == curr) {
//								parent.left = first;
//								parent.mid = second;
//							}
//							else {
//								parent.mid = first;
//								parent.right = second;
//							}
//							parent.size += parent.mid.size;
//							return true;
//						}
//					}
//					else { //x is larger than data2
//						Node first = new Node(curr.data1);
//						Node second = new Node(x);
//						
//						if (parent == null) {
//							curr.left = first;
//							curr.right = second;
//							curr.data1 = curr.data2;
//							curr.data2 = -1;
//							curr.size -= 1;
//							curr.size += first.size + second.size;
//							return true;
//						}
//						else {
//							if (parent.mid == curr) {
//								Node outterLeft = parent.left;
//								Node outterRight = parent.right;
//								Node upperLeft = new Node(parent.data1);
//								Node upperRight = new Node(parent.data2);
//								
//								parent.data2 = -1;
//								parent.data1 = curr.data2;
//								parent.size = 1;
//								
//								upperLeft.left = outterLeft;
//								upperLeft.right = first;
//								upperLeft.size += outterLeft.size + first.size;
//								
//								upperRight.left = second;
//								upperRight.right = outterRight;
//								upperRight.size += second.size + outterRight.size;
//								
//								parent.left = upperLeft;
//								parent.mid = null;
//								parent.right = upperRight;
//								parent.size += upperLeft.size + upperRight.size;
//								return true;
//							}
//							if (curr == parent.left && parent.mid != null) {
//								Node middleRight = parent.mid;
//								Node outterRight = parent.right;
//								Node upperLeft = new Node(curr.data2);
//								Node upperRight = new Node(parent.data2);
//								
//								//parent.data1 stays the same
//								parent.data2 = -1;
//								parent.size = 1;
//								
//								upperLeft.left = first;
//								upperLeft.right = second;
//								upperLeft.size += first.size + second.size;
//								
//								upperRight.left = middleRight;
//								upperRight.right = outterRight;
//								upperRight.size += middleRight.size + outterRight.size;
//								
//								parent.left = upperLeft;
//								parent.mid = null;
//								parent.right = upperRight;
//								parent.size += upperLeft.size + upperRight.size;
//								return true;
//							}
//							if (curr == parent.right && parent.mid != null) {
//								Node middleLeft = parent.mid;
//								Node outterLeft = parent.left;
//								Node upperLeft = new Node(parent.data1);
//								Node upperRight = new Node(curr.data2);
//
//								parent.data1 = parent.data2;
//								parent.data2 = -1;
//								parent.size = 1;
//								
//								upperLeft.left = outterLeft;
//								upperLeft.right = middleLeft;
//								upperLeft.size += outterLeft.size + middleLeft.size;
//								
//								upperRight.left = first;
//								upperRight.right = second;
//								upperRight.size += first.size + second.size;
//								
//								parent.left = upperLeft;
//								parent.mid = null;
//								parent.right = upperRight;
//								parent.size += upperLeft.size + upperRight.size;
//								return true;
//							}
//							
//							int tmp1 = parent.data1;
//							int tmp2 = curr.data2;
//							parent.data1 = Math.min(tmp1, tmp2);
//							parent.data2 = Math.max(tmp1, tmp2);
//							
//							if (parent.left == curr) {								
//								parent.left = first;
//								parent.mid = second;
//							}
//							else {
//								parent.mid = first;
//								parent.right = second;
//							}							
//							parent.size += parent.mid.size;
//							return true;
//						}
//					}
//				}
//			}
//			else {
//				if (parent != null) {
//					parent.size += 1;
//					track.left = parent;
//					track.right = new Node();
//					track = track.right;
//				}
//				
//				if (x < curr.data1) {
//					parent = curr;
//					curr = curr.left;
//				}
//				else if (x < curr.data2) {
//					parent = curr;
//					curr = curr.mid;
//				}
//				else {
//					parent = curr;
//					curr = curr.right;
//				}
//			}
//		}
//		
//		while (history.left != null) {
//			history.left.size -= 1;
//			history = history.right;
//		}
//		return false;
//	}
//	
//	private Node search(int x) {
//		Node curr = root;
//		while (curr != null) {
//			if (curr.data1 == x || curr.data2 == x) {
//				return curr;
//			}
//			else {
//				if (curr.data1 != -1 && curr.data2 == -1) {
//					if (x < curr.data1) {
//						curr = curr.left;
//					}
//					else {
//						curr = curr.right;
//					}
//				}
//				else {
//					if (x < curr.data1) {
//						curr = curr.left;
//					}
//					else if (x < curr.data2) {
//						curr = curr.mid;
//					}
//					else {
//						curr = curr.right;
//					}
//				}
//			}
//		}
//		
//		return null;
//	}
	
//	private boolean insertTopDown(int x) {
//		Node parent = null;
//		Node curr = root;	
//		
//		while (curr != null) {
//			if (x == curr.data1 || x == curr.data2) {
//				return false;
//			}
//			
//			if (curr.data1 == -1 || curr.data2 == -1) {
//				if (curr.left == null && curr.right == null) {
//					if (curr.data1 == -1) {
//						curr.data1 = x;
//						curr.size += 1;
//						
//						if (parent != null) {
//							parent.size += 1;
//						}
//						return true;
//					}
//					else if (curr.data2 == -1) {
//						if (curr.data1 > x) {
//							curr.data2 = curr.data1;
//							curr.data1 = x;
//						}
//						else {
//							curr.data2 = x;
//						}
//						curr.size += 1;
//						
//						if (parent != null) {
//							parent.size += 1;
//						}
//						return true;
//					}
//				}
//				else {
//					if (parent != null) {
//						//System.out.println("b " + parent.data1);
//						parent.size += 1;
//					}
//					
//					if (x < curr.data1) {
//						parent = curr;
//						curr = curr.left;
//					}
//					else if (x < curr.data2) {
//						parent = curr;
//						curr = curr.mid;
//					}
//					else {
//						parent = curr;
//						curr = curr.right;
//					}
//				}
//			}
//			else {
//				if (curr.left != null && curr.right != null) {
//					if (x < curr.data1) {
//						Node upperLeft = curr.left;
//						Node upperRight = new Node(curr.data2);
//						
//						upperLeft.left = new Node();
//						upperLeft.right = new Node();
//						
//						upperRight.left = curr.mid;
//						upperRight.right = curr.right;
//						upperRight.size += curr.mid.size + curr.right.size;
//						
//						if (parent != null) {
//							if (parent.left == curr) {
//								
//							}
//							else if (parent.mid == curr) {
//								
//							}
//							else {
//								
//							}
//						}
//						else {
//							curr.data2 = -1;
//							curr.left = upperLeft;
//							curr.mid = null;
//							curr.right = upperRight;			
//							parent = curr;
//						}
//						curr = upperLeft;
//					}
//					else if (x < curr.data2) {
//						Node upperLeft = new Node(curr.data1);
//						Node upperRight = new Node(curr.data2);
//						
//						upperLeft.left = curr.left;
//						upperRight.right = curr.right;
//						
//						upperLeft.size += curr.left.size;
//						upperRight.size += curr.right.size;
//						
//						if (x < curr.mid.data1) {
//							upperLeft.right = new Node();
//							upperLeft.size += 1;
//							
//							upperRight.left = curr.mid;
//							upperRight.size += curr.mid.size;
//						}
//						else {
//							upperLeft.right = curr.mid;
//							upperLeft.size += curr.mid.size;
//							
//							upperRight.left = new Node();
//							upperRight.left.size += 1;
//						}
//						
//						if (parent != null) {
//							if (parent.left == curr) {
//								parent.data2 = parent.data1;
//								parent.data1 = x;
//								parent.left = upperLeft;
//								parent.mid = upperRight;
//							}		
//							else if (parent.mid == curr) {
//								
//							}
//							else {
//								
//							}
//						}
// 						else {
//							curr.data1 = x;
//							curr.data2 = -1;
//							curr.size = 1;
//							
//							curr.left = upperLeft;
//							curr.mid = null;
//							curr.right = upperRight;
//							curr.size += upperLeft.size + upperRight.size;
//						}
//						
//						return true;
//					}
//					else { // x > curr.data2
//						Node upperLeft = new Node(curr.data1);
//						Node upperRight = curr.right;
//						
//						upperLeft.left = curr.left;
//						upperLeft.right = curr.mid;
//						upperLeft.size += curr.left.size + curr.mid.size;
//						
//						upperRight.left = new Node();
//						upperRight.right = new Node();
//					
//						if (parent != null) {
//							if (parent.left == curr) {
//								parent.data2 = parent.data1;
//								parent.data1 = curr.data2;
//								parent.left = upperLeft;
//								parent.mid = upperRight;
//							}
//							else if (parent.mid == curr) {
//								
//							}
//							else {
//								parent.data2 = curr.data2;
//								parent.mid = upperLeft;
//								parent.right = upperRight;
//							}
//						}
//						else {
//							curr.data1 = curr.data2;
//							curr.data2 = -1;
//							curr.left = upperLeft;
//							curr.mid = null;
//							curr.right = upperRight;
//							parent = curr;
//						}
//						curr = upperRight;
//					}
//				}
//				else {
//					Node first;
//					Node second;
//					int tmp2;
//					if (x < curr.data1) {		
//						first = new Node(x);
//						second = new Node(curr.data2);
//						tmp2 = curr.data1;
//						
//						curr.data2 = -1;
//					}
//					else if (x < curr.data2) {
//						first = new Node(curr.data1);
//						second = new Node(curr.data2);
//						tmp2 = x;
//						
//						curr.data1 = x;
//						curr.data2 = -1;
//					}
//					else {
//						first = new Node(curr.data1);
//						second = new Node(x);
//						tmp2 = curr.data2;
//						
//						curr.data1 = curr.data2;
//						curr.data2 = -1;
//					}
//					curr.size -= 1;
//					
//					if (parent == null) {
//						curr.left = first;
//						curr.right = second;
//						curr.size += first.size + second.size;
//					}
//					else {
//						int tmp1 = parent.data1;
//						if (parent.left == curr) {
//							parent.left = first;
//							parent.mid = second;
//						}
//						else {
//							parent.mid = first;
//							parent.right = second;
//						}
//						parent.data1 = Math.min(tmp1, tmp2);
//						parent.data2 = Math.max(tmp1, tmp2);
//						parent.size += 1;
//					}
//					
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
	
//	public static void main(String args[]) {
//		Tree tree = new Tree();
//		
//		System.out.println(tree.insert(9));
//		System.out.println(tree.insert(11));
//		System.out.println(tree.insert(14));
//		System.out.println(tree.insert(18));
//		System.out.println(tree.insert(22));
//		System.out.println(tree.insert(21));
//		System.out.println(tree.insert(23));
//		System.out.println(tree.insert(24));
//		System.out.println(tree.insert(19));
//		System.out.println(tree.insert(15));
//		System.out.println(tree.insert(10));
//		System.out.println(tree.insert(25));
//		System.out.println(tree.insert(16));
//		System.out.println(tree.insert(17));
//		
//		System.out.println(tree.size());
//		System.out.println(tree.root.left.size);
//		System.out.println(tree.root.mid.size);
//		System.out.println(tree.root.right.size);
//		
//		System.out.println();
//		System.out.println(tree.size(10));
//		System.out.println();
//		
//		System.out.println(tree.root.data1);
//		System.out.println(tree.root.data2);
//
//		System.out.println(tree.root.left.data1);
//		System.out.println(tree.root.mid.data1);
//		System.out.println(tree.root.right.data1);
//		System.out.println(tree.root.right.data2);
//		
//		System.out.println(tree.root.left.left.data1);
//		System.out.println(tree.root.left.left.data2);
//		System.out.println(tree.root.left.right.data1);
//		System.out.println(tree.root.mid.right.data1);
//		System.out.println(tree.root.right.left.data1);
//		System.out.println(tree.root.right.left.data2);
//		System.out.println(tree.root.right.mid.data1);
//		System.out.println(tree.root.right.right.data1);
//	}
