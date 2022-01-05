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

SinglyLinkedList<Integer> sl = new SinglyLinkedList<Integer>();

sl.addFirst(1);
sl.addFirst(2);
sl.addFirst(3);
System.out.println(sl.getFirst());
System.out.println(sl.getLast());
System.out.println(sl.removeLast());
System.out.println(sl.removeLast());
System.out.println(sl.removeLast());
sl.addLast(3);
sl.addLast(2);
sl.addLast(1);
System.out.println(sl.removeFirst());
sl.addFirst(0);
for(int i = 0; i < sl.size(); i++)
    System.out.print(sl.get(i) + " ");
System.out.println();
System.out.println(sl.remove(0));
System.out.println(sl.remove(20));
System.out.println(sl.remove(1));
System.out.println(sl.remove(2));
System.out.println(sl.size());

/exit