public class LoginService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/a2";
    private static final String DB_USER = "testuser";
    private static final String DB_PASSWORD = "testpass";

    public String authenticateUser(String email, String password) {
        String userName = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Intentional SQL error: Incorrect table name 'users' instead of 'user'
            String query = "SELECT name FROM users WHERE Email = ? AND Password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userName = rs.getString("name");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }
}
