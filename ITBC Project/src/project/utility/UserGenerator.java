package project.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserGenerator {

	private static int number = 0;
	private static String[] columns = { "Email", "First Name", "Last Name", "Password", "Address", "City", "State",
			"ZIP", "Phone number" };

	public static void generateUserDataSheet() throws IOException, InvalidFormatException {

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Contacts");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.DARK_RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Create Other rows and cells with contacts data
		for (int i = 1; i < 31; i++) {
			Row row = sheet.createRow(i);
			String firstName = UserGenerator.firstName();
			String lastName = UserGenerator.lastName();
			row.createCell(0).setCellValue(UserGenerator.email(firstName, lastName));
			row.createCell(1).setCellValue(firstName);
			row.createCell(2).setCellValue(lastName);
			row.createCell(3).setCellValue(UserGenerator.password());
			row.createCell(4).setCellValue(UserGenerator.address());
			row.createCell(5).setCellValue(UserGenerator.city());
			row.createCell(6).setCellValue(UserGenerator.state());
			row.createCell(7).setCellValue(UserGenerator.zipCode());
			row.createCell(8).setCellValue(UserGenerator.phoneNum());

		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("src\\project\\utility\\userData\\contacts.xlsx");
		workbook.write(fileOut);
		fileOut.close();
	}

	private static String state() {
		Random choice = new Random();
		String[] states = { "California", "Illinois", "New York", "Texas", "Ohio", "Colorado", "Arizona", "Michigan",
				"Nevada" };
		List<String> names = Arrays.asList(states);
		Collections.shuffle(names);
		return names.get(choice.nextInt(9));
	}

	private static String firstName() {
		Random choice = new Random();
		String[] firstName = { "Dag", "Chriss", "Henry", "Isac", "Jasper", "Myles", "Robert", "Will", "Willy", "Jake",
				"Alan", "Ant" };
		List<String> peopleFirstName = Arrays.asList(firstName);
		return peopleFirstName.get(choice.nextInt(12));

	}

	private static String lastName() {
		Random choice = new Random();
		String[] lastName = { "Hanks", "Done", "Davon", "Johner", "Reeny", "Sinter", "Huge", "Stake" };
		List<String> peopleLastName = Arrays.asList(lastName);
		return peopleLastName.get(choice.nextInt(8));
	}

	private static String address() {
		Random choice = new Random();
		String[] address = { "Miami road", "Washington st.", "New York road", "San Francisco bvd.", "Miami st.",
				"Los Angeles pl.", "Chicago road", "Dalas st.", "Houston bvd." };
		List<String> addressForPeople = Arrays.asList(address);
		return addressForPeople.get(choice.nextInt(9)) + " " + choice.nextInt(65536);
	}

	private static String email(String firstName, String lastName) {
		Random choice = new Random();
		String[] providers = { "gmail.com", "hotmail.com", "yahoo.com", "gov.us", "aol.net", "verizon.com", "msn.com",
				"live.com" };
		List<String> provider = Arrays.asList(providers);
		String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + number + "@"
				+ provider.get(choice.nextInt(8));
		number++;
		return email;
	}

	private static String password() {
		Random choice = new Random();
		String password = "Pass" + choice.nextInt(900) + 100;
		return password;
	}

	private static String phoneNum() {
		Random choice = new Random();
		String phoneNum = "555" + (choice.nextInt(900000) + 100000);
		return phoneNum;
	}

	private static String zipCode() {
		Random choice = new Random();
		String zip = choice.nextInt(90000) + 10000 + "";
		return zip;
	}

	private static String city() {
		Random choice = new Random();
		String[] cityName = { "New York City", "Los Angeles", "Washington DC", "San Francisco", "Miami", "San Antonio",
				"Austin", "Seattle", "Boston", "Memphis" };
		List<String> city = Arrays.asList(cityName);
		return city.get(choice.nextInt(10));
	}
}