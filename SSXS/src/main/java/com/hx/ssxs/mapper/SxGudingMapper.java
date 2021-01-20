package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.SxGuding;

@Mapper
public interface SxGudingMapper {
	List<SxGuding> getGuding();
	SxGuding getPageOfGD(int id);
}
