package vlad.shumilov;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        printStack();

        printQueue();

        printList();

        printBinaryTree();

        printInsertionSort();
    }

    protected static void printStack() {
        System.out.print("Stack:\n");

        Stack<String> stack = new Stack<>(2);

        stack.push("1");
        stack.push("2");

        System.out.print(stack.pop() + "\n");
        System.out.print(stack.pop() + "\n");
    }

    protected static void printQueue() {
        System.out.print("Queue:\n");

        Queue<String> queue = new Queue<>(2);

        queue.enqueue("1");
        queue.enqueue("2");
        System.out.print(queue.dequeue() + "\n");
        queue.enqueue("3");

        System.out.print(queue.dequeue() + "\n");
        System.out.print(queue.dequeue() + "\n");
    }

    protected static void printList() {
        System.out.print("List:\n");

        List<String> list = new List<>();
        list.insert("1");
        list.insert("2");
        list.insert("3");

        list.remove("2");

        list.out();
    }

    protected static void printBinaryTree() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree
                .add(9)
                .add(10)
                .add(3)
                .add(2)
                .add(6)
                .add(7)
                .add(5)
                .add(8)
                .add(11)
                .add(1)
                .add(4)
                .add(12)
                .add(13)
                .add(15)
                .add(14)
                .add(16);

        System.out.print("BinaryTree min: " + binaryTree.min() + "\n");
        System.out.print("BinaryTree max: " + binaryTree.max() + "\n");

        System.out.print("BinaryTree asc order:\n");

        BinaryTree.Iterator i = binaryTree.begin();

        while (!i.equals(binaryTree.end())) {
            System.out.print(i.getCurrent() + "\n");
            i.increment();
        }

        System.out.print(i.getCurrent() + "\n");

        System.out.print("BinaryTree desc order:\n");

        BinaryTree.Iterator j = binaryTree.end();

        while (!j.equals(binaryTree.begin())) {
            System.out.print(j.getCurrent() + "\n");
            j.decrement();
        }

        System.out.print(j.getCurrent() + "\n");

        System.out.print("BinaryTree walkInOrderAsc:\n");

        binaryTree.walkInOrderAsc(value ->
        {
            System.out.print(value + "\n");
            return value;
        });

        System.out.print("BinaryTree walkInOrderDesc:\n");

        binaryTree.walkInOrderDesc(value ->
        {
            System.out.print(value + "\n");
            return value;
        });

        Integer found1 = 5;

        BinaryTree.Iterator iter = binaryTree.find(found1);

        if (iter.getCurrent() == null) {
            System.out.print(MessageFormat.format("BinaryTree {0} not found\n", found1));
        } else {
            System.out.print(MessageFormat.format("BinaryTree {0} was found: {1}\n", found1, iter));
        }

        binaryTree.remove(9);
        binaryTree.remove(1);
        binaryTree.remove(6);

        binaryTree.walkInOrderAsc(value ->
        {
            System.out.print(value + "\n");
            return value;
        });
    }

    protected static void printInsertionSort() {
        System.out.print("insertionSort before:\n");

        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(2);

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "\n");
        }

        InsertionSort insertionSort = new InsertionSort();
        ArrayList<Integer> sortedList = insertionSort.sort(list);

        System.out.print("insertionSort after sort:\n");

        for (int i = 0; i < sortedList.size(); i++) {
            System.out.print(sortedList.get(i) + "\n");
        }
    }
}
