package pagez;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AddToCartPage extends BasePage<AddToCartPage> {


    @FindBy(xpath = "//h1[@itemprop='name']")
    private WebElement itemName ;

    @FindBy(xpath = "//div[@itemprop='description']/p")
    private WebElement itemDescription ;

    @FindBy(id = "our_price_display")
    private WebElement itemPrice ;

    @FindBy(id = "quantity_wanted")
    private WebElement quantityTextField ;

    //locator to explain parent :: ancestor
    @FindBy(xpath = "//i[@class='icon-plus']//ancestor::a")
    private WebElement increaseQuantity ;

    @FindBy(xpath = "//i[@class='icon-minus']//ancestor::a")
    private WebElement decreaseQuantity ;

    @FindBy(id = "group_1")
    private WebElement selectSize ;

    @FindBy(xpath = "//p[@id='add_to_cart']/button")
    private WebElement addToCartButton ;

    public AddToCartPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(itemName);
    }

    @Override
    protected void instantiatePage(AddToCartPage page) {
        PageFactory.initElements(driver,page);
    }

    public AddToCartPage setQuantity(String quantity){
        quantityTextField.sendKeys(quantity);
        return new AddToCartPage(driver);
    }

    public AddToCartPage increaseQty(int count){

        while (count > 0)
        {
            increaseQuantity.click();
            count-- ;
        }
        return new AddToCartPage(driver);
    }

    public AddToCartPage decreaseQtytoOne(){
        String currentQuantity = quantityTextField.getText() ;

        while (!(currentQuantity == "1"))
        {
            decreaseQuantity.click();

        }
        return new AddToCartPage(driver);
    }

    public AddToCartPage setSize(String size){

        return new AddToCartPage(driver);
    }

    public AddToCartSuccessPopUp addtoCart(){
        addToCartButton.click();
        return new AddToCartSuccessPopUp(driver);
    }
}
