package com.rain.utils.file.excel;

import com.rain.config.framework.result.Result;
import com.rain.utils.common.commonUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2018-6-22 0022.
 */
public class ExcelUtil {

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 验证EXCEL文件
     * @param filePath
     * @return boolean
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) ||!isExcel2007(filePath))) {
            return false;
        }
        return true;
    }

    /**
     * 验证,判断excel类型,
     * @param
     * @return
     */
    public static Workbook getExcelType(String filePath) throws FileNotFoundException {
        String fileName = filePath.substring(filePath.lastIndexOf("."));
        FileInputStream fileInput = new FileInputStream(filePath);
        Workbook wb = null;
        try {
            if (!ExcelUtil.validateExcel(fileName)) { // 验证文件名是否合格
                return null;
            }
            if (ExcelUtil.isExcel2003(fileName)) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(fileInput);
            }else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(fileInput);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param content 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String[] title,String[][] content, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        //声明列对象
        HSSFCell cell = null;
        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //创建内容
        for(int i=0;i<content.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<content[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(content[i][j]);
            }
        }
        return wb;
    }
}
