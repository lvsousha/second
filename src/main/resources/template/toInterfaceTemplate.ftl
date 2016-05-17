package com.zcl.dao;

import com.zcl.model.${table.name?cap_first};
import java.util.List;

public interface ${table.name?cap_first}Dao {

	public ${table.name?cap_first} select(int id);
	public ${table.name?cap_first} selectByName(String name);
	public List<${table.name?cap_first}> selectAll();
	public Integer insert(${table.name?cap_first} ${table.name});
	public Integer delete(int id);

}