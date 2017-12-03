import util.collections.RedBlackTree;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        RedBlackTree<Integer> tree = new RedBlackTree<>();
//        for (int i = 0; i < 10000; i++){
//            tree.add(
//                    r.nextInt() % 1000
//            );
//        }

        for (int i = 1; i < 6; i++){
            tree.add(
                    i
            );
        }

//        for (int i = 0; i < 10000; i++){
//            tree.remove(
//                    r.nextInt() % 10000
//            );
//        }
        System.out.println("The end!");
    }
}
