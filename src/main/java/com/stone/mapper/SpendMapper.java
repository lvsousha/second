package com.stone.mapper;

import java.util.List;

import com.stone.model.Spend;

public interface SpendMapper {

	public Spend selectSpend(int id);
	public Integer insert(Spend spend);
	public Integer delete(int id);
	public List<Spend> selectSpends();
	public void update(Spend spend);

}