package commons;

import java.io.File;

public class GlobalConstants {
	
	public static final long LONGTIMEOUT = 30;
	public static final long SHORTTIMEOUT = 5;
	public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_PATH = RELATIVE_PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
}
