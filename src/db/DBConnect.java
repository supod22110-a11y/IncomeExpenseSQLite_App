package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;
import java.io.File;
import java.sql.ResultSet;

public class DBConnect {

    // ✅ ใช้ path ที่เสถียร (ไม่พังตอน build exe)
    private static final String DB_FOLDER
            = System.getProperty("user.home") + "/MyAppDB/";
    private static final String DB_PATH = DB_FOLDER + "database.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    /**
     * สร้าง connection กับ SQLite
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 🔥 สร้างโฟลเดอร์อัตโนมัติ
            File folder = new File(DB_FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            System.out.println("DB PATH = " + DB_PATH);

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

            // 👤 user table
            String userTable = "CREATE TABLE IF NOT EXISTS user_db ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "user TEXT UNIQUE NOT NULL,"
                    + "password TEXT NOT NULL,"
                    + "created TEXT DEFAULT CURRENT_TIMESTAMP,"
                    + "role TEXT DEFAULT 'USER'"
                    + ");";

            // 💰 income_expense table
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
     * Test run
     */
   /* public static void seedData() {

        String checkSql = "SELECT COUNT(*) FROM income_expense";

        try (Connection conn = getConnection(); PreparedStatement check = conn.prepareStatement(checkSql); ResultSet rs = check.executeQuery()) {

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("มีข้อมูลอยู่แล้ว ❌ ไม่ต้อง seed");
                return;
            }

            System.out.println("กำลัง seed ข้อมูลตัวอย่าง...");

            String insert = "INSERT INTO income_expense "
                    + "(user_id, record_date, type, category, amount, note) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(insert);

            // 🔥 ตัวอย่างข้อมูล
            Object[][] data = {
                {1, "2026-01-05", "INCOME", "เงินเดือน", 15000.0, "เงินเดือนเดือน ม.ค."},
                {1, "2026-01-10", "EXPENSE", "อาหาร", 200.0, "ข้าวกลางวัน"},
                {1, "2026-02-01", "INCOME", "ฟรีแลนซ์", 5000.0, "งานเสริม"},
                {1, "2026-02-15", "EXPENSE", "ค่าไฟ", 1200.0, "บิลไฟ"},
                {1, "2026-03-01", "INCOME", "โบนัส", 3000.0, "โบนัสพิเศษ"},
                {1, "2026-03-10", "EXPENSE", "ช้อปปิ้ง", 800.0, "เสื้อผ้า"}
            };

            for (Object[] row : data) {
                ps.setInt(1, (int) row[0]);
                ps.setString(2, (String) row[1]);
                ps.setString(3, (String) row[2]);
                ps.setString(4, (String) row[3]);
                ps.setDouble(5, (double) row[4]);
                ps.setString(6, (String) row[5]);
                ps.executeUpdate();
            }

            System.out.println("Seed data เรียบร้อย ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initDatabase();
    }*/
}
