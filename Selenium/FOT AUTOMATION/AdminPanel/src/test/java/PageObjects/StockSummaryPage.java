package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.PageObjectUtils;

public class StockSummaryPage {
	WebDriver driver;
	PageObjectUtils utils;
	private By loader = By.cssSelector(".fot-loader");
	private By stockMenu = By.xpath("//span[text()='Stock']");
	private By stockSummaryMenu = By.xpath("//span[text()='Stock Summary']");
	private By productNameFilterIcon = By.xpath("//tr[@class='table-light']/th[4]/div[2]");
	private By productNameFilterInput = By.xpath("//input[@placeholder='Product Name/ Seller Name']");
	private By uomFilterIcon = By.xpath("//tr[@class='table-light']/th[6]/div[2]");
	private By uomFilterInput = By.xpath("//input[@placeholder='UOM']");
	private By productNameList = By.xpath("//div[@class='custom-grid-body']/table/tbody/tr/td[4]");
	private By expandRowIcon = By.xpath("//div[@class='custom-grid-body']/table/tbody/tr/td[1]");
	private By sellerNameList = By
			.xpath("//div[@class='custom-grid-body']/table/tbody/tr[not(td[1]//*[name()='svg' or self::i])]/td[4]");
	private By batchDetailsdropdown = By.xpath("//div[@class='px-2']/div/div/div/div[4]/select");
	private By referenceFilterIcon = By.xpath("//tr[@class='table-light']/th[4]/div[2]");
	private By referenceFilterInput = By.xpath("//input[@placeholder='Reference']");
	private By performedActionsColumn = By.xpath("//div[@class='custom-grid-body']/table/tbody/tr/td[5]");
	private By quantityColumn = By.xpath("//div[@class='custom-grid-body']/table/tbody/tr/td[6]");

	public StockSummaryPage(WebDriver driver) {
		this.driver = driver;
		this.utils = new PageObjectUtils(driver);

	}

	public void clickStockMenu() {
		utils.click(stockMenu);
	}

	public void clickStockSummaryMenu() {
		utils.scrollToElement(stockSummaryMenu);
		utils.click(stockSummaryMenu);
	}

	public void clickProductNameFilterIcon() {
		utils.waitForLoaderToDisappear(loader);
//		utils.waitForElementToAppear(productNameFilterIcon);
//		utils.waitForElementToAppear(productNameFilterIcon);
//		driver.findElement(productNameFilterIcon).click();
		utils.click(productNameFilterIcon);
	}

	public void clickUomFilterIcon() {
		utils.waitForElementToAppear(uomFilterIcon);
		driver.findElement(uomFilterIcon).click();

//	utils.click(uomFilterIcon);
	}

	public void enterProductNameInFilterInput(String productName) {
		driver.findElement(productNameFilterInput).clear();
		driver.findElement(productNameFilterInput).sendKeys(productName);
	}

	public void enterUomInFilterInput(String uom) {
		driver.findElement(uomFilterInput).clear();
		driver.findElement(uomFilterInput).sendKeys(uom);
	}

	public void selectproduct(String productName) {
		List<WebElement> rows = driver.findElements(productNameList);
		for (WebElement product : rows) {
			if (product.getText().equalsIgnoreCase(productName)) {
				product.findElement(By.xpath("../td[1]")).click();
//				driver.findElement(expandRowIcon).click();
				product.click();
				break;
			}

		}
	}

	public void selectSeller(String sellerName) {
		utils.selectByText(sellerNameList, sellerName);

	}

	public void selectBatchDetails(String batchDetails) {
		utils.selectByvisibleText(batchDetailsdropdown, batchDetails);
	}

	public void clickReferenceFilterIcon() {
		utils.waitForLoaderToDisappear(loader);
		driver.findElement(referenceFilterIcon).click();

//		utils.click(referenceFilterIcon);
	}

	public void enterReferenceInFilterInput(String orderId) {

		utils.safeSendKeys(referenceFilterInput, orderId);

	}

	public List<String> getAllPerformedActions() {
		utils.waitForElementToAppear(performedActionsColumn);

		List<WebElement> performedActionColumn = driver.findElements(performedActionsColumn);
		List<String> actionTexts = performedActionColumn.stream().map(WebElement::getText).toList();
		System.out.println("Performed Actions: " + actionTexts);
		return actionTexts;
	}

	public double getQuatity() {
		utils.waitForElementToAppear(quantityColumn);
		List<WebElement> rows = driver.findElements(quantityColumn);
//	List<WebElement> QuantityColumn = driver.findElements(By.xpath("./td[6]"));
		double totalUsedQuantity = 0.0;
		for (WebElement row : rows) {
			String qtyText = row.getText();
			double qty = Math.abs(Double.parseDouble(qtyText.trim()));
			totalUsedQuantity += qty;
			System.out.println("Quantity in row: " + totalUsedQuantity);
		}
		return totalUsedQuantity;

	}
}
