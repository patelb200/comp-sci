public class Trie {

    private static class Node {
        Character key;
        Map<Character, Node> children = new HashMap<Character, Node>();
        boolean terminal;

        public Node(Character key) {
            this.key = key;
        }
    }

    private final Node root = new Node(' ');
    private int size;

    public boolean add(String value) {
        Objects.requireNonNull(value);

        char[] chars = value.toCharArray();

        Node node = this.root;
        for (char key : chars) {
            if (node.children.containsKey(key)) {
                node = node.children.get(key);
            } else {
                Node newNode = new Node(key);
                node.children.put(key, newNode);
                node = newNode;
            }
        }

        // determine if the last node is a terminal since that would mean that the node already existed in the trie
        if (node.terminal) {
            return false;
        } else {
            this.size++;
            return (node.terminal = true);
        }
    }

    public boolean contains(String value) {
        Objects.requireNonNull(value);

        char[] chars = value.toCharArray();

        Node node = this.root;
        for (char key : chars) {
            if (node.children.containsKey(key)) {
                node = node.children.get(key);
            } else {
                return false;
            }
        }

        return node.terminal;
    }

    public boolean delete(String value) {
        Objects.requireNonNull(value);

        char[] chars = value.toCharArray();
        Stack<Node> nodes = new Stack<Node>();

        Node node = this.root;
        nodes.push(node);
        for (char key : chars) {
            if (node.children.containsKey(key)) {
                node = node.children.get(key);
                nodes.push(node);
            } else {
                break;
            }
        }

        if (nodes.size() - 1 != chars.length || !node.terminal) // '- 1' for the root node
            return false;

        node.terminal = false; // indicates the string is soft deleted in case this node has children
        char childKeyToDelete = '-';
        // process to remove child from parent in a bottom up fashion
        do {
            Node nodeToDelete = nodes.pop();
            nodeToDelete.children.remove(childKeyToDelete);
            if (nodeToDelete.children.size() == 0 && !nodeToDelete.terminal) {
                childKeyToDelete = nodeToDelete.key;
            } else {
                break;
            }
        } while (!nodes.isEmpty());

        this.size--;
        return true;
    }

    public int size() {
        return this.size;
    }
}

Trie t = new Trie();
System.out.println("add tom - " + t.add("tom"));
System.out.println("contains tom - " + t.contains("tom"));
System.out.println("contains dick - " + t.contains("dick"));
System.out.println("add harry - " + t.add("harry"));
System.out.println("add harry - " + t.add("harry"));
System.out.println("add car - " + t.add("car"));
System.out.println("add can - " + t.add("can"));
System.out.println("add cart - " + t.add("cart"));
System.out.println("delete cart - " + t.delete("cart"));
System.out.println("delete car - " + t.delete("car"));
System.out.println("delete cart - " + t.delete("cart"));
System.out.println("delete harry - " + t.delete("harry"));
System.out.println("size - " + t.size());

/exit