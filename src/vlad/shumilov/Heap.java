package vlad.shumilov;

import java.util.ArrayList;
import java.util.Vector;

public class Heap<T extends Number & Comparable<T>> {
    public Integer heapSize = 0;
    public Vector<T> vector = new Vector<>();

    public Heap(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            insert(list.get(i));
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
        Integer left = getLeftIndex(i);
        Integer right = getRightIndex(i);
        Integer largest;

        if (left < heapSize && (vector.get(i).compareTo(vector.get(left)) < 0)) {
            largest = left;
        } else {
            largest = i;
        }

        if (right < heapSize && (vector.get(largest).compareTo(vector.get(right)) < 0)) {
            largest = right;
        }

        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    public void insert(T value) {
        if (heapSize < vector.size()) {
            vector.set(heapSize++, value);
        } else {
            vector.add(value);
            ++heapSize;
        }

        increase(heapSize-1, value);
    }

    protected void increase(Integer i, T value) {
        if (!(i >= 0 && i < heapSize && (vector.get(i).compareTo(value) <=0))) {
            throw new RuntimeException("Can not increase");
        }

        vector.set(i, value);

        while (i > 0 && (vector.get(getParentIndex(i)).compareTo(vector.get(i)) <= 0)) {
            swap(i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }

    public T getParentByIndex(Integer i) {
        int parentIndex = getParentIndex(i);

        if (parentIndex >= heapSize) {
            return null;
        }

        return vector.get(parentIndex);
    }

    public T getLeftByIndex(Integer i) {
        int leftIndex = getLeftIndex(i);

        if (leftIndex >= heapSize) {
            return null;
        }

        return vector.get(leftIndex);
    }

    public T getRightByIndex(Integer i) {
        int rightIndex = getRightIndex(i);

        if (rightIndex >= heapSize) {
            return null;
        }

        return vector.get(rightIndex);
    }

    protected Integer getParentIndex(Integer i) {
        return i / 2;
    }

    protected Integer getLeftIndex(Integer i) {
        return 2*i + 1;
    }
    protected Integer getRightIndex(Integer i) {
        return 2*i + 2;
    }

    protected void swap(Integer firstIndex, Integer secondIndex) {
        T fist = vector.get(firstIndex);
        T second = vector.get(secondIndex);
        vector.set(firstIndex, second);
        vector.set(secondIndex, fist);
    }
}
