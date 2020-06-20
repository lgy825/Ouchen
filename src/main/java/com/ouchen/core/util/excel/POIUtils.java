package com.ouchen.core.util.excel;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel 导出
 * 
 * @author wangyanwen
 * @date 2017年6月6日下午3:36:01
 */
public class POIUtils {

	/**
	 * 导出数据
	 * 
	 * @param title
	 *            excel 标题
	 * @param columnTitles
	 *            表格头
	 * @param properties
	 *            表格头对应数据模型的属性(暂时不支持嵌套对象 )
	 * @param data
	 *            数据模型集合
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	public static XSSFWorkbook exportWorkbook(String title, String[] columnTitles, String[] properties, List data) throws Exception {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(); // 创建工作簿对象
			XSSFSheet sheet = workbook.createSheet(title); // 创建工作表
			XSSFRow rowm = sheet.createRow(0);// 产生表格标题行
			XSSFCell cellTiltle = rowm.createCell(0);
			XSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);// 获取列头样式对象
			XSSFCellStyle style = getStyle(workbook); // 单元格样式对象
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (columnTitles.length - 1)));
			cellTiltle.setCellStyle(columnTopStyle);
			cellTiltle.setCellValue(title);
			int columnNum = columnTitles.length;// 定义所需列数
			XSSFRow rowRowName = sheet.createRow(2); // 在索引2的位置创建行(最顶端的行开始的第二行)
			// 将列头设置到sheet的单元格中
			for (int n = 0; n < columnNum; n++) {
				XSSFCell cellRowName = rowRowName.createCell(n); // 创建列头对应个数的单元格
				cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
				XSSFRichTextString text = new XSSFRichTextString(columnTitles[n]);
				cellRowName.setCellValue(text); // 设置列头单元格的值
				cellRowName.setCellStyle(columnTopStyle); // 设置列头单元格样式
			}

			if (data != null && properties != null && properties != null) {
				List<Object[]> list = new ArrayList<Object[]>();
				Object[] objs = null;
				Map excludes = new HashMap();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				int dataSize = data.size();
				for (int i = 0; i < dataSize; i++) {
					Object obj = data.get(i);
					objs = new Object[columnTitles.length];
					for (int x = 0; x < columnTitles.length; x++) {
						Object value = getValue(obj, properties[x]);
						// 必要时调用如下方法转换成成String类型
						// BeanUtilsBean.getInstance().getConvertUtils().convert(value);
						objs[x] = value;
					}
					list.add(objs);
				}
				// 将查询出的数据设置到sheet对应的单元格中
				int listSize = list.size();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = list.get(i);// 遍历每个对象
					XSSFRow row = sheet.createRow(i + 3);// 创建所需的行数
					for (int j = 0; j < obj.length; j++) {
						XSSFCell cell = null; // 设置单元格的数据类型
						cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(obj[j] == null ? "" : obj[j].toString()); // 设置单元格的值
						cell.setCellStyle(style);
					}
				}
			}

			// 让列宽随着导出的列长自动适应
			for (int colNum = 0; colNum < columnNum; colNum++) {
				int columnWidth = sheet.getColumnWidth(colNum) / 256;
				for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
					XSSFRow currentRow;
					// 当前行未被使用过
					if (sheet.getRow(rowNum) == null) {
						currentRow = sheet.createRow(rowNum);
					} else {
						currentRow = sheet.getRow(rowNum);
					}
					if (currentRow.getCell(colNum) != null) {
						XSSFCell currentCell = currentRow.getCell(colNum);
						if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							if (currentCell.getRichStringCellValue() != null) {
								int length = currentCell.getRichStringCellValue().getString().getBytes().length;
								if (columnWidth < length) {
									columnWidth = length;
								}
							}
						}
					}
				}
				if (colNum == 0) {
					sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
				} else {
					// 限制列宽
					if (columnWidth + 4 >= 255) {
						columnWidth = 250;
					}
					sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
				}
			}
			return workbook;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 列头单元格样式
	 */
	public static XSSFCellStyle getColumnTopStyle(XSSFWorkbook workbook) {
		// 设置字体
		XSSFFont font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 11);
		// 字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		XSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;

	}

	/*
	 * 列数据信息单元格样式
	 */
	public static XSSFCellStyle getStyle(XSSFWorkbook workbook) {
		// 设置字体
		XSSFFont font = workbook.createFont();
		// 设置字体大小
		// font.setFontHeightInPoints((short)10);
		// 字体加粗
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		XSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 * 导出excel数据到浏览器
	 * 
	 * @param response
	 * @param prefix
	 *            excel 文件的文件名前缀
	 * @param title
	 *            excel 标题
	 * @param columnTitles
	 *            表格头
	 * @param properties
	 *            表格头对应数据模型的属性
	 * @param data
	 *            数据模型集合
	 */
	@SuppressWarnings("rawtypes")
	public static void export(HttpServletResponse response, String prefix, String title, String[] columnTitles, String[] properties, List data) {
		try {
			XSSFWorkbook workbook = POIUtils.exportWorkbook(title, columnTitles, properties, data);
			if (workbook != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String fileName = "Excel-" + new String((prefix + "-").getBytes("GB2312"), "ISO8859-1") + sdf.format(new Date()) + ".xlsx";
				String headStr = "attachment; filename=\"" + fileName + "\"";
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", headStr);
				OutputStream out = response.getOutputStream();
				workbook.write(out);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据属性名key 取得对象obj 的该属性值,支持内嵌对象
	 * 
	 * @author wangyanwen
	 * @date 2017年6月6日下午3:34:53
	 * @param obj
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	public static Object getValue(Object obj, String key) throws Exception {
		Map items = null;
		if (!(obj instanceof Map)) {
			// BeanUtils.describe() 会将内嵌对象转化为String，因此娶不到内嵌对象的属性
			items = PropertyUtils.describe(obj);
		} else {
			items = (Map) obj;
		}
		if (!key.contains(".")) {
			return items.get(key);
		} else {
			String preKey = key.substring(0, key.indexOf("."));
			String sufKey = key.substring(key.indexOf(".") + 1);
			Object o = items.get(preKey);
			return getValue(items.get(preKey), sufKey);
		}
	}

}
