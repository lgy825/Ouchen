package com.ouchen.core.util.excel;

import com.google.common.collect.Lists;
import com.ouchen.core.reflection.Reflections;
import com.ouchen.core.util.Encodes;
import com.ouchen.core.util.excel.annotation.ExcelField;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 导出Excel文件（导出“XLSX”格式，支持大数据量导出   @see org.apache.poi.ss.SpreadsheetVersion）
 */
public class ExportExcel {

	private static Logger log = LoggerFactory.getLogger(ExportExcel.class);

	/**
	 * 工作薄对象
	 */
	private SXSSFWorkbook wb;

	/**
	 * 工作表对象
	 */
	private SXSSFSheet sheet;

	/**
	 * 工作表对象(多个sheet页)
	 */
	private SXSSFSheet[] sheets;

	/**
	 * 样式列表
	 */
	private Map<String, CellStyle> styles;

	/**
	 * 当前行号
	 */
	private int rownum;

	/**
	 * 当前行号(多个sheet页)
	 */
	private int[] rownums;

	/**
	 * 注解列表（Object[]{ ExcelField, Field/Method }）
	 */
	List<Object[]> annotationList = Lists.newArrayList();

	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param cls 实体对象，通过annotation.ExportField获取标题
	 */
	public ExportExcel(String title, Class<?> cls){
		this(title, cls, 1);
	}

	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param cls 实体对象，通过annotation.ExportField获取标题
	 * @param type 导出类型（1:导出数据；2：导出模板）
	 * @param groups 导入分组
	 */
	public ExportExcel(String title, Class<?> cls, int type, int... groups){
		// Get annotation field
		Field[] fs = cls.getDeclaredFields();
		for (Field f : fs){
			ExcelField ef = f.getAnnotation(ExcelField.class);
			if (ef != null && (ef.type()==0 || ef.type()==type)){
				if (groups!=null && groups.length>0){
					boolean inGroup = false;
					for (int g : groups){
						if (inGroup){
							break;
						}
						for (int efg : ef.groups()){
							if (g == efg){
								inGroup = true;
								annotationList.add(new Object[]{ef, f});
								break;
							}
						}
					}
				}else{
					annotationList.add(new Object[]{ef, f});
				}
			}
		}
		// Get annotation method
		Method[] ms = cls.getDeclaredMethods();
		for (Method m : ms){
			ExcelField ef = m.getAnnotation(ExcelField.class);
			if (ef != null && (ef.type()==0 || ef.type()==type)){
				if (groups!=null && groups.length>0){
					boolean inGroup = false;
					for (int g : groups){
						if (inGroup){
							break;
						}
						for (int efg : ef.groups()){
							if (g == efg){
								inGroup = true;
								annotationList.add(new Object[]{ef, m});
								break;
							}
						}
					}
				}else{
					annotationList.add(new Object[]{ef, m});
				}
			}
		}
		// Field sorting
		Collections.sort(annotationList, new Comparator<Object[]>() {
			public int compare(Object[] o1, Object[] o2) {
				return new Integer(((ExcelField)o1[0]).sort()).compareTo(
						new Integer(((ExcelField)o2[0]).sort()));
			};
		});
		// Initialize
		List<String> headerList = Lists.newArrayList();
		for (Object[] os : annotationList){
			String t = ((ExcelField)os[0]).title();
			// 如果是导出模板，则去掉注释
			if (type==1){
				String[] ss = StringUtils.split(t, "**", 2);
				if (ss.length==2){
					t = ss[0];
				}
			}
			headerList.add(t);
		}
		initialize(title, headerList);
	}

	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headers 表头数组
	 */
	public ExportExcel(String title, String[] headers) {
		initialize(title, Lists.newArrayList(headers));
	}

	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	public ExportExcel(String title, List<String> headerList) {
		initialize(title, headerList);
	}

	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	public ExportExcel(String title, List<String> headerList, List<String> parmList) {
		initialize1(title, headerList,parmList);
	}

	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 * @param parmList 统计内容
	 * @param parmTimeList 统计时间内容
	 */
	public ExportExcel(String title, List<String> headerList, List<String> parmList, List<String> parmTimeList) {
		initialize2(title, headerList,parmList,parmTimeList);
	}

	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerArrs 表头数组
	 * @param parmList 查询条件名称
	 * @param statList 统计列表
	 */
	public ExportExcel(String title,
					   String[][] headerArrs,
					   List<String> parmList,
					   List<String> statList) {
		initialize3(title, headerArrs,parmList,statList);
	}

