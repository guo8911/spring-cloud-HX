package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.User;

@Mapper
public interface UserMapper {
	List<User> findAll();
}
