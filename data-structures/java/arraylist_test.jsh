public class AList<T> {

    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public AList(int initialCapacity) {
        this.elements = (T[]) new Object[initialCapacity];
    }

    public AList() {
        this(10);
    }

    public T get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return this.elements[index];
    }

    public boolean add(int index, T element) {
        if (index < 0 || index > this.size())
            throw new ArrayIndexOutOfBoundsException(index);

        Objects.requireNonNull(element);

        if (this.size() == this.elements.length)
            this.reallocate();

        for (int i = this.size(); i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }

        this.elements[index] = element;
        size++;

        return true;
    }

    public boolean add(T element) {
        return this.add(this.size(), element);
    }

    public boolean remove(T element) {

        Objects.requireNonNull(element);

        int match = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.elements[i].equals(element)) {
                match = i;
                break;
            }
        }

        if (match == -1)
            return false;

        return this.remove(match);
    }

    public boolean remove(int index) {
        if (index < 0 || index >= this.size())
            throw new ArrayIndexOutOfBoundsException(index);

        for (int i = index; i < this.size() - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        size--;
        return true;
    }

    public void set(int index, T element) {
        if (index < 0 || index >= this.size())
            throw new ArrayIndexOutOfBoundsException(index);

        Objects.requireNonNull(element);

        this.elements[index] = element;
    }

    @SuppressWarnings("unchecked")
    private void reallocate() {
        T[] temp = this.elements;
        this.elements = (T[]) new Object[temp.length * 2];

        System.arraycopy(temp, 0, this.elements, 0, this.size());
    }

    public int size() {
        return this.size;
    }
}

AList<Integer> al = new AList<Integer>(1);

al.add(0, 2);
al.add(1);
al.add(9);
System.out.println(al.get(2));
al.add(2, 11);
System.out.println(al.get(2));
al.remove(2);
System.out.println(al.get(2));

al.add(null);
al.add(5);
al.remove(5);
for(int i = 0; i < al.size(); i++)
    System.out.print(al.get(i) + " ");

System.out.println("\nremove object 5 " + al.remove(Integer.valueOf(5)));

for(int i = 0; i < al.size(); i++)
    System.out.print(al.get(i) + " ");

System.out.println("\nset object 10 at 2");
al.set(2,10);

for(int i = 0; i < al.size(); i++)
    System.out.print(al.get(i) + " ");
System.out.println();

al.set(3, 13);
System.out.println("size " + al.size());

/exit