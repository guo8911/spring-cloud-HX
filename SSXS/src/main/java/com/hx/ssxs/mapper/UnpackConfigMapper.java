package com.hx.ssxs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hx.ssxs.entity.UnpackConfig;

@Mapper
public interface UnpackConfigMapper {
	List<UnpackConfig> getConfigDis(int sat_id);
}
