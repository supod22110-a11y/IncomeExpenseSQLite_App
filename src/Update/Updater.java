package Updater;

import java.io.*;
import java.net.URL;
import javax.swing.*;
import java.awt.Desktop;

public class Updater {

    private static final String CURRENT_VERSION = "1.1";

    private static final String VERSION_URL
            = "https://raw.githubusercontent.com/supod22110-a11y/IncomeExpenseSQLite_App/main/version.txt";

    public static void checkUpdate() {

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new URL(VERSION_URL).openStream())
            );

            String latestVersion = br.readLine().trim();
            br.close();

            if (!latestVersion.trim().equals(CURRENT_VERSION.trim())) {

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "มีเวอร์ชันใหม่: " + latestVersion + "\nอัปเดตตอนนี้?",
                        "Update",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    downloadAndInstall();
                }
            }

        } catch (Exception e) {
            System.out.println("เช็คเวอร์ชันไม่ได้");
        }
    }

    private static void downloadAndInstall() {

        try {

            // 📁 path temp
            String tempDir = System.getProperty("java.io.tmpdir");
            String installerPath = tempDir + "MyAppSetup.exe";

            // ⬇️ download file
            String latestVersion = getLatestVersion();

            String downloadUrl
                    = "https://raw.githubusercontent.com/supod22110-a11y/IncomeExpenseSQLite_App/main/Setup/IncomeExpenseSetup_v"
                    + latestVersion + ".exe";

            URL url = new URL(downloadUrl);
            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream(installerPath);

            byte[] buffer = new byte[4096];
            int n;
            while ((n = in.read(buffer)) != -1) {
                fos.write(buffer, 0, n);
            }

            fos.close();
            in.close();

            // 🚀 สร้าง batch file เพื่อ update
            String batPath = tempDir + "update.bat";

            BufferedWriter writer = new BufferedWriter(new FileWriter(batPath));

            writer.write("@echo off\n");
            writer.write("timeout /t 2\n"); // รอโปรแกรมปิด
            writer.write("start \"\" \"" + installerPath + "\"\n");

            writer.close();

            // ▶️ รัน batch
            new ProcessBuilder("cmd", "/c", batPath).start();

            // ❌ ปิดโปรแกรมปัจจุบัน
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "อัปเดตไม่สำเร็จ");
        }
    }

    private static String getLatestVersion() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new URL(VERSION_URL).openStream())
            );
            String version = br.readLine().trim();
            br.close();
            return version;
        } catch (Exception e) {
            return CURRENT_VERSION;
        }
    }
}
