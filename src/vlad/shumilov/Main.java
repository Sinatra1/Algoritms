package vlad.shumilov;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(6);

        printStack();

        printQueue();

        printList();

        printBinaryTree((ArrayList<Integer>) list.clone());

        printHeap((ArrayList<Integer>) list.clone());

        printInsertionSort((ArrayList<Integer>) list.clone());

        printSelectionSort((ArrayList<Integer>) list.clone());

        printQuickSort((ArrayList<Integer>) list.clone());

        printMergeSort((ArrayList<Integer>) list.clone());
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

    protected static void printBinaryTree(ArrayList<Integer> list) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();

        for (int i = 0; i < list.size(); i++) {
            binaryTree.add(list.get(i));
        }

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

    protected static void printHeap(ArrayList<Integer> list) {
        Heap<Integer> heap = new Heap<>(list);

        int start = 0;

        while (start < heap.heapSize) {
            if (heap.getParentByIndex(start) != null) {
                System.out.print("Heap parent for " + heap.vector.get(start) + " : " + heap.getParentByIndex(start) + "\n");
            }

            if (heap.getLeftByIndex(start) != null) {
                System.out.print("Heap left for " + heap.vector.get(start) + " : " + heap.getLeftByIndex(start) + "\n");
            }

            if (heap.getRightByIndex(start) != null) {
                System.out.print("Heap right for " + heap.vector.get(start) + " : " + heap.getRightByIndex(start) + "\n");
            }

            start++;
        }

        System.out.print("Heap max 1: " + heap.extractMax() + "\n");
        System.out.print("Heap max 2: " + heap.extractMax() + "\n");

        heap.insert(11);
        heap.insert(9);
        heap.insert(12);

        System.out.print("Heap max 3: " + heap.extractMax() + "\n");
    }

    protected static void printInsertionSort(ArrayList<Integer> list) {
        System.out.print("insertionSort before:\n");

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

    protected static void printSelectionSort(ArrayList<Integer> list) {
        System.out.print("selectionSort before:\n");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "\n");
        }

        SelectionSort selectionSort = new SelectionSort();
        ArrayList<Integer> sortedList = selectionSort.sort(list);

        System.out.print("selectionSort after sort:\n");

        for (int i = 0; i < sortedList.size(); i++) {
            System.out.print(sortedList.get(i) + "\n");
        }
    }

    protected static void printQuickSort(ArrayList<Integer> list) {
        System.out.print("quickSort before:\n");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "\n");
        }

        QuickSort quickSort = new QuickSort<>(list);
        ArrayList<Integer> sortedList = quickSort.sort();

        System.out.print("quickSort after sort:\n");

        for (int i = 0; i < sortedList.size(); i++) {
            System.out.print(sortedList.get(i) + "\n");
        }
    }

    protected static void printMergeSort(ArrayList<Integer> list) {
        System.out.print("mergeSort before:\n");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "\n");
        }

        MergeSort mergeSort = new MergeSort<>(list);
        ArrayList<Integer> sortedList = mergeSort.sort();

        System.out.print("mergeSort after sort:\n");

        for (int i = 0; i < sortedList.size(); i++) {
            System.out.print(sortedList.get(i) + "\n");
        }
    }
}
