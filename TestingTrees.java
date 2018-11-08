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
        SkipList<Integer> slt;
        Random randomGen = new Random();

        ArrayList<Integer> rand = new ArrayList<Integer>();
        int avgBSTLevels = 0;
        int avgZTLevels = 0;
        int avgAVLTLevels = 0;
        int avgSLTLevels = 0;
        long avgBSTTime = 0;
        long avgZTTime = 0;
        long avgAVLTTime = 0;
        long avgSLTTime = 0;
        long startTime = 0;

        System.out.println("---------------------INSERT TEST--------------------");
        System.out.println("|                                                  |");

        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 50; i++) {
                rand.add(i);
                rand.add(i + 50);
            }

            Collections.shuffle(rand);
            
            bst = new BinarySearchTree();
            zt = new ZipTree();
            avlt = new AVLTree();
            slt = new SkipList<Integer>();

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

            startTime = System.nanoTime();
            for (int i = 0; i < rand.size(); i++) {
                slt.insert(rand.get(i));
            }

            avgSLTTime += System.nanoTime() - startTime;
            avgSLTLevels += slt.printHeight();


            rand.clear();
        }

        avgBSTLevels = avgBSTLevels / 100;
        avgZTLevels = avgZTLevels / 100;
        avgAVLTLevels = avgAVLTLevels / 100;
        avgSLTLevels = avgSLTLevels / 100; 
        avgBSTTime = avgBSTTime / (long)100;
        avgZTTime = avgZTTime / (long)100;
        avgAVLTTime = avgAVLTTime / (long)100;
        avgSLTTime = avgSLTTime / (long)100;
        avgBSTTime /= 1000;
        avgZTTime /= 1000;
        avgAVLTTime /= 1000;
        avgSLTTime /= 1000;

        System.out.println("| ------------------100 Elements------------------ |");
        System.out.printf("| |%11s %16s %17s| |\n", "", "Avg # of Levels", "Avg Time Taken");
        System.out.println("| |----------------------------------------------| |");
        System.out.printf("| |%11s %16d %17d| |\n", "BST", avgBSTLevels, avgBSTTime);
        System.out.printf("| |%11s %16d %17d| |\n", "ZT", avgZTLevels, avgZTTime);
        System.out.printf("| |%11s %16d %17d| |\n", "AVLT", avgAVLTLevels, avgAVLTTime);
        System.out.printf("| |%11s %16d %17d| |\n", "SLT", avgSLTLevels, avgSLTTime);
        System.out.println("| ------------------------------------------------ |");
        System.out.println("|                                                  |");

        randomGen = new Random();

        rand = new ArrayList<Integer>();
        avgBSTLevels = 0;
        avgZTLevels = 0;
        avgAVLTLevels = 0;
        avgSLTLevels = 0;
        avgBSTTime = 0;
        avgZTTime = 0;
        avgAVLTTime = 0;
        avgSLTTime = 0;
        startTime = 0;

        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 5000; i++) {
                rand.add(i);
                rand.add(i + 5000);
            }

            Collections.shuffle(rand);

            bst = new BinarySearchTree();
            zt = new ZipTree();
            avlt = new AVLTree();
            slt = new SkipList<Integer>();

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

            startTime = System.nanoTime();
            for (int i = 0; i < rand.size(); i++) {
                slt.insert(rand.get(i));
            }

            avgSLTTime += System.nanoTime() - startTime;
            avgSLTLevels += slt.printHeight();

            rand.clear();
        }

        avgBSTLevels = avgBSTLevels / 100;
        avgZTLevels = avgZTLevels / 100;
        avgAVLTLevels = avgAVLTLevels / 100;
        avgSLTLevels = avgSLTLevels / 100;
        avgBSTTime = avgBSTTime / (long)100;
        avgZTTime = avgZTTime / (long)100;
        avgAVLTTime = avgAVLTTime / (long)100;
        avgSLTTime = avgSLTTime / (long) 100;
        avgBSTTime /= 1000;
        avgZTTime /= 1000;
        avgAVLTTime /= 1000;
        avgSLTTime /= 1000;

        System.out.println("| -----------------10000 Elements----------------- |");
        System.out.printf("| |%11s %16s %17s| |\n", "", "Avg # of Levels", "Avg Time Taken");
        System.out.println("| |----------------------------------------------| |");
        System.out.printf("| |%11s %16d %17d| |\n", "BST", avgBSTLevels, avgBSTTime);
        System.out.printf("| |%11s %16d %17d| |\n", "ZT", avgZTLevels, avgZTTime);
        System.out.printf("| |%11s %16d %17d| |\n", "AVLT", avgAVLTLevels, avgAVLTTime);
        System.out.printf("| |%11s %16d %17d| |\n", "SLT", avgSLTLevels, avgSLTTime);
        System.out.println("| ------------------------------------------------ |");
        System.out.println("|                                                  |");
 
        randomGen = new Random();

        rand = new ArrayList<Integer>();
        avgBSTLevels = 0;
        avgZTLevels = 0;
        avgAVLTLevels = 0;
        avgSLTLevels = 0;
        avgBSTTime = 0;
        avgZTTime = 0;
        avgAVLTTime = 0;
        avgSLTTime = 0;
        startTime = 0;

        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 500000; i++) {
                rand.add(i);
                rand.add(i + 500000);
            }

            Collections.shuffle(rand);

            bst = new BinarySearchTree();
            zt = new ZipTree();
            avlt = new AVLTree();
            slt = new SkipList<Integer>();

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

            startTime = System.nanoTime();
            for (int i = 0; i < rand.size(); i++) {
                slt.insert(rand.get(i));
            }

            avgSLTTime += System.nanoTime() - startTime;
            avgSLTLevels += slt.printHeight();

            rand.clear();
        }

        avgBSTLevels = avgBSTLevels / 100;
        avgZTLevels = avgZTLevels / 100;
        avgAVLTLevels = avgAVLTLevels / 100;
        avgSLTLevels = avgSLTLevels / 100;
        avgBSTTime = avgBSTTime / (long)100;
        avgZTTime = avgZTTime / (long)100;
        avgAVLTTime = avgAVLTTime / (long)100;
        avgSLTTime = avgSLTTime / (long) 100;
        avgBSTTime /= 1000;
        avgZTTime /= 1000;
        avgAVLTTime /= 1000;
        avgSLTTime /= 1000;


        System.out.println("| ----------------1000000 Elements---------------- |");
        System.out.printf("| |%11s %16s %17s| |\n", "", "Avg # of Levels", "Avg Time Taken");
        System.out.println("| |----------------------------------------------| |");
        System.out.printf("| |%11s %16d %17d| |\n", "BST", avgBSTLevels, avgBSTTime);
        System.out.printf("| |%11s %16d %17d| |\n", "ZT", avgZTLevels, avgZTTime);
        System.out.printf("| |%11s %16d %17d| |\n", "AVLT", avgAVLTLevels, avgAVLTTime);
        System.out.printf("| |%11s %16d %17d| |\n", "SLT", avgSLTLevels, avgSLTTime);
        System.out.println("| ------------------------------------------------ |");
        System.out.println("|                                                  |");
        System.out.println("----------------------------------------------------");
    }
}
