package pl.florsoft.puzzles.datastructure;

import java.util.EmptyStackException;

public class Stack<E> {

    private int size = 0;
    private int maxSize;
    private Object[] values;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.values = new Object[maxSize];
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E elem = (E) values[size - 1];
        size--;
        return elem;
    }

    public void push(E val) {
        if (size + 1 > maxSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        values[size] = val;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) values[size - 1];
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
