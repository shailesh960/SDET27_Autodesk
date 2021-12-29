package com.crm.autodesk.objectRepositry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genricuUtilite.WebDriverUtilite;

public class LoginPage extends WebDriverUtilite
{
	//Declaration
@FindBy(name="user_name")
private WebElement userEdit;

@FindBy(name="user_password")
private WebElement passwordEdit;

@FindBy(id="submitButton")
private WebElement loginButton;


//Intilization 
public LoginPage(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

//Utilization
public WebElement getUserEdit() {
	return userEdit;
}


public WebElement getPasswordEdit() {
	return passwordEdit;
}


public WebElement getLoginButton() {
	return loginButton;
}

//Bussiness liberaris
public void clickOnLogginButton(String USERNAME, String PASSWORD )
{
	userEdit.sendKeys(USERNAME);
	passwordEdit.sendKeys(PASSWORD);
	loginButton.click();
	
	
}


}
