package com.jun.plugin.codegenerator.admin.core;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jun.plugin.codegenerator.admin.core.model.ClassInfo;
import com.jun.plugin.codegenerator.admin.core.util.GeneratorUtils;
import com.jun.plugin.codegenerator.admin.core.util.TableParseUtil;

/**
 * code generate tool
 */
public class CodeGeneratorTool {

	/**
	 * process Table Into ClassInfo
	 *
	 * @param tableSql
	 * @return
	 */
	public static ClassInfo processTableIntoClassInfo(String tableSql) throws IOException {
		return TableParseUtil.processTableIntoClassInfo(tableSql);
	}
	

}