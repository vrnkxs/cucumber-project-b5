package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstance;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvitationsPage {
    public InvitationsPage () {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//label[.='Recipient']/following-sibling::input")
    public WebElement recipientField;

    @FindBy(xpath = "//input[@type='radio' and @value='1']")
    public WebElement sentRadiobutton;

//    public void clickButton(String button) {
//        switch(button.toLowerCase().trim()) {
//            case "invitations" -> BrowserUtils.waitForClickable(uploadDocsButton, DocuportConstance.LARGE).click();
//            default -> throw new IllegalArgumentException("No such button: " + button);
//        }
//    }
    public void insertField (String field, String input) throws InterruptedException {
        switch (field.toLowerCase().trim()) {
            case "recipient" -> {
                Thread.sleep(3000);
                BrowserUtils.waitForVisibility(recipientField, DocuportConstance.LARGE).sendKeys(input);
            }
            default -> throw new IllegalArgumentException("No such a field: " + field);
        }
    }
}
