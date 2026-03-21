package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupDatabase {

    public static void backup() {
        String dbFilePath = "C:/Users/supod/MyAppDB/database.db"; // Path ของ SQLite ของคุณ
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date());
        String backupFolder = "backup";
        new File(backupFolder).mkdir();

        String backupFile = backupFolder + "/database_" + date + ".db";

        try (FileInputStream fis = new FileInputStream(dbFilePath); FileOutputStream fos = new FileOutputStream(backupFile)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            System.out.println("Backup Success: " + backupFile);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Backup Failed");
        }
    }
}
