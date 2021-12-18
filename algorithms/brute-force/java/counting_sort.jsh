public int[] countingSort(int[] arr, int maxKey) {

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

int[] expected = new int[]{0, 2, 2, 5, 7, 8, 12, 13, 19, 34};
int[] test = new int[]{7, 19, 2, 34, 2, 12, 8, 0, 13, 5};

countingSort(test, 34);
   
System.out.println(Arrays.toString(expected));
System.out.println(Arrays.toString(test));
System.out.println("expected == test: " + Arrays.equals(expected, test));


/exit