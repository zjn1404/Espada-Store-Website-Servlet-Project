package model;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
	private String customerId;
	private String name;
	private String gender;
	private String address;
	private String deliveryAddress;
	private LocalDate dob;
	private String phoneNumber;
	private String email;
	private String userName;
	private String password;
	private boolean registerToGetMail;
	
	public Customer() {}
	
	public Customer(String customerId, String name, String gender, String address, String deliveryAddress,
			LocalDate dob, String phoneNumber, String email, String userName, String password, boolean registerToGetMail) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.deliveryAddress = deliveryAddress;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.registerToGetMail = registerToGetMail;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isRegisterToGetMail() {
		return registerToGetMail;
	}

	public void setRegisterToGetMail(boolean registerToGetMail) {
		this.registerToGetMail = registerToGetMail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(customerId, other.customerId);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", gender=" + gender + ", address=" + address
				+ ", deliveryAddress=" + deliveryAddress + ", dob=" + dob + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", userName=" + userName + ", password=" + password + ", registerToGetMail="
				+ registerToGetMail + "]";
	}
	
}
