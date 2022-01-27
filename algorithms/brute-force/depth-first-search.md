# Depth First Search

Depth-first search is an algorithm to traverse a graph. Starting at the root, it explores the graph as deep as possible by visiting vertex `v`'s most adjacent vertex through the closest edge. Once vertex `v` reaches the end of a branch, it will backtrack and explore another branch via an non visited vertex.

## Use Cases
- Maze path finding
- Topological Sorting
- Articulation Points
- Connectivity
- Acyclic of a graph - if graph contains a back edge then its not acyclic

## DFS Forest
A ***depth-first search forest*** can be constructed when traversing the tree starting at any vertex as the root. As each node is visited, an edge called a ***tree edge*** is used to attach the child node with the parent node. When a node that is already visited is encountered then a ***back edge*** is created between the child and the ancestor node.  

- **tree edge** - the edges that create the forest.
- **back edge** - the edges that lead to an ancestor node that is not its immediate predecessor.

```
               (1)
           ->   |
          /    (5)--
tree edge/-->   |   |
         \     (6)  | <--back edge
          \->   |   |
               (7)--
```
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