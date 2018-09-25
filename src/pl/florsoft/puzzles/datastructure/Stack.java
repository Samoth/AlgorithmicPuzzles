package pl.florsoft.puzzles.datastructure;

import java.util.EmptyStackException;

public class Stack<T> {

    private int size = 0;
    private int maxSize;
    private T[] values;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.values = (T[]) new Object[maxSize];
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T elem = values[size - 1];
        size--;
        return elem;
    }

    public void push(T val) {
        if (size + 1 > maxSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        values[size] = val;
        size++;
    }

    public T peak() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return values[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(values[i]);
        }
        sb.append(" ]");
        return sb.toString();
    }

}
