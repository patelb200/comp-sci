# Hash Table

A *set* of elements in which an element can be effciently looked up with a key. A hash table implementation consists of a hash function and a data structure that is used to store the element such as an array where each index is an address in which an element is placed. The hash function determines which address an element is to be stored. There is a chance when inserting an element there will be a collision if the hash function outputs the same address value. Depending on the implementation of the hash table, there are 2 common solutions to dealing with collisions. They are *closed addressing* and *open addressing*.

## Hash Function
A function where a key is the input and the address is the output. The address is typically an index of the array. The hash function implementation should be deterministic, which means that a key will always output the same value. The output of the function should not be a number larger than the total number of slots.

`address = key mod slots`

## Collisions
Its possible that two unique keys could resolve to the same address. When this happens, its called a collision. Depending on the implementation, a collision is dealt with differently.

1. **Closed addressing** - each array index is a linked list. Each collision adds on to the linked list. Searching/Removing is done the same way as adding an element. Scan the address linked list and match on the key.
2. **Open addressing** - when a collision occurs, the next open index in the array is used to store the element. Searching is done the same way as adding an element where when a hash match occurs the key is compared until a key match occurs. When a key match occurs then for insert, the value is replaced for the existing key and for searching the value is returned. For removing elements, you cannot remove it when the hash and key match is found because there would be a gap for finding key matches during get, add, remove operations. Instead a designated symbol has to be used in place of the element that is to be removed.

## Load Factor
The ratio of filled slots to total slots in the array. The lower the load factor the lower chance of a collision.

`LF = number of keys / number of total slots` 

When the load factor gets above a certain threshold, the array needs to be resized. The rule of thumb is to resize when the load factor ratio (threshold) is greater than 0.7 and the array should be doubled in size. Then elements need to be reassigned its address by running the key through the hash function.

## Operations

- **V serch(K)** - search an element **V** using key **K**.
- **insert(K, V)** - insert an element **V** with key **K**.
- **boolean delete(K)** - delete an element with key **K**.

## Time Complexity (average/worst)
- **search** - O(1) / O(n)
- **insert** - O(1) / O(n)
- **delete** - O(1) / O(n)


## Implementations

### Open Addressing - Linear probe

```java
public class HashTable<K,V> {

    private static class Entry<K,V> {
        private K key;
        private V value;

        public Entry(){}
        public Entry(K key, V value) { this.key = key; this.value = value; }
        public int hashCode() {
            return this.key.hashCode();
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key) && value.equals(entry.value);
        }
    }

    private static final Entry DELETED = new Entry<>();
    private Entry<K,V>[] entries;
    private int size;
    private static final float LOAD_FACTOR_THRESHOLD = 0.7f;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.entries = (Entry<K,V>[]) new Entry[capacity];
    }

    public void put(K key, V value) {
        if (loadFactor() >= LOAD_FACTOR_THRESHOLD) {
            reallocate();
        }

        int bucket = bucket(key);
        int counter = bucket;
        do {
            Entry<K, V> temp = this.entries[counter];

            if (temp == null || temp == DELETED) {
                this.entries[counter] = new Entry<>(key, value);
                size++;
                break;
            }

            if (temp.key.equals(key)) {
                temp.value = value;
                break;
            }

            counter++;
        } while ((counter %= this.entries.length) != bucket);

    }

    public V get(K key) {
        if (key == null)
            return null;

        int bucket = bucket(key);
        int counter = bucket;

        do {
            Entry<K, V> temp = this.entries[counter++];

            if (temp == null)
                break;

            if (temp == DELETED)
                continue;

            if (temp.key.equals(key))
                return temp.value;

        } while ((counter %= this.entries.length) != bucket);

        return null;
    }

    @SuppressWarnings("unchecked")
    public boolean remove(K key) {
        if (key == null)
            return false;

        int bucket = bucket(key);
        int counter = bucket;

        do {
            Entry<K, V> temp = this.entries[counter++];

            if (temp == null)
                break;

            if (temp == DELETED)
                continue;

            if (temp.key.equals(key)) {
                this.entries[counter - 1] = DELETED;
                size--;
                return true;
            }

        } while ((counter %= this.entries.length) != bucket);

        return false;
    }

    public boolean containsKey(K key) {
        if (key == null)
            return false;

        int bucket = bucket(key);
        int counter = bucket;

        do {

            Entry<K, V> temp = entries[counter++];
            if (temp == null)
                return false;

            if (temp == DELETED)
                continue;

            if (temp.key.equals(key))
                return true;

        } while ((counter %= this.entries.length) != bucket);

        return false;
    }

    public int size() {
        return this.size;
    }

    private int bucket(K key) {
        return Math.abs(key.hashCode()) % this.entries.length;
    }

    private float loadFactor() {
        return ((float) this.size()) / this.entries.length;
    }

    @SuppressWarnings("unchecked")
    private void reallocate() {
        Entry<K, V>[] oldBuckets = this.entries;
        this.entries = new Entry[this.entries.length * 2];
        this.size = 0;

        for (Entry<K, V> entry : oldBuckets) {
            if (entry != null && entry != DELETED) {
                this.put(entry.key, entry.value);
            }
        }
    }
}
```

### Closed Addressing

``` java
public class HashTable<K, V> {

    public static class Entry<K, V> {
        private K key;
        private V value;

        @SuppressWarnings("unchecked")
        public Entry(){ this.key = (K) new Object(); }
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public int hashCode() {
            return this.key.hashCode();
        }
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key) && value.equals(entry.value);
        }
    }

    private List<Entry<K,V>>[] entries;
    private int size;
    private static float LOAD_FACTOR_THRESHOLD = 0.7f;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.entries = (List<Entry<K, V>>[]) new LinkedList[capacity];
        Arrays.setAll(this.entries, (i) -> new LinkedList<Entry<K, V>>());
    }

    public void put(K key, V value) {
        if (loadFactor() >= LOAD_FACTOR_THRESHOLD) {
            reallocate();
        }

        int bucket = bucket(key);
        List<Entry<K, V>> chain = this.entries[bucket];

        int match = -1;
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key.equals(key)) {
                match = i;
                break;
            }
        }

        if (match != -1) {
            chain.get(match).value = value;
        } else {
            chain.add(new Entry<K, V>(key, value));
            size++;
        }

    }

    public V get(K key) {

        int bucket = bucket(key);
        List<Entry<K, V>> chain = this.entries[bucket];

        for (Entry<K, V> entry : chain) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean remove(K key) {

        int bucket = bucket(key);
        List<Entry<K, V>> chain = this.entries[bucket];

        int match = -1;
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key.equals(key)) {
                match = i;
                break;
            }
        }

        if (match != -1) {
            chain.remove(match);
            size--;
            return true;
        } else {
            return false;
        }

    }

    private int bucket(K key) {
        return Math.abs(key.hashCode()) % this.entries.length;
    }

    private float loadFactor() {
        return ((float) this.size()) / this.entries.length;
    }

    @SuppressWarnings("unchecked")
    private void reallocate() {
        List<Entry<K, V>>[] temp = this.entries;

        this.entries = (List<Entry<K, V>>[]) new LinkedList[temp.length * 2];
        Arrays.setAll(this.entries, (i) -> new LinkedList<Entry<K, V>>());
        this.size = 0;

        for (List<Entry<K, V>> chain : temp) {
            for (Entry<K, V> entry : chain)
                this.put(entry.key, entry.value);
        }
    }

    public int size() {
        return this.size;
    }
}
```