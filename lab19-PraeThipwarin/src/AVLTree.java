import java.util.Random;
import java.util.Stack;

interface Iterator<E> {
    boolean hasNext();
    E next();
}
interface Tree<E> {
    void add(E e);
    Iterator<E> iterator();
}

public class AVLTree<E extends Comparable<? super E>> implements Tree<E> {
    private static class TreeNode<E extends Comparable<? super E>> {
        E val;
        TreeNode<E> left, right;
        public TreeNode(E val) {
            this.val = val;
        }
        public TreeNode(E val, TreeNode<E> left, TreeNode<E> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public static int height(TreeNode<?> t) {
            if (t == null) return 0;
            int lh = height(t.left);
            int rh = height(t.right);
            return Math.max(lh, rh) + 1;
        }

        public TreeNode<E> insert(E e) {
            // insert recursively
            TreeNode<E> l = left, r = right;
            if (e.compareTo(val) < 0) {
                l = l == null ? new TreeNode<>(e) : l.insert(e);
            }
            else
                r = r == null ? new TreeNode<>(e) : r.insert(e);
            // rebalance if needed
            return rebalance(val, l ,r);
        }

        private TreeNode<E> rebalance(E val, TreeNode<E> l, TreeNode<E> r) {
            int lh = height(l);
            int rh = height(r);
            if (lh - rh > 1) { // heavy left
                TreeNode<E> ll = l.left, lr = l.right;
                int llh = height(ll), lrh = height(lr);
                if (llh - lrh >= 0) { // case 1
                    r = new TreeNode<>(val, lr, r);
                    val = l.val;
                    l = ll;
                }
                else { // case 2
                    TreeNode<E> lrl = lr.left, lrr = lr.right;
                    r = new TreeNode<>(val, lrr, r);
                    val = lr.val;
                    l = new TreeNode<>(l.val, ll, lrl);
                }
            }
            else if (rh - lh > 1) { // heavy right
                TreeNode<E> rl = r.left, rr = r.right;
                int rlh = height(rl), rrh = height(rr);
                if (rlh - rrh > 0) { // case 3
                    TreeNode<E> rll = rl.left, rlr = rl.right;
                    l = new TreeNode<>(val, l, rll);
                    val = rl.val;
                    r = new TreeNode<>(r.val, rlr, rr);
                }
                else { // case 4
                    l = new TreeNode<>(val, l, rl);
                    val = r.val;
                    r = rr;
                }
            }
            return new TreeNode<>(val, l, r);
        }
    }

    private TreeNode<E> root;

    @Override
    public void add(E e) {
        if (root == null)
            root = new TreeNode<>(e);
        else
            root = root.insert(e);
    }

    @Override
    public Iterator<E> iterator() {
//        return new Iterator<E>() {
//            private Stack<TreeNode<E>> stack = new Stack<>();
//
//            {
//                if (root != null) stack.push(root);
//            }
//
//            @Override
//            public boolean hasNext() {
//                return !stack.isEmpty();
//            }
//
//            @Override
//            public E next() {
//                if (!hasNext()) throw new RuntimeException("unimplemented");
//                TreeNode<E> node = stack.pop();
//
//                // Push right first, so left is processed first
//                if (node.right != null) stack.push(node.right);
//                if (node.left != null) stack.push(node.left);
//
//                return node.val;
//            }
//        };

        return new Iterator<E>() {
            private Stack<TreeNode<E>> stack1 = new Stack<>();
            private Stack<TreeNode<E>> stack2 = new Stack<>();

            {
                if (root != null) {
                    stack1.push(root);
                    while (!stack1.isEmpty()) {
                        TreeNode<E> node = stack1.pop();
                        stack2.push(node);
                        if (node.left != null) stack1.push(node.left);
                        if (node.right != null) stack1.push(node.right);
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return !stack2.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) throw new RuntimeException("unimplemented");
                return stack2.pop().val;
            }
        };
    }

    public static void main(String[] args) {
        int N = 63;
        int seed = 2022;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = i;
        Random rand = new Random(seed);
        // create a random permutation
        for (int i = 0; i < N; i++) {
            int j = rand.nextInt(N-i)+i;
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        Tree<Integer> t = new AVLTree<>();
        for (int i = 0; i < N; i++)
            t.add(a[i]);

        for (Iterator<Integer> itr = t.iterator(); itr.hasNext(); )
            System.out.print(itr.next() + " ");
        System.out.println();

        // expected preorder:
        // 32 19 9 5 2 1 0 3 4 7 6 8 16 13 11 10 12 15 14 17 18 27 23 21 20 22 25 24 26 30 29 28 31
        // 47 40 36 34 33 35 38 37 39 44 42 41 43 45 46 55 50 48 49 52 51 54 53 59 57 56 58 61 60 62

        // expected postorder (challenge):
        // 0 1 4 3 2 6 8 7 5 10 12 11 14 15 13 18 17 16 9 20 22 21 24 26 25 23 28 29 31 30 27 19 33
        // 35 34 37 39 38 36 41 43 42 46 45 44 40 49 48 51 53 54 52 50 56 58 57 60 62 61 59 55 47 32
    }
}