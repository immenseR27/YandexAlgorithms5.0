package com.immenser.tasks.yandex.algorithms.v5.part1;

import java.util.Scanner;

public class D {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        char[][] chessboard = new char[8][8];   // игровое поле
        boolean[][] beaten = new boolean[8][8]; // массив флагов бьющихся / не бьющихся полей

        for (int i = 0; i < 8; i++){    // заполнение игрового поля
            char[] line = stdin.nextLine().trim().toCharArray();
            System.arraycopy(line, 0, chessboard[i], 0, 8);
        }
        stdin.close();

        for (int i = 0; i < 8; i++){    // обход игрового поля
            for (int j = 0; j < 8; j++){
                if (chessboard[i][j] == 'R'){   // если фигура - ладья (R)
                    beaten[i][j] = true;
                    int m = j - 1;
                    while (m >= 0 && chessboard[i][m] == '*'){    // проход влево от R
                        beaten[i][m] = true;
                        m--;
                    }
                    m = j + 1;
                    while (m < 8 && chessboard[i][m] == '*'){    // проход вправо от R
                        beaten[i][m] = true;
                        m++;
                    }
                    int k = i - 1;
                    while (k >= 0 && chessboard[k][j] == '*'){    // проход вверх от R
                        beaten[k][j] = true;
                        k--;
                    }
                    k = i + 1;
                    while (k < 8 && chessboard[k][j] == '*'){    // проход вниз от R
                        beaten[k][j] = true;
                        k++;
                    }
                }
                if (chessboard[i][j] == 'B') {   // если фигура - слон (B)
                    beaten[i][j] = true;
                    int k = i - 1;
                    int m = j - 1;
                    while (k >= 0 && m >= 0 && chessboard[k][m] == '*'){    // проход влево-вверх от B
                        beaten[k][m] = true;
                        k--;
                        m--;
                    }
                    k = i - 1;
                    m = j + 1;
                    while (k >= 0 && m < 8 && chessboard[k][m] == '*'){    // проход вправо-вверх от B
                        beaten[k][m] = true;
                        k--;
                        m++;
                    }
                    k = i + 1;
                    m = j + 1;
                    while (k < 8 && m < 8 && chessboard[k][m] == '*'){    // проход вправо-вниз от B
                        beaten[k][m] = true;
                        k++;
                        m++;
                    }
                    k = i + 1;
                    m = j - 1;
                    while (k < 8 && m >= 0 && chessboard[k][m] == '*'){    // проход влево-вниз от B
                        beaten[k][m] = true;
                        k++;
                        m--;
                    }
                }
            }
        }

        int count = 0;  // кол-во пустых клеток, которые не бьются ни одной из фигур
        for (boolean flags[] : beaten){
            for (boolean flag : flags){
                if (!flag){
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
