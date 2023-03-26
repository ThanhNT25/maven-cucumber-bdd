package commons;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	private WebDriver driverBaseTest;
	protected final Log log;
	
	public void initBeforeSuite() {
		
	}
	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	private String projectPath = System.getProperty("user.dir");
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

		 if(browserList == BrowserList.FIREFOX) {
			 WebDriverManager.firefoxdriver().setup();
			 FirefoxOptions options = new FirefoxOptions();
			 driverBaseTest = new FirefoxDriver();
		  } else if (browserList == BrowserList.H_FIREFOX) {
			  WebDriverManager.firefoxdriver().setup();
			  FirefoxOptions options = new FirefoxOptions();
			  options.addArguments(".headless");
			  options.addArguments("window-size-1920x1080");
			  driverBaseTest = new FirefoxDriver(options);
			  
		  } else if  (browserList == BrowserList.CHROME){ 
			  WebDriverManager.chromedriver().setup();
			  ChromeOptions options = new ChromeOptions();
			  driverBaseTest = new ChromeDriver();
			  
		  } else if  (browserList == BrowserList.H_CHROME){ 
			  WebDriverManager.chromedriver().setup();
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("--headless");
			  options.addArguments("window-size-1920x1080");
			  driverBaseTest = new ChromeDriver(options);
			  
		  } else if  (browserList == BrowserList.EDGE){ 
			  System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			  driverBaseTest = new EdgeDriver();
			  
		  }  else if  (browserList == BrowserList.OPERA){ 
			  System.setProperty("webdriver.opera.driver", projectPath + "\\browserDrivers\\operadriver.exe");
			  driverBaseTest = new OperaDriver();
		  } else {
			  throw new RuntimeException("Browser name invalid");
		  }
		 
		 driverBaseTest.get(GlobalConstants.PORTAL_PAGE_URL);
		 driverBaseTest.manage().window().maximize();
		 return driverBaseTest;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		 if(browserList == BrowserList.FIREFOX) {
			 System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			  driverBaseTest = new FirefoxDriver();
		  } else if (browserList == BrowserList.H_FIREFOX) {
			  System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			  FirefoxOptions options = new FirefoxOptions();
			  options.addArguments(".headless");
			  options.addArguments("window-size-1920x1080");
			  driverBaseTest = new FirefoxDriver(options);
			  
		  } else if  (browserList == BrowserList.CHROME){ 
			  System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			  driverBaseTest = new ChromeDriver();
			  
		  } else if  (browserList == BrowserList.H_CHROME){ 
			  System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("--headless");
			  options.addArguments("window-size-1920x1080");
			  driverBaseTest = new ChromeDriver(options);
			  
		  } else if  (browserList == BrowserList.EDGE){ 
			  System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			  driverBaseTest = new EdgeDriver();
			  
		  }  else if  (browserList == BrowserList.OPERA){ 
			  System.setProperty("webdriver.opera.driver", projectPath + "\\browserDrivers\\operadriver.exe");
			  driverBaseTest = new OperaDriver();
		  } else {
			  throw new RuntimeException("Browser name invalid");
		  }
		 
		 driverBaseTest.get(appUrl);
		 driverBaseTest.manage().window().maximize();
		 return driverBaseTest;
	}
	
	
	protected WebDriver getBrowserDriverEnvironment(String browserName, String environmentName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		 if(browserList == BrowserList.FIREFOX) {
			 System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			  driverBaseTest = new FirefoxDriver();
		  } else if (browserList == BrowserList.H_FIREFOX) {
			  System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			  FirefoxOptions options = new FirefoxOptions();
			  options.addArguments(".headless");
			  options.addArguments("window-size-1920x1080");
			  driverBaseTest = new FirefoxDriver(options);
			  
		  } else if  (browserList == BrowserList.CHROME){ 
			  System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			  driverBaseTest = new ChromeDriver();
			  
		  } else if  (browserList == BrowserList.H_CHROME){ 
			  System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("--headless");
			  options.addArguments("window-size-1920x1080");
			  driverBaseTest = new ChromeDriver(options);
			  
		  } else if  (browserList == BrowserList.EDGE){ 
			  System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			  driverBaseTest = new EdgeDriver();
			  
		  }  else if  (browserList == BrowserList.OPERA){ 
			  System.setProperty("webdriver.opera.driver", projectPath + "\\browserDrivers\\operadriver.exe");
			  driverBaseTest = new OperaDriver();
		  } else {
			  throw new RuntimeException("Browser name invalid");
		  }
		 
		 driverBaseTest.get(environmentName);
		 driverBaseTest.manage().window().maximize();
		 return driverBaseTest;
	}
	
