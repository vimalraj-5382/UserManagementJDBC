import java.sql.*;
import java.util.Scanner;
public class DeleteUser {

	public static void deleteUser() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter User ID to Delete User's Details ");
		int userID = input.nextInt();
		
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/user";
			String username = "root";
			String password = "6670";
			String Query = "delete from details where userID="+userID;
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement pst = con.prepareStatement(Query);
			pst.executeUpdate();
			pst.close();
			System.out.println("Successfully Deleted");
			input.close();
		}catch(SQLException e) {
			System.err.println("SQL Exception "+ e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		try {
			deleteUser();
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
