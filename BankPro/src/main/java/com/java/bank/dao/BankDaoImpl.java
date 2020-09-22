package com.java.bank.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.java.bank.dto.BankDto;

public class BankDaoImpl implements BankDao{

	private SqlSessionTemplate sqlSessionTemplate;
	
	public BankDaoImpl() {}
	
	public BankDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	@Override
	public int makeAccount(BankDto bankDto) {
		System.out.println(bankDto.toString());
		int check = 0;
		
		check = sqlSessionTemplate.insert("bank_insert", bankDto);		
		
		return check;
	}

	@Override
	public List<BankDto> showData() {
		return sqlSessionTemplate.selectList("bank_select_list");
	}

	@Override
	public BankDto select(String id) {
		
		return sqlSessionTemplate.selectOne("dao.BankMapper.bank_select", id);
	}

	@Override
	public int update(BankDto bankDto) {
		
		return sqlSessionTemplate.update("dao.BankMapper.bank_update", bankDto);
	}

}
