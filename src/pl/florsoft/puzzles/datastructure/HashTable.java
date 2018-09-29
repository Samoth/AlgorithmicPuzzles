package pl.florsoft.puzzles.datastructure;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K extends Comparable<K>, V> {

    private static final int DEFAULT_CAPACITY = 16;

    public List<Entry<K, V>>[] values;
    private int size = 0;
    private int capacity;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        values = new List[capacity];
    }

    public void put(K key, V value) {
        int idx = hash(key);
        Entry<K, V> entry = new Entry<>(key, value);
        if (values[idx] == null) {
            List<Entry<K, V>> list = new ArrayList<>();
            list.add(entry);
            values[idx] = list;
        } else {
            List<Entry<K, V>> list = values[idx];
            int oldIdx = list.indexOf(entry);
            if (oldIdx != -1) {
                list.get(oldIdx).value = value;
            } else {
                list.add(entry);
            }
        }
        size++;
    }

    public V get(K key) {
        List<Entry<K, V>> valueList = values[hash(key)];
        if (valueList == null) {
            return null;
        }
        Entry<K, V> valueEntry = findForValue(key, valueList);
        return valueEntry != null ? valueEntry.value : null;
    }

    public boolean containsKey(K key) {
        List<Entry<K, V>> valueList = values[hash(key)];
        return valueList != null && findForValue(key, valueList) != null;
    }

    public V remove(K key) {
        int idx = hash(key);
        List<Entry<K, V>> valueList = values[idx];
        if (valueList == null) {
            return null;
        }
        int valueIdx = findForValueIdx(key, valueList);
        if (valueIdx == -1) {
            return null;
        }
        Entry<K, V> entry = valueList.remove(valueIdx);
        if (valueList.isEmpty()) {
            values[idx] = null;
        }
        size--;
        return entry.value;
    }

    public void clear() {
        for (int i = 0; i < values.length; i++) {
            values[i] = null;
        }
        size = 0;
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
        boolean isFirst = true;
        for (List<Entry<K, V>> valueList : values) {
            if (valueList != null) {
                for (Entry<K, V> entry : valueList) {
                    if (isFirst) {
                        isFirst = !isFirst;
                    } else {
                        sb.append(", ");
                    }
                    sb.append("(").append(entry.key).append(" -> ").append(entry.value).append(")");

                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private int hash(Object obj) {
        return obj == null ? 0 : Math.abs(obj.hashCode()) % capacity;
    }

    private Entry<K, V> findForValue(K key, List<Entry<K, V>> list) {
        for (Entry<K, V> entry : list) {
            if (entry.key == key) {
                return entry;
            }
        }
        return null;
    }

    private int findForValueIdx(K key, List<Entry<K, V>> list) {
        for (int i = 0; i < list.size(); i++) {
            Entry<K, V> entry = list.get(i);
            if (entry.key == key) {
                return i;
            }
        }
        return -1;
    }

    static class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {

        public K key;
        public V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Entry<K, V> o) {
            if (key == null && o.key == null) {
                return 0;
            } else if (key == null) {
                return -1;
            } else if (o.key == null) {
                return 1;
            }
            return key.compareTo(o.key);
        }

    }

}
