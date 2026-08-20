package com.brutallyhonestmirror.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;

    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By registerButton = By.cssSelector("button[type='submit']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://localhost:5173/register");
    }

    public void register(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }
}
