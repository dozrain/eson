package com.rain.utils.file.zip;



import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Administrator on 2018-5-23 0023.
 */
public class ZipUtil {
    ////获取压缩包内文件,并解压zip压缩包到指定文件路径
    public static List<String> decompressionZip(String zipFilePath,String zipOutPath) throws IOException {
        //创建list 装压缩包内文件
        List<String> fileOfZip = new ArrayList();
        ZipFile zipFile = new ZipFile(zipFilePath, Charset.forName("GBK"));
        String name = zipFile.getName().substring(zipFile.getName().lastIndexOf(File.separator)+1, zipFile.getName().lastIndexOf('.'));
        //遍历压缩包内文件
        for (Enumeration entries = zipFile.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zipFile.getInputStream(entry);
            String outPath = (zipOutPath +File.separator+ name+File.separator+ zipEntryName);
            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            fileOfZip.add(outPath);
            in.close();
            out.close();
        }
        zipFile.close();
        return fileOfZip;
    }

    public static List<String> decompressionRar(String rarFilePath,String rarOutPath){
        List<String> fileOfRar = new ArrayList<>();

        File rarFile = new File(rarFilePath);
        if (!rarFile.exists()) {// 目标目录不存在时，创建该文件夹
            rarFile.mkdirs();
        }
        Archive rarArchive = null;
        String name = rarFile.getName().substring(rarFile.getName().lastIndexOf(File.separator)+1, rarFile.getName().lastIndexOf('.'));
        try {
            rarArchive = new Archive(rarFile);
            if (rarArchive != null) {
//                rarArchive.getMainHeader().print(); // 打印文件信息.
                FileHeader fileHeader = rarArchive.nextFileHeader();
                while (fileHeader != null) {
                    // 防止文件名中文乱码问题的处理

                    String fileName = fileHeader.getFileNameW().isEmpty() ? fileHeader
                            .getFileNameString() : fileHeader.getFileNameW();

                    if (fileHeader.isDirectory()) { // 文件夹
                        File fol = new File(rarOutPath +File.separator+ name+File.separator+ fileName);
                        fol.mkdirs();
                    } else { // 文件
                        String outPath = (rarOutPath +File.separator+ name+File.separator+ fileName);
                        File out = new File(outPath);
                        fileOfRar.add(outPath);
                        if (!out.exists()) {
                            if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
                                out.getParentFile().mkdirs();
                            }
                            out.createNewFile();
                        }
                        FileOutputStream os = new FileOutputStream(out);
                        rarArchive.extractFile(fileHeader, os);
                        os.close();
                    }
                    fileHeader = rarArchive.nextFileHeader();
                }
                rarArchive.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  fileOfRar;
    }

    public static List<File> decompression7z(String filePath,String outPath){
        List<File> filesOf7z = new ArrayList<>();
        return filesOf7z;
    }
}
