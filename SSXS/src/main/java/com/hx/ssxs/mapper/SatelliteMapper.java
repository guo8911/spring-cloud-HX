package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.Satellite;

@Mapper
public interface SatelliteMapper {
	List<Satellite> getSat();
}
