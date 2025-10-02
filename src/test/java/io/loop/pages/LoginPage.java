package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstance;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage() {PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy (xpath = "//input[@type='text']")
    public WebElement usernameInput;

    @FindBy (xpath = "//input[@type='password']")
    public WebElement passwordInput;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement continueButton;

    public void insertField (String field, String input) {
        switch (field.toLowerCase().trim()) {
            case "username" -> BrowserUtils.waitForVisibility(usernameInput, DocuportConstance.LARGE).sendKeys(input);
            case "password" -> BrowserUtils.waitForVisibility(passwordInput, DocuportConstance.LARGE).sendKeys(input);
            default -> throw new IllegalArgumentException("No such a field: " + field);
        }
    }

    public void clickButton (String button) throws InterruptedException {
        switch (button.toLowerCase().trim()){
            case "login" -> BrowserUtils.waitForClickable(loginButton, DocuportConstance.LARGE).click();
            case "continue" -> {
                try {
                    BrowserUtils.waitForClickable(continueButton, DocuportConstance.LARGE).click();
                } catch (StaleElementReferenceException e){
                    Thread.sleep(3000);
                    WebElement element = Driver.getDriver().findElement(By.xpath("//span[.=' Continue ']"));
                    BrowserUtils.waitForClickable(element, DocuportConstance.LARGE).click();
                }
            }
            default -> throw new IllegalArgumentException("Not such a button: " + button);
        }
    }

    /**
     * Logins to docuport
     * @param username
     * @param password
     *
     */
    public void login(String username, String password) throws InterruptedException {
        BrowserUtils.waitForClickable(loginButton, 10);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
        Thread.sleep(5000);
//        if (BrowserUtils.waitForVisibility(loginButton, 10).isDisplayed()) {
//            loginButton.click();
//        }
    }
}
