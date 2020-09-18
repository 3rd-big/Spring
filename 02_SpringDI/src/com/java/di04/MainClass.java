package com.java.di04;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appCTX.xml");
		
		CC cc = ctx.getBean("cc", CC.class);
		cc.disp();
		
		ctx.close();
		
	}
}
