package io.loop.utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;
import static org.junit.Assert.assertTrue;

public class BrowserUtils {

    public static Scenario myScenario;

    /**
     * takes screenshot
     * @author vk
     */
    public static void takeScreenshot(){
        try {
            myScenario.log("Current url is: " + Driver.getDriver().getCurrentUrl());
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            myScenario.attach(screenshot, "image/png", myScenario.getName());
        } catch (WebDriverException | ClassCastException wbd){
            wbd.getMessage();
        }
    }

    /**
     * validate if the driver switched to the expected url or title
     * @param driver
     * @param expectedUrl
     * @param expectedTitle
     * @author vk
     * implements assertion
     */
    public static void switchWindowAndValidate(WebDriver driver, String expectedUrl, String expectedTitle){

        //to lowercase the params in order to avoid miss type
        expectedTitle = expectedTitle.toLowerCase();
        expectedUrl = expectedUrl.toLowerCase();

        //get all window handles, switch one by one and each time check if the url matches expected to stop
        //var windowHandles = driver.getWindowHandles();
        Set <String> windowHandles = driver.getWindowHandles();

        for (String each : windowHandles) {
            driver.switchTo().window(each);

            if(driver.getCurrentUrl().toLowerCase().contains(expectedTitle)) {
                break;
            }
        }
        //after stopping on expected url, validate the title
        assertTrue(driver.getTitle().toLowerCase().contains(expectedTitle));
    }

    /**
     * @param driver
     * @param targetTitle
     * @author vk
     */
    public static void switchToWindow(WebDriver driver, String targetTitle){
        String origin = driver.getTitle();

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);

