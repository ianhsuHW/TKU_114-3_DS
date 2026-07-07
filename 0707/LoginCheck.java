public class LoginCheck {

    public static void main(String[] args) {
    String username = "admin";
    String password = "1234";

    String inputUsername = "admin";
    String inputPassword = "1234";

    boolean isLoginSuccess = inputUsername.equals(username) && inputPassword.equals(password);

    System.out.println("Username: " + username);
    System.out.println("Password: " + password);
    System.out.println("Input Username: " + inputUsername);
    System.out.println("Input Password: " + inputPassword);
    System.out.println("Login Success: " + isLoginSuccess);
    }
}

