package pl.florsoft.puzzles.datastructure;

public class Heap<E> {

    private int size = 0;

    public void insert(E e) {

        size++;
    }

    public E extractMin() {
        size--;
        return null;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size() == 0;
    }
}
