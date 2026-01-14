package standAlone;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Enterprise {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

		driver.get("https://mktenterprise.freshontable.com");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//div[@class='common-layout']/div[1]/div[2]")).click();
		Thread.sleep(4000);
		WebElement CountryDrp = driver.findElement(By.xpath("//div[@class='mt-5 mx-1']/div[1]/div[1]/div[1]/select"));
		Select country = new Select(CountryDrp);
		country.selectByIndex(1);
		driver.findElement(By.name("phone")).sendKeys("701919953");
		driver.findElement(By.name("password")).sendKeys("Admin@1234");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		String[] expectedProducts = { "Edible Flower Cakes", "beetroot","Green Apple" };
//		Thread.sleep(4000);
		addItem(driver, expectedProducts);
		System.out.println("Need to click on cart");
		driver.findElement(By.xpath("//div[text()='Your Cart']")).click();
		System.out.println("Clicks on cart");
		driver.findElement(By.name("lpoNumber")).sendKeys("Automation");
		driver.findElement(By.cssSelector(".button.button-primary")).click();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void addItem(WebDriver driver, String[] expectedProducts) throws InterruptedException {
		int j = 0;
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='row']/div/div[1]/div[2]/div[1]"));
		for (int i = 0; i < products.size(); i++) {
			String name = products.get(i).getText();
			System.out.println(name);

//		  convert array into array list for search
//		  Check whether the extracted name is present in array list or not
			List itemsNeeded = Arrays.asList(expectedProducts);
			if (itemsNeeded.contains(name)) {
				j++;
//		  if(name.contains("Papaya")) {    for single product
//				Thread.sleep(4000);

				driver.findElements(By.xpath("//div[@class='row']/div/div[1]/div[3]/div[1]")).get(i).click();

				if (j == itemsNeeded.size()) {
					break;
				}
			}
		}
		// driver.quit();
	}

}
