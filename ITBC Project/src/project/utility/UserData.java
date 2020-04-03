package project.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import project.objects.Registration;

public class UserData {

	private static String userName;
	private static int userNumber;

	private static int userInRow() {
		userNumber++;
		return userNumber;
	}

	public static String getName() {
		return userName;
	}

	public static String registerUser(WebDriver wd, int userID) throws IOException, InvalidFormatException {
		FileInputStream readingFile = new FileInputStream("src\\project\\utility\\userData\\contacts.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(readingFile);
		XSSFSheet sheet = wb.getSheetAt(0);
		String[] userInfo = new String[sheet.getRow(0).getPhysicalNumberOfCells()];
		for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++)
			userInfo[i] = sheet.getRow(userID).getCell(i).getStringCellValue(); // Ucitavanje jednog korisnika iz excela
		int j = 0;
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wd.findElement(By.id("email_create")).sendKeys(userInfo[j++]);
		wd.findElement(By.id("SubmitCreate")).click();
		wd.findElement(By.id("id_gender1")).click();
		wd.findElement(By.id("customer_firstname")).sendKeys(userInfo[j++]);
		wd.findElement(By.id("customer_lastname")).sendKeys(userInfo[j++]);
		wd.findElement(By.name("passwd")).sendKeys(userInfo[j++]);
		wd.findElement(By.name("address1")).sendKeys(userInfo[j++]);
		wd.findElement(By.name("city")).sendKeys(userInfo[j++]);
		wd.findElement(By.xpath("//select[@id='id_state']")).sendKeys(userInfo[j++]);
		wd.findElement(By.id("postcode")).sendKeys(userInfo[j++]);
		wd.findElement(By.name("phone_mobile")).sendKeys(userInfo[j++]);
		wd.findElement(By.name("alias")).sendKeys(Keys.chord(Keys.CONTROL, "a"), userInfo[4]);
		wd.findElement(By.id("submitAccount")).click();
		userName = " " + userInfo[1] + " " + userInfo[2];
		String checkRegistration = wd.findElement(By.className("logout")).getText();
		String checkName = wd.findElement(By.className("account")).getText();
		Registration.signOut(wd);
		return checkRegistration + " " + checkName;

	}

}