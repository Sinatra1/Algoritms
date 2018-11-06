package vlad.shumilov;

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
    };

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
            //TODO:
        }

        public void decrement() {
            //TODO:
        }

        public boolean equals(Iterator iterator) {
            return current.equals(iterator.current);
        }
    }

    protected Node<T> nil;
    protected Node<T> root = nil;

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
        return new Iterator(this, nil);
    }

    public void insert(T value) {
        Node<T> z = new Node<>(value);
        Node<T> y = nil;
        Node<T> x = root;

        while (x != null) {
            y = x;
            x = less(z.value, x.value) ? x.left : x.right;
        }

        z.parent = y;

        if (!y.equals(nil)) {
            if (less(z.value, y.value)) {
                y.left = z;
            } else {
                y.right = z;
            }
        } else {
            root = z;
        }

        z.left = nil;
        z.right = nil;
        z.color = Color.RED;

        //TODO: insertFixup(z);
    }

    protected Boolean less(T value1, T value2) {
        return Double.parseDouble(value1.toString()) < Double.parseDouble(value2.toString());
    }
}