	/**
	 * 构造函数
	 * @param titles 表格标题，传“空值”，表示无标题
	 * @param headerArrsList 表头数组(多个sheet)
	 * @param parmList 查询条件名称
	 * @param statList 统计列表
	 */
	public ExportExcel(String[] titles,
					   String[][][] headerArrsList,
					   List<String> parmList,
					   List<String> statList) {
		initialize3(titles, headerArrsList,parmList,statList);
	}

	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
//	public ExportExcel(String title, List<List<String>> headers, List<int[]> address) {
//		initialize(title, headers, address);
//	}

	/**
	 * 初始化函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	private void initialize(String title, List<String> headerList) {
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		// Create title
		if (StringUtils.isNotBlank(title)){
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
					titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
		}
		// Create header
		if (headerList == null){
			throw new RuntimeException("headerList not null!");
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(16);
		for (int i = 0; i < headerList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(styles.get("header"));
			String[] ss = StringUtils.split(headerList.get(i), "**", 2);
			if (ss.length==2){
				cell.setCellValue(ss[0]);
				Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
						new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
				comment.setString(new XSSFRichTextString(ss[1]));
				cell.setCellComment(comment);
			}else{
				cell.setCellValue(headerList.get(i));
			}
			sheet.autoSizeColumn(i);
		}
		for (int i = 0; i < headerList.size(); i++) {
			int colWidth = sheet.getColumnWidth(i)*2;
	        sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
		}
		log.debug("Initialize success.");
	}

	/**
	 * 初始化函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	private void initialize(String title, List<List<String>> headers, List<int[]> address) {
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		// Create title
		if (StringUtils.isNotBlank(title)){
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
					titleRow.getRowNum(), titleRow.getRowNum(), headers.get(0).size()-1));
		}
		// Create header
		if (headers == null){
			throw new RuntimeException("headers not null!");
		}
		for (int k = 0; k < headers.size(); k++) {
			List<String> headerList = headers.get(k) ;
			Row headerRow = sheet.createRow(rownum++);
			headerRow.setHeightInPoints(16);
			for (int i = 0; i < headerList.size(); i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellStyle(styles.get("header2"));
				String[] ss = StringUtils.split(headerList.get(i), "**", 2);
				if (ss.length==2){
					cell.setCellValue(ss[0]);
					Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
							new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
					comment.setString(new XSSFRichTextString(ss[1]));
					cell.setCellComment(comment);
				}else{
					cell.setCellValue(headerList.get(i));
				}
				sheet.autoSizeColumn(i);
			}
			for (int i = 0; i < headerList.size(); i++) {
				int colWidth = sheet.getColumnWidth(i)*2;
		        sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
			}
		}
		for (int[] ary : address) {
			sheet.addMergedRegion(new CellRangeAddress(ary[0], ary[1], ary[2], ary[3]));
		}
		log.debug("Initialize success.");
	}

	/**
	 * 创建表格样式
	 * @param wb 工作薄对象
	 * @return 样式列表
	 */
	private Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		styles.put("title", style);

