import java.util.Scanner;

public class UserAuthentication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String correctUsername = "admin"; // predefined username
        String correctPassword = "password"; // predefined password
        int attempts = 3; // number of attempts allowed from the user

        while (attempts > 0) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine(); // read username

            System.out.print("Enter password: ");
            String password = ""; // initialize password
            char ch;
            // Using console to read characters (
            // We have to use system's console to securely capture input in *masked form*.
            if (System.console() != null) {
                // If Console is available (works on terminal/command line), use it to mask the password
                char[] passwordArray = System.console().readPassword(); 
                password = new String(passwordArray); // Convert char[] to String
            } else {
                // If console is not available, use scanner to read input
                while ((ch = scanner.next().charAt(0)) != '\n') { // read password character by character
                    password += "*"; // print * for each character (masking)
                }
            }

            // Check credentials
            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                System.out.println("Access granted."); // successful login
                break;
            } else {
                attempts--; // decrement attempts
                System.out.println("Invalid credentials. You have " + attempts + " attempts left."); // error message
            }
        }

        if (attempts == 0) {
            System.out.println("Access denied."); // denied after attempts
        }

        scanner.close(); // close scanner
    }
}
