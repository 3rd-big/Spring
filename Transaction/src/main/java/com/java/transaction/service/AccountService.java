package com.java.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oracle.jdbc.internal.OracleConnection.TransactionState;

@Service
public class AccountService {
	
	@Autowired
	AccountDAO dao;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Transactional(rollbackFor = Exception.class)
	public void transfer(int money, String accountNum) {
		
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			
			String hongNum = "1234-5678";
			
			dao.withdraw(money, hongNum);
			
			String hongMoney = dao.inquire(hongNum);
	
			if(money > Integer.parseInt(hongMoney)) {
				System.out.println(dao.transfering());
			}else {
				dao.deposit(money, accountNum);
				System.out.println(dao.transferOK());
			}
			
			transactionManager.commit(status);
			
		} catch (Exception e) {
			
			transactionManager.rollback(status);
			e.printStackTrace();
		}
		
	}
	
}
