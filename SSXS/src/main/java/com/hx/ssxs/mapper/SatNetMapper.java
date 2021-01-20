package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.SatNet;

@Mapper
public interface SatNetMapper {
	List<SatNet> getSatTMConnect();
	List<SatNet> getOtherConnect();
	List<SatNet> getSatBackConnect();
}
