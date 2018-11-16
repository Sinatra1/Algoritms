package vlad.shumilov;

import java.util.ArrayList;

public class HeapSort<T extends Comparable<T>> extends InsertionSort<T> {
    public ArrayList<T> list;

    public HeapSort(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> sort() {
        checking(list);

        return sort(0, list.size() - 1);
    }

    protected ArrayList<T> sort(int first, int last) {
        checking(list);

        int size = last - first;
        Integer index;

        if (size <= 1) {
            return list;
        }

        createHeap(first, last);

        for (index = size - 1; index > 0; index--) {

        }

        return list;
    }

    public ArrayList<T> createHeap(int first, int last) {
        checking(list);

        int size = last - first;
        Integer index;

        if (size <= 1) {
            return list;
        }

        for (index = size/2 - 1; index >= 0; index--) {
            //TODO: heapify(first, last, index);
        }

        return list;
    }

    protected void heapify(int first, int last, int index) {
        int size = last - first;
        int left = 2*index + 1;
        int right = 2*index + 2;
        int largest = index;
    }

    protected void checking(ArrayList<T> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("list is empty in HeapSort");
        }
    }
}
