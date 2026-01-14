package context;

import java.util.Map;

public class ScenarioContext {
	private OrderData orderData;
	private String processingOrderId;
	private String wastageYield;
	private String changedUom;
	private String changedSeller;
	private String changedRequestedQty;
	
	

	public ScenarioContext() {
		orderData = new OrderData();
	}
	public void clear() {
		processingOrderId = null;
		changedUom = null;
		wastageYield = null;
		
	}

	public OrderData getOrderData() {
		return orderData;
	}

	public void setProcessingOrderId(String processingOrderId) {
		this.processingOrderId = processingOrderId;

	}

	public String getProcessingOrderId() {
		return processingOrderId;
	}

	public void setWastageYield(String wastageYield) {
		this.wastageYield = wastageYield;
	}

	public String getWastageYield() {
		return wastageYield;
	}
	public void setChangedUom(String changedUom) {
		this.changedUom = changedUom;
		
	}
	public String getChangedUom() {
		return changedUom;
	}
	public void setChangedSeller(String changedSeller) {
		this.changedSeller = changedSeller;
	}
	public String getChangedSeller() {
		return changedSeller;
	}
	public void setChangedRequestedQty(String changedRequestedQty) {
		this.changedRequestedQty = changedRequestedQty;
	}
	public String getChangedRequestedQty() {
		return changedRequestedQty;
	}
//	String productForStockEnytry=
//			orderData.getTransFormedProduct()!=null?orderData.getTransFormedProduct():orderData.getProduct();
//		

	public void populateOrderData(io.cucumber.datatable.DataTable dataTable) {

		Map<String, String> data = dataTable.asMap(String.class, String.class);

		if (data.containsKey("transformationType"))
			orderData.setTransformationType(data.get("transformationType"));

		if (data.containsKey("deliveryHub"))
			orderData.setHub(data.get("deliveryHub"));

		if (data.containsKey("subCategory"))
			orderData.setSubCategory(data.get("subCategory"));

		if (data.containsKey("product"))
			orderData.setProduct(data.get("product"));

		if (data.containsKey("seller"))
			orderData.setSeller(data.get("seller"));

		if (data.containsKey("uom"))
			orderData.setToUom(data.get("uom"));

		if (data.containsKey("requestedQty"))
			orderData.setRequestedQty(data.get("requestedQty"));

		// Optional / future fields
		if (data.containsKey("processUom"))
			orderData.setFromUom(data.get("processUom"));
//            if (data.containsKey("primaryUom"))
//				orderData.setFromUom(data.get("primaryUom"));
		if (data.containsKey("processQty"))
			orderData.setProcessedQty(data.get("processQty"));
		if (data.containsKey("transformedProduct"))
			orderData.setTransformedProduct(data.get("transformedProduct"));
		if (data.containsKey("sourceSeller"))
			orderData.setSourceSeller(data.get("sourceSeller"));
		if (data.containsKey("minYield"))
			orderData.setMinYield(data.get("minYield"));
		if (data.containsKey("maxYield"))
			orderData.setMaxYield(data.get("maxYield"));
		if (data.containsKey("wastageProduct"))
			orderData.setWastageProduct(data.get("wastageProduct"));
		if (data.containsKey("actualYield"))
			orderData.setActualYield(data.get("actualYield"));
	}

	
}
