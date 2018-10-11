package pagez;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class OrderHistoryPage extends BasePage<OrderHistoryPage> {

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @FindBy(id = "center_column")
    private WebElement OrderHistorySection ;

    @FindBy(className = "color-myaccount")
    private WebElement orderNumber ;

    @FindBy(xpath = "//td[@class='history_date bold']")
    private WebElement orderDate ;

    @FindBy(className = "price")
    private WebElement orderPrice ;

    @FindBy(className = "history_method")
    private WebElement paymentMethod ;

    @FindBy(xpath = "//td[@class='history_state']/span")
    private WebElement orderStatus ;


    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(OrderHistorySection);
    }

    @Override
    protected void instantiatePage(OrderHistoryPage page) {
        PageFactory.initElements(driver,page);
    }


}
