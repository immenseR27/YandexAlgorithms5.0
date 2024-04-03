package com.immenser.tasks.yandex.algorithms.v5.part2;

import java.util.Scanner;

public class C {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int N = stdin.nextInt(); // кол-во оставшихся веревок
        int maxL = 0;   // максимальная длина веревки
        int sum = 0;    // сумма длин оставшихся веревок

        for (int i = 0; i < N; i++){
            int l = stdin.nextInt();    // длина i-й веревки
            if (l > maxL){
                sum += maxL;
                maxL = l;
            }
            else {
                sum += l;
            }
        }
        stdin.close();
        System.out.println((maxL > sum ? maxL - sum : sum + maxL));

    }
}
