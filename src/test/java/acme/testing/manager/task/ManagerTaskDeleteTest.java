package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest{
	
	
	
	//deletePositivo: comprobamos que funcione el metodo delete de Manager Task. Para ello nos logeamos como manager2, vamos a list my task en el menu de
	//manager, accedemos al elemento numero 5 de la lista, lo borramos, y accedemos de nuevo al elemento 5 de la lista (que como el 5 ha sido borrado, el 
	//nuevo elemento 5 de la lista será el que inicialmente era el 6.
	//Para delete no hay ningún caso negativo ya que la acción simplemente es borrar al presionar el botón correspondiente.
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/deletePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositivo(final int recordIndex, final String title, final String initialMoment, final String finalMoment, final String workload,
		final String description, final String newTitle, final String newInitialMoment, final String newFinalMoment, final String newWorkload,
		final String newDescription) {
		
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "List my tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, initialMoment);
		super.checkColumnHasValue(recordIndex, 2, finalMoment);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, description);
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmitButton("Delete");
		
		super.clickOnMenu("Manager", "List my tasks");
		
		super.checkColumnHasValue(recordIndex, 0, newTitle);
		super.checkColumnHasValue(recordIndex, 1, newInitialMoment);
		super.checkColumnHasValue(recordIndex, 2, newFinalMoment);
		super.checkColumnHasValue(recordIndex, 3, newWorkload);
		super.checkColumnHasValue(recordIndex, 4, newDescription);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", newTitle);
		super.checkInputBoxHasValue("initialMoment", newInitialMoment);
		super.checkInputBoxHasValue("finalMoment", newFinalMoment);
		super.checkInputBoxHasValue("workload", newWorkload);
		super.checkInputBoxHasValue("description", newDescription);
		
		super.signOut();
	}
	


}

