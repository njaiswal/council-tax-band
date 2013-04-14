package com.clearqa.test;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.clearqa.test.ScreenShotOnFailure.class })
public class CTaxBandTest {
		
  @BeforeClass
  public void oneTimeSetUp() {
	  WebDriverManager.startDriver();
  }
  
  @AfterClass
  public void oneTimeTearDown() {
	  WebDriverManager.stopDriver();
  }
  
  @DataProvider(name = "dataset1")
  public Object [][] create_dataset1() {
	  return new Object[][] {
			  {"RG5 4NF", "1, Concorde Way", "F"},
			  {"RG5 4NF", "2, Concorde Way", "C"}
	  };
  }
  
  @Test(dataProvider = "dataset1")
  public void testCTaxBands(String postcode, String addr_first_line, String expected_band) {
	  WebDriver d = WebDriverManager.getDriverInstance();
	  d.get("http://www.voa.gov.uk/cti/InitS.asp");
	  CTaxBandSearch search_page = new CTaxBandSearch(d);
	  String band = search_page.search(postcode, addr_first_line);
	  Assert.assertEquals(expected_band, band);
  }
}