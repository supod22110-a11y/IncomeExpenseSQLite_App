package db;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void initDatabase() {

        try (Connection conn = DBConnect.getConnection();
             Statement st = conn.createStatement()) {

            String userTable = "CREATE TABLE IF NOT EXISTS user_db ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "user TEXT UNIQUE,"
                    + "password TEXT,"
                    + "role TEXT"
                    + ")";

            st.execute(userTable);

            String incomeTable = "CREATE TABLE IF NOT EXISTS income_expense ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "user_id INTEGER,"
                    + "record_date TEXT,"
                    + "type TEXT,"
                    + "category TEXT,"
                    + "amount REAL,"
                    + "note TEXT,"
                    + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

            st.execute(incomeTable);

            // 🔥 แก้ index ตรงนี้
            try {
                st.execute("CREATE INDEX idx_user_id ON income_expense(user_id)");
            } catch (Exception e) {
                System.out.println("Index already exists");
            }

            System.out.println("Database Ready");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}