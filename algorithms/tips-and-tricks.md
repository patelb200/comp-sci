# Tips/Tricks

1. [Math](#math)
2. [Iterating](#iterating)
3. [Bitwise](#bitwise-operators)
4. [Strings](#strings)


## Math

### Get Last Digit of Integer

``` java
var num = 1024;
while (num > 0) {
    System.out.println(num % 10); // remainder gives the last digit
    num /= 10; // division will remove the 10s place
}
```
```
4
2
0
1
```

### Avoid Integer Overflow

To avoid integer overflow when summing to large numbers that the sum is greater than `Integer.MAX`;

``` java
var a = Integer.MAX;
var b = 1;
var num = a + (b - a);
```

## Iterating

### Array Loop Wrap Around
To loop through an array starting from any position and visiting every element once.

``` java
var arr = new int[]{1,2,3,4,5,6};
int startIdx = 3, counter = 3;
int arrLength = arr.length;
do {
    // do something
    System.out.println(arr[counter]);
    counter++;
} while ((counter %= arrLength) != startIdx);
```
```
4
5
6
1
2
3
```

## Bitwise Operators

The bitwise operators work on binary values such as long, int, short, char, byte

### Definition
| Op  |     | Desc                                  |
| --- | --- | ------------------------------------- |
| &   | and | a and b is true                       |
| \|  | or  | a or b is true                        |
| ^   | xor | a and b not true or a and b not false |

### Examples

#### & (and)
``` java
var a = 0b1010;
var b = 0b1111;
a & b; // 10
```
```
1010
1111
----
1010
```

#### | (or)
``` java
var a = 0b1010;
var b = 0b1111;
a | b; // 15
```
```
1010
1111
----
1111
```

#### ^ (xor)
``` java
var a = 0b1010;
var b = 0b1111;

a ^ b; // 5
```
```
1010
1111
----
0101
```


## Strings

### Int value of Char [0-9]

``` java
char zeroC = '0' // ascii 48
char oneC = '1'; //ascii 49
int oneI = oneC - zeroC; // 49 - 48 = 1

System.out.println(oneI); // 1 
```

### Uppercase char of Lowercase char

To get the uppercase of a lowercase character, you subtract 32 because the corresponding uppercase character in the ascii table is 32 spots to the left. Vice versa.

``` java
var A = 'a' - 32; // A
var a = 'A' + 32; // a
```