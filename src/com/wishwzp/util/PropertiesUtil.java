package com.wishwzp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties文件读取类
 * @author wzp
 *
 */
public class PropertiesUtil {

	public static String getValue(String key){
		Properties prop=new Properties();
		InputStream in=new PropertiesUtil().getClass().getResourceAsStream("/diary.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String)prop.get(key);
	}
}
