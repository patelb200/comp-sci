# Counting Sort

Iterate through the array and count the number of times each value appears. This algorithm works best when the range of values is known ahead of time and is small. Once the the input is counted, iterate through the counts and recreate the final sorted array. If the range is not known then it has to be computed with an additional loop to find the max key value.

## Time complexity

`O(n)` - best, worst, and average. `O(n + m)`: `n` for input size and `m` for range of values.

## Solution

``` java
public int[] countingSort(int[] arr, int maxVal) {

    int[] counts = new int[maxVal + 1];

    for(int i = 0; i < arr.length; i++) {
        counts[arr[i]]++;
    }

    for(int i = 0, ptr = 0; i < counts.length; i++) {
        while(counts[i] > 0) {
            arr[ptr++] = i;
            counts[i]--;
        }
    }

    return arr;
}
```