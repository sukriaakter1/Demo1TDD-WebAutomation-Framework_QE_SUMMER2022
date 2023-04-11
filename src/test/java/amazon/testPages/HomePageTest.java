package amazon.testPages;

import amazon.pages.RegistrationPage;
import config.common.WebTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import amazon.pages.HomePage;

public class HomePageTest extends WebTestBase {

    public HomePage homePage;
    public RegistrationPage registrationPage;

   // @BeforeMethod
    public void getInItElements() {
        PageFactory.initElements(driver, HomePage.class);
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
    }


  //  @Test()
    public void verifySearchProductUsingValidProductName() throws InterruptedException {
        //  homePage = new HomePage(driver);
       getInItElements();
        homePage.searchProductUsingValidProductName();
        homePage.verifyProductUsingValidProductName("\"Hand Sanitizer\"");
    }

    //@Test
    public void verifySearchProductUsingValidProductNameNegativeTest1() throws InterruptedException {
        // HomePage homePage=new HomePage(driver);
     getInItElements();
        homePage.searchProductUsingValidProductName();
        homePage.verifyProductUsingValidProductName("\"Hand Sanitizer\"");
    }

   // @Test
    public void verifySearchProductUsingValidProductNameNegativeTest2() throws InterruptedException {
        //  HomePage homePage = new HomePage(driver);
     getInItElements();
        homePage.searchProductUsingValidProductName();
        homePage.verifyProductUsingValidProductName("\"Hand Sanitizer1\"");
    }

    //@Test
    public void verifySearchProductUsingValidProductNameNegativeTest3() throws InterruptedException {
        //  homePage = new HomePage(driver);
      getInItElements();
        homePage.searchProductUsingValidProductName();
        homePage.verifyProductUsingValidProductName("\"Hand Sanitizer1\"");
    }

   // @Test
    public void verifymouseHoverOnAccountAndLists() throws InterruptedException {
        //  homePage = new HomePage(driver);
        getInItElements();
        homePage.mouseHoverOnAccountAndLists();
        waitFor(10);

    }
   // @Test
    public void verifyBestSellerOption() throws InterruptedException {
        getInItElements();
        homePage.bestSellerOption();
    }
   // @Test
   public void verifyBooksOption() throws InterruptedException {
        getInItElements();
        homePage.booksOption();
   }
  // @Test
   public void verifyAmazonDealsOption() throws InterruptedException {
        getInItElements();
        homePage.dealsOption();
   }
   @Test
   public void verifyAmazonHealthOption() throws InterruptedException {
        getInItElements();
        homePage.amazonHealthOption();
   }

}
