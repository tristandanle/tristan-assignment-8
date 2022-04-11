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

	public static void main(String[] args) throws InterruptedException {

		Assignment8 assignment = new Assignment8();
		final int size = 10000;
		List<CompletableFuture<Void>> tasksFutures = new ArrayList<>();
		List<Integer> listIntegers = new ArrayList<>();
		ExecutorService poolCached = Executors.newCachedThreadPool();
		// ExecutorService poolFixed = Executors.newFixedThreadPool(1);
		// ExecutorService poolSingled = Executors.newSingleThreadExecutor();

		// Fetch data in asynchronous manner
		for (int i = 0; i < size; i++) {
			 CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(() -> assignment
					.getNumbers(), poolCached);
			//CompletableFuture<Boolean> futures = future.thenApply(integers -> listIntegers.addAll(integers));
			CompletableFuture<Void> futures = future.thenAccept(integers -> listIntegers.addAll(integers));
			tasksFutures.add(futures);
		}

		
		while (tasksFutures.stream().filter(CompletableFuture::isDone).count() < size) {
			if (Thread.interrupted()) {
			    throw new InterruptedException();
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
