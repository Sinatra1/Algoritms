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
            //TODO: heapify(j);
        }
    }

    public T extractMax() {
        if (!(heapSize > 0)) {
            throw new RuntimeException("heap is empty");
        }

        T max = vector.get(0);
        vector.set(0, vector.get(--heapSize));

        //TODO: heapify(0);

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
            T fist = vector.get(i);
            T second = vector.get(largest);
            vector.set(i, second);
            vector.set(largest, fist);
            //TODO: heapify(largest);
        }
    }
}
