package PageTests;

import base.BaseTest;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;


public class LoginPageTests extends BaseTest {


    LoginPage loginPage;
    JSONObject jsonObject = getJsonObject();
    @BeforeMethod
    public void setTest() {
        loginPage = new LoginPage(driver);
    }
    @Test
    public void LoginWithValidUserNameAndPasswordAndCheckPageTitle() {
        loginPage.enterUserName(jsonObject.get("validUserName").toString());
        loginPage.enterPassword(jsonObject.get("validPassword").toString());
        loginPage.clickLoginButton();
        String expectedUrl = jsonObject.get("productsPageUrl").toString();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }


    @Test(enabled = true)
    public void LoginWithWrongUserNameAndValidPasswordAndCheckErrorMessage() {
        loginPage.enterUserName(jsonObject.get("wrongUserName").toString());
        loginPage.enterPassword(jsonObject.get("validPassword").toString());
        loginPage.clickLoginButton();
        String expectedError = jsonObject.get("errMsgWrongUserName").toString();
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void LoginWithLockedOutUserAndValidPasswordAndCheckErrorMessage() {
        loginPage.enterUserName(jsonObject.get("lockedOutUser").toString());
        loginPage.enterPassword(jsonObject.get("validPassword").toString());
        loginPage.clickLoginButton();
        String expectedError = jsonObject.get("errMsgLockedOutUser").toString();
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void LoginWithEmptyUserNameAndValidPasswordAndCheckErrorMessage(){
        loginPage.enterUserName(jsonObject.get("emptyUserName").toString());
        loginPage.enterPassword(jsonObject.get("validPassword").toString());
        loginPage.clickLoginButton();
        String expectedError = jsonObject.get("errMsgEmptyUserName").toString();
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }
    @Test
    public void LoginWithValidUserNameAndEmptyPasswordAndCheckErrorMessage(){
        loginPage.enterUserName(jsonObject.get("validUserName").toString());
        loginPage.enterPassword(jsonObject.get("emptyPassword").toString());
        loginPage.clickLoginButton();
        String expectedError = jsonObject.get("errMsgEmptyPassword").toString();
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }
}