package com.immenser.tasks.yandex.algorithms.v5.part3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class G {
    public static void main(String[] args) {

        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            int N = Integer.parseInt(reader.readLine());    // количество точек

            HashSet<Point> points = new HashSet<>();
            // сохраняем точки в множестве points
            for (int i = 0; i < N; i++) {
               String[] xy = reader.readLine().split(" ");
               points.add(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
            }

            HashSet<Point> copy = new HashSet<>(points);    // копия points для удаления обработанных точек

            int answerCount = 3;    // количество точек, которые необходимо добавить

            HashSet<Point> answerPoints = new HashSet<>();  // для хранения точек, которые надо добавить

            answerCount = searchSquare(points, copy, answerCount, answerPoints, null);

            System.out.println(answerCount);

            for (Point point : answerPoints) {
                System.out.println(point);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // функция находит минимальное количество точек, которое необходимо добавить, чтобы получить квадрат
    public static int searchSquare(HashSet<Point> points, HashSet<Point> copy, int answerCount, HashSet<Point> answerPoints, Point oneAdditionalPoint) {
        if (copy.size() != 1) { // если в текущей копии больше одного элемента
            Iterator<Point> iterator = copy.iterator();
            Point point1 = iterator.next(); // берем первую точку множества
            while (iterator.hasNext()) {    // и последовательно выполняем проверку в паре со всеми остальными точками
                Point point2 = iterator.next();
                Pair[] pairs = findPoints(point1, point2);
                Pair pair1 = pairs[0];
                Pair pair2 = pairs[1];
                // если для текущей пары точек в исходном множестве найдется другая пара точек, с которыми они образуют квадрат
                if (points.contains(pair1.point3()) && points.contains(pair1.point4()) || points.contains(pair2.point3()) && points.contains(pair2.point4())) {
                    return  0;  // то ничего добавлять не нужно
                } else if (oneAdditionalPoint == null   // если найдется только одна точка из двух, сохраняем ее
                        && (points.contains(pair1.point3()) || points.contains(pair1.point4())
                        || points.contains(pair2.point3()) || points.contains(pair2.point4()))) {
                    if (points.contains(pair1.point3()) || points.contains(pair1.point4())) {
                        oneAdditionalPoint = getOneAdditionalPoint(pair1.point3(), pair1.point4(), points, answerPoints);
                    } else {
                        oneAdditionalPoint = getOneAdditionalPoint(pair2.point3(), pair2.point4(), points, answerPoints);
                    }
                    answerCount = 1;    // минимальное количество точек становится равным 1
                }
            }
            // если для текущей точки не нашлось пары, такой чтобы во множестве были две или одна точка, с которыми они образуют квадрат
            // удаляем текущую точку из текущей копии, чтобы не проверять другие точки с ней в паре повторно
            copy.remove(point1);
            // и вызываем эту же функцию для следующего элемента
            return searchSquare(points, copy, answerCount, answerPoints, oneAdditionalPoint);
        }
        else    // если в копии всего 1 элемент, значит в исходном множестве была всего одна точка или мы проверили все пары точек
            if (oneAdditionalPoint == null) {   // если при этом не встретилось ни одной подходящей тройки
                Iterator<Point> iterator = points.iterator();
                Point point1 = iterator.next();
                Point point2;
                // и в исходном множестве была всего одна точка, то добавляем к ней три любые точки, удовлетворяющие условию
                if (points.size() == 1) {
                    point2 = new Point(point1.x() + 1, point1.y());
                    answerCount = 3;
                }
                // а если мы проверили все пары точек и не нашли подходящей тройки, добавляем к любой паре
                // (например, к первой и второй точкам) любые две точки, удовлетворяющие условию
                else {
                    point2 = iterator.next();
                    answerCount = 2;
                }
                Pair[] pairs = findPoints(point1, point2);
                answerPoints.addAll(Arrays.asList(pairs[0].point3(), pairs[0].point4()));
            }
            else {  // если же в исходном множестве встретилась хотя бы одна подходящая тройка,
                // добавляем любой из них (например, к первой встретившейся) одну недостающую точку
                answerPoints.add(oneAdditionalPoint);
            }

        return answerCount;
    }

    // функция находит для двух точек две пары точек, с каждой из которых они образуют квадрат
    // при этом отрезок, образованный исходными двумя точками, рассматривается как сторона квадрата (не диагональ)
    public static Pair[] findPoints(Point point1, Point point2) {
        int x1 = point1.x();
        int y1 = point1.y();
        int x2 = point2.x();
        int y2 = point2.y();
        // пара 1
        Point point3 = new Point(x1 + (y2 - y1), y1 - (x2 - x1));
        Point point4 = new Point(x2 + (y2 - y1), y2 - (x2 - x1));
        Pair pair1 = new Pair(point3, point4);
        // пара 2
        Point point5 = new Point(x1 - (y2 - y1), y1 + (x2 - x1));
        Point point6 = new Point(x2 - (y2 - y1), y2 + (x2 - x1));
        Pair pair2 = new Pair(point5, point6);
        return new Pair[]{pair1, pair2};
    }

    // функция возвращает недостающую точку для тройки
    public static Point getOneAdditionalPoint(Point point3, Point point4, HashSet<Point> points, HashSet<Point> answerPoints) {
        if (points.contains(point3)) {
            return point4;
        }
        else {
            return point3;
        }
    }
}

record Point (int x, int y) {
    @Override
    public String toString() {
        return x + " " + y;
    }
};

record Pair (Point point3, Point point4) {};
