import java.sql.*;
import java.util.Scanner;
public class GetUser {
	
	public static void getUser() {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter User ID to Get User's Details ");
		int userID = input.nextInt();
		
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/user";
			String username = "root";
			String password = "6670";
			String Query = "select * from details where userID="+userID+";";
			Connection con = DriverManager.getConnection(url,username,password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(Query);
			while(rs.next()) {
				System.out.println("User id : "+ rs.getInt(1));
				System.out.println("User Name : "+ rs.getString(2));
				System.out.println("Email : "+ rs.getString(3));
				System.out.println("Password : "+ rs.getString(4));
				System.out.println("Age : "+ rs.getInt(5));
				System.out.println("Gender : "+ rs.getString(6));
				System.out.println("Height : "+ rs.getInt(7));
				System.out.println("Weight : "+ rs.getInt(8));
				System.out.println("Activity Level : "+ rs.getString(9));
				System.out.println("Date Of Birth : "+ rs.getDate(10));
				System.out.println("Contact number : "+ rs.getLong(11));		
			}
			con.close();
			input.close();
		}catch(SQLException e) {
			System.err.println("SQL Exception "+ e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		try {
			getUser();
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
