package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dataManagePackage.FamilyStatus;

public class FamilyStatusTest {

	private double[] SINGLE_INCOME_LIMITS = {0,24680,81080,90000,152540};
	private double[] SINGLE_TAX_RATES = {1320.38,7.05,5296.58,7.85,5996.80,7.85,10906.19,9.85};
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetFamilyStatus() throws FileNotFoundException, IOException {
		FamilyStatus singleFs = FamilyStatus.getFamilyStatus("single");
		
		double[] singleIncomeLimits = singleFs.getIncomeLimits();
		double[] singleTaxRates = singleFs.getIncomeTaxRates();
		
		assertNotNull(singleFs);
		assertNotNull(singleIncomeLimits);
		assertNotNull(singleTaxRates);
		for(int i = 0; i < singleIncomeLimits.length; i++)
			assertEquals(SINGLE_INCOME_LIMITS[i], singleIncomeLimits[i], 5);
		for(int i = 0; i < singleTaxRates.length; i++)
			assertEquals(SINGLE_TAX_RATES[i], singleTaxRates[i], 5);
		
	}

}
