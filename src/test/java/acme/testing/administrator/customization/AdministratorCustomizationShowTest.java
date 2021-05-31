package acme.testing.administrator.customization;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorCustomizationShowTest extends AcmePlannerTest {
	// Test cases -------------------------------------------------------------

	/*
	 * Característica "acme.features.administrator.customization.AdministratorCustomizationShowService"
	 * 
	 * Este test se encarga de comprobar que el servicio Show de la entidad Customization funcione correctamente.
	 * Para comprobarlo, entraremos en el umbral y comprobaremos que el input box(threshold) 
	 * coincida con el elemento con el que se ha pasado por el csv, como coinciden no se producirá ningun error
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/customization/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String threshold) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Threshold");		
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("tolerancia", threshold);
		
		super.signOut();
	}

}
