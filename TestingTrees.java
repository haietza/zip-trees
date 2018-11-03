import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.TreeMap;

public class TestingTrees {
    public static void main(String[] args) {
        BinarySearchTree bst;
        ZipTree zt;
        AVLTree avlt;
        Random randomGen = new Random();

        ArrayList<Integer> rand = new ArrayList<Integer>();
        int avgBSTLevels = 0;
        int avgZTLevels = 0;
        int avgAVLTLevels = 0;
        long avgBSTTime = 0;
        long avgZTTime = 0;
        long avgAVLTTime = 0;
        long startTime = 0;

        System.out.println("---------------------INSERT TEST--------------------");
        System.out.println("|                                                  |");

        for (int j = 0; j < 1000000; j++) {
            for (int i = 0; i < 500000; i++) {
                rand.add(i);
                rand.add(i + 500000);
            }

            Collections.shuffle(rand);
            
            bst = new BinarySearchTree();
            zt = new ZipTree();
            avlt = new AVLTree();

            startTime = System.nanoTime();
            for (int i = 0; i < rand.size(); i++) {
                bst.insert(rand.get(i));
            }

            avgBSTTime += System.nanoTime() - startTime;
            avgBSTLevels += bst.printHeight(bst.root);

            startTime = System.nanoTime();
            for (int i = 0; i < rand.size(); i++) {
                zt.insert(rand.get(i));
            }

            avgZTTime += System.nanoTime() - startTime;
            avgZTLevels += zt.printHeight(zt.root);

            startTime = System.nanoTime();
            for (int i = 0; i < rand.size(); i++) {
                avlt.insert(rand.get(i));
            }

            avgAVLTTime += System.nanoTime() - startTime;
            avgAVLTLevels += avlt.printHeight(avlt.root);

            rand.clear();
        }

        avgBSTLevels = avgBSTLevels / 100;
        avgZTLevels = avgZTLevels / 100;
        avgAVLTLevels = avgAVLTLevels / 100;
        avgBSTTime = avgBSTTime / (long)100;
        avgZTTime = avgZTTime / (long)100;
        avgAVLTTime = avgAVLTTime / (long)100;
        avgBSTTime /= 1000;
        avgZTTime /= 1000;
        avgAVLTTime /= 1000;

        System.out.println("| ------------------100 Elements------------------ |");
        System.out.println("| |           | Avg # of Levels | Avg Time Taken | |");
        System.out.println("| |    BST    |       " + avgBSTLevels + "        |       " + avgBSTTime  + "      | ");
        System.out.println("| |    ZT     |       " + avgZTLevels + "        |       " + avgZTTime  + "      | ");
        System.out.println("| |    AVLT   |       " + avgAVLTLevels + "        |       " + avgAVLTTime  + "      | ");
        System.out.println("| ------------------------------------------------ |");
        System.out.println("|                                                  |");
        System.out.println("----------------------------------------------------");
    }
}