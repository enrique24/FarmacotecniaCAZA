/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.caza.farmacia.vista;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author enrique
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogAcercaDe = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonCerrarAcercaDe = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bntVentanaPrincipalEdicionPMS = new javax.swing.JButton();
        btnVentanaPrincipalHojaElaboración = new javax.swing.JButton();
        bntVentanaPrincipalSalir = new javax.swing.JButton();
        bntVentanaPrincipalReenvasados = new javax.swing.JButton();
        jButtonAcercaDe = new javax.swing.JButton();

        jDialogAcercaDe.setTitle("Acerca de");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/images.jpg"))); // NOI18N

        jButtonCerrarAcercaDe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonCerrarAcercaDe.setText("Cerrar");
        jButtonCerrarAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarAcercaDeActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Versión: 1.01");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Farmacotecnia");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Enrique Martín Arenal");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Hecho por:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel4))
                        .addComponent(jLabel5)))
                .addGap(24, 24, 24))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButtonCerrarAcercaDe))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 69, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jButtonCerrarAcercaDe)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout jDialogAcercaDeLayout = new javax.swing.GroupLayout(jDialogAcercaDe.getContentPane());
        jDialogAcercaDe.getContentPane().setLayout(jDialogAcercaDeLayout);
        jDialogAcercaDeLayout.setHorizontalGroup(
            jDialogAcercaDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogAcercaDeLayout.setVerticalGroup(
            jDialogAcercaDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Farmacotecnia");
        setIconImage(getIconImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(980, 700));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        bntVentanaPrincipalEdicionPMS.setBackground(new java.awt.Color(204, 204, 255));
        bntVentanaPrincipalEdicionPMS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntVentanaPrincipalEdicionPMS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/producto.png"))); // NOI18N
        bntVentanaPrincipalEdicionPMS.setMnemonic('P');
        bntVentanaPrincipalEdicionPMS.setText("Productos, materiales, etc..");
        bntVentanaPrincipalEdicionPMS.setEnabled(false);
        bntVentanaPrincipalEdicionPMS.setFocusPainted(false);
        bntVentanaPrincipalEdicionPMS.setHideActionText(true);
        bntVentanaPrincipalEdicionPMS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntVentanaPrincipalEdicionPMS.setIconTextGap(10);
        bntVentanaPrincipalEdicionPMS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntVentanaPrincipalEdicionPMS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bntVentanaPrincipalEdicionPMSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bntVentanaPrincipalEdicionPMSMouseExited(evt);
            }
        });
        bntVentanaPrincipalEdicionPMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVentanaPrincipalEdicionPMSActionPerformed(evt);
            }
        });

        btnVentanaPrincipalHojaElaboración.setBackground(new java.awt.Color(250, 120, 120));
        btnVentanaPrincipalHojaElaboración.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnVentanaPrincipalHojaElaboración.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/factura.png"))); // NOI18N
        btnVentanaPrincipalHojaElaboración.setMnemonic('H');
        btnVentanaPrincipalHojaElaboración.setText("Hojas de elaboración");
        btnVentanaPrincipalHojaElaboración.setEnabled(false);
        btnVentanaPrincipalHojaElaboración.setFocusPainted(false);
        btnVentanaPrincipalHojaElaboración.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentanaPrincipalHojaElaboración.setMaximumSize(new java.awt.Dimension(199, 83));
        btnVentanaPrincipalHojaElaboración.setMinimumSize(new java.awt.Dimension(199, 83));
        btnVentanaPrincipalHojaElaboración.setPreferredSize(new java.awt.Dimension(199, 83));
        btnVentanaPrincipalHojaElaboración.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentanaPrincipalHojaElaboración.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentanaPrincipalHojaElaboraciónMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVentanaPrincipalHojaElaboraciónMouseExited(evt);
            }
        });
        btnVentanaPrincipalHojaElaboración.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentanaPrincipalHojaElaboraciónActionPerformed(evt);
            }
        });

        bntVentanaPrincipalSalir.setBackground(new java.awt.Color(255, 204, 153));
        bntVentanaPrincipalSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntVentanaPrincipalSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/etiqueta.png"))); // NOI18N
        bntVentanaPrincipalSalir.setMnemonic('E');
        bntVentanaPrincipalSalir.setText("Etiquetas");
        bntVentanaPrincipalSalir.setEnabled(false);
        bntVentanaPrincipalSalir.setFocusPainted(false);
        bntVentanaPrincipalSalir.setHideActionText(true);
        bntVentanaPrincipalSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntVentanaPrincipalSalir.setIconTextGap(10);
        bntVentanaPrincipalSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntVentanaPrincipalSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bntVentanaPrincipalSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bntVentanaPrincipalSalirMouseExited(evt);
            }
        });
        bntVentanaPrincipalSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVentanaPrincipalSalirActionPerformed(evt);
            }
        });

        bntVentanaPrincipalReenvasados.setBackground(new java.awt.Color(255, 255, 102));
        bntVentanaPrincipalReenvasados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntVentanaPrincipalReenvasados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/reenvasado.png"))); // NOI18N
        bntVentanaPrincipalReenvasados.setMnemonic('R');
        bntVentanaPrincipalReenvasados.setText("Reenvasados");
        bntVentanaPrincipalReenvasados.setEnabled(false);
        bntVentanaPrincipalReenvasados.setFocusPainted(false);
        bntVentanaPrincipalReenvasados.setHideActionText(true);
        bntVentanaPrincipalReenvasados.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntVentanaPrincipalReenvasados.setIconTextGap(10);
        bntVentanaPrincipalReenvasados.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntVentanaPrincipalReenvasados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bntVentanaPrincipalReenvasadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bntVentanaPrincipalReenvasadosMouseExited(evt);
            }
        });
        bntVentanaPrincipalReenvasados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVentanaPrincipalReenvasadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntVentanaPrincipalReenvasados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntVentanaPrincipalEdicionPMS, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVentanaPrincipalHojaElaboración, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntVentanaPrincipalSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntVentanaPrincipalEdicionPMS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVentanaPrincipalHojaElaboración, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntVentanaPrincipalSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntVentanaPrincipalReenvasados, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        bntVentanaPrincipalEdicionPMS.getAccessibleContext().setAccessibleDescription("");

        jButtonAcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/ayuda.png"))); // NOI18N
        jButtonAcercaDe.setToolTipText("Acerca de");
        jButtonAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcercaDeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonAcercaDe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButtonAcercaDe, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntVentanaPrincipalSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVentanaPrincipalSalirActionPerformed
        VentanaEtiquetas.instance.mostrarTodasEtiquetas();
        VentanaEtiquetas.instance.setLocationRelativeTo(null);
        VentanaEtiquetas.instance.setVisible(true);
    }//GEN-LAST:event_bntVentanaPrincipalSalirActionPerformed

    private void bntVentanaPrincipalEdicionPMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVentanaPrincipalEdicionPMSActionPerformed
       // VentanaEdicionPMS.instance;
        VentanaEdicionPMS.instance.setLocationRelativeTo(this);
        VentanaEdicionPMS.instance.setVisible(true);
    }//GEN-LAST:event_bntVentanaPrincipalEdicionPMSActionPerformed

    private void btnVentanaPrincipalHojaElaboraciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentanaPrincipalHojaElaboraciónActionPerformed

        VentanaHojasElaboracion.instance.setLocationRelativeTo(this);
        VentanaHojasElaboracion.instance.setVisible(true);
    }//GEN-LAST:event_btnVentanaPrincipalHojaElaboraciónActionPerformed

    private void bntVentanaPrincipalEdicionPMSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntVentanaPrincipalEdicionPMSMouseEntered
        if (bntVentanaPrincipalEdicionPMS.isEnabled()) {
            bntVentanaPrincipalEdicionPMS.setBackground(new java.awt.Color(145, 145, 187));
        }
    }//GEN-LAST:event_bntVentanaPrincipalEdicionPMSMouseEntered

    private void bntVentanaPrincipalEdicionPMSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntVentanaPrincipalEdicionPMSMouseExited
        bntVentanaPrincipalEdicionPMS.setBackground(new java.awt.Color(204, 204, 255));
    }//GEN-LAST:event_bntVentanaPrincipalEdicionPMSMouseExited

    private void btnVentanaPrincipalHojaElaboraciónMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentanaPrincipalHojaElaboraciónMouseExited
        btnVentanaPrincipalHojaElaboración.setBackground(new java.awt.Color(250, 120, 120));

    }//GEN-LAST:event_btnVentanaPrincipalHojaElaboraciónMouseExited

    private void btnVentanaPrincipalHojaElaboraciónMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentanaPrincipalHojaElaboraciónMouseEntered
        if (btnVentanaPrincipalHojaElaboración.isEnabled()) {
            btnVentanaPrincipalHojaElaboración.setBackground(new java.awt.Color(230, 48, 48));
        }

    }//GEN-LAST:event_btnVentanaPrincipalHojaElaboraciónMouseEntered

    private void bntVentanaPrincipalReenvasadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntVentanaPrincipalReenvasadosMouseEntered
        if (bntVentanaPrincipalReenvasados.isEnabled()) {
            bntVentanaPrincipalReenvasados.setBackground(new java.awt.Color(255, 255, 0));
        }
    }//GEN-LAST:event_bntVentanaPrincipalReenvasadosMouseEntered

    private void bntVentanaPrincipalReenvasadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntVentanaPrincipalReenvasadosMouseExited
        bntVentanaPrincipalReenvasados.setBackground(new java.awt.Color(255, 255, 102));
    }//GEN-LAST:event_bntVentanaPrincipalReenvasadosMouseExited

    private void bntVentanaPrincipalSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntVentanaPrincipalSalirMouseExited
        bntVentanaPrincipalSalir.setBackground(new java.awt.Color(255, 204, 153));
    }//GEN-LAST:event_bntVentanaPrincipalSalirMouseExited

    private void bntVentanaPrincipalSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntVentanaPrincipalSalirMouseEntered
        if (bntVentanaPrincipalSalir.isEnabled()) {
            bntVentanaPrincipalSalir.setBackground(new java.awt.Color(252, 139, 27));
        }
    }//GEN-LAST:event_bntVentanaPrincipalSalirMouseEntered

    private void bntVentanaPrincipalReenvasadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVentanaPrincipalReenvasadosActionPerformed

        VentanaReenvasados.instance.setLocationRelativeTo(this);
        VentanaReenvasados.instance.setVisible(true);
    }//GEN-LAST:event_bntVentanaPrincipalReenvasadosActionPerformed

    private void jButtonCerrarAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarAcercaDeActionPerformed
        jDialogAcercaDe.setVisible(false);
    }//GEN-LAST:event_jButtonCerrarAcercaDeActionPerformed

    private void jButtonAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcercaDeActionPerformed
        
        jDialogAcercaDe.setSize(430, 330);
        jDialogAcercaDe.setLocationRelativeTo(null);
        jDialogAcercaDe.setVisible(true);
    }//GEN-LAST:event_jButtonAcercaDeActionPerformed

    public void sesionCargada() {
        bntVentanaPrincipalEdicionPMS.setEnabled(true);
        bntVentanaPrincipalReenvasados.setEnabled(true);
        btnVentanaPrincipalHojaElaboración.setEnabled(true);
        bntVentanaPrincipalSalir.setEnabled(true);
    }
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/es/sacyl/caza/farmacia/resources/images.jpg"));

        
        return retValue;
    }

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntVentanaPrincipalEdicionPMS;
    private javax.swing.JButton bntVentanaPrincipalReenvasados;
    private javax.swing.JButton bntVentanaPrincipalSalir;
    private javax.swing.JButton btnVentanaPrincipalHojaElaboración;
    private javax.swing.JButton jButtonAcercaDe;
    private javax.swing.JButton jButtonCerrarAcercaDe;
    private javax.swing.JDialog jDialogAcercaDe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
