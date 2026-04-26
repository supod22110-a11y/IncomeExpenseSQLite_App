package db;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupDatabase {

    public static void backup() {

        String dbFolder = System.getProperty("user.home") + "/MyAppDB/";
        String dbFilePath = dbFolder + "database.db";

        String backupFolder = System.getProperty("user.home") + "/Documents/MyAppBackup/";
        File backupDir = new File(backupFolder);
        backupDir.mkdirs();

        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date());
        String backupFile = backupFolder + "database_" + date + ".db";

        File dbFile = new File(dbFilePath);

        if (!dbFile.exists()) {
            System.out.println("❌ Database not found: " + dbFilePath);
            return;
        }

        System.out.println("DB PATH = " + dbFilePath);
        System.out.println("Backup PATH = " + backupFile);

        try (FileInputStream fis = new FileInputStream(dbFile);
             FileOutputStream fos = new FileOutputStream(backupFile)) {

            byte[] buffer = new byte[4096];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            System.out.println("✅ Backup Success");


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Backup Failed");
        }
    }

    // 📂 เปิดโฟลเดอร์
    private static void openFolder(File folder) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(folder);
            } else {
                System.out.println("❌ Desktop not supported");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}