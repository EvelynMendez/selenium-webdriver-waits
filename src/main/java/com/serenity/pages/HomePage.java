package com.serenity.pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;


public class HomePage extends PageObject{
	private static final Logger log = LoggerFactory.getLogger(HomePage.class);

	@FindBy(name = "search_query")
	WebElementFacade searchBox;

	@FindBy(name = "submit_search")
	WebElementFacade searchButton;

	@FindBy(xpath = "//div[@class='right-block']/h5/a[@class='product-name']")
	List <WebElementFacade> productList;

	@FindBy(xpath = "//span[@class='cat-name']")
	WebElementFacade subCategory;

	@FindBy(partialLinkText = "Sign in")
	WebElementFacade signIn_Link;
	
	public void productSearch(String searchKey) {
		searchBox.type(searchKey);
		searchButton.click();
	}

	public void launchApplication() {
		// Configura una espera implícita de 10 segundos
		getDriver().manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);
	
		// Informa que se está lanzando la aplicación.
		log.info("Launching the application.");
	
		// Abre la URL definida en la clase (método de Serenity PageObject).
		open();
	
		// Maximiza la ventana del navegador.
		getDriver().manage().window().maximize();
	}
	

	public List<String> productMatches() {
		List<String> products = new ArrayList<String>();
		int productCount = productList.size();
		log.info("Number of Product Listed: "+productCount);
		for(int i=0; i<productCount; i++) {
			products.add(productList.get(i).getText().toLowerCase());
		}
		return products;
	}

	public void selectCategory(String category) {
		// Informa en el log qué categoría se va a seleccionar.
		log.info("Selecting Category " + category);
	
		// Espera explícita hasta que el enlace con el texto de la categoría (en mayúsculas) sea visible.
		waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(By.linkText(category.toUpperCase())));
	
		// Una vez visible, realiza el clic en el enlace de la categoría.
		getDriver().findElement(By.linkText(category.toUpperCase())).click();
	}
	

	public void selectSubCategory(String subCategory) {
		log.info("Selecting Sub-Category "+subCategory);
		getDriver().findElement(By.linkText(subCategory.toUpperCase())).click();
	}

	public String validateSubCategory() {
		return subCategory.getText().trim();
	}

	public void clickOnSignInLink() {
		// Espera explícita: aguarda hasta que el enlace "Sign In" esté clicable.
		waitForCondition().until(ExpectedConditions.elementToBeClickable(signIn_Link));
	
		// Una vez clicable, realiza el clic en el enlace "Sign In".
		signIn_Link.click();
	}
	
}
