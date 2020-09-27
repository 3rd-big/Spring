package com.java.board.dao;

import java.util.List;
import java.util.Map;

import com.java.board.dto.FileBoardDto;

public interface FileBoardDao {
	
	public int fileBoardWriteOk(FileBoardDto fileBoardDto);
	
	public List<FileBoardDto> fileBoardList();
	
	public FileBoardDto fileBoardRead(int boardNumber);
	
	public int fileBoardNumberCheck();
	
	public void fileBoardSequenceNumberAdd(Map<String, Integer> hmap);
	
	public int fileBoardSequenceCheck(Map<String, Integer> hmap);
}
