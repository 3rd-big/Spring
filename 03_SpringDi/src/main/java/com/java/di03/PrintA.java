package com.java.di03;

import org.springframework.stereotype.Component;

@Component("print-A")
public class PrintA implements Print{

	@Override
	public void print() {
		System.out.println("print A 입니다.");
	}

}
