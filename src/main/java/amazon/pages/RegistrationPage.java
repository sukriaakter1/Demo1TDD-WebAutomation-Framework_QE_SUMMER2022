package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        //  WebTestBase.driver = driver;
    }


}
