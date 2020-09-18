package com.java.di03;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appCTX.xml");
		
		Sub sub = ctx.getBean("sub1", Sub.class);
		System.out.println("생성자를 이용: " + sub.toString());
		
		Sub sub2 = ctx.getBean("sub2", Sub.class);
		System.out.println("setter를 이용: " + sub2.toString());
		
		ctx.close();
	}
}
