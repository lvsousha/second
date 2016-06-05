package com.stone.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stone.model.table.Column;
import com.stone.model.table.TableFilter;

@Service
public class DataTableService {

	
	public Map<String, Object> getFilter(TableFilter filter, String columnPrefix){
		String conditions = "";
		Map<String, Object> condition = new HashMap<>();
		Integer start = filter.getStart()+1;
		Integer end = filter.getLength()+filter.getStart();
//		Search search = filtser.getSearch();
		Column[] columns = filter.getColumns();
		for(Column column : columns){
			String key = column.getData();
			String value = column.getSearch().getValue();
			if(value != null && !value.equals("")){
//				condition.put(key, "%"+value+"%");	
				conditions +=" and "+columnPrefix+key+" like '%"+value+"%' ";
			}
		}
		condition.put("condition", conditions);
		condition.put("start", start);
		condition.put("limit", end);
		
		return condition;
	}
	
}
