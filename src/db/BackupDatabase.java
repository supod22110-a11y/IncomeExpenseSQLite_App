package db;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupDatabase {

    public static void backup() {

        try {

            String dbName = "userdb";
            String user = "root";
            String pass = "";

            String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date());
            String backupPath = "backup/" + dbName + "_" + date + ".sql";

            new File("backup").mkdir();

            ProcessBuilder pb;

            if (pass.isEmpty()) {
                pb = new ProcessBuilder("mysqldump", "-u" + user, dbName);
            } else {
                pb = new ProcessBuilder("mysqldump", "-u" + user, "-p" + pass, dbName);
            }

            pb.redirectOutput(new File(backupPath));

            Process process = pb.start();

            int result = process.waitFor();

            if (result == 0) {
                System.out.println("Backup Success");
            } else {
                System.out.println("Backup Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}