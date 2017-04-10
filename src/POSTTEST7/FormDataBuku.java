/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POSTTEST7;

import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Simbolon-PC
 */
public class FormDataBuku extends javax.swing.JFrame {

    /**
     * Creates new form FormDataBuku
     */
    public FormDataBuku() {
        initComponents();
    }
        private DefaultTableModel model;
        private final Connection con = koneksi.getConnection();
        private Statement stt; 
        private ResultSet rss;
        private PreparedStatement pst;
        
    private void InitTable(){ //inisiasi tabel atau membuat tabel secara manual
        model = new DefaultTableModel(); //instansiasi class model dari class DefaultTableModel
        model.addColumn("ID BUKU");
        model.addColumn("JUDUL"); //membuat kolom JUDUL di instance class model
        model.addColumn("PENULIS");//membuat kolom PENULIS di instance class model
        model.addColumn("HARGA");//membuat kolom HARGA di instance class model
        jTable1.setModel(model); //mengeset instance of class model ke jTable1
    }
    
    // method untuk memvalidasi data dengan parameter judul dan penulis dengan tipe data String
    private boolean validasi(String judul, String penulis){ //method untuk validasi data agar tidak boleh sama
        try {
            stt = con.createStatement(); //untuk konek ke database
            String sql = "SELECT * FROM buku WHERE judul='"+judul+"' and penulis='"+penulis+"'"; //mendeklarasikan variabel sql dengan query untuk menampilkan data sesuai kondisi judul dan penulis yang ditentukan
            rss = stt.executeQuery(sql); //untuk mengeksekusi query
            //kondisi untuk menampilkan hasil dan nilai pengembaliannya
            if(rss.next()) 
                return true; 
            else 
                return false;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            return false;
        }
    }

    
    private void TampilData(){ //fungsi untuk menampilkan data
        try{
            String sql = "SELECT * FROM buku"; //mendeklarasikan variabel sql dengan query untuk menampilkan semua data pada tabel buku
            stt = con.createStatement();//pembuatan statement
            rss = stt.executeQuery(sql);//eksekusi query
            while(rss.next()){//perulangan untuk menampilkan data
                //menampilkan data sesuai array :
                Object[] o = new Object[4]; //membuat objek baru yaitu o dengan jumlah array 4 
                o[0] = rss.getString("id");// objek data pada larik ke-0 yaitu mengambil nilai dari stt berupa id
                o[1] = rss.getString("judul");//objek data pada larik ke-1 yaitu mengambil nilai dari stt berupa judul
                o[2] = rss.getString("penulis");//objek o pada larik ke-2 yaitu mengambil nilai dari stt berupa penulis
                o[3] = rss.getInt("harga");//objek o pada larik ke-3 yaitu mengambil nilai dari stt berupa harga
                model.addRow(o);//menambah baris pada model sesuai nilai dari parameternya yaitu o
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    // method untuk mengahapus data dengan parameter id dengan tipe data String
   private boolean HapusData(String id){
        try {
            String sql = "DELETE FROM buku WHERE id="+id+";";//mendeklarasikan variabel sql dengan query untuk menghapus data sesuai kondisi
            stt = con.createStatement();//pembuatan statement
            stt.executeUpdate(sql);//eksekusi query
            return true;//mengembalikan nilai true
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;//mengembalikan nilai false
        }
    }
    // method untuk menambahkan data dengan parameter judul,penulis,harga dengan tipe data String
    public void TambahData(String judul, String penulis, String harga){
        try{
            
            String sql = "INSERT INTO buku VALUES (NULL,'"+judul+"','"+penulis+"',"+harga+")"; //mendeklarasikan variabel sql dengan query untuk menginsert data judul, penulis, harga
            stt = con.createStatement();//pembuatan statement
            stt.executeUpdate(sql);//eksekusi query
            model.addRow(new Object[]{judul,penulis,harga});//menamabah data pada baris dengan parameter baru objek yaitu yang berisi parameter judul,penulis, dan harga
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    // method untuk mengupdate data dengan parameter judul,penulis,harga yang bertipe data String
    public boolean UbahData(String id, String judul, String penulis, String harga){
       try {
            //mendeklarasikan variabel sql dengan query untuk mengupdate data judul, penulis, harga berdasarkan id sesuai data tersebut
            String sql = "UPDATE buku SET judul='"+judul
                    +"',penulis='"+penulis+"',harga="+harga
                    +" WHERE id="+id+";";

            stt = con.createStatement();//pembuatan statement
            stt.executeUpdate(sql);//eksekusi query
            return true; //mengembalikan nilai true 
        } catch (SQLException e) {
             System.out.println(e.getMessage());
             return false; //mengembalikan nilai false
        }
    }
    // method untuk mencari data dengan parameter by dan cari yang bertipe data String
    public void PencarianData(String by, String cari){
        try {
            //mendeklarasikan variabel sql dengan query untuk mencari data berdasarkan variabel by dan yang mengandung setiap huruf dari variabel cari
            String sql = "SELECT * FROM buku WHERE "+by+" LIKE '%"+cari+"%';";
            stt = con.createStatement();//pembuatan statement
            rss = stt.executeQuery(sql);//eksekusi query
                while(rss.next()){//perulangan untuk menampilkan data
                    //menampilkan data sesuai array : 
                    Object[] data = new Object[4];//membuat objek baru yaitu data dengan jumlah array 4
                    data[0] = rss.getString("id");// objek data pada larik ke-0 yaitu mengambil nilai dari stt berupa id
                    data[1] = rss.getString("judul");//objek data pada larik ke-1 yaitu mengambil nilai dari stt berupa judul
                    data[2] = rss.getString("penulis");//objek data pada larik ke-2 yaitu mengambil nilai dari stt berupa penulis
                    data[3] = rss.getInt("harga");//objek data pada larik ke-3 yaitu mengambil nilai dari stt berupa harga
                    model.addRow(data);//menambah baris pada model sesuai nilai dari parameternya yaitu data
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        comboPenulis = new javax.swing.JComboBox<>();
        txtHarga = new javax.swing.JTextField();
        btn_Ulangi = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboSearchBy = new javax.swing.JComboBox<>();
        btn_Simpan = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Hapus = new javax.swing.JButton();
        btn_Keluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(4, 84, 92));

        header.setBackground(new java.awt.Color(4, 133, 113));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM DATA BUKU");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(137, 137, 137))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(4, 133, 113));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Judul");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Harga");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Penulis");

        txtJudul.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comboPenulis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboPenulis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tere Liye", "W.S Rendra", "Felix Siauw", "Asma Nadia", "Dewi Lestari" }));

        txtHarga.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        btn_Ulangi.setBackground(Color.getHSBColor(45,115,255));
        btn_Ulangi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/refresh.png"))); // NOI18N
        btn_Ulangi.setText("Ulangi");
        btn_Ulangi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_UlangiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_UlangiMouseExited(evt);
            }
        });
        btn_Ulangi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UlangiActionPerformed(evt);
            }
        });

        txtCari.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCariCaretUpdate(evt);
            }
        });

        jLabel5.setText("Search :");

        jLabel6.setText("By :");

        comboSearchBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "judul", "penulis", "harga" }));

        btn_Simpan.setBackground(Color.getHSBColor(45,115,255));
        btn_Simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Save.png"))); // NOI18N
        btn_Simpan.setText("Simpan");
        btn_Simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_SimpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_SimpanMouseExited(evt);
            }
        });
        btn_Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SimpanActionPerformed(evt);
            }
        });

        btn_Update.setBackground(Color.getHSBColor(45,115,255));
        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Update.png"))); // NOI18N
        btn_Update.setText("Update");
        btn_Update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_UpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_UpdateMouseExited(evt);
            }
        });
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_Hapus.setBackground(Color.getHSBColor(45,115,255));
        btn_Hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Erase.png"))); // NOI18N
        btn_Hapus.setText("Hapus");
        btn_Hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_HapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_HapusMouseExited(evt);
            }
        });
        btn_Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HapusActionPerformed(evt);
            }
        });

        btn_Keluar.setBackground(Color.getHSBColor(45,115,255));
        btn_Keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/sign-out.png"))); // NOI18N
        btn_Keluar.setText("Keluar");
        btn_Keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_KeluarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_KeluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_KeluarMouseExited(evt);
            }
        });
        btn_Keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_KeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Simpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Update)
                .addGap(14, 14, 14)
                .addComponent(btn_Hapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Ulangi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Keluar)
                .addGap(147, 147, 147))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboSearchBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtHarga, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtJudul, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(comboPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Hapus)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Simpan)
                        .addComponent(btn_Ulangi)
                        .addComponent(btn_Keluar)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(comboSearchBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        InitTable();//menjalankan fungsi InitTable
        TampilData();//menjalankan fungsi TampilData
    }//GEN-LAST:event_formComponentShown

    private void btn_UlangiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UlangiMouseEntered
        //        btn_Ulangi.setBackground(Color.getHSBColor(655,275,425));
        btn_Ulangi.setBackground(Color.getHSBColor(655,675,725));
    }//GEN-LAST:event_btn_UlangiMouseEntered

    private void btn_UlangiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UlangiMouseExited
        btn_Ulangi.setBackground(Color.getHSBColor(45,115,255));
    }//GEN-LAST:event_btn_UlangiMouseExited
