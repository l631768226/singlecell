package com.example.singlecell.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CommonMethod {

    /**
     * 文件转base64字符串
     * @param filePath 文件路径
     * @return
     */
    public static String getImageStr(String filePath) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(filePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // 加密
        return Base64.encodeBase64String(data);
    }

    /**
     * java通过RServe调用R语言文件脚本
     * @param filePath R文件全路径
     * @return true执行成功 /false 执行出错
     */
    public static boolean evalR(String filePath){
        boolean result = true;
        RConnection rc = null;
        try {
            rc = new RConnection();
            rc.assign("fileName", filePath);
            rc.eval("source(fileName)");
            result = true;
        } catch (RserveException e) {
            e.printStackTrace();
            result = false;
        }finally {
            rc.close();
            return result;
        }

    }

}
