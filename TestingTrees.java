public class TestingTrees {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        ZipTree zt = new ZipTree();

        int[] nums = {3,8,1,4,6,2,10,9,20,25,15,16};

        for(int i : nums) {
            bst.insert(i);
            zt.insert(i);
        }
        System.out.println("---INSERTION TEST---");
        System.out.println("BST:");
        bst.display(bst.root);
        System.out.println();
        System.out.println("ZT:");
        zt.display(zt.root);
        System.out.println();
        System.out.println();
        System.out.println("---PRINT LEVELS---");
        System.out.println("BST:");
        bst.printLevelOrder(bst.root);
        System.out.println();
        System.out.println("ZT:");
        zt.printLevelOrder(zt.root);
    }
}