//fungsi menset ulang yang di dalam frame
    private void btn_UlangiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UlangiActionPerformed
        
//mengosongkan nilai dari variabel yang didefinisikan dibawah ini :
        txtJudul.setText("");
        txtHarga.setText("");
        comboPenulis.setSelectedItem(0);
        txtJudul.requestFocus();

    }//GEN-LAST:event_btn_UlangiActionPerformed

    //fungsi untuk menampilkan data di textfield dan combobox sesuai dengan data yang dipilih di tabel
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris = jTable1.getSelectedRow(); //deklarasi variabel baris dengan nilai yang di klik pada jTable1
        
        txtJudul.setText(jTable1.getValueAt(baris, 1).toString());//mengeset nilai di textfield txtJudul sesuai nilai dari baris ke-1 dari jTable1 yang dijadikan ke tipe data String
        comboPenulis.setSelectedItem(jTable1.getValueAt(baris, 2).toString());//mengeset nilai di combobox comboPenulis sesuai nilai dari baris ke-2 dari jTable1 yang dijadikan ke tipe data String
        txtHarga.setText(jTable1.getValueAt(baris, 3).toString());//mengeset nilai di textfield txtHarga sesuai nilai dari baris ke-3 dari jTable1 yang dijadikan ke tipe data String        
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_SimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SimpanMouseEntered
        
            //            btn_Simpan.setBackground(Color.getHSBColor(240,240,240));
            btn_Simpan.setBackground(Color.GREEN);
        
    }//GEN-LAST:event_btn_SimpanMouseEntered

    private void btn_SimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SimpanMouseExited

            btn_Simpan.setBackground(Color.getHSBColor(45,115,255));

    }//GEN-LAST:event_btn_SimpanMouseExited
