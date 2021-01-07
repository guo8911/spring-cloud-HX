package com.hx.edit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hx.edit.entity.SxProject;

@Mapper
public interface SxProjectMapper {
	int checkName(SxProject sxProject);
	int addNode(SxProject sxProject);
	int editNode(SxProject sxProject);
	void deleteProject(int id);
}
