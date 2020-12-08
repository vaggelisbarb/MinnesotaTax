package dataManagePackage;

import java.util.ArrayList;

/**
 * Class that is responsible for the Database instance. 
 * It has been refactored using singleton pattern.
 */
public class DatabaseRefactored {
	
	private static DatabaseRefactored database;
	private static String taxpayersInfoFilesPath;
	private static ArrayList<Taxpayer> taxpayersArrayList;
	
	/**
	 * The constructor is private.
	 * getDatabaseIntance() will still be able to call the constructor,
	 * but not the other objects.
	 */
	private DatabaseRefactored() {
		DatabaseRefactored.taxpayersInfoFilesPath = null;
		DatabaseRefactored.taxpayersArrayList = new ArrayList<Taxpayer>();
	}
	
	
	/**
	 * Public static method for getting the DatabaseFactored instance.
	 * This method will be called by another class, instead of the private constructor.
	 * @return The DatabaseRefactored instance
	 */
	public static DatabaseRefactored getDatabaseInstance() {
		if (database == null)
			database = new DatabaseRefactored();
		return database;
	}
	
	
	public void setTaxpayersInfoFilesPath(String taxpayersInfoFilesPath){
		DatabaseRefactored.taxpayersInfoFilesPath = taxpayersInfoFilesPath;
	}
	
	public String getTaxpayersInfoFilesPath(){
		return DatabaseRefactored.taxpayersInfoFilesPath;
	}
	
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
	
	
	
	
}
