public long fibonacci(int n) {
    if(n <= 1)
        return n;
    else
        return fibonacci(n - 1) + fibonacci(n - 2);
}

long expected = 610;
long test = fibonacci(15);

System.out.println(expected);
System.out.println(test);
System.out.println("expected == test: " + (expected == test));

/exit