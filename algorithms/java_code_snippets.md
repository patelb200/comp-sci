# Java Code Snippets

1. [Run Jshell Scripts](#run-jshell-scripts)
2. [Arrays](#arrays)
3. [Strings](#strings)
4. [Linked Lists](#linked-lists)

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

## Linked Lists

To reverse a linked list you need multiple pointers to change the links between the nodes.

``` java
public ListNode reverse(ListNode head) {
    ListNode reverse = null;
    ListNode current = head;
    ListNode temp = null;

    while(current != null) {
        temp = current.next;
        current.next = reverse;
        reverse = current;
        current = temp;
    }

    return reverse;
}
```

**Visualize:**
```
(1)->(2)->(3)->(4)
```
Iterations:


```
temp: 2
current.next: null
reverse: 1
current: 2
```
> (2)->(3)->(4)->null
> <br>(1)->null

```
temp: 3
current.next: 1
reverse: 2
current: 3
```
> (3)->(4)->null
> <br>(2)->(1)->null