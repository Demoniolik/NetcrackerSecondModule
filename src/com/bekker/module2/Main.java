package com.bekker.module2;

import java.io.*;
import java.util.*;

public class Main {
    private static Integer[] array;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 Вивести на екран лише унікальні значення (обов’язкове використання Set)\n" +
                "2 Вивести тільки парні значення (обов’язкове використання колекції List)\n" +
                "3 Вивести всі елементи у вигляді пар «First element – array[0]», «Second element –\n" +
                "array[1]» ….(обов’язкове використання колекції Map та ітератору)\n" +
                "4 Закінчити виконання програми, якщо буде в консолі введено слово «quit»");
        String input = scanner.nextLine();
        getNumberFromString();
        long start = System.currentTimeMillis();
        while (!input.equals("quit")) {
            switch (input) {
                case "1":
                    Set<Integer> integerSet = new HashSet<>(Arrays.asList(array));
                    integerSet.forEach(System.out::println);
                    break;
                case "2":
                    List<Integer> integerList = new ArrayList<>(List.of(array));
                    integerList.stream().filter(e -> e % 2 == 0).forEach(System.out::println);
                    break;
                case "3":
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int i = 0, index = 0; i < array.length / 2; ++i) {
                        map.put(array[index++], array[index++]); // <- Somehow it does not work as supposed because of indexes

                    }
                    for (Map.Entry<Integer, Integer> element : map.entrySet()) {
                        System.out.println(element.getKey() + " | " + element.getValue());
                    }
                    break;
                default:
                    System.out.println("Not correct option!");
                    break;
            }
            input = scanner.nextLine();
        }
        long end = System.currentTimeMillis();
        try (FileWriter fileWriter = new FileWriter("out.txt")) {
            fileWriter.write((end - start) + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getNumberFromString() {
        String[] input = getDataFromFile().split(" ");
        array = new Integer[input.length];
        for (int i = 0; i < input.length; ++i) {
            array[i] = Integer.parseInt(input[i]);
        }
    }

    public static String getDataFromFile() {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File("test.txt"), "Cp1251");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

}
