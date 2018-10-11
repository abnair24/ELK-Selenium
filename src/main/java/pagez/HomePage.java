package pagez;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pagez.BasePage;

import java.util.List;

public class HomePage extends BasePage<HomePage> {

    public HomePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @FindBy(id = "center_column")
    private WebElement catalog ;

    @FindBy(className = "blockbestsellers")
    private WebElement bestSellerCatalog ;

    @FindBy(className = "homefeatured")
    private WebElement popularCatalog ;

    @FindBy(xpath = "//ul[@id='homefeatured']//h5[@itemprop='name']")
    private List<WebElement> productNames ;

    @FindBy(xpath = "//ul[@id='homefeatured']//div[@class='right-block']//div[@itemprop='offers']")
    private List<WebElement> catalogPrices ;

    @FindBy(xpath=".//div[@id='contact-link']/a")
    private WebElement contactUs;

    @FindBys({@FindBy(id="homefeatured"),@FindBy(id="")})
    private WebElement products;


    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(catalog);
    }

    @Override
    protected void instantiatePage(HomePage page) {
        PageFactory.initElements(driver,page);
    }

    public HomePage openPopularCatalog(){
        popularCatalog.click();
        return new HomePage(driver);
    }

    public HomePage openBestSellerCatalog(){
        bestSellerCatalog.click();
        return new HomePage(driver);
    }

    public HomePage getProductNames(){
        for (WebElement name: productNames) {
            System.out.println("Below product are listed in catalog : " + name.getText());
        }
        return new HomePage(driver);
    }

    public HomePage getProductPrices(){
        for (WebElement price: catalogPrices) {
            System.out.println("Below prices are listed in catalog : " + price.getText());
        }
        return new HomePage(driver);
    }


    public AddToCartSuccessPopUp addnew() throws Exception{

        WebElement catItem = driver.findElement(By.xpath("(.//ul[@id='homefeatured']//div[@class='product-container'])[3]"));
        WebElement addToCart = driver.findElement(By.xpath("(.//ul[@id='homefeatured']//a[@title='Add to cart'])[3]"));

        //Using JS Executor
        scrollToElement(catItem);
//        mouseOverjQuery(catItem);

        Actions action = new Actions(driver);
        action.moveToElement(catItem).build().perform();
        addToCart.click();
        return new AddToCartSuccessPopUp(driver);
    }

    public AddToCartPage addProductToCart(String productName){

        for (WebElement name: productNames) {
            if (name.getText() == productName)
                name.click();
            break  ;
        }

        return new AddToCartPage(driver);
    }

    public ContactUsPage navToContactUs() throws Exception {
        contactUs.click();
        return new ContactUsPage(driver);
    }

}
