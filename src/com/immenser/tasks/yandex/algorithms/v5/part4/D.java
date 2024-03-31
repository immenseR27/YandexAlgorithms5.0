package com.immenser.tasks.yandex.algorithms.v5.part4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int w = stdin.nextInt(); // ширина рулона
        int n = stdin.nextInt(); //  количество слов в первой части
        int m = stdin.nextInt(); //  количество слов во второй части

        ArrayList<Integer> part1 = createWordsLengthsList(stdin, n);
        ArrayList<Integer> part2 = createWordsLengthsList(stdin, m);
        stdin.close();
        int vLine1 = binarySearch1(w, part1, part2);
        int vLine2 = binarySearch2(w, part1, part2);
        int variant1 = Math.max(countLines(vLine1, part1), countLines(w - vLine1, part2));
        int variant2 = Math.max(countLines(vLine2, part1), countLines(w - vLine2, part2));
        System.out.print(Math.min(variant1, variant2));
    }

    public static ArrayList<Integer> createWordsLengthsList(Scanner stdin, int n) {
        ArrayList<Integer> wordsLengths = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int wordLength = stdin.nextInt();
            wordsLengths.add(wordLength);
        }
        return wordsLengths;
    }

    public static int binarySearch1(int w, ArrayList<Integer> part1, ArrayList<Integer> part2) {
        int l = Collections.max(part1);
        int r = w - Collections.max(part2);
        while (l != r) {
            int m = (l + r) / 2;
            if (countLines(m, part1) <= countLines(w - m, part2)) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }
        return l;
    }

    public static int binarySearch2(int w, ArrayList<Integer> part1, ArrayList<Integer> part2) {
        int l = Collections.max(part1);
        int r = w - Collections.max(part2);
        while (l != r) {
            int m = (l + r + 1) / 2;
            if (countLines(m, part1) >= countLines(w - m, part2)) {
                l = m;
            }
            else {
                r = m - 1;
            }
        }
        return l;
    }

    public static int countLines(int blockWidth, ArrayList<Integer> wordsLengths) {
        int w = blockWidth;
        int strCount = 1;
        w = w - wordsLengths.get(0);
        for (int i = 1; i < wordsLengths.size(); i++) {
            int wordLength = wordsLengths.get(i);
            if (w >= wordLength + 1) {
                w -= wordLength + 1;
            }
            else {
                strCount += 1;
                w = blockWidth - wordLength;
            }
        }
        return strCount;
    }
}