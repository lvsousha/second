package com.stone.mapper;

import java.util.List;

import com.stone.model.Menu;

public interface MenuMapper {

	public Menu selectMenu(int id);
	public Integer insert(Menu menu);
	public Integer deleteMenu(int id);
	public List<Menu> selectParentIsNull();
	public List<Menu> selectMenuByParent(int id);

}