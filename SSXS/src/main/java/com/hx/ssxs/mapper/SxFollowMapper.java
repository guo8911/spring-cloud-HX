package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.SxFollow;

@Mapper
public interface SxFollowMapper {
	List<SxFollow> getForecast(int mid);
}
