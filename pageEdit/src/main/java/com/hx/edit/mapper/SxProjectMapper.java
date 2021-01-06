package com.hx.edit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hx.edit.entity.SxProject;

@Mapper
public interface SxProjectMapper {
	int checkName(SxProject sxProject);
	void addNode(SxProject sxProject);
	void editNode(SxProject sxProject);
}
