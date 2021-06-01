package acme.testing.administrator.customization;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorCustomizationListTest extends AcmePlannerTest {
	// Test cases -------------------------------------------------------------

	/*
	 * Característica "acme.features.administrator.customization.AdministratorCustomizationListService"
	 * 
	 * Este test se encarga de comprobar que coincida el elemento con el que se ha pasado por el csv.
	 * En este caso, ya que aquí solo mostramos el umbral, solo habría que probar que coincidia el umbral establecido(10.00)
	 * con el pasado por el fichero csv(10.00), como coinciden no se producirá ningun error. Después de esto entramos en la vista show
	 * y comprobamos de nuevo que los valores coinciden.
	 * 
	 * Para list no hay ningún caso negativo que se pueda comprobar, ya que es simplemente comprobar que los valores coincidan.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/customization/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String threshold) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Threshold");		
		
		super.checkColumnHasValue(recordIndex, 0, threshold);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("tolerancia", threshold);
		
		super.signOut();
	}

}
