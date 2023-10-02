package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.PastebinHomePage;
import page.PastebinNewPastePage;
import static org.testng.Assert.assertEquals;
import static page.PastebinHomePage.CODE_STRING;
import static page.PastebinHomePage.PASTE_NAME;
import static page.PastebinHomePage.CODE_FORMAT;

public class PastebinCreatingNewPasteTest {

    private WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Checking fields of a new item")
    public void checkCreatingNewItem() {
        PastebinNewPastePage newPaste = new PastebinHomePage(driver)
                .openPage()
                .acceptCookies()
                .enterCode()
                .enterName()
                .enterExpiration()
                .enterFormat()
                .createNewPaste();

        assertEquals(newPaste.getPageTitle(), PASTE_NAME + " - Pastebin.com");
        assertEquals(newPaste.getFormat(), CODE_FORMAT);
        assertEquals(newPaste.getCode(), CODE_STRING);
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}

