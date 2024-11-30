package test;
import main.LoginApp;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginAppTest {

    private LoginApp loginApp;

    @Before
    public void setUp() {
        loginApp = new LoginApp();
    }

    @Test
    public void testSuccessfulLogin() {
        // Test with a valid email and correct password
        String userName = loginApp.authenticateUser("johndoe@example.com", "password123");
        assertEquals("John Doe", userName);
    }

    @Test
    public void testInvalidEmail() {
        // Test with an email that doesn't exist in the database
        String userName = loginApp.authenticateUser("invalidemail@example.com", "password123");
        assertNull(userName);
    }

    @Test
    public void testIncorrectPassword() {
        // Test with a valid email but incorrect password
        String userName = loginApp.authenticateUser("johndoe@example.com", "wrongpassword");
        assertNull(userName);
    }

    @Test
    public void testEmptyFields() {
        // Test with empty email and password fields
        String userName = loginApp.authenticateUser("", "");
        assertNull(userName);
    }

    @Test
    public void testSqlInjectionAttempt() {
        // Test for SQL injection attack
        String userName = loginApp.authenticateUser("johndoe@example.com", "' OR '1'='1");
        assertNull(userName);
    }
}
