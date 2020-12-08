package dataManagePackage;
import dataManagePackage.Receipt.Receipt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * This class holds the data about a taxpayer
 *
 */
public class Taxpayer {
	private String name;
	private String afm;
	
	// Replace typecode with Class
	private String familyStatus;
	private FamilyStatus familyStatusObject;
	
	private double income;
	private double basicTax;
	private double taxIncrease;
	private double taxDecrease;
	private double totalTax;
	private ArrayList<Receipt> receipts;
	
	public Taxpayer(String name, String afm, String familyStatus, String income) throws FileNotFoundException, IOException{
		this.name = name;
		this.afm = afm;
		this.familyStatus = familyStatus;
		
		this.familyStatusObject = FamilyStatus.getFamilyStatus(familyStatus);
		
		this.income = Double.parseDouble(income);
		setBasicTaxBasedOnFamilyStatus();
		taxIncrease = 0;
		taxDecrease = 0;
		
		receipts = new ArrayList<Receipt>();
	}
	
	/**
	 * Calculates the basic tax 
	 */
	private void setBasicTaxBasedOnFamilyStatus(){
		switch(familyStatus.toLowerCase()){
			case("married filing jointly"):
				basicTax = calculateTaxForMarriedFilingJointlyTaxpayerFamilyStatus(income);
				break;
			case("married filing separately"):
				basicTax = calculateTaxForMarriedFilingSeparately(income);
				break;
			case("single"):
				basicTax = calculateTaxForSingles(income);
				break;
			case("head of household"):
				basicTax = calculateTaxForHeadOfHousehold(income);
				break;
		}
		
		totalTax = basicTax;
	}
	
	public double calculateTaxForMarriedFilingJointlyTaxpayerFamilyStatus(double totalIncome){
		double tax;
		
		if (totalIncome < 36080){
			tax = (5.35/100) * totalIncome;
		}
		else if (totalIncome < 90000){
			tax = 1930.28 + ((7.05/100) * (totalIncome-36080));
		}
		else if (totalIncome < 143350){
			tax = 5731.64 + ((7.05/100) * (totalIncome-90000));
		}
		else if (totalIncome < 254240){
			tax = 9492.82 + ((7.85/100) * (totalIncome-143350));
		}
		else{
			tax = 18197.69 + ((9.85/100) * (totalIncome-254240));
		}
		
		return tax;
	}
	
	public double calculateTaxForMarriedFilingSeparately(double totalIncome){
		double tax;
		
		if (totalIncome < 18040){
			tax = (5.35/100) * totalIncome;
		}
		else if (totalIncome < 71680){
			tax = 965.14 + ((7.05/100) * (totalIncome-18040));
		}
		else if (totalIncome < 90000){
			tax = 4746.76 + ((7.85/100) * (totalIncome-71680));
		}
		else if (totalIncome < 127120){
			tax = 6184.88 + ((7.85/100) * (totalIncome-90000));
		}
		else{
			tax = 9098.80 + ((9.85/100) * (totalIncome-127120));
		}
		
		return tax;
	}
	
	public double calculateTaxForSingles(double totalIncome){
		double tax;
		
		if (totalIncome < 24680){
			tax = (5.35/100) * totalIncome;
		}
		else if (totalIncome < 81080){
			tax = 1320.38 + ((7.05/100) * (totalIncome-24680));
		}
		else if (totalIncome < 90000){
			tax = 5296.58 + ((7.85/100) * (totalIncome-81080));
		}
		else if (totalIncome < 152540){
			tax = 5996.80 + ((7.85/100) * (totalIncome-90000));
		}
		else{
			tax = 10906.19 + ((9.85/100) * (totalIncome-152540));
		}
		
		return tax;
	}
	
	public double calculateTaxForHeadOfHousehold(double totalIncome){
		double tax;
		
		if (totalIncome < 30390){
			tax = (5.35/100) * totalIncome;
		}
		else if (totalIncome < 90000){
			tax = 1625.87 + ((7.05/100) * (totalIncome-30390));
		}
		else if (totalIncome < 122110){
			tax = 5828.38 + ((7.05/100) * (totalIncome-90000));
		}
		else if (totalIncome < 203390){
			tax = 8092.13 + ((7.85/100) * (totalIncome-122110));
		}
		else{
			tax = 14472.61 + ((9.85/100) * (totalIncome-203390));
		}
		
		return tax;
	}
	
	
	// TODO : prosthese metavliti j pou na auxanetai ana 2, wste na pigainei stin epomeni 'diada' tou pinaka incomeTaxRates
	public double calculateTax(double totalIncome, double[] incomeLimits, double[] incomeTaxRates) {
		double tax = 0;
		
		if( (totalIncome >= incomeLimits[0]) && (totalIncome <= incomeLimits[1]) )
			tax = totalIncome * (5.35/100);
		
		else if( (totalIncome >= incomeLimits[1]) && (totalIncome <= incomeLimits[2]) ) 
			tax = incomeTaxRates[0] + incomeTaxRates[1]*(totalIncome - incomeLimits[1]);
		
		else if( (totalIncome >= incomeLimits[2]) && (totalIncome <= incomeLimits[3]) ) 
			tax = incomeTaxRates[2] + incomeTaxRates[3]*(totalIncome - incomeLimits[2]);
		
		else if( (totalIncome >= incomeLimits[3]) && (totalIncome <= incomeLimits[4]) ) 
			tax = incomeTaxRates[4] + incomeTaxRates[5]*(totalIncome - incomeLimits[3]);
		
		else if (totalIncome >= incomeLimits[4]) 
			tax = incomeTaxRates[6] + incomeTaxRates[5]*(totalIncome - incomeLimits[4]);
		
		return tax;
	}
	
