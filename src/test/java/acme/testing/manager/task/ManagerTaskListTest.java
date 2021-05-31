package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskListTest extends AcmePlannerTest {
	// Test cases -------------------------------------------------------------

	/*
	 * Característica "acme.features.manager.task.ManagerTaskListMineService"
	 * 
	 * Este test se encarga de comprobar que el servicio ListMine de la entidad Task funcione correctamente,
	 * comprobando que cada columna del listado coincida con los datos pasados por el archivo csv.
	 */
	
	@ParameterizedTest		
	@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String title, final String initialMoment, final String finalMoment, final String workload, final String description) {		
	
		super.signIn("manager2", "manager2");
	
		super.clickOnMenu("Manager", "List my tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, initialMoment);
		super.checkColumnHasValue(recordIndex, 2, finalMoment);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, description);
	
		super.signOut();
	}
}
