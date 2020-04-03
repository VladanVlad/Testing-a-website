package project.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import project.objects.BuyClothes;
import project.objects.ClothesHP;
import project.objects.Registration;
import project.utility.DressesRegisterUsers;
import project.utility.UserData;
import project.utility.UserGenerator;

import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainProgram {
	WebDriver wd = new ChromeDriver();
	SoftAssert sa = new SoftAssert();

	@Test
	public void testiranjeURLSummerDresses() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ClothesHP.navigateToHomePage(wd);
		ClothesHP.navigateToSummerDresses(wd);
		String URL = wd.getCurrentUrl();
		Assert.assertEquals(URL, BuyClothes.summerDresses);
	}

	@Test
	public void testiranjeSvihURL() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ClothesHP.navigateToHomePage(wd);
		ClothesHP.navigateToCasualDresses(wd);
		String URL = wd.getCurrentUrl();
		sa.assertEquals(URL, BuyClothes.casualDresses);
		ClothesHP.navigateToEveningDresses(wd);
		URL = wd.getCurrentUrl();
		sa.assertEquals(URL, BuyClothes.eveningDresses);
		ClothesHP.navigateToSummerDressesFromDresses(wd);
		URL = wd.getCurrentUrl();
		sa.assertEquals(URL, BuyClothes.summerDresses);
		sa.assertAll();
	}

	@Test
	public void addToCart() {
		ClothesHP.navigateToHomePage(wd);
		BuyClothes.buySummerDresses(wd, 1);
		String chosenDress = BuyClothes.settingParametersForDresses(wd, 2, "M", "blue");
		System.out.println(chosenDress);
		sa.assertEquals(chosenDress, "Color : Blue, Size : M 2");
		BuyClothes.proceedToCheckout(wd);
		sa.assertEquals(wd.getCurrentUrl(), BuyClothes.checkoutURL);
		sa.assertAll();
	}

	@Test
	public void registerUser() {
		ClothesHP.navigateToHomePage(wd);
		BuyClothes.buySummerDresses(wd, 1);
		BuyClothes.settingParametersForDresses(wd, 2, "M", "blue");
		BuyClothes.proceedToCheckout(wd);
		String checkRegistration = Registration.registerUser(wd);
		sa.assertEquals(checkRegistration, "Sign out Johnny Doe");
		sa.assertAll();
	}

	@Test
	public void generateUsers() throws IOException, InvalidFormatException {
		UserGenerator.generateUserDataSheet();
		ClothesHP.navigateToHomePage(wd);
		Registration.registerUsers(wd);
		String checkRegistration;
		for (int i = 1; i < 31; i++) {
			checkRegistration = UserData.registerUser(wd, i);
			sa.assertEquals(checkRegistration, "Sign out" + UserData.getName());
			System.out.println(checkRegistration);
			System.out.println(UserData.getName());
		}
		sa.assertAll();
	}

	@Test
	public void logInUsers() throws IOException, InvalidFormatException {
		ClothesHP.navigateToHomePage(wd);
		String checkLogin;
		for (int i = 1; i < 31; i++) {
			checkLogin = Registration.loginUsers(wd, i);
			sa.assertEquals(checkLogin, "Sign out" + Registration.getName());
		}
		sa.assertAll();
	}
}
