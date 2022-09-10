# Binary Search

## Definition

Binary search is a very efficient algorithm for searching in a sorted array. It is a principal example of a decrease-by-a-constant-factor algorithm.

## Steps
- `A` = array
- `t` = target
- `m` = mid index
```
    A[m]>t          A[m]==t            A[m]<t>
A[0]...A[m − 1]       A[m]        A[m + 1]...A[n − 1]
```
- if `A[m]` **equals** target `t` then return `m`
- if `A[m]` is **>** target `t` then search sub array `0...m-1`
- if `A[m]` is **<** target `t` then search sub array `A[m+1...n-1]`
- if target `t` not found then return `-1`


## Implementation

**O(log(n)) - Iterative** - returns the index of target or where it would have been plus 1 made into a negative.
``` java
public int searchInsert(int[] nums, int target) {
    
    int start = 0, end = nums.length - 1;
    
    while(start <= end) {
        int mid = start + (end - start) / 2;
        if(nums[mid] == target) {
            return mid;
        } else if(nums[mid] > target) {
            end = mid - 1; // first half of the array not including the mid index as the end.
        } else {
            start = mid + 1; // second half of the array not including the mid index as the start.
        }
    }
    
    return -(start + 1);
}
```

**O(log(n)) - Recursive** - returns the index of target or where it would have been plus 1 made into a negative.
``` java
public int searchInsert(int[] nums, int target, int start, int end) {
    
    if(start > end)
        return -(start + 1);

    int mid = start + (end - start) / 2;

    if(nums[mid] == target)
        return mid;
    else if(nums[mid] > target)
        return searchInsert(nums, start, mid - 1);
    else
        return searchInsert(nums, mid + 1, end);
}
```