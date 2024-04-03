package com.immenser.tasks.yandex.algorithms.v5.part2;

import java.util.LinkedList;
import java.util.Scanner;

public class E {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt(); // количество ягод

        LinkedList<Integer> positiveNums = new LinkedList<>();
        LinkedList<Integer> negativeNums = new LinkedList<>();

        Berry maxPositiveB = null;
        Berry maxNegativeA = null;

        long maxH = 0;

        for (int i = 0; i < n; i++) {
            int a = stdin.nextInt();    // подъем за день от i-й ягоды
            int b = stdin.nextInt();    // спуск за ночь от i-й ягоды
            // поиск ягоды с наибольшим ночным спадом среди ягод с положительным суточным приростом
            // и заполнение списка ягод с положительным суточным приростом
            if (a >= b) {
                if (maxPositiveB == null || b > maxPositiveB.getB()) {
                    if (maxPositiveB != null) {
                        positiveNums.add(maxPositiveB.getNum());
                        maxH += maxPositiveB.getDiff();
                    }
                    maxPositiveB = new Berry(a, b, i + 1);
                }
                else {
                    maxH += a - b;
                    positiveNums.add(i + 1);
                }
            }
            // поиск ягоды с наибольшим дневным приростом среди ягод с отрицательным суточным приростом
            // и заполнение списка ягод с отрицательным суточным приростом
            else {
                if (maxNegativeA == null || a > maxNegativeA.getA()) {
                    if (maxNegativeA != null) {
                        negativeNums.add(maxNegativeA.getNum());
                    }
                    maxNegativeA = new Berry(a, b, i + 1);
                }
                else {
                    negativeNums.add(i + 1);
                }
            }
        }
        stdin.close();

        // для ягод с положительным суточным приростом ягода с наибольшим ночным спадом в конце списка
        if (maxPositiveB != null){
            positiveNums.add(maxPositiveB.getNum());
            maxH = Math.max(maxH + maxPositiveB.getA(), maxH + maxPositiveB.getDiff() + (maxNegativeA == null ? 0 : maxNegativeA.getA()));
        }
        else{   // когда все ягоды с отрицательным суточным приростом
            maxH = (maxNegativeA == null ? 0 : maxNegativeA.getA());
        }
        // для ягод с отрицательным суточным приростом ягода с наибольшим дневным приростом в начале списка
        if (maxNegativeA != null) {
            negativeNums.add(0, maxNegativeA.getNum());
        }

        // максимальная высота
        System.out.println(maxH);

        StringBuilder order = new StringBuilder();
        // положительный суточный прирост
        for (Integer positiveNum : positiveNums) {
            order.append(positiveNum);
            order.append(" ");
        }
        // отрицательный суточный прирост
        for (Integer negativeNum : negativeNums) {
            order.append(negativeNum);
            order.append(" ");
        }
        System.out.print(order);
    }
}

class Berry{
    private int a;
    private int b;
    private int diff;
    private int num;

    public Berry(int a, int b, int num) {
        this.a = a;
        this.b = b;
        this.diff = a - b;
        this.num = num;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
        this.diff = a - this.b;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
        this.diff = this.a - b;
    }

    public int getDiff() {
        return diff;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
