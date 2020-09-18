package com.java.di01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
//		Su su = new Su();
//		su.disp();
		
		// 스프링 라이브러리를 등록해서 사용가능
		// xml등록한걸 사용하겠다
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appCTX.xml");
		Su su = (Su) ctx.getBean("su");
		su.disp();
		
		ctx.close();
		
	}
}
