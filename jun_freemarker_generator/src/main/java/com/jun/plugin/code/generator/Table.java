package com.jun.plugin.code.generator;



import java.util.List;

import lombok.Data;
@Data
public class Table {
	
	private String tableName;
    private String className;
	private String classComment;
	private List<FieldInfo> fieldList;

	private String sqlName;
	private String remarks;
	private String classNameLower;
	private List<Column> columnList ;
	 
}
