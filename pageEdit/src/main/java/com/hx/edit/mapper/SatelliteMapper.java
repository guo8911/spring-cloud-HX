package com.hx.edit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.edit.entity.Satellite;

@Mapper
public interface SatelliteMapper {
	List<Satellite> getSat();
}
