package com.java.board.service;

import org.springframework.web.servlet.ModelAndView;


public interface FileBoardService {
	
	public void fileBoardWriteOk(ModelAndView mav);
	
	public void fileBoardList(ModelAndView mav);
	
	public void fileBoardRead(ModelAndView mav);
	
	public void fileBoardDownLoad(ModelAndView mav);
	
//	public void fileBoardUpdate(ModelAndView mav);
	
//	public void fileBoardUpdateOk(ModelAndView mav);
	
	public void fileBoardDeleteOk(ModelAndView mav);
}
