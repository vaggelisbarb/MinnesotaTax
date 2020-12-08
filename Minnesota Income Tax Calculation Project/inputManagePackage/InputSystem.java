package inputManagePackage;
import dataManagePackage.*;
import dataManagePackage.Receipt.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class InputSystem {
	
	/** 
	* Checks the file's extension. If .txt -> loadTaxpayerDataFromTxtFileIntoDatabase(afmInfoFilesFolderPath, afmInfoFile)
	* Else if .xml -> loadTaxpayerDataFromXmlFileIntoDatabase(afmInfoFilesFolderPath, afmInfoFile)
	*/
	
	/**
	 * @param afmInfoFilesFolderPath. A string with the folder's path to a file/s that contain info about taxpayer/s
	 * @param taxpayersAfmInfoFiles. A list that contains all the these files 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void addTaxpayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles) throws FileNotFoundException, IOException{
		for (String afmInfoFile : taxpayersAfmInfoFiles)
		{
			if (afmInfoFile.endsWith(".txt")){
				loadTaxpayerDataFromTxtFileIntoDatabase(afmInfoFilesFolderPath, afmInfoFile);
			}
			else if (afmInfoFile.endsWith(".xml")){
				loadTaxpayersDataFromXmlFileIntoDatabase(afmInfoFilesFolderPath, afmInfoFile);
			}
		}
	}
	
	
	/**
	 * @param afmInfoFileFolderPath. Folder path of the .txt file with info about 1 taxpayer 
	 * @param afmInfoFile. String with one .txt file about 1 taxpayer
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private static void loadTaxpayerDataFromTxtFileIntoDatabase(String afmInfoFileFolderPath, String afmInfoFile) throws FileNotFoundException, IOException{
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new FileInputStream(afmInfoFileFolderPath+"/"+afmInfoFile));
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println(afmInfoFileFolderPath+"/"+afmInfoFile);
			System.out.println("Problem opening " + afmInfoFile + " file.");
			System.exit(0);
		}			
		
		String taxpayerName = getParameterValueFromTxtFileLine(inputStream.nextLine(), "Name: ");
		String taxpayerAFM = getParameterValueFromTxtFileLine(inputStream.nextLine(), "AFM: ");
		String taxpayerStatus = getParameterValueFromTxtFileLine(inputStream.nextLine(), "Status: ");
		String taxpayerIncome = getParameterValueFromTxtFileLine(inputStream.nextLine(), "Income: ");
		Taxpayer newTaxpayer = new Taxpayer(taxpayerName, taxpayerAFM, taxpayerStatus, taxpayerIncome);
		
		String fileLine;
		while (inputStream.hasNextLine())
		{
			fileLine = inputStream.nextLine();
			if (fileLine.equals("")) continue;
			if (fileLine.indexOf("Receipts:")!=-1) continue;
			
			String receiptID = getParameterValueFromTxtFileLine(fileLine, "Receipt ID: ");
			String receiptDate = getParameterValueFromTxtFileLine(inputStream.nextLine(), "Date: ");
			String receiptKind = getParameterValueFromTxtFileLine(inputStream.nextLine(), "Kind: ");
			String receiptAmount = getParameterValueFromTxtFileLine(inputStream.nextLine(), "Amount: ");
			String receiptCompany = getParameterValueFromTxtFileLine(inputStream.nextLine(), "Company: ");
			String receiptCountry = getParameterValueFromTxtFileLine(inputStream.nextLine(), "Country: ");
			String receiptCity = getParameterValueFromTxtFileLine(inputStream.nextLine(), "City: ");
			String receiptStreet = getParameterValueFromTxtFileLine(inputStream.nextLine(), "Street: ");
			String receiptNumber = getParameterValueFromTxtFileLine(inputStream.nextLine(), "Number: ");
			Receipt newReceipt = ReceiptFactory.createNewReceipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);
			
			newTaxpayer.addReceiptToList(newReceipt);
		}
		
		Database.addTaxpayerToList(newTaxpayer);
	}
	
	/**
	 * @param fileLine. One line of the file
	 * @param parameterName. String containing the start of a line which is the standard parametre
	 * @return a String with the line's info without the parametre
	 */
	private static String getParameterValueFromTxtFileLine(String fileLine, String parameterName){
		return fileLine.substring(parameterName.length(), fileLine.length());
	}
	
	/**
	 * @param afmInfoFileFolderPath
	 * @param afmInfoFile
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private static void loadTaxpayersDataFromXmlFileIntoDatabase(String afmInfoFileFolderPath, String afmInfoFile) throws FileNotFoundException, IOException{
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new FileInputStream(afmInfoFileFolderPath+"/"+afmInfoFile));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening " + afmInfoFile + " file.");
			System.exit(0);
		}			
		
		String taxpayerName = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<Name> ", " </Name>");
		String taxpayerAFM = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<AFM> ", " </AFM>");
		String taxpayerStatus = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<Status> ", " </Status>");
		String taxpayerIncome = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<Income> ", " </Income>");
		Taxpayer newTaxpayer = new Taxpayer(taxpayerName, taxpayerAFM, taxpayerStatus, taxpayerIncome);
		
		String fileLine;
		while (inputStream.hasNextLine())
		{
			fileLine = inputStream.nextLine();
			if (fileLine.equals("")) continue;
			if (fileLine.indexOf("<Receipts>")!=-1) continue;
			if (fileLine.indexOf("</Receipts>")!=-1) break;
			
			String receiptID = getParameterValueFromXmlFileLine(fileLine, "<ReceiptID> ", " </ReceiptID>");
			String receiptDate = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<Date> ", " </Date>");
			String receiptKind = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<Kind> ", " </Kind>");
			String receiptAmount = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<Amount> ", " </Amount>");
			String receiptCompany = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<Company> ", " </Company>");
			String receiptCountry = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<Country> ", " </Country>");
			String receiptCity = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<City> ", " </City>");
			String receiptStreet = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<Street> ", " </Street>");
			String receiptNumber = getParameterValueFromXmlFileLine(inputStream.nextLine(), "<Number> ", " </Number>");
			Receipt newReceipt = ReceiptFactory.createNewReceipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);
			
			newTaxpayer.addReceiptToList(newReceipt);
		}
		
		Database.addTaxpayerToList(newTaxpayer);
	}
	
	private static String getParameterValueFromXmlFileLine(String fileLine, String parameterStartField, String parameterEndField){
		return fileLine.substring(parameterStartField.length(), fileLine.length()-parameterEndField.length());
	}
}