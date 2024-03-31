package com.immenser.tasks.yandex.algorithms.v5.part4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class A {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int N = stdin.nextInt();

        TreeMap<Integer, Integer> counts = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            int a = stdin.nextInt();
            if (counts.containsKey(a)) {
                counts.replace(a, counts.get(a) + 1);
            }
            else {
                counts.put(a, 1);
            }
        }

        ArrayList<Integer> keys = new ArrayList<>(counts.size());

        int sum = 0;
        for (Integer key : counts.keySet()) {
            keys.add(key);
            sum += counts.get(key);
            counts.replace(key, sum);
        }

        StringBuilder strAnswer = new StringBuilder();

        int K = stdin.nextInt();
        for (int i = 0; i < K; i++) {
            int l = stdin.nextInt();
            int r = stdin.nextInt();

            int answer;
            if (l <= keys.get(keys.size() - 1) && r >= keys.get(0)) {
                int left = binarySearch(l, keys);
                int right = binarySearch(r, keys);

                if (keys.get(right) > r) {
                    right -= 1;
                }

                if (left != 0) {
                    if (keys.get(left) >= l) {
                        left -= 1;
                    }
                    answer = counts.get(keys.get(right)) - counts.get(keys.get(left));
                }
                else {
                    answer = counts.get(keys.get(right));
                }
            }
            else {
                answer = 0;
            }
            strAnswer.append(answer);
            strAnswer.append(" ");
        }
        stdin.close();
        System.out.print(strAnswer);
    }

    public static int binarySearch(int num, ArrayList<Integer> keys) {
        int l = 0;
        int r = keys.size() - 1;
        while (l != r) {
            int m = (l + r) / 2;
            if (keys.get(m) < num) {
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        return l;
    }
}