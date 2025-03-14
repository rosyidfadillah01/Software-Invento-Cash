package admin;

import javax.swing.table.DefaultTableModel;
import config.db_connect;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

/**
 *
 * @author roshy
 */
public class dashboard extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */
    private final int userId;
    private final String userRole;
    private DefaultTableModel model;
    int idUser;
    
    private static final long MIN_VALUE = 10000000L; // Minimum 10-digit number
    private static final long MAX_VALUE = 99999999L; // Maximum 10-digit number
    
    /**
     * Creates new form admin
     * @param userId
     * @param username
     * @param userRole
     */
    public dashboard(int userId, String username, String userRole) {
        initComponents();
        dashboard.setVisible(true);
        pn_tambahKaryawan.setVisible(false);
        pn_dataKaryawan.setVisible(false);
        pn_hapusKaryawan.setVisible(false);
        pn_dataGudang.setVisible(false);
        pn_administrasi.setVisible(false);
        pn_perusahaan.setVisible(false);
        
        //back white fore orange
        btn_karyawan.setForeground(new java.awt.Color(255,102,0));
        btn_karyawan.setBackground(new java.awt.Color(255,255,255));
                
        btn_gudang.setForeground(new java.awt.Color(255,102,0));
        btn_gudang.setBackground(new java.awt.Color(255,255,255));
        
        btn_administrasi.setForeground(new java.awt.Color(255,102,0));
        btn_administrasi.setBackground(new java.awt.Color(255,255,255));
        
        btn_logoutAdmin.setForeground(new java.awt.Color(255,102,0));
        btn_logoutAdmin.setBackground(new java.awt.Color(255,255,255));
        
        btn_perusahaan.setForeground(new java.awt.Color(255,102,0));
        btn_perusahaan.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_dashboard.setBackground(new java.awt.Color(255,102,0));
        btn_dashboard.setForeground(new java.awt.Color(255,255,255));
        
        this.userId = userId;
        this.userRole = userRole;
        // Tampilkan data admin
        showNavbar();
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
       
    public static String generateRandom() {
        Random random = new Random();
        long randomString = random.nextLong((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE;
        return Long.toString(randomString);
    } 

    void tambahHapus(){
        ip_nama.setText("");
        ip_username.setText("");
        ip_password.setText("");
        ip_nik.setText("");
        ip_alamat.setText("");
        ip_pendidikan.setText("");
        ip_pengalaman.setText("");
        ip_email.setText("");
        cb_kelamin.setSelectedItem(1);
        cb_jabatan.setSelectedItem(1);
        ip_noTelp.setText("");
        ip_jenisKontrak.setText("");
        ip_pengalaman.setText("");
        ip_gaji.setText("");
        ip_darurat.setText("");
        ip_npwp.setText("");
        ip_rek.setText("");
        
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_utama = new javax.swing.JPanel();
        pn_samping = new javax.swing.JLayeredPane();
        btn_dashboard = new javax.swing.JButton();
        btn_karyawan = new javax.swing.JButton();
        btn_gudang = new javax.swing.JButton();
        btn_administrasi = new javax.swing.JButton();
        btn_logoutAdmin = new javax.swing.JButton();
        btn_perusahaan = new javax.swing.JButton();
        pn_atas = new javax.swing.JLayeredPane();
        gb_dashboard = new javax.swing.JLabel();
        pn_jabatan = new javax.swing.JPanel();
        lb_jabatan = new javax.swing.JLabel();
        lb_role = new javax.swing.JLabel();
        pn_tanggal = new javax.swing.JPanel();
        lb_tanggal = new javax.swing.JLabel();
        lb_date = new javax.swing.JLabel();
        pn_pegawai = new javax.swing.JPanel();
        lb_pegawai = new javax.swing.JLabel();
        lb_id = new javax.swing.JLabel();
        pn_nama = new javax.swing.JPanel();
        lb_halo = new javax.swing.JLabel();
        lb_nama = new javax.swing.JLabel();
        lb_come = new javax.swing.JLabel();
        pn_ubah = new javax.swing.JPanel();
        pn_hapusKaryawan = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        btnback_hapusKaryawan = new javax.swing.JButton();
        btn_hapusKaryawan1 = new javax.swing.JButton();
        lbInfo_hapusKaryawan = new javax.swing.JLabel();
        cb_namaHapus = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbNama_hapusKaryawan = new javax.swing.JLabel();
        lbJabatan_hapusKaryawan = new javax.swing.JLabel();
        lbTglLahir_hapusKaryawan = new javax.swing.JLabel();
        lbGaji_hapusKaryawan = new javax.swing.JLabel();
        pn_dataKaryawan = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_data = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_tambahKaryawan = new javax.swing.JButton();
        btn_hapusKaryawan = new javax.swing.JButton();
        lb_info = new javax.swing.JLabel();
        pn_tambahKaryawan = new javax.swing.JPanel();
        lb_tambahKaryawan = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ip_nama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ip_username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ip_password = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        ip_nik = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ip_alamat = new javax.swing.JTextArea();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel8 = new javax.swing.JLabel();
        ip_email = new javax.swing.JTextField();
        cb_kelamin = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cb_jabatan = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ip_noTelp = new javax.swing.JTextField();
        ip_jenisKontrak = new javax.swing.JTextField();
        ip_gaji = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        ip_darurat = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        ip_npwp = new javax.swing.JTextField();
        ip_rek = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ip_pendidikan = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        ip_pengalaman = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        btn_simpanKaryawan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ip_tanggal = new com.toedter.calendar.JDateChooser();
        lbInfo_tambahKaryawan = new javax.swing.JLabel();
        dashboard = new javax.swing.JLayeredPane();
        pn_karyawan = new javax.swing.JPanel();
        lb_jumlahKaryawan = new javax.swing.JLabel();
        lb_totalKaryawan = new javax.swing.JLabel();
        pn_stokBarang = new javax.swing.JPanel();
        lb_stokBarang = new javax.swing.JLabel();
        lb_stok = new javax.swing.JLabel();
        pn_pendapatan = new javax.swing.JPanel();
        lb_pendapatanBulanKmrn = new javax.swing.JLabel();
        lb_stok1 = new javax.swing.JLabel();
        gb_dashAdmin = new javax.swing.JLabel();
        pn_dataGudang = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_dataGudang = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        lb_info1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_dataRequest = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        pn_administrasi = new javax.swing.JPanel();
        lb_infoAdministrasi = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_keuangan = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        cb_kategory = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        cb_namaAdmin = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        lb_infoAdministrasi1 = new javax.swing.JLabel();
        pn_perusahaan = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb_dataPerusahaan = new javax.swing.JTable();
        lb_info3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        ip_namaPerusahaan = new javax.swing.JFormattedTextField();
        ip_emailPerusahaan = new javax.swing.JFormattedTextField();
        ip_telpPerusahaan = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pn_utama.setBackground(new java.awt.Color(255, 255, 255));

        pn_samping.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btn_dashboard.setBackground(new java.awt.Color(255, 102, 0));
        btn_dashboard.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        btn_dashboard.setText("DASHBOARD");
        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseClicked(evt);
            }
        });

        btn_karyawan.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_karyawan.setForeground(new java.awt.Color(255, 102, 0));
        btn_karyawan.setText("KARYAWAN");
        btn_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_karyawanMouseClicked(evt);
            }
        });

        btn_gudang.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_gudang.setForeground(new java.awt.Color(255, 102, 0));
        btn_gudang.setText("GUDANG");
        btn_gudang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_gudangMouseClicked(evt);
            }
        });

        btn_administrasi.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_administrasi.setForeground(new java.awt.Color(255, 102, 0));
        btn_administrasi.setText("ADMINISTRASI");
        btn_administrasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_administrasiMouseClicked(evt);
            }
        });

        btn_logoutAdmin.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_logoutAdmin.setForeground(new java.awt.Color(255, 102, 0));
        btn_logoutAdmin.setText("LOGOUT");
        btn_logoutAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutAdminMouseClicked(evt);
            }
        });

        btn_perusahaan.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_perusahaan.setForeground(new java.awt.Color(255, 102, 0));
        btn_perusahaan.setText("PERUSAHAAN");
        btn_perusahaan.setAutoscrolls(true);
        btn_perusahaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_perusahaanMouseClicked(evt);
            }
        });

        pn_samping.setLayer(btn_dashboard, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_karyawan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_gudang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_administrasi, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_logoutAdmin, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_perusahaan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pn_sampingLayout = new javax.swing.GroupLayout(pn_samping);
        pn_samping.setLayout(pn_sampingLayout);
        pn_sampingLayout.setHorizontalGroup(
            pn_sampingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_sampingLayout.createSequentialGroup()
                .addGroup(pn_sampingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_sampingLayout.createSequentialGroup()
                        .addGroup(pn_sampingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pn_sampingLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_sampingLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(pn_sampingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_karyawan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_gudang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_administrasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pn_sampingLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_logoutAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pn_sampingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_perusahaan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pn_sampingLayout.setVerticalGroup(
            pn_sampingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_sampingLayout.createSequentialGroup()
                .addComponent(btn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_gudang, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_administrasi, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(btn_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_logoutAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1566, Short.MAX_VALUE))
        );

        gb_dashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gb_dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectpbo/img/icon_dash.png"))); // NOI18N

        pn_jabatan.setBackground(new java.awt.Color(255, 102, 0));

        lb_jabatan.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lb_jabatan.setForeground(new java.awt.Color(255, 255, 255));
        lb_jabatan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_jabatan.setText("Jabatan");

        lb_role.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_role.setForeground(new java.awt.Color(255, 255, 255));
        lb_role.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_role.setText("Admin");

        javax.swing.GroupLayout pn_jabatanLayout = new javax.swing.GroupLayout(pn_jabatan);
        pn_jabatan.setLayout(pn_jabatanLayout);
        pn_jabatanLayout.setHorizontalGroup(
            pn_jabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_jabatanLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pn_jabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_jabatan)
                    .addComponent(lb_role))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pn_jabatanLayout.setVerticalGroup(
            pn_jabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_jabatanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_jabatan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_role)
                .addGap(30, 30, 30))
        );

        pn_tanggal.setBackground(new java.awt.Color(255, 102, 0));
        pn_tanggal.setForeground(new java.awt.Color(255, 153, 0));

        lb_tanggal.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lb_tanggal.setForeground(new java.awt.Color(255, 255, 255));
        lb_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_tanggal.setText("Tanggal");

        lb_date.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_date.setForeground(new java.awt.Color(255, 255, 255));
        lb_date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_date.setText("Admin");

        javax.swing.GroupLayout pn_tanggalLayout = new javax.swing.GroupLayout(pn_tanggal);
        pn_tanggal.setLayout(pn_tanggalLayout);
        pn_tanggalLayout.setHorizontalGroup(
            pn_tanggalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tanggalLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pn_tanggalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_tanggal)
                    .addComponent(lb_date))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pn_tanggalLayout.setVerticalGroup(
            pn_tanggalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_tanggalLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(lb_tanggal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_date)
                .addGap(33, 33, 33))
        );

        pn_pegawai.setBackground(new java.awt.Color(255, 102, 0));
        pn_pegawai.setForeground(new java.awt.Color(255, 153, 0));

        lb_pegawai.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lb_pegawai.setForeground(new java.awt.Color(255, 255, 255));
        lb_pegawai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_pegawai.setText("ID");

        lb_id.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_id.setForeground(new java.awt.Color(255, 255, 255));
        lb_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_id.setText("Admin");

        javax.swing.GroupLayout pn_pegawaiLayout = new javax.swing.GroupLayout(pn_pegawai);
        pn_pegawai.setLayout(pn_pegawaiLayout);
        pn_pegawaiLayout.setHorizontalGroup(
            pn_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pegawaiLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pn_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lb_pegawai)
                    .addComponent(lb_id))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        pn_pegawaiLayout.setVerticalGroup(
            pn_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_pegawaiLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(lb_pegawai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_id)
                .addGap(30, 30, 30))
        );

        pn_nama.setBackground(new java.awt.Color(255, 102, 0));

        lb_halo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_halo.setForeground(new java.awt.Color(255, 255, 255));
        lb_halo.setText("Halo,");

        lb_nama.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_nama.setForeground(new java.awt.Color(255, 255, 255));
        lb_nama.setText("Nama");

        lb_come.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lb_come.setForeground(new java.awt.Color(255, 255, 255));
        lb_come.setText("Selamat Datang");

        javax.swing.GroupLayout pn_namaLayout = new javax.swing.GroupLayout(pn_nama);
        pn_nama.setLayout(pn_namaLayout);
        pn_namaLayout.setHorizontalGroup(
            pn_namaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_namaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pn_namaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_come)
                    .addGroup(pn_namaLayout.createSequentialGroup()
                        .addComponent(lb_halo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_nama)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_namaLayout.setVerticalGroup(
            pn_namaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_namaLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(pn_namaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_halo)
                    .addComponent(lb_nama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_come)
                .addGap(25, 25, 25))
        );

        pn_atas.setLayer(gb_dashboard, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_atas.setLayer(pn_jabatan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_atas.setLayer(pn_tanggal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_atas.setLayer(pn_pegawai, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_atas.setLayer(pn_nama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pn_atasLayout = new javax.swing.GroupLayout(pn_atas);
        pn_atas.setLayout(pn_atasLayout);
        pn_atasLayout.setHorizontalGroup(
            pn_atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_atasLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(gb_dashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_nama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pn_atasLayout.setVerticalGroup(
            pn_atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_atasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_atasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(pn_jabatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pn_atasLayout.createSequentialGroup()
                        .addGroup(pn_atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(gb_dashboard)
                            .addComponent(pn_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pn_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pn_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pn_ubah.setBackground(new java.awt.Color(255, 255, 255));
        pn_ubah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pn_hapusKaryawan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Hapus Karyawan");

        btnback_hapusKaryawan.setText("Kembali");
        btnback_hapusKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnback_hapusKaryawanMouseClicked(evt);
            }
        });

        btn_hapusKaryawan1.setText("Hapus Data");
        btn_hapusKaryawan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapusKaryawan1MouseClicked(evt);
            }
        });

        cb_namaHapus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_namaHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_namaHapusActionPerformed(evt);
            }
        });

        jLabel20.setText("Pilih Nama Karyawan");

        jLabel21.setText("Nama Lengkap");

        jLabel22.setText("Jabatan");

        jLabel23.setText("Tanggal Lahir");

        jLabel24.setText("Gaji");

        javax.swing.GroupLayout pn_hapusKaryawanLayout = new javax.swing.GroupLayout(pn_hapusKaryawan);
        pn_hapusKaryawan.setLayout(pn_hapusKaryawanLayout);
        pn_hapusKaryawanLayout.setHorizontalGroup(
            pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_hapusKaryawanLayout.createSequentialGroup()
                .addGap(272, 424, Short.MAX_VALUE)
                .addComponent(lbInfo_hapusKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pn_hapusKaryawanLayout.createSequentialGroup()
                .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(cb_namaHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(jLabel19))
                    .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pn_hapusKaryawanLayout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel21)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbNama_hapusKaryawan)
                                .addComponent(lbJabatan_hapusKaryawan)
                                .addComponent(lbTglLahir_hapusKaryawan)
                                .addComponent(lbGaji_hapusKaryawan)))
                        .addGroup(pn_hapusKaryawanLayout.createSequentialGroup()
                            .addGap(197, 197, 197)
                            .addComponent(btnback_hapusKaryawan)
                            .addGap(36, 36, 36)
                            .addComponent(btn_hapusKaryawan1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_hapusKaryawanLayout.setVerticalGroup(
            pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_hapusKaryawanLayout.createSequentialGroup()
                .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_hapusKaryawanLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel21)
                            .addComponent(lbNama_hapusKaryawan))
                        .addGap(18, 18, 18)
                        .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel22)
                            .addComponent(lbJabatan_hapusKaryawan))
                        .addGap(30, 30, 30)
                        .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel23)
                            .addComponent(lbTglLahir_hapusKaryawan))
                        .addGap(18, 18, 18)
                        .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel24)
                            .addComponent(lbGaji_hapusKaryawan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addGroup(pn_hapusKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnback_hapusKaryawan)
                            .addComponent(btn_hapusKaryawan1)))
                    .addGroup(pn_hapusKaryawanLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel19)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_namaHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbInfo_hapusKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        pn_ubah.add(pn_hapusKaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pn_dataKaryawan.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(12001, 80));

        tb_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama Lengkap", "Tanggal Lahir", "NIK", "Alamat", "Email", "Jenis Kelamin", "Jabatan", "No Hanphone", "Jenis Kontrak", "Gaji", "Pendidikan", "Pengalaman", "Kontak Darurat", "No. NPWP", "No. Rekening"
            }
        ));
        tb_data.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tb_data);
        if (tb_data.getColumnModel().getColumnCount() > 0) {
            tb_data.getColumnModel().getColumn(6).setHeaderValue("Jenis Kelamin");
            tb_data.getColumnModel().getColumn(7).setHeaderValue("Jabatan");
            tb_data.getColumnModel().getColumn(8).setHeaderValue("No Hanphone");
            tb_data.getColumnModel().getColumn(9).setHeaderValue("Jenis Kontrak");
            tb_data.getColumnModel().getColumn(10).setHeaderValue("Gaji");
            tb_data.getColumnModel().getColumn(11).setHeaderValue("Pendidikan");
            tb_data.getColumnModel().getColumn(12).setHeaderValue("Pengalaman");
            tb_data.getColumnModel().getColumn(13).setHeaderValue("Kontak Darurat");
            tb_data.getColumnModel().getColumn(14).setHeaderValue("No. NPWP");
            tb_data.getColumnModel().getColumn(15).setHeaderValue("No. Rekening");
        }

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Karyawan");

        btn_tambahKaryawan.setText("Tambah Data");
        btn_tambahKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambahKaryawanMouseClicked(evt);
            }
        });

        btn_hapusKaryawan.setText("Hapus Data");
        btn_hapusKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapusKaryawanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_dataKaryawanLayout = new javax.swing.GroupLayout(pn_dataKaryawan);
        pn_dataKaryawan.setLayout(pn_dataKaryawanLayout);
        pn_dataKaryawanLayout.setHorizontalGroup(
            pn_dataKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dataKaryawanLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pn_dataKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_dataKaryawanLayout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jLabel1))
                    .addGroup(pn_dataKaryawanLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(btn_tambahKaryawan)
                        .addGap(29, 29, 29)
                        .addComponent(btn_hapusKaryawan))
                    .addGroup(pn_dataKaryawanLayout.createSequentialGroup()
                        .addComponent(lb_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pn_dataKaryawanLayout.setVerticalGroup(
            pn_dataKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_dataKaryawanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGroup(pn_dataKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_dataKaryawanLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 26, Short.MAX_VALUE)
                        .addComponent(lb_info)
                        .addGap(319, 319, 319))
                    .addGroup(pn_dataKaryawanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pn_dataKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_tambahKaryawan)
                            .addComponent(btn_hapusKaryawan))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pn_ubah.add(pn_dataKaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pn_tambahKaryawan.setBackground(new java.awt.Color(255, 255, 255));

        lb_tambahKaryawan.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lb_tambahKaryawan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_tambahKaryawan.setText("Tambah Karyawan");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Nama Lengkap");

        jLabel3.setText("Username");

        jLabel4.setText("Password");

        jLabel5.setText("Tanggal Lahir");

        jLabel6.setText("NIK");

        jLabel7.setText("Alamat");

        ip_alamat.setColumns(20);
        ip_alamat.setLineWrap(true);
        ip_alamat.setRows(5);
        jScrollPane2.setViewportView(ip_alamat);

        jLabel8.setText("Email");

        cb_kelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kelamin", "Laki - Laki", "Perempuan" }));

        jLabel9.setText("Jenis Kelamin");

        cb_jabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jabatan", "Gudang", "Admin", "Kasir" }));

        jLabel11.setText("Jabatan");

        jLabel13.setText("Gaji");

        jLabel12.setText("Jenis Kontrak");

        jLabel10.setText("No. Handphone");

        jLabel16.setText("Kontak Darurat");

        jLabel17.setText("No. NPWP");

        jLabel18.setText("No. Rekening");

        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ip_email, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cb_kelamin, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cb_jabatan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ip_noTelp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ip_jenisKontrak, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ip_gaji, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ip_darurat, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ip_npwp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ip_rek, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ip_email, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_kelamin, 0, 149, Short.MAX_VALUE)
                            .addComponent(cb_jabatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(2, 2, 2)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ip_npwp, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(ip_darurat)
                            .addComponent(ip_jenisKontrak)
                            .addComponent(ip_noTelp)
                            .addComponent(ip_gaji, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ip_rek, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ip_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cb_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(4, 4, 4)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cb_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_noTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_jenisKontrak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_gaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_darurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_npwp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_rek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setText("Pendidikan");

        ip_pengalaman.setColumns(20);
        ip_pengalaman.setLineWrap(true);
        ip_pengalaman.setRows(5);
        jScrollPane3.setViewportView(ip_pengalaman);

        jLabel15.setText("Pengalaman");

        btn_simpanKaryawan.setText("Tambah Karyawan");
        btn_simpanKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanKaryawanMouseClicked(evt);
            }
        });

        jButton2.setText("Kembali");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        ip_tanggal.setBackground(new java.awt.Color(255, 255, 255));
        ip_tanggal.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ip_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ip_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ip_username, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ip_password, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ip_nik, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ip_pendidikan, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_simpanKaryawan)
                            .addComponent(jButton2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lbInfo_tambahKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(ip_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(ip_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(ip_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(ip_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(ip_nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(ip_pendidikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_simpanKaryawan)
                        .addGap(8, 8, 8)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbInfo_tambahKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_tambahKaryawanLayout = new javax.swing.GroupLayout(pn_tambahKaryawan);
        pn_tambahKaryawan.setLayout(pn_tambahKaryawanLayout);
        pn_tambahKaryawanLayout.setHorizontalGroup(
            pn_tambahKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tambahKaryawanLayout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(lb_tambahKaryawan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pn_tambahKaryawanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_tambahKaryawanLayout.setVerticalGroup(
            pn_tambahKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tambahKaryawanLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lb_tambahKaryawan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_ubah.add(pn_tambahKaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dashboard.setBackground(new java.awt.Color(255, 255, 255));

        pn_karyawan.setBackground(new java.awt.Color(255, 102, 0));

        lb_jumlahKaryawan.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        lb_jumlahKaryawan.setForeground(new java.awt.Color(255, 255, 255));
        lb_jumlahKaryawan.setText("Jumlah Karyawan");

        lb_totalKaryawan.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        lb_totalKaryawan.setForeground(new java.awt.Color(255, 255, 255));
        lb_totalKaryawan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_totalKaryawan.setText("16");

        javax.swing.GroupLayout pn_karyawanLayout = new javax.swing.GroupLayout(pn_karyawan);
        pn_karyawan.setLayout(pn_karyawanLayout);
        pn_karyawanLayout.setHorizontalGroup(
            pn_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_karyawanLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pn_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lb_totalKaryawan)
                    .addComponent(lb_jumlahKaryawan))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pn_karyawanLayout.setVerticalGroup(
            pn_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_karyawanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_jumlahKaryawan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_totalKaryawan)
                .addGap(106, 106, 106))
        );

        pn_stokBarang.setBackground(new java.awt.Color(255, 102, 0));

        lb_stokBarang.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        lb_stokBarang.setForeground(new java.awt.Color(255, 255, 255));
        lb_stokBarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_stokBarang.setText("Stok Barang");

        lb_stok.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        lb_stok.setForeground(new java.awt.Color(255, 255, 255));
        lb_stok.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_stok.setText("16");

        javax.swing.GroupLayout pn_stokBarangLayout = new javax.swing.GroupLayout(pn_stokBarang);
        pn_stokBarang.setLayout(pn_stokBarangLayout);
        pn_stokBarangLayout.setHorizontalGroup(
            pn_stokBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_stokBarangLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pn_stokBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lb_stokBarang)
                    .addComponent(lb_stok))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pn_stokBarangLayout.setVerticalGroup(
            pn_stokBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_stokBarangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_stokBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_stok)
                .addContainerGap())
        );

        pn_pendapatan.setBackground(new java.awt.Color(255, 102, 0));

        lb_pendapatanBulanKmrn.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        lb_pendapatanBulanKmrn.setForeground(new java.awt.Color(255, 255, 255));
        lb_pendapatanBulanKmrn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_pendapatanBulanKmrn.setText("Pendapatan");

        lb_stok1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        lb_stok1.setForeground(new java.awt.Color(255, 255, 255));
        lb_stok1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_stok1.setText("10000000");

        javax.swing.GroupLayout pn_pendapatanLayout = new javax.swing.GroupLayout(pn_pendapatan);
        pn_pendapatan.setLayout(pn_pendapatanLayout);
        pn_pendapatanLayout.setHorizontalGroup(
            pn_pendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pendapatanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_pendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_pendapatanBulanKmrn)
                    .addComponent(lb_stok1))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pn_pendapatanLayout.setVerticalGroup(
            pn_pendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pendapatanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_pendapatanBulanKmrn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_stok1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        gb_dashAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectpbo/img/dash_admin.png"))); // NOI18N

        dashboard.setLayer(pn_karyawan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dashboard.setLayer(pn_stokBarang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dashboard.setLayer(pn_pendapatan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dashboard.setLayer(gb_dashAdmin, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dashboardLayout = new javax.swing.GroupLayout(dashboard);
        dashboard.setLayout(dashboardLayout);
        dashboardLayout.setHorizontalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pn_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pn_stokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pn_pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(gb_dashAdmin)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        dashboardLayout.setVerticalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_stokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(gb_dashAdmin)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pn_ubah.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        pn_dataGudang.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(12001, 80));

        tb_dataGudang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No Seri", "Nama Barang", "Stok Gudang", "Stok Etalase", "Harga", "Expired"
            }
        ));
        tb_dataGudang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tb_dataGudang.setEnabled(false);
        jScrollPane4.setViewportView(tb_dataGudang);

        jLabel25.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Data Gudang");

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane5.setColumnHeader(null);
        jScrollPane5.setColumnHeaderView(null);
        jScrollPane5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(12001, 80));
        jScrollPane5.setRequestFocusEnabled(false);

        tb_dataRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Request", "Nama Barang", "Stok Request", "Perusahaan", "Nama Request", "Email Perusahaan", "No. Perusahaan", "Tgl Request"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_dataRequest.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane5.setViewportView(tb_dataRequest);
        if (tb_dataRequest.getColumnModel().getColumnCount() > 0) {
            tb_dataRequest.getColumnModel().getColumn(1).setMinWidth(100);
            tb_dataRequest.getColumnModel().getColumn(1).setPreferredWidth(150);
            tb_dataRequest.getColumnModel().getColumn(1).setMaxWidth(200);
            tb_dataRequest.getColumnModel().getColumn(2).setMinWidth(75);
            tb_dataRequest.getColumnModel().getColumn(2).setPreferredWidth(80);
            tb_dataRequest.getColumnModel().getColumn(2).setMaxWidth(100);
            tb_dataRequest.getColumnModel().getColumn(3).setMinWidth(100);
            tb_dataRequest.getColumnModel().getColumn(3).setPreferredWidth(200);
            tb_dataRequest.getColumnModel().getColumn(3).setMaxWidth(300);
            tb_dataRequest.getColumnModel().getColumn(4).setMinWidth(50);
            tb_dataRequest.getColumnModel().getColumn(4).setPreferredWidth(170);
            tb_dataRequest.getColumnModel().getColumn(4).setMaxWidth(150);
            tb_dataRequest.getColumnModel().getColumn(5).setMinWidth(100);
            tb_dataRequest.getColumnModel().getColumn(5).setPreferredWidth(150);
            tb_dataRequest.getColumnModel().getColumn(5).setMaxWidth(200);
            tb_dataRequest.getColumnModel().getColumn(6).setMinWidth(100);
            tb_dataRequest.getColumnModel().getColumn(6).setPreferredWidth(150);
            tb_dataRequest.getColumnModel().getColumn(6).setMaxWidth(300);
            tb_dataRequest.getColumnModel().getColumn(7).setMinWidth(100);
            tb_dataRequest.getColumnModel().getColumn(7).setPreferredWidth(100);
            tb_dataRequest.getColumnModel().getColumn(7).setMaxWidth(200);
        }

        jLabel26.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Gudang Request");

        jToggleButton1.setText("Selesai Request");
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_dataGudangLayout = new javax.swing.GroupLayout(pn_dataGudang);
        pn_dataGudang.setLayout(pn_dataGudangLayout);
        pn_dataGudangLayout.setHorizontalGroup(
            pn_dataGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dataGudangLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pn_dataGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
            .addGroup(pn_dataGudangLayout.createSequentialGroup()
                .addGroup(pn_dataGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_dataGudangLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lb_info1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel25))
                    .addGroup(pn_dataGudangLayout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jLabel26)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_dataGudangLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addGap(249, 249, 249))
        );
        pn_dataGudangLayout.setVerticalGroup(
            pn_dataGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dataGudangLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pn_dataGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_info1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton1)
                .addGap(26, 26, 26))
        );

        pn_ubah.add(pn_dataGudang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pn_administrasi.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane7.setPreferredSize(new java.awt.Dimension(12001, 80));
        jScrollPane7.setRowHeaderView(null);

        tb_keuangan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Laporan", "Nama Barang", "Stok Terjual", "Harga Beli", "Harga Jual", "Diskon", "Total Jual", "Tgl Penjualan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_keuangan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tb_keuangan.setRowSelectionAllowed(false);
        jScrollPane7.setViewportView(tb_keuangan);

        jLabel28.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("LAPORAN PENJUALAN");

        cb_kategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SEMUA", "HARI INI", "KEMARIN", "3 HARI", "7 HARI", "30 HARI", "3 BULAN", "6 BULAN", "1 TAHUN" }));
        cb_kategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_kategoryActionPerformed(evt);
            }
        });

        jLabel27.setText("Pilih Karyawan : ");

        cb_namaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_namaAdminActionPerformed(evt);
            }
        });

        jLabel31.setText("Berdasarkan :");

        javax.swing.GroupLayout pn_administrasiLayout = new javax.swing.GroupLayout(pn_administrasi);
        pn_administrasi.setLayout(pn_administrasiLayout);
        pn_administrasiLayout.setHorizontalGroup(
            pn_administrasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_administrasiLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pn_administrasiLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pn_administrasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_administrasiLayout.createSequentialGroup()
                        .addComponent(lb_infoAdministrasi, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addGap(186, 186, 186)
                        .addComponent(lb_infoAdministrasi1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_administrasiLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_kategory, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_namaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_administrasiLayout.setVerticalGroup(
            pn_administrasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_administrasiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_administrasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_administrasiLayout.createSequentialGroup()
                        .addComponent(lb_infoAdministrasi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_administrasiLayout.createSequentialGroup()
                        .addGroup(pn_administrasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn_administrasiLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lb_infoAdministrasi1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                        .addGap(9, 9, 9)))
                .addGroup(pn_administrasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_kategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(cb_namaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pn_ubah.add(pn_administrasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pn_perusahaan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Kerja Sama Perusahaan");

        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane9.setPreferredSize(new java.awt.Dimension(12001, 80));

        tb_dataPerusahaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama Perusahaan", "Email", "No Telp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_dataPerusahaan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane9.setViewportView(tb_dataPerusahaan);

        jButton1.setText("Hapus");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton3.setText("Tambah");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        ip_namaPerusahaan.setBorder(javax.swing.BorderFactory.createTitledBorder("Nama Perusahaan"));

        ip_emailPerusahaan.setBorder(javax.swing.BorderFactory.createTitledBorder("Email Perusahaan"));

        ip_telpPerusahaan.setBorder(javax.swing.BorderFactory.createTitledBorder("Telepon Perusahaan"));

        jLayeredPane2.setLayer(jScrollPane9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(lb_info3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(ip_namaPerusahaan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(ip_emailPerusahaan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(ip_telpPerusahaan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ip_emailPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ip_namaPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ip_telpPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_info3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap(226, Short.MAX_VALUE)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_info3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(ip_namaPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ip_emailPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ip_telpPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(129, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pn_perusahaanLayout = new javax.swing.GroupLayout(pn_perusahaan);
        pn_perusahaan.setLayout(pn_perusahaanLayout);
        pn_perusahaanLayout.setHorizontalGroup(
            pn_perusahaanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_perusahaanLayout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_perusahaanLayout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pn_perusahaanLayout.setVerticalGroup(
            pn_perusahaanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_perusahaanLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2)
                .addContainerGap())
        );

        pn_ubah.add(pn_perusahaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout pn_utamaLayout = new javax.swing.GroupLayout(pn_utama);
        pn_utama.setLayout(pn_utamaLayout);
        pn_utamaLayout.setHorizontalGroup(
            pn_utamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_atas)
            .addGroup(pn_utamaLayout.createSequentialGroup()
                .addComponent(pn_samping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_utamaLayout.setVerticalGroup(
            pn_utamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_utamaLayout.createSequentialGroup()
                .addComponent(pn_atas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_utamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_samping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_utama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_utama, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_karyawanMouseClicked
        dashboard.setVisible(false);
        pn_tambahKaryawan.setVisible(false);
        pn_dataKaryawan.setVisible(true);
        pn_dataGudang.setVisible(false);
        pn_perusahaan.setVisible(false);
        pn_administrasi.setVisible(false);
        
        //back white fore orange
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_gudang.setForeground(new java.awt.Color(255,102,0));
        btn_gudang.setBackground(new java.awt.Color(255,255,255));
        
        btn_administrasi.setForeground(new java.awt.Color(255,102,0));
        btn_administrasi.setBackground(new java.awt.Color(255,255,255));
        
        btn_perusahaan.setForeground(new java.awt.Color(255,102,0));
        btn_perusahaan.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_karyawan.setBackground(new java.awt.Color(255,102,0));
        btn_karyawan.setForeground(new java.awt.Color(255,255,255));
        
        model = (DefaultTableModel) tb_data.getModel();
        LoadData();
        
        tambahHapus();
    }//GEN-LAST:event_btn_karyawanMouseClicked

    private void btn_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseClicked
        dashboard.setVisible(true);
        pn_tambahKaryawan.setVisible(false);
        pn_dataKaryawan.setVisible(false);
        pn_hapusKaryawan.setVisible(false);
        pn_dataGudang.setVisible(false);
        pn_administrasi.setVisible(false);
        pn_perusahaan.setVisible(false);
        
        //back white fore orange
        btn_karyawan.setForeground(new java.awt.Color(255,102,0));
        btn_karyawan.setBackground(new java.awt.Color(255,255,255));
                
        btn_gudang.setForeground(new java.awt.Color(255,102,0));
        btn_gudang.setBackground(new java.awt.Color(255,255,255));
        
        btn_administrasi.setForeground(new java.awt.Color(255,102,0));
        btn_administrasi.setBackground(new java.awt.Color(255,255,255));
        
        btn_logoutAdmin.setForeground(new java.awt.Color(255,102,0));
        btn_logoutAdmin.setBackground(new java.awt.Color(255,255,255));
        
        btn_perusahaan.setForeground(new java.awt.Color(255,102,0));
        btn_perusahaan.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_dashboard.setBackground(new java.awt.Color(255,102,0));
        btn_dashboard.setForeground(new java.awt.Color(255,255,255));
        
        tambahHapus();
    }//GEN-LAST:event_btn_dashboardMouseClicked

    private void btn_tambahKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahKaryawanMouseClicked
        pn_dataKaryawan.setVisible(false);
        pn_tambahKaryawan.setVisible(true);
        btn_karyawan.requestFocus();
    }//GEN-LAST:event_btn_tambahKaryawanMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        dashboard.setVisible(false);
        pn_dataKaryawan.setVisible(true);
        pn_tambahKaryawan.setVisible(false);
        
        
        //back white fore orange
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_gudang.setForeground(new java.awt.Color(255,102,0));
        btn_gudang.setBackground(new java.awt.Color(255,255,255));
        
        btn_administrasi.setForeground(new java.awt.Color(255,102,0));
        btn_administrasi.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_karyawan.setBackground(new java.awt.Color(255,102,0));
        btn_karyawan.setForeground(new java.awt.Color(255,255,255));
        btn_karyawan.requestFocus();
        
        tambahHapus();
    }//GEN-LAST:event_jButton2MouseClicked

    private void btn_simpanKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanKaryawanMouseClicked
        String namaLengkap = ip_nama.getText();
        String tanggalLahir = ip_tanggal.getDate().toString();
        String username = ip_username.getText();
        String password = ip_password.getText();
        String nik = ip_nik.getText();
        String id_akun = generateRandom();
        String alamat = ip_alamat.getText();
        String email = ip_email.getText();
        String jenisKelamin = (String) cb_kelamin.getSelectedItem();
        String jabatan = (String) cb_jabatan.getSelectedItem();
        String noHp = ip_noTelp.getText();
        String jenisKontrak = ip_jenisKontrak.getText();
        String gaji = ip_gaji.getText();
        String pendidikan = ip_pendidikan.getText();
        String pengalaman = ip_pengalaman.getText();
        String kontakDarurat = ip_darurat.getText();
        String nomorNPWP = ip_npwp.getText();
        String rekening = ip_rek.getText();

        // Panggil fungsi tambahData dengan nilai yang sudah diambil
        try {
            // Koneksi ke database
            Connection connection = db_connect.connect();

            // Query untuk menambahkan data
            String query = "INSERT INTO data_karyawan (id_user, NamaLengkap, Tanggal_lahir, nik, alamat, email, jenis_kelamin, jabatan, no_hp, jenis_kontrak, gaji, pendidikan, pengalaman, kontak_darurat, nomor_npwp, rekening) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id_akun);
                statement.setString(2, namaLengkap);
                statement.setString(3, tanggalLahir);
                statement.setString(4, nik);
                statement.setString(5, alamat);
                statement.setString(6, email);
                statement.setString(7, jenisKelamin);
                statement.setString(8, jabatan);
                statement.setString(9, noHp);
                statement.setString(10, jenisKontrak);
                statement.setString(11, gaji);
                statement.setString(12, pendidikan);
                statement.setString(13, pengalaman);
                statement.setString(14, kontakDarurat);
                statement.setString(15, nomorNPWP);
                statement.setString(16, rekening);
                

                statement.executeUpdate();
            }
            
            String query2 = "INSERT INTO users ( id, username, password, role) VALUES ( ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query2)) {
                statement.setString(1, id_akun);
                statement.setString(2, username);
                statement.setString(3, password);
                statement.setString(4, jabatan);

                // Eksekusi perintah insert
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    lbInfo_tambahKaryawan.setForeground(Color.green);
                    lbInfo_tambahKaryawan.setText("Karyawan Berhasil Ditambahkan");
                    tambahHapus();
                } else {
                    lbInfo_tambahKaryawan.setForeground(Color.red);
                    lbInfo_tambahKaryawan.setText("Gagal menambahkan data");
                }
            }
        } catch (SQLException e) {
            lbInfo_tambahKaryawan.setForeground(Color.orange);
            lbInfo_tambahKaryawan.setText("Database Gagal" + e.getMessage());
        }
    }//GEN-LAST:event_btn_simpanKaryawanMouseClicked

    private void btnback_hapusKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnback_hapusKaryawanMouseClicked
        dashboard.setVisible(false);
        pn_dataKaryawan.setVisible(true);
        pn_tambahKaryawan.setVisible(false);
        pn_hapusKaryawan.setVisible(false);
        pn_dataGudang.setVisible(false);
        
        
        //back white fore orange
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_gudang.setForeground(new java.awt.Color(255,102,0));
        btn_gudang.setBackground(new java.awt.Color(255,255,255));
        
        btn_administrasi.setForeground(new java.awt.Color(255,102,0));
        btn_administrasi.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_karyawan.setBackground(new java.awt.Color(255,102,0));
        btn_karyawan.setForeground(new java.awt.Color(255,255,255));
        btn_karyawan.requestFocus();
    }//GEN-LAST:event_btnback_hapusKaryawanMouseClicked

    private void btn_hapusKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusKaryawanMouseClicked
        pn_tambahKaryawan.setVisible(false);
        dashboard.setVisible(false);
        pn_dataKaryawan.setVisible(false);
        pn_hapusKaryawan.setVisible(true);
        pn_dataGudang.setVisible(false);
        
        updateComboBox(cb_namaHapus);
        btn_karyawan.requestFocus();
        lbInfo_hapusKaryawan.setVisible(false);
        
        lbNama_hapusKaryawan.setText("");
        lbJabatan_hapusKaryawan.setText("");
        lbTglLahir_hapusKaryawan.setText("");
        lbGaji_hapusKaryawan.setText("");
    }//GEN-LAST:event_btn_hapusKaryawanMouseClicked

    private void cb_namaHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_namaHapusActionPerformed
        displaySelectedEmployeeInfo(cb_namaHapus,lbNama_hapusKaryawan, lbJabatan_hapusKaryawan, lbTglLahir_hapusKaryawan, lbGaji_hapusKaryawan);
    }//GEN-LAST:event_cb_namaHapusActionPerformed

    private void btn_hapusKaryawan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusKaryawan1MouseClicked
        hapusKaryawan(cb_namaHapus,lbInfo_hapusKaryawan);
    }//GEN-LAST:event_btn_hapusKaryawan1MouseClicked

    private void btn_gudangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_gudangMouseClicked
        dashboard.setVisible(false);
        pn_dataKaryawan.setVisible(false);
        pn_tambahKaryawan.setVisible(false);
        pn_hapusKaryawan.setVisible(false);
        pn_dataGudang.setVisible(true);
        pn_administrasi.setVisible(false);
        pn_perusahaan.setVisible(false);
        
        //back white fore orange
        btn_karyawan.setForeground(new java.awt.Color(255,102,0));
        btn_karyawan.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_administrasi.setForeground(new java.awt.Color(255,102,0));
        btn_administrasi.setBackground(new java.awt.Color(255,255,255));
        
        btn_perusahaan.setForeground(new java.awt.Color(255,102,0));
        btn_perusahaan.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_gudang.setBackground(new java.awt.Color(255,102,0));
        btn_gudang.setForeground(new java.awt.Color(255,255,255));
        
            
        model = (DefaultTableModel) tb_dataGudang.getModel();
        LoadDataGudang();
        
        model = (DefaultTableModel) tb_dataRequest.getModel();
        LoadDataRequest();
        
    }//GEN-LAST:event_btn_gudangMouseClicked

    private void btn_logoutAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutAdminMouseClicked
        projectpbo.login test = new projectpbo.login();
        test.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_logoutAdminMouseClicked

    private void btn_administrasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_administrasiMouseClicked
        dashboard.setVisible(false);
        pn_tambahKaryawan.setVisible(false);
        pn_dataKaryawan.setVisible(false);
        pn_hapusKaryawan.setVisible(false);
        pn_dataGudang.setVisible(false);
        pn_administrasi.setVisible(true);
        pn_perusahaan.setVisible(false);
        
        //back white fore orange
        btn_karyawan.setForeground(new java.awt.Color(255,102,0));
        btn_karyawan.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_gudang.setForeground(new java.awt.Color(255,102,0));
        btn_gudang.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_logoutAdmin.setForeground(new java.awt.Color(255,102,0));
        btn_logoutAdmin.setBackground(new java.awt.Color(255,255,255));
        
        btn_perusahaan.setForeground(new java.awt.Color(255,102,0));
        btn_perusahaan.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_administrasi.setBackground(new java.awt.Color(255,102,0));
        btn_administrasi.setForeground(new java.awt.Color(255,255,255));
        
        updateComboBoxAdminNama(cb_namaAdmin);
        model = (DefaultTableModel) tb_keuangan.getModel();
        displaySelectKategory();
        
    }//GEN-LAST:event_btn_administrasiMouseClicked

    private void btn_perusahaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_perusahaanMouseClicked
        dashboard.setVisible(false);
        pn_tambahKaryawan.setVisible(false);
        pn_dataKaryawan.setVisible(false);
        pn_hapusKaryawan.setVisible(false);
        pn_dataGudang.setVisible(false);
        pn_administrasi.setVisible(false);
        pn_perusahaan.setVisible(true);
        
        //back white fore orange
        btn_karyawan.setForeground(new java.awt.Color(255,102,0));
        btn_karyawan.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_gudang.setForeground(new java.awt.Color(255,102,0));
        btn_gudang.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_logoutAdmin.setForeground(new java.awt.Color(255,102,0));
        btn_logoutAdmin.setBackground(new java.awt.Color(255,255,255));
        
        btn_administrasi.setForeground(new java.awt.Color(255,102,0));
        btn_administrasi.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_perusahaan.setBackground(new java.awt.Color(255,102,0));
        btn_perusahaan.setForeground(new java.awt.Color(255,255,255));
        
        model = (DefaultTableModel) tb_dataPerusahaan.getModel();
        LoadDataPerusahaan();
    }//GEN-LAST:event_btn_perusahaanMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
       String namaPerusahaan = ip_namaPerusahaan.getText();
       String email = ip_emailPerusahaan.getText();
       String noTelp = ip_telpPerusahaan.getText();
       
       if (namaPerusahaan.isEmpty()){
           lb_info3.setForeground(Color.red);
            lb_info3.setText("INPUT NAMA PERUSAHAAN");
            hideLabelAfterDelay(lb_info3, 3000);
            return;
       }
       
       if (email.isEmpty()){
               lb_info3.setForeground(Color.red);
               lb_info3.setText("INPUT EMAIL PERUSAHAAN");
               hideLabelAfterDelay(lb_info3, 3000);
               return;
       }
       
       if (noTelp.isEmpty()){
               lb_info3.setForeground(Color.red);
               lb_info3.setText("INPUT NO TELEPON PERUSAHAAN");
               hideLabelAfterDelay(lb_info3, 3000);
               return;
       }
       
       try{
           Connection connect = db_connect.connect();
           String insertQuery = "INSERT INTO list_perusahaan (nama_perusahaan, email, no_telp) VALUES (?,?,?)";
           PreparedStatement insert = connect.prepareStatement(insertQuery);
           insert.setString(1, namaPerusahaan);
           insert.setString(2, email);
           insert.setString(3, noTelp);
           int resultSet = insert.executeUpdate();
           if (resultSet > 0){
               lb_info3.setForeground(Color.green);
               lb_info3.setText("DATA BERHASIL DI INPUT");
               ip_namaPerusahaan.setText("");
               ip_emailPerusahaan.setText("");
               ip_telpPerusahaan.setText("");
               hideLabelAfterDelay(lb_info3, 3000);
               LoadDataPerusahaan();
           }else{
               lb_info3.setForeground(Color.red);
               lb_info3.setText("DATA GAGAL DI INPUT");
               hideLabelAfterDelay(lb_info3, 3000);
           }
           
       }catch(SQLException e){
           e.printStackTrace();
       }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        int selectRow = tb_dataPerusahaan.getSelectedRow();
        String nama = (String) tb_dataPerusahaan.getValueAt(selectRow, 0);
        
        try{
            Connection connect = db_connect.connect();
            String query = "DELETE FROM list_perusahaan WHERE nama_perusahaan = ?";
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setString(1, nama);
            int result = statement.executeUpdate();
            if (result > 0){
               lb_info3.setForeground(Color.green);
               lb_info3.setText("DATA BERHASIL DI HAPUS");
               hideLabelAfterDelay(lb_info3, 3000);
               LoadDataPerusahaan();
            }else{
               lb_info3.setForeground(Color.red);
               lb_info3.setText("DATA GAGAL DI HAPUS");
               hideLabelAfterDelay(lb_info3, 3000);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void cb_kategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_kategoryActionPerformed
        model = (DefaultTableModel) tb_keuangan.getModel();
        displaySelectKategory();
    }//GEN-LAST:event_cb_kategoryActionPerformed

    private void cb_namaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_namaAdminActionPerformed
        model = (DefaultTableModel) tb_keuangan.getModel();
        displaySelectKategory();
    }//GEN-LAST:event_cb_namaAdminActionPerformed

    private void jToggleButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseClicked
        int row = tb_dataRequest.getSelectedRow();

        // Check if a row is selected
        if (row >= 0) {
            Object idBarangObj = model.getValueAt(row, 0);

            // Check if the value is not null and is of type Integer
            if (idBarangObj != null && idBarangObj instanceof Integer) {
                int idBarang = (int) idBarangObj;

                try {
                    Connection connect = db_connect.connect();
                    String query = "DELETE FROM request_barang WHERE id_request = ?";
                    PreparedStatement statement = connect.prepareStatement(query);
                    statement.setInt(1, idBarang);

                    // Use executeUpdate() for DDL statements
                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        lb_info1.setForeground(Color.green);
                        lb_info1.setText("REQUEST SELESAI");
                        LoadDataRequest();
                        hideLabelAfterDelay(lb_info1, 2000);
                    } else {
                        lb_info1.setForeground(Color.red);
                        lb_info1.setText("GAGAL MENGHAPUS REQUEST");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                // Handle the case where the value is not of type Integer
                lb_info1.setForeground(Color.red);
                lb_info1.setText("Pilih baris dengan benar");
            }
        } else {
            // Handle the case where no row is selected
            lb_info1.setForeground(Color.red);
            lb_info1.setText("Pilih baris terlebih dahulu");
        }
    }//GEN-LAST:event_jToggleButton1MouseClicked
    
    public void updateComboBox(JComboBox comboBox) {
        Connection connection = db_connect.connect();
        try {
            String query = "SELECT id_user, NamaLengkap, jabatan, Tanggal_lahir, gaji FROM data_karyawan";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            DefaultComboBoxModel<String> modelCbBox = new DefaultComboBoxModel<>();
            while (resultSet.next()) {
                String nama = resultSet.getString("NamaLengkap");
                int id_user = resultSet.getInt("id_user");
                setIdUser(id_user);
                
                modelCbBox.addElement(nama);                
            }

            comboBox.setModel(modelCbBox);
            
            resultSet.close();
            statement.close();
            connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateComboBoxAdminNama(JComboBox comboBox) {
        Connection connection = db_connect.connect();
        try {
            String query = "SELECT nama_kasir FROM laporan_penjualan GROUP BY nama_kasir ORDER BY nama_kasir ASC";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            DefaultComboBoxModel<String> modelCbBox = new DefaultComboBoxModel<>();
            modelCbBox.addElement("SEMUA KASIR");
            while (resultSet.next()) {
                String nama = resultSet.getString("nama_kasir");
                
                modelCbBox.addElement(nama);                
            }

            comboBox.setModel(modelCbBox);
            
            resultSet.close();
            statement.close();
            connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void displaySelectedEmployeeInfo(JComboBox<String> comboBox, JLabel nameLabel, JLabel jabatanLabel, JLabel tanggalLabel, JLabel gajiLabel) {
        Connection connection = db_connect.connect();
        try {
            String selectedNama = (String) comboBox.getSelectedItem();

            String query = "SELECT NamaLengkap, jabatan, Tanggal_lahir, gaji FROM data_karyawan WHERE NamaLengkap=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedNama);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nama = resultSet.getString("NamaLengkap");
                String jabatan = resultSet.getString("jabatan");
                String tanggalLahir = resultSet.getString("Tanggal_lahir");
                String gaji = resultSet.getString("gaji");

                nameLabel.setText(nama);
                jabatanLabel.setText(jabatan);
                tanggalLabel.setText(tanggalLahir);
                gajiLabel.setText(gaji);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean hapusKaryawan(JComboBox<String> comboBox, JLabel lbInfo_hapusKaryawan) {
        Connection connection = db_connect.connect();
        try {
            int selectedID = getIdUser();

            String query = "DELETE FROM data_karyawan WHERE id_user = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, selectedID);
            statement.executeUpdate();
            
            String query2 = "DELETE FROM users WHERE id = ?";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setInt(1, selectedID);
            
            int rowsAffected = statement2.executeUpdate();
            
            if (rowsAffected > 0) {
                lbInfo_hapusKaryawan.setForeground(Color.green);
                lbInfo_hapusKaryawan.setText("Karyawan Berhasil Dihapus");
                updateComboBox(comboBox); // Update the JComboBox after deletion
                return true;
            } else {
                lbInfo_hapusKaryawan.setForeground(Color.red);
                lbInfo_hapusKaryawan.setText("Gagal menghapus data");
                return false;
            }
        } catch (SQLException e) {
            lbInfo_hapusKaryawan.setForeground(Color.orange);
            lbInfo_hapusKaryawan.setText("Database Gagal" + e.getMessage());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public static int hitungJumlahKaryawan() {
        int jumlahKaryawan = 0;

        try {
            // Membuat koneksi ke database
            java.sql.Connection connection = db_connect.connect();

            // Melakukan query untuk mengambil jumlah data kasir dari tabel users
            String query = "SELECT COUNT(*) AS jumlah_karyawan FROM data_karyawan";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Mengeksekusi query dan mendapatkan hasilnya
                ResultSet resultSet = statement.executeQuery();

                // Mengambil data dari hasil query
                if (resultSet.next()) {
                    // Mengambil nilai jumlah kasir
                    jumlahKaryawan = resultSet.getInt("jumlah_karyawan");
                }
            }

            // Menutup koneksi
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jumlahKaryawan;
    }
    
    public static int hitungJumlahStokBarang() {
        int jumlahStokBarang = 0;

        try {
            // Membuat koneksi ke database
            java.sql.Connection connection = db_connect.connect();

            // Melakukan query untuk mengambil jumlah data kasir dari tabel users
            String query = "SELECT COUNT(*) AS total_jumlah FROM data_barang WHERE status = 'Gudang'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Mengeksekusi query dan mendapatkan hasilnya
                ResultSet resultSet = statement.executeQuery();

                // Mengambil data dari hasil query
                if (resultSet.next()) {
                    // Mengambil nilai jumlah kasir
                    jumlahStokBarang = resultSet.getInt("total_jumlah");
                }
            }

            // Menutup koneksi
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jumlahStokBarang;
    }
    
    public static int hitungJumlahPendapatan() {
        long jumlahPendapatan = 0;

        try {
            // Membuat koneksi ke database
            java.sql.Connection connection = db_connect.connect();

            // Melakukan query untuk mengambil jumlah data kasir dari tabel users
            String query = "SELECT SUM(total_penjualan) AS penjualan FROM laporan_penjualan";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Mengeksekusi query dan mendapatkan hasilnya
                ResultSet resultSet = statement.executeQuery();

                // Mengambil data dari hasil query
                if (resultSet.next()) {
                    // Mengambil nilai jumlah kasir
                    jumlahPendapatan = resultSet.getInt("penjualan");
                }
            }

            // Menutup koneksi
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (int) jumlahPendapatan;
    }
    
    private void showNavbar() {
        int jumlahKaryawan = hitungJumlahKaryawan();
        int jumlahBarang = hitungJumlahStokBarang();
        int jumlahPendapatan = hitungJumlahPendapatan();
        int id_user = this.userId ;
        try{
            // membuat koneksi ke database
            Connection connection = db_connect.connect();
            
            //melakukan query untuk memeriksa login
            String query = "SELECT id_karyawan, NamaLengkap, jabatan FROM data_karyawan WHERE id_user = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id_user);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int id_karyawan = resultSet.getInt("id_karyawan");
                        String namaLengkap = resultSet.getString("NamaLengkap");
                        String jabatan = resultSet.getString("jabatan");
                        
                        lb_id.setText(String.valueOf(id_karyawan));
                        lb_nama.setText(namaLengkap);
                        lb_role.setText(jabatan);
                    }else{
                        lb_nama.setText("Database Tidak Sinkron");
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
       
       lb_role.setVisible(true);
       lb_role.setText(userRole);
       
       LocalDate today = LocalDate.now();
       String formattedDate = today.toString();

       lb_date.setVisible(true);
       lb_date.setText(formattedDate);
       
       lb_totalKaryawan.setText(String.valueOf(jumlahKaryawan));
       
       lb_stok1.setText(String.valueOf(jumlahPendapatan));
       
       
    }
    
    public void LoadData() {
        // Membuat model tabel
        model.setRowCount(0); // Mengosongkan tabel

        try {
            // Koneksi ke database
            Connection connection = db_connect.connect();

            // Query untuk mengambil data
            String query = "SELECT * FROM data_karyawan";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                // Menambahkan data ke model tabel
                while (resultSet.next()) {
                    Object[] row = {
                        resultSet.getInt("id_karyawan"),
                        resultSet.getString("NamaLengkap"),
                        resultSet.getString("Tanggal_lahir"),
                        resultSet.getString("nik"),
                        resultSet.getString("alamat"),
                        resultSet.getString("email"),
                        resultSet.getString("jenis_kelamin"),
                        resultSet.getString("jabatan"),
                        resultSet.getString("no_hp"),
                        resultSet.getString("jenis_kontrak"),
                        resultSet.getString("gaji"),
                        resultSet.getString("pendidikan"),
                        resultSet.getString("pengalaman"),
                        resultSet.getString("kontak_darurat"),
                        resultSet.getString("nomor_npwp"),
                        resultSet.getString("rekening")
                        
                        // Sesuaikan dengan kolom-kolom tabel Anda
                    };
                    model.addRow(row);
                }
            }

            connection.close(); // Tutup koneksi setelah selesai
        } catch (SQLException e) {
            lb_info.setText("Database Gagal");
        }
    }
    
    public void LoadDataGudang() {
        // Membuat model tabel
        model.setRowCount(0); // Mengosongkan tabel

        try {
            // Koneksi ke database
            Connection connection = db_connect.connect();

            // Query untuk mengambil data
            String query = "SELECT * FROM data_barang";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                // Menambahkan data ke model tabel
                while (resultSet.next()) {
                    Object[] row = {
                        resultSet.getString("no_seri"),
                        resultSet.getString("nama_barang"),
                        resultSet.getInt("stok_gudang"),
                        resultSet.getInt("stok_etalase"),
                        resultSet.getInt("harga_satuan"),
                        resultSet.getString("expired")
                                                
                        // Sesuaikan dengan kolom-kolom tabel Anda
                    };
                    model.addRow(row);
                }
            }

            connection.close(); // Tutup koneksi setelah selesai
        } catch (SQLException e) {
            lb_info1.setText("Database Gudang Gagal");
        }
    }
    
    public void LoadDataRequest() {
        // Membuat model tabel
        model.setRowCount(0); // Mengosongkan tabel

        try {
            // Koneksi ke database
            Connection connection = db_connect.connect();

            // Query untuk mengambil data
            String query = "SELECT request_barang.id_request, request_barang.nama_karyawan, request_barang.nama_perusahaan, request_barang.nama_barang, request_barang.stok_request, request_barang.date_request, list_perusahaan.email, list_perusahaan.no_telp FROM request_barang JOIN list_perusahaan ON request_barang.nama_perusahaan = list_perusahaan.nama_perusahaan ORDER BY request_barang.id_request ASC";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Menambahkan data ke model tabel
                while (resultSet.next()) {
                    Object[] row = {
                        resultSet.getInt("id_request"),
                        resultSet.getString("nama_barang"),
                        resultSet.getInt("stok_request"),
                        resultSet.getString("nama_perusahaan"),
                        resultSet.getString("nama_karyawan"),
                        resultSet.getString("email"),
                        resultSet.getString("no_telp"),
                        resultSet.getString("date_request")
                    };
                    model.addRow(row);
                }
            }

            connection.close(); // Tutup koneksi setelah selesai
        } catch (SQLException e) {
            e.printStackTrace();
            lb_info1.setText("Database Request Gagal");
        }
    }
    
    public void LoadDataPerusahaan() {
        // Membuat model tabel
        model.setRowCount(0); // Mengosongkan tabel

        try {
            // Koneksi ke database
            Connection connection = db_connect.connect();

            // Query untuk mengambil data
            String query = "SELECT * FROM list_perusahaan";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                // Menambahkan data ke model tabel
                while (resultSet.next()) {
                    Object[] row = {
                        resultSet.getString("nama_perusahaan"),
                        resultSet.getString("email"),
                        resultSet.getString("no_telp")
 
                    };
                    model.addRow(row);
                }
            }

            connection.close(); // Tutup koneksi setelah selesai
        } catch (SQLException e) {
            e.printStackTrace();
            lb_info3.setText("Database Request Gagal");
        }
    }
    
      
    private void displaySelectKategory(){
        String kategory = (String) cb_kategory.getSelectedItem();
        String namaKasir = (String) cb_namaAdmin.getSelectedItem();
        String query ;
        String kasirCondition = "";
      
        switch (kategory) {
            case "SEMUA":
                if (kategory == "SEMUA" && namaKasir == "SEMUA KASIR"){                 
                    model.setRowCount(0);
                    namaKasir = "";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("SEMUA DATA");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                } else {
                    model.setRowCount(0);
                    kasirCondition = "WHERE nama_kasir = ?";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("SEMUA DATA");
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi1.setText(namaKasir);
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan " + kasirCondition;
                    fetchData(query,namaKasir);
                }
                break;
            case "HARI INI":
                if (kategory == "HARI INI" && namaKasir == "SEMUA KASIR"){
                    model.setRowCount(0);
                    namaKasir = "";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("HARI INI");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() AND tgl_penjualan < CURDATE() + INTERVAL 1 DAY " + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                } else {
                    model.setRowCount(0);
                    kasirCondition = "AND nama_kasir = ?";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("HARI INI");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() AND tgl_penjualan < CURDATE() + INTERVAL 1 DAY " + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                }
                break;
            case "KEMARIN":
                if (kategory == "KEMARIN" && namaKasir == "SEMUA KASIR"){
                    model.setRowCount(0);
                    namaKasir = "";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("KEMARIN");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 1 DAY AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                }else{
                    model.setRowCount(0);
                    kasirCondition = "AND nama_kasir = ?";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("KEMARIN");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 1 DAY AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                }
                break;
            case "3 HARI":
                if (kategory == "HARI INI" && namaKasir == "SEMUA KASIR"){
                    model.setRowCount(0);
                    namaKasir = "";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("3 HARI");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 3 DAY AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                }else{
                    model.setRowCount(0);
                    kasirCondition = "AND nama_kasir = ?";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("3 HARI");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 3 DAY AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                }
                break;
            case "7 HARI":
                if (kategory == "HARI INI" && namaKasir == "SEMUA KASIR"){
                    model.setRowCount(0);
                    namaKasir = "";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("7 HARI");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 7 DAY AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                }else{
                    model.setRowCount(0);
                    kasirCondition = "AND nama_kasir = ?";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("7 HARI");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 7 DAY AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                }
                break;
            case "30 HARI":
                if (kategory == "30 HARI" && namaKasir == "SEMUA KASIR"){
                    model.setRowCount(0);
                    namaKasir = "";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("30 HARI");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 30 DAY AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                    
                }else{
                    model.setRowCount(0);
                    kasirCondition = "AND nama_kasir = ?";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("30 HARI");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 30 DAY AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                }
                break;
            case "6 BULAN":
                 if (kategory == "6 BULAN" && namaKasir == "SEMUA KASIR"){
                     model.setRowCount(0);
                     namaKasir = "";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("6 BULAN");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 6 MONTH AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                 }else{
                    model.setRowCount(0);
                    kasirCondition = "AND nama_kasir = ?";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("6 BULAN");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 6 MONTH AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                 }
                break;
            case "1 TAHUN":
                 if (kategory == "1 TAHUN" && namaKasir == "SEMUA KASIR"){
                     model.setRowCount(0);
                     namaKasir = "";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("1 TAHUN");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 1 YEAR AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                 }else{
                    model.setRowCount(0);
                    kasirCondition = "AND nama_kasir = ?";
                    lb_infoAdministrasi.setForeground(Color.black);
                    lb_infoAdministrasi.setText("1 TAHUN");
                    query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan WHERE tgl_penjualan >= CURDATE() - INTERVAL 1 YEAR AND tgl_penjualan < CURDATE()" + kasirCondition;
                    hideLabelAfterDelay(lb_infoAdministrasi, 1000);
                    fetchData(query,namaKasir);
                 }
                break;
        }

    }
    
    private void fetchData(String query, String namaKasir) {
        try {
            Connection connection = db_connect.connect();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Set parameter namaKasir if needed
                if (!"".equals(namaKasir)) {
                    preparedStatement.setString(1, namaKasir);
                }

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Populate data into the table
                        Object[] row = {
                            resultSet.getInt("kode_laporan"),
                            resultSet.getString("nama_barang"),
                            resultSet.getInt("stok"),
                            resultSet.getInt("harga_beli"),
                            resultSet.getInt("harga_final"),
                            resultSet.getInt("diskon"),
                            resultSet.getInt("total_penjualan"),
                            resultSet.getDate("tgl_penjualan")
                            // ... (adjust with other columns)
                        };
                        model.addRow(row);
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void LoadDataLaporan() {
        // Membuat model tabel
        model.setRowCount(0); // Mengosongkan tabel

        try {
            // Koneksi ke database
            Connection connection = db_connect.connect();

            // Query untuk mengambil data
            String query = "SELECT * , COALESCE (harga_diskon, harga_jual) AS harga_final FROM laporan_penjualan";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                // Menambahkan data ke model tabel
                while (resultSet.next()) {
                    Object[] row = {
                        resultSet.getInt("kode_laporan"),
                        resultSet.getString("nama_barang"),
                        resultSet.getInt("stok"),
                        resultSet.getInt("harga_beli"),
                        resultSet.getInt("harga_final"),
                        resultSet.getInt("diskon"),
                        resultSet.getInt("total_penjualan"),
                        resultSet.getDate("tgl_penjualan")
 
                    };
                    model.addRow(row);
                }
            }

            connection.close(); // Tutup koneksi setelah selesai
        } catch (SQLException e) {
            e.printStackTrace();
            lb_info1.setText("Database Request Gagal");
        }
    }

    
    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_administrasi;
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_gudang;
    private javax.swing.JButton btn_hapusKaryawan;
    private javax.swing.JButton btn_hapusKaryawan1;
    private javax.swing.JButton btn_karyawan;
    private javax.swing.JButton btn_logoutAdmin;
    private javax.swing.JButton btn_perusahaan;
    private javax.swing.JButton btn_simpanKaryawan;
    private javax.swing.JButton btn_tambahKaryawan;
    private javax.swing.JButton btnback_hapusKaryawan;
    private javax.swing.JComboBox<String> cb_jabatan;
    private javax.swing.JComboBox<String> cb_kategory;
    private javax.swing.JComboBox<String> cb_kelamin;
    private javax.swing.JComboBox<String> cb_namaAdmin;
    private javax.swing.JComboBox<String> cb_namaHapus;
    private javax.swing.JLayeredPane dashboard;
    private javax.swing.JLabel gb_dashAdmin;
    private javax.swing.JLabel gb_dashboard;
    private javax.swing.JTextArea ip_alamat;
    private javax.swing.JTextField ip_darurat;
    private javax.swing.JTextField ip_email;
    private javax.swing.JFormattedTextField ip_emailPerusahaan;
    private javax.swing.JTextField ip_gaji;
    private javax.swing.JTextField ip_jenisKontrak;
    private javax.swing.JTextField ip_nama;
    private javax.swing.JFormattedTextField ip_namaPerusahaan;
    private javax.swing.JTextField ip_nik;
    private javax.swing.JTextField ip_noTelp;
    private javax.swing.JTextField ip_npwp;
    private javax.swing.JPasswordField ip_password;
    private javax.swing.JTextField ip_pendidikan;
    private javax.swing.JTextArea ip_pengalaman;
    private javax.swing.JTextField ip_rek;
    private com.toedter.calendar.JDateChooser ip_tanggal;
    private javax.swing.JFormattedTextField ip_telpPerusahaan;
    private javax.swing.JTextField ip_username;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lbGaji_hapusKaryawan;
    private javax.swing.JLabel lbInfo_hapusKaryawan;
    private javax.swing.JLabel lbInfo_tambahKaryawan;
    private javax.swing.JLabel lbJabatan_hapusKaryawan;
    private javax.swing.JLabel lbNama_hapusKaryawan;
    private javax.swing.JLabel lbTglLahir_hapusKaryawan;
    private javax.swing.JLabel lb_come;
    private javax.swing.JLabel lb_date;
    private javax.swing.JLabel lb_halo;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_info;
    private javax.swing.JLabel lb_info1;
    private javax.swing.JLabel lb_info3;
    private javax.swing.JLabel lb_infoAdministrasi;
    private javax.swing.JLabel lb_infoAdministrasi1;
    private javax.swing.JLabel lb_jabatan;
    private javax.swing.JLabel lb_jumlahKaryawan;
    private javax.swing.JLabel lb_nama;
    private javax.swing.JLabel lb_pegawai;
    private javax.swing.JLabel lb_pendapatanBulanKmrn;
    private javax.swing.JLabel lb_role;
    private javax.swing.JLabel lb_stok;
    private javax.swing.JLabel lb_stok1;
    private javax.swing.JLabel lb_stokBarang;
    private javax.swing.JLabel lb_tambahKaryawan;
    private javax.swing.JLabel lb_tanggal;
    private javax.swing.JLabel lb_totalKaryawan;
    private javax.swing.JPanel pn_administrasi;
    private javax.swing.JLayeredPane pn_atas;
    private javax.swing.JPanel pn_dataGudang;
    private javax.swing.JPanel pn_dataKaryawan;
    private javax.swing.JPanel pn_hapusKaryawan;
    private javax.swing.JPanel pn_jabatan;
    private javax.swing.JPanel pn_karyawan;
    private javax.swing.JPanel pn_nama;
    private javax.swing.JPanel pn_pegawai;
    private javax.swing.JPanel pn_pendapatan;
    private javax.swing.JPanel pn_perusahaan;
    private javax.swing.JLayeredPane pn_samping;
    private javax.swing.JPanel pn_stokBarang;
    private javax.swing.JPanel pn_tambahKaryawan;
    private javax.swing.JPanel pn_tanggal;
    private javax.swing.JPanel pn_ubah;
    private javax.swing.JPanel pn_utama;
    private javax.swing.JTable tb_data;
    private javax.swing.JTable tb_dataGudang;
    private javax.swing.JTable tb_dataPerusahaan;
    private javax.swing.JTable tb_dataRequest;
    private javax.swing.JTable tb_keuangan;
    // End of variables declaration//GEN-END:variables
}
