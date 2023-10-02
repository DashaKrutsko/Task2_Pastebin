package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.PastebinHomePage;
import page.PastebinNewPastePage;

public class PastebinCreatingNewPasteTest {
    private static final String PASTE_NAME = "how to gain dominance among developers";
    private static final String CODE_STRING = "git config --global user.name \"New Sheriff in Town\"\ngit reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\ngit push origin master --force";
    private static final String CODE_FORMAT = "Bash";
    private static final String EXPIRATION_TIME = "10 Minutes";
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
                .enterCode(CODE_STRING)
                .enterName(PASTE_NAME)
                .enterExpiration(EXPIRATION_TIME)
                .enterFormat(CODE_FORMAT)
                .createNewPaste();

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(newPaste.getPageTitle(), PASTE_NAME + " - Pastebin.com", "Incorrect paste name");
        softAssertion.assertEquals(newPaste.getFormat(), CODE_FORMAT,"Incorrect code format");
        softAssertion.assertEquals(newPaste.getCode(), CODE_STRING ,"Incorrect code");
        softAssertion.assertAll();

    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}

