package com.serenity.pages;

import java.time.Duration;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends PageObject{
	private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
	
	@FindBy(name = "email_create")
	WebElementFacade createAccountEmail;
	
	@FindBy(name ="SubmitCreate")
	WebElementFacade createAccount_Button;
	
	@FindBy(name ="email")
	WebElementFacade registeredEmail;
	
	@FindBy(name ="passwd")
	WebElementFacade password;
	
	@FindBy(name ="SubmitLogin")
	WebElementFacade login_Button;

	public LoginPage() {
		log.info("Initializing Login Page");
	
	}
	
	public void enterEmailToCreateAccount(String email) {
	// Se configura una FluentWait que permitirá una espera explícita más flexible, definiendo tiempos, intervalos de consulta y excepciones a ignorar.
		Wait<WebDriver> wait =
        new FluentWait<>(getDriver())
			// Indica el tiempo máximo de espera antes de lanzar una excepción.
            .withTimeout(Duration.ofSeconds(4))
			// Define el intervalo de tiempo entre cada intento de comprobar la condición.
            .pollingEvery(Duration.ofMillis(600))
			// Ignora la excepción ElementNotInteractableException durante el periodo de espera,
    		// lo que evita fallos prematuros cuando un elemento aún no está listo para interactuar.
            .ignoring(ElementNotInteractableException.class);

        // Usamos la espera para ejecutar una función lambda que intente enviar el correo al campo "createAccountEmail".
		// "wait.until()" continuará reintentando la operación hasta que la función devuelva true o hasta que se alcance el timeout.
		log.info("Creating account with "+email);

		wait.until(
        d -> {
			createAccountEmail.sendKeys(email);
          return true;
        });
		
	}
	
	public void clickOnCreateAccountButton() {
		createAccount_Button.click();
	}

	public void enterEmailToSignin(String email) {
		registeredEmail.type(email);
	}
	
	public void enterPassword(String password) {
		this.password.type(password);
	}
	
	public void clickOnLoginButton() {
		login_Button.click();
	}
	
}
