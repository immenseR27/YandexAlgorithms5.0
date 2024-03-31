package com.immenser.tasks.yandex.algorithms.v5.part3;

import java.io.*;
import java.util.HashMap;

public class C {
    private static int maxOne = 0;  // кол-во повторов элемента, который встречается чаще всего
    private static int maxSum = 0;  // наибольшая сумма повторов для пары соседних элементов
    private static boolean noNeighbours = true; // флаг отсутствия соседних элементов

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            int n = Integer.parseInt(reader.readLine());    // кол-во элементов в массиве

            HashMap<Integer, Integer> numbers = new HashMap<>();

            String[] nums = reader.readLine().split(" ");
            // заполняем HashMap значениями (key: число / value: кол-во повторов)
            for (int i = 0; i < n; i++) {
                int a = Integer.parseInt(nums[i]);
                if (numbers.containsKey(a)) {
                    numbers.replace(a, numbers.get(a) + 1);
                }
                else {
                    numbers.put(a, 1);
                }
            }

            for (Integer key : numbers.keySet()) {
                // находим элемент, который встречается чаще всего
                maxOne = Math.max(numbers.get(key), maxOne);
                // вычисляем количество повторов элемента с его соседями
                countSum(numbers, key, key - 1);
                countSum(numbers, key, key + 1);
            }

            int answer;
            // если соседние элементы отсутствуют, оставляем только один наиболее часто встречающийся
            if (noNeighbours) { answer = n - maxOne; }
            // иначе оставляем два соседних с наибольшей суммой повторов
            else { answer = n - maxSum; }

            System.out.print(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // функция для вычисления суммы повторов соседних элементов
    public static void countSum(HashMap<Integer, Integer> numbers, int originKey, int neighbourKey) {
        if (numbers.containsKey(neighbourKey)) {
            int value1 = numbers.get(originKey);
            int value2 = numbers.get(neighbourKey);
            if (value1 + value2 > maxSum) {
                maxSum = value1 + value2;
                noNeighbours = false;
            }
        }
    }
}