		style = wb.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		Font dataFont = wb.createFont();
		dataFont.setFontName("Arial");
		dataFont.setFontHeightInPoints((short) 10);
		style.setFont(dataFont);
		styles.put("data", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_LEFT);
		styles.put("data1", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_CENTER);
		styles.put("data2", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		styles.put("data3", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
//		style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font headerFont = wb.createFont();
		headerFont.setFontName("Arial");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(headerFont);
		styles.put("header", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_CENTER);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(headerFont);
		style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex()) ;
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styles.put("header2", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		headerFont.setFontName("Arial");
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		headerFont.setFontHeightInPoints((short) 15);
		styles.put("param", style);
		return styles;
	}

	/**
	 * 添加一行
	 * @return 行对象
	 */
	public Row addRow(){
		return sheet.createRow(rownum++);
	}

	/**
	 * 添加一行(多个sheet)
	 * @return 行对象
	 */
	public Row addRow(int sheetNum){
		return sheets[sheetNum].createRow(rownums[sheetNum]++);
	}

	/**
	 * 添加一个单元格
	 * @param row 添加的行
	 * @param column 添加列号
	 * @param val 添加值
	 * @return 单元格对象
	 */
	public Cell addCell(Row row, int column, Object val){
		return this.addCell(row, column, val, 0, Class.class);
	}

	/**
	 * 添加一个单元格
	 * @param row 添加的行
	 * @param column 添加列号
	 * @param val 添加值
	 * @param align 对齐方式（1：靠左；2：居中；3：靠右）
	 * @return 单元格对象
	 */
	public Cell addCell(Row row, int column, Object val, int align, Class<?> fieldType){
		Cell cell = row.createCell(column);
		CellStyle style = styles.get("data"+(align>=1&&align<=3?align:""));
		try {
			if (val == null){
				cell.setCellValue("");
			} else if (val instanceof String) {
				cell.setCellValue((String) val);
			} else if (val instanceof Integer) {
				cell.setCellValue((Integer) val);
			} else if (val instanceof Long) {
				cell.setCellValue((Long) val);
			} else if (val instanceof Double) {
				cell.setCellValue((Double) val);
			} else if (val instanceof Float) {
				cell.setCellValue((Float) val);
			} else if (val instanceof Date) {
				DataFormat format = wb.createDataFormat();
	            style.setDataFormat(format.getFormat("yyyy-MM-dd"));
				cell.setCellValue((Date) val);
			} else {
				if (fieldType != Class.class){
					cell.setCellValue((String)fieldType.getMethod("setValue", Object.class).invoke(null, val));
				}else{
					cell.setCellValue((String) Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(),
						"fieldtype."+val.getClass().getSimpleName()+"Type")).getMethod("setValue", Object.class).invoke(null, val));
				}
			}
		} catch (Exception ex) {
			log.info("Set cell value ["+row.getRowNum()+","+column+"] error: " + ex.toString());
			cell.setCellValue(val.toString());
		}
		cell.setCellStyle(style);
		return cell;
	}

	/**
	 * 添加数据（通过annotation.ExportField添加数据）
	 * @return list 数据列表
	 */
	public <E> ExportExcel setDataList(List<E> list){
		for (E e : list){
			int colunm = 0;
			Row row = this.addRow();
			StringBuilder sb = new StringBuilder();
			for (Object[] os : annotationList){
				ExcelField ef = (ExcelField)os[0];
				Object val = null;
				// Get entity value
				try{
					if (StringUtils.isNotBlank(ef.value())){
						val = Reflections.invokeGetter(e, ef.value());
					}else{
						if (os[1] instanceof Field){
							val = Reflections.invokeGetter(e, ((Field)os[1]).getName());
						}else if (os[1] instanceof Method){
							val = Reflections.invokeMethod(e, ((Method)os[1]).getName(), new Class[] {}, new Object[] {});
						}
					}
					// If is dict, get dict label
//					if (StringUtils.isNotBlank(ef.dictType())){
//						val = DictUtils.getDictLabel(val==null?"":val.toString(), ef.dictType(), "");
//					}
				}catch(Exception ex) {
					// Failure to ignore
					log.info(ex.toString());
					val = "";
				}
				this.addCell(row, colunm++, val, ef.align(), ef.fieldType());
				sb.append(val + ", ");
			}
			log.debug("Write success: ["+row.getRowNum()+"] "+sb.toString());
		}
		return this;
	}

	/**
	 * 添加数据,直接把说句写入到文件
	 * @param datas
	 * @return
	 */
	public <E> ExportExcel setDataAry(List<String[]> datas){
		for (String[] strs : datas){
			int colunm = 0;
			Row row = this.addRow();
			for (int i = 0; i < strs.length; i++){
				this.addCell(row, colunm++, strs[i], 2, null);
			}
		}
		return this;
	}

	/**
	 * 输出数据流
	 * @param os 输出数据流
	 */
	public ExportExcel write(OutputStream os) throws IOException {
		wb.write(os);
		return this;
	}

	/**
	 * 输出到客户端
	 * @param fileName 输出文件名
	 */
	public ExportExcel write(HttpServletResponse response, String fileName) throws IOException {
		response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+ Encodes.urlEncode(fileName));
		write(response.getOutputStream());
		return this;
	}

	/**
	 * 输出到文件
     */
	public ExportExcel writeFile(String name) throws FileNotFoundException, IOException {
		FileOutputStream os = new FileOutputStream(name);
		this.write(os);
		return this;
	}

	/**
	 * 内容写入到二进制数组中
	 * @return
	 */
	public byte[] writeToBtye() {
		try {
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			wb.write(byteArray);
			return byteArray.toByteArray() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null ;
	}

	/**
	 * 清理临时文件
	 */
	public ExportExcel dispose(){
		wb.dispose();
		return this;
	}


	/**
	 * 初始化函数-标头+独立一行
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	private void initialize1(String title, List<String> headerList, List<String> parmList) {
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		// Create title
		if (StringUtils.isNotBlank(title)){
			//标题
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
					titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
			//独立一行
			Row parmRow = sheet.createRow(rownum++);
			parmRow.setHeightInPoints(18);
			for (int i = 0; i < parmList.size(); i++) {
				Cell parmCell = parmRow.createCell(i);
				String[] ss = StringUtils.split(headerList.get(i), "**", 2);
				if (ss.length==2){
					parmCell.setCellValue(ss[0]);
					parmCell.setCellStyle(styles.get("param"));
					Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
							new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
					comment.setString(new XSSFRichTextString(ss[1]));
					parmCell.setCellComment(comment);
				}else{
					parmCell.setCellValue(parmList.get(i));
				}
				sheet.trackAllColumnsForAutoSizing();
				sheet.autoSizeColumn(i);
			}
			for (int i = 0; i < parmList.size(); i++) {
//				if (i == parmList.size()-2) {
//					sheet.addMergedRegion(new CellRangeAddress(i,
//							i, i, parmList.size()-1));
//				}else{
					int colWidth = sheet.getColumnWidth(i)*2;
					sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
//				}
			}

		}
		// Create header
		if (headerList == null){
			throw new RuntimeException("headerList not null!");
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(18);
		for (int i = 0; i < headerList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(styles.get("header"));
			String[] ss = StringUtils.split(headerList.get(i), "**", 2);
			if (ss.length==2){
				cell.setCellValue(ss[0]);
				Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
						new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
				comment.setString(new XSSFRichTextString(ss[1]));
				cell.setCellComment(comment);
			}else{
				cell.setCellValue(headerList.get(i));
			}
			sheet.autoSizeColumn(i);
		}
		for (int i = 0; i < headerList.size(); i++) {
			int colWidth = sheet.getColumnWidth(i)*2;
	        sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
		}
		log.debug("Initialize success.");
	}


	/**
	 * 初始化函数-标头+独立一行
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	private void initialize2(String title, List<String> headerList, List<String> parmList, List<String> parmTimeList) {
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		// Create title
		if (StringUtils.isNotBlank(title)){
			//标题
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
					titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));

			//独立一行：统计时间
			Row parmTRow = sheet.createRow(rownum++);
			parmTRow.setHeightInPoints(18);
			for (int i = 0; i < parmTimeList.size(); i++) {
				Cell parmCell = parmTRow.createCell(i);
				String[] ss = StringUtils.split(headerList.get(i), "**", 2);
				if (ss.length==2){
					parmCell.setCellValue(ss[0]);
					parmCell.setCellStyle(styles.get("param"));
					Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
							new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
					comment.setString(new XSSFRichTextString(ss[1]));
					parmCell.setCellComment(comment);
				}else{
					parmCell.setCellValue(parmTimeList.get(i));
				}
				sheet.autoSizeColumn(i);
			}
			for (int i = 0; i < parmTimeList.size(); i++) {
				if (i == parmTimeList.size()-1) {
					sheet.addMergedRegion(new CellRangeAddress(i,
							i, i, parmTimeList.size()-1));
				}else{
					int colWidth = sheet.getColumnWidth(i)*2;
					sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
				}
			}

			//独立一行
			Row parmRow = sheet.createRow(rownum++);
			parmRow.setHeightInPoints(18);
			for (int i = 0; i < parmList.size(); i++) {
				Cell parmCell = parmRow.createCell(i);
				String[] ss = StringUtils.split(headerList.get(i), "**", 2);
				if (ss.length==2){
					parmCell.setCellValue(ss[0]);
					parmCell.setCellStyle(styles.get("param"));
					Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
							new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
					comment.setString(new XSSFRichTextString(ss[1]));
					parmCell.setCellComment(comment);
				}else{
					parmCell.setCellValue(parmList.get(i));
				}
				sheet.autoSizeColumn(i);
			}
			for (int i = 0; i < parmList.size(); i++) {
				if (i == parmList.size()-1) {
					sheet.addMergedRegion(new CellRangeAddress(i,
							i, i, parmList.size()-1));
				}else{
					int colWidth = sheet.getColumnWidth(i)*2;
					sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
				}
			}

		}
		// Create header
		if (headerList == null){
			throw new RuntimeException("headerList not null!");
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(18);
		for (int i = 0; i < headerList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(styles.get("header"));
			String[] ss = StringUtils.split(headerList.get(i), "**", 2);
			if (ss.length==2){
				cell.setCellValue(ss[0]);
				Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
						new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
				comment.setString(new XSSFRichTextString(ss[1]));
				cell.setCellComment(comment);
			}else{
				cell.setCellValue(headerList.get(i));
			}
			sheet.autoSizeColumn(i);
		}
		for (int i = 0; i < headerList.size(); i++) {
			int colWidth = sheet.getColumnWidth(i)*2;
	        sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
		}
		log.debug("Initialize success.");
	}

	/**
	 * 初始化函数-标头+独立一行
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerArrs 表头数组
	 * @param parmList 查询条件名称
	 * @param statList 统计列表
	 */
	private void initialize3(String title,
							 String[][] headerArrs,
							 List<String> parmList,
							 List<String> statList) {
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		// Create title
		if (StringUtils.isNotBlank(title)) {
			//标题
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
					titleRow.getRowNum(), titleRow.getRowNum(),
					headerArrs[headerArrs.length - 1].length - 1));
		}

		//独立一行：查询条件
		if(parmList != null && !parmList.isEmpty()) {
			Row parmTRow = sheet.createRow(rownum++);
			parmTRow.setHeightInPoints(18);
			for (int i = 0; i < parmList.size(); i++) {
				Cell parmCell = parmTRow.createCell(i);
				parmCell.setCellValue(parmList.get(i));
				sheet.autoSizeColumn(i);
			}
			for (int i = 0; i < parmList.size(); i++) {
				if (i == parmList.size() - 1) {
					sheet.addMergedRegion(new CellRangeAddress(i,
							i, i, parmList.size() - 1));
				} else {
					int colWidth = sheet.getColumnWidth(i) * 2;
					sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
				}
			}
		}

		// Create header
		if (headerArrs == null){
			throw new RuntimeException("headerList not null!");
		}

		for (String[] headerArr : headerArrs) {
			Row headerRow = sheet.createRow(rownum++);
			headerRow.setHeightInPoints(18);
			for (int i = 0; i < headerArr.length; i++) {
				String[] ss = StringUtils.split(headerArr[i], "**", 3);
				if (ss.length == 3) {
					int firstCol = Integer.valueOf(ss[1]);
					int lastCol = Integer.valueOf(ss[2]);
					Cell cell = headerRow.createCell(firstCol);
					cell.setCellStyle(styles.get("header"));
					cell.setCellValue(ss[0]);
					sheet.addMergedRegion(new CellRangeAddress(
							headerRow.getRowNum(), headerRow.getRowNum(), firstCol, lastCol));
				} else {
					Cell cell = headerRow.createCell(i);
					cell.setCellStyle(styles.get("header"));
					cell.setCellValue(headerArr[i]);
				}
				sheet.autoSizeColumn(i);
			}
			for (int i = 0; i < headerArr.length; i++) {
				int colWidth = sheet.getColumnWidth(i) * 2;
				sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
			}
		}

		//独立一行：合计
		if(statList != null && !statList.isEmpty()) {
			Row statRow = sheet.createRow(rownum++);
			statRow.setHeightInPoints(18);
			for (int i = 0; i < statList.size(); i++) {
				String[] ss = StringUtils.split(statList.get(i), "**", 3);
				if (ss.length == 3) {
					int firstCol = Integer.valueOf(ss[1]);
					int lastCol = Integer.valueOf(ss[2]);
					Cell cell = statRow.createCell(firstCol);
					cell.setCellStyle(styles.get("data2"));
					cell.setCellValue(ss[0]);
					sheet.addMergedRegion(new CellRangeAddress(
							statRow.getRowNum(), statRow.getRowNum(), firstCol, lastCol));
				} else {
					Cell cell = statRow.createCell(i);
					cell.setCellValue(statList.get(i));
					cell.setCellStyle(styles.get("data2"));
				}
				sheet.autoSizeColumn(i);
			}
			for (int i = 0; i < statList.size(); i++) {
				if (i == statList.size() - 1) {
					sheet.addMergedRegion(new CellRangeAddress(i,
							i, i, statList.size() - 1));
				} else {
					int colWidth = sheet.getColumnWidth(i) * 2;
					sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
				}
			}
		}

		log.debug("Initialize success.");
	}
	/**
	 * 初始化函数-标头+独立一行(多个sheet页)
	 * @param titles 表格标题，传“空值”，表示无标题
	 * @param headerArrsList 表头数组
	 * @param parmList 查询条件名称
	 * @param statList 统计列表
	 */
	private void initialize3(String[] titles,
							 String[][][] headerArrsList,
							 List<String> parmList,
							 List<String> statList) {

		if(headerArrsList.length == 1) {
			initialize3(titles[0], headerArrsList[0], parmList, statList);
		} else {
			this.wb = new SXSSFWorkbook(500);
			this.styles = createStyles(wb);
			this.sheets = new SXSSFSheet[headerArrsList.length];
			this.rownums = new int[headerArrsList.length];

			for(int hi=0; hi < headerArrsList.length; hi++) {
				String[][] headerArrs = headerArrsList[hi];
				this.rownums[hi] = 0;

				this.sheets[hi] = wb.createSheet(titles[hi]);
				// Create title
				if (StringUtils.isNotBlank(titles[hi])) {
					//标题
					Row titleRow = this.sheets[hi].createRow(rownums[hi]++);
					titleRow.setHeightInPoints(30);
					Cell titleCell = titleRow.createCell(0);
					titleCell.setCellStyle(styles.get("title"));
					titleCell.setCellValue(titles[hi]);
					this.sheets[hi].addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
							titleRow.getRowNum(), titleRow.getRowNum(),
							headerArrs[headerArrs.length - 1].length - 1));
				}

				//独立一行：查询条件
				if(parmList != null && !parmList.isEmpty()) {
					Row parmTRow = this.sheets[hi].createRow(rownums[hi]++);
					parmTRow.setHeightInPoints(18);
					for (int i = 0; i < parmList.size(); i++) {
						Cell parmCell = parmTRow.createCell(i);
						parmCell.setCellValue(parmList.get(i));
						this.sheets[hi].autoSizeColumn(i);
					}
					for (int i = 0; i < parmList.size(); i++) {
						if (i == parmList.size() - 1) {
							this.sheets[hi].addMergedRegion(new CellRangeAddress(i,
									i, i, parmList.size() - 1));
						} else {
							int colWidth = this.sheets[hi].getColumnWidth(i) * 2;
							this.sheets[hi].setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
						}
					}
				}

				// Create header
				if (headerArrs == null){
					throw new RuntimeException("headerList not null!");
				}

				for (String[] headerArr : headerArrs) {
					Row headerRow = this.sheets[hi].createRow(rownums[hi]++);
					headerRow.setHeightInPoints(18);
					for (int i = 0; i < headerArr.length; i++) {
						String[] ss = StringUtils.split(headerArr[i], "**", 3);
						if (ss.length == 3) {
							int firstCol = Integer.valueOf(ss[1]);
							int lastCol = Integer.valueOf(ss[2]);
							Cell cell = headerRow.createCell(firstCol);
							cell.setCellStyle(styles.get("header"));
							cell.setCellValue(ss[0]);
							this.sheets[hi].addMergedRegion(new CellRangeAddress(
									headerRow.getRowNum(), headerRow.getRowNum(), firstCol, lastCol));
						} else {
							Cell cell = headerRow.createCell(i);
							cell.setCellStyle(styles.get("header"));
							cell.setCellValue(headerArr[i]);
						}
						this.sheets[hi].autoSizeColumn(i);
					}
					for (int i = 0; i < headerArr.length; i++) {
						int colWidth = this.sheets[hi].getColumnWidth(i) * 2;
						this.sheets[hi].setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
					}
				}

				//独立一行：合计
				if(statList != null && !statList.isEmpty()) {
					Row statRow = this.sheets[hi].createRow(rownums[hi]++);
					statRow.setHeightInPoints(18);
					for (int i = 0; i < statList.size(); i++) {
						String[] ss = StringUtils.split(statList.get(i), "**", 3);
						if (ss.length == 3) {
							int firstCol = Integer.valueOf(ss[1]);
							int lastCol = Integer.valueOf(ss[2]);
							Cell cell = statRow.createCell(firstCol);
							cell.setCellStyle(styles.get("data2"));
							cell.setCellValue(ss[0]);
							this.sheets[hi].addMergedRegion(new CellRangeAddress(
									statRow.getRowNum(), statRow.getRowNum(), firstCol, lastCol));
						} else {
							Cell cell = statRow.createCell(i);
							cell.setCellStyle(styles.get("data2"));
							cell.setCellValue(statList.get(i));
						}
						this.sheets[hi].autoSizeColumn(i);
					}
					for (int i = 0; i < statList.size(); i++) {
						if (i == statList.size() - 1) {
							this.sheets[hi].addMergedRegion(new CellRangeAddress(i,
									i, i, statList.size() - 1));
						} else {
							int colWidth = this.sheets[hi].getColumnWidth(i) * 2;
							this.sheets[hi].setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
						}
					}
				}
			}
		}

		log.debug("Initialize success.");
	}

	/**
	 * 导出测试
	 */
	public static void main(String[] args) throws Throwable {
		
		List<String> headerList = Lists.newArrayList();
		for (int i = 1; i <= 3; i++) {
			headerList.add("表头"+i);
		}
		
		

		ExportExcel ee = new ExportExcel("表格标题", headerList);
		
		
		List<String> list1 = new ArrayList<String>();
		list1.add("1");
		list1.add("2");
		list1.add("3");
		List<String> list2 = new ArrayList<String>();
		list2.add("a");
		list2.add("b");
		list2.add("c");
		
		List<List<String>> list = new ArrayList<List<String>>();
		list.add(list1);
		list.add(list2);
		
		for (int i = 0; i < list.size(); i++) {
			Row row = ee.addRow();
			for (int j = 0; j < list.get(i).size(); j++) {
				//System.out.println(j+"-------"+list.get(i).get(j));
				ee.addCell(row, j, list.get(i).get(j));
			}
		}
		/*
		for (int i = 0; i < dataList.size(); i++) {
			Row row = ee.addRow();
			for (int j = 0; j < dataList.get(i).size(); j++) {
				//System.out.println(j);
				//System.out.println(dataList.get(i).get(j));
				ee.addCell(row, j, dataList.get(i).get(j));
			}
		}*/
		
		ee.writeFile("d:/export1.xlsx");

		ee.dispose();
		
		log.debug("Export success.");

	}

}
