package com.immenser.tasks.yandex.algorithms.v5.part1;

import java.util.Scanner;

public class E {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();  // начальная прибыль
        int k = stdin.nextInt();  // кол-во учредителей
        int d = stdin.nextInt();  // кол-во дней
        stdin.close();
        StringBuilder x = new StringBuilder("-1"); // конечная прибыль

        int i = 0;
        while ((n * 10 + i) % k != 0 && i < 10) {
            i++;
        }
        if (i != 10) {
            x = new StringBuilder(String.valueOf(n)).append(i);
            if (d != 1) {
                if (k < 10) {
                    x.append(String.valueOf(k).repeat(Math.max(0, d - 1)));
                }
                else {
                    x.append("0".repeat(Math.max(0, d-1)));
                }
            }
        }
        System.out.println(x);
    }
}
