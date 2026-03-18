package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

public class DBConnect {

    // Path ของ SQLite database
    private static final String URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/database.db";

    /**
     * สร้าง connection กับ SQLite
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);

            // เปิด foreign key
            conn.createStatement().execute("PRAGMA foreign_keys = ON;");

            System.out.println("Connected SQLite SUCCESS ✅");
        } catch (Exception e) {
            System.out.println("DB CONNECT FAILED ❌");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * สร้าง database และ table พร้อม admin default
     */
    public static void initDatabase() {
        Connection conn = getConnection();
        if (conn == null) {
            System.out.println("Connection is NULL ❌");
            return;
        }

        try (Statement stmt = conn.createStatement()) {

            // สร้าง table user_db
            String userTable = "CREATE TABLE IF NOT EXISTS user_db ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "user TEXT UNIQUE NOT NULL,"
                    + "password TEXT NOT NULL,"
                    + "created TEXT DEFAULT CURRENT_TIMESTAMP,"
                    + "role TEXT DEFAULT 'USER'"
                    + ");";

            // สร้าง table income_expense
            String incomeExpenseTable = "CREATE TABLE IF NOT EXISTS income_expense ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "user_id INTEGER,"
                    + "record_date TEXT,"
                    + "type TEXT,"
                    + "category TEXT,"
                    + "amount REAL,"
                    + "note TEXT,"
                    + "created_at TEXT DEFAULT CURRENT_TIMESTAMP,"
                    + "FOREIGN KEY(user_id) REFERENCES user_db(id) ON DELETE CASCADE"
                    + ");";

            stmt.execute(userTable);
            stmt.execute(incomeExpenseTable);

            System.out.println("Tables created or exist ✅");

            // 🔐 สร้าง admin default
            String hashedPassword = BCrypt.hashpw("12345", BCrypt.gensalt(10));

            String insertAdmin = "INSERT OR IGNORE INTO user_db (user, password, role) VALUES (?, ?, 'ADMIN');";

            try (PreparedStatement ps = conn.prepareStatement(insertAdmin)) {
                ps.setString(1, "admin");
                ps.setString(2, hashedPassword);
                int updated = ps.executeUpdate();
                if (updated == 1) {
                    System.out.println("Admin user created ✅");
                } else {
                    System.out.println("Admin user already exists ⚠️");
                }
            }

            System.out.println("Database Ready ✅");

        } catch (Exception e) {
            System.out.println("INIT DATABASE FAILED ❌");
            e.printStackTrace();
        }
    }

    /**
     * Test connection + init database
     */
    public static void main(String[] args) {
        initDatabase();
    }
}