package com.immenser.tasks.yandex.algorithms.v5.part2;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int N = stdin.nextInt(); // кол-во закрашенных клеток

        int xLeftMin = stdin.nextInt(); // крайняя левая точка со значением координаты x клетки 1
        int xRightMax = xLeftMin;   // крайняя правая точка со значением координаты x клетки 1
        int yTopMin = stdin.nextInt();  // крайняя верхняя точка со значением координаты y клетки 1
        int yBottomMax = yTopMin;   // крайняя нижняя точка со значением координаты y клетки 1

        for (int i = 1; i < N; i++){
            int x = stdin.nextInt();    // координата x клетки i
            int y = stdin.nextInt();    // координата y клетки i

            if (x < xLeftMin) {
                xLeftMin = x;
            }
            if (x > xRightMax) {
                xRightMax = x;
            }
            if (y < yBottomMax) {
                yBottomMax = y;
            }
            if (y > yTopMin) {
                yTopMin = y;
            }
        }
        stdin.close();
        System.out.printf("%d %d %d %d", xLeftMin, yBottomMax, xRightMax, yTopMin);
    }
}
