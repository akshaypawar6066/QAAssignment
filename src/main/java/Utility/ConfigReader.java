package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	public static  String getProperty(String propertyName) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream filePath=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		prop.load(filePath);
		return prop.getProperty(propertyName);
	}

}