            if (driver.getTitle().contains(targetTitle)) {
                return;
            }
        }
        driver.switchTo().window(origin);
    }

    /**
     * clicks any link from loop practice
     * @param nameOfPage
     * @author vk
     */
    public static void loopLinkClick(String nameOfPage){
        WebElement element = Driver.getDriver().findElement(By.xpath("//a[.='" + nameOfPage + "']"));
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DocuportConstance.LARGE));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * waits for the provided element to be clickable
     * @param element
     * @param timeout
     * @return element
     * @author vk
     */
    public static WebElement waitForClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * waits for the provided element to be invisible on the page
     * @param element
     * @param timeout
     * @author vk
     */
    public static void waitForInvisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * waits for the provided element to be visible on the page
     * @param element
     * @param timeout
     * @author vk
     */
    public static WebElement waitForVisibility(WebElement element, int timeout){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void uploadFileForWindows(String filePath) throws AWTException {
        //copy the file path
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        //simulate keyboard paste and enter
        Robot robot = new Robot();
        robot.delay(1000);

        //press CTRL + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        //press ENTER
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void uploadFileForMac(String filePath) throws AWTException {
        //copy the file path
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        //simulate keyboard paste and enter
        Robot robot = new Robot();
        robot.delay(1000);

        // press ⌘ + Shift + G to open go to finder
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_META);

        // Paste file path (⌘ + V)
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);

        robot.delay(1000);

        // press enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(1000);

        // Press Enter again to confirm file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void uploadFileUsingAppleScript(String filePath) throws Exception {
        String script = "tell application \"System Events\"\n"
                + "delay 1\n"
                + "keystroke \"G\" using {command down, shift down}\n"
                + "delay 1\n"
                + "keystroke \"" + filePath + "\"\n"
                + "keystroke return\n"
                + "delay 1\n"
                + "keystroke return\n"
                + "end tell";

        String[] command = { "osascript", "-e", script };
        Runtime.getRuntime().exec(command);
    }

    public static void uploadFileUsingAppleScript2(String filePath) throws Exception {
        // Convert file path to POSIX format (needed for AppleScript)
        String posixPath = filePath.replace("\\", "\\\\").replace("\"", "\\\"");

        String script =
                "tell application \"System Events\"\n"
                + "   delay 1"
                + "   keystroke \"G\" using {command down, shift down}\n"
                + "   delay 1\n"
                + "   keystroke \"" + posixPath + "\"\n"
                + "   delay 1\n"
                + "   keystroke return\n"
                + "   delay 1\n"
                + "   keystroke return\n"
                + "end tell";

        String[] command = { "osascript", "-e", script };
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
    }

    public static void uploadFileWithRobot(String filePath) throws Exception {
        // 1) Put the path onto the clipboard (robust for spaces/special chars)
        copyToClipboard(filePath);

        Robot robot = new Robot();
        robot.setAutoDelay(120); // small default delay between events

        // 2) Open "Go to the folder…" in the file dialog: ⌘ + ⇧ + G
        robot.keyPress(KeyEvent.VK_META);      // Command
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_META);

        // 3) Give the OS a moment to show the mini path dialog
        Thread.sleep(350);

        // 4) Paste the file path: ⌘ + V
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);

        // 5) Confirm path (Enter), then confirm the picker (Enter)
        Thread.sleep(200);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private static void copyToClipboard(String text) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(text), null);
    }

    public static void uploadFileMac2(String filePath) {
        // 1) Ensure absolute POSIX-like path
        String path = new java.io.File(filePath).getAbsolutePath();

        // 2) Put path on clipboard
        StringSelection selection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        try {
            Robot robot = new Robot();
            robot.setAutoWaitForIdle(true);

            // Small helper
            Runnable shortPause = () -> {
                try { Thread.sleep(350); } catch (InterruptedException ignored) {}
            };

            // 3) Give the OS time to open the file chooser (you said it's already open)
            Thread.sleep(700);

            // 4) Click center to ensure the dialog has focus
            java.awt.Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int cx = screen.width / 2;
            int cy = screen.height / 2;
            robot.mouseMove(cx, cy);
            shortPause.run();
            robot.mousePress(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(400);

            // 5) Open "Go to Folder" (⌘ + ⇧ + G)
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_META);

            Thread.sleep(500);

            // 6) Try paste (⌘ + V)
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_META);

            Thread.sleep(250);

            // If paste didn't work (sometimes blocked), type it manually.
            // You can comment this block out if paste works.
            if (!clipboardLikelyWorked()) {
                typeString(robot, path);
            }

            // 7) Hit Enter to close "Go to Folder"
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(500);

            // 8) Hit Enter again to confirm the file selection
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean clipboardLikelyWorked() {
        // Often you can just return true and rely on paste; keeping hook for debugging.
        return true;
    }

    private static void typeString(Robot robot, String s) {
        for (char c : s.toCharArray()) {
            typeChar(robot, c);
            try { Thread.sleep(8); } catch (InterruptedException ignored) {}
        }
    }

    private static void typeChar(Robot robot, char c) {
        // Basic mapping for common path characters on a US layout
        switch (c) {
            case '/': robot.keyPress(KeyEvent.VK_SLASH); robot.keyRelease(KeyEvent.VK_SLASH); return;
            case '-': robot.keyPress(KeyEvent.VK_MINUS); robot.keyRelease(KeyEvent.VK_MINUS); return;
            case '_': robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_MINUS); robot.keyRelease(KeyEvent.VK_SHIFT); return;
            case '.': robot.keyPress(KeyEvent.VK_PERIOD); robot.keyRelease(KeyEvent.VK_PERIOD); return;
            case ' ': robot.keyPress(KeyEvent.VK_SPACE); robot.keyRelease(KeyEvent.VK_SPACE); return;
            default:
                boolean upper = Character.isUpperCase(c);
                int code = KeyEvent.getExtendedKeyCodeForChar(Character.toUpperCase(c));
                if (code == KeyEvent.VK_UNDEFINED) return;
                if (upper) robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(code);
                robot.keyRelease(code);
                if (upper) robot.keyRelease(KeyEvent.VK_SHIFT);
        }
    }
}
