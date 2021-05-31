package acme.testing.administrator.customization;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorCustomizationUpdateTest  extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	
	
	/*
	 * Característica "acme.features.administrator.customization.AdministratorCustomizationUpdateService"
	 * 
	 * Se comprueba que el valor de la tolerancia se actualiza del valor "10.00" al valor "20.00".
	 * Debido a que el valor introducido es válido, no se producirá ningún error.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/customization/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updatePositive(final int recordIndex, final String threshold, final String newThreshold) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Threshold");		
		
		super.checkColumnHasValue(recordIndex, 0, threshold);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("tolerancia", threshold);
		
		super.fillInputBoxIn("tolerancia", newThreshold);
		super.clickOnSubmitButton("Update");
		
		super.checkColumnHasValue(recordIndex, 0, newThreshold);
		
		super.signOut();
	}
	
	
	/*
	 * Característica "acme.features.administrator.customization.AdministratorCustomizationUpdateService"
	 * 
	 * Se comprueba que el valor de la tolerancia se actualiza del valor "10.00" al valor "aaa".
	 * Debido a que el valor introducido no es válido porque es de tipo String, se devolverá un error
	 * que dice "Invalid value"
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/customization/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updateNegative(final int recordIndex, final String threshold, final String newThreshold) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Threshold");		
		
		super.checkColumnHasValue(recordIndex, 0, threshold);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("tolerancia", threshold);
		
		super.fillInputBoxIn("tolerancia", newThreshold);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------

}
