package vlad.shumilov;

import com.sun.istack.internal.NotNull;

public class Queue<T> {

    protected @NotNull Integer startIndex = 0;
    protected @NotNull Integer head = startIndex;
    protected @NotNull Integer tail = startIndex;
    protected @NotNull Integer n;
    protected @NotNull T[] array;

    public Queue(Integer n) {
        this.n = n;
        array = (T[]) new Object[n];
    }

    public void enqueue(T element) {
        if (isOverflow()) {
            throw new RuntimeException("queue is overflow");
        }

        array[tail] = element;

        if ((++tail) == n) {
            tail = startIndex;
        }
    }

    public @NotNull T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("queue is empty");
        }

        Integer oldHead = head;

        if ((++head) == n) {
            head = startIndex;
        }

        return array[oldHead];
    }

    protected @NotNull Boolean isOverflow() {
        if ((head % n != (tail + 1) % n) && array[startIndex] != null) {
            return true;
        }

        return false;
    }

    protected @NotNull Boolean isEmpty() {
        return head == startIndex && tail == startIndex && array[startIndex] == null;
    }
}
