package com.immenser.tasks.yandex.algorithms.v5.part1;

import java.util.Scanner;

public class F {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();  // кол-во чисел
        boolean even; // флаг четности
        StringBuilder result = new StringBuilder(); // строка знаков

        int firstNum  = stdin.nextInt();  // первый элемент во вхождении
        even = (firstNum % 2 == 0);

        for (int i = 0; i < n-1; i++){
            int x = stdin.nextInt();
            if (x % 2 != 0 && !even){
                result.append("x");
            }
            else {
                result.append("+");
                if (!(x % 2 == 0 && even)){
                    even = false;
                }
            }
        }
        stdin.close();
        System.out.println(result);
    }
}
