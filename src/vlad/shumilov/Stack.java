package vlad.shumilov;

import com.sun.istack.internal.NotNull;

public class Stack<T> {

    protected Integer top = 0;
    protected Integer n;
    protected T array[];

    public Stack(@NotNull Integer n) {
        this.n = n;

        array = (T[]) new Object[n];
    }

    public @NotNull Integer push(@NotNull T element) {
        if (isOveflow()) {
            throw new RuntimeException("stack is overflow");
        }

        array[top++] = element;

        return top;
    }

    public @NotNull T pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }

        return array[--top];
    }

    protected @NotNull Boolean isOveflow() {
        if (top >= n) {
            return true;
        }

        return false;
    }

    protected @NotNull Boolean isEmpty() {
        if (top == 0) {
            return true;
        }

        return false;
    }
}
