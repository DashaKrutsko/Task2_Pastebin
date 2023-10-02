package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class PastebinNewPastePage extends AbstractPage {

    @FindBy(xpath = "//div[@class='de1']")
    private List<WebElement> searchCode;

    @FindBy(xpath = "//a[contains(@href, 'archiv')][@class='btn -small h_800']")
    private WebElement searchFormat;

    public PastebinNewPastePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getFormat() {
        return waitForVisibilityOfElement(searchFormat).getText();
    }

    public String getCode() {
        waitForVisibilityOfListElements(searchCode);
        StringBuilder codeBuilder = new StringBuilder();
        int size = searchCode.size();
        for (WebElement codeElement : searchCode) {
            codeBuilder.append(codeElement.getText());
            if (searchCode.indexOf(codeElement) < size - 1) {
                codeBuilder.append("\n");
            }
        }
        return codeBuilder.toString();
    }

}
