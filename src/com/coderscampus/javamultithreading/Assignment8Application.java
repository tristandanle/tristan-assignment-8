package com.coderscampus.javamultithreading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Assignment8Application {

	public static void main(String[] args) {

		Assignment8 assignment = new Assignment8();
		final int size = 10000;
		List<CompletableFuture<Void>> tasks = new ArrayList<>();
		List<Integer> listIntegers = new ArrayList<>();
		ExecutorService poolCached = Executors.newCachedThreadPool();
		// ExecutorService poolFixed = Executors.newFixedThreadPool(1);
		// ExecutorService poolSingled = Executors.newSingleThreadExecutor();

		// Fetch data in asynchronous manner
		for (int i = 0; i < size; i++) {
			CompletableFuture<Void> task = CompletableFuture.supplyAsync(() -> assignment.getNumbers(), poolCached)
					.thenAccept(integers -> listIntegers.addAll(integers));
			tasks.add(task);
		}

		while (tasks.stream().filter(CompletableFuture::isDone).count() < size) {
			// Loop for the main thread to be alive until all threads are done working
			System.out.println("Sleeping ...");
        		try {
         		     Thread.sleep(1000);
       			 } catch (InterruptedException e) {
			     Thread.currentThread().interrupt();
         		     System.out.println("Interrupted.");
       			 }
		}

		// Count frequencies of numbers in the list of all numbers
		Map<Integer, Integer> results = new HashMap<>();
		for (Integer integer : listIntegers)
			results.put(integer, results.getOrDefault(integer, 0) + 1);

		for (Entry<Integer, Integer> entry : results.entrySet()) {
			System.out.print(entry.getKey() + "=" + entry.getValue() + ", ");
		}

	}

}
