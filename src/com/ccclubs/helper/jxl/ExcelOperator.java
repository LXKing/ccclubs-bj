package com.ccclubs.helper.jxl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;

/**
 * 文件写入操作， Excel csv
 * @author 
 *
 */
public class ExcelOperator implements IExcelOperator {

	public enum ExcelOperatorType{  
	    READ,WRITE,NONE  
    }  
	
	public enum FileOperatorType{
		EXCEL,CSV
	}
	final static String CHARSET = "utf-8";
	private File file;
	
	protected ExcelOperatorType operateType = ExcelOperatorType.NONE;

	private Workbook book;
	
	private ExcelWriteData writeData;

	private FileOperatorType fileOperatorType = FileOperatorType.EXCEL;
	
	public void readExcel(Workbook book) throws ExcelOpException,Exception{
		
	}
	
	 /**
     * @param datas 封装着Object[]的列表, 一般是String内容.
     * @param title 每个sheet里的标题.
     */
    public void writeExcel(OutputStream out) throws ExcelOpException,Exception{
    	if(this.getWriteData()==null){
    		throw new IllegalArgumentException("写入数据为空！");
    	}
    	List<Object> list = this.getWriteData().getList(); 
    	String[] title = this.getWriteData().getTitle();
        if(list == null) {
            throw new IllegalArgumentException("要写入excel的数据不能为空！");
        }
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(out);
            WritableSheet ws = workbook.createSheet("sheet 1", 0);//创建sheet
            int rowNum = 0;    //要写的行，jxl操作excel时，第一行是从0开始，以此类推
            if(title != null) {
                putRow(ws, 0, title);//压入标题
                rowNum = 1;
            }
            for(int i=0; i<list.size(); i++, rowNum++) {//写sheet
                Object[] cells = (Object[]) list.get(i);
                putRow(ws, rowNum, cells);    //压一行到sheet
            }
            workbook.write();
            workbook.close();    //一定要关闭, 否则没有保存Excel
        } catch (RowsExceededException e) {
            System.out.println("jxl write RowsExceededException: "+e.getMessage());
        } catch (WriteException e) {
            System.out.println("jxl write WriteException: "+e.getMessage());
        } catch (IOException e) {
            System.out.println("jxl write file i/o exception!, cause by: "+e.getMessage());
        }
    }
    
    private void putRow(WritableSheet ws, int rowNum, Object[] cells) throws RowsExceededException, WriteException {
        for(int j=0; j<cells.length; j++) {//写一行
            Label cell = new Label(j, rowNum, ""+cells[j]);
            ws.addCell(cell);
        }
    }
	
	public void execute() throws ExcelOpException, Exception{
		 InputStream fs = null;
		 OutputStream out = null;
		 Workbook workBook = null;
		 try{
			 if(this.operateType == ExcelOperatorType.READ){
				 fs = new FileInputStream(file);
				 switch (this.fileOperatorType) {
					case EXCEL:
						workBook = Workbook.getWorkbook(fs);
						writeExcel(out);
						break;
				}
				 
			 }else if(this.operateType == ExcelOperatorType.WRITE){
				 
				 switch (this.fileOperatorType) {
					case EXCEL:
						out = new FileOutputStream(file);
						writeExcel(out);
						break;
					case CSV:
						writeCsv(file);
						break;
				}
			 }
		 }finally{
				if(workBook!=null){
					workBook.close();//记得关闭
				}
				if(fs!=null){
					try {
						fs.close();
					} catch (IOException e) {
						throw e;
					}
				}
				if(out!=null){
					try {
						out.close();
					} catch (IOException e) {
						throw e;
					}
				}
		}
	}
	
	private void writeCsv(File outFile) throws UnsupportedEncodingException, IOException {
		// TODO Auto-generated method stub
		
		BufferedWriter csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "GB2312"));
		if(this.getWriteData()==null){
    		throw new IllegalArgumentException("写入数据为空！");
    	}

	
		String[] titleList = this.getWriteData().getTitle();
		StringBuffer titleRow = new StringBuffer();
		for (String title : titleList) {
			titleRow.append(CSVEncode(title));
			titleRow.append(",");
		}
		titleRow.replace(titleRow.length()-1, titleRow.length(), "");
		Date startTime = new Date();
		csvWtriter.write(titleRow.toString());
		Logger.getRootLogger().error((new Date().getTime()-startTime.getTime())/1000l);
		csvWtriter.newLine();
		
		
		List<Object> dataList = this.getWriteData().getList();
		for (Object object : dataList) {
			titleRow = new StringBuffer();
			List<Object> list = (List<Object>) object;
			for (Object data : list) {
				titleRow.append(CSVEncode(data.toString()));
				titleRow.append(",");
			}
			titleRow.replace(titleRow.length()-1, titleRow.length(), "");
			csvWtriter.write(titleRow.toString());
			csvWtriter.newLine();
		}
		csvWtriter.flush();
		csvWtriter.close();
	}
	
	public static String CSVEncode(String in){
		if ( in == null )
			return "";
		in.replaceAll("&","&amp;");
		in.replaceAll("/","&quot;");
		in.replaceAll(",", "，");
		return in;
	}

	
	//=========================================================================================================
	public ExcelOperatorType getOperateType() {
		return operateType;
	}

	public ExcelWriteData getWriteData() {
		return writeData;
	}

	public IExcelOperator setWriteData(ExcelWriteData writeData) {
		this.writeData = writeData;
		return this;
	}

	public Workbook getBook() {
		return book;
	}

	public void setBook(Workbook book) {
		this.book = book;
	}
	
	@Override
	public IExcelOperator setOperateType(ExcelOperatorType operateType) {
		this.operateType = operateType;
		return this;
	}

	@Override
	public IExcelOperator setFile(File file) {
		this.file = file;
		return this;
	}

	@Override
	public ExcelOperator getOperator() {
		return this;
	}

	public File getFile() {
		return file;
	}

	@Override
	public IExcelOperator setFileOperateType(FileOperatorType fileOperatorType) {
		// TODO Auto-generated method stub
		this.fileOperatorType = fileOperatorType;
		return this;
	}

}
