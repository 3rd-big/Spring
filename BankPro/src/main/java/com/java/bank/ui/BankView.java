package com.java.bank.ui;

import java.util.List;
import java.util.Scanner;

import com.java.bank.aop.LogAspect;
import com.java.bank.dao.BankDao;
import com.java.bank.dto.BankDto;

public class BankView implements BankUI{

	private BankDto bankDto;
	private BankDao bankDao;
	
	public BankView() {}
	
	public BankView(BankDto bankDto, BankDao bankDao) {
		this.bankDto = bankDto;
		this.bankDao = bankDao;
	}
	
	@Override
	public void execute() {
		printMenu();
		Scanner sc = new Scanner(System.in);
		System.out.print("선택하세요");
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1: makeAccount();	break;
		case 2:	deposit(); break;
		case 3:	withdraw(); break;
		case 4:	inquiry(); break;
		case 5:	showData(); break;
		case 6:	break;

		default: System.out.println("잘못입력하였습니다.");
			break;
		}
		
		sc.close();
	}

	public void setBankDto(BankDto bankDto) {
		this.bankDto = bankDto;
	}

	public void setBankDao(BankDao bankDao) {
		this.bankDao = bankDao;
	}

	public void printMenu() {
		System.out.println("MENU----------------------");
		System.out.println("1. 계좌 개설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 잔액조회");
		System.out.println("5. 전체 출력");
		System.out.println("6. 종료");
	}
	
	public void makeAccount() {
		Scanner sc = new Scanner(System.in);
		System.out.print("계좌번호: ");
		String id = sc.next();
		
		BankDto dto = bankDao.select(id);
		if(dto != null) {
			System.out.println("이미 존재하는 계좌번호 입니다.");
		}else {
			bankDto.setId(id);
			System.out.print("이름: ");
			bankDto.setName(sc.next());
			
			System.out.print("입금액: ");
			bankDto.setBalance(sc.nextLong());
			
			int check = bankDao.makeAccount(bankDto);
			
			LogAspect.logger.info(LogAspect.LogMsg + check);
			
			System.out.println("계좌가 생성되었습니다.");
		}
		
		sc.close();
	}
	
	public void deposit() {
		Scanner sc = new Scanner(System.in);
		System.out.print("입금하실 계좌번호: ");
		String id = sc.next();
		
		bankDto = bankDao.select(id);
		LogAspect.logger.info(LogAspect.LogMsg + bankDto);
		
		if(bankDto != null) {
			System.out.print("입금액: ");
			long money = sc.nextLong();
			bankDto.setBalance(bankDto.getBalance() + money);
			int check = bankDao.update(bankDto);
			LogAspect.logger.info(LogAspect.LogMsg + check);
			
			if(check > 0) {
				System.out.println("입금 완료");
			}else {
				System.out.println("입금에 실패 했습니다.");
			}
		}else {
			System.out.println("계좌번호가 존재하지 않습니다.");
		}
		
		sc.close();
	}
	
	public void showData() {
		List<BankDto> banklist = bankDao.showData();
		LogAspect.logger.info(LogAspect.LogMsg + banklist.size());
		
		for(int i=0; i<banklist.size(); i++) {
			BankDto dto = banklist.get(i);
			System.out.println(dto.toString());
		}
	}
	
	public void withdraw() {
		Scanner sc = new Scanner(System.in);
		System.out.print("출금하실 계좌번호: ");
		String id = sc.next();
		
		bankDto = bankDao.select(id);
		if(bankDto != null) {
			System.out.println("현재 잔액: " + bankDto.getBalance());
			System.out.print("출금할 금액: ");
			Long money = sc.nextLong();
			if(money > bankDto.getBalance()) {
				System.out.println("잔액이 부족합니다.");
			}else {
				bankDto.setBalance(bankDto.getBalance() - money);
				int check = bankDao.update(bankDto);
				
				if(check > 0) {
					System.out.println("출금 완료");
				}else {
					System.out.println("출금에 실패했습니다.");
				}
			}
		}else {
			System.out.println("계좌번호가 존재하지 않습니다.");
		}
		
		sc.close();
	}
	
	public void inquiry() {
		Scanner sc = new Scanner(System.in);
		System.out.print("조회하실 계좌번호: ");
		String id = sc.next();
		
		bankDto = bankDao.select(id);
		if(bankDto != null) {
			System.out.println(bankDto.getName() + "님의 잔액: " + bankDto.getBalance());
		}else {
			System.out.println("계좌번호가 존재하지 않습니다.");
		}
		
		sc.close();
	}
	
}





















