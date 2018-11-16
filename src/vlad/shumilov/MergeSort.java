package vlad.shumilov;

import java.util.ArrayList;

public class MergeSort<T extends  Comparable<T>> {
    public ArrayList<T> list;

    public MergeSort(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> sort() {
        checking(list);

        return sort(0, list.size() - 1);
    }

    protected ArrayList<T> sort(int start, int end) {
        checking(list);

        Integer delimiter = (end + start) / 2;

        if (start < delimiter) {
            sort(start, delimiter);
        }

        if ((delimiter + 1) < end) {
            sort(delimiter + 1, end);
        }

        merge(start, delimiter, end);

        return list;
    }

    protected void merge(int start, int delimiter, int end) {
        int startFirst = start;
        int startSecond = delimiter + 1;
        int endFirst = delimiter;
        int endSecond = end;

        ArrayList<T> subList = new ArrayList<>();

        while (startFirst <= endFirst && startSecond <= endSecond) {
            if (list.get(startFirst).compareTo(list.get(startSecond)) < 0) {
                subList.add(list.get(startFirst));
                startFirst++;
            } else {
                subList.add(list.get(startSecond));
                startSecond++;
            }
        }

        if (startFirst > endFirst) {
            subList.addAll(list.subList(startSecond, endSecond+1));
        } else {
            subList.addAll(list.subList(startFirst, endFirst+1));
        }

        for (int i = start; i <= end; i++) {
            list.set(i, subList.get(i - start));
        }
    }

    protected void checking(ArrayList<T> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("list is empty in MergeSort");
        }
    }
}
