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


HashTable<Integer,Integer> t = new HashTable<>(10);

t.put(1, 9);
t.put(11, 11);
System.out.println(t.get(1));
System.out.println(t.remove(1));
System.out.println(t.get(1));
System.out.println("size " + t.size());

for(int i = 0; i < 40; i++) {
    t.put(i, i);
}

System.out.println(t.get(1));
System.out.println("size " + t.size());
/exit