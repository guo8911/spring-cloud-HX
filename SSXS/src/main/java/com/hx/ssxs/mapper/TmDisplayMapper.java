package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.TmDisplay;

@Mapper
public interface TmDisplayMapper {
	List<TmDisplay> queryTMDisplayBySatCode(String sat_code);
}
