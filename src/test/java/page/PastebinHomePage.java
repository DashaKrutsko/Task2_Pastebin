package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PastebinHomePage extends AbstractPage {
    private static final String HOME_PAGE_URL = "https://pastebin.com/";
    public static final String PASTE_NAME = "how to gain dominance among developers";
    public static final String CODE_STRING = "git config --global user.name \"New Sheriff in Town\"\ngit reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\ngit push origin master --force";
    public static final String CODE_FORMAT = "Bash";

    @FindBy(id = "postform-text")
    private WebElement searchCode;

    @FindBy (id = "postform-expiration")
    private WebElement searchExpiration;

    @FindBy (id = "postform-format")
    private WebElement searchFormat;

    @FindBy (id = "postform-name")
    private WebElement searchName;

    @FindBy (xpath = "//button[@mode='primary']")
    private WebElement searchAcceptCookies;

    @FindBy(xpath = "//button[@type='submit'][@class='btn -big']")
    private WebElement searchButton;

    public PastebinHomePage(WebDriver driver) {
        super(driver);
    }

    public PastebinHomePage openPage() {
        driver.get(HOME_PAGE_URL);
        return this;
    }

    public PastebinHomePage acceptCookies() {
        waitForVisibilityOfElement(searchAcceptCookies).click();
        return this;
    }

    public PastebinHomePage enterCode() {
        waitForVisibilityOfElement(searchCode).sendKeys(CODE_STRING);
        return this;
    }

    public PastebinHomePage enterName() {
        waitForVisibilityOfElement(searchName).sendKeys(PASTE_NAME);
        return this;
    }

    public PastebinHomePage enterExpiration() {
        selectDropboxElement(searchExpiration, "10 Minutes");
        return this;
    }

    public PastebinHomePage enterFormat() {
        selectDropboxElement(searchFormat, CODE_FORMAT);
        return this;
    }

    public PastebinNewPastePage createNewPaste() {
       waitForVisibilityOfElement(searchButton).click();
       return new PastebinNewPastePage(driver);
    }

    private void selectDropboxElement(WebElement webElement, String optionText) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].style.visibility='visible';", webElement);
        Select select = new Select(waitForVisibilityOfElement(webElement));
        select.selectByVisibleText(optionText);
    }

}
