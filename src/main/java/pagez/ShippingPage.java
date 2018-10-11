package pagez;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ShippingPage extends BasePage<ShippingPage> {

    public ShippingPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @FindBy(id = "HOOK_BEFORECARRIER")
    private WebElement shippingSection ;

    @FindBy(id = "cgv")
    private WebElement tncCheckbox ;

    @FindBy(name = "processCarrier")
    private WebElement checkoutStep4 ;

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(shippingSection);
    }

    @Override
    protected void instantiatePage(ShippingPage page) {
        PageFactory.initElements(driver,page);
    }

    public PaymentPage goToPayment(){
        tncCheckbox.click();
        checkoutStep4.click();
        return new PaymentPage(driver) ;
    }
}
