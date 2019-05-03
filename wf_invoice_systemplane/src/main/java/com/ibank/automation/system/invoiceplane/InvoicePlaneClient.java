package com.ibank.automation.system.invoiceplane;

import org.slf4j.Logger;
import com.ibank.automation.system.invoiceplane.page.LoginPage;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static com.workfusion.rpa.helpers.RPA.driver;

public class InvoicePlaneClient extends RobotDriverWrapper {

	public static final String INVOICEPLANE_PARAM = "invoiceplane_url";
	
	Map<String, String> params;

    public InvoicePlaneClient(Logger logger, Map<String, String> params) {
        super(logger);
        this.params = params;
        initDriver();
    }

    private void initDriver() {
        driver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS).pageLoadTimeout(90, TimeUnit.SECONDS);
        driver().manage().deleteAllCookies();
    }

    public LoginPage getLoginPage() {
        driver().navigate().to(params.get(INVOICEPLANE_PARAM));
        return new LoginPage(logger);
    }
    
}
