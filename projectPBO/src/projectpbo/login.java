/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectpbo;

import config.db_connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author roshy
 */
public class login extends javax.swing.JFrame {

    public boolean tutup;
    
    public login() {
        initComponents();
        btn_login.setForeground(new java.awt.Color(255,102,0));
        btn_login.setBackground(new java.awt.Color(255,255,255));
    }

    void bersih(){
        ip_username.setText("username");
        ip_password.setText("password");
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_login = new javax.swing.JPanel();
        pn_password = new javax.swing.JPanel();
        gb_username1 = new javax.swing.JLabel();
        ip_password = new javax.swing.JPasswordField();
        pn_username = new javax.swing.JPanel();
        gb_username = new javax.swing.JLabel();
        ip_username = new javax.swing.JTextField();
        lb_login = new javax.swing.JLabel();
        btn_login = new javax.swing.JToggleButton();
        lb_info = new javax.swing.JLabel();
        gb_login = new javax.swing.JLabel();
        gb_exit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        pn_login.setBackground(new java.awt.Color(255, 255, 255));

        pn_password.setBackground(new java.awt.Color(255, 255, 255));

        gb_username1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectpbo/img/password.png"))); // NOI18N

        ip_password.setText("Password");
        ip_password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ip_passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ip_passwordFocusLost(evt);
            }
        });

        javax.swing.GroupLayout pn_passwordLayout = new javax.swing.GroupLayout(pn_password);
        pn_password.setLayout(pn_passwordLayout);
        pn_passwordLayout.setHorizontalGroup(
            pn_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_passwordLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(gb_username1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ip_password, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pn_passwordLayout.setVerticalGroup(
            pn_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_passwordLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pn_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ip_password)
                    .addComponent(gb_username1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        pn_username.setBackground(new java.awt.Color(255, 255, 255));

        gb_username.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectpbo/img/username.png"))); // NOI18N

        ip_username.setText("Username");
        ip_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ip_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ip_usernameFocusLost(evt);
            }
        });

        javax.swing.GroupLayout pn_usernameLayout = new javax.swing.GroupLayout(pn_username);
        pn_username.setLayout(pn_usernameLayout);
        pn_usernameLayout.setHorizontalGroup(
            pn_usernameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_usernameLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(gb_username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ip_username, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pn_usernameLayout.setVerticalGroup(
            pn_usernameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_usernameLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pn_usernameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ip_username)
                    .addComponent(gb_username, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        lb_login.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        lb_login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_login.setText("Welcome To InventoCash");

        btn_login.setText("Login");
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_loginMouseClicked(evt);
            }
        });

        lb_info.setForeground(new java.awt.Color(204, 0, 0));
        lb_info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        gb_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectpbo/img/Capture.PNG"))); // NOI18N

        gb_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectpbo/img/exit.png"))); // NOI18N
        gb_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gb_exitMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setText("Developer by Kelompok 7");

        javax.swing.GroupLayout pn_loginLayout = new javax.swing.GroupLayout(pn_login);
        pn_login.setLayout(pn_loginLayout);
        pn_loginLayout.setHorizontalGroup(
            pn_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_loginLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pn_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lb_login)
                    .addComponent(pn_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_info, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(pn_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_loginLayout.createSequentialGroup()
                        .addComponent(gb_login)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_loginLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(17, 17, 17))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_loginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gb_exit)
                .addContainerGap())
        );
        pn_loginLayout.setVerticalGroup(
            pn_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_loginLayout.createSequentialGroup()
                .addGroup(pn_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_loginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(gb_exit)
                        .addGap(22, 22, 22)
                        .addComponent(gb_login))
                    .addGroup(pn_loginLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lb_login)
                        .addGap(33, 33, 33)
                        .addComponent(pn_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pn_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_login)))
                .addGroup(pn_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_loginLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lb_info, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_loginLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void gb_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gb_exitMouseClicked
        dispose();
    }//GEN-LAST:event_gb_exitMouseClicked

    private void ip_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ip_usernameFocusGained
        String username=ip_username.getText();
        if(username.equals("Username")){
            ip_username.setText("");
        }
    }//GEN-LAST:event_ip_usernameFocusGained

    private void ip_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ip_usernameFocusLost
        String username=ip_username.getText();
        if(username.equals("")||username.equals("Username")){
            ip_username.setText("Username");
        }
    }//GEN-LAST:event_ip_usernameFocusLost

    private void ip_passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ip_passwordFocusGained
        String pass=ip_password.getText();
        if(pass.equals("Password")){
            ip_password.setText("");
        }
    }//GEN-LAST:event_ip_passwordFocusGained

    private void ip_passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ip_passwordFocusLost
        String pass=ip_password.getText();
        if(pass.equals("")||pass.equals("")){
            ip_password.setText("Password");
        }
    }//GEN-LAST:event_ip_passwordFocusLost

    private void btn_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseClicked
        String username = ip_username.getText();
        String password = ip_password.getText();
        
        try{
            // membuat koneksi ke database
            Connection connection = db_connect.connect();
            
            //melakukan query untuk memeriksa login
            String query = "SELECT id, username, role FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int userId = resultSet.getInt("id");
                        String userRole = resultSet.getString("role");
                        String get_username = resultSet.getString("username");
                        
                        switch(userRole){
                            case "Admin":
                                admin.dashboard home_admin = new admin.dashboard(userId, get_username, userRole);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     home_admin.setVisible(true);
                                this.dispose();
                                break;
                            case "Kasir":
                                kasir.dashboard home_kasir = new kasir.dashboard(userId, get_username, userRole);
                                home_kasir.setVisible(true);
                                this.dispose();
                                break;
                            case "Gudang":
                                gudang.dashboard home_gudang = new gudang.dashboard(userId, get_username, userRole);
                                home_gudang.setVisible(true);
                                this.dispose();
                                break;
                            default:
                                lb_info.setVisible(true);
                                lb_info.setText("Role tidak valid");
                                break;
                        }
                    } else {
                        lb_info.setVisible(true);
                        lb_info.setText("Username atau Password salah. Coba lagi.");
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
           
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_loginMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_login;
    private javax.swing.JLabel gb_exit;
    private javax.swing.JLabel gb_login;
    private javax.swing.JLabel gb_username;
    private javax.swing.JLabel gb_username1;
    private javax.swing.JPasswordField ip_password;
    private javax.swing.JTextField ip_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lb_info;
    private javax.swing.JLabel lb_login;
    private javax.swing.JPanel pn_login;
    private javax.swing.JPanel pn_password;
    private javax.swing.JPanel pn_username;
    // End of variables declaration//GEN-END:variables
}
