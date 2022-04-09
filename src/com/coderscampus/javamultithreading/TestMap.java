package com.coderscampus.javamultithreading;

import java.util.stream.*;
import java.util.*;
import java.util.function.*;

class TestMap {

    public static <String> Map<String, Long>  frequencyMap(Stream<String> elements) {
        return elements.collect(
            Collectors.groupingBy(
            Function.identity(),
            //HashMap::new, // can be skipped
            Collectors.counting()
            )
        );
    }

    public static void main( String args[] ) {
    	//frequencyUsingSet();
    	//frequencyIntegerUsingSet();
    	//List<Integer> list = Arrays.asList(1, 1, 3 ,4, 3, 5);
    	//frenquencyStringUsingStream();
    	
    	
    	
         List<Integer>  list1 = new ArrayList<>();
         List<Integer>  list2 = new ArrayList<>();
         for(int i=0; i < 3; i++) {
        	 list1.add(i+1);
        	 list2.add(i+2);
         }
         
         for(int i=0; i < 3; i++) {
        	 
        	 System.out.println( list1.get(i));
         }
         System.out.println("====+++++");
         for(int i=0; i < 3; i++) {
        	 System.out.println( list2.get(i));
        	
         }
         System.out.println("====");
         list1.addAll(list2);
         for (Integer ii : list1) {
        	 System.out.println(ii);
         }
    }

	public static void frenquencyStringUsingStream() {
		List<String> list = Arrays.asList("B", "A", "A", "C", "B", "A");
        Map<String, Long> frequencyMap = list.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
 
        for (Map.Entry<String, Long> entry: frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
	}

	public static void frequencyIntegerUsingSet() {
		List<Integer> list = Arrays.asList(1, 1, 3 ,4, 3, 5);
    	Set<Integer> distinct = new HashSet<>(list);
        for (Integer s: distinct) {
            System.out.println(s ); //+ ": " + Collections.frequency(list, s));
        }
	}

	public static void frequencyUsingSet() {
		List<String> list = Arrays.asList("B", "A", "A", "C", "B", "A");
        Set<String> distinct = new HashSet<>(list);
        for (String s: distinct) {
            System.out.println(s + ": " + Collections.frequency(list, s));
        }
	}
}