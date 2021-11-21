public int stringMatch(String text, String prefix) {

    char[] textArr = text.toCharArray();
    char[] prefixArr = prefix.toCharArray();

    OUTER:
    for (int i = 0; i <= textArr.length - prefixArr.length; i++) {
        for (int j = 0; j < prefixArr.length; j++) {
            if (textArr[i + j] != prefixArr[j])
                continue OUTER;
        }
        return i;
    }

    return -1;
}

System.out.println("Test: text = hello, prefix = he, output = " + stringMatch("hello", "he"));
System.out.println("Test: text = hello, prefix = llo, output = " + stringMatch("hello", "llo"));
System.out.println("Test: text = hello, prefix = hi, output = " + stringMatch("hello", "hi"));
System.out.println("Test: text = hello, prefix = l, output = " + stringMatch("hello", "l"));

/exit