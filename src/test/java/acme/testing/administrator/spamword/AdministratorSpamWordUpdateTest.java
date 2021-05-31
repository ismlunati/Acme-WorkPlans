package acme.testing.administrator.spamword;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordUpdateTest extends AcmePlannerTest {
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	
	
	/*
	 * Característica "acme.features.administrator.spamword.AdministratorSpamwordUpdateService"
	 * 
	 * Se actualizan las palabras spam y se comprueba que sean las mismas que las fichero csv.
	 * Debido a que todas la entradas de la lista coinciden con las del fichero csv, no se producirá ningún error.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamword/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String spamWord, final String newSpamWord) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Words");
		
		super.checkColumnHasValue(recordIndex, 0, spamWord);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("palabraSpam", spamWord);
		
		super.fillInputBoxIn("palabraSpam", newSpamWord);
		
		super.clickOnSubmitButton("Update");
		
		super.checkColumnHasValue(recordIndex, 0, newSpamWord);
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
	
	 /* Se actualizan las palabras spam por un espacio en blanco.
	 * Al intentar introducir los espacios en blanco, saltara el error de que no se puede introducir un espacio en blanco.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamword/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updateNegative(final int recordIndex, final String spamWord, final String newSpamWord) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Words");		
		
		super.checkColumnHasValue(recordIndex, 0, spamWord);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("palabraSpam", spamWord);
		
		super.fillInputBoxIn("palabraSpam", newSpamWord);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
}