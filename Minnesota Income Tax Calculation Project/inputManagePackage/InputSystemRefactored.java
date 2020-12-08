package inputManagePackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


/**
 * @author vaggelisbarb
 *
 */
public class InputSystemRefactored {
	
	private static InputSystemRefactored inputSystem;
	private Parser parser;
	

	private InputSystemRefactored() {
		parser = new Parser();
	}
	
	
	public static InputSystemRefactored getInputSystemInstance() {
		if (inputSystem == null)
			inputSystem = new InputSystemRefactored();
		return inputSystem;
	}
	
	
	public void addTaxPayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles) throws FileNotFoundException, IOException {
		parser.addTaxPayersDataFromFilesIntoDatabase(afmInfoFilesFolderPath, taxpayersAfmInfoFiles);
	}
		
	
	
}
