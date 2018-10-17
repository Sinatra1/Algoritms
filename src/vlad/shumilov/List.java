package vlad.shumilov;

public class List<T> {

    protected Node<T> head;
    protected Node<T> currentNode;

    public void insert(T data) {
        Node<T> node = new Node<>(data);
        node.next = head;

        if (head != null) {
            head.prev = node;
        }

        head = node;
    }

    public void remove(T data) {
        getByData(data);

        if (currentNode == null) {
            return;
        }

        if (currentNode.prev != null) {
            currentNode.prev.next = currentNode.next;
        } else {
            head = currentNode.next;
        }

        if (currentNode.next != null) {
            currentNode.next.prev = currentNode.prev;
        }

        currentNode = null;
    }

    public Node<T> getByData(T data) {
        currentNode = head;

        while (currentNode.next != null) {
            if (currentNode.data.equals(data)) {
                return currentNode;
            }

            currentNode = currentNode.next;
        }

        return currentNode;
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

        System.out.print(node.data + "\n");
    }
}
