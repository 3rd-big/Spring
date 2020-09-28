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
		return sqlSessionTemplate.insert("member_insert", memberDto);
	}
	@Override
	public int memberIdCheck(String id) {
		String value = sqlSessionTemplate.selectOne("member_id_check", id);
		int check = 0;
		if(value != null) {
			check = 1;
		}
		return check;
	}
	@Override
	public List<ZipcodeDto> zipcode(String dong) {
		return null;
	}
	
	@Override
	public String memberLoginOk(Map<String, String> map) {
		return sqlSessionTemplate.selectOne("member_login", map);
	}
	@Override
	public MemberDto memberUpdate(String id) {
		return sqlSessionTemplate.selectOne("member_select", id);
	}
	@Override
	public int memberUpdateOk(MemberDto memberDto) {
		return sqlSessionTemplate.update("member_update", memberDto);
	}
	@Override
	public int memberDeleteOk(Map<String, String> hmap) {
		return sqlSessionTemplate.delete("member_delete", hmap);
	}

}
