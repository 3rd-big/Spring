package com.java.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;

public class MemberDaoImp implements MemberDao {
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public MemberDaoImp() {}
	public MemberDaoImp(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int memberInsert(MemberDto memberDto) {
		return 0;
	}
	@Override
	public int memberIdCheck(String id) {
		return 0;
	}
	@Override
	public List<ZipcodeDto> zipcode(String dong) {
		return null;
	}
	
	@Override
	public String memberLoginOk(Map<String, String> map) {
		return null;
	}
	@Override
	public MemberDto memberUpdate(String id) {
		return null;
	}
	@Override
	public int memberUpdateOk(MemberDto memberDto) {
		return 0;
	}
	@Override
	public int memberDeleteOk(Map<String, String> hmap) {
		return 0;
	}
}
