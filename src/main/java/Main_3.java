import util.collections.RedBlackTree;

public abstract class Main_3 {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        for (int i = 1; i < 6; i++) {
            System.out.println("Adding " + i);
            tree.add(
                    i
            );
            tree.print();
        }

//        for (int i = 0; i < 10000; i++){
//            tree.remove(
//                    r.nextInt() % 10000
//            );
//        }
    }
}
