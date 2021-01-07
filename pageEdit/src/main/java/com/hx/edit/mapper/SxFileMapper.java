package com.hx.edit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hx.edit.entity.SxFile;

@Mapper
public interface SxFileMapper {
	void addFile(SxFile sxFile);
	SxFile getFile(int proj_id);
	void save(SxFile sxFile);
	String getDataIntoCheckout(int proj_id);
	void deleteFile(int proj_id);
}
