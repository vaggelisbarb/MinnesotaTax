package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Receipt.ReceiptFactory;

public class TaxpayerTest {

	private Taxpayer payerSingle;
	private Taxpayer payerMarriedJointly;
	private Taxpayer payerMarriedSeparetly;
	private Taxpayer payerHeadOH;
	
	private ReceiptFactory receiptfactory;
	private ArrayList<Receipt> receipts;
	
	@Before
	public void setUp() throws Exception {
		this.payerSingle = new Taxpayer("Apostolos Dimitriou", "102034435", "single", "56780");
		this.payerMarriedJointly = new Taxpayer("Dionisis Koinos", "251060789", "married filing jointly", "15000");
		this.payerMarriedSeparetly = new Taxpayer("Takis Ksexoristos", "340890123", "married filing separately", "14880");
		this.payerHeadOH = new Taxpayer("Vrasidas Kurios", "123456789", "head of household", "140000");
	
		this.receiptfactory = new ReceiptFactory();
		this.receipts = new ArrayList<Receipt>();
	
	}

	@Test
	public void testCalculateTaxForMarriedFilingJointlyTaxpayerFamilyStatus() {
		Double payerIncome = this.payerMarriedJointly.getIncome();
		Double calculatedTax = payerMarriedJointly.calculateTaxForMarriedFilingJointlyTaxpayerFamilyStatus(payerIncome);
		assertEquals(802.5,calculatedTax, 2);
	}

	@Test
	public void testCalculateTaxForMarriedFilingSeparately() {
		Double payerIncome = this.payerMarriedSeparetly.getIncome();
		Double calculatedTax = this.payerMarriedSeparetly.calculateTaxForMarriedFilingSeparately(payerIncome);
		assertEquals(796.08, calculatedTax, 3);
	}

	@Test
	public void testCalculateTaxForSingles() {
		Double payerIncome = this.payerSingle.getIncome();
		Double calculatedTax = this.payerSingle.calculateTaxForSingles(payerIncome);
		assertEquals(3583.43, calculatedTax, 3);
	}

	@Test
	public void testCalculateTaxForHeadOfHousehold() {
		Double payerIncome = this.payerHeadOH.getIncome();
		Double calculatedTax = this.payerHeadOH.calculateTaxForHeadOfHousehold(payerIncome);
		assertEquals(9496.495, calculatedTax, 3);
	}

	@Test
	public void testCalculateTax() {
		double[] incomeLimits = {0, 30390, 90000, 122110, 203390};
		double[] taxrate = {1625.87, (7.05/100), 5828.38, (7.05/100), 8092.13, (7.85/100), 14472.61, (9.85/100)};
		
		Double payerIncome = this.payerHeadOH.getIncome();
		Double calculatedTax = this.payerHeadOH.calculateTaxForHeadOfHousehold(payerIncome);
		Double newCalculatedTaxDouble = this.payerHeadOH.calculateTax(payerIncome, incomeLimits, taxrate);
		
		assertEquals(calculatedTax, newCalculatedTaxDouble);
		
	}
	
	@Test
	public void testGetBasicReceiptsTotalAmount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEntertainmentReceiptsTotalAmount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTravelReceiptsTotalAmount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHealthReceiptsTotalAmount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOtherReceiptsTotalAmount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalReceiptsAmount() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddReceiptToList() {		
		Receipt clinicReceipt = receiptfactory.createNewReceipt("Health", "201934", "20/10/2019", "550", "Clinic Health Care", "Greece", "Athens", "Alexandrou", "120");
		payerSingle.addReceiptToList(clinicReceipt);
		
		assertNotNull(payerSingle.getReceiptsArrayList());
	}

	@Test
	public void testRemoveReceiptFromList() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts() {
		fail("Not yet implemented");
	}

}
