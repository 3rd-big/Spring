package com.java.board.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileBoardDto{
	private int boardNumber;
	private String writer;
	private String subject;
	private String email;
	private String content;
	private String password;
	private Date writeDate;
	private int readCount;
	private int groupNumber;
	private int sequenceNumber;
	private int sequenceLevel;
	private String fileName;
	private String path;
	private String fileSize;
	
	public FileBoardDto() {}
	
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public int getSequenceLevel() {
		return sequenceLevel;
	}
	public void setSequenceLevel(int sequenceLevel) {
		this.sequenceLevel = sequenceLevel;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	@Override
	public String toString() {
		return "FileBoardDto [boardNumber=" + boardNumber + ", writer=" + writer + ", subject=" + subject + ", email="
				+ email + ", content=" + content + ", password=" + password + ", writeDate=" + writeDate
				+ ", readCount=" + readCount + ", groupNumber=" + groupNumber + ", sequenceNumber=" + sequenceNumber
				+ ", sequenceLevel=" + sequenceLevel + ", fileName=" + fileName + ", path=" + path + ", fileSize=" + fileSize
				+ "]";
	}
	
	

}
