//package utils;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Properties;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import io.cucumber.messages.types.Hook;
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class DriverManager {
//	private static WebDriver driver;       
//    private static Properties prop; 	   	
//
//    
//    public static WebDriver getDriver() throws IOException {
//        if (driver == null) {
//
//            // Load configuration from config.properties
//            prop = new Properties();
//            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
////           prop.load(Hook.class.getClassLoader().getResourceAsStream("config.properties"));
//            prop.load(fis);

//
//            String browser = prop.getProperty("browser");
//            String url = prop.getProperty("mktdemo");
//
//            // Initialize WebDriver based on browser choice
//            if (browser.equalsIgnoreCase("chrome")) {
//                WebDriverManager.chromedriver().setup();   // Auto-download & setup ChromeDriver
//                driver = new Chr omeDriver();
//            } else if (browser.equalsIgnoreCase("firefox")) {
//                WebDriverManager.firefoxdriver().setup();  // Auto-download & setup FirefoxDriver
//                driver = new FirefoxDriver();
//            } else {
//                throw new RuntimeException("Browser not supported: " + browser);
//            }
//
//            // Common WebDriver settings
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//            driver.manage().window().maximize();
//            driver.get(url);
//            
//        }
//        return driver;
//    }
//
//    // Quit the driver and clean up
//    public static void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }
//}	
//
//
