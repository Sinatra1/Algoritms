package vlad.shumilov;

public class List<T> {

    protected class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    protected Node<T> head;

    public void insert(T data) {
        Node<T> node = new Node<>(data);
        node.next = head;

        if (head != null) {
            node.prev = node;
        }

        head = node;
    }

    public void remove(T data) {
        Node<T> node = getByData(data);

        if (node == null) {
            return;
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }

        node = null;
    }

    public Node<T> getByData(T data) {
        Node<T> node = head;

        while (node.next != null) {
            if (node.data.equals(data)) {
                return node;
            }

            node = node.next;
        }

        return node;
    }

    public void out() {

        if (head == null) {
            throw new RuntimeException("list is empty");
        }

        Node<T> node = head;

        while (node.next != null) {
            System.out.print(node.data + "\n");

            node = node.next;
        }
    }
}
