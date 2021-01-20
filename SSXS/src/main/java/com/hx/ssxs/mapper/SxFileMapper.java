package com.hx.ssxs.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.SxFile;

@Mapper
public interface SxFileMapper {
	SxFile getPageFile(int proj_id);
}
