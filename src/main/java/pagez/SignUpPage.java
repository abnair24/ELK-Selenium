package pagez;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SignUpPage extends BasePage<SignUpPage> {

    public SignUpPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @FindBy(xpath = "//div[@class='account_creation']/h3[text()='Your personal information']")
    private WebElement createAccountHeader ;

    @FindBy(id = "id_gender1")
    private WebElement genderMale ;

    @FindBy(id = "id_gender2")
    private WebElement genderFemale ;

    @FindBy(id = "customer_firstname")
    private WebElement firstName ;

    @FindBy(id = "customer_lastname")
    private WebElement lastName ;

    @FindBy(id = "email")
    private WebElement email ;

    @FindBy(id = "passwd")
    private WebElement password ;

    @FindBy(id = "days")
    private WebElement daysSelect ;

    @FindBy(id = "months")
    private WebElement monthSelect ;

    @FindBy(id = "years")
    private WebElement yearSelect ;

    @FindBy(id = "firstname")
    private WebElement billingFirstname ;

    @FindBy(id = "lastname")
    private WebElement billingLastName ;

    @FindBy(id = "company")
    private WebElement companyName ;

    @FindBy(id = "address1")
    private WebElement address1 ;

    @FindBy(id = "address2")
    private WebElement address2 ;

    @FindBy(id = "city")
    private WebElement city ;

    @FindBy(id = "id_state")
    private WebElement stateSelect ;

    @FindBy(id = "postcode")
    private WebElement zip ;

    @FindBy(id = "id_country")
    private WebElement country ;

    @FindBy(id = "other")
    private WebElement additionalInfo ;

    @FindBy(id = "phone")
    private WebElement phone ;

    @FindBy(id = "phone_mobile")
    private WebElement mobile ;

    @FindBy(id = "alias")
    private WebElement addressAlias ;

    @FindBy(id = "submitAccount")
    private WebElement register ;

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(createAccountHeader);
    }

    public ShippingPage register(){


        return new ShippingPage(driver);
    }

    @Override
    protected void instantiatePage(SignUpPage page) {
        PageFactory.initElements(driver,page);
    }
}
