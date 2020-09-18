package com.java.di02;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appCTX.xml");
		Example ex = ctx.getBean("example", Example.class);
		ex.disp();
		
		Testing t = (Testing) ctx.getBean("testing");
		t.print();
		
		ctx.close();
	}
}