	public String toString(){
		return "Name: "+name
				+"\nAFM: "+afm
				+"\nStatus: "+familyStatus
				+"\nIncome: "+String.format("%.2f", income)
				+"\nBasicTax: "+String.format("%.2f", basicTax)
				+"\nTaxIncrease: "+String.format("%.2f", taxIncrease)
				+"\nTaxDecrease: "+String.format("%.2f", taxDecrease);
	}
	
	public Receipt getReceipt(int receiptID){
		return receipts.get(receiptID);
	}
	
	public ArrayList<Receipt> getReceiptsArrayList(){
		return receipts;
	}
	
	public String[] getReceiptsList(){
		String[] receiptsList = new String[receipts.size()];
		
		int c = 0;
		for (Receipt receipt : receipts){
			receiptsList[c++] = receipt.getId() + " | " + receipt.getDate() + " | " + receipt.getAmount();
		}
		
		return receiptsList;
	}
	
	public double getBasicReceiptsTotalAmount(){
		double basicReceiptsTotalAmount = 0;
		
		for (Receipt receipt : receipts){
			if (receipt.getKind().equals("Basic")){
				basicReceiptsTotalAmount += receipt.getAmount();
			}
		}
		
		return (new BigDecimal(basicReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getEntertainmentReceiptsTotalAmount(){
		double entertainmentReceiptsTotalAmount = 0;
		
		for (Receipt receipt : receipts){
			if (receipt.getKind().equals("Entertainment")){
				entertainmentReceiptsTotalAmount += receipt.getAmount();
			}
		}
		
		return (new BigDecimal(entertainmentReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTravelReceiptsTotalAmount(){
		double travelReceiptsTotalAmount = 0;
		
		for (Receipt receipt : receipts){
			if (receipt.getKind().equals("Travel")){
				travelReceiptsTotalAmount += receipt.getAmount();
			}
		}
		
		return (new BigDecimal(travelReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getHealthReceiptsTotalAmount(){
		double healthReceiptsTotalAmount = 0;
		
		for (Receipt receipt : receipts){
			if (receipt.getKind().equals("Health")){
				healthReceiptsTotalAmount += receipt.getAmount();
			}
		}
		
		return (new BigDecimal(healthReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getOtherReceiptsTotalAmount(){
		double otherReceiptsTotalAmount = 0;
		
		for (Receipt receipt : receipts){
			if (receipt.getKind().equals("Other")){
				otherReceiptsTotalAmount += receipt.getAmount();
			}
		}
		
		return (new BigDecimal(otherReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	
	public double getReceiptAmount(String receiptKind) {
		String[] kind = {"Entertainment", "Basic", "Travel", "Health", "Other" };
		double[] amount = {0, 0, 0, 0, 0};
		int position = -1;
		
		for (int i=0; i<kind.length; i++) {
			if(receiptKind.equals(kind[i]))
				position = i;
		}
		
		for (Receipt receipt : receipts) {
			if(receipt.getKind().equals(receiptKind))
				amount[position] += receipt.getAmount();
		}
		
		return (new BigDecimal(amount[position]).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());	
	}
	
	
	public double getTotalReceiptsAmount(){
		double totalReceiptsAmount = 0;
		
		for (Receipt receipt : receipts){
			totalReceiptsAmount += receipt.getAmount();
		}
		
		return (new BigDecimal(totalReceiptsAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public String getName(){
		return name;
	}
	
	public String getAFM(){
		return afm;
	}
	
	public String getFamilyStatus(){
		return familyStatus;
	}
	
	public double getIncome(){
		return (new BigDecimal(income).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getBasicTax(){
		return (new BigDecimal(basicTax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTaxInxrease(){
		return (new BigDecimal(taxIncrease).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTaxDecrease(){
		return (new BigDecimal(taxDecrease).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTotalTax(){
		return (new BigDecimal(totalTax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public void addReceiptToList(Receipt receipt){
		receipts.add(receipt);
		
		calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
	}
	
	public void removeReceiptFromList(int index){
		receipts.remove(index);
		
		calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
	}
	
	public void calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts(){
		double totalReceiptsAmount = 0;
		for (Receipt receipt : receipts){
			totalReceiptsAmount += receipt.getAmount();
		}
		
		taxIncrease = 0;
		taxDecrease = 0;
		if ((totalReceiptsAmount/(double)income) < 0.2){
			taxIncrease = basicTax * 0.08;
		}
		else if ((totalReceiptsAmount/(double)income) < 0.4){
			taxIncrease = basicTax * 0.04;
		}
		else if ((totalReceiptsAmount/(double)income) < 0.6){
			taxDecrease = basicTax * 0.15;
		}
		else{
			taxDecrease = basicTax * 0.30;
		}
		
		totalTax = basicTax + taxIncrease - taxDecrease;
	}
}