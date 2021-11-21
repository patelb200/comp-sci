public void selectionSort(int[] arr) {

    if(arr == null || arr.length == 1) {
        return;
    }

    for(int i = 0; i < arr.length; i++) {
        int minIdx = i;
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

int[] expected = new int[]{0, 2, 2, 5, 7, 8, 12, 13, 19, 34};
int[] test = new int[]{7, 19, 2, 34, 2, 12, 8, 0, 13, 5};

selectionSort(test);
   
System.out.println(Arrays.toString(expected));
System.out.println(Arrays.toString(test));
System.out.println("expected == test: " + Arrays.equals(expected, test));

/exit