package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dataManagePackage.Receipt.ReceiptRefactored;

public class ReceiptRefactoredTest {

	private ReceiptRefactored receiptRef;
	private String kind;
	private String id;
	private String date;
	private String amount;
	private String name;
	private String country;
	private String city;
	private String street;
	private String number;
	
	@Before
	public void setUp() throws Exception {
		this.kind = "Health";
		this.id = "3";
		this.date = "10/3/2014";
		this.amount = "500";
		this.name = "Dentist";
		this.country = "Greece";
		this.city = "Ioannina";
		this.street = "Lamprou";
		this.number = "20";
	}

	@Test
	public void testReceiptRefactored() {
		receiptRef = new ReceiptRefactored(kind, id, date, amount, name, country, city, street, number);
		System.out.println(receiptRef.toString());
		
		
		assertNotNull(receiptRef);
		
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetKind() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAmount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCompany() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
