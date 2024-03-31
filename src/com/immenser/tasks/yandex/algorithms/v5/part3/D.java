package com.immenser.tasks.yandex.algorithms.v5.part3;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class D {
    public static void main(String[] args) {
        String answer = "NO";
        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String[] nk = reader.readLine().split(" ");
            int n = Integer.parseInt(nk[0]);    // кол-во чисел
            int k = Integer.parseInt(nk[1]);    // расстояние между повторами

            LinkedHashSet<String> kNumsSet = new LinkedHashSet<>();

            String[] nums = reader.readLine().split(" ");
            // для первых k (n, если k > n) элементов:
            for (int i = 0; i < Math.min(k, n); i++) {
                // проверяем наличие повторов среди элементов, при наличии повторов выводим "YES"
                if (checkDuplicates(nums[i], kNumsSet)) { return; }
                // если повторов нет, добавляем элемент в kNumsSet
                else { kNumsSet.add(nums[i]); }
            }
            // для оставшихся элементов
            for (int i = Math.min(k, n); i < n; i++) {
                // проверяем наличие элемента в kNumsSet, при наличии выводим "YES"
                if (checkDuplicates(nums[i], kNumsSet)) { return; }
                // иначе удаляем крайний из kNumsSet слева элемент и добавляем туда текущий элемент
                else {
                    Iterator<String> iterator = kNumsSet.iterator();
                    kNumsSet.remove(iterator.next());
                    kNumsSet.add(nums[i]);
                }
            }
            System.out.print(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkDuplicates(String num, LinkedHashSet<String> kNumsSet) {
        if (kNumsSet.contains(num)) {
            System.out.print("YES");
            return true;
        }
        return false;
    }
}
