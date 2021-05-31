package acme.testing.administrator.spamword;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamwordListTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	
	
	/*
	 * Característica "acme.features.administrator.spamword.AdministratorSpamwordListService"
	 * 
	 * Se comprueba que coincida cada elemento con el esperado de la lista de palabras spam.
	 * Debido a que todas la entradas de la lista coinciden con las del fichero csv, no se producirá ningún error.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamword/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String spamWord) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Words");		
		
		super.checkColumnHasValue(recordIndex, 0, spamWord);
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------

}
