package com.immenser.tasks.yandex.algorithms.v5.part4;

import java.io.*;
import java.util.*;

public class F {
    public static void main(String[] args) throws IOException {

        File file = new File("input.txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);

        String[] whn = reader.readLine().split(" ");
        int w = Integer.parseInt(whn[0]); // ширина площади
        int h = Integer.parseInt(whn[1]); // высота площади
        int n = Integer.parseInt(whn[2]); // кол-во потрескавшихся плиток

        int xMin = w;
        int xMax = 0;
        TreeMap<Integer, ArrayList<Tile>> tiles = new TreeMap<>();  // key: координата x, value: список плиток с таким же значением координаты x
        // заполнение TreeMap и поиск минимального и максимального значений x
        for (int i = 0; i < n; i++) {
            String[] xy = reader.readLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            xMin = Math.min(x, xMin);
            xMax = Math.max(x, xMax);
            Tile tile = new Tile(x, y);
            if (!tiles.containsKey(x)) {
                tiles.put(x, new ArrayList<>());
            }
            tiles.get(x).add(tile);
        }

        // в columns будет записана информация о столбцах, в которых есть сломанные плитки
        ArrayList<Column> columns = new ArrayList<>(tiles.size());

        // заполнение columns префиксными и постфиксными минимумами и максимумами по y
        prefixPostfixMinMax(tiles, tiles.keySet(), columns, h);
        prefixPostfixMinMax(tiles, tiles.descendingKeySet(), columns, h);

        int finalXMin = xMin;
        int finalXMax = xMax;
        Expression checkCovering = (m) -> checkCovering(m, h, columns, finalXMin, finalXMax);

        int answer = binarySearch(0, Math.min(w, h), checkCovering);
        System.out.println(answer);
    }

    // функция проходит в заданном направлении по отсортированным по координате x сломанным плиткам
    // и заполняет columns информацией о столбцах
    public static void prefixPostfixMinMax(Map<Integer, ArrayList<Tile>> tiles, Set<Integer> keySet, ArrayList<Column> columns, int h) {
        int yMin = h;
        int yMax = 0;
        int index = 0;
        // Comparator для поиска минимального и максимального y в каждом столбце
        Comparator<Tile> yComparator = new YComparator();
        for (Integer x : keySet) {
            yMin = Math.min(yMin, Collections.min(tiles.get(x), yComparator).y());
            yMax = Math.max(yMax, Collections.max(tiles.get(x), yComparator).y());
            // в прямом направлении
            if (columns.size() == index) {
                columns.add(new Column(x, yMin, yMax));
            }
            // в обратном направлении
            else {
                Column column = columns.get(columns.size() - index - 1);
                column.setPostMinY(yMin);
                column.setPostMaxY(yMax);
            }
            index++;
        }
    }

    // бинарный поиск
    public static int binarySearch(int l, int r, Expression expression) {
        while (l != r) {
            int m = (l + r) / 2;
            if (expression.check(m)) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }
        return l;
    }

    // функция проверяет, возможно ли перекрыть все сломанные плитки при ширине дорожек trackWidth
    public static boolean checkCovering(int trackWidth, int h, ArrayList<Column> columns, int xMin, int xMax) {
        int leftMinY = h;
        int leftMaxY = 0;
        int rightMinY = h;
        int rightMaxY = 0;
        // проходим по всем столбцам, в которых есть сломанные плитки
        for (int i = 0; i < columns.size(); i++) {
            // если окно не выходит за пределы последнего столбца со сломанной плиткой
            if (columns.get(i).getX() + trackWidth - 1 <= xMax) {
                if (columns.get(i).getX() > xMin) { // Если начало окна не совпадает с первым столбцом со сломанной плиткой,
                    // то мин. и макс. y сломанных плиток слева - префиксные мин. и макс. y предыдущего столбца со сломанной плиткой,
                    // Иначе - левая часть не учитывается
                    leftMinY = columns.get(i - 1).getPrefMinY();
                    leftMaxY = columns.get(i - 1).getPrefMaxY();
                }
                // Если расстояние между правой границей окна и последним столбцом со сломанной плиткой больше нуля,
                // то мин. и макс. y сломанных плиток справа - постфиксные мин. и макс. y следующего за окном столбца со сломанной плиткой.
                // Иначе - правая часть не учитывается
                if (columns.get(i).getX() + trackWidth - 1 <= xMax) {
                    int finalI = i;
                    Expression checkNextX = (m) -> columns.get(m).getX() >= columns.get(finalI).getX() + trackWidth;
                    int j = binarySearch(i + 1, columns.size() - 1, checkNextX);
                    rightMinY = columns.get(j).getPostMinY();
                    rightMaxY = columns.get(j).getPostMaxY();
                }
            }
            // Если же в какой-то момент окно от текущей сломанной плитки выходит за пределы последнего столбца со сломанной плиткой,
            // окно сдвигается влево таким образом, чтобы правая его граница совпадала с последним столбцом со сломанной плиткой.
            // При этом мин. и макс. y сломанных плиток слева - префиксные мин. и макс. y предыдущего окну столбца со сломанной плиткой,
            // правая часть не учитывается
            else {
                int j = i;
                while (columns.get(j).getX() > xMax - trackWidth) {
                    j--;
                }
                leftMinY = columns.get(j).getPrefMinY();
                leftMaxY = columns.get(j).getPrefMaxY();

                return leftMaxY - leftMinY < trackWidth;
            }
            // максимальное расстояние по y между оставшимися сломанными плитками равно разнице между максимумом
            // среди левого и правого максимумов и минимумом среди левого и правого минимумов
            int yMaxDist = Math.max(leftMaxY, rightMaxY) - Math.min(leftMinY, rightMinY);
            // если это расстояние меньше заданной ширины дорожки, то есть способ перекрыть все сломанные плитки
            if (yMaxDist < trackWidth) {
                return true;
            }
        }
        // если ни для одного варианта размещения окна при заданной ширине дорожки не выполнилось yMaxDist < trackWidth,
        // то перекрыть все сломанные плитки такими дорожками невозможно
        return false;
    }
}
interface Expression{
    boolean check(int m);
}

record Tile (int x, int y) {}

class Column {
    private int x;
    private int prefMinY;   // префиксный минимум по y
    private int prefMaxY;   // префиксный максимум по y
    private int postMinY;    // постфиксный минимум по y
    private int postMaxY;    // постфиксный максимум по y

    public Column(int x, int prefMinY, int prefMaxY) {
        this.x = x;
        this.prefMinY = prefMinY;
        this.prefMaxY = prefMaxY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getPrefMinY() {
        return prefMinY;
    }

    public void setPrefMinY(int prefMinY) {
        this.prefMinY = prefMinY;
    }

    public int getPrefMaxY() {
        return prefMaxY;
    }

    public void setPrefMaxY(int prefMaxY) {
        this.prefMaxY = prefMaxY;
    }

    public int getPostMinY() {
        return postMinY;
    }

    public void setPostMinY(int postMinY) {
        this.postMinY = postMinY;
    }

    public int getPostMaxY() {
        return postMaxY;
    }

    public void setPostMaxY(int postMaxY) {
        this.postMaxY = postMaxY;
    }
}

class YComparator implements Comparator<Tile> {

    @Override
    public int compare(Tile o1, Tile o2) {
        return o1.y() - o2.y();
    }
}