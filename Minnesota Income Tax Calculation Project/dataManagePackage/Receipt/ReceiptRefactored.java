package dataManagePackage.Receipt;

import java.math.BigDecimal;

/*
 * TODO : Na allaksei kai i ReceiptFactory wste na dimiourgei antikeimena
 * tupou receiptReftactored kai oxi antikeimena twn diaforetikwn klasewn
 * gia kathe eidos apodeiksis
 */

public class ReceiptRefactored {
	
	private String kind;
	private String id;
	private String date;
	private double amount;
	private Company company;
	
	public ReceiptRefactored(String kind, String id, String date, String amount, String name, String country, String city, String street, String number){
		this.kind = kind;
		this.id = id;
		this.date = date;
		this.amount = Double.parseDouble(amount);
		this.company = new Company(name, country, city, street, number);
	}
	
	public String getId(){
		return id;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getKind(){
		return kind;
	}
	
	@SuppressWarnings("deprecation")
	public double getAmount(){
		return (new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public Company getCompany(){
		return company;
	}
	
	public String toString(){
		return "ID: "+id
				+"\nDate: "+date
				+"\nKind: "+kind
				+"\nAmount: "+String.format("%.2f", amount)
				+"\nCompany: "+company.getName()
				+"\nCountry: "+company.getCountry()
				+"\nCity: "+company.getCity()
				+"\nStreet: "+company.getStreet()
				+"\nNumber: "+company.getNumber();
	}
	
}
