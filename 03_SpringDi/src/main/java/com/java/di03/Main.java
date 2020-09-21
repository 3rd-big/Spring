package com.java.di03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("com/java/di03/config.xml");
		
		Excel excel = ctx.getBean(Excel.class);
		Excel excel2 = ctx.getBean(Excel.class);
		
		System.out.println(excel + "==" + excel2);
		
		excel.excelPrint();

	}
}
