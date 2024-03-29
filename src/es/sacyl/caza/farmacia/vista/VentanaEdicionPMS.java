/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.caza.farmacia.vista;

import es.sacyl.caza.farmacia.controlador.ControladorMaquinaria;
import es.sacyl.caza.farmacia.controlador.ControladorMaterialElaborar;
import es.sacyl.caza.farmacia.controlador.ControladorMaterialEnvasar;
import es.sacyl.caza.farmacia.controlador.ControladorProductos;
import es.sacyl.caza.farmacia.controlador.ControladorVentanaEdicionPSM;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author enrique
 */
public class VentanaEdicionPMS extends javax.swing.JFrame {

    private ControladorProductos controlProductos;
    private ControladorMaquinaria controMaquinaria;
    private ControladorMaterialEnvasar controlMaterialEnvasar;
    private ControladorMaterialElaborar controlMaterialElaborar;
    private ControladorVentanaEdicionPSM controlVentana;
    public PanelMaquinaria panelMaquinaria;
    public PanelMaterialElaboracion panelMaterialElaborar;
    public PanelMaterialEnvasar panelMaterialEnvasar;
    public PanelProductos panelProductos;
    public static final VentanaEdicionPMS instance= new VentanaEdicionPMS(null, false);
    
    /**
     * Creates new form VentanaEdicionPMS
     */
    private VentanaEdicionPMS(java.awt.Frame parent, boolean modal) {
        super("Productos,materiales, etc..");
        initComponents();
        panelMaquinaria = new PanelMaquinaria(false);
        panelMaterialElaborar = new PanelMaterialElaboracion(false);
        panelMaterialEnvasar = new PanelMaterialEnvasar(false);
        panelProductos = new PanelProductos(false);
        //con icono 
        jTabbedPaneEdicionPMS.addTab("Productos", new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/producto.png")), panelProductos);
        //jTabbedPaneEdicionPMS.add("Productos", panelProductos);
        jTabbedPaneEdicionPMS.add("Maquinaria", panelMaquinaria);
        jTabbedPaneEdicionPMS.add("Material para elaborar", panelMaterialElaborar);
        jTabbedPaneEdicionPMS.add("Material para envasar", panelMaterialEnvasar);
        controlProductos = new ControladorProductos(panelProductos, this);
        controMaquinaria = new ControladorMaquinaria(panelMaquinaria, this);
        controlMaterialEnvasar = new ControladorMaterialEnvasar(panelMaterialEnvasar);
        controlMaterialElaborar = new ControladorMaterialElaborar(panelMaterialElaborar, this);
        controlVentana = new ControladorVentanaEdicionPSM(this, controMaquinaria, controlMaterialElaborar, controlMaterialEnvasar, controlProductos);
        //He decidido eliminar la opcion de cambiar de estilo la aplicación
        //queda codigo obsoleto en el ControladorVentanaEdicionPSM pero con esta línea sobra para que no se ejecute
        jMenu4.setVisible(false);

    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/es/sacyl/caza/farmacia/resources/images.jpg"));

        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPaneEdicionPMS = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemArchivoNuevo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemArchivoSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemEditarModificar = new javax.swing.JMenuItem();
        jMenuItemEditarEliminar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemVerPrimero = new javax.swing.JMenuItem();
        jMenuItemVerAnterior = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jMenuItemVerSiguiente = new javax.swing.JMenuItem();
        jMenuItemVerUltimo = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();
        jRadioButtonMenuItemColores = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemClasico = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Farmacotecnia->Productos, maquinaria, etc...");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(980, 700));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPaneEdicionPMS.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneEdicionPMS.setToolTipText("");
        jTabbedPaneEdicionPMS.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneEdicionPMS)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneEdicionPMS)
        );

        jMenu1.setText("Archivo");

        jMenuItemArchivoNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemArchivoNuevo.setText("Nuevo");
        jMenu1.add(jMenuItemArchivoNuevo);
        jMenu1.add(jSeparator1);

        jMenuItemArchivoSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemArchivoSalir.setText("Salir");
        jMenu1.add(jMenuItemArchivoSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

        jMenuItemEditarModificar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemEditarModificar.setText("Modificar");
        jMenu2.add(jMenuItemEditarModificar);

        jMenuItemEditarEliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemEditarEliminar.setText("Eliminar");
        jMenu2.add(jMenuItemEditarEliminar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ver");

        jMenuItemVerPrimero.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemVerPrimero.setText("Primero");
        jMenu3.add(jMenuItemVerPrimero);

        jMenuItemVerAnterior.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemVerAnterior.setText("Anterior");
        jMenu3.add(jMenuItemVerAnterior);
        jMenu3.add(jSeparator14);

        jMenuItemVerSiguiente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemVerSiguiente.setText("Siguiente");
        jMenu3.add(jMenuItemVerSiguiente);

        jMenuItemVerUltimo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemVerUltimo.setText("Último");
        jMenu3.add(jMenuItemVerUltimo);
        jMenu3.add(jSeparator15);

        jMenu4.setText("Estilo de la ventana");

        buttonGroup1.add(jRadioButtonMenuItemColores);
        jRadioButtonMenuItemColores.setSelected(true);
        jRadioButtonMenuItemColores.setText("Colores vivos");
        jMenu4.add(jRadioButtonMenuItemColores);

        buttonGroup1.add(jRadioButtonMenuItemClasico);
        jRadioButtonMenuItemClasico.setText("Clásico");
        jMenu4.add(jRadioButtonMenuItemClasico);

        jMenu3.add(jMenu4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenu jMenu4;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItemArchivoNuevo;
    public javax.swing.JMenuItem jMenuItemArchivoSalir;
    public javax.swing.JMenuItem jMenuItemEditarEliminar;
    public javax.swing.JMenuItem jMenuItemEditarModificar;
    public javax.swing.JMenuItem jMenuItemVerAnterior;
    public javax.swing.JMenuItem jMenuItemVerPrimero;
    public javax.swing.JMenuItem jMenuItemVerSiguiente;
    public javax.swing.JMenuItem jMenuItemVerUltimo;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemClasico;
    public javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemColores;
    public javax.swing.JPopupMenu.Separator jSeparator1;
    public javax.swing.JPopupMenu.Separator jSeparator14;
    public javax.swing.JPopupMenu.Separator jSeparator15;
    public javax.swing.JTabbedPane jTabbedPaneEdicionPMS;
    // End of variables declaration//GEN-END:variables
}
