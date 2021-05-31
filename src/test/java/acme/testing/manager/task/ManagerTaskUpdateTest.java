package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTest extends AcmePlannerTest {
	
	// Test cases -------------------------------------------------------------
	
	/*
	 * Característica "acme.features.manager.task.ManagerTaskUpdateService"
	 * 
	 * Este test se encarga de comprobar que el servicio Update de la entidad Task funcione correctamente,
	 * entrando en la Task, editando los datos según los del csv y comprobando que estos cambios se han realizado con éxito
	 */
	
		@ParameterizedTest
		@CsvFileSource(resources = "/manager/task/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	
		public void updatePositive(final int recordIndex,  final String title, final String initialMoment, final String finalMoment, final String workload, final String description) {	
			super.signIn("manager2", "manager2");
			
			super.clickOnMenu("Manager", "List my tasks");		
			
			super.clickOnListingRecord(recordIndex);
			
			super.fillInputBoxIn("title", title);
			super.fillInputBoxIn("initialMoment", initialMoment);
			super.fillInputBoxIn("finalMoment", finalMoment);
			super.fillInputBoxIn("workload", workload);
			super.fillInputBoxIn("description", description);	
			
			super.clickOnSubmitButton("Update");
			
			super.checkColumnHasValue(recordIndex, 0, title);
			super.checkColumnHasValue(recordIndex, 1, initialMoment);
			super.checkColumnHasValue(recordIndex, 2, finalMoment);
			super.checkColumnHasValue(recordIndex, 3, workload);
			super.checkColumnHasValue(recordIndex, 4, description);
			
			super.clickOnListingRecord(recordIndex);
			
			super.signOut();
		}
		
		/*
		 * Característica "acme.features.manager.task.ManagerTaskUpdateService"
		 * 
		 * Este test se encarga de comprobar que el servicio Update de la entidad Task funcione incorrectamente,
		 * entrando en la Task, editando los datos según los del csv y comprobando que estos cambios generan un error
		 */
		
		@ParameterizedTest
		@CsvFileSource(resources = "/manager/task/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)	
		public void updateNegative(final int recordIndex, final String title, final String initialMoment, final String finalMoment, final String workload, final String description) {		
			super.signIn("manager2", "manager2");
			
			super.clickOnMenu("Manager", "List my tasks");		
			
			
			super.clickOnListingRecord(recordIndex);
			
			super.fillInputBoxIn("title", title);
			super.fillInputBoxIn("initialMoment", initialMoment);
			super.fillInputBoxIn("finalMoment", finalMoment);
			super.fillInputBoxIn("workload", workload);
			super.fillInputBoxIn("description", description);	
			
			super.clickOnSubmitButton("Update");
			
			super.checkErrorsExist();
			
			super.signOut();
		}
}
