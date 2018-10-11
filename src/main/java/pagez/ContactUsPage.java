package pagez;

import common.CommonHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class ContactUsPage extends BasePage<ContactUsPage> {

    @FindBy(xpath =".//h1[@class='page-heading bottom-indent']")
    private WebElement contactUsHeader;

    @FindBy(id="id_contact")
    private WebElement subjectDropDown;

    @FindBy(xpath=".//input[@id='email']")
    private WebElement emailField;

    @FindBy(xpath=".//div[@class='submit']//span")
    private WebElement sendButton;

    @FindBy(xpath=".//div[@class='form-group']/textarea[@id='message']")
    private WebElement messageField;

    @FindBy(xpath =".//p[@class='alert alert-success']")
    private WebElement successAlert;


    private static String SUCCESS_MESSAGE="Your message has been successfully sent to our team.";

    public ContactUsPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(contactUsHeader);
    }

    @Override
    protected void instantiatePage(ContactUsPage page) {
        PageFactory.initElements(driver,page);
    }

    public ContactUsPage submitQuery(String email) throws Exception {
        selectDropDownText(subjectDropDown,"Customer service");
        emailField.clear();
        emailField.sendKeys(CommonHelper.getUniqueEmail(email));
        messageField.clear();
        messageField.sendKeys("Test for query");
        sendButton.click();
        return new ContactUsPage(driver);
    }

    public void verifyContactUsSuccessMessage() throws Exception {
        Assert.assertEquals(successAlert.getText(),SUCCESS_MESSAGE);
    }

}
