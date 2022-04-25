package com.coderscampus.javamultithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Assignment8Application {

	public static void main(String[] args) throws InterruptedException {

		Assignment8 assignment = new Assignment8();
		final int size = 1000;
		List<CompletableFuture<Void>> tasksFutures = new ArrayList<>();
		// https://www.netjstech.com/2015/09/how-and-why-to-synchronize-arraylist-in-java.html
		List<Integer> listIntegers = Collections.synchronizedList(new ArrayList<>());
		//List<Integer> listIntegers = new CopyOnWriteArrayList<Integer>();
		ExecutorService poolCached = Executors.newCachedThreadPool();
		//ExecutorService poolFixed = Executors.newFixedThreadPool(3);
		// ExecutorService poolSingled = Executors.newSingleThreadExecutor();

		// Fetch data in asynchronous manner
		for (int i = 0; i < size; i++) {
			  
			 CompletableFuture<Void> future = CompletableFuture
					.supplyAsync(() -> assignment
					.getNumbers(), poolCached)
					.thenAccept(integers -> listIntegers.addAll(integers));
			 tasksFutures.add(future);
		}

		
		while (tasksFutures.stream().filter(CompletableFuture::isDone).count() < 1000)
        {
			if (Thread.interrupted()) {
			    throw new InterruptedException();
			}
        }

		// Count frequencies of numbers in the list of all numbers
		Map<Integer, Integer> results = new HashMap<>();
//		results = listIntegers.parallelStream()
//				.collect(Collectors.toConcurrentMap(
//						n -> n, n ->1, Integer::sum));
//		 System.out.println(results);
		for (Integer integer : listIntegers)
			results.put(integer, results.getOrDefault(integer, 0) + 1);
//		 System.out.println(results);
		for (Entry<Integer, Integer> entry : results.entrySet()) {
			System.out.print(entry.getKey() + "=" + entry.getValue() + ", ");
		}

	}

}
