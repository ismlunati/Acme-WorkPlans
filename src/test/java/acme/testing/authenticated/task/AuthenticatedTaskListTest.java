package acme.testing.authenticated.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListTest extends AcmePlannerTest {
	
	// Test cases -------------------------------------------------------------
	
	
	
	/*
	 * Características "acme.features.authenticated.task.AuthenticatedTaskListService" y
	 * "acme.features.authenticated.task.AuthenticatedTaskShowService"
	 * 
	 * Se comprueba que coincida cada elemento con el esperado de la lista de tareas.
	 * Debido a que todas la entradas de la lista coinciden con las del fichero csv, no se producirá ningún error.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources= "/authenticated.task/positive.csv", encoding= "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex, final String Title, final String Beginning, final String End, final String Workload, final String Description, final String isPublic) {
		
		super.signIn("authenticated", "authenticated");
		
		super.clickOnMenu("Authenticated", "List tasks");
		
		super.checkColumnHasValue(recordIndex, 0, Title);
		super.checkColumnHasValue(recordIndex, 1, Beginning);
		super.checkColumnHasValue(recordIndex, 2, End);
		super.checkColumnHasValue(recordIndex, 3, Workload);
		
		super.clickOnListingRecord(recordIndex);
		
		
		super.checkInputBoxHasValue("title", Title);
		super.checkInputBoxHasValue("initialMoment", Beginning);
		super.checkInputBoxHasValue("finalMoment", End);
		super.checkInputBoxHasValue("workload", Workload);
		super.checkInputBoxHasValue("description", Description);
		super.checkInputBoxHasValue("isPublic", isPublic);
		
		super.signOut();
		
	}

}
