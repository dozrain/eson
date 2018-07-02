package com.rain.utils.file.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018-5-18 0018.
 */
public class ImageEdge {
//    public void sobel(BufferedImage image){
//        grayData = new int[size];// 开辟内存空间
//        for (int i = 0; i < pixel.length; i++) {
//            grayData[i] = (pixel[i] & 0xff0000) >> 16;// 由于读的是灰度图，故只考虑一个分量（三分量值相同）
//        }
//    }
//
//    public void createEdgeImage(String desImageName) {
//        BufferedImage outBinary = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_BINARY);
//        float[] gradient = getPixelGradient();// 计算图像各像素点的梯度值
//        float maxGradient = gradient[0];
//        for (int i = 1; i < gradient.length; ++i)
//            if (gradient[i] > maxGradient)
//                maxGradient = gradient[i];// 获取梯度最大值
//        float scaleFactor = 255.0f / maxGradient;// 比例因子用于调整梯度大小
//        if (gradientThreshold >= 0) {
//            for (int y = 1; y < height - 1; ++y)
//                for (int x = 1; x < width - 1; ++x)
//                    if (Math.round(scaleFactor * gradient[y * width + x]) >= gradientThreshold)
//                        outBinary.setRGB(x, y, 0xffffff);// 白色
//        }// 对梯度大小进行阈值处理
//        else {
//            for (int y = 1; y < height - 1; ++y)
//                for (int x = 1; x < width - 1; ++x)
//                    outBinary.setRGB(x, y, 0x000000);// 黑色;
//        }// //不对梯度大小进行阈值处理, 直接给出用比例因子调整后的值
//
//        writeImage(outBinary, desImageName);
//    }
//    //算子计算 图像每个像素点的梯度大小
//    protected float[] getPixelGradient() {
//        float[] pixelGradient = new float[size];
//        int pixelGradientByX, pixelGradientByY;
//        for (int y = 1; y < height - 1; ++y)
//            for (int x = 1; x < width - 1; ++x) {
//                pixelGradientByX = GradientX(x, y);
//                pixelGradientByY = GradientY(x, y);
//                //用公式 g=|gx|+|gy|计算图像每个像素点的梯度大小.原因是避免平方和开方耗费大量时间
//                pixelGradient[y * width + x] = (float) (Math.abs(pixelGradientByX) + Math.abs(pixelGradientByY));
//            }
//        return pixelGradient;
//    }
//    //算子 计算 点(x,y)处的x方向梯度大小
//    protected final int GradientX(int x, int y) {
//        return getGrayPoint(x - 1, y - 1) + 2*getGrayPoint(x - 1, y)
//                + getGrayPoint(x - 1, y + 1) - getGrayPoint(x + 1, y - 1)
//                - 2*getGrayPoint(x + 1, y) - getGrayPoint(x + 1, y + 1);
//    }// 计算像素点(x,y)X方向上的梯度值
//    // 算子 计算 点(x,y)处的y方向梯度大小
//    protected final int GradientY(int x, int y) {
//        return getGrayPoint(x - 1, y - 1) + 2*getGrayPoint(x, y - 1)
//                + getGrayPoint(x + 1, y - 1) - getGrayPoint(x - 1, y + 1)
//                - 2*getGrayPoint(x, y + 1) - getGrayPoint(x + 1, y + 1);
//    }// 计算像素点(x,y)Y方向上的梯度值
//    //得到点(x,y)处的灰度值
//    public int getGrayPoint(int x, int y) {
//        return grayData[y * width + x];
//    }
//
//    public void writeImage(BufferedImage image, String imageName) {
//        FileUtil skinImageOut = new FileUtil(imageName);
//        try {
//            ImageIO.write(image, "jpg", skinImageOut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
