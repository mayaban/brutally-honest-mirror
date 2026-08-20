package com.brutallyhonestmirror.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MirrorPage {

    private final WebDriver driver;

    private final By excuseTextarea = By.cssSelector(".entry-form-textarea");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By reflectionDisplay = By.cssSelector(".reflection-display");

    public MirrorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void submitExcuse(String excuseText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(excuseTextarea));

        driver.findElement(excuseTextarea).sendKeys(excuseText);
        driver.findElement(submitButton).click();
    }

    public String waitForReflection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(reflectionDisplay));
        return driver.findElement(reflectionDisplay).getText();
    }
}