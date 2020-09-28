package com.java.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HAspect;
import com.java.board.dao.FileBoardDao;
import com.java.board.dto.FileBoardDto;
import com.java.member.dto.MemberDto;

public class FileBoardServiceImp implements FileBoardService{
	private FileBoardDao fileBoardDao;

	public void setFileBoardDao(FileBoardDao fileBoardDao) {
		this.fileBoardDao = fileBoardDao;
	}
	
	public FileBoardServiceImp() {}
	public FileBoardServiceImp(FileBoardDao fileBoardDao) {
		this.fileBoardDao = fileBoardDao;
	}

	@Override
	public void fileBoardWriteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();	
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) map.get("request");
		
		String uploadPath = "C:\\Spring\\img";
		
		Enumeration e = multiRequest.getParameterNames();
		while(e.hasMoreElements()) {
			String name = (String)  e.nextElement();
	
			String value = multiRequest.getParameter(name);
			
			map.put(name, value);
		}
		MultipartFile multipartFile = multiRequest.getFile("file");
		
		File file = new File(uploadPath, multipartFile.getOriginalFilename());
		String fileSize = Integer.toString((int)(multipartFile.getSize()/1024)) ;
		try {
			// 해당 경로에 실제로 파일 업로드
			multipartFile.transferTo(file);
		} catch (IllegalStateException | IOException e1) {
			System.out.println("파일 업로드 실패");
			e1.printStackTrace();
		}
		
		FileBoardDto fileBoardDto = new FileBoardDto();
		int boardNumber = fileBoardDao.fileBoardNumberCheck(); 
		
		fileBoardDto.setBoardNumber(boardNumber);
		
		fileBoardDto.setWriter((String)map.get("writer"));
		fileBoardDto.setSubject((String)map.get("subject"));
		fileBoardDto.setEmail((String)map.get("email"));
		fileBoardDto.setContent((String)map.get("content"));
		fileBoardDto.setPassword((String)map.get("password"));
		fileBoardDto.setFileName(multipartFile.getOriginalFilename());
		fileBoardDto.setPath(uploadPath);
		fileBoardDto.setFileSize(fileSize);
		

		if(multiRequest.getParameter("boardNumber").equals("")) {
			fileBoardDto.setGroupNumber(boardNumber);
			fileBoardDto.setSequenceNumber(0);
			fileBoardDto.setSequenceLevel(0);
			System.out.println("부모글 작성");
		}else {
			System.out.println("넘어온 시퀀스넘버" + multiRequest.getParameter("sequenceNumber"));
			int sequenceNumber = Integer.parseInt(multiRequest.getParameter("sequenceNumber"));
			int sequenceLevel = Integer.parseInt(multiRequest.getParameter("sequenceLevel"));
			int maxSequenceNumber = fileBoardDao.fileBoardMaxSequence() + 1;
			
			int groupNumber = Integer.parseInt(multiRequest.getParameter("groupNumber"));
			Map<String, Integer> hmap = new HashMap<String, Integer>();
			hmap.put("sequenceNumber", sequenceNumber);
			hmap.put("groupNumber", groupNumber);
			hmap.put("sequenceLevel", sequenceLevel);
		
			boolean isChild = fileBoardDao.fileBoardChildCheck(hmap);
			
			if(isChild) {	// 답변을 달 대상의 자식 있음
				fileBoardDto.setSequenceNumber(maxSequenceNumber);
				System.out.println("답변을 달 대상의 자식 있음");
			}else { 		// 답변을 달 대상의 자식 없음
				if(fileBoardDao.fileBoardSequenceCheck(hmap) > 0) {
					fileBoardDao.fileBoardSequenceNumberAdd(hmap);
					fileBoardDto.setSequenceNumber(sequenceNumber+1);
					System.out.println("답변을 달 대상의 자식 없음");
					System.out.println("시퀀스 중복이후 증가");
				}
			}
	
			fileBoardDto.setGroupNumber(groupNumber);
			
			fileBoardDto.setSequenceLevel(Integer.parseInt(multiRequest.getParameter("sequenceLevel")) + 1);
			System.out.println("자식글 작성");
//			System.out.println(multiRequest.getParameter("groupNumber"));
//			System.out.println(multiRequest.getParameter("sequenceNumber"));
//			System.out.println(multiRequest.getParameter("sequenceLevel"));
		}

		int check = fileBoardDao.fileBoardWriteOk(fileBoardDto);
		
		mav.addObject("check", check);
		mav.setViewName("fileBoard/writeOk");
	}

	@Override
	public void fileBoardList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber == null) {
			pageNumber = "1";
		}
		
		int currentPage = Integer.parseInt(pageNumber);
		int boardSize = 10;
		
		int startRow = (currentPage - 1) * boardSize + 1;
		int endRow = currentPage * boardSize;
		
		int count = fileBoardDao.fileBoardCount();
		
		List<FileBoardDto> boardList = null;
		
		if(count > 0) {
			boardList = fileBoardDao.fileBoardList(startRow, endRow);
		}
		
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);	
		mav.addObject("count", count);
		mav.addObject("boardList", boardList);
		
		mav.setViewName("fileBoard/list");
	}

	@Override
	public void fileBoardRead(ModelAndView mav) {
		
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		FileBoardDto fileBoardDto = fileBoardDao.fileBoardRead(boardNumber);
		
		mav.addObject("boardDto", fileBoardDto);
		mav.setViewName("fileBoard/read");
	}
	
	
}
