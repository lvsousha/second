package com.stone.mapper;

import com.stone.model.${table.tablename?cap_first};

public interface ${table.tablename?cap_first}Mapper {

	public ${table.tablename?cap_first} select${table.tablename?cap_first}(int id);
	public Integer insert(${table.tablename?cap_first} ${table.tablename});
	public Integer delete${table.tablename?cap_first}(int id);

}