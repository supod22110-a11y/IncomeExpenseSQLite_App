package updater;

import ui.LoadingDialog;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Updater {

    private static final String CURRENT_VERSION = "1.3";

    private static final String VERSION_URL =
            "https://raw.githubusercontent.com/supod22110-a11y/IncomeExpenseSQLite_App/main/version.txt";

    public static void checkUpdate() {

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new URL(VERSION_URL).openStream())
            );

            String latestVersion = br.readLine().trim();
            br.close();

            if (!latestVersion.equals(CURRENT_VERSION)) {

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "มีเวอร์ชันใหม่: " + latestVersion + "\nอัปเดตตอนนี้?",
                        "Update",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    downloadAndInstall(latestVersion);
                }
            }

        } catch (Exception e) {
            System.out.println("❌ เช็คเวอร์ชันไม่ได้");
        }
    }

    private static void downloadAndInstall(String version) {

        LoadingDialog loading = new LoadingDialog(null, "กำลังดาวน์โหลดเวอร์ชันใหม่...");

        new Thread(() -> {
            try {
                SwingUtilities.invokeLater(() -> loading.setVisible(true));

                String tempDir = System.getProperty("java.io.tmpdir");
                String installerPath = tempDir + "MyAppSetup.exe";

                String downloadUrl =
                        "https://github.com/supod22110-a11y/IncomeExpenseSQLite_App/releases/download/v"
                                + version + "/IncomeExpenseSetup_v" + version + ".exe";

                URL url = new URL(downloadUrl);
                URLConnection conn = url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(10000);

                InputStream in = conn.getInputStream();
                FileOutputStream fos = new FileOutputStream(installerPath);

                byte[] buffer = new byte[4096];
                int n;
                while ((n = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, n);
                }

                fos.close();
                in.close();

                // ✅ เช็คไฟล์
                File f = new File(installerPath);
                if (!f.exists() || f.length() == 0) {
                    throw new IOException("Download failed");
                }

                // 📄 สร้าง batch
                String batPath = tempDir + "update.bat";
                BufferedWriter writer = new BufferedWriter(new FileWriter(batPath));

                writer.write("@echo off\n");
                writer.write("timeout /t 2\n");
                writer.write("start \"\" \"" + installerPath + "\"\n");

                writer.close();

                // ▶️ รัน updater
                new ProcessBuilder("cmd", "/c", batPath).start();

                System.exit(0);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "❌ อัปเดตไม่สำเร็จ");
            } finally {
                loading.dispose();
            }
        }).start();
    }
}