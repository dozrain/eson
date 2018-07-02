package com.rain.service.file;

import com.rain.utils.file.pdf.PdfUtil;
import org.apache.xmlgraphics.image.loader.ImageContext;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2017\11\10 0010.
 */
@Service
public class ImageService {
    public static void main(String[] args) throws Exception {
//        String fileLocal = "F:\\null\\image\\吉祥报价单\\利顿报价单27项.pdf";
        String fileLocal = "F:\\null\\image\\圆通报价单\\1.pdf";
//        String fileLocal = "F:\\null\\image\\yt.jpg";
//        //根据阿里云URL,下载压缩包到指定路径
//        String fileLocalPath = FileUtil.downLoadFile(fileUrl,fileLocal);
//        //获取文件类型,判断是否为zip压缩包类型
//        FileType fileType =  FileUtil.getFileType(fileLocalPath);
//        List<String> fileOfZip = new ArrayList<>();
//        if(fileType.name().equals("ZIP")){
//            //获取压缩包内文件,并解压zip压缩包到指定文件路径
//            fileOfZip =  ZipUtil.decompressionZip(fileLocalPath,fileLocal);
//        }else if(fileType.name().equals("RAR")){
//            fileOfZip =  ZipUtil.decompressionRar(fileLocalPath,fileLocal);
//        }else {
//            System.out.println("仅支持zip和rar压缩包");
//        }

        List<ImageContext> image =  PdfUtil.pdfToImage(fileLocal);
    }
}

