package com.java.board.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HAspect;
import com.java.board.dao.FileBoardDao;
import com.java.board.dto.FileBoardDto;
import com.java.member.dto.MemberDto;


@Component
public class FileBoardServiceImp implements FileBoardService{
	@Autowired
	private FileBoardDao fileBoardDao;

	@Override
	public void fileBoardWriteOk(ModelAndView mav) {	// TODO 업로드 시간을 밀리초단위로 업로드하면 파일명이 중복되지 않음
		Map<String, Object> map = mav.getModelMap();	
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) map.get("request");
		
		String uploadPath = "C:\\Users\\kitri\\Desktop\\app";
		
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
			if(file.isDirectory()) {
				multipartFile.transferTo(file);
			}else {
				System.out.println("폴더 없음"); 
			}
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
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		FileBoardDto fileBoardDto = fileBoardDao.fileBoardRead(boardNumber);
		
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("boardDto", fileBoardDto);
		mav.setViewName("fileBoard/read");
	}

	@Override
	public void fileBoardDownLoad(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber")); 
		HAspect.logger.info(HAspect.logMsg + boardNumber);
		
		FileBoardDto fileBoardDto = fileBoardDao.fileBoardSelect(boardNumber);
		HAspect.logger.info(HAspect.logMsg + fileBoardDto);
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			String fileName = fileBoardDto.getFileName(); 
			String utfName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
			Long fileSize = Long.parseLong(fileBoardDto.getFileSize()); 
			String path = fileBoardDto.getPath();
			
			// 정해진 고정문구.. 외워라
			response.setHeader("content-Disposition", "attachment;filename=" + utfName);
			
			// 8진수 설정
			response.setContentType("application/octet-stream");  
			response.setContentLengthLong(fileSize);
			
			bis = new BufferedInputStream(new FileInputStream(path), 1024);
			bos = new BufferedOutputStream(response.getOutputStream(), 1024);
			
			while(true) {
				int data = bis.read();
				if(data == -1) {
					break;
				}
				bos.write(data);
			}
			bos.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bis != null) {
					bis.close();
				}
				if(bos != null) {
					bos.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	@Override
	public void fileBoardDeleteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		int boardNumber = (int) map.get("boardNumber");
		String password = (String) map.get("password");
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("boardNumber", boardNumber);
		hmap.put("password", password);
		
		int check = fileBoardDao.fileBoardDeleteOk(hmap);
		
		mav.addObject("check", check);
	}

	/*
	 * @Override public void fileBoardUpdate(ModelAndView mav) { Map<String, Object>
	 * map = mav.getModelMap(); HttpServletRequest request = (HttpServletRequest)
	 * map.get("request");
	 * 
	 * int boardNumber = Integer.parseInt(request.getParameter("boardNumber")); int
	 * pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	 * 
	 * FileBoardDto boardDto = fileBoardDao.fileBoardRead(boardNumber);
	 * 
	 * mav.addObject("boardDto", boardDto); mav.addObject("boardNumber",
	 * boardNumber); mav.addObject("pageNumber", pageNumber);
	 * mav.setViewName("fileBoard/update"); }
	 * 
	 * @Override public void fileBoardUpdateOk(ModelAndView mav) { Map<String,
	 * Object> map = mav.getModelMap(); MultipartHttpServletRequest request =
	 * (MultipartHttpServletRequest) map.get("request");
	 * 
	 * int boardNumber = Integer.parseInt(request.getParameter("boardNumber")); int
	 * pageNumber = Integer.parseInt(request.getParameter("pageNumber")); int
	 * fileDelCheck = Integer.parseInt(request.getParameter("fileDelCheck"));
	 * 
	 * FileBoardDto updateDto = (FileBoardDto) map.get("fileBoardDto");
	 * 
	 * // uploadFile(request, updateDto);
	 * 
	 * FileBoardDto dto = fileBoardDao.fileBoardSelect(boardNumber);
	 * if(fileDelCheck==1 && dto.getPath()!=null) { File file = new
	 * File(dto.getPath()); if(file.exists() && file.isFile()) { file.delete(); } }
	 * int check = fileBoardDao.fileBoardUpdateOk(updateDto, fileDelCheck);
	 * mav.addObject("check", check); mav.addObject("pageNumber", pageNumber);
	 * mav.addObject("boardNumber", boardNumber);
	 * 
	 * mav.setViewName("fileBoard/updateOk"); }
	 */
	
	
}
