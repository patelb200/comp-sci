# Binary Tree Traversal

You can think of the prefix in the traversal name to state which order the root is visited. For example, **In**-order, the prefix means that the root is visited in the order from left to right node. 

`In` - left -> **root** -> right  
`Pre` - **root** -> left -> right  
`Post` - left -> right -> **root**

## In Order

1. Visit left most subtree
2. Visit subtree root
3. Visit right most subtree

### Recursion
``` java
public void inorder(Node node) {
    
    if(node == null)
        return;

    inorder(node.leftTree);
    System.out.print(node.val);
    inorder(node.rightTree);
}
```

### Iterative
``` java
public void inorder(Node root) {

    Stack<Node> s = new Stack<Node>();
    
    Node currentNode = root;
    while(!s.isEmpty() || currentNode != null) {

       if(currentNode != null) {
           s.push(currentNode);
           currentNode = currentNode.left;
       } else {
           currentNode = s.pop();
           System.out.println(currentNode.val);
           currentNode = currentNode.right;
       }

    }

}
```

## Pre Order

1. Visit root
2. Visit left subtree
3. Visit right subtree


### Recursive

```java
public void preorder(Node node) {

    if(node == null)
        return;

    System.out.print(node.val);

    preorder(node.leftTree);
    preorder(node.rightTree);

}
```

### Iterative
```java
    public void preorderIterative(Node root) {

        if (root == null)
            return;

        Stack<Node> holder = new Stack<>();
        holder.push(root);

        Node temp;
        
        while (!holder.empty()) {

            temp = holder.pop();

            System.out.print(temp.val);

            if (temp.rightTree != null)
                holder.push(temp.rightTree);
            if (temp.leftTree != null)
                holder.push(temp.leftTree);

        }
}
```