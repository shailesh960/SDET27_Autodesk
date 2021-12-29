package com.crm.autodesk.genricuUtilite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * 
 * @author Shailesh
 *
 */
public class FileUtilite 
{
	/**
	 * It Is used to GEt key value From The Properties File
	 * @param key
	 * @return The Value Of The Property File In String
	 * @throws Throwable
	 */
	public String getPropertyFileKeyValue(String key) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./Data/data.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String value = pObj.getProperty(key);
		return value;
	}

}
