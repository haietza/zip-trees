import java.util.LinkedList;
import java.util.Queue;

/**
 * AVL Tree code from https://rosettacode.org/wiki/AVL_tree#Java
 * used under GNU Free Documentation License 1.2.
 */
public class AVLTree {
 
    public AVLNode root;
 
    private static class AVLNode {
        private int key;
        private int balance;
        private int height;
        private AVLNode left;
        private AVLNode right;
        private AVLNode parent;
 
        AVLNode(int key, AVLNode parent) {
            this.key = key;
            this.parent = parent;
        }
    }

    public boolean insert(int key) {
        if (root == null) {
            root = new AVLNode(key, null);
            return true;
        }
 
        AVLNode n = root;
        while (true) {
            if (n.key == key)
                return false;
 
            AVLNode parent = n;
 
            boolean goLeft = n.key > key;
            n = goLeft ? n.left : n.right;
 
            if (n == null) {
                if (goLeft) {
                    parent.left = new AVLNode(key, parent);
                } else {
                    parent.right = new AVLNode(key, parent);
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }

    private void delete(AVLNode AVLNode) {
        if (AVLNode.left == null && AVLNode.right == null) {
            if (AVLNode.parent == null) {
                root = null;
            } else {
                AVLNode parent = AVLNode.parent;
                if (parent.left == AVLNode) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                rebalance(parent);
            }
            return;
        }
 
        if (AVLNode.left != null) {
            AVLNode child = AVLNode.left;
            while (child.right != null) child = child.right;
            AVLNode.key = child.key;
            delete(child);
        } else {
            AVLNode child = AVLNode.right;
            while (child.left != null) child = child.left;
            AVLNode.key = child.key;
            delete(child);
        }
    }
 
    public void delete(int delKey) {
        if (root == null)
            return;
 
        AVLNode child = root;
        while (child != null) {
            AVLNode AVLNode = child;
            child = delKey >= AVLNode.key ? AVLNode.right : AVLNode.left;
            if (delKey == AVLNode.key) {
                delete(AVLNode);
                return;
            }
        }
    }

    public boolean find(int key) {
        if(root == null) {
            return false;
        }
        AVLNode child = root;
        while(child != null) {
            AVLNode AVLNode = child;
            child = key >= AVLNode.key ? AVLNode.right : AVLNode.left;
            if(key == AVLNode.key) {
                return true;
            }
        }
        return false;
    }
 
    private void rebalance(AVLNode n) {
        setBalance(n);
 
        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);
 
        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }
 
        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }
 
    private AVLNode rotateLeft(AVLNode a) {
 
        AVLNode b = a.right;
        b.parent = a.parent;
 
        a.right = b.left;
 
        if (a.right != null)
            a.right.parent = a;
 
        b.left = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
 
        setBalance(a, b);
 
        return b;
    }
 
    private AVLNode rotateRight(AVLNode a) {
 
        AVLNode b = a.left;
        b.parent = a.parent;
 
        a.left = b.right;
 
        if (a.left != null)
            a.left.parent = a;
 
        b.right = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
 
        setBalance(a, b);
 
        return b;
    }
 
    private AVLNode rotateLeftThenRight(AVLNode n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }
 
    private AVLNode rotateRightThenLeft(AVLNode n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }
 
    private int height(AVLNode n) {
        if (n == null)
            return -1;
        return n.height;
    }
 
    private void setBalance(AVLNode... AVLNodes) {
        for (AVLNode n : AVLNodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }
 
    public void printBalance() {
        printBalance(root);
    }
 
    private void printBalance(AVLNode n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }
 
    private void reheight(AVLNode AVLNode) {
        if (AVLNode != null) {
            AVLNode.height = 1 + Math.max(height(AVLNode.left), height(AVLNode.right));
        }
    }

    public int printHeight(AVLNode root) {
        if (root == null) return 0;
        Queue<AVLNode> q = new LinkedList<AVLNode>();
        q.add(root);
        int height = 0;
        while (true) {
            int AVLNodeCount = q.size();
            if (AVLNodeCount == 0)
                break;
            while (AVLNodeCount > 0) {
                AVLNode AVLNode = q.peek();
                q.remove();
                if (AVLNode.left != null)
                    q.add(AVLNode.left);
                if (AVLNode.right != null)
                    q.add(AVLNode.right);
                AVLNodeCount--;
            }
            height++;
        }
        return height;
    }
 
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
 
        System.out.println("Inserting values 1 to 10");
        for (int i = 1; i < 10; i++)
            tree.insert(i);
 
        System.out.print("Printing balance: ");
        tree.printBalance();

        System.out.println("Finding 1: " + tree.find(1));
        System.out.println("Finding 5: " + tree.find(5));
        System.out.println("Finding 8: " + tree.find(8));
        System.out.println("Finding 0: " + tree.find(0));
        System.out.println("");
        tree.printBalance();
    }
}
