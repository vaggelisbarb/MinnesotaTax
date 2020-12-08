package dataManagePackage;
import dataManagePackage.Receipt.*;
import inputManagePackage.*;
import outputManagePackage.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling the taxpayers and the neccesary files to afm info.
 *
 */
public class Database {
	private static String taxpayersInfoFilesPath;
	private static ArrayList<Taxpayer> taxpayersArrayList = new ArrayList<Taxpayer>();
	
	public static void setTaxpayersInfoFilesPath(String taxpayersInfoFilesPath){
		Database.taxpayersInfoFilesPath = taxpayersInfoFilesPath;
	}
	
	public static String getTaxpayersInfoFilesPath(){
		return Database.taxpayersInfoFilesPath;
	}
	
	/**
	 * @param afmInfoFilesFolderPath . A string with the folder's path to a file/s that contain info about taxpayer/s
	 * @param taxpayersAfmInfoFiles . A list that contains all the these files
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void proccessTaxpayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles) throws FileNotFoundException, IOException{
		InputSystem.addTaxpayersDataFromFilesIntoDatabase(afmInfoFilesFolderPath, taxpayersAfmInfoFiles);
	}
	
	/**
	 * Adds a taxpayer into the arraylist
	 * @param taxpayer. A Taxpayer object about a specific taxpayer
	 */
	public static void addTaxpayerToList(Taxpayer taxpayer){
		taxpayersArrayList.add(taxpayer);
	}
	
	public static int getTaxpayersArrayListSize(){
		return taxpayersArrayList.size();
	}
	
	public static Taxpayer getTaxpayerFromArrayList(int index){
		return taxpayersArrayList.get(index);
	}
	
	public static void removeTaxpayerFromArrayList(int index){
		taxpayersArrayList.remove(index);
	}
	
	/**
	 * @param index. An int with the position of the taxpayers' arraylist
	 * @return a String with the name & AFM of a specific taxpayer from the taxpayers' arraylist
	 */
	public static String getTaxpayerNameAfmValuesPairList(int index){
		Taxpayer taxpayer = taxpayersArrayList.get(index);
		return taxpayer.getName() + " | " + taxpayer.getAFM();
	}
	
	/**
	 * @return an Array of the names and AFM's of all taxpayers that are found in the taxpayers' arraylist
	 */
	public static String[] getTaxpayersNameAfmValuesPairList(){
		String[] taxpayersNameAfmValuesPairList = new String[taxpayersArrayList.size()];
		
		int c = 0;
		for (Taxpayer taxpayer : taxpayersArrayList){
			taxpayersNameAfmValuesPairList[c++] = taxpayer.getName() + " | " + taxpayer.getAFM();
		}
		
		return taxpayersNameAfmValuesPairList;
	}
	
	/**
	 * Generates a new txt/xml file for a specific taxpayer
	 * OutputSystem is been called to write into this file.
	 * @param index. An int with the position of the taxpayers in the arraylist 
	 */
	public static void updateTaxpayerInputFile(int index){
		File taxpayersInfoFilesPathFileObject = new File(taxpayersInfoFilesPath);
		FilenameFilter fileNameFilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
               return (name.toLowerCase().endsWith("_info.txt") || name.toLowerCase().endsWith("_info.xml"));
            }
         };
		
		for (File file : taxpayersInfoFilesPathFileObject.listFiles(fileNameFilter)){
			if (!file.getName().contains(taxpayersArrayList.get(index).getAFM())) continue;
			
			if (file.getName().toLowerCase().endsWith(".txt")){
				OutputSystem.saveUpdatedTaxpayerTxtInputFile(file.getAbsolutePath(), index);
			}
			if (file.getName().toLowerCase().endsWith(".xml")){
				OutputSystem.saveUpdatedTaxpayerXmlInputFile(file.getAbsolutePath(), index);
			}
			break;
		}
	}	
}