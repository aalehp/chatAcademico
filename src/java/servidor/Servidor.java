package servidor;

import config.conexion;
import hilos.hiloDeslogear;
import hilos.hiloLogear;
import hilos.hiloMensajes;
import hilos.hiloUsuarios;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Servidor extends javax.swing.JFrame {

    hiloMensajes hiloMsg;
    hiloUsuarios hiloUsr;
    hiloLogear hiloLog;
    hiloDeslogear hiloDesl;

    public Servidor() {
        initComponents();
        hiloMsg = new hiloMensajes(chatMsg, this);
        hiloUsr = new hiloUsuarios(usuariosConectados, this);
        hiloLog = new hiloLogear(chatMsg, this);
        hiloDesl = new hiloDeslogear(chatMsg, this);

        hiloLog.start();
        hiloMsg.start();
        hiloUsr.start();
        hiloDesl.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatMsg = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        usuariosConectados = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatMsg.setColumns(20);
        chatMsg.setRows(5);
        jScrollPane1.setViewportView(chatMsg);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("SERVIDOR CHAT");

        jScrollPane2.setViewportView(usuariosConectados);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("USUARIOS CONECTADOS");

        jMenu1.setText("Estadisticas Estudiantes");

        jMenuItem1.setText("Datos Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Fechas Logeo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Horas Entrada");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Horas Salida");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Mostrar datos del Usuario
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Connection conexion;
        conexion con;
        PreparedStatement ps;
        ResultSet rs;
        int b = 0;

        String matricula = JOptionPane.showInputDialog("Matricula Del Usuario a Mostrar");

        con = new conexion();
        try {
            conexion = con.getConexion();
            try {
                ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE matricula = '" + matricula + "' ORDER BY `edoSesion` ASC");
                rs = ps.executeQuery();

                while (rs.next()) {
                    if (rs.getString("matricula").equals(matricula)) {

                        String ruta = "D:\\Escuela\\PRIMAVER 2022\\Programacion Concurrente y Paralela\\Practicas\\chatAcademico\\web\\reportes//" + matricula + "_Genrales.txt";

                        File file = new File(ruta);

                        try {
                            file.createNewFile();

                            FileWriter fw = new FileWriter(file);
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write("Matricula: " + rs.getString("matricula") + "\n");
                            bw.write("Nombre: " + rs.getString("nombre") + "\n");
                            bw.write("Materia: " + rs.getString("materia") + "\n");
                            bw.write("Promedio: " + rs.getString("promedio") + "\n");
                            bw.write("Estado Sesión: " + rs.getString("edoSesion") + "\n");
                            bw.close();

                        } catch (IOException ex) {
                            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(hiloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    //Mostrar Fechas Logeo
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            Connection conexion;
            conexion con;
            PreparedStatement ps;
            ResultSet rs;
            FileWriter fw;
            BufferedWriter bw = null;
            int b = 0;

            String matricula = JOptionPane.showInputDialog("Matricula Del Usuario a Mostrar");

            con = new conexion();

            conexion = con.getConexion();

            ps = conexion.prepareStatement("SELECT * FROM registrosesiones WHERE matricula = '" + matricula + "' ORDER BY fechaLogin ASC;");
            rs = ps.executeQuery();

            String ruta = "D:\\Escuela\\PRIMAVER 2022\\Programacion Concurrente y Paralela\\Practicas\\chatAcademico\\web\\reportes//" + matricula + "_FechasLogeos.txt";
            File file = new File(ruta);

            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            while (rs.next()) {
                
                bw.write("Matricula: " + rs.getString("matricula") +" "+rs.getString("fechaLogin")+ "\n");
                
            }
            bw.close();

        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jMenuItem2ActionPerformed

//Mostrar Horas Entrada
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            Connection conexion;
            conexion con;
            PreparedStatement ps;
            ResultSet rs;
            FileWriter fw;
            BufferedWriter bw = null;
            int b = 0;

            String matricula = JOptionPane.showInputDialog("Matricula Del Usuario a Mostrar");

            con = new conexion();

            conexion = con.getConexion();

            ps = conexion.prepareStatement("SELECT * FROM registrosesiones WHERE matricula = '" + matricula + "' ORDER BY horaLogin ASC;");
            rs = ps.executeQuery();

            String ruta = "D:\\Escuela\\PRIMAVER 2022\\Programacion Concurrente y Paralela\\Practicas\\chatAcademico\\web\\reportes//" + matricula + "_horasLogeo.txt";
            File file = new File(ruta);

            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            while (rs.next()) {
                
                bw.write("Matricula: " + rs.getString("matricula") +" "+rs.getString("horaLogin")+ "\n");
                
            }
            bw.close();

        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

//Mostrar Horas Salida
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            Connection conexion;
            conexion con;
            PreparedStatement ps;
            ResultSet rs;
            FileWriter fw;
            BufferedWriter bw = null;
            int b = 0;

            String matricula = JOptionPane.showInputDialog("Matricula Del Usuario a Mostrar");

            con = new conexion();

            conexion = con.getConexion();

            ps = conexion.prepareStatement("SELECT * FROM registrosesiones WHERE matricula = '" + matricula + "' ORDER BY horaLogin ASC;");
            rs = ps.executeQuery();

            String ruta = "D:\\Escuela\\PRIMAVER 2022\\Programacion Concurrente y Paralela\\Practicas\\chatAcademico\\web\\reportes//" + matricula + "_horasSalida.txt";
            File file = new File(ruta);

            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            while (rs.next()) {
                
                bw.write("Matricula: " + rs.getString("matricula") +" "+rs.getString("horaLogOut")+ "\n");
            }
            bw.close();

        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Servidor().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatMsg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> usuariosConectados;
    // End of variables declaration//GEN-END:variables

}
