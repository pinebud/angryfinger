package others;

import java.io.File;

public class Constants {
	public static final String RESOURCE_FOLDER = System.getProperty("user.dir")+"/resources";
	
	public static final String OUTPUT_FOLDER = System.getProperty("user.dir")+"/output";
	
	public static final File TEST_RESULT_FILE=new File(Constants.OUTPUT_FOLDER+"/testResult.txt");

}
