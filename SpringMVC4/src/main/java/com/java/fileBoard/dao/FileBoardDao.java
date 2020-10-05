package com.java.fileBoard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.java.fileBoard.dto.FileBoardDto;

public interface FileBoardDao {
	
	public int fileBoardWriteOk(FileBoardDto fileBoardDto);
	
	public List<FileBoardDto> fileBoardList(int startRow, int endRow);
	
	public FileBoardDto fileBoardRead(int boardNumber);
	
	public int fileBoardNumberCheck();
	
	public void fileBoardSequenceNumberAdd(Map<String, Integer> hmap);
	
	public int fileBoardSequenceCheck(Map<String, Integer> hmap);

	public int fileBoardMaxSequence();
	
	public boolean fileBoardChildCheck(Map<String, Integer> hmap);
	
	public int fileBoardCount();
	
	public FileBoardDto fileBoardSelect(int boardNumber);
	
//	public int fileBoardUpdateOk(FileBoardDto updateDto, int fileDelCheck);
	
	public int fileBoardDeleteOk(Map<String, Object> hmap);
}
