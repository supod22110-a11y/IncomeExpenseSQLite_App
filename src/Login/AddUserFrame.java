package Login;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;

public class AddUserFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddUserFrame.class.getName());

    public AddUserFrame() {
        initComponents();
        setLocationRelativeTo(null);
        btnLogin.setUI(new RoundedButtonUI());
        btnAdmin.setUI(new RoundedButtonUI());
        btnSubmit.setUI(new RoundedButtonUI());
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtPassword.setEchoChar('*');
        addGhostText(txtUsername, "Username");
        addGhostText(txtPassword, "Password");

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
            field.setForeground(Color.GRAY);
            field.addFocusListener(this);
        }

        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (showing) {
                field.setText("");
                field.setForeground(Color.BLACK);

                showing = false;
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent e) {
            if (field.getText().isEmpty()) {
                field.setText(ghost);
                field.setForeground(Color.GRAY);
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

        jpaNorth = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        jpaCenter = new javax.swing.JPanel();
        jLaHead = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jpaSouth = new javax.swing.JPanel();
        btnSubmit = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnLogin.setBackground(new java.awt.Color(255, 255, 0));
        btnLogin.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        btnLogin.setText("Back");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnAdmin.setBackground(java.awt.Color.green);
        btnAdmin.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        btnAdmin.setText("ADMIN");
        btnAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpaNorthLayout = new javax.swing.GroupLayout(jpaNorth);
        jpaNorth.setLayout(jpaNorthLayout);
        jpaNorthLayout.setHorizontalGroup(
            jpaNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpaNorthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdmin)
                .addContainerGap())
        );
        jpaNorthLayout.setVerticalGroup(
            jpaNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpaNorthLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpaNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdmin)
                    .addComponent(btnLogin))
                .addContainerGap())
        );

        jLaHead.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        jLaHead.setText("สมัครสมาชิกสำหรับผู้ใช้");

        txtUsername.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jpaCenterLayout = new javax.swing.GroupLayout(jpaCenter);
        jpaCenter.setLayout(jpaCenterLayout);
        jpaCenterLayout.setHorizontalGroup(
            jpaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaCenterLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLaHead)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpaCenterLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jpaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        jpaCenterLayout.setVerticalGroup(
            jpaCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaCenterLayout.createSequentialGroup()
                .addComponent(jLaHead, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );

        btnSubmit.setBackground(java.awt.Color.red);
        btnSubmit.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        btnSubmit.setText("submit");
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                .addComponent(btnSubmit)
                .addGap(155, 155, 155))
        );
        jpaSouthLayout.setVerticalGroup(
            jpaSouthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaSouthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblMessage.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        lblMessage.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpaNorth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpaCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpaSouth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(lblMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpaNorth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpaCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpaSouth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        new AddAdminFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        registerUser();

    }//GEN-LAST:event_btnSubmitActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            registerUser();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            registerUser();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void registerUser() {

        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || username.equals("Username")) {
            showMessage("กรุณากรอก Username", Color.RED);
            return;
        }

        if (password.isEmpty()) {
            showMessage("กรุณากรอก Password", Color.RED);
            return;
        }

        if (password.length() < 4) {
            showMessage("รหัสผ่านต้องมากกว่า 4 ตัว", Color.RED);
            return;
        }

        db.UserDAO dao = new db.UserDAO();

        if (dao.isUserExists(username)) {
            showMessage("Username นี้ถูกใช้แล้ว", Color.RED);
            return;
        }

        boolean success = dao.register(username, password, "USER");

        if (success) {
            showMessage("สมัครสมาชิกสำเร็จ", new Color(46, 204, 113));

            javax.swing.Timer t = new javax.swing.Timer(1200, e -> {
                new LoginFrame().setVisible(true);
                dispose();
            });

            t.setRepeats(false); 
            t.start();

            txtUsername.setText("");
            txtPassword.setText("");

        } else {
            showMessage("สมัครไม่สำเร็จ", Color.RED);
        }
    }

    private void showMessage(String msg, Color color) {

        lblMessage.setText(msg);
        lblMessage.setForeground(color);

        javax.swing.Timer t = new javax.swing.Timer(2500, e -> {
            lblMessage.setText("");
        });

        t.setRepeats(false);
        t.start();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new AddUserFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLaHead;
    private javax.swing.JPanel jpaCenter;
    private javax.swing.JPanel jpaNorth;
    private javax.swing.JPanel jpaSouth;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
