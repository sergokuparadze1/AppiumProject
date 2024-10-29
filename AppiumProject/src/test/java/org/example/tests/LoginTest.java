package org.example.tests;

import io.qameta.allure.*;
import org.example.Base;
import org.example.Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends Base {
    @Test
    @Description("positive login test")
    public void loginUser() {
        LoginPage page = new LoginPage();
        page.fillUsernameWithPassword("standart_user", "secret_sauce", true);
        page.checkDashboardIsShown();
    }
    @Test
    @Description("positive login test")
    public void login_IncorrectUserAndPassword() {
        LoginPage page = new LoginPage();
        page.fillUsernameWithPassword("standart_userTest", "secret_sauce", true);
        page.checkLoginErrorMessage("Username and password do not match any user in this service");
    }

}
