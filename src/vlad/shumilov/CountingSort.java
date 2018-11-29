package vlad.shumilov;

import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Function;

public class CountingSort<T extends Number> {
    public ArrayList<T> list;

    public CountingSort(ArrayList<T> list) {
        this.list = list;
    }

    public Vector<T> sort(int maxValue, Function<T, Integer> funToInteger) {
        checking(list);

        return sort(0, list.size() - 1, maxValue, funToInteger);
    }

    protected Vector<T> sort(int start, int end, int maxValue, Function<T, Integer> funToInteger) {
        int n = end - start;
        Vector<Integer> dictionary = new Vector<>();

        for (int i = 0; i <= maxValue; i++) {
            dictionary.add(i, 0);
        }

        for (int j = start; j <= end; j++) {
            Integer i = funToInteger.apply(list.get(j));

            Integer value = dictionary.get(i);

            dictionary.set(i, value + 1);
        }

        for (int i = 1; i <= maxValue; i++) {
            dictionary.set(i, dictionary.get(i) + dictionary.get(i-1));
        }

        Vector<T> sortedList = new Vector<>();
        sortedList.setSize(list.size());

        for (int j = end; j >= start; j--) {
            int i = funToInteger.apply(list.get(j));
            sortedList.set(dictionary.get(i) - 1, list.get(j));

            if (dictionary.get(i) > 0) {
                dictionary.set(i, dictionary.get(i)-1);
            }
        }

        return sortedList;
    }

    protected void checking(ArrayList<T> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("list is empty in CountingSort");
        }
    }
}
