package com.rain.controller.file;

import com.rain.config.framework.result.Result;
import com.rain.service.file.ExcelService;
import com.rain.utils.file.excel.ExcelUtil;
import com.rain.utils.file.fileCommon.FileType;
import com.rain.utils.file.fileCommon.FileUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018-6-28 0028.
 */

@Controller
public class FileController {
    @Resource
    ExcelService excelService;
    String filePath = "C:\\Users\\Administrator\\Desktop\\dev\\ld\\AOG\\AOG后端文档\\报价\\吉祥报价.XLSX";

    //excel数据导入
    @RequestMapping(value = "file/import")
    @ResponseBody
    public Result FileImport() throws IOException {
        FileType fileType = FileUtil.getFileType(filePath);
        String fileTail = filePath.substring(filePath.lastIndexOf(".")+1,filePath.length()).toUpperCase();
        if (fileType.getValue().equals("255044462D312E")&&fileTail.equals("PDF")) {

        } else if (fileType.getValue().equals("504B0304")&&fileTail.equals("ZIP")) {

        } else if (fileType.getValue().equals("FFD8FF")&&fileTail.equals("JPEG")) {

        } else if (fileType.getValue().equals("504B0304")&&fileTail.equals("DOC")) {

        } else if (fileType.getValue().equals("D0CF11E0")&&fileTail.equals("XLS")) {
            //解析excel，获取上传的事件单
            Workbook workbook = ExcelUtil.getExcelType(filePath);
        } else if (fileType.getValue().equals("504B0304")&&fileTail.equals("XLSX")) {
            //解析excel，获取上传的事件单
            Workbook workbook = ExcelUtil.getExcelType(filePath);
        }else{

        }
        return new Result();
    }

}

