public class InsertionSort {
    public void sort(int[] arr) {

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
}

InsertionSort is = new InsertionSort();

int[] arr = new int[]{89, 45, 68, 90, 29, 34, 17};
System.out.println(Arrays.toString(arr));

is.sort(arr);

System.out.println(Arrays.toString(arr));

/exit