package com.surenpi.jenkins;

public class Test {
    public static void change(Integer a) {
        a = 50;
    }
    public static void main(String[] args) {
        Integer a = 10;
        System.out.println(a);
        change(a);
        System.out.println(a);

        int []b = {10, 20};
        System.out.println(b[0]);
        changeArray(b);
        System.out.println(b[0]);
    }

    public static void changeArray(int[] a) {
        a[0] = 50;
    }
}
