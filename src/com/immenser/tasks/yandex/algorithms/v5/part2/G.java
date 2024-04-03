package com.immenser.tasks.yandex.algorithms.v5.part2;

import java.util.Scanner;

public class G {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int t = stdin.nextInt(); // количество массивов
        for (int i = 0; i < t; i++){
            int n = stdin.nextInt(); // длина массива t
            int count = 0;
            int length = 0;
            int min = n + 1;
            StringBuilder lengths = new StringBuilder();
            for (int j = 0; j < n; j++){
                int a = stdin.nextInt(); // j-й элемент массива t
                if (a < min){
                    min = a;
                }
                if (a >= length + 1 && min >= length + 1){
                    length ++;
                }
                else {
                    lengths.append(length);
                    lengths.append(" ");
                    count++;
                    min = a;
                    length = 1;
                }
                if (j == n - 1){
                    lengths.append(length);
                    count++;
                }
            }
            stdin.close();
            System.out.println(count);
            System.out.println(lengths);
        }
    }
}
