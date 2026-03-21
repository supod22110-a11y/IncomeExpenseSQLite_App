package Login;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class AddAdminFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddAdminFrame.class.getName());

    public AddAdminFrame() {
        initComponents();
        setLocationRelativeTo(this);
        txtPass.setEchoChar('*');
        btnBack.setUI(new RoundedButtonUI());
        btnLogin.setUI(new RoundedButtonUI());
        btnSubmit.setUI(new RoundedButtonUI());
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addGhostText(txtUsers, "Admin Username");
        addGhostText(txtPass, "Password");
    }

    private static class GhostText extends java.awt.event.FocusAdapter {

        private final javax.swing.JTextField field;
        private final String ghost;
        private boolean showing;

        public GhostText(javax.swing.JTextField field, String ghost) {
            this.field = field;
            this.ghost = ghost;
            this.showing = true;

            field.setText(ghost);
            field.setForeground(java.awt.Color.GRAY);
            field.addFocusListener(this);
        }

        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (showing) {
                field.setText("");
                field.setForeground(java.awt.Color.BLACK);
                showing = false;
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent e) {
            if (field.getText().isEmpty()) {
                field.setText(ghost);
                field.setForeground(java.awt.Color.GRAY);
                showing = true;
            }
        }
    }

    private static void addGhostText(javax.swing.JTextField field, String ghost) {
        new GhostText(field, ghost);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpaHead = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jpaCenter = new javax.swing.JPanel();
        jLaHead = new javax.swing.JLabel();
        txtUsers = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        jpaSouth = new javax.swing.JPanel();
        btnSubmit = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnBack.setBackground(new java.awt.Color(0, 255, 255));
        btnBack.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(0, 0, 0));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(255, 153, 0));
        btnLogin.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpaHeadLayout = new javax.swing.GroupLayout(jpaHead);
        jpaHead.setLayout(jpaHeadLayout);
        jpaHeadLayout.setHorizontalGroup(
            jpaHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpaHeadLayout.setVerticalGroup(
            jpaHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpaHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLaHead.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        jLaHead.setText("สมัครสมาชิกสำหรับAdmin");

        txtUsers.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        txtUsers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsersKeyPressed(evt);
            }
        });

        txtPass.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jpaCenterLayout = new javax.swing.GroupLayout(jpaCenter);
        jpaCenter.setLayout(jpaCenterLayout);
        jpaCenterLayout.setHorizontalGroup(
            jpaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaCenterLayout.createSequentialGroup()
                .addGroup(jpaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpaCenterLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLaHead))
                    .addGroup(jpaCenterLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jpaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(txtPass))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jpaCenterLayout.setVerticalGroup(
            jpaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLaHead, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSubmit.setBackground(new java.awt.Color(0, 255, 102));
        btnSubmit.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        btnSubmit.setText("ยืนยัน");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpaSouthLayout = new javax.swing.GroupLayout(jpaSouth);
        jpaSouth.setLayout(jpaSouthLayout);
        jpaSouthLayout.setHorizontalGroup(
            jpaSouthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpaSouthLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );
        jpaSouthLayout.setVerticalGroup(
            jpaSouthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaSouthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMessage.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpaHead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpaCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jpaSouth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(lblMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpaHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpaCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpaSouth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new AddUserFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        registerAdmin();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void txtUsersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsersKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            registerAdmin();
        }
    }//GEN-LAST:event_txtUsersKeyPressed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            registerAdmin();
        }
    }//GEN-LAST:event_txtPassKeyPressed
    private void registerAdmin() {

        String username = txtUsers.getText().trim();
        String password = new String(txtPass.getPassword());

        // 🔥 validate
        if (username.isEmpty() || username.equalsIgnoreCase("admin username")) {
            showMessage("กรุณากรอก Username", Color.RED);
            return;
        }

        if (password.isEmpty() || password.equals("Password")) {
            showMessage("กรุณากรอก Password", Color.RED);
            return;
        }

        if (password.length() < 4) {
            showMessage("รหัสผ่านต้องมากกว่า 4 ตัว", Color.RED);
            return;
        }

        try {
            db.UserDAO dao = new db.UserDAO();

            // 🔥 เช็ค user ซ้ำ
            if (dao.isUserExists(username)) {
                showMessage("Username นี้ถูกใช้แล้ว", Color.RED);
                return;
            }

            // 🔐 ADMIN KEY (กันคนมั่วสร้าง admin)
            String key = JOptionPane.showInputDialog(this, "กรอกรหัส Admin:");

            String ADMIN_KEY = "1234"; // 🔥 เปลี่ยนเอง

            if (key == null || !key.trim().equals(ADMIN_KEY)) {
                showMessage("รหัส Admin ไม่ถูกต้อง", Color.RED);
                return;
            }

            boolean success = dao.registerAdmin(username, password);

            if (success) {

                showMessage("สร้าง Admin สำเร็จ", new Color(46, 204, 113));

                // clear input
                txtUsers.setText("");
                txtPass.setText("");

                javax.swing.Timer t = new javax.swing.Timer(1200, e -> {
                    new LoginFrame().setVisible(true);
                    dispose();
                });

                t.setRepeats(false);
                t.start();

            } else {
                showMessage("สมัครไม่สำเร็จ", Color.RED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("เกิดข้อผิดพลาด", Color.RED);
        }
    }

    private void showMessage(String msg, java.awt.Color color) {

        lblMessage.setText(msg);
        lblMessage.setForeground(color);

        javax.swing.Timer t = new javax.swing.Timer(2500, e -> {
            lblMessage.setText("");
        });

        t.setRepeats(false);
        t.start();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new AddAdminFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLaHead;
    private javax.swing.JPanel jpaCenter;
    private javax.swing.JPanel jpaHead;
    private javax.swing.JPanel jpaSouth;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsers;
    // End of variables declaration//GEN-END:variables
}
