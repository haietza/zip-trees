import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.TreeMap;

public class TestingTrees4 {
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

        System.out.println("-------------------SEARCH TEST----------------------");
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

            for (int i = 0; i < rand.size(); i++) {
                bst.insert(rand.get(i));
            }
            
            for (int i = 0; i < rand.size(); i++) {
                zt.insert(rand.get(i));
            }

            for (int i = 0; i < rand.size(); i++) {
                avlt.insert(rand.get(i));
            }

            for (int i = 0; i < rand.size(); i++) {
                slt.insert(rand.get(i));
            }

            avgBSTLevels += bst.printHeight(bst.root);
            avgZTLevels += zt.printHeight(zt.root);
            avgAVLTLevels += avlt.printHeight(avlt.root);
            avgSLTLevels += slt.printHeight();

            Collections.shuffle(rand);
            
            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!bst.find(rand.get(i))) System.out.println("BST FAILED");
            }
            avgBSTTime += System.nanoTime() - startTime;
            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!zt.find(rand.get(i), zt.root)) System.out.println("ZT FAILED");
            }
            avgZTTime += System.nanoTime() - startTime;
            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!avlt.find(rand.get(i))) System.out.println("AVLT Failed");
            }
            avgAVLTTime += System.nanoTime() - startTime;
            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!slt.contains(rand.get(i))) System.out.println("SLT Failed");
            }
            avgSLTTime += System.nanoTime() - startTime;

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

            for (int i = 0; i < rand.size(); i++) {
                bst.insert(rand.get(i));
            }

            for (int i = 0; i < rand.size(); i++) {
                zt.insert(rand.get(i));
            }

            for (int i = 0; i < rand.size(); i++) {
                avlt.insert(rand.get(i));
            }

            for (int i = 0; i < rand.size(); i++) {
                slt.insert(rand.get(i));
            }

            avgBSTLevels += bst.printHeight(bst.root);
            avgZTLevels += zt.printHeight(zt.root);
            avgAVLTLevels += avlt.printHeight(avlt.root);
            avgSLTLevels += slt.printHeight();

            Collections.shuffle(rand);

            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!bst.find(rand.get(i))) System.out.println("BST FAILED");
            }
            avgBSTTime += System.nanoTime() - startTime;
            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!zt.find(rand.get(i),zt.root)) System.out.println("ZT FAILED");
            }
            avgZTTime += System.nanoTime() - startTime;
            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!avlt.find(rand.get(i))) System.out.println("AVLT Failed");
            }
            avgAVLTTime += System.nanoTime() - startTime;
            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!slt.contains(rand.get(i))) System.out.println("SLT Failed");
            }
            avgSLTTime += System.nanoTime() - startTime;

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

            for (int i = 0; i < rand.size(); i++) {
                bst.insert(rand.get(i));
            }

            for (int i = 0; i < rand.size(); i++) {
                zt.insert(rand.get(i));
            }

            for (int i = 0; i < rand.size(); i++) {
                avlt.insert(rand.get(i));
            }

            for (int i = 0; i < rand.size(); i++) {
                slt.insert(rand.get(i));
            }

            avgBSTLevels += bst.printHeight(bst.root);
            avgZTLevels += zt.printHeight(zt.root);
            avgAVLTLevels += avlt.printHeight(avlt.root);
            avgSLTLevels += slt.printHeight();

            Collections.shuffle(rand);

            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!bst.find(rand.get(i))) System.out.println("BST FAILED");
            }
            avgBSTTime += System.nanoTime() - startTime;
            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!zt.find(rand.get(i), zt.root)) System.out.println("ZT FAILED");
            }
            avgZTTime += System.nanoTime() - startTime;
            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!avlt.find(rand.get(i))) System.out.println("AVLT Failed");
            }
            avgAVLTTime += System.nanoTime() - startTime;
            startTime = System.nanoTime();
            for(int i = 0; i < rand.size(); i++) {
                if(!slt.contains(rand.get(i))) System.out.println("SLT Failed");
            }
            avgSLTTime += System.nanoTime() - startTime;

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
