package context;

public class OrderData {
	private String transformationType;
    private String deliveryHub;
    private String subCategory;
    private String product;
    private String seller;
    private String toUom;
    private String requestedQty;

    private String orderId;
    private String orderStatus;
    private String processingOrderId;
    private String fromUom;
    private String processQty;
    private String transformedProduct;
    private String sourceSeller;
    private String minYield;
    private String maxYield;
    private String wastageProduct;
    private String actualYield;
    

    // Getters & Setters
    public String getTransformationType() {
        return transformationType;
    }
    public void setTransformationType(String transformationType) {
        this.transformationType = transformationType;
    }

    public String getHub() {
        return deliveryHub;
    }
    public void setHub(String hub) {
        this.deliveryHub = hub;
    }

    public String getSubCategory() {
        return subCategory;
    }
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }

    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getToUom() {
        return toUom;
    }
    public void setToUom(String uom) {
        this.toUom = uom;
    }

    public String getRequestedQty() {
        return requestedQty;
    }
    public void setRequestedQty(String requestedQty) {
        this.requestedQty = requestedQty;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProcessingOrderId() {
        return processingOrderId;
    }
    public void setProcessingOrderId(String processingOrderId) {
        this.processingOrderId = processingOrderId;
    }
    public String getFromUom() {
        return fromUom;
    }
    public void setFromUom(String uom) {
        this.fromUom = uom;
    }
    public String getProcessedQty() {
		return processQty;
	}
    public void setProcessedQty(String processQty) {
		this.processQty = processQty;
    }
    public String getTransFormedProduct() {
    		return transformedProduct;
    }
    public void setTransformedProduct(String transformedProduct ) {
    			this.transformedProduct = transformedProduct;
    }
    public String getSourceSeller() {
    	return sourceSeller;
    }
    public void setSourceSeller(String sourceSeller) {
		this.sourceSeller = sourceSeller;
    }
    public String getMinYield() {
    			return minYield;
    }
    public void setMinYield(String minYield) {
    	this.minYield = minYield;
    }
    public String getMaxYield() {
    			return maxYield;
    }
    public void setMaxYield(String maxYield) {
    	this.maxYield = maxYield;
    }
    public String getWastageProduct() {
    			return wastageProduct;
    }
    public void setWastageProduct(String wastageProduct) {
    	this.wastageProduct = wastageProduct;
    }
    public String getActualYield() {
    			return actualYield;
    }
    public void setActualYield(String actualYield) {
    	this.actualYield = actualYield;
    }
}