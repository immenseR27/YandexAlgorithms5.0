package com.immenser.tasks.yandex.algorithms.v5.part3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class F {
    public static void main(String[] args) {
        StringBuilder answer = new StringBuilder();
        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String[] dictionary = reader.readLine().split(" ");
            // заполнение словаря
            HashSet<String> dictSet = new HashSet<>(Arrays.asList(dictionary));

            String[] words = reader.readLine().split(" ");
            // обработка текста
            for (String word : words) {
                char[] letters = word.toCharArray();
                String shortWord = "";
                // начиная с одной буквы проверяем наличие подстроки в словаре
                // в случае отсутствия добавляем к подстроке еще одну букву
                for (int i = 0; i < letters.length; i++) {
                    shortWord = shortWord + letters[i];
                    // если подстрока есть в словаре, добавляем ее к ответу
                    if (dictSet.contains(shortWord)) {
                        answer.append(shortWord);
                        answer.append(" ");
                        break;
                    }
                    else
                        // если ни одной подстроки не оказалось в словаре, добавляем к ответу слово целиком
                        if (i == letters.length - 1) {
                            answer.append(shortWord);
                            answer.append(" ");
                        }
                }
            }

            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
