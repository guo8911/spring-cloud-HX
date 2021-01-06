package com.hx.edit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.edit.entity.Tm;

@Mapper
public interface TmMapper {
	List<Tm> getTm(Tm tm);
	List<Tm> getRawTm(Tm tm);
	int getTmCount(Tm tm);
}
