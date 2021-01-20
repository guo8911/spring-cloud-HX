package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.ViewSxProject;

@Mapper
public interface ViewSxProjectMapper {
	List<ViewSxProject> getProj();
}
