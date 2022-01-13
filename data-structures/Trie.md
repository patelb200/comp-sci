# Trie

It is a tree-like data structure also known as `prefix tree` or `digital tree`. Trie's are used in searchs such as word games or text and used to store distinct strings, although a `set` would be more space efficient when there are no common prefixes amongst the strings. Each node in the tree consists of a key, children nodes and a flag to indicate that the node is creates a complete path. The root node can be an empty string or a special marker. When adding a string to the trie, a node for each character has a key and children nodes that are the next possible nodes (characters) for the strings stored in the trie. A flag that is `true` in a node indicates the end of a string.

## Visual Tree
Strings `car`, `cart` and `can` in a Trie. The `r`, `t` and `n` nodes are terminal which would be indicated with a `true` flag.
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(' ')  
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|    
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;('c')  
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|  
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;('a')  
>&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;\  
>&nbsp;**('r')**&nbsp;&nbsp;**('n')**  
>&nbsp;&nbsp;&nbsp;|  
>&nbsp;**('t')**  

## Operations

- **boolean contains(V)** - Search for string **V**.
- **boolean add(V)** - Add string **V** to trie.
- **delete(V)** - Delete string **V** from trie.


## Time Complexity

- **boolean contains(V)** - O(n) where `n` is the length of string.
- **boolean add(V)** - O(n) where `n` is the length of string.
- **boolean delete(V)** - O(2n) where `n` is the length of string. A `stack` is utilized, which takes one pass to fill and another pass to process until it's empty.

## Implementation

``` java
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

        if (nodes.size() - 1 != chars.length || !node.terminal) // '- 1' to account for the root node
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
```