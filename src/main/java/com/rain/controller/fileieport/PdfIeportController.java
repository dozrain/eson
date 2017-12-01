package com.rain.controller.fileieport;

import com.rain.service.ExcelIeportService;
import com.rain.utils.ErrorData;
import com.rain.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
@Controller
public class PdfIeportController {

    @Resource
    ExcelIeportService excelImportService;

    @RequestMapping(value = "pdf/import")
    @ResponseBody
    public Result priceExcelImport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取excel路径
        String filePath = request.getParameter("filePath");
        //调用导入excel文件方法
        //excelImportService.createExcel(filePath);
        //返回反馈信息
        return new Result(ErrorData.SUCCESS_CODE,"导入成功");
    }

}
