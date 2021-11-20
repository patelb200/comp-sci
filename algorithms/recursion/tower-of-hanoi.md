# Tower of Hanoi

Move all disks from rod 1 to 3 where only a smaller disk can be stacked on top of a larger disk and only 1 disk can be moved at a time.


## Explanation
There are 3 main operations that need to occur.

Input n disks:  
1. Move top n - 1 disks from rod 1 to rod 2
   - Use rod 3 as an aux
2. Move 1 disk from rod 1 to rod 3  
3. Move top n - 1 disks from peg 2 to peg 3
   - use rod 1 as an aux


## Solution
disk - number of disks  
start - starting peg  
end - ending peg

``` java
public void towerOfHanoi(int disks) {
    towerOfHanoiHelper(disks, 1, 3);
}

public void towerOfHanoiHelper(int disks, int start, int end) {
    if(disks == 1) {
        printMove(start, end);
    } else { 
	    int auxRod = 6 - (start + end);
        towerOfHanoiHelper(disks - 1, start, auxRod);
        towerOfHanoiHelper(1, start, end);
        towerOfHanoiHelper(disks - 1, auxRod, end);
    }
}

public void printMove(int start, int end) {
    System.out.println(start + " -> " + end);
}
```