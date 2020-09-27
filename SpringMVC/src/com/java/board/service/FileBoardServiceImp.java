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
//			System.out.println("부모글 작성");
		}else {
			int sequenceNumber = Integer.parseInt(multiRequest.getParameter("sequenceNumber")) + 1;
			int groupNumber = Integer.parseInt(multiRequest.getParameter("groupNumber"));
			Map<String, Integer> hmap = new HashMap<String, Integer>();
			hmap.put("sequenceNumber", sequenceNumber);
			hmap.put("groupNumber", groupNumber);
			
			if(fileBoardDao.fileBoardSequenceCheck(hmap) > 0) {
				fileBoardDao.fileBoardSequenceNumberAdd(hmap);
			}
			
			
			fileBoardDto.setGroupNumber(groupNumber);
			fileBoardDto.setSequenceNumber(sequenceNumber);
			fileBoardDto.setSequenceLevel(Integer.parseInt(multiRequest.getParameter("sequenceLevel")) + 1);
//			System.out.println("자식글 작성");
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
		
		List<FileBoardDto> boardList = fileBoardDao.fileBoardList();
		
		mav.addObject("boardSize", 5);
		mav.addObject("currentPage", 1);
		mav.addObject("count", boardList.size());
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
