import java.sql.*;
import java.util.Scanner;

public class UpdateUser {

    public static void updateUser() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter User Field that you want to Update:");
        System.out.println("1. user_ID / 2. user Name");
        System.out.println("3. Email / 4. Password");
        System.out.println("5. Age / 6. Gender");
        System.out.println("7. Height / 8. Weight");
        System.out.println("9. Activity Level / 10. Date of Birth");
        System.out.println("11. Contact Number");
        int user_field_num = input.nextInt();
        input.nextLine();


        String user_field;
		switch(user_field_num) {
		case 1:
			user_field="userID";
			break;
		case 2:
			user_field="fullName";
			break;
		case 3:
			user_field="email";
			break;
		case 4:
			user_field="password";
			break;
		case 5:
			user_field="age";
			break;
		case 6:
			user_field="gender";
			break;
		case 7:
			user_field="height";
			break;
		case 8:
			user_field="weight";
			break;
		case 9:
			user_field="activityLevel";
			break;
		case 10:
			user_field="dateOfBirth";
			break;
		case 11:
			user_field="contact";
			break;
		default:
			System.out.println("invalid input");
			return;
		}

        System.out.println("Enter User ID to Update User's Details:");
        int user_id = input.nextInt();
        input.nextLine();

        System.out.println("Enter the new value for " + user_field + ":");
        String updated_value = input.nextLine();


        if ("email".equals(user_field)) {
            if (!updated_value.contains("@gmail.com")) {
                System.out.println("Invalid email. Email must contain '@gmail.com'.");
                return;
            }
        } else if ("height".equals(user_field) || "weight".equals(user_field)) {
            try {
                Integer.parseInt(updated_value);
            } catch (NumberFormatException e) {
                System.out.println(user_field + " must be a number.");
                return;
            }
        } else if ("contact".equals(user_field)) {
            try {
                if (updated_value.length() != 10 || !updated_value.matches("\\d+")) {
                    System.out.println("Invalid phone number. It must contain exactly 10 digits.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Contact must be a number.");
                return;
            }
        } else if ("age".equals(user_field)) {
            try {
                int age = Integer.parseInt(updated_value);
                if (age < 18) {
                    System.out.println("Minimum age requirement is 18.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Age must be a number.");
                return;
            }
        } else if ("password".equals(user_field)) {
            if (updated_value.length() < 6 || updated_value.length() > 12) {
                System.out.println("Password must be between 6 to 12 characters.");
                return;
            }
        } else if ("gender".equals(user_field)) {
            if (updated_value.equalsIgnoreCase("male")) {
                updated_value = "Male";
            } else if (updated_value.equalsIgnoreCase("female")) {
                updated_value = "Female";
            } else {
                System.out.println("Invalid input. Enter 'Male' or 'Female'.");
                return;
            }
        } else if ("dateOfBirth".equals(user_field)) {
            if (!updated_value.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Invalid date format. Use 'YYYY-MM-DD'.");
                return;
            }
        }else if ("activityLevel".equals(user_field)) {
            if (updated_value.equalsIgnoreCase("sedentary")) {
                updated_value = "Sedentary";
            } else if (updated_value.equalsIgnoreCase("moderate")) {
                updated_value = "Moderate";
            } else if (updated_value.equalsIgnoreCase("active")) {
                updated_value = "Active";
            } else {
                System.out.println("Invalid input. Please enter one of the following: 'Sedentary', 'Moderate', 'Active'.");
                return;
            }
        }


        try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "6670");
             PreparedStatement pst = con.prepareStatement("UPDATE details SET " + user_field + " = ? WHERE userID = ?")) {

            pst.setString(1, updated_value);
            pst.setInt(2, user_id);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User details updated successfully.");
            } else {
                System.out.println("No user found with the given User ID.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
        input.close();
    }

    public static void main(String[] args) {
        try {
            updateUser();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
