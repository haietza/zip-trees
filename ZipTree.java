import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class ZipTree {

	public static Node root;

	public ZipTree() {
		this.root = null;
	}
	
	public boolean find(int key, Node root) {
        if(root == null) {
            return false;
        }

        if(root.key == key) {
            return true;
        }

        if(key < root.key) {
            if(root.left == null) {
                return false;
            }
            if(key == root.left.key) {
                return true;
            } else {
                return find(key, root.left);
            }
        } else {
            if(root.right == null) {
                return false;
            }
            if(key == root.right.key) {
                return true;
            } else {
                return find(key, root.right);
            }
        }
	}
	
	public void delete(int key) {
        Node cur = root;
        Node prev = null;
        while (key != cur.key) {
            prev = cur;
            if (key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        Node left = cur.left;
        Node right = cur.right;

        if (left == null) {
            cur = right;
        } else if (right == null) {
            cur = left;
        } else if (left.rank >= right.rank) {
            cur = left;
        } else {
            cur = right;
        }

        if (root.key == key) {
            root = cur;
        } else if (key < prev.key) {
            prev.left = cur;
        } else {
            prev.right = cur;
        }

        while (left != null && right != null) {
            if (left.rank >= right.rank) {
                while (left != null && left.rank >= right.rank) {
                    prev = left;
                    left = left.right;
                }
                prev.right = right;
            } else {
                while (right != null && left.rank < right.rank) {
                    prev = right;
                    right = right.left;
                }
                prev.left = left;
            }
        }
	}
	
	public void insert(int y) {
        Node x = new Node(y);
        int rank = randomRankTest();
        x.rank = rank;
        int key = x.key;
        Node cur = root;
        Node prev = null;
        Node fix = null;
        while (cur != null && (rank < cur.rank || (rank == cur.rank && key > cur.key))) {
            prev = cur;
            if (key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        if (cur == root) {
            root = x;
        } else if (key < prev.key) {
            prev.left = x;
        } else {
            prev.right = x;
        }

        if (cur == null) {
            x.left = null;
            x.right = null;
            return;
        }
        
        if (key < cur.key) {
            x.right = cur;
        } else {
            x.left = cur;
        }

        prev = x;
        
        while (cur != null) {
            fix = prev;
            if (cur.key < key) {
                while (cur != null && cur.key <= key) {
                    prev = cur;
                    cur = cur.right;
                }
            } else {
                while (cur != null && cur.key >= key) {
                    prev = cur;
                    cur = cur.left;
                }
            }
            if (fix.key > key || (fix == x && prev.key > key)) {
                fix.left = cur;
            } else {
                fix.right = cur;
            }
        }
	}

	private int randomRank() {
		int heads = 0;
		while (Math.random() < 0.5) {
			heads++;
		}
        return heads;
	}

    private int randomRankTest() {
        Random rand = new Random();
        int heads = 0;
        while(rand.nextInt(2) != 0) {
            heads++;
        }
        return heads;
    }

    public void display(Node root) {
        if (root == null) return;
        display(root.left);
        System.out.print(" " + root.key);
        display(root.right);
    }

    public void printLevelOrder(Node root) { 
        if (root == null) return; 
        Queue<Node> q = new LinkedList<Node>(); 
        q.add(root); 
        int height = 0;
        while (true) { 
            int nodeCount = q.size();
            if (nodeCount == 0) 
                break; 
            System.out.println("Level: " + height);
            while (nodeCount > 0) { 
                Node node = q.peek();
                
                System.out.println("Key: " + node.key + ", rank: " + node.rank + " "); 
                if (node.left != null) {
                    System.out.print("Left: " + node.left.key + ", rank: " + node.left.rank + " ");
                }
                if (node.right != null) {
                    System.out.print("Right: " + node.right.key + ", rank: " + node.right.rank + " ");
                }
                q.remove(); 
                if (node.left != null) 
                    q.add(node.left); 
                if (node.right != null) 
                    q.add(node.right); 
                nodeCount--; 
            }
            height++;
            System.out.println();
            System.out.println();
        } 
    }
    
    public int printHeight(Node root) {
        if (root == null) return 0;
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        int height = 0;
        while (true) {
            int nodeCount = q.size();
            if (nodeCount == 0)
                break;
            while (nodeCount > 0) {
                Node node = q.peek();
                q.remove();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
                nodeCount--;
            }
            height++;
        }
        return height;
    }

    public static void main(String args[]) {
		ZipTree zt = new ZipTree();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
		list.add(8);
		list.add(1);
		list.add(4);
		list.add(6);
	    list.add(2);
		list.add(10);
		list.add(9);
		list.add(20);
		list.add(25);
	    list.add(15);
	    list.add(16);
		//System.out.println("Original Tree: ");
		//zt.display(zt.root);		
		//System.out.println("");

        Collections.shuffle(list);

        for(int i = 0; i < list.size(); i++) {
            zt.insert(list.get(i));
        }

        Collections.shuffle(list);

        for(int i = 0; i < list.size(); i++) {
            System.out.print("Check " + list.get(i) + " : ");
            System.out.println("CALLING FIND ON " + list.get(i));
            boolean x = zt.find(list.get(i), zt.root);
            System.out.println("FOUND " + x);
        }
        
        System.out.print("Check 100: ");
        System.out.println("CALLING FIND ON 100");
        boolean x = zt.find(100, zt.root);
        System.out.println("FOUND " + x);
                                                        
        //System.out.println("Delete Node with no children (2): ");
        //zt.delete(2);		
		//zt.display(root);
        //System.out.println("\n Delete Node with one child (4): ");
        //zt.delete(4);		
		//zt.display(root);
        //System.out.println("\n Delete Node with Two children (10): ");
        //zt.delete(10);
		zt.printLevelOrder(root);
	}
}
