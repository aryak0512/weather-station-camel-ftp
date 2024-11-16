package com.example.weather.learnlist;

import java.util.*;

public class App {

    public static void main(String[] args) {

        // [24,5,16,7]
        List<Integer> list = new ArrayList<>(); // declaration

        // add few numbers to list
        list.add(24);
        list.add(5);
        list.add(16);
        list.add(7);

        //System.out.println(list);


        // [Interface] = new [Class]
        List<String> sumegha = new ArrayList<>(); // declaration

        // add few numbers to list
        sumegha.add("Sydney");
        sumegha.add("Australia");
        sumegha.add("NSW");

        //System.out.println(sumegha);


        List<Product> productList = new ArrayList<>();

        Product p1 = new Product("Iphone", 4, 875.00);
        Product p2 = new Product("Samsung", 20, 11875.00);
        Product p3 = new Product("BBM", 40, 1875.00);

        productList.add(p1);
        productList.add(p2);
        productList.add(p3);

//        for ( Product p : productList )
//            System.out.println(p);


        // {1 : "One" , 2 : "Two" , 3 : "Three"}
        Map<Integer, Product> map = new HashMap<>();
        map.put(1, p1);
        map.put(2, p2);
        map.put(3, p3);

        //System.out.println(map);

        // retrieve from the map
        System.out.println(map.get(1)); // ??? p1
        System.out.println(map.get(2).getPrice()); // 11875


        // [9,5,7]
        Set set = new HashSet();
    }
}
