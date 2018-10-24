public class TestingTrees {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        ZipTree zt = new ZipTree();

        int[] nums = {3,8,1,4,6,2,10,9,20,25,15,16};
        Node[] nodes = {new Node(3), new Node(8), new Node(1),
                    new Node(4), new Node(6), new Node(2),
                    new Node(10), new Node(9), new Node(20),
                    new Node(25), new Node(15), new Node(16)};

        for(int i : nums) bst.insert(i);
        for(Node n : nodes) zt.insert(n);
        System.out.println("---INSERTION TEST---");
        System.out.println("BST:");
        bst.display(bst.root);
        System.out.println();
        System.out.println("ZT:");
        zt.display(zt.root);
        System.out.println();
        System.out.println("---PRINT LEVELS---");
        System.out.println("BST:");
        bst.printLevelOrder(bst.root);
        System.out.println("ZT:");
        zt.printLevelOrder(zt.root);
    }
}
