/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POSTTEST6;
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
        model.addColumn("JUDUL"); //membuat kolom JUDUL di instance class model
        model.addColumn("PENULIS");//membuat kolom PENULIS di instance class model
        model.addColumn("HARGA");//membuat kolom HARGA di instance class model
        jTable1.setModel(model); //mengeset instance of class model ke jTable1
    }
    private boolean validasi(String judul){ //method untuk validasi data agar tidak boleh sama
        try {
            stt = con.createStatement(); //untuk konek ke database
            String sql = "SELECT * FROM buku WHERE judul='"+judul+"'"; //mendeklarasikan variabel sql dengan query untuk menampilkan data sesuai kondisi judul yang ditentukan
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
                Object[] o = new Object[3]; //membuat object o dengan jumlah array 3
                //menampilkan data sesuai array : 
                o[0] = rss.getString("judul");
                o[1] = rss.getString("penulis");
                o[2] = rss.getInt("harga");
                model.addRow(o);
            }
        rss.close();//menutup rss
        stt.close();//menutup stt
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
   private void HapusData(String judul){
        try {
            String sql="delete from buku where judul='"+judul+"'";//mendeklarasikan variabel sql dengan query untuk menghapus data sesuai kondisi
            stt = con.createStatement();//pembuatan statement
            stt.executeUpdate(sql);//eksekusi query
            //mengosongkan atau menset isi dari tesxfield dan combobox di frame
            txtJudul.setText("");
            comboPenulis.setSelectedItem(0);
            txtHarga.setText("");
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    // method untuk menambahkan data dengan parameter judul,penulis,harga
    public void TambahData(String judul, String penulis, String harga){
        try{
            
            String sql = "INSERT INTO buku VALUES (NULL,'"+judul+"','"+penulis+"',"+harga+")"; //mendeklarasikan variabel sql dengan query untuk menginsert data judul, penulis, harga
            stt = con.createStatement();//pembuatan statement
            stt.executeUpdate(sql);//eksekusi query
            
            //mengosongkan atau menset isi dari tesxfield dan combobox di frame
            txtJudul.setText("");
            comboPenulis.setSelectedItem(0);
            txtHarga.setText("");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    // method untuk mngupdate data dengan parameter judul,penulis,harga
    public void UpdateData(String judul, String penulis, String harga){
       try {
            int baris = jTable1.getSelectedRow();
            String sql = "update buku set judul='"+judul+"',penulis='"+penulis+"',harga="+harga+" where judul='"+jTable1.getValueAt(baris, 0).toString()+"'";//mendeklarasikan variabel sql dengan query untuk mengupdate data judul, penulis, harga dari kondisi yang ditentukan
            stt = con.createStatement();//pembuatan statement
            stt.executeUpdate(sql);//eksekusi query
            
            //mengosongkan atau menset isi dari tesxfield dan combobox di frame
            txtJudul.setText("");
            comboPenulis.setSelectedItem(0);
            txtHarga.setText("");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
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
        cari = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        combocari = new javax.swing.JComboBox<>();
        btn_Simpan1 = new javax.swing.JButton();
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

        jPanel1.setBackground(new java.awt.Color(248, 178, 83));

        header.setBackground(new java.awt.Color(231, 136, 8));

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

        jPanel2.setBackground(new java.awt.Color(231, 136, 8));

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

        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cariKeyTyped(evt);
            }
        });

        jLabel5.setText("Search :");

        jLabel6.setText("By :");

        combocari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "judul", "penulis", "harga" }));

        btn_Simpan1.setBackground(Color.getHSBColor(45,115,255));
        btn_Simpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Save.png"))); // NOI18N
        btn_Simpan1.setText("Simpan");
        btn_Simpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_Simpan1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_Simpan1MouseExited(evt);
            }
        });
        btn_Simpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Simpan1ActionPerformed(evt);
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
                .addComponent(btn_Simpan1)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(combocari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(comboPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)))))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Simpan1)
                    .addComponent(btn_Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Hapus)
                    .addComponent(btn_Ulangi)
                    .addComponent(btn_Keluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(combocari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
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
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        txtJudul.setText("");
        txtHarga.setText("");
        comboPenulis.setSelectedItem("");
        txtJudul.requestFocus();

    }//GEN-LAST:event_btn_UlangiActionPerformed

    //fungsi untuk menampilkan data di textfield dan combobox sesuai dengan data yang dipilih di tabel
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris = jTable1.getSelectedRow(); //deklarasi variabel baris dengan nilai yang di klik pada jTable1
        
        String judul_edit = jTable1.getValueAt(baris, 0).toString(); //deklarasi variabel judul_edit dengan nilai dari baris ke-0 dari jTable1 yang dijadikan ke tipe data String
        String penulis_edit = jTable1.getValueAt(baris, 1).toString(); //deklarasi variabel penulis_edit dengan nilai dari baris ke-1 dari jTable1 yang dijadikan ke tipe data String
        String harga_edit = jTable1.getValueAt(baris, 2).toString();//deklarasi variabel harga_edit dengan nilai dari baris ke-2 dari jTable1 yang dijadikan ke tipe data String
        
        txtJudul.setText(judul_edit); //mengeset nilai di textfield txtJudul sesuai dari nilai judul_edit
        comboPenulis.setSelectedItem(penulis_edit); //mengeset nilai di combobox comboPenulis sesuai dari nilai penulis_edit
        txtHarga.setText(harga_edit);//mengeset nilai di textfield txtHarga sesuai dari nilai harga_edit
    }//GEN-LAST:event_jTable1MouseClicked
