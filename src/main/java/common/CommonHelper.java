package common;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class CommonHelper {

    public static String getPropData(String item) throws IOException {
        Properties prop = new Properties();
        InputStream input = CommonHelper.class.getClassLoader().getResourceAsStream("config.properties");
        prop.load(input);
        return prop.getProperty(item);
    }


    public static String getUniqueEmail(String name){
        String value = name + new SimpleDateFormat("ddMMYYhhmm").format(new Date()) + "@mailinator.com";
        return value;
    }

    public static String timeStamp(String name) {
        String value = name + new SimpleDateFormat("ddMMYYhhmmss").format(new Date());
        return value;
    }


    @Test
    public void dd(){
        System.out.println(getUniqueEmail("chandra"));

    }
}
