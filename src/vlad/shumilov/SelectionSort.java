package vlad.shumilov;

import java.util.ArrayList;

public class SelectionSort<T extends Comparable<T>> extends InsertionSort<T> {

    @Override
    public ArrayList<T> sort(ArrayList<T> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("list is empty in SelectionSort");
        }

        for (int i = 0; i < list.size(); i++) {
            T min = list.get(i);
            int minIndex = i;

            for (int j = i; j < list.size(); j++) {
                if (list.get(j).compareTo(min) < 0) {
                    min = list.get(j);
                    minIndex = j;
                }
            }

            swap(list, minIndex, i);
        }

        return list;
    }
}
