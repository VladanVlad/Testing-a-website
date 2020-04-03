package project.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BuyClothes {

	public static final String summerDresses = "http://automationpractice.com/index.php?id_category=11&controller=category";
	public static final String eveningDresses = "http://automationpractice.com/index.php?id_category=10&controller=category";
	public static final String casualDresses = "http://automationpractice.com/index.php?id_category=9&controller=category";
	public static final String checkoutURL = "http://automationpractice.com/index.php?controller=authentication&multi-shipping=0&display_guest_checkout=0&back=http%3A%2F%2Fautomationpractice.com%2Findex.php%3Fcontroller%3Dorder%26step%3D1%26multi-shipping%3D0";
											 
	public static void buySummerDresses(WebDriver wd, int picNum) {
		ClothesHP.navigateToSummerDresses(wd);
		wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		List<WebElement> summerDresses = wd.findElements(By.className("product_img_link"));
		if (picNum > summerDresses.size()) {
			System.out.println("We have " + summerDresses.size() + " dresses in out 'Summer Dresses' catalogue");
		} else
			summerDresses.get(picNum - 1).click();
	}

	public static String settingParametersForDresses(WebDriver wd, int amount, String size, String color) {
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		for (int i = 1; i < amount; i++)
			wd.findElement(By.className("icon-plus")).click();
		Select dressSize = new Select(wd.findElement(By.id("group_1")));
		dressSize.selectByVisibleText(size);
		List<WebElement> colors = wd.findElements(By.className("color_pick"));
		int dressColor = choosingColor(color);
		colors.get(dressColor).click();
		wd.findElement(By.name("Submit")).click();
		wd.findElement(By.xpath("//a[@class='btn btn-default button button-medium']")).click();
		WebElement stuff = wd.findElement(By.linkText("Color : Blue, Size : M"));
		String num = numberOrdered(wd, amount);
		return stuff.getText() + " " + num;
	}

	public static void proceedToCheckout(WebDriver wd) {
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wd.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']")).click();
	}

	private static String numberOrdered(WebDriver wd, int amount) {
		WebElement webE = wd.findElement(By.xpath("//input[@name='quantity_5_24_0_0']"));
		String num = webE.getAttribute("value");
		return num;
	}

	private static int choosingColor(String color) {
		String dressColor = color.toLowerCase();
		switch (dressColor) {
		case "black":
			return 0;
		case "orange":
			return 1;
		case "blue":
			return 2;
		case "yellow":
			return 3;
		default:
			return 2;
		}
	}
}