//	private String getEnvironmentUrl(String serverName) {
//		String envUrl = null;
//		EnvironmentList environment = EnvironmentList.valueOf(serverName.toUpperCase());
//		if(environment == EnvironmentList.DEV) {
//			envUrl = "https://demo.nopcommerce.com/";
//		} else if(environment == EnvironmentList.TESTING) {
//			envUrl = "https://admin.demo.nopcommerce.com/";
//		}  else if(environment == EnvironmentList.STAGING) {
//			envUrl = "https://staging.orangehrmlive.com/";
//		}  else if(environment == EnvironmentList.PRODUCT) {
//			envUrl = "https://production.orangehrmlive.com/";
//		}
//		return envUrl;
//	}
//	
//	
//	public WebDriver getDriverInstance() {
//		return this.driverBaseTest;
//	}
//	
//	protected int generateFakeNumber() {
//		Random rand = new Random();
//		return rand.nextInt(9999);
//	}
//	
//	protected boolean VerifyTrue(boolean condition) {
//		boolean pass = true;
//		try {
//			if (condition == true) {
//				log.info(" -------------------------- PASSED -------------------------- ");
//			} else {
//				log.info(" -------------------------- FAILED -------------------------- ");
//			}
//			Assert.assertTrue(condition);
//		} catch (Throwable e) {
//			pass = false;
//
//			// Add lỗi vào ReportNG
//			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//			Reporter.getCurrentTestResult().setThrowable(e);
//		}
//		return pass;
//	}
//
//
//	protected boolean verifyFailed(boolean condition) {
//		boolean pass = true;
//		try {
//			if (condition == false) {
//				log.info(" -------------------------- PASSED -------------------------- ");
//			} else {
//				log.info(" -------------------------- FAILED -------------------------- ");
//			}
//			Assert.assertFalse(condition);
//		} catch (Throwable e) {
//			pass = false;
//			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//			Reporter.getCurrentTestResult().setThrowable(e);
//		}
//		return pass;
//	}
//
//
//	protected boolean verifyEquals(Object actual, Object expected) {
//		boolean pass = true;
//		try {
//			Assert.assertEquals(actual, expected);
//			log.info(" -------------------------- PASSED -------------------------- ");
//		} catch (Throwable e) {
//			pass = false;
//			log.info(" -------------------------- FAILED -------------------------- ");
//			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//			Reporter.getCurrentTestResult().setThrowable(e);
//		}
//		return pass;
//	}
	
	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driverBaseTest.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driverBaseTest != null) {
				driverBaseTest.manage().deleteAllCookies();
				driverBaseTest.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected String getCurrentDate() {
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
//		if(day < 10) {
//			String dayValue = "0" + day;
//			return dayValue;
//		}
		return String.valueOf(day);
	}
	
	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		String valueMonth ;
		if (month == 1) {
			return valueMonth ="January";
		} else if (month == 2) {
			return valueMonth ="February";
		} else if (month == 3) {
			return valueMonth ="March";
		} else if (month == 4) {
			return valueMonth ="April";
		} else if (month == 5) {
			return valueMonth ="May";
		} else if (month == 6) {
			return valueMonth ="Jule";
		} else if (month == 7) {
			return valueMonth ="July";
		} else if (month == 8) {
			return valueMonth ="August";
		} else if (month == 9) {
			return valueMonth ="ASeptemberpril";
		} else if (month == 10) {
			return valueMonth ="October";
		} else if (month == 11) {
			return valueMonth ="November";
		} else if (month == 12) {
			return valueMonth ="December";
		} 
		return String.valueOf(month);
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return now.getYear() + "";
	}

//	protected String getCurrentDay() {
//		return  getCurrentDate() + "/" + getCurrentMonth() + "/" +getCurrentYear();
//	}
	
	protected String getCurrentDay() {
		return getCurrentMonth() + " " + getCurrentDate() + ", " +getCurrentYear();
	}
}