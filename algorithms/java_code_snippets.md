# Java Code Snippets

1. [Run Jshell Scripts](#run-jshell-scripts)
2. [Arrays](#arrays)
3. [Strings](#strings)

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

### Conversion

#### Array to List

``` java
Integer[] arr = new Integer[]{1, 2, 3, 4, 5};

List<Integer> list = Arrays.asList(arr);
```
``` java
int[] arr = new int[]{1, 2, 3, 4, 5};

List<Integer> list = IntStream.of(arr).boxed().collect(Collectors.toList());
```

#### List to Array

``` java
List<Integer> list = new ArrayList<Integer>();
list.add(1);
list.add(2);
list.add(3);

int[] arr = list.stream().mapToInt(x -> x).toArray();
```

### Copy array

#### Using iteration

``` java
int[] original = new int[]{1, 2, 3, 4, 5};
int[] clone = new int[original.length];

for(int i: i < original.length; i++) {
    clone[i] = original[i];
}
```

#### Using java utilities

``` java
int[] original = new int[]{1, 2, 3, 4, 5};
int[] clone = new int[original.length];

// copy from index 0 of original to array clone start from 0 to end of clone
System.arraycopy(original, 0, clone, 0);

// copy from index 0 of original to array clone start from 0 to given length of 2
System.arraycopy(original, 0, clone, 0, 2);

// Copy original starting at index 0 and return new instance
int[] copy = Arrays.copyOf(original);

// Copy original starting at index 0 and return new instance with given length
int[] copy = Arrays.copyOf(original, 2);

// Copy original starting at given index and end at given length
int[] copy = Arrays.copyOfRange(original, 2, 5);
```

## Strings

### Check character type
The following are different ways to determine if the character is a letter of the english alphabet or a digit;

``` java
char a = 'a';
char one = '1';

Character.isLetter(a); // true
Character.isLetter(one); // false

Character.isDigit(one); //true

Character.isLetterOrDigit(a); // true

int cNum = 'd' - 'a'; //3
boolean isLowerLetter = cNum > 0 && cNum < 26; //true
```