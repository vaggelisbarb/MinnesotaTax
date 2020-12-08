package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jfree.io.FileUtilities;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.TestClass;
import org.junit.rules.TemporaryFolder;


import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;
import inputManagePackage.InputSystem;
import outputManagePackage.OutputSystem;

public class OutputSystemTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	private OutputSystem outputsystem;
	private static Database db;
	private static InputSystem inpSystem;
	private File txtTempFile;
	private File xmlTempFile;
	
	@BeforeClass
	public static void dbSetUp() throws Exception {
		inpSystem = new InputSystem();
		db = new Database();

		String txtInputFile = "130456093_INFO.txt";
		String xmlInputFile = "130456094_INFO.xml";
		String folderPath = "InputFiles";
		
		List<String> taxpayersInputfiles = new ArrayList<String>();
		taxpayersInputfiles.add(txtInputFile);
		taxpayersInputfiles.add(xmlInputFile);
	
		InputSystem.addTaxpayersDataFromFilesIntoDatabase(folderPath, taxpayersInputfiles);
	}
	
	@Before
	public void setUp() throws Exception {
		outputsystem = new OutputSystem();	
	
		txtTempFile = tempFolder.newFile("ZarrasNewFile.txt");
		xmlTempFile = tempFolder.newFile("ZisisNewFile.xml");
	
	}

	@Test
	public void testSaveUpdatedTaxpayerTxtInputFile() {
		outputsystem.saveUpdatedTaxpayerTxtInputFile(txtTempFile.getPath(), 0);
		
		int lines = tests.FileUtilities.countLinesOfAFile(txtTempFile.getPath());
		
		assertNotNull(txtTempFile);
		assertEquals(57, lines);
		
	}

	@Test
	public void testSaveUpdatedTaxpayerXmlInputFile() {
		outputsystem.saveUpdatedTaxpayerTxtInputFile(xmlTempFile.getPath(), 1);
		
		int lines = tests.FileUtilities.countLinesOfAFile(xmlTempFile.getPath());
		
		assertNotNull(xmlTempFile);
		assertEquals(37, lines);
	}

	@Test
	public void testSaveTaxpayerInfoToTxtLogFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveTaxpayerInfoToXmlLogFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateTaxpayerReceiptsPieJFreeChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateTaxpayerTaxAnalysisBarJFreeChart() {
		fail("Not yet implemented");
	}

}
