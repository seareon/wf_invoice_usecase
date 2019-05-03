# InvoicePlane System Automation Module

## Current Module Features

Main goal of this system-type module is to provide application-specific Selenium PageObjects hence providing reusable library for number of use cases where InvoicePlane is in scope of RPA automation.
Currently implemented LoginPage, MainPage, MenuNavigationBar, CreateProductPage, ProductsPage. Following features are implemented in PageObjects:
 
1. Authentication (login) into InvoicePlane.
2. Navigation using InvoicePlane menu.
3. Collect list of products from Products > View products page. Products are shown by 15. Module's method iterates pagination until last page and collects all products. 
4. Create product on Products > Create product page. In case of insufficient product input data or timeout method throws RuntimeException which can be caught later in Bot Task.

## Bot Task To Invoke InvoicePlane Automation Code

This module doesn't contain any Bot Tasks. Typically Bot Tasks are use case specific and should be kept in process-type module, which isn't shared and specific to concrete use case implementation. 

## How To Use

When you need to use this module - include it into <modules> section of your main pom.xml file.
Then in you Robot class create instance of Client and you ready to work with pageObjects:
``` 
final InvoicePlaneClient client = new InvoicePlaneClient(logger, params);
final LoginPage loginPage = client.getLoginPage();
```

## Secret Vault Credentials To Configure
**alias:** invoiceplane_credentials_alias  
**key:** wf-robot@mail.com  
**value:** BotsRock4ever! 
	
## Global Variable To Configure
**name:** invoiceplane_url  
**value:** https://train-invoiceplane.workfusion.com
