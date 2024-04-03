package com.immenser.tasks.yandex.algorithms.v5.part2;

import java.util.Scanner;

public class H {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt(); // количество рас
        int m = stdin.nextInt(); // количество классов

        int[][] forces = new int[n][m];
        Hero strongestHero1 = new Hero(0, 0, 0);    // самый сильный персонаж
        Hero strongestHero2 = new Hero(0, 0, 0);    // второй по силе персонаж

        // Записываем таблицу и ищем двух персонажей с максимальной силой
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int a = stdin.nextInt(); // сила персонажа i-й расы j-го класса
                forces[i][j] = a;
                if (a >= strongestHero1.getForce()) {
                    strongestHero2 = new Hero(strongestHero1);
                    strongestHero1 = new Hero(i, j, a);
                }
                else
                if (a > strongestHero2.getForce()){
                    strongestHero2 = new Hero(i, j, a);
                }
            }
        }
        stdin.close();

        int row;
        int column;

        int race1 = strongestHero1.getRaceNumber();
        int class1 = strongestHero1.getClassNumber();

        int race2 = strongestHero2.getRaceNumber();
        int class2 = strongestHero2.getClassNumber();


        Hero strongestHero3 = new Hero(0, 0, 0);

        // Если у strongestHero1 и strongestHero2 совпали строка или столбец,
        // ищем персонажа с максимальной силой в оставшейся части таблицы (strongestHero3)
        if (race1 == race2 ^ class1 == class2){
            if (race1 == race2){ // если совпала строка, вычеркиваем эту строку и столбец, в котором strongestHero3
                row = race1;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (i != row){
                            if (forces[i][j] > strongestHero3.getForce()){
                                strongestHero3 = new Hero(i, j, forces[i][j]);
                            }
                        }
                    }
                }
                column = strongestHero3.getClassNumber();
            }
            else {  // если совпал столбец, вычеркиваем этот столбец и строку, в которой strongestHero3
                column = class1;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (j != column){
                            if (forces[i][j] > strongestHero3.getForce()){
                                strongestHero3 = new Hero(i, j, forces[i][j]);
                            }
                        }
                    }
                }
                row = strongestHero3.getRaceNumber();
            }
        }
        // Если ни строка, ни столбец не совпали, для каждого из трех вариантов:
        // 1. Строка и столбец strongestHero1
        // 2. Строка strongestHero1 и столбец strongestHero2
        // 3. Строка strongestHero2 и столбец strongestHero1
        // ищем максимальное значение силы, которое останется в таблице после вычеркивания
        else {
            // Пусть A - строка strongestHero1.getRaceNumber(), B - столбец strongestHero1.getClassNumber(),
            // C - строка strongestHero2.getRaceNumber(), D - столбец strongestHero2.getClassNumber().
            // Тогда strongestHeroXY - самый сильный персонаж в строке/столбце X
            // за исключением пересечения со столбцом/строкой Y
            Hero strongestHeroCD = new Hero(0, 0, 0);
            Hero strongestHeroBA = new Hero(0, 0, 0);
            Hero strongestHeroCB = new Hero(0, 0, 0);
            Hero strongestHeroDA = new Hero(0, 0, 0);
            Hero strongestHeroAB = new Hero(0, 0, 0);
            Hero strongestHeroDC = new Hero(0, 0, 0);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // максимумы в строке/столбце X за исключением пересечения со столбцом/строкой Y
                    if (i == race1 || i == race2 || j == class1 || j == class2) {
                        if (i == race2 && j != class2) {
                            if (forces[i][j] > strongestHeroCD.getForce()) {
                                strongestHeroCD = new Hero(i, j, forces[i][j]);
                            }
                        }
                        if (j == class1 && i != race1) {
                            if (forces[i][j] > strongestHeroBA.getForce()) {
                                strongestHeroBA = new Hero(i, j, forces[i][j]);
                            }
                        }
                        if (i == race2 && j != class1) {
                            if (forces[i][j] > strongestHeroCB.getForce()) {
                                strongestHeroCB = new Hero(i, j, forces[i][j]);
                            }
                        }
                        if (j == class2 && i != race1) {
                            if (forces[i][j] > strongestHeroDA.getForce()) {
                                strongestHeroDA = new Hero(i, j, forces[i][j]);
                            }
                        }
                        if (i == race1 && j != class1) {
                            if (forces[i][j] > strongestHeroAB.getForce()) {
                                strongestHeroAB = new Hero(i, j, forces[i][j]);
                            }
                        }
                        if (j == class2 && i != race2) {
                            if (forces[i][j] > strongestHeroDC.getForce()) {
                                strongestHeroDC = new Hero(i, j, forces[i][j]);
                            }
                        }
                    }
                    else {  // максимум для оставшейся части
                        if (forces[i][j] > strongestHero3.getForce()){
                            strongestHero3 = new Hero(i, j, forces[i][j]);
                        }
                    }
                }
            }
            // Пусть xy - максимальная сила персонажа, если вычеркнуть строку x и столбец y
            int ad = Math.max(strongestHeroCD.getForce(), strongestHeroBA.getForce());
            ad = Math.max(ad, strongestHero3.getForce());

            int ab = Math.max(strongestHeroCB.getForce(), strongestHeroDA.getForce());
            ab = Math.max(ab, strongestHero3.getForce());

            int cb = Math.max(strongestHeroAB.getForce(), strongestHeroDC.getForce());
            cb = Math.max(cb, strongestHero3.getForce());

            // Ищем вариант, в котором максимальная сила персонажа минимальна
            if (ad <= ab && ad <= cb){
                row = race1;
                column = class2;
            }
            else
            if (ab <= ad && ab <= cb){
                row = race1;
                column = class1;
            }
            else {
                row = race2;
                column = class1;
            }
        }

        System.out.printf("%d %d", row + 1, column + 1);
    }
}

class Hero{
    private int raceNumber;
    private int classNumber;
    private int force;

    public Hero(int raceNumber, int classNumber, int force) {
        this.raceNumber = raceNumber;
        this.classNumber = classNumber;
        this.force = force;
    }

    public Hero(Hero hero){
        this.raceNumber = hero.raceNumber;
        this.classNumber = hero.classNumber;
        this.force = hero.force;
    }

    public int getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(int raceNumber) {
        this.raceNumber = raceNumber;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }
}
