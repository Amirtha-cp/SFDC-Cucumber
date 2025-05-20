package com.Automation.Base;


import io.cucumber.java.*;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.Automation.Utility.*;

public class Hooks {
    public static ExtentReportUtils extentReportUtilsObj;
    public static Logger logger = LogManager.getLogger(Hooks.class);
    String url = Constants.TEST_URL;

    @Before(order = 0)
    public void initReport() {
        extentReportUtilsObj = ExtentReportUtils.getInstance();
        System.out.println("\n \t \t" + extentReportUtilsObj.hashCode());
        logger.debug(extentReportUtilsObj.hashCode());
        logger.debug("Hooks: initReport - Initializing extent report");
    }

    @Before(order = 1)
    public void setUp(Scenario scenario) throws InterruptedException {
        String browser = System.getProperty("browser", "chrome");
        BaseTest.launchBrowser(browser);
       
        BaseTest.goToUrl(url);;
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
    	BaseTest.closeDriver();
    }

    @After(order = 0)
    public void endReport() {
    	if (extentReportUtilsObj != null) {
            extentReportUtilsObj.endExtentReport();
            logger.debug("Hooks: endReport - Flushing extent report");
        } else {
            System.err.println("extentReportUtilsObj is null in endReport()");
        }
//        extentReportUtilsObj.endExtentReport();
       
    }
}

