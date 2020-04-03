package project.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DressesRegisterUsers {

	public String email, gender, firstName, lastName, password, DOB, address, city, state, POC, phoneNumber;

	public DressesRegisterUsers(String email, String gender, String firstName, String lastName, String password,
			String dOB, String address, String city, String state, String pOC, String phoneNumber) {
		this.email = email;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		DOB = dOB;
		this.address = address;
		this.city = city;
		this.state = state;
		POC = pOC;
		this.phoneNumber = phoneNumber;
	}

}
