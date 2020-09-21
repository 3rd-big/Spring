package com.java.aop;

public class Mam implements Person{

	@Override
	public void awake() {
		System.out.println("일어난다");
	}

	@Override
	public void work() {
		System.out.println("집안일 한다");
	}

	@Override
	public void sleep() {
		System.out.println("잠을 잔다");
	}

}
