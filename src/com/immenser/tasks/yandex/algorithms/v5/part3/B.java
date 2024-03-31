package com.immenser.tasks.yandex.algorithms.v5.part3;

import java.util.HashMap;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
       Scanner stdin = new Scanner(System.in);
       String str1 = stdin.nextLine();
       String str2 = stdin.nextLine();
       stdin.close();

       HashMap<Character, Integer> chars = new HashMap<>();
       String answer = "NO";

       char[] chars1 = str1.toCharArray();
       for (char c : chars1) {  // добавление символов строки 1 и их количеств в HashMap
           if (chars.containsKey(c)) {
               chars.replace(c, chars.get(c) + 1);
           }
           else {
               chars.put(c, 1);
           }
       }
        // сравнение строки 2 со строкой 1
       if (str1.length() == str2.length()) {    // если строки не равны, далее не проверяем
           char[] chars2 = str2.toCharArray();
           for (char c : chars2) {
               if (chars.get(c) != null) {  // если символ присутствует в chars, уменьшаем кол-во этого символа на 1
                   chars.replace(c, chars.get(c) - 1);
                   if (chars.get(c) == 0) { // если после уменьшения кол-во символов равно 0, удаляем этот ключ из chars
                       chars.remove(c);
                   }
               }
               else {   // если символ отсутствует в chars, выводим NO и выходим из программы
                   System.out.print(answer);
                   return;
               }
           }
           if (chars.size() == 0) { // если все символы из str2 нашлись в str1, и в str1 больше не осталось символов,
               answer = "YES";  // значит строки являются анаграммами
           }
       }
       System.out.print(answer);
    }
}
