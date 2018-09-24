package pl.florsoft.puzzles.datastructure;

import java.util.NoSuchElementException;

public class LinkedList<T> {

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(T val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void add(T val) {
        Node newNode = new Node(val);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.value;
    }

    public T get(int idx) {
        if (idx >= size || idx < 0) {
            throw new NoSuchElementException();
        }
        Node node = head;
        for (int i = 0; i < idx; i++) {
            node = node.next;
        }
        return node.value;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T node = getFirst();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return node;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T node = getLast();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        return node;
    }

    public T remove(T val) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node elem = head;
        while (elem != null && (elem.value == null && val != null || elem.value != null && !elem.value.equals(val))) {
            elem = elem.next;
        }
        if (elem == null) {
            throw new NoSuchElementException();
        }
        if (elem.prev != null) {
            elem.prev.next = elem.next;
        } else {
            head = elem.next;
        }
        if (elem.next != null) {
            elem.next.prev = elem.prev;
        } else {
            tail = elem.prev;
        }
        return elem.value;
    }

    public T remove(int idx) {
        if (idx >= size || idx < 0) {
            throw new NoSuchElementException();
        }
        Node elem = head;
        for (int i = 0; i < idx; i++) {
            elem = elem.next;
        }
        if (elem.prev != null) {
            elem.prev.next = elem.next;
        } else {
            head = elem.next;
        }
        if (elem.next != null) {
            elem.next.prev = elem.prev;
        } else {
            tail = elem.prev;
        }
        return elem.value;
    }

    public boolean contains(T val) {
        if (isEmpty()) {
            return false;
        }
        Node elem = head;
        int cnt = 0;
        while (elem != null && (elem.value == null && val != null || elem.value != null && !elem.value.equals(val))) {
            elem = elem.next;
            cnt++;
        }
        return elem != null && cnt < size;
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
        Node elem = head;
        boolean isFirst = true;
        while (elem != null) {
            if (!isFirst) {
                sb.append(", ");
            } else {
                isFirst = !isFirst;
            }
            sb.append(elem.value);
            elem = elem.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    class Node {
        public T value;
        public Node prev;
        public Node next;

        public Node(T val) {
            this.value = val;
        }
    }

}
