package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.SxProject;
import com.hx.ssxs.entity.SxProjectSat;

@Mapper
public interface SxProjectMapper {
	int checkName(SxProject sxProject);
	List<SxProjectSat> getSatProject();
}
