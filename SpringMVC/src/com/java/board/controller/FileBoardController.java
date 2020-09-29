package com.java.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.java.board.dto.FileBoardDto;
import com.java.board.service.FileBoardService;

public class FileBoardController extends MultiActionController {
	private FileBoardService fileBoardService;

	public void setFileBoardService(FileBoardService fileBoardService) {
		this.fileBoardService = fileBoardService;
	}

	public FileBoardController() {}
	
	public FileBoardController(FileBoardService fileBoardService) {
		this.fileBoardService = fileBoardService;
	}
	
	public ModelAndView fileBoardWrite (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardNumber", request.getParameter("boardNumber"));
		mav.addObject("groupNumber", request.getParameter("groupNumber"));
		mav.addObject("sequenceNumber", request.getParameter("sequenceNumber"));
		mav.addObject("sequenceLevel", request.getParameter("sequenceLevel"));
		mav.setViewName("fileBoard/write");
		return mav;
	}
	public ModelAndView fileBoardWriteOk (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
//		System.out.println("글쓰기 시작");
//		System.out.println(request.getParameter("boardNumber"));
//		System.out.println(request.getParameter("groupNumber"));
//		System.out.println(request.getParameter("sequenceNumber"));
//		System.out.println(request.getParameter("sequenceLevel"));
		fileBoardService.fileBoardWriteOk(mav);
	
		return mav;
	}
	
	public ModelAndView fileBoardList (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		System.out.println(request.getParameter("pageNumber"));
		mav.addObject("request", request);
		fileBoardService.fileBoardList(mav);
		
		return mav;
	}
	
	public ModelAndView fileBoardRead (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		fileBoardService.fileBoardRead(mav);
		
		return mav;
	}
	
	public void fileBoardDownLoad (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("response", response);
		
		fileBoardService.fileBoardDownLoad(mav);
	}
	
	public ModelAndView fileBoardUpdate (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
//		fileBoardService.fileBoardUpdate(mav);
		
		return mav;
	}
	
	public ModelAndView fileBoardUpdateOk (HttpServletRequest request, HttpServletResponse response, FileBoardDto fileBoardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("fileBoardDto", fileBoardDto);
		
//		fileBoardService.fileBoardUpdateOk(mav);
		
		return mav;
	}
	
	public ModelAndView fileBoardDelete (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardNumber", request.getParameter("boardNumber"));
		mav.addObject("pageNumber", request.getParameter("pageNumber"));
		mav.setViewName("fileBoard/delete");
		return mav;
	}
	
	public ModelAndView fileBoardDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("password", request.getParameter("password"));
		mav.addObject("pageNumber", request.getParameter("pageNumber"));
		mav.addObject("boardNumber", request.getParameter("boardNumber"));
		
		fileBoardService.fileBoardDeleteOk(mav);
		
		return mav;
	}
	
}
