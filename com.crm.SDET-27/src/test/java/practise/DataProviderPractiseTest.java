package practise;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractiseTest
{
	@Test(dataProvider="getData")
	public void readDataFromDataProviderTest(String name, int num,String model)
	{
		System.out.println("Mobile Number==="+name+" Quantity "+num+ "  ModelNO. "+model);
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr= new Object[5][3];
		
		objArr[0][0]="Iphone";
		objArr[0][1]= 10;
		objArr[0][2]= "13Pro";
		
		objArr[1][0]="Samsung";
		objArr[1][1]=20;
		objArr[1][2]= "Corbi2";
		
		objArr[2][0]="Nokia";
		objArr[2][1]=30;
		objArr[2][2]= "Lumia";
		
		objArr[3][0]= "Vivo";
		objArr[3][1]= 40;
		objArr[3][2]= "V!2";
		
		objArr[4][0]= "Redmi";
		objArr[4][1]= 50;
		objArr[4][2]= "Note7";
		
		return objArr;
	}

}
