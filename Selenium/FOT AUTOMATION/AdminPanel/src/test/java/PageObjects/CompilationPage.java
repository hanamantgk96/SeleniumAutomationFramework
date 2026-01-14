package PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.PageObjectUtils;

public class CompilationPage {
	WebDriver driver;
	PageObjectUtils utils; 
	By loader = By.cssSelector(".fot-loader");
	By ORDPROCESSING = By.xpath("//span[text()=\"Order Processing\"]");
	By ORDCOMPILATION = By.xpath("//span[text()='Order Compilation']");
	By PAGETITLE = By.xpath("//div[text()='Order Compilation']");
	By COMPILATIONCOLUMNS = By.xpath("//tr[@class='table-light']/th");
	//tr[@class='table-light']/th/div
	
	//*[@id=\"grid_167072586_0\"]/div[2]/div/table/thead/tr[1]/th
	public CompilationPage(WebDriver driver) {
		this.driver=driver;
		this.utils = new PageObjectUtils(driver);
	}
	public void clickOrdProcessing() {
		utils.waitForLoaderToDisappear(loader);
		
		driver.findElement(ORDPROCESSING).click();
		
	}
	public void clickOrdCompilation() {
		utils.waitForLoaderToDisappear(loader);
		driver.findElement(ORDCOMPILATION).click();
	}
	public boolean isPageDisplayed() {
		return driver.findElement(PAGETITLE).isDisplayed();
	}
	public List<String> getColumns() {
	    List<WebElement> columnElements = driver.findElements(COMPILATIONCOLUMNS);
	   List<String> ColumnText = columnElements.stream().map(s ->s.getText()).collect(Collectors.toList());
//	    List<String> ColumnText = new ArrayList<>();
//	    for(WebElement e:columnElements) {
//	    	ColumnText.add(e.getText());
//	    }
	    return ColumnText;
	}
	
 
  }

