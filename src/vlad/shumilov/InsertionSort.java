package vlad.shumilov;

import java.util.ArrayList;

public class InsertionSort<T extends Comparable<T>> {

    public ArrayList<T> sort(ArrayList<T> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("list is empty in InsertionSort");
        }

        for (int i = 1; i < list.size(); i++) {
            int j = i - 1;
            int k = i;

            while (j > 0 && (list.get(k).compareTo(list.get(j)) < 0)) {
                swap(list, j, k);
                j--;
                k--;
            }

            if (list.get(k).compareTo(list.get(j)) < 0) {
                swap(list, j, k);
            }
        }

        return list;
    }

    protected void swap(ArrayList<T> list, Integer firstIndex, Integer secondIndex) {
        T fist = list.get(firstIndex);
        T second = list.get(secondIndex);
        list.set(firstIndex, second);
        list.set(secondIndex, fist);
    }
}
