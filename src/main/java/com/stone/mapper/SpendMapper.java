package com.stone.mapper;

import java.util.List;
import java.util.Map;

import com.stone.model.Spend;

public interface SpendMapper {

	public Spend selectSpend(int id);
	public Integer insert(Spend spend);
	public Integer delete(int id);
	public List<Spend> selectSpends();
	public void update(Spend spend);
	public List<Spend> select(Map<String,Object> parameters);
	public Integer count(Map<String,Object> parameters);

}