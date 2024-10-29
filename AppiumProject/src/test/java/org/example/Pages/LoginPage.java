package org.example.Pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    protected AndroidDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Username\"]")
    private WebElement username;

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]")
    private WebElement password;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")
    private WebElement loginBtn;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Dashboard\"]")
    private WebElement dashBoard;

    By loginErrorMessage = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");

    public void sendKey(String txt, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(txt);
    }
    public void clickToBtn(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    @Step("ეს მეთოდი ავსებს ინფორმაციას მომხამრებელს და პაროლს")
    public void fillUsernameWithPassword(String user, String pass, boolean clickToBtn) {
        sendKey(user, username);
        sendKey(pass, password);
        if (clickToBtn) {
            clickToBtn(loginBtn);
        }
    }
    @Step("ამოწმებს შეცდომის ტექსტს")
    public void checkLoginErrorMessage(String messageText) {
        String error = "";
        try{
            error = new WebDriverWait(driver , Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage)).getText();
        }catch (Exception exception){}
        Assert.assertEquals(error , messageText , "შეცდომის ტექსტი არ შეესაბამება დაბრუნებულ");
    }
    @Step("მოწმდება გადავიდა თუ არა მთავარ გვერდზე")
    public void checkDashboardIsShown() {
        Assert.assertTrue(dashBoard.isDisplayed(), "Dashboard is not displayed, login might have failed.");
    }
}
