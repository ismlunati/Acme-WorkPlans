package acme.testing.authenticated.manager;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateTest extends AcmePlannerTest {
	
	// Test cases -------------------------------------------------------------
	
	
	
	/*
	 * Características "acme.features.authenticated.manager"
	 * 
	 * Comprobamos que la opción de "Become a manager" funcione correctamente.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources= "/authenticated.manager/manager.csv", encoding= "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void create(final String username, final String password, final String name, final String surname, final String email,  final String phone) {		
		
		super.signUp(username, password, name, surname, email, phone);
		
		super.signIn(username, password);
				
		super.clickOnMenu("Account", "Become a manager");

		super.clickOnSubmitButton("Register");
		
		super.signOut();

	}

}
