package com.hx.edit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.edit.entity.SxGuding;

@Mapper
public interface SxGudingMapper {
	List<SxGuding> getGuding();
	String getGudingUrl(int id);
}
