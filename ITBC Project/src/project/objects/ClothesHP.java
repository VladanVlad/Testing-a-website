package project.objects;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ClothesHP {
	public static final String HOME_URL = "http://automationpractice.com/index.php";

	public static void navigateToHomePage(WebDriver wd) {
		wd.get(HOME_URL);
	}

	public static void navigateToSummerDresses(WebDriver wd) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd.navigate().to(HOME_URL);
		Actions action = new Actions(wd);
		action.moveToElement(wd.findElement(By.xpath("//a[@class='sf-with-ul'][contains(text(),'Women')]"))).perform();
		wd.findElement(By.xpath("//li[@class='sfHover']//ul//li//a[contains(text(),'Summer Dresses')]")).click();
	}

	public static void navigateToSummerDressesFromDresses(WebDriver wd) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd.navigate().to(HOME_URL);
		Actions action = new Actions(wd);
		action.moveToElement(wd.findElement(By.xpath(
				"//body[@id='index']/div[@id='page']/div[@class='header-container']/header[@id='header']/div/div[@class='container']/div[@class='row']/div[@id='block_top_menu']/ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[2]/a[1]")))
				.perform();
		wd.findElement(By.xpath(
				"//li[@class='sfHover']//ul[@class='submenu-container clearfix first-in-line-xs']//li//a[contains(text(),'Summer Dresses')]"))
				.click();
	}

	public static void navigateToCasualDresses(WebDriver wd) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd.navigate().to(HOME_URL);
		Actions action = new Actions(wd);
		action.moveToElement(wd.findElement(By.xpath(
				"//body[@id='index']/div[@id='page']/div[@class='header-container']/header[@id='header']/div/div[@class='container']/div[@class='row']/div[@id='block_top_menu']/ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[2]/a[1]")))
				.perform();
		wd.findElement(By.xpath(
				"//li[@class='sfHover']//ul[@class='submenu-container clearfix first-in-line-xs']//li//a[contains(text(),'Casual Dresses')]"))
				.click();
	}

	public static void navigateToEveningDresses(WebDriver wd) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd.navigate().to(HOME_URL);
		Actions action = new Actions(wd);
		action.moveToElement(wd.findElement(By.xpath(
				"//body[@id='index']/div[@id='page']/div[@class='header-container']/header[@id='header']/div/div[@class='container']/div[@class='row']/div[@id='block_top_menu']/ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[2]/a[1]")))
				.perform();
		wd.findElement(By.xpath("//li[@class='sfHover']//a[contains(text(),'Evening Dresses')]")).click();
	}
}
