import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
	
	public static Node root;
	
	public BinarySearchTree() {
		this.root = null;
	}
	
	public boolean find(int id) {
		Node current = root;
		while (current != null) {
			if (current.key == id) {
				return true;
			} else if (current.key > id) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}

	public boolean delete(int id) {
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while (current.key != id) {
			parent = current;
			if (current.key > id) {
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			if (current == null) {
				return false;
			}
		}

		// If I am here that means we have found the node
		// Case 1: if node to be deleted has no children
		if (current.left == null && current.right == null) {
			if (current == root) {
				root = null;
			}
			
			if (isLeftChild == true) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
		// Case 2 : if node to be deleted has only one child
		else if (current.right == null) {
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		}
		else if (current.left == null) {
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else if (current.left != null && current.right != null) {
			// Now we have found the minimum element in the right sub tree
			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}
	
	public Node getSuccessor(Node deleleNode) {
		Node successsor = null;
		Node successsorParent = null;
		Node current = deleleNode.right;
		while (current != null) {
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}

		// Check if successor has the right child, it cannot have left child for sure
		// If it does have the right child, add it to the left of successorParent.
		// successsorParent
		if (successsor != deleleNode.right) {
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}
	
	public void insert(int id) {
		Node newNode = new Node(id);
		if (root == null) {
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while (true) {
			parent = current;
			if (id < current.key) {				
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					return;
				}
			} else {
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					return;
				}
			}
		}
	}

	public void display(Node root) {
		if (root != null) {
			display(root.left);
			System.out.print(" " + root.key);
			display(root.right);
		}
	}

	public static void main(String args[]) {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(3);
		b.insert(8);
		b.insert(1);
		b.insert(4);
		b.insert(6);
		b.insert(2);
		b.insert(10);
		b.insert(9);
		b.insert(20);
		b.insert(25);
		b.insert(15);
		b.insert(16);
		System.out.println("Original Tree: ");
		b.display(b.root);		
		System.out.println("");
		System.out.println("Check whether Node with value 4 exists: " + b.find(4));
		System.out.println("Delete Node with no children (2): " + b.delete(2));		
		b.display(root);
		System.out.println("\n Delete Node with one child (4): " + b.delete(4));		
		b.display(root);
		System.out.println("\n Delete Node with Two children (10): " + b.delete(10));		
		b.display(root);
	}

    public void printLevelOrder(Node root)
    {
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
                System.out.print("Key: " + node.key + " "); 
                if (node.left != null) {
                    System.out.print("Left: " + node.left.key + " ");
                }
                if (node.right != null) {
                    System.out.print("Right: " + node.right.key + " ");
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
        while(true) {
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
}

class Node{
    int rank;
	int key;
	Node left;
	Node right;	
	public Node(int key) {
		this.key = key;
		left = null;
		right = null;
        rank = 0;
	}
}