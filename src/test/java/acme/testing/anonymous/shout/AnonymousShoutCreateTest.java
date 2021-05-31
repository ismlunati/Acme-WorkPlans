package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {
	
	// Test cases -------------------------------------------------------------

	/*
	 * Característica "acme.features.anonymous.shout.AnonymousShoutCreateService"
	 * 
	 * Este test se encarga de comprobar que el servicio Create de la entidad Shout funcione correctamente,
	 * entrando en el formulario de creación y rellenando los distintos campos con los datos pasados por el csv.
	 * Después comprobamos que se han creado correctamente yendo a la vista del listado de Shouts y comprobando cada 
	 * columna con su valor esperado.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String moment, final String author, final String text, final String info) {		

		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "List shouts");

		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);

	}
	
	/*
	 * Característica "acme.features.anonymous.shout.AnonymousShoutCreateService"
	 * 
	 * Este test se encarga de comprobar que el servicio Create de la entidad Shout capture correctamente los fallos,
	 * entrando en el formulario de creación y rellenando los distintos campos con los datos pasados por el csv.
	 * Estos campos se rellenarán de forma errónea, comprobando los distintos fallos que nuestra aplicación debe capturar.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String moment, final String author, final String text, final String info) {		

		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");

		super.checkErrorsExist();

	}
}
