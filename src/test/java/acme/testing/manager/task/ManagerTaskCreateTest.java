package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest{
	
	//createPositivo: comprobamos que funcione el metodo create de Manager Task. Para ello nos logeamos como manager1, vamos a create task en el menu de
	//manager, rellenamos el formulario, accedemos a list my task y comprobamos que los valores de la task que hay sean los mismo que la que hemos añadido
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String initialMoment, final String finalMoment, final String workload,
		final String description) {
		
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "Create Task");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("initialMoment", initialMoment);
		super.fillInputBoxIn("finalMoment", finalMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.clickOnSubmitButton("Create");

		super.clickOnMenu("Manager", "List my tasks");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, initialMoment);
		super.checkColumnHasValue(recordIndex, 2, finalMoment);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, description);
		
		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("initialMoment", initialMoment);
		super.checkInputBoxHasValue("finalMoment", finalMoment);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		
		super.signOut();		
	}
	
	//createNegativo: comprobamos fallo del metodo create de Manager Task. Para ello nos logeamos como manager1, vamos a create task en el menu de
	//manager, rellenamos el formulario con distintos errores especificados en la descripcion y comprobamos que de mal.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativo(final int recordIndex, final String title, final String initialMoment, final String finalMoment, final String workload,
		final String description) {
		
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "Create Task");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("initialMoment", initialMoment);
		super.fillInputBoxIn("finalMoment", finalMoment);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("workload", workload);
		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();
		super.signOut();
		
	} 

}
