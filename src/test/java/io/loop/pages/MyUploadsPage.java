package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstance;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyUploadsPage {

    public MyUploadsPage () {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[contains(text(), 'Upload documents')]")
    public WebElement uploadDocsButton;

    @FindBy(xpath = "//span[contains(text(), 'Upload file')]")
    public WebElement uploadFileButton;

    @FindBy(xpath = "//span[contains(text(),' Upload ')]")
    public WebElement uploadButton;

    @FindBy(xpath = "//label[.='Service']/following-sibling::input[1]")
    public WebElement serviceDropdown;

    @FindBy(xpath = "//div[.='Bookkeeping' and @class='v-list-item__title']")
    public WebElement bookkeepingOption;

    @FindBy(xpath = "//div[@class='mb-3 col col-12']/span")
    public WebElement docTypeOption;

    @FindBy(xpath = "//div[@class='mb-3 col col-12']/span[.=' Q1 ']")
    public WebElement quarterOption;

    public void clickButton(String button) {
        switch(button.toLowerCase().trim()) {
            case "upload documents" -> BrowserUtils.waitForClickable(uploadDocsButton, DocuportConstance.LARGE).click();
            case "upload file" -> BrowserUtils.waitForClickable(uploadFileButton, DocuportConstance.LARGE).click();
            default -> throw new IllegalArgumentException("No such button: " + button);
        }
    }

}
