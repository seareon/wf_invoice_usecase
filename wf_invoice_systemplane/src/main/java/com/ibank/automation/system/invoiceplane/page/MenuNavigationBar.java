package com.ibank.automation.system.invoiceplane.page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import com.ibank.automation.system.invoiceplane.RobotDriverWrapper;

public class MenuNavigationBar extends RobotDriverWrapper {

    @FindBy(xpath = "//a[contains(@class,'logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/a[text()='Dashboard']")
    private WebElement dashboardMenu;

    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/a//span[text()='Products']/parent::*")
    private WebElement productsMenu;

    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/ul/li/a[text()='View products']")
    private WebElement viewProductsMenuItem;
    
    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/ul/li/a[text()='Create product']")
    private WebElement createProductMenuItem;


    public MenuNavigationBar(Logger logger) {
        super(logger);
    }

    public ProductsPage openProducts() {
        productsMenu.click();
        viewProductsMenuItem.click();

        return new ProductsPage(logger);
    }
    
    public CreateProductPage openCreateProduct() {
        productsMenu.click();
        createProductMenuItem.click();

        return new CreateProductPage(logger);
    }

    public void openDashboard() {
        dashboardMenu.click();
    }

    // If it is necessary to logout from Invoice Plane explicitly
    public void logout() {
        try {
            logoutButton.click();
        } catch (TimeoutException ex) {
            logger.info("Timed out on waiting logout button");
        }
    }

}