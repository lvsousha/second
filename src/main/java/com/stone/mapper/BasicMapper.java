package com.stone.mapper;

import java.util.List;
import java.util.Map;

import com.stone.model.Basic;

public interface BasicMapper {

	public List<Basic> selectAll(Map<String,Integer> parameters);
	public List<Basic> select(Map<String,Object> parameters);
	public Integer count();
	public Basic select(String entname);

}
