package com.clearqa.test;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
	private static HashMap<Long, WebDriver> map = new HashMap<Long, WebDriver>();
	
	public static WebDriver getDriverInstance() {
		WebDriver d = map.get(Thread.currentThread().getId());
		return d;
	}
	
	public static WebDriver startDriver(String type) {
		WebDriver d;
		if(type.equalsIgnoreCase("chrome")){
			d = new ChromeDriver();
			map.put(Thread.currentThread().getId(), d);
		} else if(type.equalsIgnoreCase("firefox")) {
			d = new FirefoxDriver();
			map.put(Thread.currentThread().getId(), d);
		} else {
			throw new IllegalArgumentException("Browser type not supported: " + type);
		}
		return d;
	}
	
	public static void stopDriver() {
		WebDriver d = map.get(Thread.currentThread().getId());
		d.close();
	}
}