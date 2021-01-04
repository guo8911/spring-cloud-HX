package com.hx.edit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.edit.entity.ViewSxProject;

@Mapper
public interface SxProjectMapper {
	List<ViewSxProject> getProj();
}
