package com.rain.utils.file.pdf;

import com.rain.model.pojo.file.image.ImageContent;
import com.rain.utils.file.zip.ZipUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-5-23 0023.
 */
public class PdfUtil2 {
    private static Logger logger = LoggerFactory.getLogger(PdfUtil2.class);

    public static List pdfToImage(String pdfPath,int j) {
        File file = new File(pdfPath);

        PDDocument pdDocument = null;
        ImageContent imageContent;
        List imageContentList = new ArrayList();
        String name = file.getName().substring(0,file.getName().lastIndexOf("."));
        try {
            // 读入PDF
            pdDocument = PDDocument.load(new File(pdfPath));
            List<PDPage> pages =  pdDocument.getDocumentCatalog().getAllPages();
            for (int i = 0; i <pages.size() ; i++) {
                BufferedImage image = pages.get(i).convertToImage(BufferedImage.TYPE_3BYTE_BGR, 300);
                ImageIO.write(image,"png",new File("F:\\null\\价拨件image\\"+name+".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(pdDocument != null){try {pdDocument.close();} catch (Exception e){}}
        }
        return imageContentList;
    }

    public static void main(String[] args) throws IOException {
        String source = "F:\\null\\价拨件pdf\\test.zip";
        String target = "F:\\null\\新建文件夹\\";
        List<String> imageList =  ZipUtil.decompressionZip(source ,target);
        for (int i = 0; i <imageList.size() ; i++) {
            pdfToImage(imageList.get(i),i);
        }
    }
}
