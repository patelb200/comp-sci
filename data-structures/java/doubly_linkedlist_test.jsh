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

DoublyLinkedList<Integer> d = new DoublyLinkedList<Integer>();
d.addFirst(101);
d.addFirst(102);
d.addFirst(103);
d.addLast(104);
d.addLast(105);
d.add(0, 107);
d.add(1, 108);
d.add(2, 109);
d.add(d.size(), 110);
System.out.println(d.printForward());
System.out.println(d.printReverse());
System.out.println("remove last " + d.removeLast());
System.out.println("get " + (d.size() - 1) + " " + d.get(d.size() - 1));
System.out.println("remove first " + d.removeFirst());
System.out.println("get 0 " + d.get(0));
d.remove(d.get(0));
d.remove(103);
System.out.println(d.printForward());
System.out.println(d.printReverse());

/exit