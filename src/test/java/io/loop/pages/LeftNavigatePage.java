package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstance;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftNavigatePage {

    public LeftNavigatePage () {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[contains(text(),'Upload')]")
    public WebElement uploadButton;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    public WebElement homeButton;

    @FindBy(xpath = "//span[contains(text(),'Received')]")
    public WebElement receivedDocsButton;

    @FindBy(xpath = "//span[contains(text(),'My uploads')]")
    public WebElement myUploads;

    @FindBy(xpath = "//span[contains(text(),'Invitations')]")
    public WebElement invitationsButton;

    @FindBy(xpath = "//span[contains(text(),'1099 Form')]")
    public WebElement form1099;

    @FindBy(xpath = "//span[contains(text(),'Reconciliations')]")
    public WebElement reconciliations;

    @FindBy(xpath = "//a[contains(text(),'Terms')]")
    public WebElement termsAndConditionsButton;

    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {
            case "home" -> BrowserUtils.waitForClickable(homeButton, DocuportConstance.LARGE).click();
            case "upload" -> BrowserUtils.waitForClickable(uploadButton, DocuportConstance.LARGE).click();
            case "received docs" -> BrowserUtils.waitForClickable(receivedDocsButton, DocuportConstance.LARGE).click();
            case "invitations" -> BrowserUtils.waitForClickable(invitationsButton, DocuportConstance.LARGE).click();
            case "my uploads" -> BrowserUtils.waitForClickable(myUploads, DocuportConstance.LARGE).click();
            case "1099 form" -> BrowserUtils.waitForClickable(form1099, DocuportConstance.LARGE).click();
            case "reconciliations" -> BrowserUtils.waitForClickable(reconciliations, DocuportConstance.LARGE).click();
            case "terms and conditions" -> BrowserUtils.waitForClickable(termsAndConditionsButton, DocuportConstance.LARGE).click();
        }
    }

}
