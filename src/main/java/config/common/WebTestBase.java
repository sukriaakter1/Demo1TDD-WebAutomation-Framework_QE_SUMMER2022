package config.common;

import amazon.pages.HomePage;
import amazon.pages.RegistrationPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import config.reporting.ExtentManager;
import config.reporting.ExtentTestManager;
import config.utilities.ReadPropertiesFrom;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class WebTestBase {


    // Create Driver
    public static WebDriver driver ;
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
    String local = System.getenv("BROWSERSTACK_LOCAL");
    String LocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

    static Properties readProperty = ReadPropertiesFrom.loadProperties("src/main/resources/Configuration.properties");
    // public static final String BROWSERSTACK_USERNAME = "demooxxxxcvs_j7GhEU";
    //  public static final String BROWSERSTACK_ACCESS_KEY = "LWHWjKqHsnsdaKprskz2";

    public static final String SAUCE_LABS_USERNAME = "oauth-mfislammir-ce160";
    public static final String SAUCE_LABS_ACCESS_KEY = "6f53a710-ba2d-42ab-804c-4667c054134b";


    // Read Properties file


    // Cloud Infrastructure Implementation
    // BrowserStack
    // jovimab390@asoflex.com/Test12345
    // SauceLabs
    // jovimab390@asoflex.com/Jovimab390$


    // Reporting Utilities

    /**
     * **************************************************
     * ********** Start Of Reporting Utilities **********
     * **************************************************
     * **************************************************
     */
    //ExtentReport
    public static ExtentReports extent;
    public static ExtentTest logger;


    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) throws Exception {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));
        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }
        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            //logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            // logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
            //We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.
            String screenshotPath = captureScreenShotWithPath(driver, result.getName());
            //To add it in the extent report
            //   logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));

//            if (result.getStatus() == ITestResult.FAILURE) {
//                captureScreenShotWithPath(driver, result.getName());
//                logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
//            }

        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();

        // driver.close();
        //driver.quit();
        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
        extent.endTest(logger);
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


    public static String convertToString(String st) {
        String splitString = "";
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }


    @BeforeTest
    public void setUpAutomation() {
        getLog("********************************* Automation Start *********************************");
    }


    @Parameters({"userCloudEnv", "cloudEnvName", "os", "osVersion", "browserName", "browserVersion", "url"})
    @BeforeMethod
    public void setUpBrowser(@Optional("false") boolean useCloudEnv, @Optional("sauceLabs") String cloudEnvName, @Optional("OS X") String os, @Optional("Ventura") String osVersion, @Optional("safari") String browserName, @Optional("16.3") String browserVersion, @Optional("https://www.google.com/") String url) throws MalformedURLException {
        if (useCloudEnv) {
            getCloudDriver(cloudEnvName, os, osVersion, browserName, browserVersion);
        } else {
            getLocalDriver(browserName);
        }
        driverSetUp(url);
    }

    public void getLocalDriver(String browserName) {
        if (Objects.equals(browserName, "chrome")) {
            setUpChromeBrowserWithWebDriverManager();
        } else if (Objects.equals(browserName, "firefox")) {
            setUpFirefoxBrowserWithWebDriverManager();
        } else if (Objects.equals(browserName, "internetExplorer")) {
            setUpInternetExplorerBrowserWithWebDriverManager();
        } else if (Objects.equals(browserName, "edge")) {
            setUpEdgeBrowserWithWebDriverManager();
        } else if (Objects.equals(browserName, "safari")) {
            setUpSafariBrowserWithWebDriverManager();
        }
    }

    public void getCloudDriver(String envName, String os, String osVersion, String browserName, String browserVersion) throws MalformedURLException {
        // Add the following capabilities to run your test script
//        String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
//        String local = System.getenv("BROWSERSTACK_LOCAL");
//        String LocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        HashMap<String, Object> options = new HashMap<>();
        options.put("os", os);
        options.put("osVersion", osVersion);
        options.put("seleniumVersion", "4.8.1");
//        options.put("sessionName", "BStack Build Name: " + buildName);
//        options.put("local", local);
//        options.put("localIdentifier", LocalIdentifier);
        if (envName.equalsIgnoreCase("browserStack")) {
            capabilities.setCapability("bstack:options", options);
            //  driver = new RemoteWebDriver(new URL("https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY + "@hub.browserstack.com/wd/hub"), capabilities);
            driver = new RemoteWebDriver(new URL("https://" + readProperty.getProperty("BROWSERSTACK_USERNAME") + ":" + readProperty.getProperty("BROWSERSTACK_ACCESS_KEY") + "@hub.browserstack.com/wd/hub"), capabilities);
        } else if (envName.equalsIgnoreCase("sauceLabs")) {
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName(os);
            browserOptions.setBrowserVersion(browserVersion);
            options.put("build", "selenium-build-KLW8W");
            browserOptions.setCapability("name", "<your test name>");
            browserOptions.setCapability("sauce:options", options);
            capabilities.setCapability("osVersion", osVersion);
            URL url = new URL("https://" + SAUCE_LABS_USERNAME + ":" + SAUCE_LABS_ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub");
            driver = new RemoteWebDriver(url, browserOptions);
        }
    }

    //@AfterMethod
    @AfterMethod
    public void tearDown() {
        // Closing browser
        //  driver.close();
        assert driver != null;
        driver.quit();
        System.out.println("********************************* Automation End *********************************");

    }


    public void driverSetUp(String url) {
        // Navigate to the URL
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().deleteAllCookies();
    }

    public void setUpChromeBrowserWithWebDriverManager() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
    }

    public void setUpFirefoxBrowserWithWebDriverManager() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--remote-allow-origins=*");
        driver = new FirefoxDriver(firefoxOptions);
    }

    public void setUpInternetExplorerBrowserWithWebDriverManager() {
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
    }

    public void setUpSafariBrowserWithWebDriverManager() {
        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
    }

    public void setUpEdgeBrowserWithWebDriverManager() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }

    public static void getLog(String message) {
        Reporter.log(message, true);
    }


    public static String captureScreenShotWithPath(WebDriver driver, String screenShotName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String fileName = System.getProperty("user.dir") + "/ScreenShots/" + screenShotName + "_" + dateName + ".png";
        try {
            FileUtils.copyFile(file, new File(fileName));
            getLog("ScreenShot Captured");
        } catch (IOException e) {
            getLog("Exception while taking ScreenShot " + e.getMessage());
        }
        return fileName;
    }

    public void waitFor(int seconds) throws InterruptedException {
        Thread.sleep(1000 * seconds);
    }




}
