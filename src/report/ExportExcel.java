package report;

import db.DBConnect;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import session.UserSession;

public class ExportExcel {

    public static void export(int month, int year, String username) {

        String sql = "SELECT record_date,type,category,amount,note "
                + "FROM income_expense WHERE user_id=?";

        try {

            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, UserSession.getUserId());

            ResultSet rs = ps.executeQuery();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Report");

            // ===== Style =====

            Font titleFont = workbook.createFont();
            titleFont.setFontHeightInPoints((short) 16);
            titleFont.setBold(true);

            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(titleFont);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle moneyStyle = workbook.createCellStyle();
            DataFormat format = workbook.createDataFormat();
            moneyStyle.setDataFormat(format.getFormat("#,##0.00"));

            // ===== Title =====

            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Income Expense Report");
            titleCell.setCellStyle(titleStyle);

            sheet.createRow(1).createCell(0)
                    .setCellValue("Month : " + month + " / " + year);

            sheet.createRow(2).createCell(0)
                    .setCellValue("User : " + username);

            // ===== Header =====

            Row header = sheet.createRow(4);

            String[] columns = {"Date", "Type", "Category", "Amount", "Note"};

            for (int i = 0; i < columns.length; i++) {

                Cell cell = header.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);

            }

            // ===== Date Format =====

            SimpleDateFormat thaiDate =
                    new SimpleDateFormat("d-MM-yyyy", new Locale("th", "TH"));

            int rowNum = 5;

            double totalIncome = 0;
            double totalExpense = 0;

            while (rs.next()) {

                Row row = sheet.createRow(rowNum++);

                java.util.Date date = rs.getDate("record_date");
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");

                row.createCell(0).setCellValue(thaiDate.format(date));
                row.createCell(1).setCellValue(type);
                row.createCell(2).setCellValue(rs.getString("category"));

                Cell moneyCell = row.createCell(3);
                moneyCell.setCellValue(amount);
                moneyCell.setCellStyle(moneyStyle);

                row.createCell(4).setCellValue(rs.getString("note"));

                if (type.equalsIgnoreCase("Income") || type.equals("รายรับ")) {
                    totalIncome += amount;
                } else {
                    totalExpense += amount;
                }

            }

            // ===== Summary =====

            int summaryRow = rowNum + 1;

            Row r1 = sheet.createRow(summaryRow);
            r1.createCell(2).setCellValue("Total Income");
            r1.createCell(3).setCellValue(totalIncome);

            Row r2 = sheet.createRow(summaryRow + 1);
            r2.createCell(2).setCellValue("Total Expense");
            r2.createCell(3).setCellValue(totalExpense);

            Row r3 = sheet.createRow(summaryRow + 2);
            r3.createCell(2).setCellValue("Balance");
            r3.createCell(3).setCellValue(totalIncome - totalExpense);

            // ===== Auto column =====

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // ===== Save file =====

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Excel Report");

            FileNameExtensionFilter filter =
                    new FileNameExtensionFilter("Excel File", "xlsx");

            fileChooser.setFileFilter(filter);

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {

                String filePath =
                        fileChooser.getSelectedFile().getAbsolutePath();

                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                FileOutputStream fileOut = new FileOutputStream(filePath);

                workbook.write(fileOut);

                fileOut.close();
                workbook.close();

                JOptionPane.showMessageDialog(null, "Export Excel สำเร็จ");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}