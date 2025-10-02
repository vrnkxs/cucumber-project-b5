package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstance;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class ReceivedDocsPage {

    public ReceivedDocsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[@class='subtitle-2 text-none' and .='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//label[.='Document name']/following-sibling::input")
    public WebElement documentName;

    @FindBy(xpath = "//label[.='Tags']/following-sibling::div/input")
    public WebElement tags;

    @FindBy(xpath = "//span[@class='white--text' and .='License']")
    public WebElement licenceInput;

    @FindBy(xpath = "//span[@class='white--text' and .='Tax Return']")
    public WebElement taxReturnInput;

    @FindBy(xpath = "//label[.='Upload date']/following-sibling::input")
    public WebElement uploadDate;

    @FindBy(xpath = "//div[@class='v-btn__content' and text()='30']")
    public WebElement date30;

    @FindBy(xpath = "//label[.='Uploaded by']/following-sibling::input[1]")
    public WebElement uploadedBy;

    @FindBy(xpath = "//div[@class='v-list-item__title' and .='advisor advisor']")
    public WebElement advisorAdvisor;

    public void clickButton(String button) {
        switch(button.toLowerCase().trim()) {
            case "search" -> BrowserUtils.waitForClickable(searchButton, DocuportConstance.LARGE).click();
            default -> throw new IllegalArgumentException("No such button: " + button);
        }
    }
    public void insertField (String field, String input) throws InterruptedException {
        switch (field.toLowerCase().trim()) {
            case "document name" ->
                    BrowserUtils.waitForVisibility(documentName, DocuportConstance.LARGE).sendKeys(input);
            default -> throw new IllegalArgumentException("No such a field: " + field);
        }
    }

    public void selectOption (String field, String option) throws InterruptedException {
        switch (field.toLowerCase().trim()) {
            case "tags" -> {
                BrowserUtils.waitForClickable(tags, DocuportConstance.LARGE).click();
//                BrowserUtils.waitForClickable(licenceInput, DocuportConstance.LARGE).click();
//                BrowserUtils.waitForVisibility(taxReturnInput, DocuportConstance.LARGE).click();
                if (option.equalsIgnoreCase("license")) {
                    BrowserUtils.waitForClickable(licenceInput, DocuportConstance.LARGE).click();
                } else if (option.equalsIgnoreCase("tax return")) {
                    BrowserUtils.waitForClickable(taxReturnInput, DocuportConstance.LARGE).click();
                }
            }
            case "upload date" -> {
                Thread.sleep(3000);
                BrowserUtils.waitForClickable(uploadDate, DocuportConstance.LARGE).click();
                BrowserUtils.waitForClickable(date30, DocuportConstance.LARGE).click();
            }
            case "uploaded by" -> {
                    BrowserUtils.waitForVisibility(uploadedBy, DocuportConstance.LARGE).click();
                    BrowserUtils.waitForVisibility(advisorAdvisor, DocuportConstance.LARGE).click();
            }
            default -> throw new IllegalArgumentException("No such a field: " + field);
        }
    }
}
