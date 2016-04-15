package edu.ben.template.model;

public class Validator {

	public static boolean validatePasswordsMatch(String password, String confirmPassword) {

		return password.equals(confirmPassword);
	}

	public static boolean validatePassword(String password, boolean required) {

		if (required){
		return (!isNull(password) && password.matches(".{2,}"));
		}
		return (isNull(password) || password.matches(".{2,}"));
	}

	public static boolean validateName(String name) {

		return (!isNull(name) && name.matches(".{2,}"));
	}

	public static boolean validateGraduationYear(String year, boolean required) {

		if (year.equals("Select"))
			year = "";
		
		if (!required) {
			return (isNull(year) || year.matches("[0-9]{4}"));
		}

		return (!isNull(year) && year.matches("[0-9]{4}"));
	}

	public static boolean validateEmail(String email, boolean required) {

		if (!required) {
			return (isNull(email) || email.matches("[a-zA-Z](?:[a-zA-Z_0-9])+@[a-zA-Z_0-9]+[.][a-zA-Z_0-9]{2,4}"));
		}
		return (!isNull(email) && email.matches("[a-zA-Z](?:[a-zA-Z_0-9])+@[a-zA-Z_0-9]+[.][a-zA-Z_0-9]{2,4}"));
	}
	
	public static boolean validateSelect(String s) {

		return !s.equals("Select");
	}


	public static boolean isNull(String s) {

		return (s.equals("")|| s.equals(null));
	}
}
