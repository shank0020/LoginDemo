package com.logindemo.LoginDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logindemo.LoginDemo.dao.UserDao;
import com.logindemo.LoginDemo.model.User;


@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public User login(User user) {
		User existingUser = userDao.checkUser(user.getUsername(), user.getPassword());
		if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

	public void registerUser(User user) {
		userDao.registerUser(user);
	}
}
