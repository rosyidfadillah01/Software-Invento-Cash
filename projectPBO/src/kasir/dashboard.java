package kasir;

import javax.swing.table.DefaultTableModel;
import config.db_connect;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;


public class dashboard extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */
    private final int userId;
    private final String userRole;
    private DefaultTableModel model;
    
    /**
     * Creates new form admin
     * @param userId
     * @param username
     * @param userRole
     */
    
    
    
    public dashboard(int userId, String username, String userRole) {
        initComponents();
        dashboard.setVisible(true);
        pn_kasir.setVisible(false);
        pn_diskon.setVisible(false);
        pn_edit_profile.setVisible(false);
        pn_barangCacat.setVisible(false);
        
        //back white fore orange
        btn_Kasir.setForeground(new java.awt.Color(255,102,0));
        btn_Kasir.setBackground(new java.awt.Color(255,255,255));
        
        btn_Diskon.setForeground(new java.awt.Color(255,102,0));
        btn_Diskon.setBackground(new java.awt.Color(255,255,255));
        
        btn_editProfile.setForeground(new java.awt.Color(255,102,0));
        btn_editProfile.setBackground(new java.awt.Color(255,255,255));
        
        btn_barangCacat.setForeground(new java.awt.Color(255,102,0));
        btn_barangCacat.setBackground(new java.awt.Color(255,255,255));
        
        btn_logoutKasir.setForeground(new java.awt.Color(255,102,0));
        btn_logoutKasir.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_dashboard.setBackground(new java.awt.Color(255,102,0));
        btn_dashboard.setForeground(new java.awt.Color(255,255,255));
        
        
        this.userId = userId;
        this.userRole = userRole;
        //this.username = username;
        // Tampilkan data admin
        showNavbar();
    }
    
    private int calculateTotal(int total, int columnIndex) {
        for (int row = 0; row < model.getRowCount(); row++) {
            total += (int) model.getValueAt(row, columnIndex);
        }

        return total;
    }
    
    private Date parseDateString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
    
    void bersihDiskon(){
        no_seri.setText("");
        nama_brg.setText("");
        ip_diskon.setText("");
        harga_awal.setText("");
        harga_akhir.setText("");
    }
    
    void bersihBarangCacat(){
        p_NoSeriRequest.setText("");
        p_namaBarangRequest.setText("");
        p_StokRequest.setValue(0);
        ip_alasanCacat.setText("");
    }
    
    void bersihKasir(){
        ip_noSeriKasir.setText("");
        dt_namaBarangKasir.setText("");
        ip_jumlahKasir.setValue(0);
        model.setRowCount(0);
        lb_totalHarga.setText("");
        ip_bayarKasir.setText("");
        ip_kembaliKasir.setText("");
    }
    
    private static void hideLabelAfterDelay(JLabel label, int delay) {
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
                ((Timer) e.getSource()).stop(); // Stop the timer after hiding the label
            }
        });

        timer.setRepeats(false); // Set to false so it only runs once
        timer.start(); // Start the timer
    }
    
    private void showNavbar() {
                
        try{
            // membuat koneksi ke database
            Connection connection = db_connect.connect();
            
            //melakukan query untuk memeriksa login
            String query = "SELECT id_karyawan, NamaLengkap, jabatan FROM data_karyawan WHERE id_karyawan = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                int id_karyawan = this.userId ;
                statement.setInt(1, id_karyawan);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String namaLengkap = resultSet.getString("NamaLengkap");
                        String jabatan = resultSet.getString("jabatan");
                        
                        lb_nama.setText(namaLengkap);
                        lb_jabatan.setText(jabatan);
                        
                        
                        hitungPenjualanHariIni(namaLengkap);
                        hitungPenjualanBulanIni(namaLengkap);
                        hitungBarangBulanIni(namaLengkap);
                    }else{
                        lb_welcome1.setText("Database Tidak Sinkron");
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
           
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
       lb_id.setText(String.valueOf(userId));
       
       lb_jabatan.setVisible(true);
       lb_jabatan.setText(userRole);
       
       LocalDate today = LocalDate.now();
       String formattedDate = today.toString();

       lb_date.setVisible(true);
       lb_date.setText(formattedDate);
              
    }
    
    private void hitungPenjualanHariIni(String namaKasir){
        try{
            // membuat koneksi ke database
            Connection connection = db_connect.connect();
            
            //melakukan query untuk memeriksa login
            String query = "SELECT nama_kasir, SUM(total_penjualan) AS hasil_today FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() AND tgl_penjualan < CURDATE() + INTERVAL 1 DAY AND nama_kasir = ? GROUP BY nama_kasir";
            try (PreparedStatement statement = connection.prepareStatement(query)) {   
                statement.setString(1, namaKasir);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int hasilToday = resultSet.getInt("hasil_today");
                                                
                        dt_today.setValue(hasilToday);
                    }else{
                        dt_today.setValue(0);
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
           
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    private void hitungPenjualanBulanIni(String namaKasir){
        try{
            // membuat koneksi ke database
            Connection connection = db_connect.connect();
            
            //melakukan query untuk memeriksa login
            String query = "SELECT nama_kasir, SUM(total_penjualan) AS hasil_month FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 30 DAY AND nama_kasir = ? GROUP BY nama_kasir";
            try (PreparedStatement statement = connection.prepareStatement(query)) {   
                statement.setString(1, namaKasir);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        long hasilToday = resultSet.getInt("hasil_month");
                        
                        dt_month.setValue(hasilToday);
                    }else{
                        dt_month.setValue(0);
                        
                        return;
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
           
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    private void hitungBarangBulanIni(String namaKasir){
        try{
            // membuat koneksi ke database
            Connection connection = db_connect.connect();
            
            //melakukan query untuk memeriksa login
            String query = "SELECT nama_kasir, SUM(stok) AS hasil_today FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 30 DAY AND nama_kasir = ? GROUP BY nama_kasir";
            try (PreparedStatement statement = connection.prepareStatement(query)) {   
                statement.setString(1, namaKasir);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int hasilToday = resultSet.getInt("hasil_today");
                        
                        d_br.setText(String.valueOf(hasilToday));
                    }else{
                        d_br.setText("Belum Terjual ");
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
           
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_utama = new javax.swing.JPanel();
        pn_atas = new javax.swing.JLayeredPane();
        gb_dashboard = new javax.swing.JLabel();
        pn_1 = new javax.swing.JPanel();
        lb_hello = new javax.swing.JLabel();
        lb_welcome1 = new javax.swing.JLabel();
        lb_nama = new javax.swing.JLabel();
        pn_2 = new javax.swing.JPanel();
        lb_role = new javax.swing.JLabel();
        lb_jabatan = new javax.swing.JLabel();
        pn_3 = new javax.swing.JPanel();
        lb_tanggal = new javax.swing.JLabel();
        lb_date = new javax.swing.JLabel();
        pn_4 = new javax.swing.JPanel();
        lb_id = new javax.swing.JLabel();
        lb_idKaryawan = new javax.swing.JLabel();
        pn_samping = new javax.swing.JLayeredPane();
        btn_dashboard = new javax.swing.JButton();
        btn_Kasir = new javax.swing.JButton();
        btn_Diskon = new javax.swing.JButton();
        btn_editProfile = new javax.swing.JButton();
        btn_logoutKasir = new javax.swing.JButton();
        btn_barangCacat = new javax.swing.JButton();
        pn_content = new javax.swing.JLayeredPane();
        dashboard = new javax.swing.JPanel();
        pn_penjualan_hari_ini = new javax.swing.JPanel();
        lb_bg = new javax.swing.JLabel();
        dt_today = new javax.swing.JFormattedTextField();
        pn_pendapatan_bulan_ini = new javax.swing.JPanel();
        lb_bdp = new javax.swing.JLabel();
        dt_month = new javax.swing.JFormattedTextField();
        pn_total_terjual = new javax.swing.JPanel();
        lb_br = new javax.swing.JLabel();
        d_br = new javax.swing.JLabel();
        pn_kasir = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ip_noSeriKasir = new javax.swing.JTextField();
        btn_insertTable = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_kasir = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lb_totalHarga = new javax.swing.JFormattedTextField();
        ip_jumlahKasir = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dt_namaBarangKasir = new javax.swing.JLabel();
        btn_deleteTbKasir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ip_kembaliKasir = new javax.swing.JFormattedTextField();
        lb_infoKasir = new javax.swing.JLabel();
        btn_hitung = new javax.swing.JButton();
        ip_bayarKasir = new javax.swing.JTextField();
        pn_diskon = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        lb_infoDiskon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_diskon = new javax.swing.JTable();
        no_seri = new javax.swing.JTextField();
        ip_diskon = new javax.swing.JTextField();
        btn_inputdiskon = new javax.swing.JButton();
        input_noseri = new javax.swing.JLabel();
        input_namabrg = new javax.swing.JLabel();
        input_diskon = new javax.swing.JLabel();
        input_hargaawl = new javax.swing.JLabel();
        input_hargaakhr = new javax.swing.JLabel();
        btn_deleteDiskon = new javax.swing.JButton();
        nama_brg = new javax.swing.JLabel();
        harga_awal = new javax.swing.JLabel();
        harga_akhir = new javax.swing.JLabel();
        input_diskon1 = new javax.swing.JLabel();
        pn_barangCacat = new javax.swing.JPanel();
        btn_simpanRequest = new javax.swing.JButton();
        p_StokRequest = new javax.swing.JSpinner();
        title_StokRequest = new javax.swing.JLabel();
        p_NoSeriRequest = new javax.swing.JTextField();
        title_NoSeriRequest = new javax.swing.JLabel();
        title_namaBarangRequest = new javax.swing.JLabel();
        title_Request = new javax.swing.JLabel();
        p_namaBarangRequest = new javax.swing.JLabel();
        lb_infoBarangCacat = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ip_alasanCacat = new javax.swing.JTextArea();
        title_StokRequest1 = new javax.swing.JLabel();
        pn_edit_profile = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btn_simpanProfil = new javax.swing.JButton();
        ip_namaProfil = new javax.swing.JTextField();
        ip_nikProfil = new javax.swing.JTextField();
        ip_emailProfil = new javax.swing.JTextField();
        ip_hpProfil = new javax.swing.JTextField();
        ip_kontakProfil = new javax.swing.JTextField();
        ip_npwpProfil = new javax.swing.JTextField();
        ip_rekeningProfil = new javax.swing.JTextField();
        lb_idProfil = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ip_alamatProfil = new javax.swing.JTextArea();
        rb_perempuan = new javax.swing.JRadioButton();
        rb_laki = new javax.swing.JRadioButton();
        ip_tglProfil = new com.toedter.calendar.JDateChooser();
        lb_infoProfil = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pn_utama.setBackground(new java.awt.Color(255, 255, 255));

        gb_dashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gb_dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectpbo/img/icon_dash.png"))); // NOI18N

        pn_1.setBackground(new java.awt.Color(255, 102, 51));

        lb_hello.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lb_hello.setForeground(new java.awt.Color(255, 255, 255));
        lb_hello.setText("Hallo,");

        lb_welcome1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lb_welcome1.setForeground(new java.awt.Color(255, 255, 255));
        lb_welcome1.setText("Selamat Datang");

        lb_nama.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_nama.setForeground(new java.awt.Color(255, 255, 255));
        lb_nama.setText("Nama");

        javax.swing.GroupLayout pn_1Layout = new javax.swing.GroupLayout(pn_1);
        pn_1.setLayout(pn_1Layout);
        pn_1Layout.setHorizontalGroup(
            pn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_welcome1)
                    .addGroup(pn_1Layout.createSequentialGroup()
                        .addComponent(lb_hello)
                        .addGap(18, 18, 18)
                        .addComponent(lb_nama)))
                .addContainerGap(164, Short.MAX_VALUE))
        );
        pn_1Layout.setVerticalGroup(
            pn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_hello)
                    .addComponent(lb_nama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_welcome1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_2.setBackground(new java.awt.Color(255, 102, 51));

        lb_role.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lb_role.setForeground(new java.awt.Color(255, 255, 255));
        lb_role.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_role.setText("Jabatan");

        lb_jabatan.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_jabatan.setForeground(new java.awt.Color(255, 255, 255));
        lb_jabatan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_jabatan.setText("KASIR");

        javax.swing.GroupLayout pn_2Layout = new javax.swing.GroupLayout(pn_2);
        pn_2.setLayout(pn_2Layout);
        pn_2Layout.setHorizontalGroup(
            pn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lb_role)
                    .addComponent(lb_jabatan))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        pn_2Layout.setVerticalGroup(
            pn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lb_role)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_jabatan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_3.setBackground(new java.awt.Color(255, 102, 51));

        lb_tanggal.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lb_tanggal.setForeground(new java.awt.Color(255, 255, 255));
        lb_tanggal.setText("Tanggal");

        lb_date.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_date.setForeground(new java.awt.Color(255, 255, 255));
        lb_date.setText("12-11");

        javax.swing.GroupLayout pn_3Layout = new javax.swing.GroupLayout(pn_3);
        pn_3.setLayout(pn_3Layout);
        pn_3Layout.setHorizontalGroup(
            pn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lb_tanggal)
                    .addComponent(lb_date))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pn_3Layout.setVerticalGroup(
            pn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lb_tanggal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_date)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_4.setBackground(new java.awt.Color(255, 102, 51));

        lb_id.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_id.setForeground(new java.awt.Color(255, 255, 255));
        lb_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_id.setText("2");

        lb_idKaryawan.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lb_idKaryawan.setForeground(new java.awt.Color(255, 255, 255));
        lb_idKaryawan.setText("ID Karyawan");

        javax.swing.GroupLayout pn_4Layout = new javax.swing.GroupLayout(pn_4);
        pn_4.setLayout(pn_4Layout);
        pn_4Layout.setHorizontalGroup(
            pn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_4Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(pn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lb_id)
                    .addComponent(lb_idKaryawan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_4Layout.setVerticalGroup(
            pn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lb_idKaryawan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_id)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_atas.setLayer(gb_dashboard, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_atas.setLayer(pn_1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_atas.setLayer(pn_2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_atas.setLayer(pn_3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_atas.setLayer(pn_4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pn_atasLayout = new javax.swing.GroupLayout(pn_atas);
        pn_atas.setLayout(pn_atasLayout);
        pn_atasLayout.setHorizontalGroup(
            pn_atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_atasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gb_dashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_atasLayout.setVerticalGroup(
            pn_atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pn_atasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gb_dashboard)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pn_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btn_dashboard.setBackground(new java.awt.Color(255, 102, 51));
        btn_dashboard.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        btn_dashboard.setText("DASHBOARD");
        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseClicked(evt);
            }
        });

        btn_Kasir.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_Kasir.setForeground(new java.awt.Color(255, 102, 0));
        btn_Kasir.setText("KASIR");
        btn_Kasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_KasirMouseClicked(evt);
            }
        });

        btn_Diskon.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_Diskon.setForeground(new java.awt.Color(255, 102, 0));
        btn_Diskon.setText("DISKON");
        btn_Diskon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DiskonMouseClicked(evt);
            }
        });

        btn_editProfile.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_editProfile.setForeground(new java.awt.Color(255, 102, 0));
        btn_editProfile.setText("EDIT PROFIL");
        btn_editProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editProfileMouseClicked(evt);
            }
        });

        btn_logoutKasir.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_logoutKasir.setForeground(new java.awt.Color(255, 102, 0));
        btn_logoutKasir.setText("LOGOUT");
        btn_logoutKasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutKasirMouseClicked(evt);
            }
        });

        btn_barangCacat.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_barangCacat.setForeground(new java.awt.Color(255, 102, 0));
        btn_barangCacat.setText("BARANG CACAT");
        btn_barangCacat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_barangCacatMouseClicked(evt);
            }
        });

        pn_samping.setLayer(btn_dashboard, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_Kasir, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_Diskon, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_editProfile, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_logoutKasir, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_barangCacat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pn_sampingLayout = new javax.swing.GroupLayout(pn_samping);
        pn_samping.setLayout(pn_sampingLayout);
        pn_sampingLayout.setHorizontalGroup(
            pn_sampingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Kasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Diskon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_barangCacat, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addComponent(btn_editProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_logoutKasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pn_sampingLayout.setVerticalGroup(
            pn_sampingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_sampingLayout.createSequentialGroup()
                .addComponent(btn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Kasir, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_barangCacat, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_logoutKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pn_content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboard.setBackground(new java.awt.Color(255, 255, 255));

        pn_penjualan_hari_ini.setBackground(new java.awt.Color(255, 102, 51));

        lb_bg.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lb_bg.setForeground(new java.awt.Color(255, 255, 255));
        lb_bg.setText("PENJUALAN HARI INI");

        dt_today.setBackground(pn_penjualan_hari_ini.getBackground());
        dt_today.setBorder(null);
        dt_today.setForeground(new java.awt.Color(255, 255, 255));
        dt_today.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("Rp #,##0"))));
        dt_today.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        dt_today.setRequestFocusEnabled(false);
        dt_today.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout pn_penjualan_hari_iniLayout = new javax.swing.GroupLayout(pn_penjualan_hari_ini);
        pn_penjualan_hari_ini.setLayout(pn_penjualan_hari_iniLayout);
        pn_penjualan_hari_iniLayout.setHorizontalGroup(
            pn_penjualan_hari_iniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_penjualan_hari_iniLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pn_penjualan_hari_iniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_bg)
                    .addComponent(dt_today, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_penjualan_hari_iniLayout.setVerticalGroup(
            pn_penjualan_hari_iniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_penjualan_hari_iniLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_bg, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dt_today, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pn_pendapatan_bulan_ini.setBackground(new java.awt.Color(255, 102, 51));

        lb_bdp.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lb_bdp.setForeground(new java.awt.Color(255, 255, 255));
        lb_bdp.setText("PENDAPATAN BULAN INI");

        dt_month.setBackground(pn_penjualan_hari_ini.getBackground());
        dt_month.setBorder(null);
        dt_month.setForeground(new java.awt.Color(255, 255, 255));
        dt_month.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("Rp #,##0"))));
        dt_month.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        dt_month.setRequestFocusEnabled(false);
        dt_month.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout pn_pendapatan_bulan_iniLayout = new javax.swing.GroupLayout(pn_pendapatan_bulan_ini);
        pn_pendapatan_bulan_ini.setLayout(pn_pendapatan_bulan_iniLayout);
        pn_pendapatan_bulan_iniLayout.setHorizontalGroup(
            pn_pendapatan_bulan_iniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pendapatan_bulan_iniLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pn_pendapatan_bulan_iniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dt_month, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_bdp))
                .addContainerGap(221, Short.MAX_VALUE))
        );
        pn_pendapatan_bulan_iniLayout.setVerticalGroup(
            pn_pendapatan_bulan_iniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pendapatan_bulan_iniLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lb_bdp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dt_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pn_total_terjual.setBackground(new java.awt.Color(255, 102, 51));

        lb_br.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lb_br.setForeground(new java.awt.Color(255, 255, 255));
        lb_br.setText("TOTAL TERJUAL ");

        d_br.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        d_br.setForeground(new java.awt.Color(255, 255, 255));
        d_br.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_br.setText("76");

        javax.swing.GroupLayout pn_total_terjualLayout = new javax.swing.GroupLayout(pn_total_terjual);
        pn_total_terjual.setLayout(pn_total_terjualLayout);
        pn_total_terjualLayout.setHorizontalGroup(
            pn_total_terjualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_total_terjualLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pn_total_terjualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(d_br)
                    .addComponent(lb_br))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pn_total_terjualLayout.setVerticalGroup(
            pn_total_terjualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_total_terjualLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lb_br)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d_br)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dashboardLayout = new javax.swing.GroupLayout(dashboard);
        dashboard.setLayout(dashboardLayout);
        dashboardLayout.setHorizontalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pn_penjualan_hari_ini, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_total_terjual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_pendapatan_bulan_ini, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(312, Short.MAX_VALUE))
        );
        dashboardLayout.setVerticalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(pn_penjualan_hari_ini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_pendapatan_bulan_ini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_total_terjual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        pn_content.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));

        pn_kasir.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("NO SERI");

        ip_noSeriKasir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ip_noSeriKasirKeyPressed(evt);
            }
        });

        btn_insertTable.setText("INSERT");
        btn_insertTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_insertTableMouseClicked(evt);
            }
        });

        tb_kasir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                " Nama Barang", "Jumlah", "Harga Satuan", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tb_kasir);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("TOTAL BAYAR");

        lb_totalHarga.setBorder(null);
        lb_totalHarga.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("RP#,##0"))));
        lb_totalHarga.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_totalHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lb_totalHargaActionPerformed(evt);
            }
        });

        jLabel4.setText("JUMLAH");

        jLabel5.setText("NAMA BARANG");

        btn_deleteTbKasir.setText("DELETE");
        btn_deleteTbKasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteTbKasirMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("BAYAR");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel6.setText("KEMBALI");

        jButton1.setText("SELESAI");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        ip_kembaliKasir.setBorder(null);
        ip_kembaliKasir.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("RP#,##0"))));
        ip_kembaliKasir.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        ip_kembaliKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ip_kembaliKasirActionPerformed(evt);
            }
        });

        btn_hitung.setText("HITUNG");
        btn_hitung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hitungMouseClicked(evt);
            }
        });

        ip_bayarKasir.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        javax.swing.GroupLayout pn_kasirLayout = new javax.swing.GroupLayout(pn_kasir);
        pn_kasir.setLayout(pn_kasirLayout);
        pn_kasirLayout.setHorizontalGroup(
            pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_kasirLayout.createSequentialGroup()
                .addGroup(pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_kasirLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_hitung))
                    .addGroup(pn_kasirLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_kasirLayout.createSequentialGroup()
                                .addGroup(pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ip_noSeriKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dt_namaBarangKasir)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ip_jumlahKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_insertTable)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_totalHarga)
                            .addComponent(ip_bayarKasir)
                            .addGroup(pn_kasirLayout.createSequentialGroup()
                                .addGroup(pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(btn_deleteTbKasir)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(ip_kembaliKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1)
                                    .addComponent(lb_infoKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 21, Short.MAX_VALUE)))))
                .addGap(45, 45, 45))
        );
        pn_kasirLayout.setVerticalGroup(
            pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_kasirLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_noSeriKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_insertTable)
                    .addComponent(ip_jumlahKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dt_namaBarangKasir)
                    .addComponent(btn_deleteTbKasir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_kasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_kasirLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_totalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ip_bayarKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btn_hitung)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ip_kembaliKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lb_infoKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pn_content.add(pn_kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 540));

        pn_diskon.setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        title.setText("DISKON");

        tb_diskon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NO SERI", "NAMA BARANG", "DISKON", "HARGA AWAL", "HARGA DISKON"
            }
        ));
        jScrollPane1.setViewportView(tb_diskon);

        no_seri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_seriActionPerformed(evt);
            }
        });
        no_seri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                no_seriKeyPressed(evt);
            }
        });

        ip_diskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ip_diskonKeyPressed(evt);
            }
        });

        btn_inputdiskon.setText("INPUT DISKON");
        btn_inputdiskon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_inputdiskonMouseClicked(evt);
            }
        });

        input_noseri.setText("NO SERI");

        input_namabrg.setText("NAMA BARANG");

        input_diskon.setText("DISKON");

        input_hargaawl.setText("HARGA AWAL");

        input_hargaakhr.setText("HARGA AKHIR");

        btn_deleteDiskon.setText("DELETE");
        btn_deleteDiskon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteDiskonMouseClicked(evt);
            }
        });

        input_diskon1.setText("%");

        javax.swing.GroupLayout pn_diskonLayout = new javax.swing.GroupLayout(pn_diskon);
        pn_diskon.setLayout(pn_diskonLayout);
        pn_diskonLayout.setHorizontalGroup(
            pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_diskonLayout.createSequentialGroup()
                .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_diskonLayout.createSequentialGroup()
                        .addGap(372, 372, 372)
                        .addComponent(title))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_diskonLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(input_namabrg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(input_noseri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pn_diskonLayout.createSequentialGroup()
                                .addComponent(input_diskon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(89, 89, 89)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nama_brg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(no_seri, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_diskonLayout.createSequentialGroup()
                                .addComponent(ip_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(input_diskon1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_diskonLayout.createSequentialGroup()
                                .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(input_hargaawl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(input_hargaakhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(harga_awal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(harga_akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addComponent(btn_inputdiskon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_deleteDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lb_infoDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_diskonLayout.createSequentialGroup()
                .addGap(0, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pn_diskonLayout.setVerticalGroup(
            pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_diskonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(no_seri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(input_noseri)
                    .addComponent(input_hargaawl)
                    .addComponent(harga_awal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(input_namabrg)
                    .addComponent(input_hargaakhr)
                    .addComponent(nama_brg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(harga_akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_diskonLayout.createSequentialGroup()
                        .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(ip_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(input_diskon)
                            .addComponent(input_diskon1))
                        .addGap(14, 14, 14))
                    .addGroup(pn_diskonLayout.createSequentialGroup()
                        .addComponent(lb_infoDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))))
            .addGroup(pn_diskonLayout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addGroup(pn_diskonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_inputdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_deleteDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_content.add(pn_diskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 540));

        pn_barangCacat.setBackground(new java.awt.Color(255, 255, 255));

        btn_simpanRequest.setText("SIMPAN");
        btn_simpanRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanRequestMouseClicked(evt);
            }
        });

        title_StokRequest.setText("JUMLAH");

        p_NoSeriRequest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                p_NoSeriRequestKeyPressed(evt);
            }
        });

        title_NoSeriRequest.setText("NO SERI");

        title_namaBarangRequest.setText("NAMA BARANG");

        title_Request.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title_Request.setText("BARANG CACAT");

        p_namaBarangRequest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        ip_alasanCacat.setColumns(20);
        ip_alasanCacat.setRows(5);
        jScrollPane4.setViewportView(ip_alasanCacat);

        title_StokRequest1.setText("ALASAN");

        javax.swing.GroupLayout pn_barangCacatLayout = new javax.swing.GroupLayout(pn_barangCacat);
        pn_barangCacat.setLayout(pn_barangCacatLayout);
        pn_barangCacatLayout.setHorizontalGroup(
            pn_barangCacatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_barangCacatLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(pn_barangCacatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(title_namaBarangRequest)
                    .addComponent(title_NoSeriRequest)
                    .addComponent(p_NoSeriRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_StokRequest)
                    .addComponent(p_StokRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpanRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_Request)
                    .addComponent(p_namaBarangRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_infoBarangCacat, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_StokRequest1))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        pn_barangCacatLayout.setVerticalGroup(
            pn_barangCacatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_barangCacatLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(title_Request)
                .addGap(28, 28, 28)
                .addComponent(title_NoSeriRequest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p_NoSeriRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(title_namaBarangRequest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p_namaBarangRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(title_StokRequest)
                .addGap(3, 3, 3)
                .addComponent(p_StokRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title_StokRequest1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_simpanRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_infoBarangCacat, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pn_content.add(pn_barangCacat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pn_edit_profile.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("PROFIL");

        jLabel8.setText("Nama Lengkap ");

        jLabel9.setText("ID ");

        jLabel10.setText("Tanggal Lahir");

        jLabel11.setText("NIK");

        jLabel12.setText("Alamat");

        jLabel13.setText("Email");

        jLabel14.setText("Jenis Kelamin");

        jLabel15.setText("No HP");

        jLabel16.setText("Kontak Darurat");

        jLabel17.setText("No. NPWP");

        jLabel18.setText("No. Rekening");

        btn_simpanProfil.setText("Simpan");
        btn_simpanProfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanProfilMouseClicked(evt);
            }
        });

        ip_emailProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ip_emailProfilActionPerformed(evt);
            }
        });

        lb_idProfil.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        ip_alamatProfil.setColumns(20);
        ip_alamatProfil.setRows(5);
        jScrollPane5.setViewportView(ip_alamatProfil);

        rb_perempuan.setText("Perempuan");
        rb_perempuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rb_perempuanMouseClicked(evt);
            }
        });

        rb_laki.setText("Laki - Laki");
        rb_laki.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rb_lakiMouseClicked(evt);
            }
        });
        rb_laki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_lakiActionPerformed(evt);
            }
        });

        ip_tglProfil.setBackground(new java.awt.Color(255, 255, 255));
        ip_tglProfil.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout pn_edit_profileLayout = new javax.swing.GroupLayout(pn_edit_profile);
        pn_edit_profile.setLayout(pn_edit_profileLayout);
        pn_edit_profileLayout.setHorizontalGroup(
            pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_edit_profileLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_edit_profileLayout.createSequentialGroup()
                        .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_edit_profileLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(46, 46, 46)
                                .addComponent(ip_npwpProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_edit_profileLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(ip_namaProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pn_edit_profileLayout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(22, 22, 22)
                                    .addComponent(ip_kontakProfil))
                                .addGroup(pn_edit_profileLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(66, 66, 66)
                                    .addComponent(ip_hpProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pn_edit_profileLayout.createSequentialGroup()
                                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_edit_profileLayout.createSequentialGroup()
                                        .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))
                                        .addGap(31, 31, 31))
                                    .addGroup(pn_edit_profileLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(32, 32, 32)))
                                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_edit_profileLayout.createSequentialGroup()
                                        .addComponent(rb_perempuan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rb_laki))
                                    .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(ip_emailProfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(ip_tglProfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                        .addComponent(ip_nikProfil, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(lb_idProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pn_edit_profileLayout.createSequentialGroup()
                        .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_edit_profileLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(30, 30, 30)
                                .addComponent(ip_rekeningProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lb_infoProfil, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_simpanProfil)
                        .addGap(75, 75, 75))))
        );
        pn_edit_profileLayout.setVerticalGroup(
            pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_edit_profileLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_idProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ip_namaProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ip_tglProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_nikProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(ip_emailProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(rb_perempuan)
                    .addComponent(rb_laki))
                .addGap(12, 12, 12)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(ip_hpProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_kontakProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(ip_npwpProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(ip_rekeningProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_edit_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_infoProfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_edit_profileLayout.createSequentialGroup()
                        .addComponent(btn_simpanProfil)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pn_content.add(pn_edit_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));

        javax.swing.GroupLayout pn_utamaLayout = new javax.swing.GroupLayout(pn_utama);
        pn_utama.setLayout(pn_utamaLayout);
        pn_utamaLayout.setHorizontalGroup(
            pn_utamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_atas)
            .addGroup(pn_utamaLayout.createSequentialGroup()
                .addComponent(pn_samping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 891, Short.MAX_VALUE))
            .addGroup(pn_utamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_utamaLayout.createSequentialGroup()
                    .addGap(0, 209, Short.MAX_VALUE)
                    .addComponent(pn_content, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pn_utamaLayout.setVerticalGroup(
            pn_utamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_utamaLayout.createSequentialGroup()
                .addComponent(pn_atas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_samping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(pn_utamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_utamaLayout.createSequentialGroup()
                    .addContainerGap(110, Short.MAX_VALUE)
                    .addComponent(pn_content, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_utama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_utama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
  
    
    private void btn_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseClicked
        dashboard.setVisible(true);
        pn_kasir.setVisible(false);
        pn_diskon.setVisible(false);
        pn_barangCacat.setVisible(false);
      
        //back white fore orange
        btn_Kasir.setForeground(new java.awt.Color(255,102,0));
        btn_Kasir.setBackground(new java.awt.Color(255,255,255));
        
        btn_Diskon.setForeground(new java.awt.Color(255,102,0));
        btn_Diskon.setBackground(new java.awt.Color(255,255,255));
        
        btn_barangCacat.setForeground(new java.awt.Color(255,102,0));
        btn_barangCacat.setBackground(new java.awt.Color(255,255,255));
        
        btn_editProfile.setForeground(new java.awt.Color(255,102,0));
        btn_editProfile.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_dashboard.setBackground(new java.awt.Color(255,102,0));
        btn_dashboard.setForeground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_btn_dashboardMouseClicked

    private void btn_KasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_KasirMouseClicked
        dashboard.setVisible(false);
        pn_kasir.setVisible(true);
        pn_diskon.setVisible(false);
        pn_barangCacat.setVisible(false);
        
        //back white fore orange
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_Diskon.setForeground(new java.awt.Color(255,102,0));
        btn_Diskon.setBackground(new java.awt.Color(255,255,255));
        
        btn_barangCacat.setForeground(new java.awt.Color(255,102,0));
        btn_barangCacat.setBackground(new java.awt.Color(255,255,255));
        
        btn_editProfile.setForeground(new java.awt.Color(255,102,0));
        btn_editProfile.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_Kasir.setBackground(new java.awt.Color(255,102,0));
        btn_Kasir.setForeground(new java.awt.Color(255,255,255));
        
        
        model = (DefaultTableModel) tb_kasir.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_btn_KasirMouseClicked

    private void btn_DiskonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DiskonMouseClicked
        dashboard.setVisible(false);
        pn_kasir.setVisible(false);
        pn_diskon.setVisible(true);
        pn_barangCacat.setVisible(false);
        
        //back white fore orange
        btn_Kasir.setForeground(new java.awt.Color(255,102,0));
        btn_Kasir.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_barangCacat.setForeground(new java.awt.Color(255,102,0));
        btn_barangCacat.setBackground(new java.awt.Color(255,255,255));
        
        btn_editProfile.setForeground(new java.awt.Color(255,102,0));
        btn_editProfile.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_Diskon.setBackground(new java.awt.Color(255,102,0));
        btn_Diskon.setForeground(new java.awt.Color(255,255,255));
        
        model = (DefaultTableModel) tb_diskon.getModel();
        model.setRowCount(0);
        
        bersihDiskon();
        lb_infoDiskon.setText("");
        LoadDataDiskon();
    }//GEN-LAST:event_btn_DiskonMouseClicked

    private void LoadDataDiskon(){
        Connection connect = db_connect.connect();
        try{
            String query = "SELECT no_seri, nama_barang, diskon, harga_jual, harga_diskon FROM barang_diskon";
            try (PreparedStatement statement = connect.prepareStatement(query);
                    
                ResultSet resultSet = statement.executeQuery()) {
                // Menambahkan data ke model tabel
                while (resultSet.next()) {
                    Object[] row = {
                        resultSet.getString("no_seri"),
                        resultSet.getString("nama_barang"),
                        resultSet.getInt("diskon"),
                        resultSet.getInt("harga_jual"),
                        resultSet.getInt("harga_diskon")
                    };
                    model.addRow(row);
                }
            }
            connect.close(); // Tutup koneksi setelah selesai
        } catch (SQLException e) {
            lb_infoDiskon.setText("Database Gagal" + e);
        }
    }
    
    private void no_seriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_seriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_seriActionPerformed

    private void btn_inputdiskonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_inputdiskonMouseClicked
        String noSeri = no_seri.getText();
        String namaBarang = nama_brg.getText();
        String diskonString = ip_diskon.getText();
        String HargaAwal = harga_awal.getText();
        String HargaAkhir = harga_akhir.getText();

        if (noSeri.isEmpty() || diskonString.isEmpty() || namaBarang.isEmpty()) {
            if (noSeri.isEmpty()) {
                lb_infoDiskon.setForeground(Color.red);
                lb_infoDiskon.setText("No Seri Belum Anda isi.");
            } else if (diskonString.isEmpty()) {
                lb_infoDiskon.setForeground(Color.red);
                lb_infoDiskon.setText("Nilai Diskon Belum Anda isi.");
            } else if (namaBarang.isEmpty()) {
                lb_infoDiskon.setForeground(Color.red);
                lb_infoDiskon.setText("TEKAN ENTER PADA NO SERI");
            } else if (HargaAkhir.isEmpty()) {
                lb_infoDiskon.setForeground(Color.red);
                lb_infoDiskon.setText("TEKAN ENTER PADA DISKON");
            }
        } else {
            try{
                int diskon = Integer.parseInt(diskonString);
                int hargaAwal = Integer.parseInt(HargaAwal);
                int hargaAkhir = Integer.parseInt(HargaAkhir);

                try {
                    Connection connect = db_connect.connect();

                    // Update the record in barang_etalase table
                    String updateQuery = "UPDATE barang_etalase SET harga_diskon = ?, diskon = ? WHERE no_seri = ?";
                    PreparedStatement updateStatement = connect.prepareStatement(updateQuery);
                    updateStatement.setInt(1, hargaAkhir);
                    updateStatement.setInt(2, diskon);
                    updateStatement.setString(3, noSeri);
                    int rowsUpdated = updateStatement.executeUpdate();

                    // Check if the update was successful
                    if (rowsUpdated > 0) {
                        // Insert a new record in barang_diskon table
                        String insertQuery = "INSERT INTO barang_diskon (no_seri, nama_barang, diskon, harga_jual, harga_diskon) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement insertStatement = connect.prepareStatement(insertQuery);
                        insertStatement.setString(1, noSeri);
                        insertStatement.setString(2, namaBarang);
                        insertStatement.setInt(3,diskon);
                        insertStatement.setInt(4, hargaAwal);
                        insertStatement.setInt(5, hargaAkhir);
                        int rowsInserted = insertStatement.executeUpdate();

                        // Check if the insert was successful
                        if (rowsInserted > 0) {
                            lb_infoDiskon.setForeground(Color.green);
                            lb_infoDiskon.setText("DATA BERHASIL DI INPUT");
                            bersihDiskon();
                            LoadDataDiskon();
                        } else {
                            lb_infoDiskon.setForeground(Color.red);
                            lb_infoDiskon.setText("DATA GAGAL DI INPUT");
                        }
                    } else {
                        lb_infoDiskon.setForeground(Color.red);
                        lb_infoDiskon.setText("UPDATE GAGAL, No Seri tidak ditemukan");
                    }
                    connect.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }catch (NumberFormatException e) {
                lb_infoDiskon.setForeground(Color.red);
                lb_infoDiskon.setText("Format angka tidak valid");
            }
        }
    }//GEN-LAST:event_btn_inputdiskonMouseClicked

    private void btn_deleteDiskonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteDiskonMouseClicked
        int row = (int) tb_diskon.getSelectedRow();
        // Pastikan ada baris yang dipilih
        if (row != -1) {
            // Mendapatkan nilai dari kolom "no seri" (ganti 1 dengan indeks kolom yang sesuai)
            String noSeri = model.getValueAt(row, 0).toString();

            try{
                Connection connect = db_connect.connect();
                String query = "UPDATE barang_etalase SET diskon = null, harga_diskon = null WHERE no_seri = ?";
                PreparedStatement updateStatement = connect.prepareStatement(query);
                updateStatement.setString(1, noSeri);
                int rowsUpdated = updateStatement.executeUpdate();

                // Check if the update was successful
                if (rowsUpdated > 0) {
                    // Insert a new record in barang_diskon table
                        String deleteQuery = "DELETE FROM barang_diskon WHERE no_seri = ?";
                        PreparedStatement insertStatement = connect.prepareStatement(deleteQuery);
                        insertStatement.setString(1, noSeri);
                        int rowsInsert = insertStatement.executeUpdate();
                        if (rowsInsert > 0){
                            lb_infoDiskon.setForeground(Color.green);
                            lb_infoDiskon.setText("DATA BERHASIL DI HAPUS");
                            model.removeRow(row);
                            LoadDataDiskon();
                        } else {
                            lb_infoDiskon.setForeground(Color.red);
                            lb_infoDiskon.setText("DATA GAGAL DI HAPUS");
                        }
                } else {
                    lb_infoDiskon.setForeground(Color.red);
                    lb_infoDiskon.setText("UPDATE GAGAL, No Seri tidak ditemukan");
                }
                connect.close();
                
                
            }catch(SQLException e){
                e.printStackTrace();
            }
        } else {
            // Tidak ada baris yang dipilih
            lb_infoDiskon.setForeground(Color.red);
            lb_infoDiskon.setText("Pilih baris terlebih dahulu.");
        }
    }//GEN-LAST:event_btn_deleteDiskonMouseClicked

    private void lb_totalHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lb_totalHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_totalHargaActionPerformed

    private void ip_noSeriKasirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ip_noSeriKasirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchDataKasir(ip_noSeriKasir);
        }
    }//GEN-LAST:event_ip_noSeriKasirKeyPressed

    private void btn_insertTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_insertTableMouseClicked
        String noSeri = ip_noSeriKasir.getText();
        String NamaBarang = dt_namaBarangKasir.getText();
        int stokjual = (int)ip_jumlahKasir.getValue();
        int stokSisa;
        int harga_diskon;
        float diskon ;
        
        if (stokjual == 0 || stokjual < 0){
            lb_infoKasir.setForeground(Color.red);
            lb_infoKasir.setText("VALUE STOK TIDAK VALID !!!");
            hideLabelAfterDelay(lb_infoKasir, 3000);
            return;
        }
        
        if (NamaBarang.isEmpty()){
            lb_infoKasir.setForeground(Color.red);
            lb_infoKasir.setText("TEKAN ENTER PADA NO SERI !!!");
            hideLabelAfterDelay(lb_infoKasir, 3000);
            return;
        }
        
        try{
            Connection connect = db_connect.connect();
            String query = "SELECT no_seri, nama_barang, stok_etalase, COALESCE(harga_diskon, harga_jual) AS harga_final FROM barang_etalase WHERE no_seri = ?";
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setString(1, noSeri);
            
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String namaBarang = resultSet.getString("nama_barang");
                int stokEtalase = resultSet.getInt("stok_etalase");
                int hargaJual = resultSet.getInt("harga_final");
                               
                if (stokjual > stokEtalase){
                    lb_infoKasir.setForeground(Color.red);
                    lb_infoKasir.setText("PEMBELIAN BARANG MELEBIHI STOK ETALASE");
                    hideLabelAfterDelay(lb_infoKasir, 3000);
                    return;
                }
                int hargaTotal = stokjual * hargaJual;
                
                model.addRow(new Object[]{namaBarang, stokjual, hargaJual, hargaTotal});
                
                int totalSemua = calculateTotal(0,3); //0 adalah total awal dan 3 adalah posisi index kolom
                lb_totalHarga.setValue(totalSemua);
                
            }
            resultSet.close();
            statement.close();
            connect.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_insertTableMouseClicked

    private void btn_deleteTbKasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteTbKasirMouseClicked
        int selectKasir =tb_kasir.getSelectedRow();
        model.removeRow(selectKasir);
        
        int totalSemua = calculateTotal(0,3); //0 adalah total awal dan 3 adalah posisi index kolom
        lb_totalHarga.setValue(totalSemua);
    }//GEN-LAST:event_btn_deleteTbKasirMouseClicked

    private void ip_kembaliKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ip_kembaliKasirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ip_kembaliKasirActionPerformed

    private void btn_hitungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hitungMouseClicked
        int totalHarga = (int) lb_totalHarga.getValue();
        int bayar = Integer.parseInt(ip_bayarKasir.getText());
        if (bayar < totalHarga){
            lb_infoKasir.setForeground(Color.red);
            lb_infoKasir.setText("Pembayaran Kurang dari Total Bayar");
            return;
        }
        int kembali = bayar - totalHarga;
        ip_kembaliKasir.setValue(kembali);
    }//GEN-LAST:event_btn_hitungMouseClicked

    private void no_seriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_seriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchDataEtalase(no_seri);
        }
    }//GEN-LAST:event_no_seriKeyPressed

    private void ip_diskonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ip_diskonKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if("".equals(no_seri.getText())){
                lb_infoDiskon.setForeground(Color.red);
                lb_infoDiskon.setText("ISI NO SERI TERLEBIH DAHULU");
                hideLabelAfterDelay(lb_infoDiskon, 3000);
            }else{
                String diskonString = ip_diskon.getText();
                
                if (isNumeric(diskonString)) {
                    int HargaAwal = Integer.parseInt(harga_awal.getText());
                    int diskonInt = Integer.parseInt(diskonString);
                    if (diskonInt < 100) {
                        float hasilDiskon = (float) diskonInt / 100.0f;
                        float setelahDiskonFloat = HargaAwal - (hasilDiskon * HargaAwal);
                        int setelahDiskon = (int) setelahDiskonFloat;

                        harga_akhir.setText(String.valueOf(setelahDiskon));
                        lb_infoDiskon.setForeground(Color.black);
                        lb_infoDiskon.setText("Diskon " + ip_diskon.getText() + "%");

                    }else{
                        lb_infoDiskon.setForeground(Color.red);
                        lb_infoDiskon.setText("DISKON TIDAK BOLEH LEBIH DARI 100");
                    }
                }else{
                    lb_infoDiskon.setForeground(Color.red);
                    lb_infoDiskon.setText("DISKON HARUS ANGKA");
                }
            }
        }
    }//GEN-LAST:event_ip_diskonKeyPressed

    private void btn_barangCacatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_barangCacatMouseClicked
        dashboard.setVisible(false);
        pn_kasir.setVisible(false);
        pn_diskon.setVisible(false);
        pn_barangCacat.setVisible(true);
        pn_edit_profile.setVisible(false);
        
        //back white fore orange
        btn_Kasir.setForeground(new java.awt.Color(255,102,0));
        btn_Kasir.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_editProfile.setForeground(new java.awt.Color(255,102,0));
        btn_editProfile.setBackground(new java.awt.Color(255,255,255));
        
        btn_Diskon.setForeground(new java.awt.Color(255,102,0));
        btn_Diskon.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_barangCacat.setBackground(new java.awt.Color(255,102,0));
        btn_barangCacat.setForeground(new java.awt.Color(255,255,255));
        
        btn_barangCacat.requestFocus();
    }//GEN-LAST:event_btn_barangCacatMouseClicked

    private void btn_editProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editProfileMouseClicked
        dashboard.setVisible(false);
        pn_kasir.setVisible(false);
        pn_diskon.setVisible(false);
        pn_edit_profile.setVisible(true);
        pn_barangCacat.setVisible(false);
        
        //back white fore orange
        btn_Kasir.setForeground(new java.awt.Color(255,102,0));
        btn_Kasir.setBackground(new java.awt.Color(255,255,255));
        
        btn_Diskon.setForeground(new java.awt.Color(255,102,0));
        btn_Diskon.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_barangCacat.setForeground(new java.awt.Color(255,102,0));
        btn_barangCacat.setBackground(new java.awt.Color(255,255,255));
        
        btn_logoutKasir.setForeground(new java.awt.Color(255,102,0));
        btn_logoutKasir.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_editProfile.setBackground(new java.awt.Color(255,102,0));
        btn_editProfile.setForeground(new java.awt.Color(255,255,255));
        
        btn_editProfile.requestFocus();
        loadDataProfil();
    }//GEN-LAST:event_btn_editProfileMouseClicked

    private void btn_simpanProfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanProfilMouseClicked
        int idUser = Integer.parseInt(lb_idProfil.getText());
        String namaLengkap = ip_namaProfil.getText();
        Date tglLahirDate = ip_tglProfil.getDate();
         // Define the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
         // Convert Date to String
        String tglLahir = dateFormat.format(tglLahirDate);
        
        String nik = ip_nikProfil.getText();
        String alamat = ip_alamatProfil.getText();
        String email = ip_emailProfil.getText();
        String jenisKelamin ;
        if (rb_perempuan.isSelected()){
            jenisKelamin = "Perempuan";
        } else if (rb_laki.isSelected()){
            jenisKelamin = "Laki - Laki";
        } else {
            jenisKelamin = "Undefined";
        }
        
        String noHp = ip_hpProfil.getText();
        String darurat = ip_kontakProfil.getText();
        String npwp = ip_npwpProfil.getText();
        String rekening = ip_rekeningProfil.getText();
        
        
        Connection connect = db_connect.connect();
        
        try{
            String query = "UPDATE data_karyawan " +
                   "SET NamaLengkap = ?, Tanggal_lahir = ?, nik = ?, alamat = ?, email = ?, " +
                   "jenis_kelamin = ?, no_hp = ?, kontak_darurat = ?, nomor_npwp = ?, rekening = ? " +
                   "WHERE id_user = ?";
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setString(1, namaLengkap);
            statement.setString(2, tglLahir);
            statement.setString(3, nik);
            statement.setString(4, alamat);
            statement.setString(5, email);
            statement.setString(6, jenisKelamin);
            statement.setString(7, noHp);
            statement.setString(8, darurat);
            statement.setString(9, npwp);
            statement.setString(10, rekening);
            statement.setInt(11, idUser);
            int hasil = statement.executeUpdate();
            if(hasil > 0){
                lb_infoProfil.setForeground(Color.green);
                lb_infoProfil.setText("BERHASIL DI SIMPAN");
                hideLabelAfterDelay(lb_infoProfil, 3000);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_simpanProfilMouseClicked

    private void rb_perempuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_perempuanMouseClicked
        rb_laki.setSelected(false);
    }//GEN-LAST:event_rb_perempuanMouseClicked

    private void rb_lakiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_lakiMouseClicked
        rb_perempuan.setSelected(false);
    }//GEN-LAST:event_rb_lakiMouseClicked

    private void btn_logoutKasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutKasirMouseClicked
        projectpbo.login test = new projectpbo.login();
        test.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_logoutKasirMouseClicked

    private void p_NoSeriRequestKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_NoSeriRequestKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchDataCacat(p_NoSeriRequest);
        }
    }//GEN-LAST:event_p_NoSeriRequestKeyPressed

    private void btn_simpanRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanRequestMouseClicked
        String noSeri = p_NoSeriRequest.getText();
        String alasan = ip_alasanCacat.getText();
        if (noSeri.isEmpty()){
            lb_infoBarangCacat.setForeground(Color.red);
            lb_infoBarangCacat.setText("NO SERI TIDAK BOLEH KOSONG");
            return;
        }

        if (alasan.isEmpty()){
            lb_infoBarangCacat.setForeground(Color.red);
            lb_infoBarangCacat.setText("ALASAN TIDAK BOLEH KOSONG");
            return;
        }

        String namaBarang = p_namaBarangRequest.getText();
        if(namaBarang.isEmpty()){
            lb_infoBarangCacat.setForeground(Color.red);
            lb_infoBarangCacat.setText("TEKAN ENTER SETELAH INPUT NO SERI");
            return;
        }

        int jumlahCacat = (int) p_StokRequest.getValue();
        int sisaStok;

        try {
            Connection connect = db_connect.connect();
            String selectQuery = "SELECT barang_etalase.stok_etalase, data_barang.harga_satuan FROM barang_etalase JOIN data_barang ON barang_etalase.no_seri = data_barang.no_seri WHERE barang_etalase.no_seri = ?";
            PreparedStatement selectStatement = connect.prepareStatement(selectQuery);
            selectStatement.setString(1, noSeri);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                if (jumlahCacat < 0) {
                    lb_infoBarangCacat.setForeground(Color.red);
                    lb_infoBarangCacat.setText("JUMLAH TIDAK BOLEH MINES");
                    return; // Tambahkan titik koma di sini
                }

                int stokEtalase = resultSet.getInt("stok_etalase");
                int hargaSatuan = resultSet.getInt("harga_satuan");

                if (jumlahCacat > stokEtalase) {
                    lb_infoBarangCacat.setForeground(Color.red);
                    lb_infoBarangCacat.setText("JUMLAH STOK MELEBIHI STOK ETALASE");
                    return;
                }

                sisaStok = stokEtalase - jumlahCacat;

                String updateQuery = "UPDATE barang_etalase SET stok_etalase = ? WHERE no_seri = ?";
                PreparedStatement updateStatement = connect.prepareStatement(updateQuery);
                updateStatement.setInt(1, sisaStok);
                updateStatement.setString(2, noSeri);
                int rowUpdate =  updateStatement.executeUpdate();
                if (rowUpdate > 0) {
                    String insertQuery = "INSERT INTO barang_cacat (no_seri, nama_barang, harga_unit, stok, alasan) VALUES (?,?,?,?,?)";
                    PreparedStatement insertStatement = connect.prepareStatement(insertQuery);
                    insertStatement.setString(1, noSeri);
                    insertStatement.setString(2, namaBarang);
                    insertStatement.setInt(3, hargaSatuan);
                    insertStatement.setInt(4, jumlahCacat);
                    insertStatement.setString(5, alasan);
                    int insertRow = insertStatement.executeUpdate();
                    if(insertRow > 0){
                        lb_infoBarangCacat.setForeground(Color.green);
                        lb_infoBarangCacat.setText("DATA BERHASIL DI INPUT");
                        bersihBarangCacat();
                    }else{
                        lb_infoBarangCacat.setForeground(Color.red);
                        lb_infoBarangCacat.setText("DATA GAGAL DI INPUT");
                    }
                } else {
                    lb_infoBarangCacat.setForeground(Color.red);
                    lb_infoBarangCacat.setText("DATA GAGAL DI UPDATE");
                }
            }
            resultSet.close();
            selectStatement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
            lb_infoBarangCacat.setForeground(Color.red);
            lb_infoBarangCacat.setText("TERJADI KESALAHAN: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_simpanRequestMouseClicked

    public static int generateRandomNumber() {
        // Set the minimum and maximum values for the 5-digit number
        int minValue = 10000;
        int maxValue = 99999;

        // Create a Random object
        Random random = new Random();

        // Generate a random number within the specified range
        int randomNumber = random.nextInt((maxValue - minValue) + 1) + minValue;

        return randomNumber;
    }
    
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        String noSeri = ip_noSeriKasir.getText();
        int jumlahRow = tb_kasir.getRowCount(); 
        int noLaporan = generateRandomNumber();
        String nama = lb_nama.getText();
        String bayarKasir = ip_bayarKasir.getText();
        
        if (noSeri.isEmpty()){
            lb_infoKasir.setForeground(Color.red);
            lb_infoKasir.setText("Isi No Seri Terlebih dahulu");
            return;
        }
        
        if (jumlahRow == 0){
            lb_infoKasir.setForeground(Color.red);
            lb_infoKasir.setText("TEKAN TOMBOL Insert");
            return;
        }
        
        if (bayarKasir.isEmpty()){
            lb_infoKasir.setForeground(Color.red);
            lb_infoKasir.setText("Isi Bayar");
            return; 
        }
        
        try {
            Connection connect = db_connect.connect();

            int columnIndexNamaBarang = 0;
            int columnIndexJumlah = 1;
            int columnIndexTotal = 3;

            for (int i = 0; i < jumlahRow; i++) {
                String namaBarang = (String) tb_kasir.getValueAt(i, columnIndexNamaBarang);
                int jumlahBarang = (int) tb_kasir.getValueAt(i, columnIndexJumlah);
                int jumlahTotal = (int) tb_kasir.getValueAt(i, columnIndexTotal);

                String selectQuery = "SELECT stok_etalase, harga_gudang, stok_jual, harga_jual, harga_diskon, diskon FROM barang_etalase WHERE nama_barang = ?";
                try (PreparedStatement selectStatement = connect.prepareStatement(selectQuery)) {
                    selectStatement.setString(1, namaBarang);

                    try (ResultSet resultSelect = selectStatement.executeQuery()) {
                        if (resultSelect.next()) {
                            int stokEtalase = resultSelect.getInt("stok_etalase");
                            int hargaGudang = resultSelect.getInt("harga_gudang");
                            int hargaJual = resultSelect.getInt("harga_jual");
                            int hargaDiskon = resultSelect.getInt("harga_diskon");
                            int diskon = resultSelect.getInt("diskon");
                            int stokJual = resultSelect.getInt("stok_jual");

                            int sisaStok = stokEtalase - jumlahBarang;
                            int jumlahStok = stokJual + jumlahBarang;

                            String updateQuery = "UPDATE barang_etalase SET stok_etalase = ?, stok_jual = ? WHERE nama_barang = ?";
                            try (PreparedStatement updateStatement = connect.prepareStatement(updateQuery)) {
                                updateStatement.setInt(1, sisaStok);
                                updateStatement.setInt(2, jumlahStok);
                                updateStatement.setString(3, namaBarang);

                                // Use executeUpdate for UPDATE queries
                                int rowsUpdated = updateStatement.executeUpdate();
                                                                
                                if (rowsUpdated > 0) {
                                    
                                    if (hargaDiskon == 0){
                                        String insertQuery = "INSERT INTO laporan_penjualan (kode_laporan, nama_barang, stok, harga_beli, harga_jual, diskon, harga_diskon, total_penjualan, nama_kasir) VALUES(?,?,?,?,?,?,?,?,?)";
                                        try (PreparedStatement insertStatement = connect.prepareStatement(insertQuery)) {
                                            insertStatement.setInt(1, noLaporan);
                                            insertStatement.setString(2, namaBarang);
                                            insertStatement.setInt(3, jumlahBarang);
                                            insertStatement.setInt(4, hargaGudang);
                                            insertStatement.setInt(5, hargaJual);
                                            insertStatement.setObject(6, null);
                                            insertStatement.setObject(7, null);
                                            insertStatement.setInt(8, jumlahTotal);
                                            insertStatement.setString(9, nama);

                                            // Use executeUpdate for INSERT queries
                                            int rowsInserted = insertStatement.executeUpdate();

                                            if (rowsInserted > 0) {
                                                lb_infoKasir.setForeground(Color.green);
                                                lb_infoKasir.setText("DATA BERHASIL DIINPUT");
                                                hideLabelAfterDelay(lb_infoKasir, 3000);
                                            } else {
                                                lb_infoKasir.setForeground(Color.red);
                                                lb_infoKasir.setText("DATA GAGAL DIINPUT");
                                                hideLabelAfterDelay(lb_infoKasir, 3000);
                                            }
                                        }
                                    }else{
                                        String insertQuery = "INSERT INTO laporan_penjualan (kode_laporan, nama_barang, stok, harga_beli, harga_jual, diskon, harga_diskon, total_penjualan, nama_kasir) VALUES(?,?,?,?,?,?,?,?,?)";
                                        try (PreparedStatement insertStatement = connect.prepareStatement(insertQuery)) {
                                            insertStatement.setInt(1, noLaporan);
                                            insertStatement.setString(2, namaBarang);
                                            insertStatement.setInt(3, jumlahBarang);
                                            insertStatement.setInt(4, hargaGudang);
                                            insertStatement.setInt(5, hargaJual);
                                            insertStatement.setInt(6, diskon);
                                            insertStatement.setInt(7, hargaDiskon);
                                            insertStatement.setInt(8, jumlahTotal);
                                            insertStatement.setString(9, nama);

                                            // Use executeUpdate for INSERT queries
                                            int rowsInserted = insertStatement.executeUpdate();

                                            if (rowsInserted > 0) {
                                                lb_infoKasir.setForeground(Color.green);
                                                lb_infoKasir.setText("DATA BERHASIL DIINPUT");
                                                hideLabelAfterDelay(lb_infoKasir, 3000);
                                            } else {
                                                lb_infoKasir.setForeground(Color.red);
                                                lb_infoKasir.setText("DATA GAGAL DIINPUT");
                                                hideLabelAfterDelay(lb_infoKasir, 3000);
                                            }
                                        }
                                    }
                                } else {
                                    lb_infoKasir.setForeground(Color.red);
                                    lb_infoKasir.setText("DATA GAGAL DI UPDATE");
                                    hideLabelAfterDelay(lb_infoKasir, 3000);
                                }
                            }
                        } else {
                            lb_infoKasir.setForeground(Color.red);
                            lb_infoKasir.setText("GAGAL MENGAMBIL DATA");
                            hideLabelAfterDelay(lb_infoKasir, 3000);
                        }
                    }
                }
            }

            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void rb_lakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_lakiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_lakiActionPerformed

    private void ip_emailProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ip_emailProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ip_emailProfilActionPerformed

    private void searchDataCacat(JTextField input){
        try {
            // Mengambil teks dari JTextField
            String keyword = input.getText();

            // Mendapatkan koneksi dari metode connect() (pastikan metode connect() telah didefinisikan)
            Connection connect = db_connect.connect();

            // Menyiapkan dan mengeksekusi kueri SQL
            String query = "SELECT nama_barang FROM barang_etalase WHERE no_seri = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, keyword);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Melakukan sesuatu dengan hasil pencarian
            if(resultSet.next()){
                String namaBarang = resultSet.getString("nama_barang");
                
                p_namaBarangRequest.setForeground(Color.black);
                p_namaBarangRequest.setText(namaBarang);
            }else{
                nama_brg.setForeground(Color.red);
                nama_brg.setText("NO SERI TIDAK TERSEDIA di ETALASE");
            }
            
            // Tutup sumber daya setelah selesai
            resultSet.close();
            preparedStatement.close();
            connect.close();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void searchDataEtalase(JTextField input){
        try {
            // Mengambil teks dari JTextField
            String keyword = input.getText();

            // Mendapatkan koneksi dari metode connect() (pastikan metode connect() telah didefinisikan)
            Connection connect = db_connect.connect();

            // Menyiapkan dan mengeksekusi kueri SQL
            String query = "SELECT nama_barang, harga_jual FROM barang_etalase WHERE no_seri = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, keyword);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Melakukan sesuatu dengan hasil pencarian
            if(resultSet.next()){
                String namaBarang = resultSet.getString("nama_barang");
                int hargaJual = resultSet.getInt("harga_jual");
                
                nama_brg.setForeground(Color.black);
                nama_brg.setText(namaBarang);
                harga_awal.setText(String.valueOf(hargaJual));
            }else{
                nama_brg.setForeground(Color.red);
                nama_brg.setText("NO SERI TIDAK TERSEDIA di ETALASE");
            }

            // Tutup sumber daya setelah selesai
            resultSet.close();
            preparedStatement.close();
            connect.close();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void searchDataKasir(JTextField input){
        try {
            // Mengambil teks dari JTextField
            String keyword = input.getText();

            // Mendapatkan koneksi dari metode connect() (pastikan metode connect() telah didefinisikan)
            Connection connect = db_connect.connect();

            // Menyiapkan dan mengeksekusi kueri SQL
            String query = "SELECT nama_barang FROM barang_etalase WHERE no_seri = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, keyword);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Melakukan sesuatu dengan hasil pencarian
            if(resultSet.next()){
                String namaBarang = resultSet.getString("nama_barang");
                dt_namaBarangKasir.setForeground(Color.black);
                dt_namaBarangKasir.setText(namaBarang);
            }else{
                dt_namaBarangKasir.setForeground(Color.red);
                dt_namaBarangKasir.setText("BARANG TIDAK TERSEDIA");
            }

            // Tutup sumber daya setelah selesai
            resultSet.close();
            preparedStatement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    void loadDataProfil(){
        String idString = lb_id.getText();
        int idInt = Integer.parseInt(idString);
        lb_idProfil.setText(idString);
                
        Connection connect = db_connect.connect();
        String query = "SELECT NamaLengkap,Tanggal_lahir,nik,alamat,email,jenis_kelamin,no_hp,kontak_darurat,nomor_npwp,rekening FROM data_karyawan WHERE id_user = ?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setInt(1, idInt);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String namaLengkap = resultSet.getString("NamaLengkap");
                    String TanggalLahir = resultSet.getString("Tanggal_lahir");
                    String nik = resultSet.getString("nik");
                    String alamat = resultSet.getString("alamat");
                    String email = resultSet.getString("email");
                    String jenis_kelamin = resultSet.getString("jenis_kelamin");
                    String no_hp = resultSet.getString("no_hp");
                    String kontak_darurat = resultSet.getString("kontak_darurat");
                    String nomor_npwp = resultSet.getString("nomor_npwp");
                    String rekening = resultSet.getString("rekening");
                    
                    ip_namaProfil.setText(namaLengkap);
                    
                    // Convert the String to a Date
                    Date dateFromDatabase = parseDateString(TanggalLahir);
                    
                    // Set the date to the JDateChooser
                    ip_tglProfil.setDate(dateFromDatabase);
                    
                    ip_nikProfil.setText(nik);
                    ip_alamatProfil.setText(alamat);
                    ip_emailProfil.setText(email);
                    
                    if ("Laki - Laki".equals(jenis_kelamin)){
                        rb_laki.setSelected(true);
                        rb_perempuan.setSelected(false);
                    }else{
                        rb_laki.setSelected(false);
                        rb_perempuan.setSelected(true);
                    }
                    
                    ip_hpProfil.setText(no_hp);
                    ip_kontakProfil.setText(kontak_darurat);
                    ip_npwpProfil.setText(nomor_npwp);
                    ip_rekeningProfil.setText(rekening);
                    
                }else{
                    lb_welcome1.setText("Database Tidak Sinkron");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Diskon;
    private javax.swing.JButton btn_Kasir;
    private javax.swing.JButton btn_barangCacat;
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_deleteDiskon;
    private javax.swing.JButton btn_deleteTbKasir;
    private javax.swing.JButton btn_editProfile;
    private javax.swing.JButton btn_hitung;
    private javax.swing.JButton btn_inputdiskon;
    private javax.swing.JButton btn_insertTable;
    private javax.swing.JButton btn_logoutKasir;
    private javax.swing.JButton btn_simpanProfil;
    private javax.swing.JButton btn_simpanRequest;
    private javax.swing.JLabel d_br;
    private javax.swing.JPanel dashboard;
    private javax.swing.JFormattedTextField dt_month;
    private javax.swing.JLabel dt_namaBarangKasir;
    private javax.swing.JFormattedTextField dt_today;
    private javax.swing.JLabel gb_dashboard;
    private javax.swing.JLabel harga_akhir;
    private javax.swing.JLabel harga_awal;
    private javax.swing.JLabel input_diskon;
    private javax.swing.JLabel input_diskon1;
    private javax.swing.JLabel input_hargaakhr;
    private javax.swing.JLabel input_hargaawl;
    private javax.swing.JLabel input_namabrg;
    private javax.swing.JLabel input_noseri;
    private javax.swing.JTextArea ip_alamatProfil;
    private javax.swing.JTextArea ip_alasanCacat;
    private javax.swing.JTextField ip_bayarKasir;
    private javax.swing.JTextField ip_diskon;
    private javax.swing.JTextField ip_emailProfil;
    private javax.swing.JTextField ip_hpProfil;
    private javax.swing.JSpinner ip_jumlahKasir;
    private javax.swing.JFormattedTextField ip_kembaliKasir;
    private javax.swing.JTextField ip_kontakProfil;
    private javax.swing.JTextField ip_namaProfil;
    private javax.swing.JTextField ip_nikProfil;
    private javax.swing.JTextField ip_noSeriKasir;
    private javax.swing.JTextField ip_npwpProfil;
    private javax.swing.JTextField ip_rekeningProfil;
    private com.toedter.calendar.JDateChooser ip_tglProfil;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lb_bdp;
    private javax.swing.JLabel lb_bg;
    private javax.swing.JLabel lb_br;
    private javax.swing.JLabel lb_date;
    private javax.swing.JLabel lb_hello;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_idKaryawan;
    private javax.swing.JLabel lb_idProfil;
    private javax.swing.JLabel lb_infoBarangCacat;
    private javax.swing.JLabel lb_infoDiskon;
    private javax.swing.JLabel lb_infoKasir;
    private javax.swing.JLabel lb_infoProfil;
    private javax.swing.JLabel lb_jabatan;
    private javax.swing.JLabel lb_nama;
    private javax.swing.JLabel lb_role;
    private javax.swing.JLabel lb_tanggal;
    private javax.swing.JFormattedTextField lb_totalHarga;
    private javax.swing.JLabel lb_welcome1;
    private javax.swing.JLabel nama_brg;
    private javax.swing.JTextField no_seri;
    private javax.swing.JTextField p_NoSeriRequest;
    private javax.swing.JSpinner p_StokRequest;
    private javax.swing.JLabel p_namaBarangRequest;
    private javax.swing.JPanel pn_1;
    private javax.swing.JPanel pn_2;
    private javax.swing.JPanel pn_3;
    private javax.swing.JPanel pn_4;
    private javax.swing.JLayeredPane pn_atas;
    private javax.swing.JPanel pn_barangCacat;
    private javax.swing.JLayeredPane pn_content;
    private javax.swing.JPanel pn_diskon;
    private javax.swing.JPanel pn_edit_profile;
    private javax.swing.JPanel pn_kasir;
    private javax.swing.JPanel pn_pendapatan_bulan_ini;
    private javax.swing.JPanel pn_penjualan_hari_ini;
    private javax.swing.JLayeredPane pn_samping;
    private javax.swing.JPanel pn_total_terjual;
    private javax.swing.JPanel pn_utama;
    private javax.swing.JRadioButton rb_laki;
    private javax.swing.JRadioButton rb_perempuan;
    private javax.swing.JTable tb_diskon;
    private javax.swing.JTable tb_kasir;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title_NoSeriRequest;
    private javax.swing.JLabel title_Request;
    private javax.swing.JLabel title_StokRequest;
    private javax.swing.JLabel title_StokRequest1;
    private javax.swing.JLabel title_namaBarangRequest;
    // End of variables declaration//GEN-END:variables
}
