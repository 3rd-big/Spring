package com.java.transaction.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {

	@Autowired
	SqlSession sqlSession;
	
	public int withdraw(int money, String accountNum) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("money", String.valueOf(money));
		map.put("accountNum", accountNum);
		
		return sqlSession.update("account.withdraw", map);
		//return 1;
	}

	public int deposit(int money, String accountNum) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("money", String.valueOf(money));
		map.put("accountNum", accountNum);
		
		return sqlSession.update("account.deposit", map);
		//return 1;
	}

	public String transferOK() throws Exception{
		return "이체완료 되었습니다.";
	}
	
	public String transfering() throws Exception{
		throw new Exception();
	}

	public String inquire(String accountNum) {
		return sqlSession.selectOne("account.inquire", accountNum);
	}
	
}