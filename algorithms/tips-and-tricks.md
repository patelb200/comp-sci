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
    num /= 10; // division will remove the 1s place
}
```
```
4
2
0
1
```

### Detect Integer Overflow

To detect integer overflow when summing two integers that the sum is greater than `Integer.MAX`;

``` java
var a = Integer.MAX;
var b = 2;
try{
    var result = Math.addExact(a, b);
} catch(ArithmeticException e){
    System.out.println("Overflow!");
}

try{
    var result = Math.multiplyExact(a, b);
} catch(ArithmeticException e){
    System.out.println("Overflow!");
}
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
| Op  |                | Desc                                  |
| --- | ---            | ------------------------------------- |
| &   | and            | a and b is true                       |
| \|  | or             | a or b is true                        |
| ^   | xor            | a and b not true or a and b not false |
| <<  | left shift | shifts a bit pattern to the left |
| >>  | signed right shift | shifts a bit pattern to the right |
| >>> | unsigned right shift | shifts a zero into the leftmost position |

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

#### Binary Arithmetic

When shifting signed integers, the left most 32 bit is used for the sign.

```
11111111111111111111111111111111 = -1
01111111111111111111111111111111 = 2147483647

10000000000000000000000000000000 = -2147483648
00000000000000000000000000000000 = 0
```

##### Multiply by 2 (signed left shift)

``` java

Integer.toBinaryString(1); // 001 = 1
Integer.toBinaryString(1 << 1); // 010 = 2
Integer.toBinaryString(1 << 2); // 100 = 4

Integer.toBinaryString(3); // 0011 = 3
Integer.toBinaryString(3 << 1); // 0110 = 6
Integer.toBinaryString(3 << 2); // 1100 = 12

// Note negative integer representation
Integer.toBinaryString(-1); // 11111111111111111111111111111111 = -1
Integer.toBinaryString(-1 << 1); // 11111111111111111111111111111110 = -2
Integer.toBinaryString(-1 << 2);  // 11111111111111111111111111111100 = -4

```
##### Divide by 2 (signed right shift)
``` java

Integer.toBinaryString(4); // 100 = 4
Integer.toBinaryString(4 >> 1); // 010 = 2
Integer.toBinaryString(4 >> 2); // 001 = 1

Integer.toBinaryString(7); // 0111 = 7
Integer.toBinaryString(7 >> 1); // 0011 = 3
Integer.toBinaryString(7 >> 2); // 0001 = 1

// Note negative integer representation
Integer.toBinaryString(-4); // 11111111111111111111111111111100 = -4
Integer.toBinaryString(-4 >> 1); // 11111111111111111111111111111110 = -2
Integer.toBinaryString(-4 >> 2);  // 11111111111111111111111111111111 = -1

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
char A = 'a' - 32; // A
char a = 'A' + 32; // a
```

### Map English Alphabet Character to Array Index

When you need to count the number of english characters in a string in **constant space**, you can create an array of size 26 where 0 is the `a` bucket. 

**Note:** All characters in the string should be the same case.

- lowercase. ascii `a` offset
- uppercase. ascii `A` offset

``` java
int[] alpha = new int[26];
String s = "bharatpatel";

for(int i = 0; i < s.length(); i++) {
    alpha[s.charAt(i) - 'a']++; 
}

System.out.println(Arrays.toString(alpha));
```
```
[3, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 2, 0, 0, 0, 0, 0, 0]
```