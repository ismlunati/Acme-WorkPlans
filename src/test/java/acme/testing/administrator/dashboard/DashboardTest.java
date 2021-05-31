package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;


public class DashboardTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	//Comprobamos que funcione correctamente el dashboard. Para ello accedemos como administrador y vamos al dashboard. Con el metodo locateOne(Byx.path()) 
	//conseguimos los valores del dashboard, accediendo a ellos a traves de las etiquetas del jsp (Table->Tr[1, 2, 3, ...]->Td, etiqueta donde se encuentra
	//el valor de la categoria del dashboard, y comprobamos que esos valores sean los esperados
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final String totalNumberPublicTask, final String totalNumberPrivateTask, final String totalNumberFinishedTask, final String totalNumberNonFinishedTask, final String averageTaskWorkload, 
		final String minTaskWorkload, final String maxTaskWorkload, final String deviationTaskWorkload, final String averageTaskExecutionPeriods, final String minTaskExecutionPeriods, final String maxTaskExecutionPeriods, 
		final String deviationTaskExecutionPeriods) {		
		
		super.signIn("administrator","administrator");
		this.navigateHome();
		super.clickOnMenu("Administrator", "Dashboard");
		assert super.getCurrentUrl().equals("/administrator/dashboard/show");
		
		
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[1]/td")).getText().equals(totalNumberPublicTask);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[2]/td")).getText().equals(totalNumberPrivateTask);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[3]/td")).getText().equals(totalNumberFinishedTask);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[4]/td")).getText().equals(totalNumberNonFinishedTask);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[5]/td")).getText().equals(averageTaskWorkload);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[6]/td")).getText().equals(minTaskWorkload);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[7]/td")).getText().equals(maxTaskWorkload);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[8]/td")).getText().equals(deviationTaskWorkload);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[9]/td")).getText().equals(averageTaskExecutionPeriods);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[10]/td")).getText().equals(minTaskExecutionPeriods);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[11]/td")).getText().equals(maxTaskExecutionPeriods);
		assert super.locateOne(By.xpath("//table[@id='dashboard']//tr[12]/td")).getText().equals(deviationTaskExecutionPeriods);
		
		super.signOut();
	}
	
	//dashboardNegativo:Comprobamos que no funcione el Dashboard bajo ciertas condiciones. Para ello intentamos acceder a el sin loggear.
	
	@Test
	public void dashboardNegativo() {
		super.navigate("/administrator/dashboard/show", null);
		super.checkErrorsExist();
	}

}

