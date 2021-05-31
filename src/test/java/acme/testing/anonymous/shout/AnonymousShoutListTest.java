package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListTest extends AcmePlannerTest{

	// Test cases -------------------------------------------------------------
	
	/*
	 * Característica "acme.features.anonymous.shout.AnonymousShoutListService"
	 * 
	 * Este test se encarga de comprobar que el servicio List de la entidad Shout funcione correctamente,
	 * comprobando que cada columna del listado coincida con los datos pasados por el archivo csv.
	 */

		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void list(final int recordIndex, final String moment, final String author, final String text, final String info) {		

			super.clickOnMenu("Anonymous", "List shouts");
			
			super.checkColumnHasValue(recordIndex, 0, moment);
			super.checkColumnHasValue(recordIndex, 1, author);
			super.checkColumnHasValue(recordIndex, 2, text);
			super.checkColumnHasValue(recordIndex, 3, info);


		}
		
}
