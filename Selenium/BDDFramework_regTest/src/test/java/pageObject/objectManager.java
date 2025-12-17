package pageObject;

import org.openqa.selenium.WebDriver;

public class objectManager {

	public WebDriver driver;

	public LoginPage loginPage;
	public Lpopage Lpopage;
	public Placeorder placeorder;
	public OrderCompilation orderCompilation;
	public DeliveryModule deliveryModule;

	public objectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage getLoginPage1() {
		loginPage = new LoginPage(driver);
		return loginPage;
	}

	public Lpopage getLpopage() {
		Lpopage = new Lpopage(driver);
		return Lpopage;
	}

	public Placeorder getplaceorder() {
		placeorder = new Placeorder(driver);
		return placeorder;

	}
	
	public OrderCompilation getOrderCompilation() {
		orderCompilation = new OrderCompilation(driver);
				return orderCompilation;
		
	}
	
	public DeliveryModule getdeliveryModule()  {
		deliveryModule = new DeliveryModule(driver);
				return deliveryModule;
		
	}
	
	
}