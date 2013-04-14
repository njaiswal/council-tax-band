package com.clearqa.test;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CTaxBandSearch
{
	private WebDriver driver;
	private Properties prop = new Properties();
	
	public CTaxBandSearch(WebDriver d) {
		this.driver = d;
		if(!driver.getTitle().equals("Search for your Council Tax band")) {
			throw new IllegalStateException("This is not Council Tax band search page. It title is: " + driver.getTitle());
		}
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("ui_mapping.properties"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public String search(String postcode, String first_addr_line) {
	    driver.findElement(By.id(prop.getProperty("ctband.postcode_search"))).clear();
	    driver.findElement(By.id(prop.getProperty("ctband.postcode_search"))).sendKeys(postcode);
	    driver.findElement(By.xpath(prop.getProperty("ctband.search_button_xpath"))).click();
	    driver.findElement(By.partialLinkText(first_addr_line.toUpperCase())).click();
	    WebElement e = driver.findElement(By.xpath(prop.getProperty("ctband.result_table_xpath")));
	    return e.getText();
	}
}