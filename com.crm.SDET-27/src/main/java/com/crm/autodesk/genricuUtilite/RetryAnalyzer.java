package com.crm.autodesk.genricuUtilite;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer
{
	int count= 0;
	int retryCount=2;

	public boolean retry(ITestResult result) 
	{
		while(count<retryCount)
		{
			count++;
			return true;
			
		}
		return false;
	}

}
