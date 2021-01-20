package com.hx.ssxs.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.SxCheckout;

@Mapper
public interface SxCheckoutMapper {
	SxCheckout checkOutFile(int proj_id);
}
