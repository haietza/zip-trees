public class ZipTree {
	public static  Node root;
	public ZipTree(){
		this.root = null;
	}
	
	// This is from BST class, has not been converted yet.
	public boolean find(int id){
		Node current = root;
		while(current!=null){
			if(current.data==id){
				return true;
			}else if(current.data>id){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}
	
	public Node delete(Node x, Node root){
		if (x.key == root.key {
			return zip(root.left, root.right);
		}

		if (x.key < root.key) {
			if (x.key == root.left.key) {
				root.left = zip(root.left.left, root.left.right);
			} else {
				delete(x, root.left);
			}
		} else {
			if (x.key == root.right.key) {
				root.right = zip(root.right.left, root.right.right);
			} else {
				delete(x, root.right);
			}
		}
		return root;
	}

	private Node zip(Node x, Node y) {
		if (x == null) {
			return y;
		}

		if (y == null) {
			return x;
		}

		if (x.rank < y.rank) {
			y.left = zip(x, y.left);
			return y;
		} else {
			x.right = zip(x.right, y);
			return x;
		}
	}
	
	public Node insert(Node x, Node root){
		if (root == null) {
			x.left = null;
			x.right = null;
			//x.rank = callRandomRank();
			return x;
		}
		
		if (x.key < root.key) {
			if (insert(x, root.left == x) {
				if (x.rank < root.rank) {
					root.left = x;
				} else {
					root.left = x.right;
					x.right = root;
					return x;
				}
			}
		} else {
			if (insert(x, root.right) == x {
				if (x.rank <= root.rank {
					root.right = x;
				} else {
					root.right = x.left;
					x.left = root;
					return x;
				}
			}
		}
		return root;
	}
	
	public static void main(String arg[]){
	}
}

class Node{
	int data;
	int rank;
	Node left;
	Node right;	
	public Node(int data){
		this.data = data;
		rank = null;
		left = null;
		right = null;
	}
}