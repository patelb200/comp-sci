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
System.out.println(t.get(100));
System.out.println("size " + t.size());
/exit