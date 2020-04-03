package project.objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import project.utility.UserData;

public class Registration {

	public static String user;

	public static void registerUsers(WebDriver wd) {
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wd.findElement(By.className("login")).click();
	}

	public static String loginUsers(WebDriver wd, int userID) throws IOException, InvalidFormatException {
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		FileInputStream readingFile = new FileInputStream("src\\project\\utility\\userData\\contacts.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(readingFile);
		XSSFSheet sheet = wb.getSheetAt(0);
		String[] userInfo = new String[sheet.getRow(0).getPhysicalNumberOfCells()];
		for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++)
			userInfo[i] = sheet.getRow(userID).getCell(i).getStringCellValue(); // Ucitavanje jednog korisnika iz excela
		wd.findElement(By.className("login")).click();
		wd.findElement(By.id("email")).sendKeys(userInfo[0]);
		wd.findElement(By.id("passwd")).sendKeys(userInfo[3]);
		wd.findElement(By.id("SubmitLogin")).click();
		user = " " + userInfo[1] + " " + userInfo[2];
		String checkRegistration = wd.findElement(By.className("logout")).getText();
		String checkName = wd.findElement(By.className("account")).getText();
		signOut(wd);
		return checkRegistration + " " + checkName;

	}

	public static String getName() {
		return user;
	}

	public static void signOut(WebDriver wd) {
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wd.findElement(By.className("logout")).click();
	}

	public static String registerUser(WebDriver wd) {
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wd.findElement(By.id("email_create")).sendKeys("johnnydoe105@yahoo.com");
		wd.findElement(By.id("SubmitCreate")).click();
		wd.findElement(By.id("id_gender1")).click();
		wd.findElement(By.id("customer_firstname")).sendKeys("Johnny");
		wd.findElement(By.id("customer_lastname")).sendKeys("Doe");
		wd.findElement(By.name("passwd")).sendKeys("MyPassword13");
		wd.findElement(By.name("address1")).sendKeys("107 Highland Place");
		wd.findElement(By.name("city")).sendKeys("San Francisco");
		wd.findElement(By.xpath("//select[@id='id_state']")).sendKeys("California");
		wd.findElement(By.id("postcode")).sendKeys("59057");
		wd.findElement(By.name("phone_mobile")).sendKeys("555-013-9879");
		wd.findElement(By.name("alias")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "107 Highland Place");
		wd.findElement(By.id("submitAccount")).click();
		String checkRegistration = wd.findElement(By.className("logout")).getText();
		String checkName = wd
				.findElement(By.xpath("//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']"))
				.getText();
		return checkRegistration + " " + checkName;
	}

}
