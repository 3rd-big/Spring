package com.java.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.java.board.dto.FileBoardDto;

public class FileBoardDaoImp implements FileBoardDao{
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public FileBoardDaoImp() {}

	@Override
	public int fileBoardWriteOk(FileBoardDto fileBoardDto) {
		return sqlSessionTemplate.insert("fileBoard_insert", fileBoardDto);
	}

	@Override
	public List<FileBoardDto> fileBoardList() {
		List<FileBoardDto> list = sqlSessionTemplate.selectList("fileBoard_list"); 
		for (FileBoardDto fileBoardDto : list) {
			System.out.println(fileBoardDto.toString());
		}
		
		return list;
	}

	@Override
	public FileBoardDto fileBoardRead(int boardNumber) {
		return sqlSessionTemplate.selectOne("fileBoard_select", boardNumber);
	}

	@Override
	public int fileBoardNumberCheck() {
		String value = sqlSessionTemplate.selectOne("boardNumberCheck");
		return Integer.parseInt(value);
	}

	@Override
	public void fileBoardSequenceNumberAdd(Map<String, Integer> hmap) {
		sqlSessionTemplate.update("sequenceNumber_update", hmap);
	}

	@Override
	public int fileBoardSequenceCheck(Map<String, Integer> hmap) {
		String value = sqlSessionTemplate.selectOne("sequenceCheck", hmap);
		int count = 0;
		if(value != null) {
			count = 1;
		}
		return count;
	}
}
