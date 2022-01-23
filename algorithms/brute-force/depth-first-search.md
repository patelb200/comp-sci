# Depth First Search

Depth-first search is an algorithm to traverse a graph. Starting at the root, it explores the graph as deep as possible by visiting vertex `v`'s most adjacent vertex through the closest edge. Once vertex `v` reaches the end of a branch, it will backtrack and explore another branch via an non visited vertex.

## Use Cases
- Maze path finding
- Topological Sorting
- Articulation Points

## Data structure

**Stack** - As each vertex is visited, it is pushed onto the stack. Once a dead end is reached, such as if the vertex was already visited or it has no more branches then it is popped off the stack.

## Implementation
```
Input: Graph "node", Empty Set to track visited nodes, StringBuilder sb to visualize node visits

Graph:
    (1)
    / \
  (2) (5)--
       |   |
      (6)  |
       |   |
      (7)--

Output: " -> 2 -> 7 -> 6 -> 5 -> 1"
```


### Recursive

``` java
public void recursive(Node node, Set<Node> visited, StringBuilder sb) {

    if (visited.contains(node))
        return;

    visited.add(node);
    for (Node child : node.getChildren()) {
        recursive(child, visited, sb);
    }

    // visit node
    sb.append(" -> ").append(node.getData());
}
```