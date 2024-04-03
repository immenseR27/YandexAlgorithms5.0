package com.immenser.tasks.yandex.algorithms.v5.part2;

import java.util.Scanner;

public class D {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int N = stdin.nextInt(); // кол-во выпиленных клеток
        int[][] chessboard = new int[10][10];
        // -1 - рамка, 0 - не выпилена, 1 - выпилена
        // заполнение рамки
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (i == 0 || j == 0 || i == 9 || j == 9){
                    chessboard[i][j] = -1;
                }
            }
        }
        // заполнение выпиленных клеток
        for (int i = 1; i < N + 1; i++){
            int m = stdin.nextInt();    // номер строки i-й клетки
            int n = stdin.nextInt();    // номер столбца i-й клетки
            chessboard[m][n] = 1;
        }
        stdin.close();

        int p = 0; // периметр фигуры

        int[] mShifts = {0, 0, -1, 1};
        int[] nShifts = {-1, 1, 0, 0};
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                if (chessboard[i][j] == 1){
                    int neighbors = 0;
                    for (int k = 0; k < 4; k++) {
                        if (chessboard[i + mShifts[k]][j + nShifts[k]] == 1){
                            neighbors ++;
                        }
                    }
                    p += (4 - neighbors);
                }
            }
        }
        System.out.println(p);
    }
}
