package com.augmenter.app.fixtures;

import java.util.function.Consumer;

import org.junit.Test;

public class TestPlayground {
	
	@Test
	public void test() {
		Consumer<String> consumer = (str)  ->  str.concat(" IT_WORKS!");
		CustomConsumer<String> cconsumer = str -> str = str + "abc"; 
		
		String itWorks =  "Dude ";
		String itWorksObj =  new String("Dude ");
		int  i = 1;
		
		itWorks = cconsumer.transform(itWorks);
		
		perfromOps(itWorks);
		perfromOps(i);
		
		System.out.println(itWorks);
		System.out.println(i);
	}
	
	private void perfromOps(String str) {
		str = str +  "it works!";
	}
	
	private void perfromOps(int i) {
		i = i++;
	}
	
	@FunctionalInterface
	public interface CustomConsumer<T> {
		
		T transform(T str);
	}
}
