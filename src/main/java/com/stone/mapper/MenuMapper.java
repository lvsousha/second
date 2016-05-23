package com.stone.mapper;

import com.stone.model.Menu;

public interface MenuMapper {

	public Menu selectMenu(int id);
	public Integer insert(Menu menu);
	public Integer deleteMenu(int id);

}