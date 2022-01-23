public class Node {
    private int data;
    private List<Node> children = new ArrayList<Node>();

    public Node(int data) {
        this.data = data;
    }

    public void addChild(Node node) {
        this.children.add(node);
    }

    public int getData() {
        return this.data;
    }

    public List<Node> getChildren() {
        return this.children;
    }
}
public class DFS {

    public static void recursive(Node node, Set<Node> visited, StringBuilder sb) {

        if (visited.contains(node))
            return;

        visited.add(node);
        for (Node child : node.getChildren()) {
            recursive(child, visited, sb);
        }

        sb.append(" -> ").append(node.getData());
    }
}

var n1 = new Node(1);
n1.addChild(new Node(2));
n1.addChild(new Node(3));
n1.addChild(new Node(4));

var n2 = new Node(5);
var n3 = new Node(6);
var n4 = new Node(7);

n1.addChild(n2);
n2.addChild(n3);
n3.addChild(n4);
n4.addChild(n2);

var dfs = new DFS();

var sb = new StringBuilder();
dfs.recursive(n1, new HashSet<Node>(), sb);
System.out.println(sb.toString());

/exit