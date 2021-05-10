# Stack

Dynamic sets that grow and shrink with a **last-in, first-out (LIFO)** policy.

## Operations

- **push(T)** - insert an element **T** at the top of the stack.
- **T pop()** - remove an element **T** from the top of the stack.
- **T peek()** - view an element that is at the top of the stack.

> [2] \|/ push /|\ pop
>
> [1]
>
> [0]

## Time Complexity

- **push** - O(1)
- **pop** - O(1)
- **peek** - O(1)

## Implementation

### Array

```java
public class Stack<T> {

    private final T[] elements;
    private int pointer = -1;

    public Stack(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    public void push(T element) throws RuntimeException {
        if (pointer < elements.length - 1)
            elements[++pointer] = element;
        else
            throw new RuntimeException("Overflow");
    }

    public T pop() throws RuntimeException {
        if (!isEmpty()) {
            return elements[pointer--];
        }
        throw new RuntimeException("Underflow");
    }

    public T peek() throws RuntimeException {
        if (!isEmpty()) {
            return elements[pointer];
        }
        throw new RuntimeException("Underflow");
    }

    public boolean isEmpty() {
        return pointer < 0;
    }

    public int size() {
        if (isEmpty())
            return 0;
        else
            return pointer + 1;
    }
}
```

### Linked Nodes

```java
public class Stack<T> {

    class Node<T> {
        T val;
        Node<T> next;

        Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }
    }

    private Node<T> top;
    private int size;

    public void push(T element) {
        if (top == null)
            top = new Node<>(element, null);
        else {
            top = new Node<>(element, top);
        }
        size++;
    }

    public T pop() throws RuntimeException {
        if (top != null) {
            size--;
            Node<T> temp = top;
            top = top.next;
            return temp.val;
        } else {
            throw new RuntimeException("Underflow");
        }
    }

    public T peek() throws RuntimeException {
        if (top != null) {
            return top.val;
        } else {
            throw new RuntimeException("Underflow");
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}
```