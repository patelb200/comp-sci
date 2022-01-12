public class StackArray<T> {

    private final T[] elements;
    private int head, size = 0;

    @SuppressWarnings("unchecked")
    public StackArray(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public T peek() {
        if (this.isEmpty())
            throw new RuntimeException("Stack underflow");

        return this.elements[this.head - 1];
    }

    public void push(T element) {
        if (this.isFull())
            throw new RuntimeException("Stack overflow");

        this.size++;
        this.elements[this.head++] = element;
    }

    public T pop() {
        if (this.isEmpty())
            throw new RuntimeException("Stack underflow");

        this.size--;
        return this.elements[--this.head];
    }

    public boolean isFull() {
        return this.elements.length == this.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.size;
    }

}

StackArray<Integer> sa = new StackArray<Integer>(4);

sa.push(1);
sa.push(2);
System.out.println(sa.peek());
System.out.println(sa.pop());
System.out.println(sa.pop());
System.out.println(sa.pop());
sa.push(3);
sa.push(4);
sa.push(5);
System.out.println(sa.pop());
System.out.println(sa.pop());
System.out.println(sa.pop());
System.out.println(sa.pop());
System.out.println(sa.size());
System.out.println(sa.isEmpty());
sa.push(1);
sa.push(2);
sa.push(3);
sa.push(4);
System.out.println(sa.peek());
System.out.println(sa.isFull());
sa.push(5);
while(!sa.isEmpty())
    System.out.print(sa.pop() + "->");

/exit