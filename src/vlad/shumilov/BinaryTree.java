package vlad.shumilov;

import java.util.function.Function;

public class BinaryTree<T> {
    protected class Node<T> {
        public T value;

        public Node<T> left;
        public Node<T> right;
        public Node<T> parent;

        Node(T value) {
            this.value = value;
        }

        public void delete() {
            left = null;
            right = null;
        }
    }

    protected class Iterator {
        protected BinaryTree<T> tree;
        protected Node<T> current;

        Iterator(BinaryTree<T> tree, Node<T> node) {
            this.tree = tree;
            current = node;
        }

        Iterator(BinaryTree<T> tree) {
            this.tree = tree;
            current = null;
        }

        public T getCurrent() {
            if (current == null) {
                return null;
            }

            return current.value;
        }

        public void increment() {
            if (current == null || tree == null) {
                return;
            }

            current = tree.getNext(current);
        }

        public void decrement() {
            if (current == null || tree == null) {
                current = max(tree.root);
                return;
            }

            current = tree.getPrevious(current);
        }

        public boolean equals(Iterator iterator) {
            return current.equals(iterator.current);
        }
    }

    protected Node<T> min;
    protected Node<T> max;
    protected Node<T> succ;
    public Node<T> root;
    public Boolean valueCopy = true;

    BinaryTree() {

    }

    public BinaryTree<T> add(T value) {
        insert(value);
        return this;
    }

    public T min() {
        if (isEmpty()) {
            throw new RuntimeException("is empty");
        }

        return min(root).value;
    }

    public T max() {
        if (isEmpty()) {
            throw new RuntimeException("is empty");
        }

        return max(root).value;
    }

    protected Boolean isEmpty() {
        return root == null;
    }

    public void walkInOrderAsc(Function<T, T> fun) {
        walkInOrderAsc(root, fun);
    }

    public void walkInOrderDesc(Function<T, T> fun) {
        walkInOrderDesc(root, fun);
    }

    public Iterator begin() {
        return new Iterator(this, min(root));
    }

    public Iterator end() {
        return new Iterator(this, max(root));
    }

    public void insert(T value) {
        Node<T> z = new Node<>(value);
        Node<T> y = null;
        Node<T> x = root;

        while (x != null) {
            y = x;
            x = less(z.value, x.value) ? x.left : x.right;
        }

        z.parent = y;

        if (y != null) {
            if (less(z.value, y.value)) {
                y.left = z;
            } else {
                y.right = z;
            }
        } else {
            root = z;
        }
    }

    public Node<T> search(T value) {
        if (root == null) {
            return null;
        }

        Node<T> x = root;

        while (x != null && (less(x.value, value) || less(value, x.value))) {
            x = less(value, x.value) ? x.left : x.right;
        }

        return x;
    }

    public Iterator find(T value) {
        return new Iterator(this, search(value));
    }

    public Node<T> min(Node<T> x) {
        if (x != null) {
            while (x.left != null) {
                x = x.left;
            }
        }

        return x;
    }

    public Node<T> max(Node<T> x) {
        if (x != null) {
            while (x.right != null) {
                x = x.right;
            }
        }

        return x;
    }

    public Node<T> getNext(Node<T> x) {
        if (x.right != null) {
            return min(x.right);
        }

        Node<T> y = x.parent;

        while (y != null && x.equals(y.right)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    public Node<T> getPrevious(Node<T> x) {
        if (x.left != null) {
            return max(x.left);
        }

        Node<T> y = x.parent;

        while (y != null && x.equals(y.left)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    public void remove(Node<T> z) {
        Node<T> y = null;

        if (z.left == null || z.right == null) {
            y = z;
        } else {
            y = getNext(z);
        }

        Node<T> x = y.left != null ? y.left : y.right;

        if (x != null) {
            x.parent = y.parent;
        }

        if (y.parent == null) {
            root = x;
        } else {
            if (y.equals(y.parent.left)) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }

        if (valueCopy) {
            if (!y.equals(z)) {
                z.value = y.value;
            }

            y.left = y.right = null;

            y = null;
        } else {
            if (!y.equals(z)) {
                y.left = z.left;
                y.right = z.right;
                y.parent = z.parent;

                if (z.left != null) {
                    z.left.parent = y;
                }

                if (z.right != null) {
                    z.right.parent = y;
                }

                if (root.equals(z)) {
                    root = y;
                } else {
                    if (z.equals(z.parent.left)) {
                        z.parent.left = y;
                    } else {
                        z.parent.right = y;
                    }
                }
            }

            z.left = z.right = null;
            z = null;
        }
    }

    public void remove(T value) {
        Node<T> z = search(value);

        if (z != null) {
            remove(z);
        }
    }

    public void walkInOrderAsc(Node<T> x, Function<T, T> fun) {
        if (x == null) {
            return;
        }

        walkInOrderAsc(x.left, fun);

        try {
            fun.apply(x.value);
        } catch (Exception e) {
            return;
        }

        walkInOrderAsc(x.right, fun);
    }

    public void walkInOrderDesc(Node<T> x, Function<T, T> fun) {
        if (x == null) {
            return;
        }

        walkInOrderDesc(x.right, fun);

        try {
            fun.apply(x.value);
        } catch (Exception e) {
            return;
        }

        walkInOrderDesc(x.left, fun);
    }

    protected Boolean less(T value1, T value2) {
        return Double.parseDouble(value1.toString()) < Double.parseDouble(value2.toString());
    }
}
