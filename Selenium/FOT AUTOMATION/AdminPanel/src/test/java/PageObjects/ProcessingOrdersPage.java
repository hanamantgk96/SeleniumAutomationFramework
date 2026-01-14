package PageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.PageObjectUtils;

public class ProcessingOrdersPage {

	WebDriver driver;
	PageObjectUtils utils;

	/* ================== Locators ================== */
	private By mainMenu = By.className("menu-cont");
	private By orderProcessingMenu = By.xpath("//span[text()='Order Processing']");
	private By processingOrdersMenu = By.xpath("//span[text()='Processing Orders']");
	private By createNewButton = By.cssSelector(".proOrder-apply-btn");
	private By loader = By.cssSelector(".fot-loader");
	private By processingOrderId = By.xpath("//div[@class='custom-grid-body']/table/tbody/tr[1]/td[1]/span[1]");

	private By transformationTypeDropdown = By.name("transformationType");
//    private By hubDropdown = By.xpath("//div[@class='proOrder-details-cont primarypdt-right-box']//div[3]//div[1]//div[1]//select[1]");
	private By hubDropdown = By.xpath("//div[@class='proOrder-inputs'][2]/div/div/select");

	private By subCategoryField = By.xpath("//div[@class='proOrder-details-cont primarypdt-right-box']//div[4]");
	private By subCategoryFilterIcon = By.cssSelector(".custom-filter-icon");
	private By searchInputField = By.xpath("//div[@class='grid-text-input']/input");
	private By gridListRows = By.xpath("//div[@class='custom-grid-body']/table/tbody/tr");

	private By productField = By.xpath("//div[@class='proOrder-details-cont primarypdt-right-box']/div[5]");
	private By secondaryProductInput = By.xpath("//input[@name='parentProductName']");

	private By sellerDropdown = By.cssSelector("select[name='procSellerId']");
	private By uomDropdown = By.xpath("//select[@name='procUomId']");
	private By requestedQuantityInput = By.cssSelector("input[name='requestedQty']");

	private By infoPopupMessage = By.cssSelector(".message");
	private By okButton = By.xpath("//button[text()='OK']");
	private By sendForProcessButton = By.xpath("//button[text()='Send For Process']");

	private By confirmButton = By.xpath("//button[text()='Confirm']");
	private By filterIconForProcessingOrderId = By.xpath("//th[@title='Processing Order ID ']/div[2]");
	private By processingOrderIdFilterInput = By.xpath("//input[@placeholder='Processing Order ID']");
	private By moreButton = By.cssSelector(".actions-dropdown");
	private By editOption = By.xpath("//a[text()='Edit']");
	private By applyButton = By.xpath("//button[text()='Apply']");
	private By updateButton = By.xpath("//button[text()='Update']");// div[@title='Draft']/div
	// div[@class='custom-grid-body']/table/tbody/tr/td[11]/div/div
	private By orderStatus = By.xpath("//div[@class='custom-grid-body']/table/tbody/tr/td[11]/div/div");
//    private By orderCreatedStatus = By.xpath("//div[@title='Order Created']/div");
	private By primaryUomDropdown = By
			.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[7]/div[1]/div[1]/select[1]");
	private By processedQuantityInput = By.cssSelector("input[name='parentProcessQty']");
	private By processOrderOption = By.xpath("//a[text()='Process Order']");
	private By transformButton = By.xpath("//button[text()='Transform']");
	private By transformedProductField1 = By.xpath("//div[@class='proOrder-container']/div[2]/div[3]//div[@class='proOrder-inputs']");
//	private By transformedProductField =By.xpath("//div[@class='proOrder-inputs transform-proOrder-text']//input[@class='fot-formControl empty empty']");
//	private By transformedProductField = By.xpath("//label[text()='Transformed Product']");

	private By sourceSellerDropdown = By.xpath("//div[@class='proOrder-details-cont primarypdt-box']/div[6]/div/div//select");
	private By minimumYieldInput = By.name("processMinYieldPer");
	private By maximumYieldInput = By.name("processMaxYieldPer");
	private By addWastageButton = By.xpath("//button[text()='Add Wastage +']");
	private By wastageProductDropdown = By.xpath("//div[@class='proOrder-container']/div[2]/div[4]/div[2]/div/div//div/select");
	private By wastMiniYield = By.name("wasteMinYield");
	private By wastMaxYield = By.name("wasteMaxYield");
	private By selectedWastage = By.name("wastePdtId");
	private By actualYieldInput = By.name("processActualYield");
	private By transformedProductField2 = By.xpath("//div[@class='proOrder-inputs transform-proOrder-text']//input[@class='fot-formControl empty empty']");
	private By wasteActualYieldInUnits = By.name("wasteActualYieldInUnits");	/* ================== Constructor ================== */

	public ProcessingOrdersPage(WebDriver driver) {
		this.driver = driver;
		this.utils = new PageObjectUtils(driver);
	}

	/* ================== Navigation ================== */
	public void clickMainMenu() {
		utils.waitForLoaderToDisappear(loader);
		utils.click(mainMenu);
	}

