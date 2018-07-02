package com.rain.utils.file.pdf;

import com.rain.model.vo.ImageContent;
import com.rain.utils.file.image.Image;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.rain.utils.file.image.ImageLocation.jxConvert;
import static com.rain.utils.file.image.ImageLocation.ytConvert;

/**
 * Created by Administrator on 2018-5-23 0023.
 */
public class PdfUtil {
    private static Logger logger = LoggerFactory.getLogger(PdfUtil.class);

    public static List pdfToImage(String pdfPath) {
        PDDocument pdDocument = null;
        ImageContent imageContent;
        List imageContentList = new ArrayList();
        try {
            // 读入PDF
            pdDocument = PDDocument.load(new File(pdfPath));
            List<PDPage> pages =  pdDocument.getDocumentCatalog().getAllPages();
            for (int i = 0; i <pages.size() ; i++) {
                BufferedImage image = pages.get(i).convertToImage(BufferedImage.TYPE_3BYTE_BGR, 300);
                if(false) {
                    imageContent = jxConvert(image, i);
                }else{
                    imageContent = ytConvert(image, i);
                }
                if(imageContent!= null){
                    BufferedImage matnr = imageContent.getMatnr();
                    if(matnr != null){
                        String zqydno = Image.readImageContent(matnr,true,"F");
                        String str =  zqydno.replaceAll("[^0-9A-Z-/]", "").trim();
                        if(str.length()<5){
                            str =  "invalidDistinguish";
                        }
                        imageContent.setMatnrTxt(str);
                        imageContent.setMatnr(null);
                    }
                    BufferedImage qyTime = imageContent.getQyTime();
                    if(qyTime != null){
                        String time = Image.readImageContent(qyTime,true,"time");
                        String date =  time.replaceAll("[^0-9]", "").trim();
                        if(date.length()<6){
                            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期
                            date = df.format(new Date());
                        }
                        imageContent.setQyTimeText(date);
                        imageContent.setQyTime(null);
                    }
                    imageContent.setPath(pdfPath);
                    imageContentList.add(imageContent);
                    logger.info("解析"+i+"次件号为:"+imageContent.getMatnrTxt());
                    logger.info("解析"+i+"次时间:"+imageContent.getQyTimeText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(pdDocument != null){try {pdDocument.close();} catch (Exception e){}}
        }
        return imageContentList;
    }
}
