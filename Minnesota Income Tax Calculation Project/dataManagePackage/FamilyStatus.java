package dataManagePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FamilyStatus {

	private double[] incomeLimits;
	private double[] incomeTaxRates;
	
	static final FamilyStatus SINGLE = new FamilyStatus();
	static final FamilyStatus HEAD_OF_HOUSEHOLD = new FamilyStatus();
	static final FamilyStatus MARRIED_FILLING_JOINTLY = new FamilyStatus();
	static final FamilyStatus MARRIED_FILLING_SEPARATELY = new FamilyStatus();
	
	static Properties prop;
	
	
	public FamilyStatus() {}
	
	public FamilyStatus(double[] incomeLimits, double[] incomeTaxRates) {
		super();
		this.incomeLimits = incomeLimits;
		this.incomeTaxRates = incomeTaxRates;
	} 
	
	public void setIncomeLimits(double[] inc) {
		this.incomeLimits=inc;
	}
	
	public void setIncomeTaxRates(double[] incomeTaxRates) {
		this.incomeTaxRates = incomeTaxRates;
	}
	
	public double[] getIncomeLimits() {
		return incomeLimits;
	}

	public double[] getIncomeTaxRates() {
		return incomeTaxRates;
	}


	
	/**
	 * @param familystatus
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static FamilyStatus getFamilyStatus(String familystatus) throws FileNotFoundException, IOException {
		
		switch (familystatus) {
		case "single":
			SINGLE.setIncomeLimits(processDataFromProperties("single_income"));
			SINGLE.setIncomeTaxRates(processDataFromProperties("single_tax"));
			return SINGLE;
		case "head of household":
			HEAD_OF_HOUSEHOLD.setIncomeLimits(processDataFromProperties("head_of_household_income"));
			HEAD_OF_HOUSEHOLD.setIncomeTaxRates(processDataFromProperties("head_of_household_tax"));
			return HEAD_OF_HOUSEHOLD;
		case "married filing separately":
			MARRIED_FILLING_SEPARATELY.setIncomeLimits(processDataFromProperties("married_filling_separately_income"));
			MARRIED_FILLING_SEPARATELY.setIncomeTaxRates(processDataFromProperties("married_filling_separately_tax"));
			return MARRIED_FILLING_SEPARATELY;
		case "married filing jointly":
			MARRIED_FILLING_JOINTLY.setIncomeLimits(processDataFromProperties("married_filling_jointly_income"));
			MARRIED_FILLING_JOINTLY.setIncomeTaxRates(processDataFromProperties("married_filling_jointly_tax"));
			return MARRIED_FILLING_JOINTLY;
		default:
			throw new IllegalArgumentException("Unexpected value: " + familystatus);
		}
			
	}
	
	/**
	 * @param familystatus String with the family status(this must match with the property name fro mthe properties file)
	 * @return String[] with the data(income or tax limits) of the given familystatus
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static double[] processDataFromProperties(String familystatus) throws FileNotFoundException, IOException{
		prop = new Properties();
		prop.load(new FileInputStream("config.properties"));
	
		String[] infos = fetchArrayFromPropFile(familystatus, prop);
		
		System.out.println("__Processing data__");
		double[] arr = new double[infos.length];
		for(int i=0; i<infos.length; i++) {
			arr[i] = Double.parseDouble(infos[i]);
			System.out.println("["+i+"]\t"+arr[i]);
		}
		
		return arr;
	}
	
	
	/**
	 * @param propertyName String that indicates which property is gonna be fetched from properties file(e.g single_income)
	 * @param propFile config.properties file
	 * @return String[] with the data(income or tax limits) of the given propertyName
	 */
	private static String[] fetchArrayFromPropFile(String propertyName, Properties propFile) {

		  //get array split up
		  String a = propFile.getProperty(propertyName);

		  //create the array with correct size
		  String[] array = new String[a.length()];

		  //split by comma and store into array
		  array = a.split(",");
		  System.out.println("\nFetching data for : "+propertyName.toUpperCase()+" from config.properties");

		  return array;
	}
	
}
