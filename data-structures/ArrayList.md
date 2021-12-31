# ArrayList

An expandable array. `Array`s are fixed length while `ArrayList`'s capacity can grow as elements are added. The list is instantiated with an initial capacity and once the capacity is reached, a new array is created with a larger capacity and all existing elements from the previous array are copied over to the new array.

## Operations

- **T get(i)** - returns the element `T` at given index `i`.
- **boolean add(T)/add(i, T)** - add element `T` at the end of list or at the specified index `i`. Always returns `true`.
- **boolean remove(T)/remove(i)** - Search list and remove the element `T`.
- **set(i, T)** - Replace the element at index `i`.

## Time Complexity

- **get** - O(1) The element is accessed by the index
- **add** - O(n) Adding element causes all elements to shift
- **remove** - O(n) Removing element causes all element to shift
- **set** - O(1) - The element is replaced at index

## Implementation

``` java
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
```