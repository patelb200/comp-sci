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

    public Stack(int size) {
        elements = (T[])new Object[size];
    }

    public void push(T element) throws RuntimeException {
        if(pointer < elements.length - 1)
            elements[++pointer] = element;
        else
            throw new RuntimeException("Overflow");
    }

    public T pop() throws RuntimeException {
        if(!isEmpty()) {
            return elements[pointer--];
        }
        throw new RuntimeException("Underflow");
    }

    public T peek() throws RuntimeException {
        if(!isEmpty()) {
            return elements[pointer];
        }
        throw new RuntimeException("Underflow");
    }

    public boolean isEmpty() {
        return pointer < 0;
    }

    public int size() {
        if(isEmpty())
            return 0;
        else
            return pointer + 1;
    }
}
```