package vlad.shumilov;

import java.util.ArrayList;

public class InterpolationSearch<T extends Number & Comparable<T>> extends BinarySearch<T> {

    public InterpolationSearch(ArrayList<T> sortedList) {
        super(sortedList);
    }

    @Override
    protected int getDelimiterIndex(int l, int r) {
        return (value.intValue() - sortedList.get(l).intValue()) * (r - l) / (sortedList.get(r).intValue() - sortedList.get(l).intValue()) + l;
    }
}
