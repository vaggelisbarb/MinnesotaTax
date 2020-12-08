package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dataManagePackage.DatabaseRefactored;
import dataManagePackage.Taxpayer;

public class DatabaseRefactoredTest {

	private Taxpayer taxpayerOne;
	private Taxpayer taxpayerTwo;
	
	
	@Before
	public void setUp() throws Exception {
		taxpayerOne = new Taxpayer("Evangelos Barmpalias", "150230357", "single", "20000");
		//taxpayerTwo = new Taxpayer("Dimitrios Zervas", "190034010", "married filing separately", "45000");
	}

	@Test
	public void testGetDatabaseInstance() {
		DatabaseRefactored db1 = DatabaseRefactored.getDatabaseInstance();
		DatabaseRefactored db2 = DatabaseRefactored.getDatabaseInstance();
		
		// Check if db1 and db2 is the same instance of DatabaseRefactored Class
		assertNotNull(db1);
		assertNotNull(db2);
		assertEquals(db1, db2);
		
		db1 = null;
		db2 = null;
	}

	@Test
	public void testSetTaxpayersInfoFilesPath() {
		String taxpayersInfoFilesPath = "/InputFiles/130456093_INFO.txt";
		DatabaseRefactored db1 = DatabaseRefactored.getDatabaseInstance();
		db1.setTaxpayersInfoFilesPath(taxpayersInfoFilesPath);
		
		assertEquals(taxpayersInfoFilesPath, db1.getTaxpayersInfoFilesPath());
		db1 = null;
	}
	
	@Test
	public void testAddTaxpayerToList() {
		
		String[] nameArray = { "Evangelos Barmpalias"/*, "Dimitrios Zervas"*/};
		String[] afmArray = { "150230357"/*, "190034010"*/};
		DatabaseRefactored db1 = DatabaseRefactored.getDatabaseInstance();

		db1.addTaxpayerToList(taxpayerOne);
		//db1.addTaxpayerToList(this.taxpayerTwo);
		
		for(int i = 0; i < db1.getTaxpayersArrayListSize(); i++) {
			assertEquals(nameArray[i] + " | " + afmArray[i], db1.getTaxpayerNameAfmValuesPairList(i));
		}
	}

	


}
