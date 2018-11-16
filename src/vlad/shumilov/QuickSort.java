package vlad.shumilov;

import java.util.ArrayList;

public class QuickSort<T extends Comparable<T>> extends InsertionSort<T> {

    public ArrayList<T> list;

    public QuickSort(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> sort() {
        checking(list);

        return sort(0, list.size() - 1);
    }

    protected ArrayList<T> sort(int start, int end) {
        checking(list);

        int xIndex = end - ((end - start) / 2);
        T x = list.get(xIndex);

        int i = start;
        int j = end;

        while (j >= i) {
            while (list.get(i).compareTo(x) < 0) {
                i++;
            }

            while (x.compareTo(list.get(j)) < 0) {
                j--;
            }

            if (j >= i) {
                swap(list, i, j);
                i++;
                j--;
            }
        }

        if (j > start) {
            sort(start, j+1);
        }

        if (end > (i+1)) {
            sort(i, end);
        }

        return list;
    }

    protected void checking(ArrayList<T> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("list is empty in QuickSort");
        }
    }
}
