package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.SxTrackCount;

@Mapper
public interface SxTrackCountMapper {
	List<SxTrackCount> getTrackCount(int mid);
}
