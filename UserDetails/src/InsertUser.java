import java.sql.*;
import java.util.Scanner;

public class InsertUser {
	
	public static void insertUser() {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Your ID : ");
		int user_id = input.nextInt();
		input.nextLine();
		
		System.out.println("Enter Your Name : ");
		String user_name = input.nextLine();
		
		System.out.println("Enter Your Email : ");
		String user_email = input.nextLine();
		if(!user_email.contains("@gmail.com")) {
			System.out.println("invalid email. email must contains '@gmail.com'");
			return;
		}
		
		System.out.println("Enter password(6-12 digits) : ");
		String user_password = input.nextLine();
		int password_length = user_password.length();
		if(password_length<6 && password_length>12) {
			System.out.println("Password must be contains 6 to 12 digits only");
			return;
		}
		
		System.out.println("Enter Your Age(minimum age requirement is 18) : ");
		int user_age = input.nextInt();
		if(user_age<18) {
			System.out.println("Minimum Age Requirement is 18 ");
			return;
		}
		
		System.out.println("Select Your Gender(1 / 2) :");
		System.out.println("1. Male");
		System.out.println("2. Female");
		int select_gender = input.nextInt();
		String user_gender;
		if(select_gender==1) {
			user_gender="male";
		}else if(select_gender==2) {
			user_gender="female";
		}else {
			System.out.println("invalid input");
			return;
		}
		
		System.out.println("Enter Your Height (in Cm) : ");
		int user_height = input.nextInt();
		
		System.out.println("Enter Your Weight (in Kg) : ");
		int user_weight = input.nextInt();
		
		System.out.println("Select Your Activity Level (1 / 2 / 3) : ");
		System.out.println("1. Sedentary ");
		System.out.println("2. Moderate ");
		System.out.println("3. Active ");
		int select_act = input.nextInt();
		String activity_level;
		if(select_act == 1) {
			activity_level="Sedentary";
		}else if(select_act == 2) {
			activity_level="Moderate";
		}else if(select_act == 3) {
			activity_level="Active";
		}else {
			System.out.println("invalid input ");
			return;
		}
		
		System.out.println("Enter Your DOB (YYYY-MM-DD) : ");
		String B_date = input.next();
		
		
		System.out.println("Enter Contact Number: ");
        long user_phone = input.nextLong();
        if (String.valueOf(user_phone).length() != 10) {
            System.out.println("Invalid phone number. It must contain 10 digits.");
            return;
        }
		
		try {	
			String url = "jdbc:mysql://127.0.0.1:3306/user";
			String username = "root";
			String password = "6670";
			String Query = "insert into details values(?,?,?,?,?,?,?,?,?,?,?)";
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement pst = con.prepareStatement(Query);
			pst.setInt(1, user_id);
			pst.setString(2, user_name);
			pst.setString(3, user_email);
			pst.setString(4, user_password);
			pst.setInt(5, user_age);
			pst.setString(6, user_gender);
			pst.setInt(7, user_height);
			pst.setInt(8, user_weight);
			pst.setString(9, activity_level);
			pst.setDate(10, Date.valueOf(B_date));
			pst.setLong(11, user_phone);
			pst.executeUpdate();
			con.close();
			pst.close();
			input.close();
			System.out.println("Successfully Entered All the Details");
			
		}
		catch(SQLException e){
			System.err.println("SQL Exceptions "+ e.getMessage());
		}
		
	}
	
	public static void main(String []args) throws Exception {
			insertUser();
	}

}