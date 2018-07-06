package com.rain.utils.file.image;

import com.rain.model.vo.Coordinates;
import com.rain.model.vo.ImageContent;
import com.rain.model.vo.Line;
import com.rain.utils.common.ImageCommom;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * Created by ron on 2018/5/22.
 */
public class ImageLocation {
    //吉祥合同定位
    public static ImageContent jxConvert(BufferedImage img,int i){
        ImageContent imageContent = new ImageContent();
        BufferedImage result = null;
        int w = 0,h = 0;
        try {
            //原图黑化
            img =  Image.blackImage(img,500);
            w = img.getWidth();
            h = img.getHeight();
            //缩放原图
            if(w > 1000){
                img = Image.scaleImage(1000, h/(w/1000), img);
            }
            //找距离左边最近的线条 线条长度至少大于100
            List<Line> lines = ImageCommom.findLineByX(img);
            //找不到则无法继续
            if(lines == null || lines.size()==0){
                return null;
            }
            //最左边的线 坐标
            Line line = lines.get(0);
            //计算最左边的线的偏转角度
            double angle = ImageCommom.coordinateToAngle(line.getX1(),line.getY1(),line.getX2(),line.getY2());
            if(line.getX2()<line.getX1()){
                angle = -angle;
            }
            //旋转原图 将原图摆正
            img = Image.spinImage(angle, img);

            //寻找缩放后的图片中的线条
            lines = ImageCommom.findRow(img);
            if(lines == null || lines.size() == 0){
                return null;
            }

            //线条转化为坐标系
            Coordinates s = ImageCommom.convert2coordinate(lines);
            img = img.getSubimage(s.getXlist().get(0), 0,
                    s.getXlist().get(s.getXlist().size()-1)-s.getXlist().get(0),
                    s.getYlist().get(s.getYlist().size()-1));
            //二值化
            img =  Image.twoValueImage(img,200);

            //寻找二值化后的图片中的线条
            lines = ImageCommom.findRow(img, 50 ,0, 20);

            //线条转化为坐标系
            s = ImageCommom.convert2coordinate(lines);

            //假定表格宽1000，日期距离表格顶部130个像素
            //两个日期分别距离屏幕左侧80个像素，距离第5根竖线80个像素
            //件号在第5竖线与第2竖线之间 在第3横与第2横之间
            double radio = img.getWidth()/1000d;
            //依据1000像素的图片计算 ?=y/1000*x
            int dw = (int)(150*radio);
            int dh = (int)(30*radio);
            int offsetx = (int)(90*radio);
            int offsety = (int)(145*radio);

            //获取件号
            result = img.getSubimage(s.getXlist().get(1)+5, s.getYlist().get(1)+5,
                    Math.abs(s.getXlist().get(4)-s.getXlist().get(1)-10),
                    Math.abs(s.getYlist().get(2)-s.getYlist().get(1)));
            imageContent.setMatnr(result);
//            ImageIO.write(result,  "jpg", new File(outPath+"part.jpg"));

            //归还日期
            result = img.getSubimage(s.getXlist().get(4) + offsetx,
                    s.getYlist().get(0) - offsety, dw, dh );
            imageContent.setQyTime(result);
        } catch (Exception e) {
            System.out.println("定位图片异常");
            e.printStackTrace();
        }
        return imageContent;
    }
    //圆通合同定位
    public static ImageContent ytConvert(BufferedImage img,int i){
        ImageContent imageContent= new ImageContent();
        BufferedImage zqydno = null;
        BufferedImage time = null;
        int w = 0,h = 0;
        try {
            long st = System.currentTimeMillis();
            w = img.getWidth();
            h = img.getHeight();
            //缩放
            if(w > 1000){
                img = Image.scaleImage(1000, h/(w/1000), img);
            }
            //黑化
            img =  Image.blackImage(img,350);
            long et = System.currentTimeMillis();
            System.out.println(et-st);

//            ImageIO.write(img,"jpg",new File("F:\\null\\outFile\\"+i+"4.jpg"));
            //找距离左边最近的线条 线条长度至少大于100
            List<Line> lines = ImageCommom.findLineByX(img);
            if(lines == null || lines.size()==0){
                return null;
            }
            //最左边的线 坐标
            Line line = lines.get(0);
            int fx = line.getX2();
            int fy = line.getY2();
            //计算最左边的线的偏转角度
            double angle = ImageCommom.coordinateToAngle(line.getX1(),line.getY1(),line.getX2(),line.getY2());
            if(line.getX2()<line.getX1()){
                angle = -angle;
            }
            //旋转图片
            img = Image.spinImage(angle, img);
            ImageIO.write(img,"jpg",new File("F:\\null\\outFile\\"+i+"旋转.jpg"));
            //旋转后重新二值化
            img = Image.twoValueImage(img,200);

            img = img.getSubimage(0, line.getY2(), img.getWidth(), 350);
            ImageIO.write(img,"jpg",new File("F:\\null\\outFile\\"+i+"ceshi.jpg"));
            //获取最左边线
            lines = ImageCommom.findLineByX(img,0,0,100 , 8);
            if(lines == null || lines.size() == 0){
                return null;
            }
            //第二段线 的坐标
            line = lines.get(lines.size()-1);
            //计算最左边的线的偏转角度
            double angle2 = ImageCommom.coordinateToAngle(line.getX1(),line.getY1(),line.getX2(),line.getY2());
            if(line.getX2()<line.getX1()){
                angle2 = -angle2;
            }
            if(line.getX2()-line.getX1()>10||line.getX2()-line.getX1()<0){
            //旋转图片
            img = Image.spinImage(angle2, img);
            }

            ImageIO.write(img,"jpg",new File("F:\\null\\outFile\\"+i+"旋转2.jpg"));
            w = img.getWidth();
            h = img.getHeight();

            //截取第二段线 所在的表格
            img = img.getSubimage(0, 0,
                    Math.abs(w),  Math.abs(line.getY2()-line.getY1()));
            lines = ImageCommom.findRow(img, 50 ,0, 10);

//            ImageCommom.applicationDebug(img,lines, false,"F:\\null\\outFile\\"+i+"test.jpg");
            if(lines == null || lines.size() == 0){
                return null;
            }
            Coordinates s = ImageCommom.convert2coordinate(lines);

            //获取件号
            zqydno = img.getSubimage(s.getXlist().get(1)+3, s.getYlist().get(2)+3,
                    Math.abs(s.getXlist().get(2)-s.getXlist().get(1)),
                    Math.abs(s.getYlist().get(3)-s.getYlist().get(2)));
            imageContent.setMatnr(zqydno);
            ImageIO.write(zqydno,"jpg",new File("F:\\null\\outFile\\"+i+"件号.jpg"));

            //获取时间
            time = img.getSubimage(s.getXlist().get(7)+3, s.getYlist().get(2)+3,
                    Math.abs(s.getXlist().get(8)-s.getXlist().get(7)),
                    Math.abs(s.getYlist().get(3)-s.getYlist().get(2)));
            imageContent.setQyTime(time);
            ImageIO.write(time,"jpg",new File("F:\\null\\outFile\\"+i+"日期.jpg"));
            System.out.println("end");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageContent;
    }
}
