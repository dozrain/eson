package com.rain.model.vo;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

import java.io.File;

/**
 * Created by Administrator on 2018-5-31 0031.
 */
public class TessData {
    private volatile static ITesseract instance;
    private TessData(){}
    public static ITesseract getInstance(){
        if (instance == null) {
            synchronized (TessData.class) {
                if (instance == null) {
                    instance = new Tesseract();
                    File tessDataFolder = LoadLibs.extractTessResources("tessdata");
                    instance.setDatapath(tessDataFolder.getAbsolutePath());
                    instance.setLanguage("eng");//英文库识别数字比较准确
                }
            }
        }
        return instance;
    }
}
