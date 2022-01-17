# Insertion Sort

For each element in the unsorted array, traverse the sorted subarray from the right to the left and insert into the position where the left less than the current element.

## Time complexity

- `O(n^2)` - The average and worst case when the elements are unsorted.
- `O(n)` - Best case when the elements are already sorted.

## Solution

``` java
public void insertionSort(int[] arr) {

    for (int i = 0; i < arr.length; i++) {
        int curr = arr[i];
        // everything in subarray i-1...0 is sorted. The current element 'i' must find its position to insert in there.
        int j = i - 1;
        while (j >= 0 && arr[j] > curr) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = curr;
    }
    
}
```