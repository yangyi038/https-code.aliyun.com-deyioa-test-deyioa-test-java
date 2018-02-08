package com.framework.excelTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.framework.OptionDictSupport;
import com.fs.comm.model.City;
public class DoExcel{
	/**
	 * creates an {@link HSSFWorkbook} the specified OS filename.
	 */
	private static HSSFWorkbook readFile(File file) throws IOException {
		return new HSSFWorkbook(new FileInputStream(file));
	}
	public static void excelExport(List<?> listdata, HttpServletResponse response,
			String filename,String header,String headermate) throws Exception {
		javax.servlet.ServletOutputStream servletoutputstream = response
				.getOutputStream();
		response.reset();// 必须加，不然保存不了临时文件
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		if (filename != null && !filename.equals("")) {
			filename = new String((filename + ".xls").getBytes("gb2312"),"UTF-8");// 取消乱码
			response.addHeader("Content-Disposition", "attachment; filename="+ filename);
		}
		excelExportList(listdata,(OutputStream) (servletoutputstream),header,headermate);
	}
	
	public static void excelExportList(List listdata, OutputStream outputstream, String header_,String headermates_)throws Exception {

		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 在Excel 工作簿中建一工作表
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		// 设置单元格格式(文本)
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
		String[] headers = header_.split(",");
		String[] headermates = headermates_.split(",");
		// 在索引0的位置创建行（第一行）
		HSSFRow row = sheet.createRow(0);

		int ii = 0;// 有些列是隐藏的，所以ii为列的真正列号
		for (int i = 0; i < headers.length; i++) {
			String[] headermate = headermates[i].split("@");// 截取列名，获取列的属性值，例如：列名，是否为隐藏字段
			if(headermate.length>1){
				if (headermate[1].equals("true") || headermate[0].equals("cb")
						|| headermate[0].equals("act")) {// true为隐藏字段
					continue;
				}
			}
			HSSFCell cell = row.createCell(ii);// 第一列，标题
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(headers[i]);
			ii++;
		}

		try {
			int k = 1;// 行号
			// System.out.println("输出第"+k+"行");
			for (Iterator<?> iterator = listdata.iterator(); iterator.hasNext();) {

				Object obj2 = iterator.next();
				// 设置j行从第二行开始
				row = sheet.createRow(k);
				if(obj2 instanceof Map){
					Map<String, String> map = (Map) obj2;
					int kk = 0;// 有些列是隐藏的，所以ii为列的真正列号
					for (int i = 0; i < headermates.length; i++) {
						String[] headermate = headermates[i].split("@");
						String name = headermate[0];// 字段名
						if(headermate.length>1){
						String hidden = headermate[1];// 是否为隐藏字段
						if (hidden.equals("true") || name.equals("cb")
								|| name.equals("act")) {
							continue;
						}
						}
						Object obj1 = map.get(name);
						HSSFCell cell = row.createCell(kk);
						// 设置单元格属性
						cell.setCellStyle(cellStyle);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

						if (obj1 != null) {

							if (name.indexOf(".") > 0) {
								name = name.split("\\.")[1];
							}
							if (obj1.getClass().getName()
									.equals("java.lang.String")) {// 字符类型
								obj1 = OptionDictSupport.getCodeName(
										name, (String) obj1
												+ "");
								cell.setCellValue((String) obj1);
							} else if (obj1.getClass().getName()
									.equals("java.sql.Date")) {// 时间类型
								cell.setCellValue(obj1.toString());
							} else if (obj1.getClass().getName()
									.equals("java.lang.Integer")) {// 数字类型
								obj1 = OptionDictSupport.getCodeName(
										name,
										String.valueOf(obj1) + "");
								cell.setCellValue((String) obj1);
							}

						} else {
							cell.setCellValue("");
						}
						kk++;
					}
				}else{
					int kk = 0;// 有些列是隐藏的，所以ii为列的真正列号
					for (int i = 0; i < headermates.length; i++) {
						String[] headermate = headermates[i].split("@");
						String name = headermate[0];// 字段名
						String hidden = headermate[1];// 是否为隐藏字段
						if (hidden.equals("true") || name.equals("cb")
								|| name.equals("act")) {
							continue;
						}
						Class clazz = obj2.getClass();
						String first = (name).substring(0, 1).toUpperCase()+(name.toLowerCase()).substring(1);
						 Method m3 = clazz.getDeclaredMethod("get"+first);
						 Object obj1 =m3.invoke(obj2); 
						HSSFCell cell = row.createCell(kk);
						// 设置单元格属性
						cell.setCellStyle(cellStyle);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

						if (obj1 != null) {

							if (name.indexOf(".") > 0) {
								name = name.split("\\.")[1];
							}
							if (obj1.getClass().getName()
									.equals("java.lang.String")) {// 字符类型
								obj1 = OptionDictSupport.getCodeName(
										name, (String) obj1
												+ "");
								cell.setCellValue((String) obj1);
							} else if (obj1.getClass().getName()
									.equals("java.sql.Date")) {// 时间类型
								cell.setCellValue(obj1.toString());
							} else if (obj1.getClass().getName()
									.equals("java.lang.Integer")) {// 数字类型
								obj1 = OptionDictSupport.getCodeName(
										name,
										String.valueOf(obj1) + "");
								cell.setCellValue((String) obj1);
							}

						} else {
							cell.setCellValue("");
						}
						kk++;
					}
				}
				k++;
			}

			// 把相应的Excel 工作簿存盘
			workbook.write(outputstream);
			// 操作结束，关闭文件
			outputstream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputstream != null) {
				try {
					outputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static  String getCity(HttpServletRequest request,String code,String name){
		if(code==null||code.equals("")){
			return "";
		}
		 Map<String,Object> citytree=(Map<String,Object>)request.getSession().getServletContext().getAttribute("city");
		 String revalue="";
 		City city=(City)citytree.get(code.toString().substring(0, 2)+"0000");
 		revalue=city.getMc();
 		if(code.toString().substring(0, 2).equals("99")){
 			city=(City)citytree.get(code);
 			revalue=revalue+"-"+city.getMc();
 		}else{
 			if(!code.toString().substring(2, 4).equals("00")){
 	 			city=(City)citytree.get(code.toString().substring(0, 4)+"00");
 	 			revalue=revalue+"-"+city.getMc();
 	 		}
 	 		if(!code.toString().substring(4, 6).equals("00")){
 	 			city=(City)citytree.get(code);
 	 			revalue=revalue+"-"+city.getMc();
 	 		}
 	 		if(name.split("_").length>1){
 	 			if(name.split("_")[1].equals("s")){
 	 				if(revalue.split("-").length==2){
 	 	 				revalue=revalue.split("-")[0];
 	 	 			}else{
 	 	 				revalue="";
 	 	 			}
 	 			}
 	 			else if(name.split("_")[1].equals("sx")){
 	 				if(revalue.split("-").length==3){
 	 	 				revalue=revalue.split("-")[1]+revalue.split("-")[2];
 	 	 				
 	 	 			}else if(revalue.split("-").length==2){
 	 	 				revalue=revalue.split("-")[1];
 	 	 			}else{
 	 	 				revalue="";
 	 	 			}	
 	 			}else if(name.split("_")[1].equals("sf")){
 	 				if(revalue.split("-").length>1){
 	 					revalue=revalue.split("-")[0];
 	 	 			}	
 	 			}
 	 				
 	 		}
 		}
 		return revalue;
	} 
	 public static void main(String[] args) throws Exception {
		 DoExcel doExcel=new DoExcel();
	    }	
}
