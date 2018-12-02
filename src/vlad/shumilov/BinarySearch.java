package vlad.shumilov;

import java.util.ArrayList;

public class BinarySearch<T extends Comparable<T>> {
    protected ArrayList<T> sortedList;

    public BinarySearch(ArrayList<T> sortedList) {
        this.sortedList = sortedList;
    }

    public Integer findIndex(T value) {
        if (sortedList.isEmpty()) {
            throw new RuntimeException("sortedList is empty in BinarySearch");
        }

        int l = 0;
        int r = sortedList.size() - 1;

        while (l <= r) {
            int i = l + (r - l) / 2;
            if (less(value, sortedList.get(i))) {
                r = --i;
            } else if (less(sortedList.get(i), value)) {
                l = ++i;
            } else {
                return i;
            }
        }

        return null;
    }

    protected Boolean less(T value1, T value2) {
        return (value1.compareTo(value2) < 0);
    }
}
