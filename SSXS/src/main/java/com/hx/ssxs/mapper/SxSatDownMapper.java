package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.SxSatDown;



@Mapper
public interface SxSatDownMapper {
	List<SxSatDown> getSatDownInfoByTime(SxSatDown sxSatDown);
	List<SxSatDown> getSatDownForecastInfo(SxSatDown sxSatDown);
}
