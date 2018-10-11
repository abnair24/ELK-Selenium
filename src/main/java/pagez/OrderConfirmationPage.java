package pagez;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by aswathyn on 09/01/17.
 */
public class OrderConfirmationPage extends BasePage<OrderConfirmationPage> {

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @FindBy(xpath = "//h1[@class='page-heading' and text()='Order confirmation']")
    private WebElement pageHeading ;

    @FindBy(xpath = "//div[@class='box order-confirmation']")
    private WebElement OrderConfrimSection ;

    @FindBy(xpath = "//p[@class='cart_navigation exclusive']/a")
    private WebElement navToOrderHistory ;

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(pageHeading);
    }

    @Override
    protected void instantiatePage(OrderConfirmationPage page) {
        PageFactory.initElements(driver,page);
    }

    public OrderHistoryPage backToORder(){
        navToOrderHistory.click();
        return new OrderHistoryPage(driver);
    }
}
