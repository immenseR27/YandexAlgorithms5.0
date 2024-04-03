package com.immenser.tasks.yandex.algorithms.v5.part2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int N = stdin.nextInt(); // кол-во дней, на которые предсказана цена
        int K = stdin.nextInt(); // разница в днях между покупкой и продажей

        int maxProfit = 0; //максимальная прибыль
        LinkedList<Integer> prices = new LinkedList<>();    // список цен на K дней подряд

        for (int i = 0; i < (K < N ? K + 1 : N); i++){    // заполняем список первыми (K+1)-элементами
            prices.add(stdin.nextInt());
        }

        maxProfit = Math.max(maxProfit, findMaxProfit(prices)); // ищем max прибыль для первых K-элементов

        for (int i = 0; i < (K < N ? N - (K + 1) : 0); i++){  // для оставшихся (N-(K+1))-элементов
            // сдвигаем список на один элемент вправо и считаем разницу между ним и минимальным элементом в списке
            int price = stdin.nextInt();
            prices.remove(0);
            prices.add(price);
            maxProfit = Math.max(maxProfit, price - Collections.min(prices));
        }
        stdin.close();
        System.out.println(maxProfit);
    }

    public static int findMaxProfit (LinkedList<Integer> prices) {
        int maxProfit = 0;
        int min = prices.get(0);
        int max = prices.get(0);
        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i) > max){
                max = prices.get(i);
            }
            if (prices.get(i) < min){
                maxProfit = Math.max(maxProfit, max - min);
                min = prices.get(i);
                max = prices.get(i);
            }
        }
        return Math.max(maxProfit, max - min);
    }
}
