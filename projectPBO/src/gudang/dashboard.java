package gudang;

import com.toedter.calendar.JDateChooser;
import javax.swing.table.DefaultTableModel;
import config.db_connect;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.Timer;


public class dashboard extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */
    private final int userId;
    private final String userRole;
    private DefaultTableModel model;
    private int id_barang;
    private long harga_jual,harga_satuan;
    
    /**
     * Creates new form admin
     * @param userId
     * @param username
     * @param userRole
     */
    
    public dashboard(int userId, String username, String userRole) {
        initComponents();
        dashboard.setVisible(true);
        pn_dataBarang.setVisible(false);
        pn_barang.setVisible(false);
        pn_refund.setVisible(false);
        pn_barangKeluar.setVisible(false);
        pn_hapusBarang.setVisible(false);
        pn_request.setVisible(false);
        pn_profilGudang.setVisible(false);
        
        //back white fore orange
        btn_DataBarang.setForeground(new java.awt.Color(255,102,0));
        btn_DataBarang.setBackground(new java.awt.Color(255,255,255));
        
        btn_barang.setForeground(new java.awt.Color(255,102,0));
        btn_barang.setBackground(new java.awt.Color(255,255,255));
        
        btn_editProfile.setForeground(new java.awt.Color(255,102,0));
        btn_editProfile.setBackground(new java.awt.Color(255,255,255));
        
        btn_requestBarang.setForeground(new java.awt.Color(255,102,0));
        btn_requestBarang.setBackground(new java.awt.Color(255,255,255));
        
        btn_logoutGudang.setForeground(new java.awt.Color(255,102,0));
        btn_logoutGudang.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_dashboard.setBackground(new java.awt.Color(255,102,0));
        btn_dashboard.setForeground(new java.awt.Color(255,255,255));
        
        this.userId = userId;
        this.userRole = userRole;
        //this.username = username;
        // Tampilkan data admin
        showNavbar();
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public long getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(long harga_jual) {
        this.harga_jual = harga_jual;
    }

    public long getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(long harga_satuan) {
        this.harga_satuan = harga_satuan;
    }
    
    
    
    public static Date convertJDateToSqlDate(JDateChooser jDateChooser) {
        // Mendapatkan tanggal dari JDateChooser
        java.util.Date utilDate = jDateChooser.getDate();

        if (utilDate != null) {
            // Konversi java.util.Date ke java.sql.Date
            Date sqlDate = new Date(utilDate.getTime());
            return sqlDate;
        }

        return null;
    }
    
    void bersihHapus(){
        ip_NoSeriHapus.setText("");
        dt_namaBarangHapus.setText("");
        dt_stokHapus.setText("");
        
        // Clear the items in the comboBox
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
        cb_expiredHapus.setModel(model2);
                
        text_HapusBarang.setText("");
    }
    
    void bersihTambah(){
        ip_NamaBarang.setText("");
        ip_NoSeri.setText("");
        ip_Stok.setValue(0);
        ip_HargaSatuan.setText("");
        lb_info.setForeground(Color.black);
    }
    
    void bersihRequest(){
        p_namaBarangRequest.setText("");
        p_StokRequest.setValue(0);
        lb_infoRequest.setText("");
    }
    
    void bersihRefund(){
        ip_NoSeriRefund.setText("");
        lb_NamaBarangRefund.setText("");
        lb_StokTersedia.setText("");
        
        // Clear the items in the comboBox
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
        cb_refundBarang.setModel(model2);
                
        ip_StokRefund.setValue(0);
        ip_alasanRefund.setText("");
    }
    
    void bersihKeluar(){
        ip_NoSeriKeluar.setText("");
        dt_NamaBarangKeluar.setText("");
        dt_StokTersediaGudang.setText("");
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
        cb_ExpiredKeluar.setModel(model2);
        ip_StokKeluar.setValue(0);
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
    
    public static int hitungBarangGudang() {
        int jumlahBarang = 0;

        try {
            // Membuat koneksi ke database
            java.sql.Connection connection = db_connect.connect();

            // Melakukan query untuk mengambil jumlah data kasir dari tabel users
            String query = "SELECT COUNT(*) AS StokGudang FROM data_barang WHERE status = 'Gudang'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Mengeksekusi query dan mendapatkan hasilnya
                ResultSet resultSet = statement.executeQuery();

                // Mengambil data dari hasil query
                if (resultSet.next()) {
                    // Mengambil nilai jumlah kasir
                    jumlahBarang = resultSet.getInt("StokGudang");
                }
            }

            // Menutup koneksi
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jumlahBarang;
    }
    
    public static int BarangRequest(){
        int jumlahRequest = 0;
        
        try {
            // Membuat koneksi ke database
            Connection connection = db_connect.connect();

            // Melakukan query untuk mengambil jumlah data kasir dari tabel users
            String query = "SELECT COUNT(*) AS jumlah_refund FROM request_barang";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Mengeksekusi query dan mendapatkan hasilnya
                ResultSet resultSet = statement.executeQuery();

                // Mengambil data dari hasil query
                if (resultSet.next()) {
                    // Mengambil nilai jumlah kasir
                    jumlahRequest = resultSet.getInt("jumlah_refund");
                    
                }
            }

            // Menutup koneksi
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jumlahRequest ;
    }
    
    public static int BarangRefund() {
        int jumlahRefund = 0;

        try {
            // Membuat koneksi ke database
            Connection connection = db_connect.connect();

            // Melakukan query untuk mengambil jumlah data kasir dari tabel users
            String query = "SELECT COUNT(*) AS jumlah_refund FROM barang_refund";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Mengeksekusi query dan mendapatkan hasilnya
                ResultSet resultSet = statement.executeQuery();

                // Mengambil data dari hasil query
                if (resultSet.next()) {
                    // Mengambil nilai jumlah kasir
                    jumlahRefund = resultSet.getInt("jumlah_refund");
                }
            }

            // Menutup koneksi
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jumlahRefund;
    }
    
    public static int BarangEtalase() {
        int jumlahEtalase = 0;

        try {
            // Membuat koneksi ke database
            java.sql.Connection connection = db_connect.connect();

            // Melakukan query untuk mengambil jumlah data kasir dari tabel users
            String query = "SELECT COUNT(*) AS jumlah_etalase FROM barang_etalase";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Mengeksekusi query dan mendapatkan hasilnya
                ResultSet resultSet = statement.executeQuery();

                // Mengambil data dari hasil query
                if (resultSet.next()) {
                    // Mengambil nilai jumlah kasir
                    jumlahEtalase = resultSet.getInt("jumlah_etalase");
                }
            }

            // Menutup koneksi
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jumlahEtalase;
    }
    
    public void LoadData() {
        // Membuat model tabel
        model.setRowCount(0); // Mengosongkan tabel

        try {
            // Koneksi ke database
            Connection connection = db_connect.connect();

            // Query untuk mengambil data
            String query = "SELECT no_seri,nama_barang,stok_gudang,expired,tgl_input FROM data_barang WHERE status = 'Gudang'";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    
                ResultSet resultSet = statement.executeQuery()) {
                // Menambahkan data ke model tabel
                while (resultSet.next()) {
                    Object[] row = {
                        resultSet.getString("no_seri"),
                        resultSet.getString("nama_barang"),
                        resultSet.getInt("stok_gudang"),
                        resultSet.getString("expired"),
                        resultSet.getDate("tgl_input")
                    };
                    model.addRow(row);
                }
            }

            connection.close(); // Tutup koneksi setelah selesai
        } catch (SQLException e) {
            lb_welcome1.setText("Database Gagal" + e);
        }
    }
      
  
    
    private void showNavbar() {
        int barangGudang = hitungBarangGudang();
        int barangRequest = BarangRequest();
        int BarangRefund = BarangRefund();
        int BarangEtalase = BarangEtalase();
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
                        String namaLengkap = resultSet.getString("NamaLengkap");
                        String jabatan = resultSet.getString("jabatan");
                        
                        lb_id.setText(String.valueOf(id_user));
                        lb_nama.setText(namaLengkap);
                        lb_jabatan.setText(jabatan);
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
       
       d_bg.setText(String.valueOf(barangGudang));
       
       d_bdp.setText(String.valueOf(barangRequest));
       
       d_br.setText(String.valueOf(BarangRefund));
       
       d_be.setText(String.valueOf(BarangEtalase));
       
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
        btn_requestBarang = new javax.swing.JButton();
        btn_dashboard = new javax.swing.JButton();
        btn_DataBarang = new javax.swing.JButton();
        btn_barang = new javax.swing.JButton();
        btn_editProfile = new javax.swing.JButton();
        btn_logoutGudang = new javax.swing.JButton();
        pn_content = new javax.swing.JLayeredPane();
        pn_barangKeluar = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        title_BarangKeluar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        title_NoSeriKeluar = new javax.swing.JLabel();
        ip_NoSeriKeluar = new javax.swing.JTextField();
        cari_keluar = new javax.swing.JButton();
        title_NamaBarangKeluar = new javax.swing.JLabel();
        dt_NamaBarangKeluar = new javax.swing.JLabel();
        title_StokTersediaGudang = new javax.swing.JLabel();
        dt_StokTersediaGudang = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        title_StokKeluar = new javax.swing.JLabel();
        ip_StokKeluar = new javax.swing.JSpinner();
        title_ExpiredKeluar = new javax.swing.JLabel();
        cb_ExpiredKeluar = new javax.swing.JComboBox<>();
        lb_infoBarangKeluar = new javax.swing.JLabel();
        btn_SimpanBarangKeluar = new javax.swing.JButton();
        btn_BackBarangKeluar = new javax.swing.JButton();
        dashboard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pn_barangGudang = new javax.swing.JPanel();
        lb_bg = new javax.swing.JLabel();
        d_bg = new javax.swing.JLabel();
        pn_brg_perjalanan = new javax.swing.JPanel();
        lb_bdp = new javax.swing.JLabel();
        d_bdp = new javax.swing.JLabel();
        pn_return = new javax.swing.JPanel();
        lb_br = new javax.swing.JLabel();
        d_br = new javax.swing.JLabel();
        pn_return1 = new javax.swing.JPanel();
        lb_be = new javax.swing.JLabel();
        d_be = new javax.swing.JLabel();
        pn_dataBarang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_dataBarang = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_dataCacat = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        pn_barang = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        title_namaBarang = new javax.swing.JLabel();
        title_NoSeri = new javax.swing.JLabel();
        title_Stok = new javax.swing.JLabel();
        title_HargaSatuan = new javax.swing.JLabel();
        ip_NamaBarang = new javax.swing.JTextField();
        ip_NoSeri = new javax.swing.JTextField();
        ip_HargaSatuan = new javax.swing.JTextField();
        btn_TambahBarang = new javax.swing.JButton();
        btn_Refund = new javax.swing.JButton();
        btn_BarangKeluar = new javax.swing.JButton();
        btn_hapusBarang = new javax.swing.JButton();
        lb_info = new javax.swing.JLabel();
        ip_Stok = new javax.swing.JSpinner();
        title_Stok1 = new javax.swing.JLabel();
        ip_dateExp = new com.toedter.calendar.JDateChooser();
        cb_inputCategory = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        pn_refund = new javax.swing.JPanel();
        title_Refund = new javax.swing.JLabel();
        lb_NamaBarangRefund = new javax.swing.JLabel();
        title_NoSeriRefund = new javax.swing.JLabel();
        ip_NoSeriRefund = new javax.swing.JTextField();
        title_StokRefund = new javax.swing.JLabel();
        ip_StokRefund = new javax.swing.JSpinner();
        title_HargaSatuanRefund = new javax.swing.JLabel();
        btn_simpanRefund = new javax.swing.JButton();
        btn_backRefund = new javax.swing.JButton();
        jScrollPaneRefund = new javax.swing.JScrollPane();
        ip_alasanRefund = new javax.swing.JTextArea();
        title_namaBarangRefund1 = new javax.swing.JLabel();
        lb_StokTersedia = new javax.swing.JLabel();
        title_StokTersedia = new javax.swing.JLabel();
        cari_refund = new javax.swing.JButton();
        title_StokRefund1 = new javax.swing.JLabel();
        cb_refundBarang = new javax.swing.JComboBox<>();
        lb_infoRefund = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        cb_categoryRefund = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_refund = new javax.swing.JTable();
        pn_hapusBarang = new javax.swing.JPanel();
        alasan_HapusBarang = new javax.swing.JLabel();
        textArea_HapusBarang = new javax.swing.JScrollPane();
        text_HapusBarang = new javax.swing.JTextArea();
        btn_backHapusBarang = new javax.swing.JButton();
        btn_simpanHapusBarang = new javax.swing.JButton();
        title_NoSeriHapusBarang = new javax.swing.JLabel();
        ip_NoSeriHapus = new javax.swing.JTextField();
        title_HapusBarang = new javax.swing.JLabel();
        lb_infoHapus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_cariHapus = new javax.swing.JButton();
        dt_namaBarangHapus = new javax.swing.JLabel();
        dt_stokHapus = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cb_expiredHapus = new javax.swing.JComboBox<>();
        pn_request = new javax.swing.JPanel();
        btn_simpanRequest = new javax.swing.JButton();
        p_StokRequest = new javax.swing.JSpinner();
        title_StokRequest = new javax.swing.JLabel();
        p_namaBarangRequest = new javax.swing.JTextField();
        title_namaBarangRequest = new javax.swing.JLabel();
        title_Request = new javax.swing.JLabel();
        lb_infoRequest = new javax.swing.JLabel();
        cb_requestBarang = new javax.swing.JComboBox<>();
        title_namaBarangRequest1 = new javax.swing.JLabel();
        pn_profilGudang = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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
        btn_simpanProfil = new javax.swing.JButton();
        ip_namaProfil = new javax.swing.JTextField();
        ip_nikProfil = new javax.swing.JTextField();
        ip_emailProfil = new javax.swing.JTextField();
        ip_hpProfil = new javax.swing.JTextField();
        ip_kontakProfil = new javax.swing.JTextField();
        ip_npwpProfil = new javax.swing.JTextField();
        ip_rekeningProfil = new javax.swing.JTextField();
        lb_idProfil = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
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
        lb_jabatan.setText("Gudang");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lb_id)
                    .addComponent(lb_idKaryawan))
                .addGap(35, 35, 35))
        );
        pn_4Layout.setVerticalGroup(
            pn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
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

        btn_requestBarang.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_requestBarang.setForeground(new java.awt.Color(255, 102, 0));
        btn_requestBarang.setText("REQUEST BARANG");
        btn_requestBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_requestBarangMouseClicked(evt);
            }
        });

        btn_dashboard.setBackground(new java.awt.Color(255, 102, 51));
        btn_dashboard.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        btn_dashboard.setText("DASHBOARD");
        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseClicked(evt);
            }
        });

        btn_DataBarang.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_DataBarang.setForeground(new java.awt.Color(255, 102, 0));
        btn_DataBarang.setText("DATA GUDANG");
        btn_DataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DataBarangMouseClicked(evt);
            }
        });

        btn_barang.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_barang.setForeground(new java.awt.Color(255, 102, 0));
        btn_barang.setText("BARANG");
        btn_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_barangMouseClicked(evt);
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

        btn_logoutGudang.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_logoutGudang.setForeground(new java.awt.Color(255, 102, 0));
        btn_logoutGudang.setText("LOGOUT");
        btn_logoutGudang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutGudangMouseClicked(evt);
            }
        });

        pn_samping.setLayer(btn_requestBarang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_dashboard, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_DataBarang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_barang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_editProfile, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pn_samping.setLayer(btn_logoutGudang, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pn_sampingLayout = new javax.swing.GroupLayout(pn_samping);
        pn_samping.setLayout(pn_sampingLayout);
        pn_sampingLayout.setHorizontalGroup(
            pn_sampingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_DataBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_barang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_requestBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addComponent(btn_editProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_logoutGudang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pn_sampingLayout.setVerticalGroup(
            pn_sampingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_sampingLayout.createSequentialGroup()
                .addComponent(btn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_DataBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_requestBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_logoutGudang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        pn_content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pn_barangKeluar.setBackground(new java.awt.Color(255, 255, 255));

        title_BarangKeluar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        title_BarangKeluar.setText("BARANG KELUAR");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        title_NoSeriKeluar.setText("NO SERI");

        cari_keluar.setText("Cari");
        cari_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cari_keluarMouseClicked(evt);
            }
        });

        title_NamaBarangKeluar.setText("NAMA BARANG :");

        dt_NamaBarangKeluar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        title_StokTersediaGudang.setText("STOK TERSEDIA :");

        dt_StokTersediaGudang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_NoSeriKeluar)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ip_NoSeriKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cari_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(title_NamaBarangKeluar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dt_NamaBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(title_StokTersediaGudang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dt_StokTersediaGudang, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_NoSeriKeluar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_NoSeriKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari_keluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(dt_NamaBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_NamaBarangKeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(title_StokTersediaGudang)
                    .addComponent(dt_StokTersediaGudang, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        title_StokKeluar.setText("JUMLAH");

        title_ExpiredKeluar.setText("EXPIRED");

        cb_ExpiredKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ExpiredKeluarActionPerformed(evt);
            }
        });

        btn_SimpanBarangKeluar.setText("SIMPAN");
        btn_SimpanBarangKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SimpanBarangKeluarMouseClicked(evt);
            }
        });

        btn_BackBarangKeluar.setText("BACK");
        btn_BackBarangKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_BackBarangKeluarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cb_ExpiredKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_ExpiredKeluar)
                    .addComponent(ip_StokKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_StokKeluar)
                    .addComponent(lb_infoBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_BackBarangKeluar)
                        .addGap(7, 7, 7)
                        .addComponent(btn_SimpanBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_ExpiredKeluar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_ExpiredKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(title_StokKeluar)
                .addGap(3, 3, 3)
                .addComponent(ip_StokKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(lb_infoBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_SimpanBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_BackBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jLayeredPane1.setLayer(title_BarangKeluar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(title_BarangKeluar))
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_BarangKeluar)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pn_barangKeluarLayout = new javax.swing.GroupLayout(pn_barangKeluar);
        pn_barangKeluar.setLayout(pn_barangKeluarLayout);
        pn_barangKeluarLayout.setHorizontalGroup(
            pn_barangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_barangKeluarLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(353, Short.MAX_VALUE))
        );
        pn_barangKeluarLayout.setVerticalGroup(
            pn_barangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_barangKeluarLayout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        pn_content.add(pn_barangKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 540));

        dashboard.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectpbo/img/gudang.png"))); // NOI18N

        pn_barangGudang.setBackground(new java.awt.Color(255, 102, 51));

        lb_bg.setForeground(new java.awt.Color(255, 255, 255));
        lb_bg.setText("BARANG GUDANG");

        d_bg.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        d_bg.setForeground(new java.awt.Color(255, 255, 255));
        d_bg.setText("56");

        javax.swing.GroupLayout pn_barangGudangLayout = new javax.swing.GroupLayout(pn_barangGudang);
        pn_barangGudang.setLayout(pn_barangGudangLayout);
        pn_barangGudangLayout.setHorizontalGroup(
            pn_barangGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_barangGudangLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pn_barangGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(d_bg)
                    .addComponent(lb_bg))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        pn_barangGudangLayout.setVerticalGroup(
            pn_barangGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_barangGudangLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lb_bg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d_bg)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pn_brg_perjalanan.setBackground(new java.awt.Color(255, 102, 51));

        lb_bdp.setForeground(new java.awt.Color(255, 255, 255));
        lb_bdp.setText("BARANG REQUEST");

        d_bdp.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        d_bdp.setForeground(new java.awt.Color(255, 255, 255));
        d_bdp.setText("56");

        javax.swing.GroupLayout pn_brg_perjalananLayout = new javax.swing.GroupLayout(pn_brg_perjalanan);
        pn_brg_perjalanan.setLayout(pn_brg_perjalananLayout);
        pn_brg_perjalananLayout.setHorizontalGroup(
            pn_brg_perjalananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_brg_perjalananLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pn_brg_perjalananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(d_bdp)
                    .addComponent(lb_bdp))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pn_brg_perjalananLayout.setVerticalGroup(
            pn_brg_perjalananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_brg_perjalananLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lb_bdp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d_bdp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_return.setBackground(new java.awt.Color(255, 102, 51));

        lb_br.setForeground(new java.awt.Color(255, 255, 255));
        lb_br.setText("BARANG RETURN");

        d_br.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        d_br.setForeground(new java.awt.Color(255, 255, 255));
        d_br.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_br.setText("76");

        javax.swing.GroupLayout pn_returnLayout = new javax.swing.GroupLayout(pn_return);
        pn_return.setLayout(pn_returnLayout);
        pn_returnLayout.setHorizontalGroup(
            pn_returnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_returnLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pn_returnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(d_br)
                    .addComponent(lb_br))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pn_returnLayout.setVerticalGroup(
            pn_returnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_returnLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lb_br)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d_br)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pn_return1.setBackground(new java.awt.Color(255, 102, 51));

        lb_be.setForeground(new java.awt.Color(255, 255, 255));
        lb_be.setText("BARANG ETALASE");

        d_be.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        d_be.setForeground(new java.awt.Color(255, 255, 255));
        d_be.setText("76");

        javax.swing.GroupLayout pn_return1Layout = new javax.swing.GroupLayout(pn_return1);
        pn_return1.setLayout(pn_return1Layout);
        pn_return1Layout.setHorizontalGroup(
            pn_return1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_return1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pn_return1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lb_be)
                    .addComponent(d_be))
                .addGap(26, 26, 26))
        );
        pn_return1Layout.setVerticalGroup(
            pn_return1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_return1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lb_be)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d_be)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dashboardLayout = new javax.swing.GroupLayout(dashboard);
        dashboard.setLayout(dashboardLayout);
        dashboardLayout.setHorizontalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(pn_barangGudang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pn_brg_perjalanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pn_return, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pn_return1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        dashboardLayout.setVerticalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pn_barangGudang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pn_brg_perjalanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pn_return, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(pn_return1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pn_content.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));

        pn_dataBarang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel2.setText("DATA BARANG GUDANG");

        tb_dataBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No Seri", "Nama Barang", "Jumlah", "Tgl Exp", "Tgl Tiba"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_dataBarang);

        tb_dataCacat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No Seri", "Nama Barang", "Jumlah", "Harga", "Tgl input", "Alasan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tb_dataCacat);

        jLabel20.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel20.setText("DATA BARANG CACAT");

        javax.swing.GroupLayout pn_dataBarangLayout = new javax.swing.GroupLayout(pn_dataBarang);
        pn_dataBarang.setLayout(pn_dataBarangLayout);
        pn_dataBarangLayout.setHorizontalGroup(
            pn_dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dataBarangLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pn_dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        pn_dataBarangLayout.setVerticalGroup(
            pn_dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dataBarangLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_content.add(pn_dataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 540));

        pn_barang.setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        title.setText("INPUT BARANG");

        title_namaBarang.setText("NAMA BARANG");

        title_NoSeri.setText("NO SERI");

        title_Stok.setText("STOK");

        title_HargaSatuan.setText("HARGA / SATUAN BELI");

        ip_NamaBarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ip_NoSeri.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ip_HargaSatuan.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_TambahBarang.setText("SIMPAN");
        btn_TambahBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_TambahBarangMouseClicked(evt);
            }
        });

        btn_Refund.setText("REFUND BARANG");
        btn_Refund.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_RefundMouseClicked(evt);
            }
        });

        btn_BarangKeluar.setText("BARANG KELUAR");
        btn_BarangKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_BarangKeluarMouseClicked(evt);
            }
        });

        btn_hapusBarang.setText("HAPUS BARANG");
        btn_hapusBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapusBarangMouseClicked(evt);
            }
        });

        title_Stok1.setText("EXPIRED");

        ip_dateExp.setBackground(new java.awt.Color(255, 255, 255));
        ip_dateExp.setDateFormatString("dd-MM-yyyy");

        cb_inputCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alat Tulis", "Snack", "Biskuit", "Permen", "Kecantikan", "Make Up", "Sabun Cuci Muka", "Makanan Ringan", "Air Mineral", "Minuman Vitamin", "Minuman Berasa", "Minuman Soda", "Susu", "Kopi", "Teh" }));

        jLabel19.setText("KATEGORI");

        javax.swing.GroupLayout pn_barangLayout = new javax.swing.GroupLayout(pn_barang);
        pn_barang.setLayout(pn_barangLayout);
        pn_barangLayout.setHorizontalGroup(
            pn_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_barangLayout.createSequentialGroup()
                .addContainerGap(242, Short.MAX_VALUE)
                .addGroup(pn_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(title)
                    .addComponent(title_namaBarang)
                    .addComponent(btn_TambahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_barangLayout.createSequentialGroup()
                        .addComponent(btn_Refund, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btn_BarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btn_hapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pn_barangLayout.createSequentialGroup()
                            .addGroup(pn_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(title_Stok)
                                .addComponent(ip_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addGroup(pn_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(ip_dateExp, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(title_Stok1)))
                        .addComponent(ip_NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(title_NoSeri)
                        .addComponent(ip_NoSeri, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(title_HargaSatuan)
                        .addComponent(ip_HargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cb_inputCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(243, Short.MAX_VALUE))
            .addGroup(pn_barangLayout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addComponent(lb_info)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_barangLayout.setVerticalGroup(
            pn_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_barangLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addGap(5, 5, 5)
                .addComponent(cb_inputCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(title_namaBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ip_NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(title_NoSeri)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ip_NoSeri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_barangLayout.createSequentialGroup()
                        .addComponent(title_Stok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ip_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_barangLayout.createSequentialGroup()
                        .addComponent(title_Stok1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ip_dateExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(title_HargaSatuan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ip_HargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_TambahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(pn_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Refund, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_BarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_info)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pn_content.add(pn_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 540));

        pn_refund.setBackground(new java.awt.Color(255, 255, 255));

        title_Refund.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        title_Refund.setText("BARANG REFUND");

        lb_NamaBarangRefund.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        title_NoSeriRefund.setText("NO SERI");

        title_StokRefund.setText("JUMLAH");

        title_HargaSatuanRefund.setText("ALASAN REFUND");

        btn_simpanRefund.setText("SIMPAN");
        btn_simpanRefund.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanRefundMouseClicked(evt);
            }
        });

        btn_backRefund.setText("BACK");
        btn_backRefund.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_backRefundMouseClicked(evt);
            }
        });

        ip_alasanRefund.setColumns(20);
        ip_alasanRefund.setRows(5);
        jScrollPaneRefund.setViewportView(ip_alasanRefund);

        title_namaBarangRefund1.setText("NAMA BARANG :");

        lb_StokTersedia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        title_StokTersedia.setText("STOK TERSEDIA :");

        cari_refund.setText("Cari");
        cari_refund.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cari_refundMouseClicked(evt);
            }
        });

        title_StokRefund1.setText("EXPIRED");

        cb_refundBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_refundBarangActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setText("KATEGORI");

        cb_categoryRefund.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_categoryRefundActionPerformed(evt);
            }
        });

        tb_refund.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "No Seri", "Nama Barang"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tb_refund);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_categoryRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_categoryRefund, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_refundLayout = new javax.swing.GroupLayout(pn_refund);
        pn_refund.setLayout(pn_refundLayout);
        pn_refundLayout.setHorizontalGroup(
            pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_refundLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_refundLayout.createSequentialGroup()
                        .addComponent(btn_backRefund)
                        .addGap(7, 7, 7)
                        .addComponent(btn_simpanRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_infoRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_refundLayout.createSequentialGroup()
                        .addGroup(pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title_Refund)
                            .addComponent(title_NoSeriRefund)
                            .addGroup(pn_refundLayout.createSequentialGroup()
                                .addComponent(ip_NoSeriRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cari_refund, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(title_StokRefund)
                            .addComponent(title_StokRefund1)
                            .addComponent(ip_StokRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_refundBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title_HargaSatuanRefund)
                            .addGroup(pn_refundLayout.createSequentialGroup()
                                .addGroup(pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(title_namaBarangRefund1)
                                    .addComponent(title_StokTersedia))
                                .addGap(18, 18, 18)
                                .addGroup(pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_StokTersedia, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_NamaBarangRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPaneRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_refundLayout.setVerticalGroup(
            pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_refundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_refundLayout.createSequentialGroup()
                        .addComponent(title_Refund)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(title_NoSeriRefund)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ip_NoSeriRefund, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cari_refund))
                        .addGap(9, 9, 9)
                        .addGroup(pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lb_NamaBarangRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title_namaBarangRefund1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lb_StokTersedia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title_StokTersedia))
                        .addGap(9, 9, 9)
                        .addComponent(title_StokRefund1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_refundBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(title_StokRefund)
                        .addGap(3, 3, 3)
                        .addComponent(ip_StokRefund, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(title_HargaSatuanRefund)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneRefund, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_refundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lb_infoRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpanRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_backRefund, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        pn_content.add(pn_refund, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 540));

        pn_hapusBarang.setBackground(new java.awt.Color(255, 255, 255));

        alasan_HapusBarang.setText("ALASAN HAPUS");

        text_HapusBarang.setColumns(20);
        text_HapusBarang.setRows(5);
        textArea_HapusBarang.setViewportView(text_HapusBarang);

        btn_backHapusBarang.setText("BACK");
        btn_backHapusBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_backHapusBarangMouseClicked(evt);
            }
        });

        btn_simpanHapusBarang.setText("SIMPAN");
        btn_simpanHapusBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanHapusBarangMouseClicked(evt);
            }
        });

        title_NoSeriHapusBarang.setText("NO SERI");

        title_HapusBarang.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        title_HapusBarang.setText("HAPUS BARANG");

        jLabel3.setText("NAMA BARANG");

        jLabel4.setText("STOK");

        btn_cariHapus.setText("CARI");
        btn_cariHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cariHapusMouseClicked(evt);
            }
        });

        dt_namaBarangHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dt_namaBarangHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        dt_stokHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dt_stokHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel5.setText("Expired");

        cb_expiredHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_expiredHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_hapusBarangLayout = new javax.swing.GroupLayout(pn_hapusBarang);
        pn_hapusBarang.setLayout(pn_hapusBarangLayout);
        pn_hapusBarangLayout.setHorizontalGroup(
            pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_hapusBarangLayout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addGroup(pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(title_NoSeriHapusBarang)
                        .addComponent(textArea_HapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(alasan_HapusBarang)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_hapusBarangLayout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addGroup(pn_hapusBarangLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(25, 25, 25)
                                    .addGroup(pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dt_namaBarangHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dt_stokHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addComponent(jLabel5)
                        .addComponent(cb_expiredHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(title_HapusBarang)
                        .addGroup(pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_infoHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, pn_hapusBarangLayout.createSequentialGroup()
                                .addComponent(btn_backHapusBarang)
                                .addGap(12, 12, 12)
                                .addComponent(btn_simpanHapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pn_hapusBarangLayout.createSequentialGroup()
                        .addComponent(ip_NoSeriHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cariHapus)))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        pn_hapusBarangLayout.setVerticalGroup(
            pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_hapusBarangLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(title_HapusBarang)
                .addGap(26, 26, 26)
                .addComponent(title_NoSeriHapusBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_NoSeriHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cariHapus))
                .addGap(18, 18, 18)
                .addGroup(pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(dt_namaBarangHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(dt_stokHapus, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_expiredHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(alasan_HapusBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textArea_HapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lb_infoHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_hapusBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpanHapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_backHapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        pn_content.add(pn_hapusBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pn_request.setBackground(new java.awt.Color(255, 255, 255));

        btn_simpanRequest.setText("SIMPAN");
        btn_simpanRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanRequestMouseClicked(evt);
            }
        });

        title_StokRequest.setText("JUMLAH");

        title_namaBarangRequest.setText("NAMA BARANG");

        title_Request.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        title_Request.setText("REQUEST BARANG");

        cb_requestBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_requestBarangActionPerformed(evt);
            }
        });

        title_namaBarangRequest1.setText("PILIH PERUSAHAAN");

        javax.swing.GroupLayout pn_requestLayout = new javax.swing.GroupLayout(pn_request);
        pn_request.setLayout(pn_requestLayout);
        pn_requestLayout.setHorizontalGroup(
            pn_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_requestLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(pn_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_simpanRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p_StokRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_StokRequest)
                    .addComponent(p_namaBarangRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_namaBarangRequest)
                    .addComponent(lb_infoRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_Request)
                    .addComponent(cb_requestBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_namaBarangRequest1))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        pn_requestLayout.setVerticalGroup(
            pn_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_requestLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(title_Request)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(title_namaBarangRequest1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_requestBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(title_namaBarangRequest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p_namaBarangRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(title_StokRequest)
                .addGap(3, 3, 3)
                .addComponent(p_StokRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_infoRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_simpanRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pn_content.add(pn_request, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pn_profilGudang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("PROFIL");

        jLabel6.setText("Nama Lengkap ");

        jLabel8.setText("ID ");

        jLabel9.setText("Tanggal Lahir");

        jLabel10.setText("NIK");

        jLabel11.setText("Alamat");

        jLabel12.setText("Email");

        jLabel13.setText("Jenis Kelamin");

        jLabel14.setText("No HP");

        jLabel15.setText("Kontak Darurat");

        jLabel16.setText("No. NPWP");

        jLabel17.setText("No. Rekening");

        btn_simpanProfil.setText("Simpan");
        btn_simpanProfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanProfilMouseClicked(evt);
            }
        });

        lb_idProfil.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        ip_alamatProfil.setColumns(20);
        ip_alamatProfil.setRows(5);
        jScrollPane2.setViewportView(ip_alamatProfil);

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

        ip_tglProfil.setBackground(new java.awt.Color(255, 255, 255));
        ip_tglProfil.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout pn_profilGudangLayout = new javax.swing.GroupLayout(pn_profilGudang);
        pn_profilGudang.setLayout(pn_profilGudangLayout);
        pn_profilGudangLayout.setHorizontalGroup(
            pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_profilGudangLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_profilGudangLayout.createSequentialGroup()
                        .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_profilGudangLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(46, 46, 46)
                                .addComponent(ip_npwpProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_profilGudangLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(ip_namaProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pn_profilGudangLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(22, 22, 22)
                                    .addComponent(ip_kontakProfil))
                                .addGroup(pn_profilGudangLayout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(66, 66, 66)
                                    .addComponent(ip_hpProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pn_profilGudangLayout.createSequentialGroup()
                                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_profilGudangLayout.createSequentialGroup()
                                        .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))
                                        .addGap(31, 31, 31))
                                    .addGroup(pn_profilGudangLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(32, 32, 32)))
                                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_profilGudangLayout.createSequentialGroup()
                                        .addComponent(rb_perempuan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rb_laki))
                                    .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(ip_emailProfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(ip_tglProfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                        .addComponent(ip_nikProfil, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(lb_idProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pn_profilGudangLayout.createSequentialGroup()
                        .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_profilGudangLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(30, 30, 30)
                                .addComponent(ip_rekeningProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lb_infoProfil, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_simpanProfil)
                        .addGap(75, 75, 75))))
        );
        pn_profilGudangLayout.setVerticalGroup(
            pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_profilGudangLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_idProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ip_namaProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ip_tglProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_nikProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(ip_emailProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(rb_perempuan)
                    .addComponent(rb_laki))
                .addGap(12, 12, 12)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(ip_hpProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip_kontakProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(ip_npwpProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(ip_rekeningProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profilGudangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_infoProfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_profilGudangLayout.createSequentialGroup()
                        .addComponent(btn_simpanProfil)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pn_content.add(pn_profilGudang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));

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
                .addContainerGap(73, Short.MAX_VALUE))
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
        pn_dataBarang.setVisible(false);
        pn_barang.setVisible(false);
        pn_request.setVisible(false);
        pn_refund.setVisible(false);
      
        //back white fore orange
        btn_DataBarang.setForeground(new java.awt.Color(255,102,0));
        btn_DataBarang.setBackground(new java.awt.Color(255,255,255));
        
        btn_barang.setForeground(new java.awt.Color(255,102,0));
        btn_barang.setBackground(new java.awt.Color(255,255,255));
        
        btn_editProfile.setForeground(new java.awt.Color(255,102,0));
        btn_editProfile.setBackground(new java.awt.Color(255,255,255));
        
        btn_requestBarang.setForeground(new java.awt.Color(255,102,0));
        btn_requestBarang.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_dashboard.setBackground(new java.awt.Color(255,102,0));
        btn_dashboard.setForeground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_btn_dashboardMouseClicked

    private void btn_DataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataBarangMouseClicked
        dashboard.setVisible(false);
        pn_dataBarang.setVisible(true);
        pn_barang.setVisible(false);
        pn_request.setVisible(false);
        pn_hapusBarang.setVisible(false);
        pn_profilGudang.setVisible(false);
        
        //back white fore orange
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_barang.setForeground(new java.awt.Color(255,102,0));
        btn_barang.setBackground(new java.awt.Color(255,255,255));
        
        btn_editProfile.setForeground(new java.awt.Color(255,102,0));
        btn_editProfile.setBackground(new java.awt.Color(255,255,255));
        
        btn_requestBarang.setForeground(new java.awt.Color(255,102,0));
        btn_requestBarang.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_DataBarang.setBackground(new java.awt.Color(255,102,0));
        btn_DataBarang.setForeground(new java.awt.Color(255,255,255));
        
        model = (DefaultTableModel) tb_dataBarang.getModel();
        LoadData();
        tampilTableCacat (tb_dataCacat);
    }//GEN-LAST:event_btn_DataBarangMouseClicked

    private void btn_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_barangMouseClicked
        dashboard.setVisible(false);
        pn_dataBarang.setVisible(false);
        pn_barang.setVisible(true);
        pn_request.setVisible(false);
        pn_barangKeluar.setVisible(false);
        pn_profilGudang.setVisible(false);
        
        //back white fore orange
        btn_DataBarang.setForeground(new java.awt.Color(255,102,0));
        btn_DataBarang.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_editProfile.setForeground(new java.awt.Color(255,102,0));
        btn_editProfile.setBackground(new java.awt.Color(255,255,255));
        
        btn_requestBarang.setForeground(new java.awt.Color(255,102,0));
        btn_requestBarang.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_barang.setBackground(new java.awt.Color(255,102,0));
        btn_barang.setForeground(new java.awt.Color(255,255,255));
        
        lb_info.setText("");
        bersihTambah();
    }//GEN-LAST:event_btn_barangMouseClicked

    private void btn_RefundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RefundMouseClicked
        pn_barang.setVisible(false);
        pn_refund.setVisible(true);
        
        btn_barang.requestFocus();
        bersihRefund();
        lb_info.setText("");
        
        updateComboBoxCategory(cb_categoryRefund);
    }//GEN-LAST:event_btn_RefundMouseClicked

    private void btn_backRefundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backRefundMouseClicked
        pn_refund.setVisible(false);
        pn_barang.setVisible(true);
        
        btn_barang.requestFocus();
        bersihTambah();
    }//GEN-LAST:event_btn_backRefundMouseClicked

    private void btn_BarangKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_BarangKeluarMouseClicked
        pn_barang.setVisible(false);
        pn_barangKeluar.setVisible(true);
        
        btn_barang.requestFocus();
        bersihKeluar();
    }//GEN-LAST:event_btn_BarangKeluarMouseClicked

    private void btn_hapusBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusBarangMouseClicked
        dashboard.setVisible(false);
        pn_dataBarang.setVisible(false);
        pn_barang.setVisible(false);
        pn_refund.setVisible(false);
        pn_hapusBarang.setVisible(true);                   
        pn_request.setVisible(false);
        pn_barangKeluar.setVisible(false);
        pn_profilGudang.setVisible(false);
        
        bersihHapus();
        btn_barang.requestFocus();
    }//GEN-LAST:event_btn_hapusBarangMouseClicked

    private void btn_backHapusBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backHapusBarangMouseClicked
        pn_hapusBarang.setVisible(false);
        pn_barang.setVisible(true);
        
        btn_barang.requestFocus();
        bersihHapus();
    }//GEN-LAST:event_btn_backHapusBarangMouseClicked

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
    
    private void btn_requestBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_requestBarangMouseClicked
        dashboard.setVisible(false);
        pn_dataBarang.setVisible(false);
        pn_barang.setVisible(false);
        pn_refund.setVisible(false);
        pn_hapusBarang.setVisible(false);
        pn_barangKeluar.setVisible(false);
        pn_profilGudang.setVisible(false);
        pn_profilGudang.setVisible(false);
                     
        pn_request.setVisible(true);
        
        //back white fore orange
        btn_DataBarang.setForeground(new java.awt.Color(255,102,0));
        btn_DataBarang.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_editProfile.setForeground(new java.awt.Color(255,102,0));
        btn_editProfile.setBackground(new java.awt.Color(255,255,255));
        
        btn_barang.setForeground(new java.awt.Color(255,102,0));
        btn_barang.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_requestBarang.setBackground(new java.awt.Color(255,102,0));
        btn_requestBarang.setForeground(new java.awt.Color(255,255,255));
        
        updateComboBoxRequest(cb_requestBarang);
    }//GEN-LAST:event_btn_requestBarangMouseClicked

    private void btn_TambahBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TambahBarangMouseClicked

        String namaBarang = ip_NamaBarang.getText();
        String noSeri = ip_NoSeri.getText();
        int stok = (int) ip_Stok.getValue();
        String hargaSatuanText = ip_HargaSatuan.getText();
        Date dateExp = ip_dateExp.getDate();
        
        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(dateExp);
        String category = (String) cb_inputCategory.getSelectedItem();
        
        try {
            //Mengonversi Text to Int;
            int hargaSatuan = Integer.parseInt(hargaSatuanText);
            
            //menghitung hargaJual
            float hargaUntungFloat = hargaSatuan*0.3f;
            
            //konversi float to int;
            int hargaUntung = Math.round(hargaUntungFloat);
            int hargaJual = hargaUntung + hargaSatuan;
            
            // Membuat koneksi ke database
            Connection connection = db_connect.connect();

            // Query untuk menambahkan data
            String query = "INSERT INTO data_barang (no_seri, nama_barang, stok_gudang, harga_satuan, harga_jual, status, expired, category) VALUES (?,?,?,?,?,?,?,?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, noSeri);
                statement.setString(2, namaBarang);
                statement.setInt(3, stok);
                statement.setInt(4, hargaSatuan);
                statement.setInt(5, hargaJual);
                statement.setString(6, "Gudang");
                statement.setString(7, dateString);
                statement.setString(8, category);

                // Eksekusi pernyataan insert
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    
                    bersihTambah();
                    lb_info.setForeground(Color.green);
                    lb_info.setText("Data berhasil ditambahkan");
                    hideLabelAfterDelay(lb_info, 3000);
                    
                } else {
                    lb_info.setForeground(Color.red);
                    lb_info.setText("Gagal menambahkan data");
                }
            }
        } catch (NumberFormatException | SQLException e) {
            lb_info.setText("Gagal menambahkan data: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_TambahBarangMouseClicked

    private void cari_refundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cari_refundMouseClicked
        String noSeri = ip_NoSeriRefund.getText();
        
        try{
            // membuat koneksi ke database
            Connection connection = db_connect.connect();
            
            //melakukan query untuk memeriksa login
            String query = "SELECT nama_barang, stok_gudang FROM data_barang WHERE no_seri = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, noSeri);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String namaBarang = resultSet.getString("nama_barang");
                        int stok_gudang = resultSet.getInt("stok_gudang");
                        
                        lb_NamaBarangRefund.setForeground(Color.black);
                        lb_NamaBarangRefund.setText(namaBarang);
                        lb_StokTersedia.setText(String.valueOf(stok_gudang));
                        
                        updateComboBox(cb_refundBarang,lb_NamaBarangRefund);
                    }else{
                        lb_NamaBarangRefund.setForeground(Color.red);
                        lb_NamaBarangRefund.setText("Barang Tidak Ada");
                        lb_StokTersedia.setText("");
                        
                        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
                        cb_refundBarang.setModel(model2);
                        
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_cari_refundMouseClicked

    private void cb_refundBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_refundBarangActionPerformed
        displaySelectedExpired(cb_refundBarang,lb_StokTersedia);
    }//GEN-LAST:event_cb_refundBarangActionPerformed

    private void btn_simpanRefundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanRefundMouseClicked
               
        int IDbarang = getId_barang();
        int stokRefund = (int) ip_StokRefund.getValue();
        int stokGudang = Integer.parseInt(lb_StokTersedia.getText());
        String selectedExpired = (String) cb_refundBarang.getSelectedItem();
        
        String alasanRefund = ip_alasanRefund.getText();
        
        int hasil = stokGudang - stokRefund;
        
        Connection connection = db_connect.connect();
        
        try{
            String updateQuery = "UPDATE data_barang SET stok_gudang = ? WHERE id_barang = ?";
            try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                statement.setInt(1, hasil);
                statement.setInt(2, IDbarang);
                statement.executeUpdate();
                                
            } catch (SQLException e){
                e.printStackTrace();
                lb_infoRefund.setText(e.getMessage());
            }
            
            String insertQuery = "INSERT INTO barang_refund (id_barang,stok_refund,alasan_refund,status_refund,expired) VALUE (?,?,?,?,?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)){
                insertStatement.setInt(1, IDbarang);
                insertStatement.setInt(2, stokRefund);
                insertStatement.setString(3, alasanRefund);
                insertStatement.setInt(4, 1);
                insertStatement.setString(5, selectedExpired);
                
                int rowStatement = insertStatement.executeUpdate();
                if (rowStatement > 0){
                    bersihRefund();
                    lb_infoRefund.setForeground(Color.green);
                    lb_infoRefund.setText("Data Berhasil Refund !!!");
                    hideLabelAfterDelay(lb_infoRefund, 3000);
                }else{
                    lb_infoRefund.setForeground(Color.red);
                    lb_infoRefund.setText("Data Tidak Masuk !!!");
                }
            
            } catch (SQLException e){
                e.printStackTrace();
                lb_infoRefund.setText(e.getMessage());
            }
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
            lb_infoRefund.setText(e.getMessage());
        }
    }//GEN-LAST:event_btn_simpanRefundMouseClicked

    private void btn_SimpanBarangKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SimpanBarangKeluarMouseClicked
        int IDbarang = getId_barang();
        
        String namaBarang = dt_NamaBarangKeluar.getText();
        
        String selectExp = (String) cb_ExpiredKeluar.getSelectedItem();
                
         
                
        Connection connection = db_connect.connect();
        try{
            String query = "SELECT harga_satuan, harga_jual FROM data_barang WHERE id_barang = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, IDbarang);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        long hargaSatuan = resultSet.getInt("harga_satuan");
                        long hargaJual = resultSet.getInt("harga_jual");
                        
                        setHarga_satuan(hargaSatuan);
                        setHarga_jual(hargaJual);
                        
                    }else{
                        lb_infoBarangKeluar.setForeground(Color.red);
                        lb_infoBarangKeluar.setText("Barang Tidak Ada");
                        
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            
            
            int stokKeluar = (int) ip_StokKeluar.getValue();
            int stokGudang = Integer.parseInt(dt_StokTersediaGudang.getText());
            int hasil = stokGudang - stokKeluar;    
            
            String updateQuery = "UPDATE data_barang SET stok_gudang = ?, stok_etalase = ? WHERE id_barang = ?";
            try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                statement.setInt(1, hasil);
                statement.setInt(2,stokKeluar);
                statement.setInt(3, IDbarang);
                statement.executeUpdate();
                                
            } catch (SQLException e){
                e.printStackTrace();
                lb_infoBarangKeluar.setText(e.getMessage());
            }
            
            String noSeri = ip_NoSeriKeluar.getText();
            long HargaSatuan = getHarga_satuan();
            long HargaJual = getHarga_jual();
            
            String insertQuery = "INSERT INTO barang_etalase (id_barang,nama_barang,stok_etalase,harga_gudang,harga_jual,expired, no_seri, stok_jual) VALUE (?, ?,?,?,?,?,? ,?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)){
                insertStatement.setInt(1, IDbarang);
                insertStatement.setString(2, namaBarang);
                insertStatement.setInt(3, stokKeluar);
                insertStatement.setLong(4, HargaSatuan);
                insertStatement.setLong(5, HargaJual);
                insertStatement.setString(6, selectExp);
                insertStatement.setString(7, noSeri);
                insertStatement.setInt(8, 0);
                
                int rowStatement = insertStatement.executeUpdate();
                if (rowStatement > 0){
                    bersihKeluar();
                    lb_infoBarangKeluar.setForeground(Color.green);
                    lb_infoBarangKeluar.setText("Data Berhasil Etalase !!!");
                    hideLabelAfterDelay(lb_infoBarangKeluar, 3000);
                }else{
                    lb_infoBarangKeluar.setForeground(Color.red);
                    lb_infoBarangKeluar.setText("Data Tidak Masuk !!!");
                }
            
            } catch (SQLException e){
                e.printStackTrace();
                lb_infoBarangKeluar.setText(e.getMessage());
            }
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
            lb_infoBarangKeluar.setText(e.getMessage());
        }
    }//GEN-LAST:event_btn_SimpanBarangKeluarMouseClicked

    private void btn_BackBarangKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_BackBarangKeluarMouseClicked
        pn_barangKeluar.setVisible(false);
        pn_barang.setVisible(true);
        
        btn_barang.requestFocus();
        bersihTambah();
        bersihKeluar();
    }//GEN-LAST:event_btn_BackBarangKeluarMouseClicked

    private void cari_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cari_keluarMouseClicked
        String noSeri = ip_NoSeriKeluar.getText();
        
        try{
            // membuat koneksi ke database
            Connection connection = db_connect.connect();
            
            //melakukan query untuk memeriksa login
            String query = "SELECT nama_barang, stok_gudang FROM data_barang WHERE no_seri = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, noSeri);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String namaBarang = resultSet.getString("nama_barang");
                        int stok_gudang = resultSet.getInt("stok_gudang");
                        
                        dt_NamaBarangKeluar.setForeground(Color.black);
                        dt_NamaBarangKeluar.setText(namaBarang);
                        dt_StokTersediaGudang.setText(String.valueOf(stok_gudang));
                        updateComboBox(cb_ExpiredKeluar,dt_NamaBarangKeluar);
                    }else{
                        dt_NamaBarangKeluar.setForeground(Color.red);
                        dt_NamaBarangKeluar.setText("Barang Tidak Ada");
                        dt_StokTersediaGudang.setText("");
                        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
                        cb_ExpiredKeluar.setModel(model2);
                        
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_cari_keluarMouseClicked

    private void cb_ExpiredKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ExpiredKeluarActionPerformed
        displaySelectedExpired(cb_ExpiredKeluar,dt_StokTersediaGudang);
    }//GEN-LAST:event_cb_ExpiredKeluarActionPerformed

    private void btn_cariHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cariHapusMouseClicked
        String noSeri = ip_NoSeriHapus.getText();
        
        try{
            // membuat koneksi ke database
            Connection connection = db_connect.connect();
            
            //melakukan query untuk memeriksa login
            String query = "SELECT nama_barang, stok_gudang FROM data_barang WHERE no_seri = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, noSeri);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String namaBarang = resultSet.getString("nama_barang");
                        int stok_gudang = resultSet.getInt("stok_gudang");
                        
                        dt_namaBarangHapus.setForeground(Color.black);
                        dt_namaBarangHapus.setText(namaBarang);
                        dt_stokHapus.setText(String.valueOf(stok_gudang));
                        updateComboBox(cb_expiredHapus,dt_namaBarangHapus);
                    }else{
                        dt_namaBarangHapus.setForeground(Color.red);
                        dt_namaBarangHapus.setText("Barang Tidak Ada");
                        
                        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
                        cb_expiredHapus.setModel(model2);
                        dt_stokHapus.setText("");
                        
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_cariHapusMouseClicked

    private void cb_expiredHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_expiredHapusActionPerformed
        displaySelectedExpired(cb_expiredHapus,dt_stokHapus);
    }//GEN-LAST:event_cb_expiredHapusActionPerformed

    private void btn_simpanHapusBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanHapusBarangMouseClicked
        int IDbarang = getId_barang();
        String NoSeri = ip_NoSeriHapus.getText();
        String namaBarang = dt_namaBarangHapus.getText();
        String selectExp = (String) cb_expiredHapus.getSelectedItem();
        String alasanHapus = text_HapusBarang.getText();
                                
        Connection connection = db_connect.connect();
        try{
                        
                       
            String insertQuery = "INSERT INTO barang_hapus (no_seri,id_barang,nama_barang,alasan_hapus,expired) VALUE (?,?,?,?,?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)){
                insertStatement.setString(1, NoSeri);
                insertStatement.setInt(2, IDbarang);
                insertStatement.setString(3, namaBarang);
                insertStatement.setString(4, alasanHapus);
                insertStatement.setString(5, selectExp);
                
                int rowStatement = insertStatement.executeUpdate();
                if (rowStatement > 0){
                    bersihHapus();
                    lb_infoHapus.setForeground(Color.green);
                    lb_infoHapus.setText("Data Gudang diHapus !!!");
                    hideLabelAfterDelay(lb_infoHapus, 3000);
                }else{
                    lb_infoHapus.setForeground(Color.red);
                    lb_infoHapus.setText("Data Gagal diHapus !!!");
                }
                        
            } catch (SQLException e){
                e.printStackTrace();
                lb_infoHapus.setText(e.getMessage());
            }
            
            String updateQuery = "DELETE FROM data_barang WHERE id_barang = ?";
            try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                statement.setInt(1, IDbarang);
                statement.executeUpdate();
                                
            } catch (SQLException e){
                e.printStackTrace();
                lb_infoHapus.setText(e.getMessage());
            }
            
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
            lb_infoHapus.setText(e.getMessage());
        }
    }//GEN-LAST:event_btn_simpanHapusBarangMouseClicked

    private void btn_simpanRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanRequestMouseClicked
        String namaPerusahaan = (String) cb_requestBarang.getSelectedItem();
        String namaKaryawan = lb_nama.getText();
        String namaBarang = p_namaBarangRequest.getText();
        int stokRequest = (int)p_StokRequest.getValue();
        
        if (namaBarang.isEmpty()){
            lb_infoRequest.setForeground(Color.red);
            lb_infoRequest.setText("NAMA BARANG TIDAK BOLEH KOSONG !!!");
            hideLabelAfterDelay(lb_infoRequest, 3000);
            return;
        }
        if (stokRequest == 0 || stokRequest < 0){
            lb_infoRequest.setForeground(Color.red);
            lb_infoRequest.setText("VALUE TIDAK VALID !!!");
            hideLabelAfterDelay(lb_infoRequest, 3000);
            return;
        }
        
        Connection connect = db_connect.connect();
        try{
            String query = "INSERT INTO request_barang (nama_karyawan,nama_barang,stok_request, nama_perusahaan) VALUES (?,?,?,?)";
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setString(1, namaKaryawan);
            statement.setString(2, namaBarang);
            statement.setInt(3, stokRequest);
            statement.setString(4, namaPerusahaan);
            int ResultSet = statement.executeUpdate();
            if (ResultSet > 0){
                bersihRequest();
                lb_infoRequest.setForeground(Color.green);
                lb_infoRequest.setText("Request Barang Berhasil !!!");
                hideLabelAfterDelay(lb_infoRequest, 3000);
            }else{
                lb_infoRequest.setForeground(Color.red);
                lb_infoRequest.setText("Gagal Request Barang !!!");
                hideLabelAfterDelay(lb_infoRequest, 3000);
            }
            statement.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_simpanRequestMouseClicked

    private void btn_editProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editProfileMouseClicked
        dashboard.setVisible(false);
        pn_dataBarang.setVisible(false);
        pn_barang.setVisible(false);
        pn_refund.setVisible(false);
        pn_hapusBarang.setVisible(false);
        pn_barangKeluar.setVisible(false);
        pn_request.setVisible(false);
        
        pn_profilGudang.setVisible(true);
                            
        //back white fore orange
        btn_DataBarang.setForeground(new java.awt.Color(255,102,0));
        btn_DataBarang.setBackground(new java.awt.Color(255,255,255));
        
        btn_dashboard.setForeground(new java.awt.Color(255,102,0));
        btn_dashboard.setBackground(new java.awt.Color(255,255,255));
        
        btn_requestBarang.setForeground(new java.awt.Color(255,102,0));
        btn_requestBarang.setBackground(new java.awt.Color(255,255,255));
        
        btn_barang.setForeground(new java.awt.Color(255,102,0));
        btn_barang.setBackground(new java.awt.Color(255,255,255));
        
        //back orange fore white
        btn_editProfile.setBackground(new java.awt.Color(255,102,0));
        btn_editProfile.setForeground(new java.awt.Color(255,255,255));
        
        loadDataProfil();
    }//GEN-LAST:event_btn_editProfileMouseClicked

    private void rb_perempuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_perempuanMouseClicked
        rb_laki.setSelected(false);
    }//GEN-LAST:event_rb_perempuanMouseClicked

    private void rb_lakiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_lakiMouseClicked
        rb_perempuan.setSelected(false);
    }//GEN-LAST:event_rb_lakiMouseClicked

    private void btn_logoutGudangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutGudangMouseClicked
        projectpbo.login test = new projectpbo.login();
        test.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_logoutGudangMouseClicked

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

    private void cb_categoryRefundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_categoryRefundActionPerformed
        displaySelectedCategory(cb_categoryRefund,tb_refund);
    }//GEN-LAST:event_cb_categoryRefundActionPerformed

    private void cb_requestBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_requestBarangActionPerformed
        
    }//GEN-LAST:event_cb_requestBarangActionPerformed

    public void updateComboBoxCategory(JComboBox<String> comboBox) {
        try {
            Connection connect = db_connect.connect();

            String query = "SELECT category FROM data_barang GROUP BY category ORDER BY category ASC";
            try (PreparedStatement statement = connect.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Clear existing items in the combo box
                comboBox.removeAllItems();

                // Add items to the combo box
                while (resultSet.next()) {
                    String category = resultSet.getString("category");
                    comboBox.addItem(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateComboBox(JComboBox comboBox, JLabel namabarang) {
        // Get the name of the item (nama_barang) from a JLabel named lb_NamaBarangRefund
        String nama_barang = namabarang.getText();

        try {
            // Establish a connection to the database using a custom class db_connect
            Connection connection = db_connect.connect();

            // Execute a query to retrieve expiration dates for the given item name
            String query = "SELECT expired FROM data_barang WHERE nama_barang = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                // Set the item name parameter in the prepared statement
                statement.setString(1, nama_barang);

                // Execute the query and retrieve the result set
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Create a DefaultComboBoxModel to store the expiration dates
                    DefaultComboBoxModel<String> modelCbBox = new DefaultComboBoxModel<>();
                    
                    modelCbBox.addElement("PILIH EXP");
                    // Iterate through the result set and add each expiration date to the model
                    while (resultSet.next()) {
                        String expired = resultSet.getString("expired");
                                                                                               
                        modelCbBox.addElement(expired);                
                    }
                 
                    // Set the model with expiration dates to the JComboBox
                    comboBox.setModel(modelCbBox);

                    // Close the result set, statement, and database connection
                    resultSet.close();
                    statement.close();
                    connection.close();
                }
            }
        } catch (SQLException e) {
            // Handle SQLException (print stack trace in this case)
            e.printStackTrace();
        } 
    }
    
    public void updateComboBoxRequest(JComboBox<String> comboBox) {
        try {
            Connection connect = db_connect.connect();

            String query = "SELECT nama_perusahaan FROM list_perusahaan ORDER BY nama_perusahaan ASC";
            try (PreparedStatement statement = connect.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Clear existing items in the combo box
                comboBox.removeAllItems();

                // Add items to the combo box
                while (resultSet.next()) {
                    String category = resultSet.getString("nama_perusahaan");
                    comboBox.addItem(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void displaySelectedExpired(JComboBox<String> comboBox, JLabel stok) {
        Connection connection = db_connect.connect();
        try{
            String selectedExpired = (String) comboBox.getSelectedItem();
            
            String query = "SELECT id_barang, stok_gudang FROM data_barang WHERE expired = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedExpired);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int stokGudang = resultSet.getInt("stok_gudang");
                int idBarang = resultSet.getInt("id_barang");
                
                stok.setText(String.valueOf(stokGudang));
                setId_barang(idBarang);
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void displaySelectedCategory (JComboBox<String> comboBox, JTable table){
        DefaultTableModel model2 = (DefaultTableModel) table.getModel();
        model2.setRowCount(0); // Clear existing rows in the table

        Connection connection = db_connect.connect();
        try {
            String selectedCategory= (String) comboBox.getSelectedItem();

            String query = "SELECT no_seri, nama_barang FROM data_barang WHERE category = ? GROUP BY no_seri ORDER BY nama_barang ASC";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, selectedCategory);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String noSeri = resultSet.getString("no_seri");
                        String NamaBarang  = resultSet.getString("nama_barang");

                        // Add data to the table model
                        Object[] row = { noSeri, NamaBarang };
                        model2.addRow(row);
                    }
                }
               }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
        public void tampilTableCacat (JTable table){
        DefaultTableModel model2 = (DefaultTableModel) table.getModel();
        model2.setRowCount(0); // Clear existing rows in the table

        Connection connection = db_connect.connect();
        try {
            String query = "SELECT no_seri,nama_barang,harga_unit,stok,alasan FROM barang_cacat ORDER BY nama_barang ASC";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String noSeri = resultSet.getString("no_seri");
                        String namaBarang  = resultSet.getString("nama_barang");
                        int hargaUnit  = resultSet.getInt("harga_unit");
                        int stok = resultSet.getInt("stok");
                        String alasan = resultSet.getString("alasan");

                        // Add data to the table model
                        Object[] row = { noSeri, namaBarang ,hargaUnit,stok,alasan};
                        model2.addRow(row);
                    }
                }
               }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alasan_HapusBarang;
    private javax.swing.JButton btn_BackBarangKeluar;
    private javax.swing.JButton btn_BarangKeluar;
    private javax.swing.JButton btn_DataBarang;
    private javax.swing.JButton btn_Refund;
    private javax.swing.JButton btn_SimpanBarangKeluar;
    private javax.swing.JButton btn_TambahBarang;
    private javax.swing.JButton btn_backHapusBarang;
    private javax.swing.JButton btn_backRefund;
    private javax.swing.JButton btn_barang;
    private javax.swing.JButton btn_cariHapus;
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_editProfile;
    private javax.swing.JButton btn_hapusBarang;
    private javax.swing.JButton btn_logoutGudang;
    private javax.swing.JButton btn_requestBarang;
    private javax.swing.JButton btn_simpanHapusBarang;
    private javax.swing.JButton btn_simpanProfil;
    private javax.swing.JButton btn_simpanRefund;
    private javax.swing.JButton btn_simpanRequest;
    private javax.swing.JButton cari_keluar;
    private javax.swing.JButton cari_refund;
    private javax.swing.JComboBox<String> cb_ExpiredKeluar;
    private javax.swing.JComboBox<String> cb_categoryRefund;
    private javax.swing.JComboBox<String> cb_expiredHapus;
    private javax.swing.JComboBox<String> cb_inputCategory;
    private javax.swing.JComboBox<String> cb_refundBarang;
    private javax.swing.JComboBox<String> cb_requestBarang;
    private javax.swing.JLabel d_bdp;
    private javax.swing.JLabel d_be;
    private javax.swing.JLabel d_bg;
    private javax.swing.JLabel d_br;
    private javax.swing.JPanel dashboard;
    private javax.swing.JLabel dt_NamaBarangKeluar;
    private javax.swing.JLabel dt_StokTersediaGudang;
    private javax.swing.JLabel dt_namaBarangHapus;
    private javax.swing.JLabel dt_stokHapus;
    private javax.swing.JLabel gb_dashboard;
    private javax.swing.JTextField ip_HargaSatuan;
    private javax.swing.JTextField ip_NamaBarang;
    private javax.swing.JTextField ip_NoSeri;
    private javax.swing.JTextField ip_NoSeriHapus;
    private javax.swing.JTextField ip_NoSeriKeluar;
    private javax.swing.JTextField ip_NoSeriRefund;
    private javax.swing.JSpinner ip_Stok;
    private javax.swing.JSpinner ip_StokKeluar;
    private javax.swing.JSpinner ip_StokRefund;
    private javax.swing.JTextArea ip_alamatProfil;
    private javax.swing.JTextArea ip_alasanRefund;
    private com.toedter.calendar.JDateChooser ip_dateExp;
    private javax.swing.JTextField ip_emailProfil;
    private javax.swing.JTextField ip_hpProfil;
    private javax.swing.JTextField ip_kontakProfil;
    private javax.swing.JTextField ip_namaProfil;
    private javax.swing.JTextField ip_nikProfil;
    private javax.swing.JTextField ip_npwpProfil;
    private javax.swing.JTextField ip_rekeningProfil;
    private com.toedter.calendar.JDateChooser ip_tglProfil;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPaneRefund;
    private javax.swing.JLabel lb_NamaBarangRefund;
    private javax.swing.JLabel lb_StokTersedia;
    private javax.swing.JLabel lb_bdp;
    private javax.swing.JLabel lb_be;
    private javax.swing.JLabel lb_bg;
    private javax.swing.JLabel lb_br;
    private javax.swing.JLabel lb_date;
    private javax.swing.JLabel lb_hello;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_idKaryawan;
    private javax.swing.JLabel lb_idProfil;
    private javax.swing.JLabel lb_info;
    private javax.swing.JLabel lb_infoBarangKeluar;
    private javax.swing.JLabel lb_infoHapus;
    private javax.swing.JLabel lb_infoProfil;
    private javax.swing.JLabel lb_infoRefund;
    private javax.swing.JLabel lb_infoRequest;
    private javax.swing.JLabel lb_jabatan;
    private javax.swing.JLabel lb_nama;
    private javax.swing.JLabel lb_role;
    private javax.swing.JLabel lb_tanggal;
    private javax.swing.JLabel lb_welcome1;
    private javax.swing.JSpinner p_StokRequest;
    private javax.swing.JTextField p_namaBarangRequest;
    private javax.swing.JPanel pn_1;
    private javax.swing.JPanel pn_2;
    private javax.swing.JPanel pn_3;
    private javax.swing.JPanel pn_4;
    private javax.swing.JLayeredPane pn_atas;
    private javax.swing.JPanel pn_barang;
    private javax.swing.JPanel pn_barangGudang;
    private javax.swing.JPanel pn_barangKeluar;
    private javax.swing.JPanel pn_brg_perjalanan;
    private javax.swing.JLayeredPane pn_content;
    private javax.swing.JPanel pn_dataBarang;
    private javax.swing.JPanel pn_hapusBarang;
    private javax.swing.JPanel pn_profilGudang;
    private javax.swing.JPanel pn_refund;
    private javax.swing.JPanel pn_request;
    private javax.swing.JPanel pn_return;
    private javax.swing.JPanel pn_return1;
    private javax.swing.JLayeredPane pn_samping;
    private javax.swing.JPanel pn_utama;
    private javax.swing.JRadioButton rb_laki;
    private javax.swing.JRadioButton rb_perempuan;
    private javax.swing.JTable tb_dataBarang;
    private javax.swing.JTable tb_dataCacat;
    private javax.swing.JTable tb_refund;
    private javax.swing.JScrollPane textArea_HapusBarang;
    private javax.swing.JTextArea text_HapusBarang;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title_BarangKeluar;
    private javax.swing.JLabel title_ExpiredKeluar;
    private javax.swing.JLabel title_HapusBarang;
    private javax.swing.JLabel title_HargaSatuan;
    private javax.swing.JLabel title_HargaSatuanRefund;
    private javax.swing.JLabel title_NamaBarangKeluar;
    private javax.swing.JLabel title_NoSeri;
    private javax.swing.JLabel title_NoSeriHapusBarang;
    private javax.swing.JLabel title_NoSeriKeluar;
    private javax.swing.JLabel title_NoSeriRefund;
    private javax.swing.JLabel title_Refund;
    private javax.swing.JLabel title_Request;
    private javax.swing.JLabel title_Stok;
    private javax.swing.JLabel title_Stok1;
    private javax.swing.JLabel title_StokKeluar;
    private javax.swing.JLabel title_StokRefund;
    private javax.swing.JLabel title_StokRefund1;
    private javax.swing.JLabel title_StokRequest;
    private javax.swing.JLabel title_StokTersedia;
    private javax.swing.JLabel title_StokTersediaGudang;
    private javax.swing.JLabel title_namaBarang;
    private javax.swing.JLabel title_namaBarangRefund1;
    private javax.swing.JLabel title_namaBarangRequest;
    private javax.swing.JLabel title_namaBarangRequest1;
    // End of variables declaration//GEN-END:variables
}
