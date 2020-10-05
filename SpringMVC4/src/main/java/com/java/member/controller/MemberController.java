package com.java.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.InternalResourceView;

import com.java.aop.HAspect;
import com.java.member.dto.MemberDto;
import com.java.member.service.MemberService;

@Component
@Controller
public class MemberController{	//command
	@Autowired
	private MemberService memberService;

	@RequestMapping(value="member/test.do")
	public ModelAndView testing(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("OK.");
		
		ModelAndView mav = new ModelAndView("member/testing");
		mav.addObject("msg", "hi");
		
		return mav;
	}
	@RequestMapping(value="member/register.do")
	public ModelAndView memberRegister(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/register");
	}
	@RequestMapping(value="member/registerOk.do")
	public ModelAndView memberRegisterOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		
		HAspect.logger.info(HAspect.logMsg + memberDto.toString());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDto", memberDto);
		
		memberService.memberRegisterOk(mav);
		
		return mav;
	}
	
	@RequestMapping(value="member/idCheck.do")
	public ModelAndView memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		memberService.memberIdCheck(mav);
		
		return mav;
	}
	
	public ModelAndView memberZipcode(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	@RequestMapping(value="member/login.do")
	public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/login");
	}
	
	@RequestMapping(value="member/loginOk.do")
	public ModelAndView memberLoginOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		memberService.memberLoginOk(mav);
		
		return mav;
	}
	
	@RequestMapping(value="member/main.do")
	public ModelAndView memberMain(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/main");
	}
	
	@RequestMapping(value="member/logout.do")
	public ModelAndView memberLogout(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/logout");
	}
		
	@RequestMapping(value="member/update.do")
	public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		memberService.memberUpdate(mav);
		
		return mav;
	}
	
	@RequestMapping(value="member/updateOk.do")
	public ModelAndView memberUpdateOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDto", memberDto);
		
		memberService.memberUpdateOk(mav);
		
		return mav;
	}
	
	@RequestMapping(value="member/delete.do")
	public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/delete");
	}
	
	@RequestMapping(value="member/deleteOk.do")
	public ModelAndView memberDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		memberService.memberDeleteOk(mav);
		
		return mav;
	}
	
}
