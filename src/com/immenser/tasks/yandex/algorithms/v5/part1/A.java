package com.immenser.tasks.yandex.algorithms.v5.part1;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);
        int P, V, Q, M;

        String s1[] = stdin.nextLine().split(" ");
        String s2[] = stdin.nextLine().split(" ");
        stdin.close();

        P = Integer.parseInt(s1[0]);
        V = Integer.parseInt(s1[1]);
        Q = Integer.parseInt(s2[0]);
        M = Integer.parseInt(s2[1]);

        int leftV, rightV, leftM, rightM;

        leftV = P - V;  //крайнее левое дерево Васи
        rightV = P + V; //крайнее правое дерево Васи
        leftM = Q - M;  //крайнее левое дерево Маши
        rightM = Q + M; //крайнее правое дерево Маши

        int count = 0; //количество покрашенных деревьев

        if (V != 0 && M != 0){
            if (rightV < leftM || rightM < leftV){  //диапазоны деревьев В. и М. не пересекаются
                count = V * 2 + M * 2 + 2;
            }
            if (leftM <= rightV && leftM > leftV && rightM > rightV){   //диапазоны деревьев В. и М. пересекаются, диапазон В. левее диапазона М.
                count = rightM - leftV + 1;
            }
            if (leftV <= rightM && leftV > leftM && rightV > rightM){   //диапазоны деревьев В. и М. пересекаются, диапазон М. левее диапазона В.
                count = rightV - leftM + 1;
            }
            if (leftV <= leftM && rightV >= rightM){    //диапазон деревьев В. содержит диапазон деревьев М.
                count = V * 2 + 1;
            }
            if (leftM <= leftV && rightM >= rightV){    //диапазон деревьев М. содержит диапазон деревьев В.
                count = M * 2 + 1;
            }
        }
        else
        if (V == 0 && M == 0){  //если В. и М. не могут отходить от своих деревьев
            count = (P == Q ? 1 : 2);   //если ведра В. и М. в одной точке, будет покрашено 1 дерево, если в разных - 2
        }
        else {
            if (V == 0) {   //если только В. не может отходить от своего дерева
                count = ((P < leftM) || (P > rightM) ? M * 2 + 2 : M * 2 + 1);  //дерево В. внутри диапазона деревьев М.
            }
            if (M == 0) {   //если только М. не может отходить от своего дерева
                count = ((Q < leftM) || (Q > rightM) ? V * 2 + 2 : V * 2 + 1);  //дерево М. внутри диапазона деревьев В.
            }
        }
        System.out.println(count);
    }
}
