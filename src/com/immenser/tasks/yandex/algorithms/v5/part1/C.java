package com.immenser.tasks.yandex.algorithms.v5.part1;

import java.util.Scanner;

public class C {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt(); //кол-во строк
        long count = 0;  // минимальное количество нажатий

        for (int i=0; i<n; i++){
            int num = stdin.nextInt();  //кол-во пробелов в i-й строке
            switch (num % 4) {
                case 0 -> count = count + num / 4;
                case 1 -> count = count + num / 4 + 1;
                case 2, 3 -> count = count + num / 4 + 2;
            }
        }
        stdin.close();
        System.out.println(count);
    }
}
