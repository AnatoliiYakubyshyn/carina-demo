package com.zebrunner.carina.demo.swaglabs;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.zebrunner.carina.core.AbstractTest;

public class LoginTest extends AbstractTest {

    @Test
    public void loginTest() {
        getDriver().get("https://www.saucedemo.com");
        getDriver().manage().addCookie(new Cookie("session-username", "standard_user"));
        getDriver().get("https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
