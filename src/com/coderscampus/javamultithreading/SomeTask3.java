package com.coderscampus.javamultithreading;

import java.util.Random;
import java.util.concurrent.Callable;

//class SomeTask implements Runnable , Callable<TaskDto>{
class SomeTask3 {
private static int taskId = 0;
private TaskDto taskDto;

	
	public SomeTask3 doSomeWork()  { // doSomeWork return the class itself
		taskDto = new TaskDto();
		for (int i =0 ; i< 11000000; i++) {
			Integer someInt1 = new Random().nextInt();
			Integer someInt2 = new Random().nextInt();
			Double something = (someInt1 + someInt2) * 1.5; 
		taskDto.setValue(something );
		}
		taskId++;
		System.out.println("Iam a taskId: " + taskId + "! " + "Iam a task - running on thread" + 
	                         Thread.currentThread().getName());
		return this;
	}
		
public TaskDto markComplete () {
	taskDto.setFinished(true);
	return taskDto;
}



}