//fungsi untuk melakukan pencarian
    private void cariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyTyped
        model.getDataVector().removeAllElements();//mendeklarasikan model yang mmengambil data vector dan meremove semua ellements
        model.fireTableDataChanged();//mendeklarasikan model yang di fireTableDataChanged
        String caridengan = combocari.getSelectedItem().toString();//mendeklarasikan variabel caridengan yang mengambil nilai dari combocari yang di jadikan string
        try {
            
             String sql="select * from buku where "+caridengan+" like '%"+cari.getText()+"%'";////mendeklarasikan variabel sql dengan query untuk mencari data sesuai nilai dari combocari yang mengandung data sesuai nilai yang diinput di textfield cari
           stt = con. createStatement();//pembuatan statement
            rss = stt. executeQuery(sql);//eksekusi query
            ResultSet rss=stt.executeQuery(sql);
            while (rss.next()) { // perulangan result set dari variable rss hingga tidak terpenuhi untuk perulangan
                //menampilkan data sesuai array : 
                Object[] o=new Object[3];
                o[0]=rss.getString("JUDUL");
                o[1]=rss.getString("PENULIS");
                o[2]=rss.getString("HARGA");
            model.addRow(o);
            }
            stt.close();//menutup stt
            rss.close();//menutup rss
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }//GEN-LAST:event_cariKeyTyped

    private void btn_Simpan1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Simpan1MouseEntered
        
            //            btn_Simpan.setBackground(Color.getHSBColor(240,240,240));
            btn_Simpan1.setBackground(Color.GREEN);
        
    }//GEN-LAST:event_btn_Simpan1MouseEntered

    private void btn_Simpan1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Simpan1MouseExited

            btn_Simpan1.setBackground(Color.getHSBColor(45,115,255));

    }//GEN-LAST:event_btn_Simpan1MouseExited