//event ActionPerformed pada btn_Simpan untuk menjalankan fungsi TambahData
    private void btn_SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SimpanActionPerformed
        if (txtJudul.getText().length()!=0 && txtHarga.getText().length()!=0) { //penyeleksian kondisi jika textfield txtJudul dan txtHarga tidak kosong
            String judul = txtJudul.getText(); //mendeklarasikan judul sesuai dengan texfield txtJudul
            String penulis = comboPenulis.getSelectedItem().toString();//mendeklarasikan penulis sesuai dengan combobox combopenulis
            String harga = txtHarga.getText(); //mendeklarasikan harga sesuai dengan texfield txtHarga
            
            if(validasi(judul,penulis)){ //penyeleksian kondisi yang diambil dari fungsi validasi dengan parameter judul dan penulis
                JOptionPane.showMessageDialog(this, "Judul Sudah ada Boss");
            }
            else{// jika tidak 
            TambahData(judul,penulis,harga); //menjalankan fungsi TambahData sesuai dengan parameter judul, penulis, harga
            InitTable();//menampilkan ulang model dari table buku
            TampilData();//untuk menampilkan ulang fungsi TampilData
            JOptionPane.showMessageDialog(this, "Berhasil Simpan Data");
            }
        } 
        else {
        JOptionPane.showMessageDialog(this, "Isi Data Terlebih Dahulu Broo");    
        
        }
    }//GEN-LAST:event_btn_SimpanActionPerformed

    private void btn_UpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UpdateMouseEntered
        //            btn_Simpan.setBackground(Color.getHSBColor(240,240,240));
        btn_Update.setBackground(Color.getHSBColor(655,675,725));

    }//GEN-LAST:event_btn_UpdateMouseEntered

    private void btn_UpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UpdateMouseExited
        btn_Update.setBackground(Color.getHSBColor(45,115,255));
    }//GEN-LAST:event_btn_UpdateMouseExited
