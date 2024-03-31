package com.immenser.tasks.yandex.algorithms.v5.part3;

import java.io.*;
import java.util.TreeMap;

public class A {
    public static void main(String[] args) {
        TreeMap<String, Boolean> tracks = new TreeMap<>();
        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String n = reader.readLine();   // количество человек
            String k = reader.readLine();   // количество песен в плейлисте

            String line = reader.readLine();    // считываем строку с плейлистом первого человека
            String[] playlist = line.split(" ");
            for (String track : playlist) { // заполняем tracks треками из плейлиста первого человека
                tracks.put(track, false);
            }

            k = reader.readLine();
            while (k != null) {
                line = reader.readLine();   // считываем строку с плейлистом
                playlist = line.split(" ");
                for (String track : playlist) {
                    tracks.replace(track, true);    // если в tracks есть запись с ключом track, устанавливаем значение true
                }
                tracks.keySet().removeIf(key -> !tracks.get(key));  // удаляем все ключи со значением false
                for (String track : tracks.keySet()) { // снова устанавливаем для всех ключей значение false
                    tracks.replace(track, false);
                }
                k = reader.readLine();
            }

            StringBuilder finalPlaylist = new StringBuilder();
            for (String track : tracks.keySet()) {
                finalPlaylist.append(track);
                finalPlaylist.append(" ");
            }

            System.out.println(tracks.size());
            System.out.print(finalPlaylist);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
