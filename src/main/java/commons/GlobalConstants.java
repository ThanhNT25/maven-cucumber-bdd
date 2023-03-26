package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com";
	//public static final String PORTAL_PAGE_URL_WORDPRESS = "http://localhost/automation/wp-admin/";
	//public static final String PORTAL_PAGE_URL_JQUERY = "https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/";
	public static final String PORTAL_PAGE_URL_JQUERY_DATA_TABLE = "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/";
	public static final String PORTAL_PAGE_URL_JQUERY_UPLOAD_FILE = "https://blueimp.github.io/jQuery-File-Upload/";
	public static final String PORTAL_PAGE_URL_FACEBOOK = "https://www.facebook.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com";
	public static final String NOPCOMMERCE_URL = "https://demo.nopcommerce.com/register";
	public static final String PORTAL_TESTING_URL = "https://admin-demo.nopcommerce.com";
	public static final String ADMIN_TESTING_URL = "https://admin-demo.nopcommerce.com";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.dir");
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" +File.separator;
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_FROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;
	public static final String DB_DEV_URL = "32.18.252:9860";
	public static final String DB_DEV_USER = "Automation";
	public static final String DB_DEV_PASS = "Automation";
	public static final String DB_TEST_URL = "32.18.252:9860";
	public static final String DB_TEST_USER = "Automation";
	public static final long SHORT_TIME = 5;
	public static final long LONG_TIME = 15;
	public static final long RETRY_TEST_FAIL = 3;

}
