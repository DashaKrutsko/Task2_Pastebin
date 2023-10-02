package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class AbstractPage {
    static final Duration WAIT_TIMEOUT_SECONDS = Duration.ofSeconds(20);
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForVisibilityOfElement(WebElement webElement) {
        return new WebDriverWait(driver, AbstractPage.WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForVisibilityOfListElements(List<WebElement> webElements) {
        new WebDriverWait(driver, AbstractPage.WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }
}
