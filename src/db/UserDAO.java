package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

    public User login(String username, String password) {

        try (Connection conn = DBConnect.getConnection()) {

            String sql = "SELECT * FROM user_db WHERE user = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String hashed = rs.getString("password");

                if (BCrypt.checkpw(password, hashed)) {

                    return new User(
                            rs.getInt("id"),
                            rs.getString("user"),
                            hashed,
                            rs.getString("role")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // สมัครสมาชิก
    public boolean register(String username, String password, String role) {

        try (Connection conn = DBConnect.getConnection()) {

            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

            String sql = "INSERT INTO user_db (user, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, hashed);
            ps.setString(3, role);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean registerAdmin(String username, String password) {

        String sql = "INSERT INTO user_db(user,password,role) VALUES(?,?, 'ADMIN')";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String hash = BCrypt.hashpw(password, BCrypt.gensalt());

            ps.setString(1, username);
            ps.setString(2, hash);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isUserExists(String username) {

        String sql = "SELECT id FROM user_db WHERE user = ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
