package vlad.shumilov;

import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Function;

public class CountingSort<T extends Number> {
    public ArrayList<T> list;

    public CountingSort(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> sort(Function<T, Integer> funToInteger, int maxValue) {
        checking(list);

        return sort(0, list.size() - 1, funToInteger, maxValue);
    }

    protected ArrayList<T> sort(int start, int end, Function<T, Integer> funToInteger, int maxValue) {
        int n = end - start;

        Vector<Integer> c = new Vector<>();
        c.setSize(maxValue + 1);

        for (int j = start; j < end; j++) {
            try {
                Integer index = funToInteger.apply(list.get(j));
                Integer value = c.get(index);
                c.set(index, value+1);
            } catch (Exception e) {

            }
        }


        return list;
    }

    protected void checking(ArrayList<T> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("list is empty in CountingSort");
        }
    }
}
