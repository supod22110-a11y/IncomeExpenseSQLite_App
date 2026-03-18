package MainFrame;

import db.BackupDatabase;
import db.DBConnect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import session.UserSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.util.Rotation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import report.ExportExcel;


public class IncomeExpenseFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(IncomeExpenseFrame.class.getName());

    private double totalIncome;
    private double totalExpense;
    private int selectedId = -1;

    public IncomeExpenseFrame() {
        initComponents();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {

                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        null,
                        "ต้องการสำรองข้อมูล?",
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
        jtaTable.setAutoCreateRowSorter(true);
        setLocationRelativeTo(null);

        setupDateChooser();
        setupTableStyle();
        refreshAll();

        // โหลดธีมที่เคยบันทึกไว้
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        btnThemeGroup = new javax.swing.ButtonGroup();
        jpaHead = new javax.swing.JPanel();
        jlaHead = new javax.swing.JLabel();
        jpaLeft = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jDate = new com.toedter.calendar.JDateChooser();
        jlaType = new javax.swing.JLabel();
        jrbIncome = new javax.swing.JRadioButton();
        jrbExpense = new javax.swing.JRadioButton();
        jlaCate = new javax.swing.JLabel();
        txtCate = new javax.swing.JTextField();
        jlaAmount = new javax.swing.JLabel();
        jforAmount = new javax.swing.JFormattedTextField();
        jlaNote = new javax.swing.JLabel();
        jspNote = new javax.swing.JScrollPane();
        txtAreaNote = new javax.swing.JTextArea();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jpaRight = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaTable = new javax.swing.JTable();
        jcbbMonth = new javax.swing.JComboBox<>();
        jycYear = new com.toedter.calendar.JYearChooser();
        jpaChart = new javax.swing.JPanel();
        jMenuHead = new javax.swing.JMenuBar();
        jMenuBack = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemBack = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jlaHead.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlaHead.setText("บันทึกรายรับรายจ่าย");

        javax.swing.GroupLayout jpaHeadLayout = new javax.swing.GroupLayout(jpaHead);
        jpaHead.setLayout(jpaHeadLayout);
        jpaHeadLayout.setHorizontalGroup(
            jpaHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpaHeadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlaHead)
                .addGap(475, 475, 475))
        );
        jpaHeadLayout.setVerticalGroup(
            jpaHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlaHead, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        lblUser.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IconUserAdmin.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("วันที่ทำการ");

        jDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jDate.setMaxSelectableDate(new java.util.Date(236235290477000L));
        jDate.setMinSelectableDate(new java.util.Date(-45031615123000L));
        jDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDateFocusGained(evt);
            }
        });

        jlaType.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlaType.setText("ประเภท");

        btnGroup.add(jrbIncome);
        jrbIncome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jrbIncome.setText("INCOME");

        btnGroup.add(jrbExpense);
        jrbExpense.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jrbExpense.setText("EXPENSE");
        jrbExpense.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jlaCate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlaCate.setText("หมวดหมู่");

        txtCate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jlaAmount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlaAmount.setText("จำนวนเงิน");

        jforAmount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jlaNote.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlaNote.setText("หมายเหตุ");

        jspNote.setToolTipText("");
        jspNote.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtAreaNote.setColumns(20);
        txtAreaNote.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAreaNote.setRows(5);
        jspNote.setViewportView(txtAreaNote);

        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEdit.setText("แก้ไข");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAdd.setText("บันทึก");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setText("ลบ");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setText("ยกเลิก");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpaLeftLayout = new javax.swing.GroupLayout(jpaLeft);
        jpaLeft.setLayout(jpaLeftLayout);
        jpaLeftLayout.setHorizontalGroup(
            jpaLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpaLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspNote, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jforAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCate)
                    .addGroup(jpaLeftLayout.createSequentialGroup()
                        .addGroup(jpaLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpaLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jpaLeftLayout.createSequentialGroup()
                                    .addGroup(jpaLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jlaType))
                                    .addGap(195, 195, 195))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpaLeftLayout.createSequentialGroup()
                                    .addGap(42, 42, 42)
                                    .addComponent(jrbIncome)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jrbExpense)))
                            .addComponent(jlaCate)
                            .addComponent(jlaAmount)
                            .addComponent(jlaNote))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpaLeftLayout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(btnCancel)))
                .addContainerGap())
        );
        jpaLeftLayout.setVerticalGroup(
            jpaLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlaType, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpaLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbIncome)
                    .addComponent(jrbExpense))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlaCate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlaAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jforAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlaNote, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspNote, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jpaLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtaTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "วันที่", "ประเภท", "หมวดหมู่", "จำนวนเงิน", "หมายเหตุ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtaTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jtaTableMouseMoved(evt);
            }
        });
        jtaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtaTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtaTable);

        jcbbMonth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcbbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "เลือกเดือนทั้งหมด", "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม" }));
        jcbbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbMonthActionPerformed(evt);
            }
        });

        jycYear.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jycYearPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jpaRightLayout = new javax.swing.GroupLayout(jpaRight);
        jpaRight.setLayout(jpaRightLayout);
        jpaRightLayout.setHorizontalGroup(
            jpaRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaRightLayout.createSequentialGroup()
                .addGroup(jpaRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpaRightLayout.createSequentialGroup()
                        .addGap(634, 634, 634)
                        .addComponent(jcbbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jycYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpaRightLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jpaRightLayout.setVerticalGroup(
            jpaRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaRightLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpaRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jycYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbbMonth, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpaChartLayout = new javax.swing.GroupLayout(jpaChart);
        jpaChart.setLayout(jpaChartLayout);
        jpaChartLayout.setHorizontalGroup(
            jpaChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpaChartLayout.setVerticalGroup(
            jpaChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenuHead.setAlignmentX(1.0F);
        jMenuHead.setAlignmentY(1.0F);
        jMenuHead.setInheritsPopupMenu(true);

        jMenuBack.setText("ตัวเลือก");
        jMenuBack.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        jMenuItem1.setText("รายงานExcel");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuBack.add(jMenuItem1);

        jMenuItemBack.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenuItemBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IconBack.png"))); // NOI18N
        jMenuItemBack.setLabel("กลับไปสู่หน้าหลัก");
        jMenuItemBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBackActionPerformed(evt);
            }
        });
        jMenuBack.add(jMenuItemBack);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenuItemExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IconExit.png"))); // NOI18N
        jMenuItemExit.setText("ออกจากระบบ");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuBack.add(jMenuItemExit);

        jMenuHead.add(jMenuBack);

        setJMenuBar(jMenuHead);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpaHead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpaLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpaRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpaChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpaHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpaLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpaRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpaChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteDate();

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        ClearForm();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void jcbbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbMonthActionPerformed
        refreshAll();
    }//GEN-LAST:event_jcbbMonthActionPerformed

    private void jycYearPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jycYearPropertyChange
        refreshAll();
    }//GEN-LAST:event_jycYearPropertyChange

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int comfirm = JOptionPane.showConfirmDialog(this, "คุณจะเพิ่มข้อมูลใช่ไหม", "ยืนยัน", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (comfirm == JOptionPane.YES_OPTION) {
            addData();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void jDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateFocusGained

    }//GEN-LAST:event_jDateFocusGained

    private void jtaTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtaTableMouseClicked
        int viewRow = jtaTable.getSelectedRow();

        if (viewRow == -1) {
            return;
        }

        // 🔥 แปลง view index → model index
        int row = jtaTable.convertRowIndexToModel(viewRow);

        // 🔥 วันที่
        Object dateObj = jtaTable.getModel().getValueAt(row, 1);
        if (dateObj instanceof java.util.Date) {
            jDate.setDate((java.util.Date) dateObj);
        }

        selectedId = (int) jtaTable.getModel().getValueAt(row, 0);
        System.out.println(
                jtaTable.getModel().getValueAt(row, 0).getClass()
        );

        // ประเภท
        String type = jtaTable.getModel().getValueAt(row, 2).toString();
        if (type.equals("INCOME")) {
            jrbIncome.setSelected(true);
        } else {
            jrbExpense.setSelected(true);
        }

        // category
        txtCate.setText(jtaTable.getModel().getValueAt(row, 3).toString());

        // 🔥 amount (ปลอดภัยกว่า)
        jforAmount.setText(
                jtaTable.getModel().getValueAt(row, 4).toString()
        );

        // note
        txtAreaNote.setText(
                jtaTable.getModel().getValueAt(row, 5).toString()
        );

    }//GEN-LAST:event_jtaTableMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int comfirm = JOptionPane.showConfirmDialog(this, "คุณจะแก้ไขข้อมูลใช่ไหม", "ยืนยัน", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (comfirm == JOptionPane.YES_OPTION) {
            updateData();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void jMenuItemBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBackActionPerformed
        new MainFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemBackActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
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

    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jtaTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtaTableMouseMoved
        JTableHeader header = jtaTable.getTableHeader();
        header.repaint();
    }//GEN-LAST:event_jtaTableMouseMoved

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int month = jcbbMonth.getSelectedIndex() + 1;
        int year = jycYear.getYear();

        ExportExcel.export(month, year, UserSession.getUsername());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public void loadPieChart3D(double income, double expense) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("รายรับ", income);
        dataset.setValue("รายจ่าย", expense);

        JFreeChart chart = ChartFactory.createPieChart3D(
                "สัดส่วนรายรับ vs รายจ่าย",
                dataset,
                true,
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.7f);

        plot.setSectionPaint("รายรับ", Color.GREEN);
        plot.setSectionPaint("รายจ่าย", Color.RED);

        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                "{0} = {1} ({2})"
        ));

        // ===== ธีม =====
        ChartPanel chartPanel = new ChartPanel(chart);

        jpaChart.removeAll();
        jpaChart.setLayout(new BorderLayout());
        jpaChart.add(chartPanel, BorderLayout.CENTER);
        jpaChart.revalidate();
        jpaChart.repaint();

    }

    public double[] getTotalIncomeExpense() {

        totalIncome = 0;
        totalExpense = 0;

        // ใช้กับ Mysql
        int year = jycYear.getYear() - 543;

        int monthIndex = jcbbMonth.getSelectedIndex();

        // ใช้กับMysql
        String sql = "SELECT "
                + "SUM(CASE WHEN type='INCOME' THEN amount ELSE 0 END) AS income, "
                + "SUM(CASE WHEN type='EXPENSE' THEN amount ELSE 0 END) AS expense "
                + "FROM income_expense "
                + "WHERE user_id=? AND YEAR(record_date)=? ";

        if (monthIndex != 0) {
            sql += " AND MONTH(record_date)=? ";

        }

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            int paramIndex = 1;

            ps.setInt(paramIndex++, UserSession.getUserId());
            ps.setInt(paramIndex++, year);

            if (monthIndex != 0) {
                ps.setInt(paramIndex++, monthIndex);

            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalIncome = rs.getDouble("income");
                totalExpense = rs.getDouble("expense");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new double[]{totalIncome, totalExpense};
    }

    public void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jtaTable.getModel();
        model.setRowCount(0);

        System.out.println("LOAD USER ID = " + UserSession.getUserId());
        // 🔹 Renderer สำหรับคอลัมน์วันที่
        int year = jycYear.getYear() - 543;

        int monthIndex = jcbbMonth.getSelectedIndex();

        //ใช้กับ Mysql
        String sql = "SELECT id, record_date, type, category, amount, note "
                + "FROM income_expense WHERE user_id=? AND YEAR(record_date)=? ";

        if (monthIndex != 0) {
            sql += " AND MONTH(record_date)=? ";

        }

        sql += " ORDER BY record_date DESC";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, UserSession.getUserId());
            ps.setInt(2, year);

            if (monthIndex != 0) {
                ps.setInt(3, monthIndex);

            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("record_date"),
                    rs.getString("type"),
                    rs.getString("category"),
                    rs.getDouble("amount"),
                    rs.getString("note")
                });

            }

            // 🔹 ซ่อน id (ย้ายออกมานอก loop)
            jtaTable.getColumnModel().getColumn(0).setMinWidth(0);
            jtaTable.getColumnModel().getColumn(0).setMaxWidth(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addData() {
        Date utilDate = jDate.getDate();
        if (utilDate == null) {
            JOptionPane.showMessageDialog(this, "รูปแบบวันที่ไม่ถูกต้อง (dd-MM-yyyy)");
            return;
        }

        System.out.println("INSERT USER ID = " + UserSession.getUserId());
        java.sql.Date sqlDete = new java.sql.Date(utilDate.getTime());

        String type = jrbIncome.isSelected() ? "INCOME" : "EXPENSE";
        String category = txtCate.getText().trim();
        String amountText = jforAmount.getText().trim();
        String note = txtAreaNote.getText().trim();

        if (category.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "กรุณากรอกให้ครบ");
            return;
        }
        //ใช้กับ Mysql
        double amount = Double.parseDouble(amountText);

        String sql = "INSERT INTO income_expense "
                + "(user_id, record_date, type, category, amount, note)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, UserSession.getUserId());
            ps.setDate(2, sqlDete);
            ps.setString(3, type);
            ps.setString(4, category);
            ps.setDouble(5, amount);
            ps.setString(6, note);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "บันทึกสำเร็จ");

            refreshAll();
            ClearForm();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateData() {

        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "กรุณาเลือกข้อมูลที่แก้ไข");
            return;
        }
        Date utilDate = jDate.getDate();
        if (utilDate == null) {
            JOptionPane.showMessageDialog(this, "กรุณาเลือกวันที่");
            return;
        }
        java.util.Date sqlDate = new java.sql.Date(utilDate.getTime());

        String type = jrbIncome.isSelected() ? "INCOME" : "EXPENSE";
        String category = txtCate.getText().trim();
        String amountText = jforAmount.getText().trim();
        String note = txtAreaNote.getText().trim();

        if (category.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "กรุณากรอกข้อมูลให้ครบ");
            return;
        }

        double amount = Double.parseDouble(amountText);

        String sql = "UPDATE income_expense SET "
                + "record_date =?, type =?, category =?, amount =?, note =? "
                + "WHERE id =?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, (java.sql.Date) sqlDate);
            ps.setString(2, type);
            ps.setString(3, category);
            ps.setDouble(4, amount);
            ps.setString(5, note);
            ps.setInt(6, selectedId);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "แก้ไขสำเร็จ");

            refreshAll();
            ClearForm();
            selectedId = -1;
        } catch (Exception e) {
        }
    }

    private void deleteDate() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "กรุณาเลือกข้อมูลที่ต้อองการ");
            return;
        }

        int comfirm = JOptionPane.showConfirmDialog(this, "คุณต้องการลบข้อมูลหรือไม่", "ยืนยันการลบ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (comfirm != JOptionPane.YES_OPTION) {
            return;
        }
        String sql = "DELETE FROM income_expense WHERE id=? ";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, selectedId);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "ลบสำเร็จ");

            refreshAll();
            ClearForm();
            selectedId = -1;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupTableStyle() {

        jtaTable.setDefaultEditor(Object.class, null);
        jtaTable.setRowHeight(28);
        jtaTable.setShowGrid(false);
        jtaTable.setFillsViewportHeight(true);
        jtaTable.setOpaque(true);

        // Renderer กลาง
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer left = new DefaultTableCellRenderer();
        left.setHorizontalAlignment(JLabel.LEFT);

        // วันที่ (format)
        DefaultTableCellRenderer dateRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {

                super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                setHorizontalAlignment(JLabel.CENTER);

                if (value instanceof java.util.Date) {
                    setText(formatDateThaiShort((java.util.Date) value));
                }

                return this;
            }
        };

        jtaTable.getColumnModel().getColumn(0).setCellRenderer(center);
        jtaTable.getColumnModel().getColumn(1).setCellRenderer(dateRenderer);
        jtaTable.getColumnModel().getColumn(2).setCellRenderer(center);
        jtaTable.getColumnModel().getColumn(3).setCellRenderer(center);
        jtaTable.getColumnModel().getColumn(4).setCellRenderer(center);
        jtaTable.getColumnModel().getColumn(5).setCellRenderer(left);

        JTableHeader header = jtaTable.getTableHeader();
        header.setBorder(BorderFactory.createMatteBorder(
                0, 0, 2, 0, new Color(200, 200, 200)));

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                // 🔥 Style
                label.setBackground(Color.WHITE);
                label.setForeground(new Color(40, 40, 40));
                label.setFont(new Font("Tahoma", Font.BOLD, 15));
                label.setHorizontalAlignment(JLabel.CENTER);

                // เส้นแบ่งสวย ๆ
                label.setBorder(BorderFactory.createMatteBorder(
                        0, 0, 1, 1, new Color(220, 220, 220)));

                return label;
            }
        });

        header.setReorderingAllowed(false); // ไม่ให้ลากสลับคอลัมน์
        header.setResizingAllowed(true);

    }

    public void refreshAll() {
        loadTableData();
        double[] totals = getTotalIncomeExpense();
        loadPieChart3D(totals[0], totals[1]);
    }

    private String formatDateThaiShort(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        return String.format("%02d-%02d-%04d", day, month, year);
    }

    private void ClearForm() {

        jDate.setDate(new java.util.Date());
        txtCate.setText("");
        jforAmount.setText("");
        txtAreaNote.setText("");
        jrbIncome.setSelected(true);
        selectedId = -1; // อย่าลืม Reset ID ที่เลือกค้างไว้ด้วยครับ

    }

    private void setupDateChooser() {

        jDate.setDateFormatString("dd-MM-yyyy");
        jDate.setDate(new java.util.Date());

        // ❗ ทำให้พิมพ์เองไม่ได้ แต่ยังเลือกจากปฏิทินได้
        ((javax.swing.JTextField) jDate.getDateEditor().getUiComponent())
                .setEditable(false);

        ((javax.swing.JTextField) jDate.getDateEditor().getUiComponent())
                .setFont(new Font("Tahoma", Font.BOLD, 18));

// ให้แน่ใจว่ายัง enabled อยู่
        jDate.setEnabled(true);
        jDate.getDateEditor().setEnabled(true);
    }

    /* private void fixYearChooserTheme(Color bg, Color fg) {

        JSpinner spinner = (JSpinner) jycYear.getSpinner();

        spinner.setBackground(bg);
        spinner.setForeground(fg);

        JComponent editor = spinner.getEditor();

        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField tf
                    = ((JSpinner.DefaultEditor) editor).getTextField();

            tf.setBackground(bg);
            tf.setForeground(fg);
            tf.setCaretColor(fg);
            tf.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            tf.setOpaque(true);
        }

        for (Component c : spinner.getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(bg);
                c.setForeground(fg);
            }
        }

        spinner.repaint();
    }*/

 /* private void updateLabelColor(Container container, Color color) {
        for (Component comp : container.getComponents()) {

            if (comp instanceof JLabel) {
                comp.setForeground(color);
            }

            // 🔥 ถ้ามี panel ซ้อนอยู่ ให้ไล่ลึกลงไป
            if (comp instanceof Container) {
                updateLabelColor((Container) comp, color);
            }
        }
    }*/
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new IncomeExpenseFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.ButtonGroup btnThemeGroup;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenuBack;
    private javax.swing.JMenuBar jMenuHead;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemBack;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbbMonth;
    private javax.swing.JFormattedTextField jforAmount;
    private javax.swing.JLabel jlaAmount;
    private javax.swing.JLabel jlaCate;
    private javax.swing.JLabel jlaHead;
    private javax.swing.JLabel jlaNote;
    private javax.swing.JLabel jlaType;
    private javax.swing.JPanel jpaChart;
    private javax.swing.JPanel jpaHead;
    private javax.swing.JPanel jpaLeft;
    private javax.swing.JPanel jpaRight;
    private javax.swing.JRadioButton jrbExpense;
    private javax.swing.JRadioButton jrbIncome;
    private javax.swing.JScrollPane jspNote;
    private javax.swing.JTable jtaTable;
    private com.toedter.calendar.JYearChooser jycYear;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextArea txtAreaNote;
    private javax.swing.JTextField txtCate;
    // End of variables declaration//GEN-END:variables
}
