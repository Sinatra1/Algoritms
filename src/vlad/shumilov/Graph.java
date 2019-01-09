package vlad.shumilov;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Vector;

public class Graph<T extends Comparable<T>> {
    public Vector<Node<T>> G = new Vector<>();

    protected static final int WHITE = 0;
    protected static final int GRAY = 1;
    protected static final int BLACK = 2;

    protected class Node<T extends Comparable<T>> {
        T data;
        int color = WHITE;
        int distance = 0;
        Integer prevIndex = null;

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
        if (G.isEmpty() || index >= G.size()) {
            throw new RuntimeException("index is out graph size");
        }

        G.get(index).color = GRAY;
        G.get(index).distance = 0;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(index);

        while (!queue.isEmpty()) {
            int u = queue.pollFirst();

            for (int j = 0; j < G.get(u).itemsIndexes.size(); j++) {
                int v = G.get(u).itemsIndexes.get(j);

                if (G.get(v).color == WHITE) {
                    G.get(v).color = GRAY;
                    G.get(v).distance = G.get(u).distance + 1;
                    G.get(v).prevIndex = u;
                    queue.addLast(v);
                }
            }

            G.get(u).color = BLACK;
        }
    }

    public void printPath(int indexS, int indexV) {
        if (indexS == indexV) {
            System.out.print(G.get(indexS).data + "\n");
            return;
        }

        if (G.get(indexV).prevIndex == null) {
            System.out.print("Пути от " + G.get(indexS).data + " к " + G.get(indexV).data + " нет");
            return;
        }

        printPath(indexS, G.get(indexV).prevIndex);
        System.out.print(G.get(indexV).data + "\n");
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
