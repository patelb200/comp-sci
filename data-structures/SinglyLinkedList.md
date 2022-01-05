# Singly Linked List

The singly `LinkedList` consists of nodes that have a pointer to the next node in the list and the data value. Instantiating the `LinkedList` does not require memory to be allocated ahead of time such as in an `Array` and the size can grow and shrink dynamically. It has the ability to add and remove an element anywhere in the list without having to shift elements as you would in an `ArrayList`, which is great for when you have to do frequent inserts and removals. 

## Operations

- **addFirst** - *add* a node to the `head` of the list
- **addLast** - *add* a node to the `tail` of the list
- **removeFirst** - *remove* a node from the `head` of the list
- **removeLast** - *remove* a node from the `tail` of the list
- **remove(T)** - *search* and *remove* node from the list
- **T getFirst** - *return* the value from the `head` of the list
- **T getLast** - *return* the value from the  `tail` of the list
- **T get(i)** - *traverse* the list to the given index and return the value


## Time Complexity

- **addFirst** - O(1)
- **addLast** - O(1)
- **removeFirst** - O(1)
- **removeLast** - O(n)
- **remove(T)** - O(n)
- **getFirst** - O(1)
- **getLast** - O(1)
- **get(i)** - O(n)

## Implementation

### Linked Nodes

```java
public class SinglyLinkedList<T> {

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> head, tail;
    private int size;

    public void addFirst(T element) {
        Objects.requireNonNull(element);

        if (head == null) {
            this.head = new Node<T>(element);
            this.tail = this.head;
        } else {
            Node<T> temp = this.head;
            this.head = new Node<T>(element, temp);
        }

        this.size++;
    }

    public void addLast(T element) {
        Objects.requireNonNull(element);

        if (tail == null) {
            this.head = new Node<T>(element);
            this.tail = this.head;
        } else {
            this.tail.next = new Node<T>(element);
            this.tail = this.tail.next;
        }

        this.size++;
    }

    public T getFirst() {
        if (this.head == null)
            throw new NoSuchElementException();

        return this.head.data;
    }

    public T getLast() {
        if (this.tail == null)
            throw new NoSuchElementException();

        return this.tail.data;
    }

    public T get(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException(index);

        if (this.size() == 0 || this.size() < index)
            throw new NoSuchElementException();

        int counter = 0;
        Node<T> node = this.head;
        while (counter++ != index) {
            node = node.next;
        }

        return node.data;
    }

    public boolean remove(T element) {
        if (this.size() == 0)
            throw new NoSuchElementException();


        if (this.head == this.tail) {
            if (this.head.data.equals(element)) {
                this.head = this.tail = null;
                this.size--;
                return true;
            }
        } else {
            Node<T> node = this.head;
            Node<T> prev = null;
            while (node != null) {
                if (node.data.equals(element)) {
                    if (prev != null) {
                        prev.next = node.next;
                    } else {
                        this.head = this.head.next;
                    }

                    if (node.next == null) {
                        this.tail = prev;
                    }
                    
                    this.size--;
                    return true;
                }
                prev = node;
                node = node.next;
            }
        }

        return false;
    }

    public T removeFirst() {
        if (this.size() == 0)
            throw new NoSuchElementException();

        Node<T> temp = this.head;
        if (this.head != this.tail) {
            this.head = this.head.next;
        } else {
            this.head = this.tail = null;
        }

        this.size--;
        return temp.data;
    }

    public T removeLast() {
        if (this.size() == 0)
            throw new NoSuchElementException();

        Node<T> node = this.head;
        Node<T> prev = null;
        while (node != this.tail) {
            prev = node;
            node = node.next;
        }

        if (prev != null) {
            prev.next = null;
            this.tail = prev;
        } else
            this.head = this.tail = null;

        this.size--;
        return node.data;
    }

    public int size() {
        return this.size;
    }
}
```