package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import inputManagePackage.InputSystem;


public class DatabaseTest {

	private List<String> filesList;
	private Database db;
	private InputSystem inputsystem;
	
	private String filesDirPath;
	private String taxpayerZarrasFile;
	private String taxpayerZisisFile; 
	
	private Taxpayer taxpayerOne;
	private Taxpayer taxpayerTwo;
	
	@Before
	public void setUp() throws Exception {
		this.db = new Database();
		this.inputsystem = new InputSystem();
		this.filesList = new ArrayList<String>();
		taxpayerOne = new Taxpayer("Evangelos Barmpalias", "150230357", "single", "20000");
		taxpayerTwo = new Taxpayer("Dimitrios Zervas", "190034010", "married filing separately", "45000");

		
		this.filesDirPath = "InputFiles/";
		this.taxpayerZarrasFile = "130456093_INFO.txt"; 
		this.taxpayerZisisFile = "130456094_INFO.xml";
		filesList.add(taxpayerZarrasFile);
		filesList.add(taxpayerZisisFile);
		
	}


	@Test
	public void testProccessTaxpayersDataFromFilesIntoDatabase() throws FileNotFoundException, IOException {
		db.proccessTaxpayersDataFromFilesIntoDatabase(filesDirPath, filesList);
		for(int i = 0; i < db.getTaxpayersArrayListSize(); i++) {
			assertNotNull(db.getTaxpayerFromArrayList(i));
		}
		
		db.removeTaxpayerFromArrayList(0);
		db.removeTaxpayerFromArrayList(0);
		
	}

	@Test
	public void testAddTaxpayerToList() {
		
		String[] nameArray = { "Evangelos Barmpalias", "Dimitrios Zervas"};
		String[] afmArray = { "150230357", "190034010"};
			
		db.addTaxpayerToList(this.taxpayerOne);
		db.addTaxpayerToList(this.taxpayerTwo);
		
		for(int i = 0; i < db.getTaxpayersArrayListSize(); i++) {
			assertEquals(nameArray[i] + " | " + afmArray[i], db.getTaxpayerNameAfmValuesPairList(i));
		}
	}


	@Test
	public void testGetTaxpayerFromArrayList() {
		db.addTaxpayerToList(this.taxpayerOne);
		db.addTaxpayerToList(this.taxpayerTwo);
		
		Taxpayer payer = db.getTaxpayerFromArrayList(1);
		assertEquals(taxpayerTwo.getName(), payer.getName());
		assertEquals(taxpayerTwo.getAFM(), payer.getAFM());
		assertEquals(taxpayerTwo.getFamilyStatus(), payer.getFamilyStatus());
		assertEquals(taxpayerTwo.getIncome(), payer.getIncome(), 5);
		assertEquals(taxpayerTwo.getBasicTax(), payer.getBasicTax(), 4);
		assertEquals(taxpayerTwo.getBasicReceiptsTotalAmount(), payer.getBasicReceiptsTotalAmount(),2);
		assertEquals(taxpayerTwo.getEntertainmentReceiptsTotalAmount(), payer.getEntertainmentReceiptsTotalAmount(),3);
		assertEquals(taxpayerTwo.getHealthReceiptsTotalAmount(), payer.getHealthReceiptsTotalAmount(),3);
		
	}

	@Test
	public void testRemoveTaxpayerFromArrayList() {
		// Insert one Taxpayer to the list and check the size(should be 1)
		db.addTaxpayerToList(taxpayerOne);
		assertEquals(1, db.getTaxpayersArrayListSize());
		
		//Remove the first element (index=0) from the list and check the new size of the array
		db.removeTaxpayerFromArrayList(0);
		assertEquals(0, db.getTaxpayersArrayListSize());
	}

	
	@Test
	public void testUpdateTaxpayerInputFile() {
		fail("Not yet implemented");
	}

}
