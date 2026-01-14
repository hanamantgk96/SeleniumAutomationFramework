package PageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	public LoginPage loginPage;
	public CompilationPage compilationPage;
	public ProcessingOrdersPage proOrdersPage;
	public StockSummaryPage stockSummaryPage;
	
    WebDriver driver;
	public PageObjectManager(WebDriver driver) {
	this.driver=driver;
	}
	public LoginPage getLoginPage() {
		if(loginPage == null)
		loginPage= new LoginPage(driver);
		return loginPage;
	}
	public CompilationPage getCompilationPage(){
		if(compilationPage == null)
			compilationPage = new CompilationPage(driver);
		return compilationPage;
	}
	public ProcessingOrdersPage getproOrdersPage() {
		if(proOrdersPage == null)
			proOrdersPage = new ProcessingOrdersPage(driver);
		return proOrdersPage;
	}
	public StockSummaryPage getStockSummaryPage() {
		if(stockSummaryPage == null)
			stockSummaryPage = new StockSummaryPage(driver);
		return stockSummaryPage;
	}
	

}
