package vlad.shumilov;

import java.util.ArrayList;
import java.util.Vector;

public class Graph<T extends Comparable<T>> {
    public Vector<Node<T>> G = new Vector<>();

    protected class Node<T extends Comparable<T>> {
        T data;
        ArrayList<Integer> itemsIndexes = new ArrayList<>();

        Node(T data) {
            this.data = data;
        }
    }

    public Vector<Node<T>> fillGraphFromList(ArrayList<T> list) {
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("");
        }

        G.setSize(list.size());

        for (int i = 0; i < list.size()-1; i++) {
            for (int j = i+1; j < list.size(); j++) {
                if (isNearestItems(list.get(i), list.get(j))) {
                    if (G.get(i) == null) {
                        G.set(i, new Node<>(list.get(i)));
                    }

                    G.get(i).itemsIndexes.add(j);

                    if (G.get(j) == null) {
                        G.set(j, new Node<>(list.get(j)));
                    }

                    G.get(j).itemsIndexes.add(i);
                }
            }
        }

        return G;
    }

    public void breadthFirstSearch(int index) {

    }

    protected boolean isNearestItems(T itemA, T itemB) {
        int diff = 0;
        String strA = itemA.toString();
        String strB = itemB.toString();

        for (int i = 0; i < strA.length(); i++) {
            if (!strA.substring(i, i+1).equals(strB.substring(i, i+1)) && ++diff > 1) {
                return false;
            }
        }

        return true;
    }
}
