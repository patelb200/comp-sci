# Java Code Snippets

1. [Run Jshell Scripts](#run-jshell-scripts)
2. [Arrays](#arrays)

## Run jshell scripts

To run `.jsh` files, you need minimum jdk 9.

### Run script

``` sh
jshell selection_sort.jsh
```
## Arrays

### Sorting

#### 1D

``` java
var arr = new int[]{3,5,2,1,7};
Arrays.sort(arr, Comparator.comparing(a -> a));
Arrays.toString(arr);
$ ==> "[1, 2, 3, 6, 7]"
```

#### 2D

``` java
var arr = new int[][]{{3,2},{5,1},{5,0},{4,2},{1,3},{7,6}};
var comp = Comparator.<int[]>comparingInt(a -> a[0]).thenComparing(a -> a[1]);
Arrays.sort(arr, comp);
Arrays.deepToString(arr);

$ ==> "[[1, 3], [3, 2], [4, 2], [5, 0], [5, 1], [7, 6]]"
```