public class Queue<T> {

    private T[] elements;
    private int head, tail, size = 0;

    @SuppressWarnings("unchecked")
    public Queue(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public void enqueue(T element) {
        if(!isFull()) {
            if(tail == this.elements.length)
                tail = 0;
            this.elements[tail++] = element;
            size++;
        } else
            throw new RuntimeException("Overflow");
    }

    public T dequeue() {
        if(!isEmpty()) {
            if(head == this.elements.length)
                head = 0;
            size--;
            return this.elements[head++];
        } else
            throw new RuntimeException("Underflow");
    }

    public T peek() {
        if(!isEmpty())
            return this.elements[head];
        else
            throw new RuntimeException("Underflow");
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == this.elements.length;
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