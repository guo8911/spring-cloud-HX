package com.hx.ssxs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.ssxs.entity.User;
import com.hx.ssxs.mapper.UserMapper;
import com.hx.ssxs.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
    private UserMapper userMapper;
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userMapper.findAll();
	}

}
