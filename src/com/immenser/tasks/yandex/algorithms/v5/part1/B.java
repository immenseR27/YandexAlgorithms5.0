package com.immenser.tasks.yandex.algorithms.v5.part1;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int home1;  // счет первой команды дома
        int guest1; // счет первой команды в гостях
        int home2;  // счет второй команды в дома
        int guest2; // счет второй команды в гостях

        String s1[] = stdin.nextLine().split(":");
        String s2[] = stdin.nextLine().split(":");

        int place = stdin.nextInt();
        stdin.close();

        if (place == 1){    // если текущий матч первая команда играет в гостях
            home1 = Integer.parseInt(s1[0]);
            guest1 = Integer.parseInt(s2[0]);
            home2 = Integer.parseInt(s2[1]);
            guest2 = Integer.parseInt(s1[1]);
        }
        else {  // если текущий матч первая команда играет дома
            home1 = Integer.parseInt(s2[0]);
            guest1 = Integer.parseInt(s1[0]);
            home2 = Integer.parseInt(s1[1]);
            guest2 = Integer.parseInt(s2[1]);
        }

        int command1 = home1 + guest1;  // общий счет первой команды
        int command2 = home2 + guest2;  // общий счет второй команды

        int count = 0;  // кол-во мячей, которое необходимо забить

        if (command1 <= command2) { // если общий счет первой команды меньше или равен счету второй команды
            if (place == 1) {   // если текущий матч первая команда играет в гостях
                if (command2 - command1 <= guest2 - guest1) {    // если разница общих счетов меньше или равна разности счетов в гостях,
                    // то необходимо забить на один мяч больше этой разницы
                    count = command2 - command1 + 1;
                } else {    // если разница общих счетов больше, чем разность счетов в гостях, то достаточно сравнять разницу
                    count = command2 - command1;
                }
            }
            else    // если текущий матч первая команда играет дома
                if (guest1 <= guest2) { // если счет первой команды в гостях меньше или равен счету второй команды в гостях,
                    // то необходимо забить на один мяч больше этой разницы
                    count = command2 - command1 + 1;
                } else {    // если счет первой команды в гостях больше счета второй команды в гостях, то достаточно сравнять разницу
                    count = command2 - command1;
                }
        }
        System.out.println(count);
    }
}
