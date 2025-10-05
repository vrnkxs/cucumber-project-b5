package io.loop.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.POM;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstance;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SampleDocuportStepDefs {

    private static final Logger LOG = LogManager.getLogger();
    POM pages = new POM();

    @When("user inserts {string} to {string} field on {string} page")
    public void user_insert_to_field_on_page(String input, String field, String page) throws InterruptedException {

        switch (page.toLowerCase().trim()) {
            case "login" -> {
                pages.getLoginPage().insertField(field, input);
                LOG.info(input + " - was successfully sent to - " + field);
            }
            case "received docs" -> {
                pages.getReceivedDocsPage().insertField(field, input);
                LOG.info(input + " - was successfully sent to - " + field);
            }
            case "invitations" -> {
                pages.getInvitationsPage().insertField(field, input);
                LOG.info(input + " - was successfully sent to - " + field);
            }
            default -> throw new IllegalArgumentException("No such a page: " + page);
        }

    }
    @When("user clicks {string} button on {string} page")
    public void user_clicks_button_on_page(String button, String page) throws InterruptedException {
        switch (page.toLowerCase().trim()) {
            case "login", "choose account" -> {
                pages.getLoginPage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "left navigate" -> {
                pages.getLeftNavigatePage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "received docs" -> {
                pages.getReceivedDocsPage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "my uploads" -> {
                pages.getMyUploadsPage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "invitations" -> {
                pages.getMyUploadsPage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            default -> throw new IllegalArgumentException("No such a page: " + page);
        }
    }

    @When("user selects option on {string} field on {string} page")
    public void user_selects_option_on_field_on_page(String field, String page, List <String> allOptions) throws InterruptedException {
        for (String each : allOptions) {
            pages.getReceivedDocsPage().selectOption(field, each);
            LOG.info(each + " - was successfully selected");
        }
    }

    @When("user selects {string} on {string} field on {string} page")
    public void user_selects_on_field_on_page(String option, String field, String page) throws InterruptedException {
        switch (field.toLowerCase().trim()) {
            case "upload date", "uploaded by" -> {
                pages.getReceivedDocsPage().selectOption(field, option);
                LOG.info(option + " - was successfully selected");
            }
        }
    }

    @When("user clicks {string} button on {string}")
    public void user_clicks_button_on_on_page(String button, String field) {
        switch (field.toLowerCase().trim()) {
            case "header" -> {
                pages.getReceivedDocsPage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "footer" -> {
                pages.getReceivedDocsPage().clickButton2(button);
                LOG.info(button + " - was successfully clicked");
            }
        }
    }

    @When("user should be able to see the message {string}")
    public void user_should_be_able_to_see_the_message(String string) {
        WebElement element = Driver.getDriver().findElement(By.xpath("//p[contains(text(), 'Your search returned')]"));
        assertEquals("Your search returned was not found", string, element.getText());
    }

    @When("user attaches the document and enters the required information")
    public void user_attaches_the_document_and_enters_the_required_information() throws InterruptedException, AWTException {
        //        WebElement element = Driver.getDriver().findElement(By.xpath("//input[@type='file']"));
//        element.sendKeys("/Users/veronikakuliba/Desktop/text.txt");
//        BrowserUtils.uploadFileForMac("/Users/veronikakuliba/Desktop/text.txt");

        Thread.sleep(3000);
        //BrowserUtils.typeChar("/Users/veronikakuliba/Desktop/text.txt");

        BrowserUtils.uploadFileMac2("/Users/veronikakuliba/Desktop/text.txt");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        BrowserUtils.waitForClickable(pages.getMyUploadsPage().serviceDropdown, DocuportConstance.LARGE).click();
        BrowserUtils.waitForClickable(pages.getMyUploadsPage().bookkeepingOption, DocuportConstance.LARGE).click();
        BrowserUtils.waitForClickable(pages.getMyUploadsPage().docTypeOption, DocuportConstance.LARGE).click();
        BrowserUtils.waitForClickable(pages.getMyUploadsPage().quarterOption, DocuportConstance.LARGE).click();
    }

    @Then("user uploads a document")
    public void user_uploads_a_document() throws Exception {
        BrowserUtils.waitForClickable(pages.getMyUploadsPage().uploadButton, DocuportConstance.LARGE).click();
    }

    @When("user clicks sent button")
    public void user_clicks_sent_button() throws InterruptedException {
        Thread.sleep(3000);
        BrowserUtils.waitForClickable(pages.getInvitationsPage().sentRadiobutton, DocuportConstance.LARGE).click();
    }

}
