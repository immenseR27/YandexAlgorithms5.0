package com.immenser.tasks.yandex.algorithms.v5.part4;

import java.util.ArrayList;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt(); // кол-во полков
        int m = stdin.nextInt(); // кол-во вылазок

        ArrayList<Long> counts = new ArrayList<>(n);
        counts.add(0L);
        long sum = 0;

        for (int i = 0; i < n; i++) {
            int a = stdin.nextInt(); // число орков в i-м полке
            sum += a;
            counts.add(sum);
        }

        for (int i = 0; i < m; i++) {
            int k = stdin.nextInt();    // кол-во полков i-й вылазки
            long s = stdin.nextLong();  // кол-во орков i-й вылазки
            int answer;
            answer = binarySearch(k, s, counts);
            if (counts.get(answer + k - 1) - counts.get(answer - 1) != s) {
                answer = -1;
            }
            System.out.println(answer);
        }

        stdin.close();
    }

    public static int binarySearch(int k, long s, ArrayList<Long> counts) {
        int l = 1;
        int r = counts.size() - 1;
        while (l != r) {
            int m = (l + r) / 2;
            if (m - k >= 0 && counts.get(m) - counts.get(m - k) >= s) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }
        return l - (k - 1);
    }
}
