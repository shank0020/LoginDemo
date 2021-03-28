package com.logindemo.LoginDemo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.logindemo.LoginDemo.model.User;
import com.logindemo.LoginDemo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@RequestMapping("users/login")
	public String login() {
		return "users/login";
	}
	
	@RequestMapping("users/registration")
    public String registration() {
        return "users/registration";
    }
	
	@RequestMapping(value = "users/registration", method = RequestMethod.POST)
	public String registerUser(User user) {
		userService.registerUser(user);
		return "users/login";
	}
	//ModelandView()
	@RequestMapping(value = "users/login", method = RequestMethod.POST)
    public ModelAndView loginUser(User user, HttpSession session) {
		User existingUser = userService.login(user);
		if (existingUser != null) {
			ModelAndView modelandview = new ModelAndView("loginuser");
            session.setAttribute("loggeduser", existingUser);
            return modelandview;
        } else {
            return new ModelAndView("users/login");
        }
    }

}
