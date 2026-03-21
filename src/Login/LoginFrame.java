package Login;

import org.mindrot.jbcrypt.BCrypt;
import MainFrame.MainFrame;
import db.DBConnect;
import db.DatabaseSetup;
import db.UserDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;
import model.User;
import session.UserSession;

public class LoginFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginFrame.class.getName());

    // color palette constants
    private static final Color COLOR_BG = new Color(245, 245, 250);
    private static final Color COLOR_PRIMARY = new Color(52, 152, 219);
    private static final Color COLOR_PRIMARY_DARK = new Color(41, 128, 185);
    private static final Color COLOR_TEXT_DARK = new Color(33, 37, 41);
    private static final Color COLOR_LABEL = new Color(44, 62, 80);
    private static final Color COLOR_ERROR = new Color(231, 76, 60);
    private static final Color COLOR_SUCCESS = new Color(46, 204, 113);

    public LoginFrame() {

        setContentPane(new GradientPanel());
        setLayout(new GridBagLayout());

        initComponents();

        initDesign();
        setLocationRelativeTo(null);
        jcbCheck.setOpaque(false);
        txtPassword.setEchoChar('*');
        lblMessage.setHorizontalAlignment(JLabel.CENTER);

        btnLogin.setUI(new RoundedButtonUI());

        jcbCheck.setBackground(getContentPane().getBackground());
        jcbCheck.setForeground(COLOR_TEXT_DARK);
        jcbCheck.setFocusPainted(false);
    }

    private void initDesign() {
        SwingUtilities.updateComponentTreeUI(this);
        // font styles
        Font normal = getThaiFont(Font.PLAIN, 16);
        Font bold = getThaiFont(Font.BOLD, 16);
        Font title = getThaiFont(Font.BOLD, 28);

        // apply backgrounds using constant palette
        getContentPane().setBackground(COLOR_BG);

        RoundedPanel.setBackground(Color.WHITE);
        RoundedPanel.setOpaque(true);
        // headers and labels
        txtHead.setFont(title);
        txtHead.setForeground(new Color(52, 73, 94));

        laUser.setFont(bold);
        laUser.setForeground(COLOR_LABEL);
        laPassword.setFont(bold);
        laPassword.setForeground(COLOR_LABEL);

        // text fields
        txtUsername.setFont(normal);
        txtPassword.setFont(normal);
        txtUsername.setBackground(Color.WHITE);
        txtPassword.setBackground(Color.WHITE);

        // button and checkbox
        btnLogin.setFont(bold);
        btnLogin.setBackground(COLOR_PRIMARY);
        btnLogin.setForeground(Color.WHITE);
        jcbCheck.setFont(normal);
        jcbCheck.setForeground(new Color(52, 73, 94)); // เทาเข้ม modern
        jcbCheck.setBackground(Color.WHITE);
        jcbCheck.setFocusPainted(false);

        // message label
        lblMessage.setFont(bold);
        lblMessage.setForeground(COLOR_ERROR);

        // round textfields
        Color softBorder = new Color(189, 195, 199);

        txtUsername.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new RoundedBorder(15, softBorder),
                javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        txtPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new RoundedBorder(15, softBorder),
                javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        // focus styling for fields
        addFocusStyle(txtUsername, COLOR_PRIMARY);
        addFocusStyle(txtPassword, COLOR_PRIMARY);

        // placeholders (ghost text)
        addGhostText(txtUsername, "Username");
        addGhostText(txtPassword, "Password");
    }

// update RoundedBorder constructor to accept color if necessary (see class file)
    private Font getThaiFont(int style, int size) {
        String[] fonts = {
            "Leelawadee UI",
            "Tahoma",
            "Segoe UI",
            "Noto Sans Thai"
        };

        for (String name : fonts) {
            Font f = new Font(name, style, size);
            if (f.canDisplay('ก')) {
                return f;
            }
        }

        return new Font(Font.SANS_SERIF, style, size);
    }

