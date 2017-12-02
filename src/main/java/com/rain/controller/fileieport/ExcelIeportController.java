package com.rain.controller.fileieport;

import com.rain.service.ExcelIeportService;
import com.rain.utils.ErrorData;
import com.rain.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
@Controller
public class ExcelIeportController {

    //excel数据导入
    @RequestMapping(value = "excel/import")
    @ResponseBody
    public Result priceExcelImport(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
            String result ="";
            //创建处理EXCEL的类
            ExcelIeportService readExcel = new ExcelIeportService();
            //解析excel，获取上传的事件单
            List  dataList = readExcel.getExcelInfo(file);
            //和你具体业务有关,这里不做具体的示范
            if(dataList != null && !dataList.isEmpty()){
                result = "上传成功";
            }else{
                result = "上传失败";
            }
        //返回反馈信息
        return new Result(ErrorData.SUCCESS_CODE,result);
    }


//    //excel数据导出
//    @RequestMapping(value = "excel/export")
//    @ResponseBody
//    public Result priceExcelExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        //获取excel路径
//        String filePath = request.getParameter("filePath");
//        //调用导入excel文件方法
//        //说excelIeportService.export(filePath);
//        //返回反馈信息
//        return new Result(ErrorData.SUCCESS_CODE,"导出成功");
//    };

}
