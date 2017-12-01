package com.rain.service;

import com.rain.dao.mapper.ExcelDataMapper;
import com.rain.dao.entity.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
@Service
public class ExcelImportService {

    @Autowired
    private static ExcelDataMapper priceListMapper;


    public static void readExcle() throws IOException {

        User priceList = new User();

        Workbook wb;
        Sheet sheet;
        Row row;
        Cell cell;

        String filepath = "com_lru.xlsx";
        //String filepath = "com_lru.xls";
        InputStream is = new FileInputStream("F:\\null\\Parker.xlsx");

        String ext = filepath.substring(filepath.lastIndexOf("."));
        if(".xls".equals(ext)){
            wb = new HSSFWorkbook(is);
        }else if(".xlsx".equals(ext)){
            wb = new XSSFWorkbook(is);
        }else{
            wb=null;
        }

        //获取第一张Sheet表
        sheet = wb.getSheetAt(0);
        /** 得到Excel的行数 */
        int firstRowNumber = 0;
        int lastRowNumber = sheet.getLastRowNum();

        for (int i = 0; i < lastRowNumber + 1; i++) {
            //获取当前行数据
            row = sheet.getRow(i);
            //获取一行有多少单元格 当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            List list = new ArrayList<>();
            // 获取当前行每一个单元格
            if (i >0) {
                for (int j = 0; j < lastCellNum; j++) {
                    if (j >= 0) {
                        cell = row.getCell(j);
                        String cellValue = "";
                        cellValue = getInputString(cell);
                        list.add(cellValue);
                    };
                };


                priceListMapper.insertPriceCata(priceList);
                list.clear();
            };
        };

    }
//    检验导入数据格式
    private static String getInputString(Cell cell) {
        String cellValue =  "";
        if(cell!=null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    cellValue = cell.getNumericCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    cellValue = cell.getCellFormula() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }
}
