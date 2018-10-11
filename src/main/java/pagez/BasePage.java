package pagez;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;



public abstract class BasePage  <P extends BasePage> {

    protected WebDriver driver;
    protected WebDriverWait waitTime;
    protected static final long ELEMENT_WAIT = 10;
    protected static final long IMPLICIT_WAIT = 20;
    protected static final int PAGE_LOAD_TIMEOUT = 30;
    protected static final int POLLING_RATE = 2;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
    }

    /**
     * Method to get the condition for checking the page load
     *
     * @return ExpectedCondition for the element to be verified.
     */
    protected abstract ExpectedCondition<?> getPageLoadCondition();

    /**
     * Method for child page instantiation
     */
    protected abstract void instantiatePage(P page);

    protected void implicitWaitMethod() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    protected void waitforAjax(){
        waitTime = new WebDriverWait(driver,130);
        waitTime.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean)js.executeScript("return jQuery.active == 0");
        }});

    }

    protected void waitForPageToLoad(ExpectedCondition<?> expectedCondition) {

        Wait wait = new FluentWait(driver)
                .withTimeout(java.time.Duration.ofSeconds(PAGE_LOAD_TIMEOUT))
                .pollingEvery(java.time.Duration.ofSeconds(POLLING_RATE));
        wait.until(getPageLoadCondition());
    }

    protected void waitForElement(ExpectedCondition expectedCondition) {
        waitTime = new WebDriverWait(driver,30);

        waitTime.until(expectedCondition);

    }

    public void selectDropDownText(WebElement element, String textValue) throws Exception {
        try {
            Select selectValue = new Select(element);
            selectValue.selectByVisibleText(textValue);
        } catch (NoSuchElementException ex) {
            throw new Exception(textValue + "not found", ex);
        }
    }

    public void enterText(WebElement webElement, String message) throws Exception {
        if (!(webElement == null)) {
            if (webElement.isDisplayed()) {
                webElement.clear();
                webElement.sendKeys(message);
            } else {
                throw new Exception("Text element not found");
            }
        }
    }

    public void clickIcon(WebElement element, String message) throws Exception {
        if (isElementPresent(element)) {
            element.click();
        } else {
            throw new Exception(message + " not found");
        }
    }

    protected void waitForElement(ExpectedCondition expectedCondition, WebElement element) throws Exception {
        try {
            waitTime = new WebDriverWait(driver, ELEMENT_WAIT);
            waitTime.until(expectedCondition);
        } catch (Exception e) {

            throw new Exception("Element is not visible in UI");
        }
    }

    public void clickButton(WebElement webElement) throws Exception {
        if (isElementPresent(webElement)) {
            if (webElement.isEnabled()) {
                webElement.click();
            } else {
                throw new Exception(webElement.toString() + " not clickable");
            }
        } else {
            throw new Exception(webElement.toString() + " not visible");
        }
    }

    public boolean isElementPresent(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void scrollToPageBottom() throws Exception {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public void scrollToElement(WebElement element) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void mouseOverjQuery(WebElement element) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].hover()",element);
    }

}
