package pl.florsoft.puzzles.datastructure;

import java.util.NoSuchElementException;

public class Queue<E> {

    private QueueNode<E> first;
    private QueueNode<E> last;
    private int size;

    public void add(E val) {
        QueueNode<E> newNode = new QueueNode<>(val);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.nextElem = newNode;
            last = newNode;
        }
        size++;
    }

    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E elem = first.value;
        first = first.nextElem;
        size--;
        return elem;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        QueueNode elem = first;
        boolean isFirst = true;
        while (elem != null) {
            if (!isFirst) {
                sb.append(", ");
            } else {
                isFirst = !isFirst;
            }
            sb.append(elem.value);
            elem = elem.nextElem;
        }
        sb.append(" ]");
        return sb.toString();
    }

    private static class QueueNode<E> {
        E value;
        QueueNode<E> nextElem;

        public QueueNode(E value) {
            this.value = value;
        }
    }

}
