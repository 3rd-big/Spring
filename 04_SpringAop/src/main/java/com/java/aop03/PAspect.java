package com.java.aop03;

import org.aspectj.lang.JoinPoint;

public class PAspect {
	
	public void open(JoinPoint joinpoint) {
		System.out.println("강의장에 들어온다.");
	}
	
	public void close(JoinPoint joinpoint) {
		System.out.println("수업끝!! 강의장을 나간다!");
	}
	
	public void error(JoinPoint joinpoint) {
		System.out.println("식권없음!! 밥을 굶는다!!");
	}
	
	public void eat(JoinPoint joinpoint) {
		System.out.println("점심을 맛있게 먹는다!!");
	}
}
