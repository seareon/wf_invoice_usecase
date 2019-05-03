package com.ibank.automation.system.invoiceplane.page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import com.ibank.automation.system.invoiceplane.RobotDriverWrapper;
import com.ibank.automation.system.invoiceplane.to.ProductTO;
import java.util.List;

public class CreateProductPage extends RobotDriverWrapper {

    @FindBy(id = "family_id")
    private WebElement family;
	
    @FindBy(id = "product_sku")
    private WebElement sku;

    @FindBy(id = "product_name")
    private WebElement productName;
    
    @FindBy(id = "product_description")
    private WebElement productDescription;

    @FindBy(id = "product_price")
    private WebElement productPrice;
    
    @FindBy(id = "tax_rate_id")
    private WebElement taxRate;

    @FindBy(xpath = "//button[@id='btn-submit']")
    private WebElement submit;
    
    @FindBy(xpath = "//div[@class='alert alert-danger']")
    private List<WebElement> addProductFailed;

    public CreateProductPage(Logger logger) {
        super(logger);
    }

    public ProductsPage addProduct(ProductTO product) {    	
    	family.click();
    	family.findElement(By.xpath("//option[contains(.,'" + product.getFamily() + "')]")).click();  	
    	    	
    	sku.click();
    	sku.clear();
    	sku.sendKeys(product.getSku());
    	
    	productName.click();
    	productName.clear();
    	productName.sendKeys(product.getProductName());
    	
    	productDescription.click();
    	productDescription.clear();
    	productDescription.sendKeys(product.getProductDescription());
    	
    	productPrice.click();
    	productPrice.clear();
    	productPrice.sendKeys(product.getPrice());
    	
    	taxRate.click();
    	taxRate.findElement(By.xpath("//option[contains(.,'" + product.getFamily() + "')]")).click();

        submit.click();
                
        try {
            if (addProductFailed.size() > 0) {
            	logger.debug(String.valueOf(addProductFailed.size()));
                throw new RuntimeException(addProductFailed.get(0).getText() + "\n" + "Product: " + product.getProductName());
            }            
        } catch (TimeoutException e) {
            logger.debug("Unkown error during creation of Invoice Plane product");
            throw new RuntimeException();
        }
    
    	return new ProductsPage(logger);
    }
    
}