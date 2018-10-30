import java.util.ArrayList;
import java.util.Collections;

public class TestingTrees {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        ZipTree zt = new ZipTree();

        ArrayList<Integer> rand = new ArrayList<Integer>();

        for(int i = 0; i < 500; i++) {
            rand.add(i);
            rand.add(i + 500);
        }
        
        Collections.shuffle(rand);
        
        for(int i = 0; i < rand.size(); i++) {
            bst.insert(rand.get(i));
            zt.insert(rand.get(i));
        }

        System.out.println("---PRINT HEIGHTS---");
        System.out.println("BST:");
        System.out.println(bst.printHeight(bst.root));
        System.out.println();
        System.out.println("ZT:");
        System.out.println(zt.printHeight(zt.root));
    }
}
