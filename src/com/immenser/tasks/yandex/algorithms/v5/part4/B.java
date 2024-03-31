package com.immenser.tasks.yandex.algorithms.v5.part4;

import java.math.BigInteger;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        long n = stdin.nextLong();    // кол-во клеток поля
        stdin.close();
        long k = 0;
        if (n != 0) {
            k = binarySearch(n);
        }
        System.out.println(k);
    }

    public static long binarySearch(long n) {
        long l = 1;
        long r = n;

        while (l != r) {
            long m = (l + r + 1) / 2;
            if (countShipsCells(BigInteger.valueOf(m)).add(countGapCells(BigInteger.valueOf(m))).compareTo(BigInteger.valueOf(n)) > 0) {
                r = m - 1;
            }
            else {
                l = m;
            }
        }
        return l;
    }

    public static BigInteger countShipsCells(BigInteger k) {
        return k.multiply(k.add(BigInteger.valueOf(1)).multiply(k.add(BigInteger.valueOf(2)))).divide(BigInteger.valueOf(6));
    }

    public static BigInteger countGapCells(BigInteger k) {
        return k.multiply(k).add(k).divide(BigInteger.valueOf(2)).subtract(BigInteger.valueOf(1));
    }
}