	public void clickOrderProcessingMenu() {
		utils.waitForLoaderToDisappear(loader);
//        utils.click(orderProcessingMenu);
//        utils.waitForLoaderToDisappear(loader);
		driver.findElement(orderProcessingMenu).click();
	}

	public void clickProcessingOrdersMenu() {

//        utils.click(processingOrdersMenu);
//		utils.waitForLoaderToDisappear(loader);
//		utils.waitForElementToBeClickable(processingOrdersMenu);
		utils.click(processingOrdersMenu);
//		driver.findElement(processingOrdersMenu).click();
	}

	public void clickCreateNewButton() {
//        utils.click(createNewButton);
		driver.findElement(createNewButton).click();
	}

	/* ================== Order Creation ================== */

	public void selectTransformationType(String type) {
		utils.selectByvisibleText(transformationTypeDropdown, type);
	}

	public void selectHub(String hub) {
//		utils.waitForElementToBeClickable(hubDropdown);
//		utils.waitForLoaderToDisappear(loader);
//		utils.waitForElementToAppear(hubDropdown);
//    	driver.findElement(hubDropdown).click();

//        utils.selectByValue(hubDropdown, "50127");
		utils.selectByvisibleText(hubDropdown, hub);

	}

	public String getSelectedHub() {
		String hub = driver.findElement(hubDropdown).getText();
		return hub;

	}
	public void selectSubCategory(String subCategory) {
		driver.findElement(subCategoryField).click();
		utils.click(subCategoryFilterIcon);
		driver.findElement(searchInputField).sendKeys(subCategory);
		driver.findElement(searchInputField).sendKeys(Keys.ENTER);
		utils.selectByText(gridListRows, subCategory);
//        utils.waitForLoaderToDisappear(loader);
		
		
		
	}

//	public void openSubCategoryField() {
////        utils.click(subCategoryField);
//		driver.findElement(subCategoryField).click();
//		
//	}
//
	public void clickFilter() {
////        utils.click(subCategoryFilterIcon);
		driver.findElement(subCategoryFilterIcon).click();
	}
//
//	public void enterSubCategory(String subCategory) {
//		driver.findElement(searchInputField).sendKeys(subCategory);
////        utils.clearAndType(searchInputField, subCategory);
////        new Actions(driver).sendKeys(Keys.ENTER).perform();
//	}
//
//	public void selectSubCategoryFromGrid(String subCategory) {
//		utils.selectByText(gridListRows, subCategory);
//        utils.waitForLoaderToDisappear(loader);
	

//	public void openProductField() {
//		driver.findElement(productField).click();
////		utils.click(productField);
//	}
//
//	public void enterProductName(String product) {
//
////        utils.clearAndType(searchInputField, product);
//		driver.findElement(searchInputField).sendKeys(product);
//	}

	public void selectProductFromGrid(String product) {
		driver.findElement(productField).click();
		driver.findElement(subCategoryFilterIcon).click();
		driver.findElement(searchInputField).sendKeys(product);
		driver.findElement(searchInputField).sendKeys(Keys.ENTER);
		utils.selectByText(gridListRows, product);
//		utils.waitForLoaderToDisappear(loader);
	}

	public String getSecondaryProductName() {
		
		
		return driver.findElement(secondaryProductInput).getAttribute("value");
	}

	public void selectSeller(String seller) {
		utils.selectByvisibleText(sellerDropdown, seller);
	}
	public void selectChangedSeller(String seller) {
		utils.selectByvisibleText(sellerDropdown, seller);
	}

	public void selectUom(String uom) {
		utils.selectByvisibleText(uomDropdown, uom);
	}
	public void selectChangedUom(String uom) {
		utils.selectByvisibleText(uomDropdown, uom);
	}

	public void clearAndEnterRequestedQuantity(String quantity) {
		WebElement qtyField = driver.findElement(requestedQuantityInput);
		qtyField.click();
		utils.clearInput(qtyField);
		utils.ValidateAndCofirmPopUp(infoPopupMessage, okButton, "Request Quantity cannot be empty!");
		qtyField.sendKeys(quantity);
//        utils.ValidateAndCofirmPopUp(infoPopupMessage, okButton,
//                "Request Quantity cannot be empty!");
//        utils.clearAndType(requestedQuantityInput, quantity);
	}

	public void clickSendForProcess() {
		utils.click(sendForProcessButton);
	}

	public void clickConfirmButton() {
		utils.click(confirmButton);
	}

	public String getSuccessPopupText() {
		utils.waitForElementToAppear(infoPopupMessage);
		return driver.findElement(infoPopupMessage).getText();
	}

	public void clickOkButton() {
		utils.click(okButton);
	}

	public String getProcessingOrderId() {
		return driver.findElement(processingOrderId).getText();
	}

	public void clickFilterForProcessingOrderId() {
		driver.navigate().refresh();
		utils.waitForLoaderToDisappear(loader);
		driver.findElement(filterIconForProcessingOrderId).click();
	}

