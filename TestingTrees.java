import java.util.Random;
public class TestingTrees {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        ZipTree zt = new ZipTree();

        Random randomGen = new Random();

        for(int i = 0; i < 1000; i++) {
            int ran = randomGen.nextInt(100);
            bst.insert(ran);
            zt.insert(ran);
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
