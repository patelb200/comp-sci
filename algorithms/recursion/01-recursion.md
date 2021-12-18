# Recursion

Recursion is the process of defining a problem in terms of itself. Its a top down approach to solving a problem. Another classification name is decrease and conquer. Recursive functions use a call stack. Each function call has a copy of the variables in relation to its own stack frame. Stack overflow errors can be caused by large inputs because the stack grows too large.

## Parts of a Recursion Algorithm
1. Base case - when to stop recursing, compute the result immediately 
2. Recursive step - compute the result with a call to the same function but with inputs reduced in size