//event ActionPerformed pada btn_Update untuk menjalankan fungsi UbahData
    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        int baris = jTable1.getSelectedRow();//mendekarasikan variabel baris sesuai data yang dipilih di tabel
        String id = jTable1.getValueAt(baris, 0).toString(); //mendeklarasikan variabel id yang bernilai dari baris ke-0 dari tabel yang di konversi ke string
        String judul = txtJudul.getText();//mendeklarasikan judul sesuai dengan texfield txtJudul
        String penulis = comboPenulis.getSelectedItem().toString();//mendeklarasikan penulis sesuai dengan combobox combopenulis
        String harga = txtHarga.getText();//mendeklarasikan harga sesuai dengan texfield txtHarga
        
        int ok=JOptionPane.showConfirmDialog(this,"Update Data Yang Dipilih?","Confirmation Update",JOptionPane.YES_NO_OPTION);
        try {
            if(ok==0){
                if (UbahData(id,judul,penulis,harga)) //Kondisi jika menjalankan fungsi UbahData dengan parameter id, judul, penulis, dan harga
                    JOptionPane.showMessageDialog(null, "Berhasil Ubah Data");
                else 
                    JOptionPane.showMessageDialog(null, "Gagal Ubah Data");
                    InitTable();//menjalankan fungsi InitTable
                    TampilData();//menjalankan TampilData
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error");
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_HapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HapusMouseEntered
        btn_Hapus.setBackground(Color.getHSBColor(255,275,115));
    }//GEN-LAST:event_btn_HapusMouseEntered

    private void btn_HapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HapusMouseExited
        btn_Hapus.setBackground(Color.getHSBColor(45,115,255));
    }//GEN-LAST:event_btn_HapusMouseExited
//event ActionPerformed pada btn_Hapus untuk menjalankan fungsi HapusData
    private void btn_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HapusActionPerformed
       int baris = jTable1.getSelectedRow(); //mendekarasikan variabel baris sesuai data yang dipilih di tabel
       String id = jTable1.getValueAt(baris, 0).toString();//mendeklarasikan variabel id yang bernilai dari baris ke-0 dari tabel yang di konversi ke string
       
       int ya = JOptionPane.showConfirmDialog(this, "Ingin Menghapus Data?","Confirm Hapus",JOptionPane.YES_NO_OPTION);
       if (ya==0)
       {
        try
         {
            if(HapusData(id)) //Kondisi jika menjalankan fungsi HapusData dengan parameter id            
                JOptionPane.showMessageDialog(null, "Data Dengan"+"\nID : "+id+"\nJudul : "+txtJudul.getText()+"\nPenulis : "+comboPenulis.getSelectedItem()+"\nHarga : "+txtHarga.getText()+"\nBerhasil Dihapus");       
            else
                JOptionPane.showMessageDialog(null, "Gagal Hapus Data");
            InitTable();//menjalankan fungsi InitTable
            TampilData();//menjalankan TampilData
         }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Delete Data Gagal");
        }
       }
    }//GEN-LAST:event_btn_HapusActionPerformed

    private void btn_KeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_KeluarMouseClicked
        System.exit(0);//keluar dari program
    }//GEN-LAST:event_btn_KeluarMouseClicked

    private void btn_KeluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_KeluarMouseEntered
        btn_Keluar.setBackground(Color.getHSBColor(75,225,55));
    }//GEN-LAST:event_btn_KeluarMouseEntered

    private void btn_KeluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_KeluarMouseExited
        btn_Keluar.setBackground(Color.getHSBColor(45,115,255));
    }//GEN-LAST:event_btn_KeluarMouseExited

    private void btn_KeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_KeluarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_KeluarActionPerformed
//event CaretUpdate pada txtCari untuk menjalankan fungsi PencarianData
    private void txtCariCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCariCaretUpdate
        InitTable();//menjalankan fungsi InitTable
        if (txtCari.getText().length()==0 ){ //kondisi jika texfield txtCari kosong
            TampilData(); // akan langsung menjalankan fungsi TampilData
        } else { //kondisi jika texfield txtCari tidak kosong
            PencarianData(comboSearchBy.getSelectedItem().toString(), txtCari.getText()); // akan langsung menjalankan fungsi PencarianData dengan 2 parameter yaitu nilai dari comboSearchBy yang dijadikan string dan nilai dari txtCari
        }
    }//GEN-LAST:event_txtCariCaretUpdate

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
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDataBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Hapus;
    private javax.swing.JButton btn_Keluar;
    private javax.swing.JButton btn_Simpan;
    private javax.swing.JButton btn_Ulangi;
    private javax.swing.JButton btn_Update;
    private javax.swing.JComboBox<String> comboPenulis;
    private javax.swing.JComboBox<String> comboSearchBy;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJudul;
    // End of variables declaration//GEN-END:variables
}