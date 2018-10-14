
import common.CommonHelper;
import common.DriverManager;
import org.testng.annotations.Test;
import pagez.HomePage;

public class SignUpTest  {

    @Test()
    public void pTest() throws Exception {
        HomePage pg = new HomePage(DriverManager.getDriver());

        pg.addnew().goToSummary()
                .goToAuthenticate().signUp(CommonHelper.getUniqueEmail("autotest")) ;

    }
}
