# Queue

Dynamic sets that grow and shrink with a **first-in, first-out (FIFO)** policy.

## Operations

- **enqueue(T)** - insert an element **T** at the back of the queue.
- **T dequeue()** - remove an element **T** from the front of the queue.
- **T peek()** - view an element that is at the front of the queue.

> enqueue => [2][1][0] <= dequeue/peek

## Time Complexity

- **enqueue** - O(1)
- **dequeue** - O(1)
- **peek** - O(1)

## Implementation

### Array

```java
public class Queue<T> {

    private final T[] elements;
    private int head, tail, size = 0;

    public Queue(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    public void enqueue(T element) throws RuntimeException {
        if (!isFull()) {
            size++;
            // for wrap around
            if (tail > elements.length - 1)
                tail = 0;
            elements[tail++] = element;
        } else
            throw new RuntimeException("Overflow");
    }

    public T dequeue() throws RuntimeException {
        if (!isEmpty()) {
            size--;
            // for wrap around
            if (head > elements.length - 1)
                head = 0;
            return elements[head++];
        }
        throw new RuntimeException("Underflow");
    }

    public T peek() throws RuntimeException {
        if (!isEmpty())
            return elements[head];
        throw new RuntimeException("Underflow");
    }

    public boolean isFull() {
        return size() == elements.length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }
}
```

### Linked Nodes

```java
public class Queue<T> {

    private static class Node<T> {
        private T val;
        private Node<T> next;

        Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }
    }

    private T[] elements;
    private int size;
    private final int capacity;
    private Node<T> head;
    private Node<T> tail;

    public Queue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(T element) throws RuntimeException {

        if (isFull())
            throw new RuntimeException("Overflow");

        if (this.head == null) {
            this.head = new Node<>(element, null);
            this.tail = this.head;
        } else {
            this.tail.next = new Node<>(element, null);
            this.tail = this.tail.next;
        }

        size++;
    }

    public T dequeue() throws RuntimeException {

        if (isEmpty())
            throw new RuntimeException("Underflow");

        Node<T> poll = this.head;
        this.head = poll.next;
        size--;
        return poll.val;
    }

    public T peek() throws RuntimeException {

        if (isEmpty())
            throw new RuntimeException("Underflow");

        return this.head.val;
    }

    public boolean isFull() {
        return size() == this.capacity;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }
}
```