package packageone;

import common.CommonHelper;
import common.DriverManager;
import org.testng.annotations.Test;
import pagez.HomePage;

public class ThirdTest {

    @Test()
    public void contactUsTest() throws Exception {

        HomePage pg = new HomePage(DriverManager.getDriver());

        pg.navToContactUs().submitQuery(CommonHelper.getPropData("name")).verifyContactUsSuccessMessage();
    }
}
