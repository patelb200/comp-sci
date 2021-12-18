public long factorial(int n) {
    if(n == 0)
        return 1;
    else
        return n * factorial(n - 1);
}

long expected = 3_628_800;
long test = factorial(10);

System.out.println(expected);
System.out.println("expected == test: " + (expected == test));

/exit