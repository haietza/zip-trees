import java.util.LinkedList;
import java.util.Queue;

public class ZipTree {

	public static Node root;

	public ZipTree() {
		this.root = null;
	}
	
	public Node find(int key, Node root) {
		if (root.key == key) {
            return root;
        }

        if (key < root.key) {
            if (key == root.left.key) {
                return root.left;
            } else {
                find(key, root.left);
            }
        } else {
            if(key == root.right.key) {
                return root.right;
            } else {
                find(key, root.right);
            }
        }
        return null;
	}
	
	public void delete(Node x) {
        int key = x.key;
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

        if (root == x) {
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
	
	public void insert(Node x) {
        int rank = randomRank();
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
}