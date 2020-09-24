package com.java.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.InternalResourceView;

import com.java.aop.HAspect;
import com.java.member.dto.MemberDto;
import com.java.member.service.MemberService;

public class MemberController extends MultiActionController{	//command
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public MemberController() {}
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public ModelAndView testing(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("OK.");
		
		ModelAndView mav = new ModelAndView("member/testing");
		mav.addObject("msg", "hi");
		
		return mav;
	}
	public ModelAndView memberRegister(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/register");
	}
	public ModelAndView memberRegisterOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDto", memberDto);
		
		memberService.memberRegisterOk(mav);
		
		return mav;
	}
	
	public ModelAndView memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	public ModelAndView memberZipcode(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	public ModelAndView memberLoginOk(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	public ModelAndView memberMain(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	public ModelAndView memberLogout(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	public ModelAndView memberUpdateOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		return null;
	}
	public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	public ModelAndView memberDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
}
