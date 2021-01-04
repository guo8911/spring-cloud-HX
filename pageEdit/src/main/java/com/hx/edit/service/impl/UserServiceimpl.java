package com.hx.edit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.edit.entity.User;
import com.hx.edit.mapper.UserMapper;
import com.hx.edit.service.UserService;
@Service("userService")
public class UserServiceimpl implements UserService{

	@Autowired
    private UserMapper userMapper;
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userMapper.findAll();
	}

}
