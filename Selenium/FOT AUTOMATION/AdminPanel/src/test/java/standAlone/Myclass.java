package standAlone;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Myclass {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://mktadmin.freshontable.com");
		driver.manage().window().maximize();
		driver.findElement(By.name("userName")).sendKeys("UAEADMIN");
		driver.findElement(By.name("password")).sendKeys("Admin@4321");
		driver.findElement(By.cssSelector(".button")).click();
		Thread.sleep(4000);
		driver.findElement(By.className("menu-cont")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[text()=\"Order Processing\"]")).click();
		Thread.sleep(8000);
		driver.findElement(By.className("menu-cont")).click();
		Thread.sleep(4000);

		driver.findElement(By.cssSelector(".proOrder-apply-btn")).click();
		Thread.sleep(4000);
		WebElement drp = driver.findElement(By.name("transformationType"));
//		drp.click();
		Select dropdown = new Select(drp);
		dropdown.selectByVisibleText("SKU Change");
		dropdown.getFirstSelectedOption().getText().equals("SKU Change");
		Assert.assertTrue(dropdown.getFirstSelectedOption().getText().equals("SKU Change"),
				"Selected correct transformation type");
		Thread.sleep(4000);

		WebElement hub = driver.findElement(
				By.xpath("//div[@class='proOrder-details-cont primarypdt-right-box']//div[3]//div[1]//div[1]//select"));
		Select sel = new Select(hub);
		sel.selectByIndex(2);

		Assert.assertFalse(
				sel.getFirstSelectedOption().getText().equals("Fresh On table , Al Bahyah  Al bayah 1 , Abu Dhabi"),
				"Both are same");
		driver.findElement(By.xpath("//div[@class='proOrder-details-cont primarypdt-right-box']//div[4]")).click();
		Thread.sleep(4000);
		driver.findElement(By.className("custom-filter-icon")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@class='grid-text-input']/input")).sendKeys("fis");
		Actions a = new Actions(driver);
		a.keyDown(Keys.ENTER).build().perform();
		Thread.sleep(4000);
		List<WebElement> options = driver.findElements(By.xpath("//div[@class='custom-grid-body']/table/tbody/tr"));
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase("fish")) {
				option.click();
				break;
			}
		}

//		driver.findElement(By.cssSelector("div[class='grid-text-input']")).click();
//		driver.findElement(By.cssSelector("div[class='grid-text-input']")).sendKeys("fis");

		Thread.sleep(8000);

	}

}
