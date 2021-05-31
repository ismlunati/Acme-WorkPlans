package acme.testing.administrator.spamword;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordShowTest extends AcmePlannerTest {
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	
	
	/*
	 * Característica "acme.features.administrator.spamword.AdministratorSpamwordShowService"
	 * 
	 * Se comprueba que se muestran correctamente las palabras Spam
	 * Debido a que todas la entradas de la lista coinciden con las del fichero csv, no se producirá ningún error.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamword/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String spamWord) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Words");
		
		super.checkColumnHasValue(recordIndex, 0, spamWord);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("palabraSpam", spamWord);
		
		super.signOut();
	}
}