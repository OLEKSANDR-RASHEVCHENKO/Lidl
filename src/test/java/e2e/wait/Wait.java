package e2e.wait;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Wait {
    public WebDriver driver;

    Duration TIMEOUT = Duration.ofSeconds(10);

    public Wait(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait setWait() {
        return new WebDriverWait(driver, TIMEOUT);
    }

    public void forVisibility(WebElement element) {
        try {
            setWait().until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException(element.getAccessibleName() + " is not visible more than " + TIMEOUT.toString());
        }
    }

    public void forAllVisibility(List<WebElement> elements) {
        try {
            setWait().until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (TimeoutException e) {
            throw new TimeoutException(elements.size() + " are not visible more than " + TIMEOUT.toString());
        }
    }

    public void forInvisibility(WebElement element) {
        try {
            setWait().until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException(element.getAccessibleName() + " is visible more than " + TIMEOUT.toString());
        }
    }

    public void forClickable(WebElement element) {
        try {
            setWait().until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new TimeoutException(element.getAccessibleName() + " is not clickable more than " + TIMEOUT.toString());
        }
    }

    public void forInClickable(WebElement element) {
        try {
            setWait().until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
        } catch (TimeoutException e) {
            throw new TimeoutException(element.getAccessibleName() + " is not clickable more than " + TIMEOUT.toString());
        }
    }

    public void forAttributeNotEmpty(WebElement element, String attribute) {
        try {
            setWait().until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
        } catch (TimeoutException e) {
            throw new TimeoutException(element.getAccessibleName() + " attribute is empty after " + TIMEOUT.toString());
        }
    }
}
