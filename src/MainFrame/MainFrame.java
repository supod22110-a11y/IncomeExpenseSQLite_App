package MainFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import Login.LoginFrame;
import Updater.Updater;
import db.BackupDatabase;
import db.DBConnect;
import db.DatabaseSetup;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import session.UserSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

public class MainFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());

    public MainFrame() {

        UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.BOLD, 20));

        if (!UserSession.isLogin()) {
            JOptionPane.showMessageDialog(null, "กรุณาล็อคอินก่อน", "Access Denied", JOptionPane.WARNING_MESSAGE);
            new LoginFrame().setVisible(true);
            this.dispose();
            return;
        }
        initComponents();
        Updater.checkUpdate();
        checkRole();
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {

                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        null,
                        "ต้องการสำรองข้อมูลไหม?",
                        "Exit",
                        javax.swing.JOptionPane.YES_NO_OPTION
                );

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    new Thread(() -> {
                        BackupDatabase.backup();
                    }).start();
                    JOptionPane.showMessageDialog(null, "Backup สำเร็จ");
                    UserSession.logout();
                    System.exit(0);
                }

            }
        });
        lblUser.setText(" " + UserSession.getUsername());
        setLocationRelativeTo(null);
        System.out.println("MAIN username = " + UserSession.getUsername());

        loadTableMonthly();
        formatTable();
        formatHeader();
        loadBarChart();
        loadSummary();

    }

    private void checkRole() {

        String role = UserSession.getRole(); // หรือ getUser().getRole()

        if (role.equals("USER")) {
            ButDelete.setVisible(false);  // ซ่อนปุ่มลบ
        }

        if (role.equals("ADMIN")) {
            ButDelete.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PaHead = new javax.swing.JPanel();
        LaHead = new javax.swing.JLabel();
        PaNorth = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        LaIncomeExpen = new javax.swing.JLabel();
        LaExit = new javax.swing.JLabel();
        PaCenter = new javax.swing.JPanel();
        ButDelete = new javax.swing.JButton();
        JComboMonth = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableMain = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        JYear = new com.toedter.calendar.JYearChooser();
        PaJfreechart = new javax.swing.JPanel();
        PaSummerize = new javax.swing.JPanel();
        LaSumIncome = new javax.swing.JLabel();
        LaSumEx = new javax.swing.JLabel();
        txtSumIncome = new javax.swing.JTextField();
        txtSumEx = new javax.swing.JTextField();
        LaTotal = new javax.swing.JLabel();
        txtSumTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        LaHead.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LaHead.setText("บัญชีรายรับรายจ่าย");

        javax.swing.GroupLayout PaHeadLayout = new javax.swing.GroupLayout(PaHead);
        PaHead.setLayout(PaHeadLayout);
        PaHeadLayout.setHorizontalGroup(
            PaHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaHeadLayout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addComponent(LaHead)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PaHeadLayout.setVerticalGroup(
            PaHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LaHead, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        PaNorth.setBorder(new javax.swing.border.MatteBorder(null));

        lblUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IconUserAdmin.png"))); // NOI18N

        LaIncomeExpen.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LaIncomeExpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IconIncomeExpen.png"))); // NOI18N
        LaIncomeExpen.setText("บันทึกรายรับและรายจ่าย");
        LaIncomeExpen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LaIncomeExpen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LaIncomeExpenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LaIncomeExpenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LaIncomeExpenMouseExited(evt);
            }
        });

        LaExit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LaExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IconMainExit.png"))); // NOI18N
        LaExit.setText("ออกจากระบบ");
        LaExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LaExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LaExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LaExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LaExitMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PaNorthLayout = new javax.swing.GroupLayout(PaNorth);
        PaNorth.setLayout(PaNorthLayout);
        PaNorthLayout.setHorizontalGroup(
            PaNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaNorthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LaIncomeExpen)
                .addGap(18, 18, 18)
                .addComponent(LaExit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUser)
                .addContainerGap())
        );
        PaNorthLayout.setVerticalGroup(
            PaNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaNorthLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LaExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LaIncomeExpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaCenter.setBackground(new java.awt.Color(255, 255, 255));
        PaCenter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ค้นหาลบหรือแก้ไข", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        ButDelete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ButDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IconDelete.png"))); // NOI18N
        ButDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButDeleteMouseExited(evt);
            }
        });
        ButDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButDeleteActionPerformed(evt);
            }
        });

        JComboMonth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JComboMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "เลือกเดือนทั้งหมด", "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม" }));
        JComboMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboMonthActionPerformed(evt);
            }
        });

        JTableMain.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JTableMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ลำดับ", "เดือน", "จำนวนเงินรายรับ", "จำนวนเงินรายจ่าย", "จำนวนเงินคงเหลือ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JTableMain);

        JYear.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JYearPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout PaCenterLayout = new javax.swing.GroupLayout(PaCenter);
        PaCenter.setLayout(PaCenterLayout);
        PaCenterLayout.setHorizontalGroup(
            PaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
            .addGroup(PaCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaCenterLayout.createSequentialGroup()
                        .addComponent(JComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButDelete))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        PaCenterLayout.setVerticalGroup(
            PaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaCenterLayout.createSequentialGroup()
                .addGroup(PaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PaCenterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JComboMonth, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(JYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(ButDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaJfreechart.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "กราฟแท่ง", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        javax.swing.GroupLayout PaJfreechartLayout = new javax.swing.GroupLayout(PaJfreechart);
        PaJfreechart.setLayout(PaJfreechartLayout);
        PaJfreechartLayout.setHorizontalGroup(
            PaJfreechartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PaJfreechartLayout.setVerticalGroup(
            PaJfreechartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PaSummerize.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "สรุป", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        LaSumIncome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LaSumIncome.setText("รวมยอดรายรับ");

        LaSumEx.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LaSumEx.setText("รวมยอดรายจ่าย");

        txtSumIncome.setBackground(new java.awt.Color(51, 51, 51));
        txtSumIncome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSumIncome.setForeground(java.awt.Color.green);
        txtSumIncome.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtSumEx.setBackground(new java.awt.Color(51, 51, 51));
        txtSumEx.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSumEx.setForeground(java.awt.Color.red);
        txtSumEx.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        LaTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LaTotal.setText("ยอดทั้งหมด");

        txtSumTotal.setBackground(new java.awt.Color(51, 51, 51));
        txtSumTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSumTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtSumTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaSummerizeLayout = new javax.swing.GroupLayout(PaSummerize);
        PaSummerize.setLayout(PaSummerizeLayout);
        PaSummerizeLayout.setHorizontalGroup(
            PaSummerizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaSummerizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaSummerizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LaSumIncome)
                    .addComponent(LaSumEx)
                    .addComponent(LaTotal))
                .addGap(18, 18, 18)
                .addGroup(PaSummerizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSumTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(txtSumEx)
                    .addComponent(txtSumIncome))
                .addContainerGap())
        );
        PaSummerizeLayout.setVerticalGroup(
            PaSummerizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaSummerizeLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PaSummerizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSumIncome, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(LaSumIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaSummerizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSumEx, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LaSumEx, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PaSummerizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSumTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaHead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PaJfreechart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PaSummerize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PaNorth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PaCenter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PaHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PaNorth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PaCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PaSummerize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PaJfreechart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LaExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaExitMouseClicked
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "ต้องการออกจากระบบหรือไม่?",
                "Logout",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            new Thread(() -> {
                BackupDatabase.backup();
            }).start();
            JOptionPane.showMessageDialog(null, "Backup สำเร็จ");
            session.UserSession.logout();

            new Login.LoginFrame().setVisible(true);

            this.dispose();
        }

    }//GEN-LAST:event_LaExitMouseClicked

    private void LaExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaExitMouseEntered
        LaExit.setForeground(Color.red);
        LaExit.setFont(new Font("Tahoma", Font.BOLD, 20));
    }//GEN-LAST:event_LaExitMouseEntered

    private void LaExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaExitMouseExited

        LaExit.setForeground(Color.black);
        LaExit.setFont(new Font("Tahoma", Font.BOLD, 18));


    }//GEN-LAST:event_LaExitMouseExited

    private void JYearPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JYearPropertyChange
        if ("year".equals(evt.getPropertyName())) {
            loadTableMonthly();
            loadBarChart();
            loadSummary();

        }
    }//GEN-LAST:event_JYearPropertyChange

    private void JComboMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboMonthActionPerformed
        loadTableMonthly();
        loadBarChart();
        loadSummary();
    }//GEN-LAST:event_JComboMonthActionPerformed

    private void txtSumTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumTotalActionPerformed

    }//GEN-LAST:event_txtSumTotalActionPerformed

    private void ButDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButDeleteActionPerformed
        int SelectRow = JTableMain.getSelectedRow();
        if (SelectRow == -1) {
            JOptionPane.showMessageDialog(this, "กรุณาเลือกข้อมูลก่อน", "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String monthName = JTableMain.getValueAt(SelectRow, 1).toString();
        Map<String, Integer> monthMap = new HashMap<>();
        monthMap.put("มกราคม", 1);
        monthMap.put("กุมภาพันธ์", 2);
        monthMap.put("มีนาคม", 3);
        monthMap.put("เมษายน", 4);
        monthMap.put("พฤษภาคม", 5);
        monthMap.put("มิถุนายน", 6);
        monthMap.put("กรกฎาคม", 7);
        monthMap.put("สิงหาคม", 8);
        monthMap.put("กันยายน", 9);
        monthMap.put("ตุลาคม", 10);
        monthMap.put("พฤศจิกายน", 11);
        monthMap.put("ธันวาคม", 12);
        int month = monthMap.get(monthName);

        int comfirm = JOptionPane.showConfirmDialog(this, "ต้องการลบข้อมูลเดือน" + monthName + "ใช่หรือไม่?", "ยืนยันการลบ", JOptionPane.YES_NO_OPTION);
        if (comfirm == JOptionPane.YES_OPTION) {
            int year = JYear.getYear() - 543;
            String sql = "DELETE FROM income_expense "
                    + "WHERE user_id=? "
                    + "AND strftime('%Y', record_date)=? "
                    + "AND strftime('%m', record_date)=?";

            try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, UserSession.getUserId());
                ps.setInt(2, year);
                ps.setInt(3, month);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "ลบเรียบร้อยแล้ว");
                loadBarChart();
                loadTableMonthly();
                loadSummary();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_ButDeleteActionPerformed

    private void LaIncomeExpenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaIncomeExpenMouseEntered

        LaIncomeExpen.setForeground(Color.BLUE);
        LaIncomeExpen.setFont(new Font("Tahoma", Font.BOLD, 20));
    }//GEN-LAST:event_LaIncomeExpenMouseEntered

    private void LaIncomeExpenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaIncomeExpenMouseExited

        LaIncomeExpen.setForeground(Color.BLACK);
        LaIncomeExpen.setFont(new Font("Tahoma", Font.BOLD, 18));


    }//GEN-LAST:event_LaIncomeExpenMouseExited

    private void LaIncomeExpenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaIncomeExpenMouseClicked
        new IncomeExpenseFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LaIncomeExpenMouseClicked

    private void ButDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButDeleteMouseEntered
        ButDelete.setBackground(new Color(200, 50, 50));
    }//GEN-LAST:event_ButDeleteMouseEntered

    private void ButDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButDeleteMouseExited
        ButDelete.setBackground(new Color(70, 70, 70));
    }//GEN-LAST:event_ButDeleteMouseExited
    private Integer getSelectedMonth() {
        int index = JComboMonth.getSelectedIndex();
        if (index == 0) { // เลือกเดือนทั้งหมด
            return null;
        }
        return index; // มกราคม=1, กุมภาพันธ์=2 ...
    }

    public void loadBarChart() {
        Integer month = getSelectedMonth();
        int year = JYear.getYear() - 543;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String sql = "SELECT strftime('%m', record_date) AS m, "
                + "SUM(CASE WHEN type='INCOME' THEN amount ELSE 0 END) AS income, "
                + "SUM(CASE WHEN type='EXPENSE' THEN amount ELSE 0 END) AS expense "
                + "FROM income_expense "
                + "WHERE user_id=? AND strftime('%Y', record_date)=? ";

        if (month != null) {
            sql += " AND strftime('%m', record_date)=? ";
        }
        sql += "GROUP BY strftime('%m', record_date) ORDER BY m";

        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, UserSession.getUserId());
            ps.setString(2, String.valueOf(year));

            if (month != null) {
                ps.setString(3, String.format("%02d", month));
            }
            ResultSet rs = ps.executeQuery();

            String[] months = {
                "", "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน",
                "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"
            };

            while (rs.next()) {
                int m = rs.getInt("m");
                dataset.addValue(rs.getDouble("income"), "รายรับ", months[m]);
                dataset.addValue(rs.getDouble("expense"), "รายจ่าย", months[m]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "รายรับ vs รายจ่าย ปี " + year,
                "เดือน",
                "จำนวนเงิน",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        PaJfreechart.removeAll();
        PaJfreechart.setLayout(new java.awt.BorderLayout());
        PaJfreechart.add(chartPanel, java.awt.BorderLayout.CENTER);
        PaJfreechart.validate();
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 153, 0)); // รายรับ
        renderer.setSeriesPaint(1, new Color(204, 0, 0)); // รายจ่าย

    }

    public void loadTableMonthly() {
        Integer month = getSelectedMonth();
        int year = JYear.getYear() - 543;
        DefaultTableModel model = (DefaultTableModel) JTableMain.getModel();
        model.setRowCount(0);

        String sql = "SELECT strftime('%m', record_date) AS m, "
                + "SUM(CASE WHEN type='INCOME' THEN amount ELSE 0 END) AS income, "
                + "SUM(CASE WHEN type='EXPENSE' THEN amount ELSE 0 END) AS expense "
                + "FROM income_expense "
                + "WHERE user_id=? AND strftime('%Y', record_date)=? ";

        if (month != null) {
            sql += " AND strftime('%m', record_date)=? ";
        }

        sql += "GROUP BY strftime('%m', record_date) ORDER BY m";

        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, UserSession.getUserId());
            ps.setString(2, String.valueOf(year));

            if (month != null) {
                ps.setString(3, String.format("%02d", month));
            }
            try (ResultSet rs = ps.executeQuery()) {

                int no = 1;
                double balance = 0;
                String[] months = {
                    "", "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน",
                    "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"
                };

                while (rs.next()) {
                    double income = rs.getDouble("income");
                    double expense = rs.getDouble("expense");
                    balance += income - expense;

                    model.addRow(new Object[]{
                        no++,
                        months[rs.getInt("m")],
                        income,
                        expense,
                        balance
                    });
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadSummary() {
        Integer month = getSelectedMonth();
        int year = JYear.getYear() - 543;

        String sql = "SELECT strftime('%m', record_date) AS m, "
                + "SUM(CASE WHEN type='INCOME' THEN amount ELSE 0 END) AS income, "
                + "SUM(CASE WHEN type='EXPENSE' THEN amount ELSE 0 END) AS expense "
                + "FROM income_expense "
                + "WHERE user_id=? AND strftime('%Y', record_date)=? ";

        if (month != null) {
            sql += " AND strftime('%m', record_date)=? ";
        }
        sql += "GROUP BY strftime('%m', record_date) ORDER BY m";

        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, UserSession.getUserId());
            ps.setString(2, String.valueOf(year));

            if (month != null) {
                ps.setString(3, String.format("%02d", month));
            }
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double income = rs.getDouble("income");   // ✅ แก้ตรงนี้
                double expense = rs.getDouble("expense"); // ✅ แก้ตรงนี้
                double total = income - expense;

                txtSumIncome.setText(String.format("%,.2f", income));
                txtSumEx.setText(String.format("%,.2f", expense));
                txtSumTotal.setText(String.format("%,.2f", total));
                System.out.println("INCOME = " + income);
                System.out.println("EXPENSE = " + expense);
                if (total > 0) {
                    txtSumTotal.setForeground(new Color(0, 255, 120));
                } else if (total < 0) {
                    txtSumTotal.setForeground(Color.red);
                } else {
                    txtSumIncome.setText("0.00");
                    txtSumEx.setText("0.00");
                    txtSumTotal.setText("0.00");
                    txtSumTotal.setForeground(Color.WHITE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void formatTable() {

        JTableMain.setRowHeight(35);
        JTableMain.setFont(new Font("Tahoma", Font.BOLD, 18));

        // ===== Header =====
        JTableMain.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        JTableMain.getTableHeader().setBackground(new Color(60, 63, 65));
        JTableMain.getTableHeader().setForeground(Color.WHITE);

        // ===== จัดกึ่งกลางคอลัมน์ลำดับ + เดือน =====
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JTableMain.getColumnModel().getColumn(0).setCellRenderer(center);
        JTableMain.getColumnModel().getColumn(1).setCellRenderer(center);
        JTableMain.setGridColor(new Color(200, 200, 200));
        JTableMain.setShowGrid(true);

        // ===== Renderer ตัวเลข =====
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (value instanceof Number) {
                    double amount = ((Number) value).doubleValue();
                    setText(String.format("%,.2f", amount));
                    setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

                    if (column == 4) { // คงเหลือ
                        if (amount < 0) {
                            setForeground(Color.RED);
                        } else {
                            setForeground(new Color(0, 180, 0));
                        }
                    } else {
                        setForeground(Color.BLACK);
                    }
                }

                return c;
            }
        };

        // ใช้กับคอลัมน์ตัวเลข
        JTableMain.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        JTableMain.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        JTableMain.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
    }

    private void formatHeader() {

        JTableHeader header = JTableMain.getTableHeader();

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                label.setBackground(Color.BLACK);
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Tahoma", Font.BOLD, 16));
                label.setHorizontalAlignment(CENTER);

                label.setBorder(BorderFactory.createMatteBorder(
                        0, 0, 1, 1, new Color(220, 220, 220))); // เส้นบาง ๆ สีเทาอ่อน

                return label;
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButDelete;
    private javax.swing.JComboBox<String> JComboMonth;
    private javax.swing.JTable JTableMain;
    private com.toedter.calendar.JYearChooser JYear;
    private javax.swing.JLabel LaExit;
    private javax.swing.JLabel LaHead;
    private javax.swing.JLabel LaIncomeExpen;
    private javax.swing.JLabel LaSumEx;
    private javax.swing.JLabel LaSumIncome;
    private javax.swing.JLabel LaTotal;
    private javax.swing.JPanel PaCenter;
    private javax.swing.JPanel PaHead;
    private javax.swing.JPanel PaJfreechart;
    private javax.swing.JPanel PaNorth;
    private javax.swing.JPanel PaSummerize;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextField txtSumEx;
    private javax.swing.JTextField txtSumIncome;
    private javax.swing.JTextField txtSumTotal;
    // End of variables declaration//GEN-END:variables
}
