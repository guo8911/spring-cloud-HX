package com.hx.edit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.edit.entity.User;

@Mapper
public interface UserMapper {
	List<User> findAll();
}
