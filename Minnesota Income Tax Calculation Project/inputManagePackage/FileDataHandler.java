/**
 * 
 */
package inputManagePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import dataManagePackage.DatabaseRefactored;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Receipt.ReceiptFactory;
import dataManagePackage.Receipt.ReceiptRefactored;

/**
 * @author vaggelisbarb
 *
 */
public abstract class FileDataHandler {
	
	// db is the global DatabaseRefactored instance
	public DatabaseRefactored db = DatabaseRefactored.getDatabaseInstance();
	
	
	/**
	 * @param afmInfoFilesFolderPath
	 * @param taxpayersAfmInfoFiles
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void addTaxPayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles) throws FileNotFoundException, IOException{
		for (String afmInfoFile : taxpayersAfmInfoFiles) {
			String extension = afmInfoFile.substring(afmInfoFile.lastIndexOf(".")+1);
			
			loadTaxpayerDataFromFileIntoDatabase(extension, afmInfoFilesFolderPath, afmInfoFile);
		}
	}
	
	
	/**
	 * @param extension
	 * @param afmInfoFileFolderPath
	 * @param afmInfoFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void loadTaxpayerDataFromFileIntoDatabase(String extension, String afmInfoFileFolderPath, String afmInfoFile) throws FileNotFoundException, IOException {
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream(afmInfoFileFolderPath+"/"+afmInfoFile));
		}catch(FileNotFoundException e){
			System.out.println(afmInfoFileFolderPath+"/"+afmInfoFile);
			System.out.println("Error!! Cannot open " + afmInfoFile + " file.");
			System.exit(0);
		}
		
		String taxpayerName = getParameterValueFromFileLine(inputStream.nextLine(), "Name", extension);
		String taxpayerAFM = getParameterValueFromFileLine(inputStream.nextLine(), "AFM", extension);
		String taxpayerStatus = getParameterValueFromFileLine(inputStream.nextLine(), "Status", extension);
		String taxpayerIncome = getParameterValueFromFileLine(inputStream.nextLine(), "Income", extension);
		Taxpayer newTaxpayer = new Taxpayer(taxpayerName, taxpayerAFM, taxpayerStatus, taxpayerIncome);
		
		String fileLine;
		while (inputStream.hasNextLine()) {
			fileLine = inputStream.nextLine();
			
			if (receiptsCheckingConditions(extension, fileLine)) {
				String receiptID = getParameterValueFromFileLine(fileLine, "ReceiptID", extension);
				String receiptDate = getParameterValueFromFileLine(inputStream.nextLine(), "Date", extension);
				String receiptKind = getParameterValueFromFileLine(inputStream.nextLine(), "Kind", extension);
				String receiptAmount = getParameterValueFromFileLine(inputStream.nextLine(), "Amount", extension);
				String receiptCompany = getParameterValueFromFileLine(inputStream.nextLine(), "Company", extension);
				String receiptCountry = getParameterValueFromFileLine(inputStream.nextLine(), "Country", extension);
				String receiptCity = getParameterValueFromFileLine(inputStream.nextLine(), "City", extension);
				String receiptStreet = getParameterValueFromFileLine(inputStream.nextLine(), "Street", extension);
				String receiptNumber = getParameterValueFromFileLine(inputStream.nextLine(), "Number", extension);
				
				// TODO Check if the ReceiptRefactored class is working
				ReceiptRefactored newReceipt = new ReceiptRefactored(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);
				
				// TODO Change Taxpayer class method to take argument ReceiptRefactored objects
				//newTaxpayer.addReceiptToList(newReceipt);
				
			}
			db.addTaxpayerToList(newTaxpayer); 
			
		}
		
	}
	
	public abstract String getParameterValueFromFileLine(String fileLine, String parameterName, String extension);
	
	public abstract boolean receiptsCheckingConditions(String extension, String fileLine);
	
}