//event ActionPerformed untuk menjalankan fungsi TambahData
    private void btn_Simpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Simpan1ActionPerformed
        if (txtJudul.getText().length()!=0 && txtHarga.getText().length()!=0) { //penyeleksian kondisijika textfield txtJudul dan txtHarga tidak kosong
            String judul = txtJudul.getText(); //mendeklarasikan judul sesuai dengan texfield txtJudul
            String penulis = comboPenulis.getSelectedItem().toString();//mendeklarasikan penulis sesuai dengan combobox combopenulis
            String harga = txtHarga.getText(); //mendeklarasikan harga sesuai dengan texfield txtHarga
            
            if(validasi(judul)){ //penyeleksian kondisi yang diambil dari fungsi validasi dengan parameter judul
                JOptionPane.showMessageDialog(this, "Judul Sudah ada Boss");
            }
            else{
            TambahData(judul,penulis,harga); //menjalankan fungsi TambahData sesuai dengan parameter judul, penulis, harga
            InitTable();//menampilkan ulang model dari table buku
            TampilData();//untuk menampilkan ulang fungsi TampilData
            JOptionPane.showMessageDialog(this, "Berhasil Simpan Data");
            }
        } 
        else {
        JOptionPane.showMessageDialog(this, "Isi Data Terlebih Dahulu Broo");    
        
        }
    }//GEN-LAST:event_btn_Simpan1ActionPerformed

    private void btn_UpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UpdateMouseEntered
        //            btn_Simpan.setBackground(Color.getHSBColor(240,240,240));
        btn_Update.setBackground(Color.getHSBColor(655,675,725));

    }//GEN-LAST:event_btn_UpdateMouseEntered

    private void btn_UpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UpdateMouseExited
        btn_Update.setBackground(Color.getHSBColor(45,115,255));
    }//GEN-LAST:event_btn_UpdateMouseExited

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        String judul = txtJudul.getText();//mendeklarasikan judul sesuai dengan texfield txtJudul
        String penulis = comboPenulis.getSelectedItem().toString();//mendeklarasikan penulis sesuai dengan combobox combopenulis
        String harga = txtHarga.getText();//mendeklarasikan harga sesuai dengan texfield txtHarga
        
        int ok=JOptionPane.showConfirmDialog(this,"Update Data Yang Dipilih?","Confirmation Update",JOptionPane.YES_NO_OPTION);
         try
            {
                if(ok==0)
                {
                try
                    {
                        UpdateData(judul,penulis,harga); //menjalankan fungsi UpdateData tadi dengan parameter yang ada
                        InitTable(); //untuk menampilkan ulang model yang ada sehingga akan update ketika ada perubahan setelah dilakukan UpdateData di database
                        TampilData();//untuk menampilkan ulang fungsi TampilData
                        
                        JOptionPane.showMessageDialog(this,"Update Data Sukses");
                    }
                catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(this, "Update Data Gagal");
                    }
                }
                
           }catch (Exception e){}
       
            
//            JOptionPane.showMessageDialog(this, "Data Berhasil Di Update");
       
//            JOptionPane.showMessageDialog(this, "Maaf, Silahkan Pilih Data Yang Ingin Diupdate Terlebih Dahulu");
       
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_HapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HapusMouseEntered
        btn_Hapus.setBackground(Color.getHSBColor(255,275,115));
    }//GEN-LAST:event_btn_HapusMouseEntered

    private void btn_HapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HapusMouseExited
        btn_Hapus.setBackground(Color.getHSBColor(45,115,255));
    }//GEN-LAST:event_btn_HapusMouseExited
//fungsi utnuk menghaous data
    private void btn_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HapusActionPerformed
       int baris = jTable1.getSelectedRow(); //mendekarasikan variabel baris sesuai data yang dipilih di tabel
   String judul = jTable1.getValueAt(baris, 0).toString(); //mendeklarasikan variabel judul yang bernilai dari baris ke-0 dari tabel yang di konversi ke string
   
   int ya = JOptionPane.showConfirmDialog(this, "Ingin Menghapus Data?","Confirm Hapus",JOptionPane.YES_NO_OPTION);
   
       if (ya==0)
       {
        try
         {
            HapusData(judul);//menjalankan fungsi HapusData dengan parameter judul berdasarkan baris yang di pilih
            InitTable();//menampilkan ulang model dari table buku
            TampilData();//menampilkan ulang dari model table buku
            
            JOptionPane.showMessageDialog(this, "Data Dengan"+"\nJudul : "+txtJudul.getText()+"\nPenulis : "+comboPenulis.getSelectedItem()+"\nHarga : "+txtHarga.getText()+"\nBerhasil Dihapus");
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
    private javax.swing.JButton btn_Simpan1;
    private javax.swing.JButton btn_Ulangi;
    private javax.swing.JButton btn_Update;
    private javax.swing.JTextField cari;
    private javax.swing.JComboBox<String> comboPenulis;
    private javax.swing.JComboBox<String> combocari;
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
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJudul;
    // End of variables declaration//GEN-END:variables
}