package vlad.shumilov;

import java.util.function.Function;

public class RedBlackTree<T> {
    protected enum Color {
        RED(true), BLACK(false);

        protected boolean value = false;

        Color(Boolean value) {
            this.value = value;
        }

        public boolean getValue() {
            return value;
        }
    }

    protected class Node<T> {
        public T value;

        public Node<T> left;
        public Node<T> right;
        public Node<T> parent;
        public Color color = Color.BLACK;

        Node(T value) {
            this.value = value;
        }
    }

    protected class Iterator {
        protected RedBlackTree<T> tree;
        protected Node<T> current;

        Iterator(RedBlackTree<T> tree, Node<T> node) {
            this.tree = tree;
            current = node;
        }

        public T getCurrent() {
            if (current == null) {
                return null;
            }

            return current.value;
        }

        public void increment() {
            if (tree == null || current == null) {
                return;
            }

            current = tree.getNext(current);
        }

        public void decrement() {
            if (tree == null || current == null) {
                current = max(tree.root);
                return;
            }

            current = tree.getPrevious(current);
        }

        public boolean equals(Iterator iterator) {
            return current.equals(iterator.current);
        }
    }

    public Node<T> root = null;

    public RedBlackTree<T> add(T value) {
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

        //z.left = null;
        //z.right = null;
        z.color = Color.RED;

        //insertFixup(z);
    }

    public Iterator find(T value) {
        return new Iterator(this, search(value));
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

    public Node<T> min(Node<T> x) {
        if (x != null) {
            while (x.left != null && x.left != null) {
                x = x.left;
            }
        }

        return x;
    }

    public Node<T> max(Node<T> x) {
        if (x != null) {
            while (x.right != null && x.right == null) {
                x = x.right;
            }
        }

        return x;
    }

    public Node<T> getNext(Node<T> x) {
        if (x != null && x.right != null) {
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

        while (y.left != null && x.equals(y.left)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    public void remove(T value) {
        Node<T> z = search(value);

        if (z != null) {
            remove(z);
        }
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

        if (!y.equals(z)) {
            z.value = y.value;

            if (y.color.equals(Color.BLACK)) {
                removeFixup(x);
            }

            y = null;
        }
    }

    public void walkInOrderAsc(Function<T, T> fun) {
        walkInOrderAsc(root, fun);
    }

    public void walkInOrderDesc(Function<T, T> fun) {
        walkInOrderDesc(root, fun);
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

    public void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;

        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            root = y;
        } else {
            if (x.equals(x.parent.left)) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }

        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node<T> x) {
        Node<T> y = x.left;
        x.left = y.right;

        if (y.right != null) {
            y.right.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            root = y;
        } else {
            if (x.equals(x.parent.right)) {
                x.parent.right = y;
            } else {
                x.parent.left = y;
            }
        }

        y.right = x;
        x.parent = y;
    }

    protected void insertFixup(Node<T> z) {
        Node<T> y;

        while (z.parent != null && z.parent.color.equals(Color.RED)) {
            if (z.parent.equals(z.parent.parent.left)) {
                y = z.parent.parent.right;

                if (y.color.equals(Color.RED)) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z.equals(z.parent.right)) {
                        z = z.parent;
                        leftRotate(z);
                    }

                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;

                    rightRotate(z.parent.parent);
                }
            } else {
                y = z.parent.parent.left;

                if (y != null && y.color.equals(Color.RED)) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (y != null && z.equals(z.parent.left)) {
                        z = z.parent.left;
                        rightRotate(z);
                    }

                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;

                    if (y != null) {
                        leftRotate(z.parent.parent);
                    }
                }
            }
        }

        root.color = Color.BLACK;
    }

    public void removeFixup(Node<T> x) {
        if (x == null) {
            return;
        }

        Node<T> w;

        while (!x.equals(root) && x.color.equals(Color.BLACK)) {
            if (x.equals(x.parent.left)) {
                w = x.parent.right;

                if (w.color.equals(Color.RED)) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }

                if (w.left.color.equals(Color.BLACK) && w.right.color.equals(Color.BLACK)) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.right.color.equals(Color.BLACK)) {
                        w.left.color = Color.BLACK;
                        w.color = Color.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }

                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    x.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                w = x.parent.left;

                if (w.color.equals(Color.RED)) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }

                if (w.right.color.equals(Color.BLACK) && w.left.color.equals(Color.BLACK)) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.left.color.equals(Color.BLACK)) {
                        w.right.color = Color.BLACK;
                        w.color = Color.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }

                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    x.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }

        x.color = Color.BLACK;
    }

    public void destroy(Node<T> x) {
        if (x == null) {
            return;
        }

        destroy(x.left);
        destroy(x.right);
        x = null;
    }

    protected Boolean less(T value1, T value2) {
        return Double.parseDouble(value1.toString()) < Double.parseDouble(value2.toString());
    }
}
