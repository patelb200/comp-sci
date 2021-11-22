public void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        boolean swap = false;
        for (int j = 0; j < arr.length - i - 1; j++) {
            if (arr[j] > arr[j+1]) {
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp;
                swap = true;
            }
        }

        if(!swap) {
            break;
        }
    }
}

int[] expected = new int[]{-10, -1, 0, 2, 2, 5, 7, 8, 8, 12, 13, 19, 34};
int[] test = new int[]{7, 19, 2, 34, 2, 12, 8, 0, 13, 5, -1, 8, -10};

bubbleSort(test);
   
System.out.println(Arrays.toString(expected));
System.out.println(Arrays.toString(test));
System.out.println("expected == test: " + Arrays.equals(expected, test));

/exit