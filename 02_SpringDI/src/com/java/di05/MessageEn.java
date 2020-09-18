package com.java.di05;

public class MessageEn implements Message{

	@Override
	public void sayHello(String name) {
		System.out.println("Hello. " + name);
	}

}
