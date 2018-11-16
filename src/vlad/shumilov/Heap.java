package vlad.shumilov;

import java.util.ArrayList;

public class Heap<T extends Number & Comparable<T>> {
    protected Integer heapSize = 0;
    protected ArrayList<T> list;

    public Heap(ArrayList<T> list) {
        heapSize = list.size();
        this.list = list;

        int j = heapSize / 2;

        while (j >= 1) {
            j--;
            heapify(j);
        }
    }

    public T extractMax() {
        if (!(heapSize > 0)) {
            throw new RuntimeException("heap is empty");
        }

        T max = list.get(0);
        list.set(0, list.get(--heapSize));

        heapify(0);

        return max;
    }

    public void heapify(Integer i) {
        Integer l = 2*i + 1;
        Integer r = 2*i + 2;
        Integer largest;

        if (l < heapSize && (list.get(i).compareTo(list.get(l)) < 0)) {
            largest = l;
        } else {
            largest = i;
        }

        if (r < heapSize && (list.get(largest).compareTo(list.get(r)) < 0)) {
            largest = r;
        }

        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    public void insert(T value) {
        if (heapSize < list.size()) {
            list.set(heapSize++, value);
        } else {
            list.set(list.size() + 1, value);
            ++heapSize;
        }

        increase(heapSize-1, value);
    }

    protected void increase(Integer i, T value) {
        if (!(i >= 0 && i < heapSize && (list.get(i).compareTo(value) <=0))) {
            throw new RuntimeException("Can not increase");
        }

        list.set(i, value);

        while (i > 0 && (list.get(getParent(i)).compareTo(list.get(i)) <= 0)) {
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    protected Integer getParent(Integer i) {
        return i / 2 ;
    }

    protected void swap(Integer firstIndex, Integer secondIndex) {
        T fist = list.get(firstIndex);
        T second = list.get(secondIndex);
        list.set(firstIndex, second);
        list.set(secondIndex, fist);
    }
}
