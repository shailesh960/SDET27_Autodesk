package com.crm.autodesk.genricuUtilite;

import java.util.Date;
import java.util.Random;

/**
 * 
 * @author Shailesh
 *
 */
public class JavaUtilite
{
	/**
	 * It is ude To Get Rondom Number
	 * @return Integer Value of Random Number
	 */
	public int getRandomNumber()
	{
		Random random=new Random();
		int randomNumber = random.nextInt();
		return randomNumber;
		
	}
	/**
	 * It is Use Get The Date And Time Of The System
	 * @return it returns The date And Time In String
	 */
	public String getSyatemDateAndTime()
	{
		Date date=new Date();
		return date.toString();
		
	}
	
	/**
	 * Get The System date With Format
	 * @return finalFormat return in String
	 */
	public String getSystemDateWithFormat()
	{
		Date dateFormat=new Date();
		String date = dateFormat.toString();
		String yyyy=date.split("")[5];
		String dd=date.split("")[2];
		int mm=dateFormat.getMonth()+1;
	
	String finalFormat=yyyy+"-"+mm+"-"+dd;
		return finalFormat;
	}
}
