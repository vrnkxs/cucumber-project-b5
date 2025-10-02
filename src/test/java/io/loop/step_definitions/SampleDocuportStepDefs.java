package io.loop.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.loop.pages.POM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

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
            case "upload date" -> {
                pages.getReceivedDocsPage().selectOption(field, option);
                LOG.info(option + " - was successfully selected");
            }
        }
    }
}
