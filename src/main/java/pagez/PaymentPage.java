package pagez;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PaymentPage extends BasePage<PaymentPage> {

    public PaymentPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @FindBy(className = "paiement_block")
    private WebElement paymentSection ;

    @FindBy(className = "bankwire")
    private WebElement payByWire ;

    @FindBy(className = "cheque")
    private WebElement payByCheck ;



    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(paymentSection);
    }
    @Override
    protected void instantiatePage(PaymentPage page) {
        PageFactory.initElements(driver,page);
    }

    public PaymentConfirmPage selectPaymentMode(String paymentMethod){
        if (paymentMethod == "payByWire")
            payByWire.click();
        else if(paymentMethod == "payByCheck")
            payByCheck.click();
        return new PaymentConfirmPage(driver);
    }
}
