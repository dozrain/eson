package com.rain.controller.file;

import com.rain.utils.framework.error.ErrorData;
import com.rain.utils.framework.result.Result;
import com.rain.service.file.ExcelService;
import com.rain.utils.common.TypeChangeUtil;
import com.rain.utils.common.commonUtil;
import com.rain.utils.file.excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
@Controller
public class ExcelController {
    @Resource
    ExcelService excelService;

    //excel数据导入
    @RequestMapping(value = "excel/import")
    @ResponseBody
    public Result excelImport() throws IOException {
        String filePath = "C:\\Users\\Administrator\\Desktop\\dev\\ld\\AOG\\AOG后端文档\\报价\\吉祥报价.XLSX";
        //解析excel，获取上传的事件单
        Workbook workbook = ExcelUtil.getExcelType(filePath);
        List dataList =  excelService.readExcelValue(workbook);
        List billList =  excelService.getBill(dataList);// 读取Excel里面客户的信息
        Result result1 = excelExport(billList);
        //和你具体业务有关,这里不做具体的示范
        String result ="";
        if(dataList != null && !dataList.isEmpty()){
            result = "上传成功";
        }else{
            result = "上传失败";
        }
        //返回反馈信息
        return new Result(ErrorData.SUCCESS_CODE,result);
    }


    //excel数据导出
    @RequestMapping(value = "excel/export")
    @ResponseBody
    public Result excelExport(List excelExportList) throws IOException {
        //获取数据
        if(excelExportList ==null&& excelExportList.size()<0){
            return null;
        }
        //excel标题
        String[] title = {"合同日期","业务类型","件号","供方报价","报价","公式","状态"};
        //excel文件名
        String excelName = "Excel"+System.currentTimeMillis()+".xls";
        String excelPath = "F:\\null\\image\\";
        //sheet名
        String sheetName = "sheet";

        for (int i = 0; i <excelExportList.size() ; i++) {
            Map map = TypeChangeUtil.objectToMap(excelExportList.get(i));
        }


        //获取excel内容
        String[][] content= new String[excelExportList.size()][title.length];
        for (int i = 0; i < excelExportList.size(); i++) {
            Object[] objectsArr= commonUtil.getFiledValues(excelExportList.get(i));
            for (int j = 0; j < objectsArr.length; j++) {
                content[i][j] = objectsArr[j].toString();
            }
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        //响应到客户端
        try {
            File file = new File(excelPath+excelName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取excel路径
        String result = "导出成功";
        //返回反馈信息
        return new Result(ErrorData.SUCCESS_CODE,result);
    };

}
