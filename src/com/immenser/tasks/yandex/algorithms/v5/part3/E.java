package com.immenser.tasks.yandex.algorithms.v5.part3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class E {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            TreeMap<Integer, Integer> numsCounts = new TreeMap<>();  // key - элемент, value - кол-во строк, где он есть
            TreeMap<Integer, Boolean> numsAllows = new TreeMap<>();  // key - элемент, value - флаг разрешения инкремента

            for (int i = 0; i < 3; i++){
                // при переходе на новую строку счетчики всех элементов снова можно увеличивать
                for (Integer key : numsAllows.keySet()) {
                    numsAllows.replace(key, true);
                }
                reader.readLine();    // пропускаем строку с n (кол-во элементов в i-й строке)
                String[] nums = reader.readLine().split(" ");
                for (String str : nums) {   // считаем для каждого элемента его количество
                    int num = Integer.parseInt(str);
                    // если элемент уже есть в numsCounts, проверяем, увеличивался ли его счетчик в numsCounts i-й строкой
                    if (numsCounts.containsKey(num) && numsAllows.get(num)) {   // если нет - увеличиваем
                        numsCounts.replace(num, numsCounts.get(num) + 1);
                    }
                    else {  // если элемента нет в numsCounts - добавляем
                        numsCounts.put(num, 1);
                        numsAllows.put(num, false);
                    }
                }
            }

            StringBuilder answer = new StringBuilder();
            // если элемент встречался минимум в двух строках, добавляем его к ответу
            for (Integer key : numsCounts.keySet()) {
                if (numsCounts.get(key) >= 2) {
                    answer.append(key);
                    answer.append(" ");
                }
            }

            System.out.print(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}