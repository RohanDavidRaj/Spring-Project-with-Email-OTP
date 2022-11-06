package com.ty.springmapping;

public class EmailMessage {
	
	public static String getMess(String empName,String empId) {
		
		String mss="\r\n"
				+ "Hello "+empName+",\r\n"
				+ "\r\n"
				+ "Thank you for joining your Employee ID is"+empId+".\r\n"
				+ "\r\n"
				+ "We’d like to confirm that your account was created successfully.\r\n"
				+ "\r\n"
				+ "If you experience any issues logging into your account, reach out to us at rohandavid@gmail.com \".\r\n"
				+ "\r\n"
				+ "Best,\r\n"
				+ "By Rohan Raj team";
		
		return mss;
		
	}
	public static void main(String[] args) {
		
	}

}
