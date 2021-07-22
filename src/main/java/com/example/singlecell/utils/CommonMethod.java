package com.example.singlecell.utils;

import org.apache.poi.hpsf.Decimal;
import org.apache.tomcat.util.codec.binary.Base64;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static javafx.scene.input.KeyCode.M;

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

    /**
     * 科学计数法字符串转小数点字符串
     * @param strData
     * @return
     */
    public static String changeDataToS(String strData){
        BigDecimal bd = new BigDecimal(strData);
        return bd.toString();
    }

    public static Double changeDataToD(String strData){
        BigDecimal bd = new BigDecimal(strData);
        return bd.doubleValue();
    }

    public static String format(Double a){
        java.text.NumberFormat NF = java.text.NumberFormat.getInstance();
        //设置数值的小数部分所允许的最大位数，多于小数位被舍弃
        NF.setMaximumFractionDigits(20);
        //设置数值的小数部分所允许的最小位数，避免小数位有多余的0
        NF.setMinimumFractionDigits(0);
        //设置数值的整数部分所允许的最大位数，多的前几位被省略掉
        NF.setMaximumIntegerDigits(4);
        //设置数值的整数部分所允许的最小位数，不足前面补0
        NF.setMinimumIntegerDigits(1);
        //去掉科学计数法显示，避免显示为111,111,111,111
        NF.setGroupingUsed(false);
        System.out.println(NF.format(a));
        return NF.format(a);
    }

}
