
import common.CommonHelper;
import common.DriverManager;
import org.testng.annotations.Test;
import pagez.HomePage;


public class FirstTest {

    @Test()
    public void aTest() throws Exception {

        HomePage pg = new HomePage(DriverManager.getDriver());

        pg.addnew().goToSummary()
                .goToAuthenticate().signIn("dummyname01@mailinator.com","root@1234").goToShipping().goToPayment()
                .selectPaymentMode("payByWire").confrimOrder().backToORder();
    }

    @Test()
    public void bTest() throws Exception {

        HomePage pg = new HomePage(DriverManager.getDriver());

        pg.navToContactUs().submitQuery(CommonHelper.getPropData("name")).verifyContactUsSuccessMessage();
    }

}