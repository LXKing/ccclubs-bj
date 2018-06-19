package com.ccclubs.helper.jxl;

import java.io.File;

import com.ccclubs.helper.jxl.ExcelOperator.ExcelOperatorType;
import com.ccclubs.helper.jxl.ExcelOperator.FileOperatorType;

/**
 * @author joel
 * @category excel操作
 * @date 2012-12-23
 */
public interface IExcelOperator {

	/**
	 * 文件操作类型 读写
	 * @param operateType
	 * @return
	 */
	public IExcelOperator setOperateType(ExcelOperatorType operateType);
	
	public IExcelOperator setFile(File file);

	public ExcelOperator getOperator();
	
	/**
	 * 文件类型 Excel csv
	 * @param fileOperatorType
	 * @return
	 */
	public IExcelOperator setFileOperateType(FileOperatorType fileOperatorType);
	/**
	 * 写入数据
	 * @param writeData
	 * @return
	 */
	public IExcelOperator setWriteData(ExcelWriteData writeData);
}
