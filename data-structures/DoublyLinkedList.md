# Doubly Linked List

The doubly `LinkedList` consists of nodes that have a pointer to the next and previous node in the list and the data value. Instantiating the `LinkedList` does not require memory to be allocated ahead of time such as in an `Array` and the size can grow and shrink dynamically. It has the ability to add and remove an element anywhere in the list without having to shift elements as you would in an `ArrayList`, which is great for when you have to do frequent inserts and removals. The list can be traversed from the front or the back of the list because of the nodes having pointers to both of its neighbors.

## Operations

- **addFirst** - *add* a node to the `head` of the list
- **addLast** - *add* a node to the `tail` of the list
- **add(i, T)** - *traverse* the list to index `i` and *insert* the element `T`
- **removeFirst** - *remove* a node from the `head` of the list
- **removeLast** - *remove* a node from the `tail` of the list
- **remove(T)** - *search* and *remove* node from the list
- **T getFirst** - *return* the value from the `head` of the list
- **T getLast** - *return* the value from the  `tail` of the list
- **T get(i)** - *traverse* the list to the given index and return the value


## Time Complexity

- **addFirst** - O(1)
- **addLast** - O(1)
- **add(i, T)** - O(n)
- **removeFirst** - O(1)
- **removeLast** - O(n)
- **remove(T)** - O(n)
- **getFirst** - O(1)
- **getLast** - O(1)
- **get(i)** - O(n)

## Implementation

### Linked Nodes

``` java
public class DoublyLinkedList<T> {

    private static class Node<T> {
        Node<T> next;
        Node<T> prev;
        T data;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head, tail;
    private int size = 0;

    public boolean addFirst(T element) {
        Objects.requireNonNull(element);

        if (this.head == null) {
            this.head = this.tail = new Node<T>(element);
        } else {
            Node<T> temp = this.head;
            this.head = new Node<T>(element);
            this.head.next = temp;
            temp.prev = this.head;
        }
        this.size++;
        return true;
    }

    public boolean addLast(T element) {
        Objects.requireNonNull(element);

        if (this.tail == null) {
            this.tail = this.head = new Node<T>(element);
        } else {
            Node<T> temp = this.tail;
            this.tail = new Node<T>(element);
            this.tail.prev = temp;
            temp.next = this.tail;
        }
        this.size++;
        return true;
    }

    public boolean add(int index, T element) {
        Objects.requireNonNull(element);
        if (index < 0 || index > this.size())
            throw new IndexOutOfBoundsException(index);

        if (index == 0) {
            this.addFirst(element);
        } else if (index == this.size()) {
            this.addLast(element);
        } else {
            int counter = 0;
            Node<T> node = this.head;
            while (counter++ != index) {
                node = node.next;
            }
            Node<T> newNode = new Node<T>(element);
            newNode.prev = node.prev;
            node.prev.next = newNode;
            newNode.next = node;
            node.prev = newNode;
            this.size++;
        }
        return true;
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
        if (index < 0 || index > this.size() - 1)
            throw new IndexOutOfBoundsException(index);

        // Depending on which side of the list the index is, we can traverse from the head or tail for efficiency
        int counter;
        Node<T> node;
        if (index < (this.size() / 2)) {
            counter = 0;
            node = this.head;
            while (counter++ != index) {
                node = this.head.next;
            }
            return node.data;
        } else {
            counter = this.size() - 1;
            node = this.tail;
            while (counter-- != index) {
                node = node.prev;
            }
            return node.data;
        }
    }

    public T removeFirst() {
        if (this.size() == 0)
            throw new NoSuchElementException();

        Node<T> temp = this.head;
        if (this.head == this.tail) {
            this.head = this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }
        this.size--;
        return temp.data;
    }

    public T removeLast() {
        if (this.size() == 0)
            throw new NoSuchElementException();

        Node<T> temp = this.tail;
        if (this.head == this.tail) {
            this.head = this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        this.size--;
        return temp.data;
    }

    public boolean remove(T element) {
        Objects.requireNonNull(element);

        if (this.head == this.tail) {
            if (this.head.data.equals(element)) {
                this.head = this.tail = null;
                size--;
                return true;
            }
        } else {
            Node<T> node = this.head;

            while (node != null) {
                if (node.data.equals(element)) {

                    Node<T> prev = node.prev;
                    Node<T> next = node.next;
                    if (prev != null)
                        prev.next = next;
                    else // head
                        this.head = next;

                    if (next != null)
                        next.prev = prev;
                    else // tail
                        this.tail = prev;

                    node.prev = node.next = null;
                    this.size--;
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public String printForward() {
        StringBuilder sb = new StringBuilder("head -> ");
        Node<T> node = this.head;
        while (node != null) {
            sb.append(node.data).append(" -> ");
            node = node.next;
        }
        sb.append("tail");
        return sb.toString();
    }

    public String printReverse() {
        StringBuilder sb = new StringBuilder("tail -> ");
        Node<T> node = this.tail;
        while (node != null) {
            sb.append(node.data).append(" -> ");
            node = node.prev;
        }
        sb.append("head");
        return sb.toString();
    }

    public int size() {
        return this.size;
    }
}
```