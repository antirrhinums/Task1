package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();

        list.add(random.nextInt());
        for(int i = 0; i < 999; ++i){
            list.add(random.nextInt(), random.nextInt(0, list.size()));
        }
        System.out.println(list.size());

        for(int i = 0; i < 1000; ++i){
            list.remove(random.nextInt(0, list.size()));
        }
        System.out.println(list.size());

        for(int i = 0; i < 10; ++i){
            list.add(random.nextInt(0, 100));
        }
        System.out.println(list);

        System.out.println("Без компаратора:");
        list.sort(null);
        System.out.println(list);

        System.out.println("С компаратором:");
        list.sort((a,b) -> b.compareTo(a));
        System.out.println(list);
    }
}