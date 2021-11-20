# Selection Sort

Scan the array for the smallest element and swap it with the first element. Move to the next element and do the comparision for each element to the right of the current element.

## Time complexity

`O(n^2)` - for each current element iterate over all elements to the right of the current element.

## Solution

``` java
public void selectionSort(int[] arr) {

    if(arr == null || arr.length == 1) {
        return;
    }

    for(int i = 0; i < arr.length; i++) {
        int minIdx = i;
        // find min value to the right of the current element
        for(int j = i + 1; j < arr.length; j++) {
            if(arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }

        // swap
        int temp = arr[i];
        arr[i] = arr[minIdx];
        arr[minIdx] = temp;
    }
}
```