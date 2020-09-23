package com.java.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.transaction.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	AccountService service;
	
    @RequestMapping("/")
    public String transferPage() {
        System.out.println("account");
        return "account";
    }
    
    @RequestMapping("/transfer")
    public String transfer(int money, String accountNum) {
    	service.transfer(money, accountNum);
    	
    	return "transfer-ok";
    }
    
}
