package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.DeviceInfo;

@Mapper
public interface DeviceInfoMapper {
	List<DeviceInfo> getDeviceInfo();
}