	public void enterProcessingOrderIdInFilter(String orderID) {
		driver.findElement(processingOrderIdFilterInput).sendKeys(orderID);
//        utils.clearAndType(processingOrderIdFilterInput, orderID);
	}

	public void clickMoreButton() {
//    	driver.findElement(moreButton).click();
		utils.click(moreButton);
	}

	public void clickEditOption() {
//    	driver.findElement(editOption).click();
//		utils.waitForElementToBeClickable(editOption);
		utils.click(editOption);
	}

	public void clickApplyButton() {
		driver.findElement(applyButton).click();
//        utils.click(applyButton);
	}

	public void clickUpdateButton() {
		driver.findElement(updateButton).click();
//        utils.click(updateButton);
	}

	public String getOrderStatus() {
		return driver.findElement(orderStatus).getText();
	}

//    public String getOrderCreatedStatus() {
//        return driver.findElement(orderCreatedStatus).getText();
//    }

	public void selectPrimeryUom(String uom) {
		utils.waitForLoaderToDisappear(loader);
//    	utils.waitForElementToAppear(primaryUomDropdown);
//    	driver.findElement(primaryUomDropdown).click();
//    	utils.selectByValue(primaryUomDropdown, "199");
		utils.selectByvisibleText(primaryUomDropdown, uom);
	}

	public void enterProcessedQuantity(String quantity) {
//    	utils.click(processedQuantityInput);
//		utils.waitForLoaderToDisappear(loader);
		driver.findElement(processedQuantityInput).click();
		driver.findElement(processedQuantityInput).sendKeys(quantity);
//        utils.clearAndType(processedQuantityInput, quantity);
	}

	public void clickProcessOrderOption() {
		driver.findElement(processOrderOption).click();
//        utils.click(processOrderOption);
	}

	public void clickTransformButton() {
		driver.findElement(transformButton).click();
//        utils.click(transformButton);
	}

	public void acceptBrowserAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void pressEnterKey() {
		new Actions(driver).keyDown(Keys.ENTER).perform();
	}
//	public void openTransformedProduct() {
//		driver.findElement(transformedProductField).click();
//	}
//	public void enterTransformedProductName(String product) {
//		driver.findElement(searchInputField).sendKeys(product);
//	}
	public void selectTransformedProductFromGrid1(String product) {
		driver.findElement(transformedProductField1).click();
		driver.findElement(subCategoryFilterIcon).click();
		driver.findElement(searchInputField).sendKeys(product);
		driver.findElement(searchInputField).sendKeys(Keys.ENTER);
		utils.selectByText(gridListRows, product);
	}
	public void selectSourceSeller(String seller) {
//		utils.waitForLoaderToDisappear(loader);
		utils.waitForElementToBeClickable(sourceSellerDropdown);
		utils.selectByvisibleText(sourceSellerDropdown, seller);
	}
	public void enterMinimumYield(String yield) {
		WebElement yieldField = driver.findElement(minimumYieldInput);
		utils.clearInput(yieldField);
		yieldField.sendKeys(yield);
	}
	public void enterMaxixmumYield(String yield) {
		WebElement yieldField = driver.findElement(maximumYieldInput);
		utils.clearInput(yieldField);
		yieldField.sendKeys(yield);
	}
	public void clickAddWastageButton() {
		driver.findElement(addWastageButton).click();
	}
	public void selectWastageProduct() {
		utils.selectByValue(wastageProductDropdown, "14300");
		System.out.println("selected waste" + driver.findElement(wastageProductDropdown).getText());
//		driver.findElement(wastageProductDropdown).click();
//		utils.selectByvisibleText(wastageProductDropdown, wastageProduct);
	}
	public void enterWastageMinYield(String yield) {
		driver.findElement(wastMiniYield).sendKeys(yield);
	}
	public void enterWastageMaxYield(String yield) {
		driver.findElement(wastMaxYield).sendKeys(yield);
	}
	public String getSelectedWaste() {
		 return driver.findElement(selectedWastage).getText();
	}
	public void enterActualYield(String yield) {
		driver.findElement(actualYieldInput).sendKeys(yield);
	}
	public void selectTransformedProductFromGrid2(String product) {
		driver.findElement(transformedProductField2).click();
		driver.findElement(subCategoryFilterIcon).click();
		driver.findElement(searchInputField).sendKeys(product);
		driver.findElement(searchInputField).sendKeys(Keys.ENTER);
		utils.selectByText(gridListRows, product);

	}
	public String getWasteActualYieldInUnits() {
		return driver.findElement(wasteActualYieldInUnits).getAttribute("value");
	}
	public void clearAndEnterChangedRequestedQuantity(String quantity) {
		WebElement qtyField = driver.findElement(requestedQuantityInput);
		qtyField.click();
		utils.clearInput(qtyField);
		utils.ValidateAndCofirmPopUp(infoPopupMessage, okButton, "Request Quantity cannot be empty!");
		qtyField.sendKeys(quantity);
//        utils.ValidateAndCofirmPopUp(infoPopupMessage, okButton,
//                "Request Quantity cannot be empty!");
//        utils.clearAndType(requestedQuantityInput, quantity);
	}
}
