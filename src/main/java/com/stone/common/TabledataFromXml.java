package com.stone.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.stone.model.Field;
import com.stone.model.Table;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TabledataFromXml {

	public static void main(String[] args) {
		TabledataFromXml tdfx = new TabledataFromXml();
		List<Table> tables = tdfx.loadData("src/main/resources/tables.xml");
		for(Table table : tables){
			System.out.println(table.getTablename());
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("table", table);
			tdfx.printToSql("toSqlTemplate.ftl", root, table.getTablename());
		}
	}
	
	public List<Table> loadData(String path){
		List<Table> tables = new ArrayList<Table>();
		Map<String, Table> tableMaps = new HashMap<String, Table>();
		SAXReader reader = new SAXReader();
		try{
			Document document = reader.read(new File(path));
			Element root = document.getRootElement();
			List<Element> table_nodes = root.elements();
			for(Element table_node : table_nodes){
				Table table = new Table();
				List<String> foreigns = new ArrayList<String>();
				List<String> indexs = new ArrayList<String>();
				List<Field> fields = new ArrayList<Field>();
				List<String> javaPackages = new ArrayList<String>();
				Boolean hasDate = false;
				Boolean hasDecimal = false;
				String columnPrefix = "";
				List<Attribute> table_node_attributes = table_node.attributes();
				table.setPrefix("sec_");
				for(Attribute table_node_attribute : table_node_attributes){
					String name = table_node_attribute.getName();
					String value = table_node_attribute.getValue();
					if(name.equals("tablename"))
						table.setTablename(value);
					if(name.equals("prefix"))
						columnPrefix = value;
				}
//				System.out.println();
				List<Element> field_nodes = table_node.elements();
				for(Element field_node : field_nodes){
					Field field = new Field();
					field.setPrefix(columnPrefix);
					List<Attribute> field_node_attributes = field_node.attributes();
					for(Attribute field_node_attribute : field_node_attributes){
						String name = field_node_attribute.getName();
						String value = field_node_attribute.getValue();
						if(name.equals("name"))
							field.setName(value);
						if(name.equals("isNull"))
							field.setIsNull(value.equals("1")?"is null":"is not null");
						if(name.endsWith("key"))
							field.setKey(value);
//						if(name.equals("increase"))
//							field.setIncrease(value.equals("true")?true:false);
						if(name.equals("key"))
							field.setKey(value);
						if(name.equals("defaultValue"))
							field.setDefaultValue(value);
						if(name.equals("references"))
							field.setReferences(value);
						if(name.equals("length"))
							field.setLength(value);
						if(name.equals("precision"))
							field.setPrecision(value);
						if(name.equals("sacle"))
							field.setScale(value);
					}
					if(field_node.getName().equals("int")){
						field.setJdbctype("int");
						field.setJavatype("Integer");
						field.setInserttype("INTEGER");
					}
					if(field_node.getName().equals("varchar")){
						if(field.getLength() == null)
							field.setLength("64");
						field.setJdbctype("varchar("+field.getLength()+")");
						field.setJavatype("String");
						field.setInserttype("VARCHAR");
					}
					if(field_node.getName().equals("datetime")){
						field.setJdbctype("datetime");
						field.setJavatype("Date");
						field.setInserttype("DATE");
						hasDate = true;
					}
					if(field_node.getName().equals("decimal")){
						if(field.getPrecision() == null)
							field.setPrecision("64");
						if(field.getScale() == null)
							field.setScale("4");
						field.setJdbctype("decimal("+field.getPrecision()+","+field.getScale()+")");
						field.setJavatype("BigDecimal");
						field.setInserttype("DECIMAL");
						hasDecimal = true;
					}
					if(field_node.getName().equals("bool")){
						field.setJdbctype("bit");
						field.setJavatype("Boolean");
						field.setInserttype("BIT");
					}
					if(field_node.getName().equals("ref")){
						field.setJdbctype("int");
						field.setJavatype(FileUtil.firstToUpcase(field.getReferences()));
//						field.setConstraint("foreign");
					}
					field.setSimpleConstraint(field.getIsNull());
					if(field.getKey()!=null && field.getKey().equals("primary"))
						field.setSimpleConstraint(field.getIsNull()+" IDENTITY PRIMARY KEY");
					if(field.getKey()!=null && field.getKey().equals("unique"))
						field.setSimpleConstraint(field.getIsNull()+" UNIQUE");
					if(field.getKey()!=null && field.getKey().equals("foreign")){
						Table t = tableMaps.get(field.getReferences());
						if(t != null){
							foreigns.add("foreign key ("+field.getPrefix()+field.getName()+") references "+t.getPrefix()+t.getTablename()+"s("+t.getColumnPrefix()+"id)");							
						}else if(t == null){
							foreigns.add("foreign key ("+field.getPrefix()+field.getName()+") references "+table.getPrefix()+table.getTablename()+"s("+columnPrefix+"id)");
						}
					}
//					if(field.getIndex())
//						indexs.add("create index idx_"+field.getName()+" on "+table.getPrefix()+table.getTablename()+"("+field.getPrefix()+field.getName()+");");
					fields.add(field);
				}
				if(hasDate)
					javaPackages.add("java.util.Date");
				if(hasDecimal)
					javaPackages.add("java.math.BigDecimal");
				table.setJavaPackages(javaPackages);
				table.setFields(fields);
				table.setForeigns(foreigns);
				table.setIndexs(indexs);
				table.setColumnPrefix(columnPrefix);
				tables.add(table);
				tableMaps.put(table.getTablename(), table);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return tables;
	}

	public void printToSql(String templateName, Map<String, Object> root, String outFileName) {
        FileWriter out = null;
        try {
        	File outfile = new File("src/main/resources/schema/" + outFileName+".sql");
            out = new FileWriter(outfile);
            Template temp = this.getTemplate(templateName);
            temp.process(root, out);
    		System.out.println("成功生成" + outFileName+".sql");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	public Template getTemplate(String name) {
        try {
            Configuration cfg = new Configuration();
            cfg.setClassForTemplateLoading(this.getClass(), "/template");
            Template temp = cfg.getTemplate(name);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
