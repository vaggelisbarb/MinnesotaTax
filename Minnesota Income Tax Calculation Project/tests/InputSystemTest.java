/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.awt.desktop.PrintFilesEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.io.FileUtilities;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;
import inputManagePackage.InputSystem;

/**
 * @author vaggelisbarb
 *
 */
public class InputSystemTest {
	
	private Database db;
	private InputSystem inputsystem;
	private String txtInputFile;
	private String xmlInputFile;
	private String folderPath;
	private List<String> taxpayersInputFiles;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		taxpayersInputFiles = new ArrayList<String>();
		this.txtInputFile = "130456093_INFO.txt";
		this.xmlInputFile = "130456094_INFO.xml";
		this.folderPath = "InputFiles";
		
		taxpayersInputFiles.add(txtInputFile); // Create proper arraylist with the inputfile
		taxpayersInputFiles.add(xmlInputFile);
		
		db = new Database();
		inputsystem = new InputSystem();
	}

	/**
	 * Test method for {@link inputManagePackage.InputSystem#addTaxpayersDataFromFilesIntoDatabase(java.lang.String, java.util.List)}.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testAddTaxpayersDataFromFilesIntoDatabase() throws FileNotFoundException, IOException {
		inputsystem.addTaxpayersDataFromFilesIntoDatabase(folderPath, taxpayersInputFiles);
		
		// if the file is loaded successfully, database's arraylist field must have
		// added the new Taxpayer
		Taxpayer taxpayerZarras = db.getTaxpayerFromArrayList(0); 
		Taxpayer taxpayerZisis = db.getTaxpayerFromArrayList(1);
		
		assertNotNull(taxpayerZarras);
		assertEquals("130456093", taxpayerZarras.getAFM());
		assertEquals("Apostolos Zarras", taxpayerZarras.getName());
		assertEquals("Married Filing Jointly", taxpayerZarras.getFamilyStatus());
		
		assertNotNull(taxpayerZisis);
		assertEquals("130456094", taxpayerZisis.getAFM());
		assertEquals("Nikos Zisis", taxpayerZisis.getName());
		assertEquals("Single", taxpayerZisis.getFamilyStatus());
		
		ArrayList<Receipt> receiptListZarras = taxpayerZarras.getReceiptsArrayList();
		assertNotNull(receiptListZarras);
		
		ArrayList<Receipt> receiptListZisis = taxpayerZisis.getReceiptsArrayList();
		assertNotNull(receiptListZisis);
					
		// Empty Database's Arraylist
		db.removeTaxpayerFromArrayList(1);
		db.removeTaxpayerFromArrayList(0);
		
		// Delete Database object
		db = null;
	}

}
