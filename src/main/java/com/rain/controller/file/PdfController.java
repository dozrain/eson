package com.rain.controller.file;


import com.rain.config.framework.result.Result;
import com.rain.service.file.PdfService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
@Controller
public class PdfController {

    @Resource
    PdfService pdfService;


    @RequestMapping(value = "pdf/import")
    @ResponseBody
    public Result pdfIeport() throws IOException {
        return pdfService.readContentByPDF("a");
    }

}