// display a temporary message in the center label and optionally fade out
    private void showMessage(String text, Color color) {
        lblMessage.setText(text);
        lblMessage.setForeground(color);
        // clear after a short delay
        Timer t = new Timer(2500, (ActionEvent e) -> lblMessage.setText(""));
        t.setRepeats(false);
        t.start();
    }

// shake the window horizontally to indicate error
    private void shakeWindow() {
        final Point orig = getLocation();
        final int distance = 10;
        final int cycles = 6; // back and forth count
        Timer timer = new Timer(20, null);
        timer.addActionListener(new java.awt.event.ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                int xOffset = ((count % 2 == 0) ? distance : -distance);
                setLocation(orig.x + xOffset, orig.y);
                count++;
                if (count > cycles) {
                    timer.stop();
                    setLocation(orig);
                }
            }
        });
        timer.start();
    }

// change border color when field gains/loses focus
    private void addFocusStyle(final JTextField field, final Color highlight) {
        final Border defaultBorder = field.getBorder();
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                        new RoundedBorder(15, highlight),
                        javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15)));
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(defaultBorder);
            }
        });
    }

// simple ghost text implementation for placeholders
    private static class GhostText extends FocusAdapter {

        private final JTextField field;
        private final String ghost;
        private boolean showing;

        public GhostText(JTextField field, String ghost) {
            this.field = field;
            this.ghost = ghost;
            this.showing = true;
            field.setText(ghost);
            field.setForeground(Color.GRAY);
            field.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (showing) {
                field.setText("");
                field.setForeground(Color.BLACK);
                showing = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (field.getText().isEmpty()) {
                field.setText(ghost);
                field.setForeground(Color.GRAY);
                showing = true;
            }
        }
    }

    // helper to avoid repeating ghost text code
    private static void addGhostText(JTextField field, String ghost) {
        new GhostText(field, ghost);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RoundedPanel = new javax.swing.JPanel();
        txtHead = new javax.swing.JLabel();
        laUser = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        laPassword = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jcbCheck = new javax.swing.JCheckBox();
        lblRegister = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Personal Finance Login");
        setBackground(new java.awt.Color(245, 247, 250));
        setPreferredSize(new java.awt.Dimension(500, 400));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        RoundedPanel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RoundedPanel.setPreferredSize(new java.awt.Dimension(500, 300));

        txtHead.setFont(new java.awt.Font("Leelawadee UI", 1, 26)); // NOI18N
        txtHead.setForeground(new java.awt.Color(33, 37, 41));
        txtHead.setText("เข้าสู่ระบบ");

        laUser.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        laUser.setText("UserName");

        txtUsername.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsernameKeyTyped(evt);
            }
        });

        laPassword.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        laPassword.setText("Password");

        btnLogin.setBackground(new java.awt.Color(52, 152, 219));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLogin.setForeground(java.awt.Color.white);
        btnLogin.setText("Login");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblMessage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(255, 51, 51));

        txtPassword.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        jcbCheck.setBackground(new java.awt.Color(255, 255, 255));
        jcbCheck.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jcbCheck.setText("แสดงรหัส");
        jcbCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jcbCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jcbCheckMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jcbCheckMouseExited(evt);
            }
        });
        jcbCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbCheckActionPerformed(evt);
            }
        });

        lblRegister.setFont(new java.awt.Font("Leelawadee UI", 1, 16)); // NOI18N
        lblRegister.setForeground(new java.awt.Color(0, 0, 0));
        lblRegister.setText("ผู้ที่ม่มีบัญชีUsers/ADMIN");
        lblRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegisterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRegisterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblRegisterMouseExited(evt);
            }
        });

        javax.swing.GroupLayout RoundedPanelLayout = new javax.swing.GroupLayout(RoundedPanel);
        RoundedPanel.setLayout(RoundedPanelLayout);
        RoundedPanelLayout.setHorizontalGroup(
            RoundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoundedPanelLayout.createSequentialGroup()
                .addGroup(RoundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RoundedPanelLayout.createSequentialGroup()
                        .addGroup(RoundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RoundedPanelLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RoundedPanelLayout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(txtHead))
                            .addGroup(RoundedPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(laUser, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RoundedPanelLayout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RoundedPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(laPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RoundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbCheck)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 94, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoundedPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblRegister)))
                .addContainerGap())
        );
        RoundedPanelLayout.setVerticalGroup(
            RoundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoundedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHead, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RoundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RoundedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(RoundedPanel, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbCheckActionPerformed
        if (jcbCheck.isSelected()) {
            txtPassword.setEchoChar((char) 0);
            jcbCheck.setText("ซ่อนรหัส");
        } else {
            txtPassword.setEchoChar('*');
            jcbCheck.setText("แสดงรหัส");
        }
    }//GEN-LAST:event_jcbCheckActionPerformed

    private void jcbCheckMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbCheckMouseExited
        if (jcbCheck.isSelected()) {
            jcbCheck.setForeground(new Color(52, 73, 94));
        } else {
            jcbCheck.setForeground(COLOR_TEXT_DARK);
        }
    }//GEN-LAST:event_jcbCheckMouseExited

    private void jcbCheckMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbCheckMouseEntered
        jcbCheck.setForeground(new Color(41, 128, 185));
    }//GEN-LAST:event_jcbCheckMouseEntered

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        btnLogin.doClick();
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

        String username = txtUsername.getText().trim();
        char[] pass = txtPassword.getPassword();
        String password = new String(pass);

        if (username.isEmpty() || username.equals("Username")) {
            showMessage("กรุณากรอก Username", COLOR_ERROR);
            return;
        }

        if (password.isEmpty() || password.equals("Password")) {
            showMessage("กรุณากรอก Password", COLOR_ERROR);
            return;
        }

        btnLogin.setEnabled(false);
        btnLogin.setText("Loading...");

        UserDAO dao = new UserDAO();
        User user = dao.login(username, password);

        java.util.Arrays.fill(pass, '0');

        if (user != null) {

            UserSession.setUser(user);

            showMessage("เข้าสู่ระบบสำเร็จ!", COLOR_SUCCESS);

            Timer t = new Timer(800, e -> {
                new MainFrame().setVisible(true);
                dispose();
            });
            t.setRepeats(false);
            t.start();

        } else {
            showMessage("Username หรือ Password ไม่ถูก", COLOR_ERROR);
            shakeWindow();
            btnLogin.setEnabled(true);
            btnLogin.setText("Login");

        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        btnLogin.setBackground(COLOR_PRIMARY);
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        btnLogin.setBackground(COLOR_PRIMARY_DARK);
    }//GEN-LAST:event_btnLoginMouseEntered

    private void txtUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyTyped
        lblMessage.setText("");
    }//GEN-LAST:event_txtUsernameKeyTyped

    private void lblRegisterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegisterMouseEntered
        lblRegister.setText("สมัครสมาชิก");
        lblRegister.setForeground(Color.RED);

    }//GEN-LAST:event_lblRegisterMouseEntered

    private void lblRegisterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegisterMouseExited
        lblRegister.setText("ผู้ที่ม่มีบัญชีUsers/ADMIN");
        lblRegister.setForeground(Color.BLACK);

    }//GEN-LAST:event_lblRegisterMouseExited

    private void lblRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegisterMouseClicked
        new AddUserFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblRegisterMouseClicked

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        btnLogin.doClick();
    }//GEN-LAST:event_txtUsernameActionPerformed

    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DBConnect.initDatabase();
                DBConnect.seedData();
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RoundedPanel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox jcbCheck;
    private javax.swing.JLabel laPassword;
    private javax.swing.JLabel laUser;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JLabel txtHead;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
