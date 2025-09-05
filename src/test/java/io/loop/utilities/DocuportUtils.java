package io.loop.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class DocuportUtils {
    /**
     * logins to docuport application
     * @param driver, which is initialized in the test base
     * param role, comes from docuport constants
     * author vk
     */
    public static void login(WebDriver driver, String role) throws InterruptedException {
        driver.get(ConfigurationReader.getProperties("docuportBETA"));
        WebElement username = driver.findElement(By.xpath("//label[.='Username or email']/following-sibling::input"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        switch (role.toLowerCase()) {
            case "client":
                username.sendKeys(DocuportConstance.USERNAME_CLIENT);
                password.sendKeys(DocuportConstance.PASSWORD);
                break;
            case "supervisor":
                username.sendKeys(DocuportConstance.USERNAME_SUPERVISOR);
                password.sendKeys(DocuportConstance.PASSWORD);
                break;
            case "advisor":
                username.sendKeys(DocuportConstance.USERNAME_ADVISOR);
                password.sendKeys(DocuportConstance.PASSWORD);
                break;
            case "employee":
                username.sendKeys(DocuportConstance.USERNAME_EMPLOYEE);
                password.sendKeys(DocuportConstance.PASSWORD);
                break;
            default:
                throw new InterruptedException("There is not such a role: " + role);
        }
        loginButton.click();

        if (role.toLowerCase().equals("client")) {
            Thread.sleep(3000);
            WebElement cont = driver.findElement(By.xpath("//button[@type='submit']"));
            cont.click();
            Thread.sleep(3000);
        }
    }

    public static void logout(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement userIcon = driver.findElement(By.xpath("//div[@class='v-avatar primary']"));
        userIcon.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement logoutButton = driver.findElement(By.xpath("//span[contains(text(),'Log out')]"));
        logoutButton.click();
    }
    /**
     * returns any field from table by given email
     * @param driver
     * @param emailAddress
     * @param field
     * @return
     * @author vk
     */
    public static String returnAnyFieldFromTable(WebDriver driver, String emailAddress, String field) throws InterruptedException {
        WebElement element = null;
        String xpath = "";

        switch (field.toLowerCase()) {
            case "full name":
                xpath = "//td[2][text()='" + emailAddress + "']/preceding-sibling::td//span[2]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "username":
                xpath = "//td[2][text()='" + emailAddress + "']/following-sibling::td[1]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "inviter":
                xpath = "//td[2][text()='" + emailAddress + "']/following-sibling::td[2]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "phone number":
                xpath = "//td[2][text()='" + emailAddress + "']/following-sibling::td[3]/span";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "role":
                xpath = "//td[2][text()='" + emailAddress + "']/following-sibling::td[4]/span/span";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "advisor":
                xpath = "//td[2][text()='" + emailAddress + "']/following-sibling::td[5]";
                element = driver.findElement(By.xpath(xpath));
                break;
            default: throw new InterruptedException("There is not such a field: " + field);
        }
        return element.getText().trim();
    }
}
