package com.java.di03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Excel {
	
	// 타입으로 의존성 주입, required = false [설정을 통해 빈이 없을 경우] 에러 발생을 막음
	@Autowired(required = false)
	@Qualifier("print-B")
	Print print;
	
	public Excel() {
		System.out.println("excel 생성");
	}
	
	public void excelPrint() {
		if(print != null) {
			print.print();
		}else {
			System.out.println("print는 null 입니다.");
		}
	}
}
