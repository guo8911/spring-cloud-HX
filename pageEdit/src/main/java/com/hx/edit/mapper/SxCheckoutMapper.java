package com.hx.edit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hx.edit.entity.SxCheckout;

@Mapper
public interface SxCheckoutMapper {
	void addCheckout(SxCheckout sxCheckout);
	SxCheckout getCheckout(int proj_id);
	void delCheckout(int proj_id);
	void delUserCheckout(String user_id );
}
