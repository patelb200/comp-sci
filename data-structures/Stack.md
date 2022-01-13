# Stack

Dynamic sets that grow and shrink with a **last-in, first-out (LIFO)** policy.

## Operations

- **push(T)** - insert an element **T** at the top of the stack.
- **T pop()** - remove an element **T** from the top of the stack.
- **T peek()** - view an element that is at the top of the stack.

> \\|/ push&nbsp;&nbsp;[2]&nbsp;&nbsp;&nbsp;/|\ pop  
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[1]  
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[0]

## Time Complexity

- **push** - O(1)
- **pop** - O(1)
- **peek** - O(1)

## Implementation

### Array

```java
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

        T temp = this.elements[--this.head];
        this.elements[this.head] = null; // regain space
        return temp;
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
```

### Linked Nodes

```java
public class StackLinkedNodes<T> {

    private static class Node<T> {
        Node<T> next;
        T data;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> head;
    private int size;

    public T peek() {
        if (this.isEmpty())
            throw new RuntimeException("Stack underflow");

        return this.head.data;
    }

    public void push(T element) {
        if (this.isEmpty()) {
            this.head = new Node<T>(element, null);
        } else {
            Node<T> temp = this.head;
            this.head = new Node<T>(element, temp);
        }
        this.size++;
    }

    public T pop() {
        if (this.isEmpty())
            throw new RuntimeException("Stack underflow");

        Node<T> temp = this.head;
        this.head = temp.next;
        temp.next = null;
        this.size--;
        return temp.data;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.size;
    }

    public Node<T> getNode() {
        return this.head;
    }
}
```