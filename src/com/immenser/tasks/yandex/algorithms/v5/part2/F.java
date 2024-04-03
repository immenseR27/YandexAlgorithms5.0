package com.immenser.tasks.yandex.algorithms.v5.part2;

import java.util.ArrayList;
import java.util.Scanner;

public class F {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt(); // количество секторов колеса
        ArrayList<Integer> sectors = new ArrayList<>(n);
        // сохраняем числа, записанные в секторах колеса в sectors
        for (int i = 0; i < n; i++) {
            sectors.add(stdin.nextInt());
        }
        int a = stdin.nextInt();    // нижняя граница начальной угловой скорости
        int b = stdin.nextInt();    // верхняя граница начальной угловой скорости
        int k = stdin.nextInt();    // снижение скорости при переходе между секторами
        stdin.close();

        int max = -1;

        boolean round = (b - a) / (n * k) >= 1;

        b = (round ? a + n * k : a + ((b - a) / k) * k);

        for (int i = a; i <= b; i += k){
            if (i / k < 1 || i / k == 1 && i % k == 0) {
                max = sectors.get(0);
            }
            else {
                if (i % k == 0) {
                    max = Math.max((sectors.get((i / k - 1) % n)), max);
                    max = Math.max(sectors.get(((n - (i / k - 1) % n) % n)), max);
                } else {
                    max = Math.max(sectors.get((i / k) % n), max);
                    max = Math.max(sectors.get(((n - (i / k) % n) % n)), max);
                }
            }
        }
        System.out.print(max);
    }
}
