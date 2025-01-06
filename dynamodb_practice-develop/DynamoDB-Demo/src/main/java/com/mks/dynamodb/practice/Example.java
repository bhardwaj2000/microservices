package com.mks.dynamodb.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {

    public static void main(String[] args) {
        Integer[] sId={1,3,6,9};
        for (Integer integer : sId) {
            System.out.println(integer);
        }
        System.out.println("printing array stream");
        Arrays.stream(sId,2,4).forEach(System.out::println);

        List<String> departmentList= List.of("cse","mech","it","ece","civil","ee");
        departmentList.stream().filter(dept->dept.startsWith("m")).forEach(System.out::println);

//        Stream.generate(new Random()::nextInt).limit(10).forEach(System.out::println);

//        Stream.iterate(1, n->n+2).skip(10).limit(20).max(Integer::compareTo).ifPresent(i-> System.out.println(i));

        long count = Stream.iterate(1, n -> n + 1).skip(5).limit(15).count();
        System.out.println(count);


        Integer reduce = Stream.iterate(1, n -> n + 1).skip(5).limit(15).reduce(0, (a, b) -> a + b);
        System.out.println(reduce);

    }
}
