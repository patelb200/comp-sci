# Bubble Sort

For each element in the array, compare with its adjacent element and swap if current element is out of order. On each iteration of the array starting from 0, the largest value in the array will eventually bubble up to the end of the array.

## Time complexity

`O(n^2)` - for each current element iterate over all elements to the right of the current element.

## Solution

``` java
public void bubbleSort(int[] arr) {
    // note skipping last element
    for (int i = 0; i < arr.length - 1; i++) {
        
        boolean swap = false;

        // note skipping additional last element on each pass since the last element on each pass is sorted.
        for (int j = 0; j < arr.length - i - 1; j++) {
            if (arr[j] > arr[j+1]) {
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp;
                swap = true;
            }
        }

        // if the current pass had zero swaps then end since it means array is sorted.
        if(!swap) {
            break;
        }
    }
}
```