package vlad.shumilov;

import java.util.List;
import java.util.Vector;

public class Heap<T extends Number & Comparable<T>> {
    protected Integer heapSize = 0;
    protected Vector<T> vector;

    public void Heap(Integer b, Integer e) {
        heapSize = e - b;
        vector.setSize(heapSize);

        List<T> sublist = vector.subList(b, e);

        for (int i = 0; i < sublist.size(); i++) {
            vector.set(i, sublist.get(i));
        }

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

        T max = vector.get(0);
        vector.set(0, vector.get(--heapSize));

        heapify(0);

        return max;
    }

    public void heapify(Integer i) {
        Integer l = 0;//TODO: left(i);
        Integer r = 0;//TODO: right(i);
        Integer largest;

        if (l < heapSize && (vector.get(i).compareTo(vector.get(l)) < 0)) {
            largest = l;
        } else {
            largest = i;
        }

        if (r < heapSize && (vector.get(largest).compareTo(vector.get(r)) < 0)) {
            largest = r;
        }

        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    public void increase(Integer i, T value) {
        if (!(i >= 0 && i < heapSize && (vector.get(i).compareTo(value) <=0))) {
            throw new RuntimeException("Can not increase");
        }

        vector.set(i, value);

        while (i > 0 && (vector.get(getParent(i)).compareTo(vector.get(i)) <= 0)) {
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    public void insert(T value) {
        if (heapSize < vector.size()) {
            vector.set(heapSize++, value);
        } else {
            vector.set(vector.size() + 1, value);
            ++heapSize;
        }

        increase(heapSize-1, value);
    }

    protected Integer getParent(Integer i) {
        return i / 2 ;
    }

    protected void swap(Integer firstIndex, Integer secondIndex) {
        T fist = vector.get(firstIndex);
        T second = vector.get(secondIndex);
        vector.set(firstIndex, second);
        vector.set(secondIndex, fist);
    }
}
