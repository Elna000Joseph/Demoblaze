/**
 *  This is the class is developed to for doing page level action
 * @author elna.joseph
 */
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import objectrepository.LoginAndRegisterPageObjectRepo;

public class LoginAndRegisterPage extends LoginAndRegisterPageObjectRepo {
	
	
	public void launchTheApplication(String url)
	{
		openUrl(url);
		waitforload(10000);
		verifyElementPresent(titleProductStore, "Launched the  Product Purchase on Demoblaze application successfully", "Verify Launched the Product Purchase on Demoblaze application successfully");
	}

public void clickOnSignUpButton()
{
	verifyElementPresent(btnHomeSignup, "User able to see Sign Up button", "Verify User able to see Sign Up button");
	click(btnHomeSignup,"Sign up");
	waitforload(2000);
	verifyElementPresent(lblSignUp, "User clicked on SignUp button and Reached to Signup page", "Verify User clicked on SignUp button and Reached to Signup page");

}
	

public void registerNewUSer(String username, String password)
{
	clear(txtFldUserName,"USername");
	enterText(txtFldUserName, username, "Username", true);
	waitforload(2000);
	clear(txtFldPassword,"PassWord");
	enterText(txtFldPassword, password, "PassWord", true);
	waitforload(2000);

		click(btnSignUp, "SignUp");
		waitforload(3000);
		String alertMessage=alertText();
		alertAccpet();
		
		waitforload(2000);
		if(alertMessage.contentEquals("Sign up successful."))
		{
			pass("User registered successfully "+alertMessage,"VErify user regresitered successfully or not");
		}
		else if(alertMessage.contentEquals("This user already exist."))
		{
			info("User was not able to register "+alertMessage,"VErify user regresitered successfully or not");
			click(btnClose, "Close");
		}
		else
		{
			fail("Occuered an unexpected error, please tru again later","Occuered an unexpected error, please tru again later");
		}
	
}

public void clickOnLogin()
{
	waitforload(2000);
	verifyElementPresent(btnHomeLogIn, "USer able to see Login button", "Verify USer able to see Loginp button");
	click(btnHomeLogIn,"Log in");
	verifyElementPresent(lblLogin, "User clicked on Login button and REached to Login page", "VerifyUser clicked on Login button and REached to Login page");
}

public void loginUser(String username,String password)
{
	clear(txtFldLoginUSerName,"USername");
	enterText(txtFldLoginUSerName, username, "Username", true);
	waitforload(2000);
	clear(txtFldLoginPassword,"PassWord");
	enterText(txtFldLoginPassword, password, "PassWord", true);
	waitforload(2000);
	click(btnLogin, "Login");
	waitforload(2000);
	String actName=getText(nameOfUSer, "Name of the user");
	String[] names=actName.split(" ");
	compareValues(username, names[1], "Name of the user");
	pass("User logged in successfully","Verify user login successfully or not");
}

public void selectCategoryAndProduct(String category, String prodcut)
{
	By categoryLoc=selectValue(category);
	click(categoryLoc, "Category");
	waitforload(2000);
	By productLoc=selectValue(prodcut);
	verifyElementPresent(productLoc, "User clicked on category "+category+" and able to see the product", "Verify user clicked on the category");
	click(productLoc, "Product");
	waitforload(1000);
	By productNAme=heading(prodcut);
	verifyElementPresent(productNAme, "User clicked on product "+prodcut+" and reached to product detail page", "Verify user clicked on the product");
	verifyElementPresent(btnAddToCart, "User able to see Add to cart button", "Verify User able to see Add to cart button");

}



public void clickOnADDTocart()
{
	click(btnAddToCart, "Add to Cart");
	waitforload(2000);
	String alert=alertText();
	alertAccpet();
	
	compareValues("Product added.", alert, "Add to cart confirmation message");
}

public void clickOnCart()
{
	verifyElementPresent(btnCart, "USer able to see Cart button", "Verify USer able to see Cart button");
	click(btnCart,"Cart");
	By productNAme=heading("Products");
	verifyElementPresent(productNAme, "User clicked on Cart button and REached to Cart page", "VerifyUser clicked on Cart button and REached to Cart page");
	
}

public void clickOnPlaceOrder()
{
	waitforload(2000);
	click(btnPlaceOrder,"Place order");
	verifyElementPresent(lblPlaceORder, "User clicked on place orderr button", "Verify User clicked on place orderr button");
}

public void enterPurchaseDetails(String name,String country,String city,String card,String month,String year)
{
	waitforload(1000);
	enterText(txtFldname, name, "Name", false);
	enterText(txtFldCountry, country, "Country", false);
	enterText(txtFldCity, city, "City", false);
	enterText(txtFldCard, card, "Card", false);
	enterText(txtFldMonth, month, "Month", false);
	enterText(txtFldYEar, year, "Year", true);
}

public void clickOnPurchase()
{
	click(btnPurchase, "Purchase");
	verifyElementPresent(purchasesuccess, "User able to see purchase success message", "Verify User able to see purchase success message");
	String details=getText(purchaseResponseDetails, "Puchase details");
	pass("USer able to see purchase details "+details,"Verify purchase details");
	waitforload(2000);
	click(btnOK, "OK");
	waitforload(2000);
	verifyElementNotPresent(purchasesuccess, "User clicked on OK button", "Verify User clicked on OK button");
}

public void logOut()
{
	click(btnLogOut, "Log out");
	verifyElementPresent(btnHomeLogIn, "User clicked on log out and successfully logged out", "Verify User clicked on log out and successfully logged out");
}
}
