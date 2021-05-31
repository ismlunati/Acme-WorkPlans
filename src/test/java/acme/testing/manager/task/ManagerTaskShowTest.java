package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskShowTest extends AcmePlannerTest {
	
	// Test cases -------------------------------------------------------------

	/*
	 * Característica "acme.features.manager.task.ManagerTaskShowService"
	 * 
	 * Este test se encarga de comprobar que el servicio Show de la entidad Task funcione correctamente,
	 * entrando en la Task y comprobando que cada input box tenga el contenido que debe (según el csv)
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void show(final int recordIndex, final String title, final String initialMoment, final String finalMoment, final String workload, final String description) {		
		super.signIn("manager2", "manager2");

		super.clickOnMenu("Manager", "List my tasks");

		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("initialMoment", initialMoment);
		super.checkInputBoxHasValue("finalMoment", finalMoment);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);

		super.signOut();
	}
}
