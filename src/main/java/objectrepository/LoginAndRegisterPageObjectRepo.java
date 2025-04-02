/**
 *  This is the class is developed to store object in the page
 * @author elna.joseph
 */
package objectrepository;

import org.openqa.selenium.By;

public class LoginAndRegisterPageObjectRepo extends webresources.SeleniumActions {
	
	
	public By titleProductStore=By.xpath("//a[@id='nava']");
	public By btnHomeSignup=By.id("signin2");
	public By lblSignUp=By.xpath("//h5[text()='Sign up']");
	public By txtFldUserName=By.id("sign-username");
	public By txtFldPassword=By.id("sign-password");
	public By txtFldLoginUSerName=By.id("loginusername");
	public By txtFldLoginPassword=By.id("loginpassword");
	public By btnSignUp=By.xpath("//button[text()='Sign up']");
	public By btnClose=By.xpath("//button[text()='Sign up']//preceding::button[@class='btn btn-secondary'][1]");
	
	public By btnHomeLogIn=By.id("login2");
	public By btnLogin=By.xpath("//button[text()='Log in']");
	public By lblLogin=By.xpath("//h5[text()='Log in']");
	public By nameOfUSer=By.id("nameofuser");
	public By btnAddToCart=By.xpath("//a[text()='Add to cart']");
	public By btnCart=By.id("cartur");
	public By btnPlaceOrder=By.xpath("//button[text()='Place Order']");
	public By lblPlaceORder=By.xpath("//h5[text()='Place order']");
	
	public By txtFldname=By.id("name");
	public By txtFldCountry=By.id("country");
	public By txtFldCity=By.id("city");
	public By txtFldCard=By.id("card");
	public By txtFldMonth=By.id("month");
	public By txtFldYEar=By.id("year");
	public By btnPurchase=By.xpath("//button[text()='Purchase']");
	public By btnOK=By.xpath("//button[text()='OK']");
	public By purchasesuccess=By.xpath("//h2[text()='Thank you for your purchase!']");
	public By purchaseResponseDetails=By.xpath("//h2[text()='Thank you for your purchase!']//following::p");
	public By btnLogOut=By.id("logout2");
	
	public By selectValue(String value)
	

	{
		By locator=By.xpath("//a[text()='"+value+"']");
		return locator;
	}
	
	
	public By heading(String value)
	{
		By locator=By.xpath("//h2[text()='"+value+"']");
		return locator;
	}
	
}
	
	
