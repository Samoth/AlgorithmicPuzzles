package pl.florsoft.puzzles.datastructure.list;

import java.util.NoSuchElementException;

public class LinkedList<E> {

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(E val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void add(E val) {
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

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.value;
    }

    public E get(int idx) {
        if (idx >= size || idx < 0) {
            throw new NoSuchElementException();
        }
        Node node = head;
        for (int i = 0; i < idx; i++) {
            node = node.next;
        }
        return node.value;
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E node = getFirst();
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

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E node = getLast();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        return node;
    }

    public E remove(E val) {
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

    public E remove(int idx) {
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

    public boolean contains(E val) {
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
        public E value;
        public Node prev;
        public Node next;

        public Node(E val) {
            this.value = val;
        }
    }

}
