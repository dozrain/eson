package com.rain.utils.file.image;

import com.rain.model.pojo.file.image.Line;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2018-5-16 0016.
 */
public class Image {
    //亮度图片
    public int[][] brightenImage(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] gray = new int[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                int argb = image.getRGB(x, y);
                // 图像加亮（调整亮度识别率非常高）
                int r = (int) (((argb >> 16) & 0xFF) * 1.1 + 30);
                int g = (int) (((argb >> 8) & 0xFF) * 1.1 + 30);
                int b = (int) (((argb >> 0) & 0xFF) * 1.1 + 30);
                if (r >= 255){
                    r = 255;
                }
                if (g >= 255){
                    g = 255;
                }
                if (b >= 255){
                    b = 255;
                }
                gray[x][y] = (int) Math.pow((Math.pow(r, 2.2) * 0.2973 + Math.pow(g, 2.2)* 0.6274 + Math.pow(b, 2.2) * 0.0753), 1 / 2.2);
            }
        }
        return gray;
    }
    /**
     * 黑化.
     * @param image 读取后图片
     */
    public static BufferedImage  blackImage(BufferedImage image,int threshold) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        //二值化图片
        BufferedImage twoValueImage = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_BINARY);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (isTarget(image.getRGB(x, y), threshold)) {
                    twoValueImage.setRGB(x, y, Color.WHITE.getRGB());
                } else {
                    twoValueImage.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        return twoValueImage ;
    }
    //灰化图片
    public static BufferedImage grayImage(BufferedImage image) throws IOException{
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY
        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }
        return grayImage;
    }
    //获取二值化阈值
    public static int getThreshold(int[][] gray,BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        int[] histData = new int[width * height];
        // Calculate histogram
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                int red = 0xFF & gray[x][y];
                histData[red]++;
            }
        }
        // Total number of pixels
        int pixelNumber = width * height;

        float sum = 0;
        for (int t = 0; t < 256; t++)
            sum += t * histData[t];

        float sumB = 0;
        int wB = 0;
        int wF = 0;

        float varMax = 0;
        int threshold = 0;

        for (int t = 0; t < 256; t++){
            wB += histData[t]; // Weight Background
            if (wB == 0)
                continue;

            wF = pixelNumber - wB; // Weight Foreground
            if (wF == 0)
                break;

            sumB += (float) (t * histData[t]);

            float mB = sumB / wB; // Mean Background
            float mF = (sum - sumB) / wF; // Mean Foreground

            // Calculate Between Class Variance
            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

            // Check if new maximum found
            if (varBetween > varMax){
                varMax = varBetween;
                threshold = t;
            }
        }
        return threshold;
    }
    //判断图片目标
    public static Boolean isTarget(int imageRGB, int targetThreshold){
        final Color color = new Color(imageRGB);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        if (red + green + blue <= targetThreshold) {
            return true;
        }
        return false;
    }
    //二值化图片
    public static BufferedImage  twoValueImage(BufferedImage image,int threshold) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        //二值化图片
        BufferedImage twoValueImage = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_BINARY);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (isTarget(image.getRGB(x, y), threshold)) {
                    twoValueImage.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    twoValueImage.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        return twoValueImage ;
        }
    //缩放图片
    public static BufferedImage scaleImage(int scaleWidth,int scaleHeight,BufferedImage image) throws Exception {
        int width = image.getWidth();
        int height = image.getHeight();
        // Create the buffered image.
        BufferedImage bufferedImage = new BufferedImage(scaleWidth, scaleHeight, BufferedImage.TYPE_INT_RGB);
        // Copy image to buffered image.
        Graphics graphics = bufferedImage.createGraphics();
        // Clear background and paint the image.
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, scaleWidth, scaleHeight);
        graphics.drawImage(image.getScaledInstance(scaleWidth,scaleHeight,BufferedImage.SCALE_SMOOTH),
                0, 0, null);
        graphics.dispose();
        return bufferedImage;
    }
    //旋转图片内容
    public static BufferedImage spinImage(double degree, BufferedImage image) throws Exception {
        int swidth = 0; // 旋转后的宽度
        int sheight = 0; // 旋转后的高度
        int x; // 原点横坐标
        int y; // 原点纵坐标
        // 处理角度--确定旋转弧度
        degree = degree % 360;
        if (degree < 0)
            degree = 360 + degree;// 将角度转换到0-360度之间
        double theta = Math.toRadians(degree);// 将角度转为弧度

        // 确定旋转后的宽和高
        if (degree == 180 || degree == 0 || degree == 360) {
            swidth = image.getWidth();
            sheight = image.getHeight();
        } else if (degree == 90 || degree == 270) {
            sheight = image.getWidth();
            swidth = image.getHeight();
        } else {
//            swidth = (int) (Math.sqrt(image.getWidth() * image.getWidth()
//                    + image.getHeight() * image.getHeight()));
//            sheight = (int) (Math.sqrt(image.getWidth() * image.getWidth()
//                    + image.getHeight() * image.getHeight()));
            swidth = (int) (Math.sqrt(image.getWidth() * image.getWidth()));
            sheight = (int) (Math.sqrt(image.getWidth() * image.getWidth()));
        }

        x = (swidth / 2) - (image.getWidth() / 2);// 确定原点坐标
        y = (sheight / 2) - (image.getWidth() / 2);

        BufferedImage spinImage = new BufferedImage(swidth, sheight, image.getType());
        // 设置图片背景颜色
        Graphics2D gs = (Graphics2D) spinImage.getGraphics();
        gs.setColor(Color.white);
        gs.fillRect(0, 0, swidth, sheight);// 以给定颜色绘制旋转后图片的背景

        AffineTransform at = new AffineTransform();
        at.rotate(theta, swidth / 2, sheight / 2);// 旋转图象
        at.translate(x, y);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        spinImage = op.filter(image, spinImage);
        return spinImage;
    }

    /**
     * 目标图片定位
     */
    public static void fillData(BufferedImage image, int[][] data) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x <width ; x++) {
                data[x][y] = image.getRGB(x,y);
            }
        }
    }
    /**
     * 画线
     */
    public static BufferedImage drawLines(BufferedImage img, List<Line> lines, boolean drawAll) throws IOException {
        BufferedImage temp = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_BGR);
        Graphics g = temp.getGraphics();
        g.drawImage(img,0,0,img.getWidth(),img.getHeight(),null);
        g.setColor(Color.green);
        for(Line t : lines){
            if(drawAll){
                if(t.isTransverse()){
                    g.drawLine(0,t.getY1(),temp.getWidth(),t.getY2());
                }else{
                    g.drawLine(t.getX1(),0, t.getX2(), temp.getHeight());
                }
            }else{
                if(t.isTransverse()){
                    g.drawLine(t.getX1(),t.getY1(),t.getX2(),t.getY2());
                }else{
                    g.drawLine(t.getX1(),t.getY1(), t.getX2(), t.getY2());
                }
            }

        }
        g.dispose();
        return temp;
    }

    //读取图片内容
    public static String readImageContent(BufferedImage image,Boolean isEnglish,String fontLibraryPath){
        String englishLibrary = "eng";
        String chineseLibrary = "chi_sim";
        ITesseract instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getAbsolutePath());
        if(isEnglish){
            instance.setLanguage(englishLibrary);//英文库识别数字比较准确
        }else {
            instance.setDatapath(fontLibraryPath);//设置训练库地址
            instance.setLanguage(chineseLibrary);
        }
        try {
            return instance.doOCR(image);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return "发生未知错误";
        }
    }

    public static List<Line> findLineByX(BufferedImage img, int offsetX, int offsetY, int length, int threshold){
        List<Line> linesList =  new ArrayList<>();
        Line line = null;
        int w = img.getWidth(), h=img.getHeight(), lstX=0,lstY=0;
        if(img.getWidth() == 0){
            return null;
        }
        for (int y = offsetY; y < h; y++) {
            for (int x = offsetX; x < w ; x++) {
                if (img.getRGB(x,y) == Color.WHITE.getRGB()) {
                    if(line == null){
                        line = new Line();
                        line.setX1(x);
                        line.setY1(y);
                    }else{
                        //与上个白点 x坐标数值相差5
                        if(Math.abs(lstX -x) > threshold || Math.abs(lstY - y) > threshold ){
                            line.setX2(lstX);
                            line.setY2(lstY);
                            //线条长度大于length
                            if(line.length() > length){
                                linesList.add(line);
                            }
                            line = null;
                        }
                    }
                    lstX = x;
                    lstY = y;
                    break;
                }
            }
        }
        return linesList;
    }
}
