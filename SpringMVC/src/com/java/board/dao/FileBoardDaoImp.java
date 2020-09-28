package com.java.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.servlet.ModelAndView;

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
	public List<FileBoardDto> fileBoardList(int startRow, int endRow) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<FileBoardDto> list = sqlSessionTemplate.selectList("fileBoard_list", map); 
//		for (FileBoardDto fileBoardDto : list) {
//			System.out.println(fileBoardDto.toString());
//		}
		
		return list;
	}

	@Override
	public FileBoardDto fileBoardRead(int boardNumber) {
		sqlSessionTemplate.update("fileBoard_view", boardNumber);
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
		int count = -1;
		if(value != null) {
			count = 1;
		}
		return count;
	}

	@Override
	public int fileBoardMaxSequence() {
		String value = sqlSessionTemplate.selectOne("maxSequenceNumber_select");
		return Integer.parseInt(value);
	}

	@Override
	public boolean fileBoardChildCheck(Map<String, Integer> hmap) {
		String value = sqlSessionTemplate.selectOne("childCheck_select", hmap);
		boolean isChild = false;
		if(value != null) {
			isChild = true;
		}
		return isChild;
	}


	@Override
	public int fileBoardCount() {
		return sqlSessionTemplate.selectOne("fileBoard_getCount");
	}
	
}
