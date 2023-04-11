package amazon.pages;

import config.common.GlobalReUsableMethods;
import config.common.WebTestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static amazon.pageElements.HomePageElements.*;
import static config.common.GlobalReUsableMethods.*;


public class HomePage extends WebTestBase {
    // Action Methods class for all type of business logic/ function/ actions purpose: Page Object class

    // Constructor of the Page Object class and pass driver from WebTestBase class
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        //  WebTestBase.driver = driver;
    }


    //By searchBox = By.xpath(searchBoxWebElement);
    public void searchProductUsingValidProductName1() {
        //  driver.findElement(By.xpath(searchBoxWebElement)).sendKeys("Hand Sanitizer");
        //   driver.findElement(By.xpath(searchButtonWebElement)).click();
        //  driver.findElement(searchBox).sendKeys("Hand Sanitizer");

    }


    // Find by Annotation with How: Modern Approach
    @FindBy(how = How.XPATH, using = searchBoxWebElement)
    public WebElement searchBox1;
    @FindBy(how = How.XPATH, using = searchButtonWebElement)
    public WebElement searchButton1;

//    @FindBy(how = How.ID, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.NAME, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.CSS, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.TAG_NAME, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.LINK_TEXT, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.PARTIAL_LINK_TEXT, using = searchButtonWebElement) public WebElement searchButton;
//    @FindBy(how = How.CLASS_NAME, using = searchButtonWebElement) public WebElement searchButton;


    // Find by Annotation with WithHow : Modern Approach
    @FindBy(xpath = searchBoxWebElement)
    public WebElement searchBox;
    @FindBy(xpath = searchButtonWebElement)
    public WebElement searchButton;
    @FindBy(xpath = verifySearchedProductWebElement)
    public WebElement verifySearchedProduct;
    @FindBy(xpath = accountAndListsWebElement)
    public WebElement accountAndLists;
    @FindBy(xpath = watchlistWebElement)
    public WebElement watchlist;

    // mine
    @FindBy(xpath = bestSeller)public WebElement bestSellerButton;
   @FindBy(xpath = amazonDevice)public WebElement amazonDevices;
   @FindBy(xpath = books)public WebElement amazonBooks;
   @FindBy(xpath = newRealese)public WebElement booksNewRealese;
   @FindBy(xpath = amazonDeal)public WebElement amazonDealOption;
   @FindBy(xpath =dealsCoupnos )WebElement amazonDealsCoupons;
   @FindBy(xpath =amazonHealth )WebElement amazonHealthOption;
    // Action Method:
    public void searchProductUsingValidProductName2() {
        searchBox1.sendKeys("Hand Sanitizer");
        searchButton1.click();

    }

    public void searchProductUsingValidProductName() throws InterruptedException {
        searchBox.sendKeys("T-Shirt");
        Thread.sleep(5000);
        enterValueOnWebElement(searchBox, "Hand Sanitizer");
        // searchButton.click();
        clickOnWebElement(searchButton);
      //  GlobalReUsableMethods.clickOnWebElement(searchBox);
    }

    public void verifyProductUsingValidProductName(String expectedProduct) {
        String actualProduct = verifySearchedProduct.getText();
        Assert.assertEquals(actualProduct, expectedProduct, "Product does not match as expected");
    }

    public void mouseHoverOnAccountAndLists() throws InterruptedException {
        mouseHoverByWebElementWithPerform(accountAndLists);
        waitFor(3);
        clickOnWebElement(watchlist);
    }

    public void bestSellerOption() throws InterruptedException {
        clickOnWebElement(bestSellerButton);
        waitFor(5);
        clickOnWebElement(amazonDevices);
    }
     public void booksOption() throws InterruptedException {
        clickOnWebElement(amazonBooks);
        waitFor(5);
        clickOnWebElement(booksNewRealese);
     }
     public void dealsOption() throws InterruptedException {
       clickOnWebElement(amazonDealOption);
       waitFor(5);
       clickOnWebElement(amazonDealsCoupons);
     }
    public void amazonHealthOption() throws InterruptedException {
        clickOnWebElement(amazonHealthOption);
        waitFor(5);
    }


}
