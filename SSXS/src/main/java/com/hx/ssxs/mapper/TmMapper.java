package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.Tm;

@Mapper
public interface TmMapper {
	List<Tm> getParamInfo(int mid);
	List<Tm> getTmBySatId(int sat_id);
}
