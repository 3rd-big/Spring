package com.java.di05;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appCTX.xml");
		
		Message en = ctx.getBean("messageEn", Message.class);
		en.sayHello("tsseo");
		
		Message kr = ctx.getBean("messageKr", Message.class);
		kr.sayHello("서태수");
	}
}
