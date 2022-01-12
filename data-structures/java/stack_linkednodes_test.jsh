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

StackLinkedNodes<Integer> sa = new StackLinkedNodes<Integer>();

sa.push(1);
sa.push(2);
System.out.println(sa.peek());
System.out.println(sa.pop());
System.out.println(sa.pop());
System.out.println(sa.pop());
sa.push(3);
sa.push(4);
sa.push(5);
System.out.println(sa.pop());
System.out.println(sa.pop());
System.out.println(sa.pop());
System.out.println(sa.pop());
System.out.println("size " + sa.size());
System.out.println("isEmpty " + sa.isEmpty());
sa.push(1);
sa.push(2);
sa.push(3);
sa.push(4);
System.out.println("peek " + sa.peek());
sa.push(5);
System.out.println("size " + sa.size());
while(!sa.isEmpty())
    System.out.print(sa.pop() + "->");

/exit