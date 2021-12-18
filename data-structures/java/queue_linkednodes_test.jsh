public class Queue<T> {

    private static class Node<T> {
        private T data;
        private Node<T> next;
        public Node(T data){ this.data = data; }
        public Node(T data, Node<T> next){ this.data = data; this.next = next;}
    }

    private Node<T> head, tail;
    private final int capacity;
    private int size = 0;

    public Queue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(T element) {
        if(!isFull()) {
            if(this.head == null) {
                this.head = new Node<T>(element);
                this.tail = this.head;
            } else {
                Node<T> temp = new Node<T>(element);
                this.tail.next = temp;
                this.tail = temp;
            }
            size++;
        } else
            throw new RuntimeException("Overflow");
    }

    public T dequeue() {
        if(!isEmpty()) {
            Node<T> temp = this.head;
            this.head = this.head.next;
            size--;
            return temp.data;
        } else
            throw new RuntimeException("Underflow");
    }

    public T peek() {
        if(!isEmpty()) {
            return this.head.data;
        } else
            throw new RuntimeException("Underflow");
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }
}


Queue<Integer> q = new Queue<Integer>(3);

System.out.println("isEmpty " + q.isEmpty());
q.enqueue(1);
System.out.println("isFull " + q.isFull());
q.enqueue(2);
System.out.println("isFull " + q.isFull());
q.enqueue(3);
System.out.println("isFull " + q.isFull());
System.out.println("dequeue " + q.dequeue());
System.out.println("peek " + q.peek());
System.out.println("isFull " + q.isFull());
System.out.println("isEmpty " + q.isEmpty());
q.enqueue(4);
System.out.println("peek " + q.peek());
System.out.println("isFull " + q.isFull());
System.out.println("dequeue " + q.dequeue());
q.enqueue(5);
q.enqueue(6);
System.out.println("peek " + q.peek());
System.out.println("dequeue " + q.dequeue());
System.out.println("dequeue " + q.dequeue());
System.out.println("dequeue " + q.dequeue());
System.out.println("size " + q.size());

/exit