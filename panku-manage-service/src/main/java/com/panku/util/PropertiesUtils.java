package com.panku.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @description: get properties content
 * @author: uaike
 * @create: 2020-11-25
 */
public class PropertiesUtils {

    /**
     * GET ALL PROPERTY
     * @param props
     */
    private static Map<String, String> printAllProperty(Properties props)
    {
      @SuppressWarnings("rawtypes")
      Enumeration en = props.propertyNames();
      Map<String, String> propsMap = new HashMap<>();
      while (en.hasMoreElements())
      {
          String key = (String) en.nextElement();
          String value = props.getProperty(key);
          propsMap.put(key, value);
      }
      return propsMap;
    }

    /**
     * Retrieves a value based on a keyword from the specified file
     * @param filePath
     * @param keyWord
     * @return
     */
    public static String getValueByKey(String filePath, String keyWord){
        Properties prop = null;
        String value = null;
        try {
             // 通过Spring中的PropertiesLoaderUtils工具类进行获取
             prop = PropertiesLoaderUtils.loadAllProperties(filePath);
             // 根据关键字查询相应的值
             value = prop.getProperty(keyWord);
         } catch (IOException e) {
             e.printStackTrace();
         }
        return value;
    }

    /**
     * get all properties by filepath
     * @param filePath
     */
    public static void getPropertiesByFilePath(String filePath){
        Properties prop = null;
        try {
            // 通过Spring中的PropertiesLoaderUtils工具类进行获取
            prop = PropertiesLoaderUtils.loadAllProperties(filePath);
            printAllProperty(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取配置文件所有信息
     *
     * @Title: getProperitesByStreamAndPath
     * @Description: 第二种方式：使用缓冲输入流读取配置文件，然后将其加载，再按需操作
     *                    绝对路径或相对路径， 如果是相对路径，则从当前项目下的目录开始计算，
     *                  如：当前项目路径/config/config.properties,
     *                  相对路径就是config/config.properties
     *
     * @param filePath
     * @return void
     * @throws
     */
     public static void getProperitesByStreamAndPath(String filePath){
         Properties prop = new Properties();
         try {
                 // 通过输入缓冲流进行读取配置文件
                 InputStream InputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
                 // 加载输入流
                 prop.load(InputStream);
                 printAllProperty(prop);
             } catch (Exception e) {
                 e.printStackTrace();
             }
     }

     /**
       * 根据key读取value
       *
       * @Title: getValueByKeyAndFilePath
       * @Description: 第三种方式：
       *                    相对路径， properties文件需在classpath目录下，
       *                  比如：config.properties在包com.test.config下，
       *                  路径就是/com/test/config/config.properties
       * @param filePath
       * @param keyWord
       * @return
       * @return String
       * @throws
       */
     public static String getValueByKeyAndFilePath(String filePath, String keyWord){
         Properties prop = new Properties();
         String value = null;
         try {
             InputStream inputStream = PropertiesUtils.class.getResourceAsStream(filePath);
             prop.load(inputStream);
             value = prop.getProperty(keyWord);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return value;
     }

     /**
       * 读取配置文件所有信息
       *
       * @Title: getPropertiesAllByFilePath
       * @Description: 第三种方式：
       *                    相对路径， properties文件需在classpath目录下，
       *                  比如：config.properties在包com.test.config下，
       *                  路径就是/com/test/config/config.properties
       * @param filePath
       * @return
       * @throws
       */
     public static void getPropertiesAllByFilePath(String filePath){
         Properties prop = new Properties();
         try {
             InputStream inputStream = PropertiesUtils.class.getResourceAsStream(filePath);
             prop.load(inputStream);
             printAllProperty(prop);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

}
