/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.caza.farmacia.vista;

import es.sacyl.caza.farmacia.controlador.ControladorFichasDeMedicamentos;
import es.sacyl.caza.farmacia.controlador.ControladorMaquinaria;
import es.sacyl.caza.farmacia.controlador.ControladorMaterialElaborar;
import es.sacyl.caza.farmacia.controlador.ControladorMaterialEnvasar;
import es.sacyl.caza.farmacia.controlador.ControladorProductos;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author enrique
 */
public class VentanaHojasElaboracion extends javax.swing.JFrame {

    ControladorFichasDeMedicamentos control;
    public PanelMaquinaria panelAddMaquinaria;
    public ControladorMaquinaria controlMaquinaria;
    public PanelMaterialElaboracion panelAddMaterialElaboracion;
    public ControladorMaterialElaborar controlMaterialElaborar;
    public PanelMaterialEnvasar panelAddMaterialEnvasar;
    public ControladorMaterialEnvasar controlMaterialEnvasar;
    public PanelProductos panelAddProductos;
    public ControladorProductos controlProductos;
    public final static VentanaHojasElaboracion instance= new VentanaHojasElaboracion(null, false);

    /**
     * Creates new form VentanaHojasElaboracion
     */
    private VentanaHojasElaboracion(java.awt.Frame parent, boolean modal) {
        super("Hojas de elaboración");
        initComponents();
        addPanelesADialog();
        controlMaquinaria = new ControladorMaquinaria(panelAddMaquinaria);
        controlMaterialElaborar = new ControladorMaterialElaborar(panelAddMaterialElaboracion);
        controlMaterialEnvasar = new ControladorMaterialEnvasar(panelAddMaterialEnvasar);
        controlProductos = new ControladorProductos(panelAddProductos);
        //Cambiar la cantidad que se mueve el scroll al girar la rueda del ratón
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        control = new ControladorFichasDeMedicamentos(this);
       // jButtonAceptarFichasDeMedicamentos.setVisible(false);
       // jButtonCancelarFichasDeMedicamentos.setVisible(false);
        jPanelAceptarCancelar.setVisible(false);
        jPanelAceptarCancelar.setBackground(new java.awt.Color(245,83,83,200));
    }

    //Añade los paneles correspondientes a cada JDialog
    private void addPanelesADialog() {
        //Panel maquinaria
        panelAddMaquinaria = new PanelMaquinaria(true);
        javax.swing.GroupLayout jDialogAddMaquinariaLayout = new javax.swing.GroupLayout(jDialogAddMaquinaria.getContentPane());
        jDialogAddMaquinaria.getContentPane().setLayout(jDialogAddMaquinariaLayout);
        jDialogAddMaquinariaLayout.setHorizontalGroup(
                jDialogAddMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 975, Short.MAX_VALUE)
                .addGroup(jDialogAddMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialogAddMaquinariaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(panelAddMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialogAddMaquinariaLayout.setVerticalGroup(
                jDialogAddMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 575, Short.MAX_VALUE)
                .addGroup(jDialogAddMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialogAddMaquinariaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(panelAddMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
        );
        //Panel material elaboracion
        panelAddMaterialElaboracion = new PanelMaterialElaboracion(true);
        javax.swing.GroupLayout jDialogAddMaterialElaborarLayout = new javax.swing.GroupLayout(jDialogAddMaterialElaborar.getContentPane());
        jDialogAddMaterialElaborar.getContentPane().setLayout(jDialogAddMaterialElaborarLayout);
        jDialogAddMaterialElaborarLayout.setHorizontalGroup(
                jDialogAddMaterialElaborarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 975, Short.MAX_VALUE)
                .addGroup(jDialogAddMaterialElaborarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialogAddMaterialElaborarLayout.createSequentialGroup()
                                .addComponent(panelAddMaterialElaboracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialogAddMaterialElaborarLayout.setVerticalGroup(
                jDialogAddMaterialElaborarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 600, Short.MAX_VALUE)
                .addGroup(jDialogAddMaterialElaborarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelAddMaterialElaboracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //Panel material para envasar
        panelAddMaterialEnvasar = new PanelMaterialEnvasar(true);
        javax.swing.GroupLayout jDialogAddMaterialEnvasarLayout = new javax.swing.GroupLayout(jDialogAddMaterialEnvasar.getContentPane());
        jDialogAddMaterialEnvasar.getContentPane().setLayout(jDialogAddMaterialEnvasarLayout);
        jDialogAddMaterialEnvasarLayout.setHorizontalGroup(
                jDialogAddMaterialEnvasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 975, Short.MAX_VALUE)
                .addGroup(jDialogAddMaterialEnvasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialogAddMaterialEnvasarLayout.createSequentialGroup()
                                .addComponent(panelAddMaterialEnvasar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialogAddMaterialEnvasarLayout.setVerticalGroup(
                jDialogAddMaterialEnvasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 600, Short.MAX_VALUE)
                .addGroup(jDialogAddMaterialEnvasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelAddMaterialEnvasar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        //Panel productos
        panelAddProductos = new PanelProductos(true);
        javax.swing.GroupLayout jDialogAddProductosLayout = new javax.swing.GroupLayout(jDialogAddProducto.getContentPane());
        jDialogAddProducto.getContentPane().setLayout(jDialogAddProductosLayout);
        jDialogAddProductosLayout.setHorizontalGroup(
                jDialogAddProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 975, Short.MAX_VALUE)
                .addGroup(jDialogAddProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialogAddProductosLayout.createSequentialGroup()
                                .addComponent(panelAddProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialogAddProductosLayout.setVerticalGroup(
                jDialogAddProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 600, Short.MAX_VALUE)
                .addGroup(jDialogAddProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelAddProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    @Override
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

        jDialogAddMaquinaria = new javax.swing.JDialog();
        jDialogAddMaterialElaborar = new javax.swing.JDialog();
        jDialogAddMaterialEnvasar = new javax.swing.JDialog();
        jDialogAddProducto = new javax.swing.JDialog();
        jDialogModificarProducto = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jComboBoxDialogModificarProductoUnidades = new javax.swing.JComboBox();
        jButtonDialogModificarProductoAceptar = new javax.swing.JButton();
        jLabelDialogModificarProductoProductos = new javax.swing.JLabel();
        jButtonDialogModificarProductoCancelar = new javax.swing.JButton();
        jTextFieldDialogModificarProductoCantidad = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jCheckBoxCSP = new javax.swing.JCheckBox();
        jDialogEtiquetas = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jButtonDialogEtiquetasVolver = new javax.swing.JButton();
        jButtonDialogEtiquetasNuevaEtiqueta = new javax.swing.JButton();
        jButtonDialogEtiquetasVerEtiquetas = new javax.swing.JButton();
        jPopupMenuCopiarPegar = new javax.swing.JPopupMenu();
        jMenuItemCopiar = new javax.swing.JMenuItem();
        jMenuItemPegar = new javax.swing.JMenuItem();
        jToolBarProductos = new javax.swing.JToolBar();
        jButtonPrimeroFichasDeMedicamentos = new javax.swing.JButton();
        jButtonAtrasFichasDeMedicamentos = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jLabelPosicionFichasDeMedicamentos = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButtonSiguienteFichasDeMedicamentos = new javax.swing.JButton();
        jButtonUltimoFichasDeMedicamentos = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButtonNuevoFichasDeMedicamentos = new javax.swing.JButton();
        jButtonModificarFichasDeMedicamentos = new javax.swing.JButton();
        jButtonEliminarFichasDeMedicamentos = new javax.swing.JButton();
        jButtonMostrarEtiqueta = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanelAceptarCancelar = new javax.swing.JPanel();
        jButtonAceptarFichasDeMedicamentos = new javax.swing.JButton();
        jButtonCancelarFichasDeMedicamentos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanelListaFichasDeMedicamentos = new javax.swing.JPanel();
        jScrollPaneListaMedicamentos = new javax.swing.JScrollPane();
        jListFichasDeMedicamentos = new javax.swing.JList();
        jPanelEdicionDatosFichasDeMedicamentos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaMedicamento = new javax.swing.JTextArea();
        jLabelIdFichasDeMedicamentos = new javax.swing.JLabel();
        jComboBoxVeredicto = new javax.swing.JComboBox();
        jTextFieldObservacionesVeredicto = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaProcedimiento = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaObservaciones = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaEstabilidad = new javax.swing.JTextArea();
        jComboBoxViaAdministracion = new javax.swing.JComboBox();
        jTextFieldDatosOrganolepsis = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaParaEtiqueta = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaObservacionesElaboracion = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextAreaOrigen = new javax.swing.JTextArea();
        jComboBoxEDO = new javax.swing.JComboBox();
        jComboBoxTipo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxElaboradoPor = new javax.swing.JComboBox();
        jPanelProductosBuscar = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxBuscarFichasDeMedicamentos = new javax.swing.JComboBox();
        jTextFieldBuscarFichasDeMedicamentos = new javax.swing.JTextField();
        jButtonBuscarFichasDeMedicamentos = new javax.swing.JButton();
        jButtonQuitarBuscarFichasDeMedicamentos = new javax.swing.JButton();
        jCheckBoxMostrarTodo = new javax.swing.JCheckBox();
        jPanelMaquinaria = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableMaquinaria = new javax.swing.JTable();
        jButtonAddMaquinaria = new javax.swing.JButton();
        jButtonEliminarMaquinaria = new javax.swing.JButton();
        jPanelMaterialEnvasar = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableMaterialEnvasar = new javax.swing.JTable();
        jButtonAddMaterialEnvasar = new javax.swing.JButton();
        jButtonEliminarMaterialEnvasar = new javax.swing.JButton();
        jPanelMaterialElaborar = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTableMaterialElaborar = new javax.swing.JTable();
        jButtonAddMaterialElaborar = new javax.swing.JButton();
        jButtonEliminarMaterialElaborar = new javax.swing.JButton();
        jPanelProductos = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jButtonAddProducto = new javax.swing.JButton();
        jButtonModificarProducto = new javax.swing.JButton();
        jButtonEliminarProducto = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemArchivoNuevo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemArchivoSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemEditarModificar = new javax.swing.JMenuItem();
        jMenuItemEditarEliminar = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenuItemAddMaquinaria = new javax.swing.JMenuItem();
        jMenuItemAddMaterialElaborar = new javax.swing.JMenuItem();
        jMenuItemAddMaterialEnvasar = new javax.swing.JMenuItem();
        jMenuItemAddProducto = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemVerPrimero = new javax.swing.JMenuItem();
        jMenuItemVerAnterior = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jMenuItemVerSiguiente = new javax.swing.JMenuItem();
        jMenuItemVerUltimo = new javax.swing.JMenuItem();

        jDialogAddMaquinaria.setTitle("Farmacotecnia->Hojas de elaboración->Añadir maquinaria");
        jDialogAddMaquinaria.setAlwaysOnTop(true);
        jDialogAddMaquinaria.setModal(true);
        jDialogAddMaquinaria.setResizable(false);

        javax.swing.GroupLayout jDialogAddMaquinariaLayout = new javax.swing.GroupLayout(jDialogAddMaquinaria.getContentPane());
        jDialogAddMaquinaria.getContentPane().setLayout(jDialogAddMaquinariaLayout);
        jDialogAddMaquinariaLayout.setHorizontalGroup(
            jDialogAddMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialogAddMaquinariaLayout.setVerticalGroup(
            jDialogAddMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jDialogAddMaterialElaborar.setTitle("Farmacotecnia->Hojas de elaboración->Añadir material elaboración");
        jDialogAddMaterialElaborar.setAlwaysOnTop(true);
        jDialogAddMaterialElaborar.setModal(true);

        javax.swing.GroupLayout jDialogAddMaterialElaborarLayout = new javax.swing.GroupLayout(jDialogAddMaterialElaborar.getContentPane());
        jDialogAddMaterialElaborar.getContentPane().setLayout(jDialogAddMaterialElaborarLayout);
        jDialogAddMaterialElaborarLayout.setHorizontalGroup(
            jDialogAddMaterialElaborarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );
        jDialogAddMaterialElaborarLayout.setVerticalGroup(
            jDialogAddMaterialElaborarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jDialogAddMaterialEnvasar.setTitle("Farmacotecnia->Hojas de elaboración->Añadir material para envasar");
        jDialogAddMaterialEnvasar.setAlwaysOnTop(true);
        jDialogAddMaterialEnvasar.setModal(true);

        javax.swing.GroupLayout jDialogAddMaterialEnvasarLayout = new javax.swing.GroupLayout(jDialogAddMaterialEnvasar.getContentPane());
        jDialogAddMaterialEnvasar.getContentPane().setLayout(jDialogAddMaterialEnvasarLayout);
        jDialogAddMaterialEnvasarLayout.setHorizontalGroup(
            jDialogAddMaterialEnvasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialogAddMaterialEnvasarLayout.setVerticalGroup(
            jDialogAddMaterialEnvasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jDialogAddProducto.setTitle("Farmacotecnia->Hojas de elaboración->Añadir producto");
        jDialogAddProducto.setAlwaysOnTop(true);
        jDialogAddProducto.setModal(true);

        javax.swing.GroupLayout jDialogAddProductoLayout = new javax.swing.GroupLayout(jDialogAddProducto.getContentPane());
        jDialogAddProducto.getContentPane().setLayout(jDialogAddProductoLayout);
        jDialogAddProductoLayout.setHorizontalGroup(
            jDialogAddProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialogAddProductoLayout.setVerticalGroup(
            jDialogAddProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jDialogModificarProducto.setAlwaysOnTop(true);
        jDialogModificarProducto.setModal(true);

        jPanel2.setBackground(new java.awt.Color(255, 223, 204));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2), "Modificar producto"));

        jComboBoxDialogModificarProductoUnidades.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxDialogModificarProductoUnidades.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ML", "MG", "L", "GR", "gotas", "bolsas", "vial", "comp", "cápsulas", "frasco", "ampollas" }));

        jButtonDialogModificarProductoAceptar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDialogModificarProductoAceptar.setText("Aceptar");

        jLabelDialogModificarProductoProductos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDialogModificarProductoProductos.setText("jLabel21");

        jButtonDialogModificarProductoCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDialogModificarProductoCancelar.setText("Cancelar");

        jTextFieldDialogModificarProductoCantidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldDialogModificarProductoCantidad.setText("jTextField2");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Unidades:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Cantidad:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Producto:");

        jCheckBoxCSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxCSP.setText("C.S.P. (cantidad suficiente para)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxCSP)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDialogModificarProductoProductos))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDialogModificarProductoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonDialogModificarProductoAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDialogModificarProductoCancelar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxDialogModificarProductoUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabelDialogModificarProductoProductos))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextFieldDialogModificarProductoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxCSP)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jComboBoxDialogModificarProductoUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDialogModificarProductoAceptar)
                    .addComponent(jButtonDialogModificarProductoCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogModificarProductoLayout = new javax.swing.GroupLayout(jDialogModificarProducto.getContentPane());
        jDialogModificarProducto.getContentPane().setLayout(jDialogModificarProductoLayout);
        jDialogModificarProductoLayout.setHorizontalGroup(
            jDialogModificarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogModificarProductoLayout.setVerticalGroup(
            jDialogModificarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialogEtiquetas.setAlwaysOnTop(true);
        jDialogEtiquetas.setModal(true);

        jPanel5.setBackground(new java.awt.Color(255, 223, 204));

        jPanel4.setBackground(new java.awt.Color(251, 250, 250));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("¿Qué desea hacer con el medicamento seleccionado?");

        jButtonDialogEtiquetasVolver.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDialogEtiquetasVolver.setText("Volver");

        jButtonDialogEtiquetasNuevaEtiqueta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDialogEtiquetasNuevaEtiqueta.setText("Crear una nueva etiqueta relacionada con este medicamento");
        jButtonDialogEtiquetasNuevaEtiqueta.setToolTipText("");

        jButtonDialogEtiquetasVerEtiquetas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDialogEtiquetasVerEtiquetas.setText("Ver las etiquetas relacionadas con este medicamento");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonDialogEtiquetasVerEtiquetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDialogEtiquetasNuevaEtiqueta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDialogEtiquetasVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jButtonDialogEtiquetasVerEtiquetas)
                .addGap(18, 18, 18)
                .addComponent(jButtonDialogEtiquetasNuevaEtiqueta)
                .addGap(18, 18, 18)
                .addComponent(jButtonDialogEtiquetasVolver)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogEtiquetasLayout = new javax.swing.GroupLayout(jDialogEtiquetas.getContentPane());
        jDialogEtiquetas.getContentPane().setLayout(jDialogEtiquetasLayout);
        jDialogEtiquetasLayout.setHorizontalGroup(
            jDialogEtiquetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogEtiquetasLayout.setVerticalGroup(
            jDialogEtiquetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jMenuItemCopiar.setText("Copiar (Ctlr+C)");
        jPopupMenuCopiarPegar.add(jMenuItemCopiar);

        jMenuItemPegar.setText("Pegar (Ctlr+V)");
        jPopupMenuCopiarPegar.add(jMenuItemPegar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Farmacotecnia->Hojas de elaboración");
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(900, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBarProductos.setBackground(new java.awt.Color(193, 115, 115));
        jToolBarProductos.setFloatable(false);
        jToolBarProductos.setRollover(true);

        jButtonPrimeroFichasDeMedicamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/flechaPrimero.png"))); // NOI18N
        jButtonPrimeroFichasDeMedicamentos.setToolTipText("Se coloca en el primer medicamento");
        jButtonPrimeroFichasDeMedicamentos.setEnabled(false);
        jButtonPrimeroFichasDeMedicamentos.setFocusable(false);
        jButtonPrimeroFichasDeMedicamentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPrimeroFichasDeMedicamentos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarProductos.add(jButtonPrimeroFichasDeMedicamentos);

        jButtonAtrasFichasDeMedicamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/flechaIzquierda.png"))); // NOI18N
        jButtonAtrasFichasDeMedicamentos.setToolTipText("Se coloca en el medicamento anterior");
        jButtonAtrasFichasDeMedicamentos.setEnabled(false);
        jButtonAtrasFichasDeMedicamentos.setFocusable(false);
        jButtonAtrasFichasDeMedicamentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAtrasFichasDeMedicamentos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarProductos.add(jButtonAtrasFichasDeMedicamentos);
        jToolBarProductos.add(jSeparator3);

        jLabelPosicionFichasDeMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelPosicionFichasDeMedicamentos.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPosicionFichasDeMedicamentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPosicionFichasDeMedicamentos.setText("Producto 200 de 300");
        jLabelPosicionFichasDeMedicamentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToolBarProductos.add(jLabelPosicionFichasDeMedicamentos);
        jToolBarProductos.add(jSeparator4);

        jButtonSiguienteFichasDeMedicamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/flechaDerecha.png"))); // NOI18N
        jButtonSiguienteFichasDeMedicamentos.setToolTipText("Se coloca en el medicamento siguiente");
        jButtonSiguienteFichasDeMedicamentos.setFocusable(false);
        jButtonSiguienteFichasDeMedicamentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSiguienteFichasDeMedicamentos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarProductos.add(jButtonSiguienteFichasDeMedicamentos);

        jButtonUltimoFichasDeMedicamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/flechaUltimo.png"))); // NOI18N
        jButtonUltimoFichasDeMedicamentos.setToolTipText("Se coloca en el último medicamento");
        jButtonUltimoFichasDeMedicamentos.setFocusable(false);
        jButtonUltimoFichasDeMedicamentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUltimoFichasDeMedicamentos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarProductos.add(jButtonUltimoFichasDeMedicamentos);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jSeparator2.setPreferredSize(new java.awt.Dimension(6, 1));
        jToolBarProductos.add(jSeparator2);

        jButtonNuevoFichasDeMedicamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/facturaAgregar.png"))); // NOI18N
        jButtonNuevoFichasDeMedicamentos.setToolTipText("Crea un nuevo medicamento");
        jButtonNuevoFichasDeMedicamentos.setFocusable(false);
        jButtonNuevoFichasDeMedicamentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevoFichasDeMedicamentos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarProductos.add(jButtonNuevoFichasDeMedicamentos);

        jButtonModificarFichasDeMedicamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/facturaEditar.png"))); // NOI18N
        jButtonModificarFichasDeMedicamentos.setToolTipText("Permite modificar el medicamento actual, pulsar los botones de aceptar o cancelar situados al final de la sección datos medicamento para salir del modo de edición");
        jButtonModificarFichasDeMedicamentos.setFocusable(false);
        jButtonModificarFichasDeMedicamentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonModificarFichasDeMedicamentos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarProductos.add(jButtonModificarFichasDeMedicamentos);

        jButtonEliminarFichasDeMedicamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/facturaBorrar.png"))); // NOI18N
        jButtonEliminarFichasDeMedicamentos.setToolTipText("Elimina el medicamento actual");
        jButtonEliminarFichasDeMedicamentos.setFocusable(false);
        jButtonEliminarFichasDeMedicamentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEliminarFichasDeMedicamentos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarProductos.add(jButtonEliminarFichasDeMedicamentos);

        jButtonMostrarEtiqueta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/etiqueta.png"))); // NOI18N
        jButtonMostrarEtiqueta.setToolTipText("Muestra las etiqueta relacionadas con este medicamento o crea una nueva etiqueta para este medicamento");
        jButtonMostrarEtiqueta.setFocusable(false);
        jButtonMostrarEtiqueta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonMostrarEtiqueta.setMaximumSize(new java.awt.Dimension(55, 55));
        jButtonMostrarEtiqueta.setMinimumSize(new java.awt.Dimension(55, 55));
        jButtonMostrarEtiqueta.setPreferredSize(new java.awt.Dimension(55, 55));
        jButtonMostrarEtiqueta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarProductos.add(jButtonMostrarEtiqueta);

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/sacyl/caza/farmacia/resources/facturaImprimir.png"))); // NOI18N
        jButtonImprimir.setToolTipText("Imprime la hoja de elaboración del medicamento actual");
        jButtonImprimir.setFocusable(false);
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarProductos.add(jButtonImprimir);

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelAceptarCancelar.setBackground(new java.awt.Color(245, 83, 83));

        jButtonAceptarFichasDeMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAceptarFichasDeMedicamentos.setMnemonic('A');
        jButtonAceptarFichasDeMedicamentos.setText("Aceptar");

        jButtonCancelarFichasDeMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonCancelarFichasDeMedicamentos.setMnemonic('C');
        jButtonCancelarFichasDeMedicamentos.setText("Cancelar");

        javax.swing.GroupLayout jPanelAceptarCancelarLayout = new javax.swing.GroupLayout(jPanelAceptarCancelar);
        jPanelAceptarCancelar.setLayout(jPanelAceptarCancelarLayout);
        jPanelAceptarCancelarLayout.setHorizontalGroup(
            jPanelAceptarCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAceptarCancelarLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButtonAceptarFichasDeMedicamentos)
                .addGap(52, 52, 52)
                .addComponent(jButtonCancelarFichasDeMedicamentos)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanelAceptarCancelarLayout.setVerticalGroup(
            jPanelAceptarCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAceptarCancelarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAceptarCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAceptarFichasDeMedicamentos)
                    .addComponent(jButtonCancelarFichasDeMedicamentos))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanelAceptarCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 310, 50));
        jLayeredPane1.setLayer(jPanelAceptarCancelar, javax.swing.JLayeredPane.DRAG_LAYER);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(951, 2050));

        jPanel1.setBackground(new java.awt.Color(255, 223, 204));
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(932, 2050));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelListaFichasDeMedicamentos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelListaFichasDeMedicamentos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2), "Lista Medicamentos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), java.awt.Color.black)); // NOI18N

        jListFichasDeMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jListFichasDeMedicamentos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPaneListaMedicamentos.setViewportView(jListFichasDeMedicamentos);

        javax.swing.GroupLayout jPanelListaFichasDeMedicamentosLayout = new javax.swing.GroupLayout(jPanelListaFichasDeMedicamentos);
        jPanelListaFichasDeMedicamentos.setLayout(jPanelListaFichasDeMedicamentosLayout);
        jPanelListaFichasDeMedicamentosLayout.setHorizontalGroup(
            jPanelListaFichasDeMedicamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListaFichasDeMedicamentosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneListaMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanelListaFichasDeMedicamentosLayout.setVerticalGroup(
            jPanelListaFichasDeMedicamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListaFichasDeMedicamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneListaMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelListaFichasDeMedicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 77, -1, -1));

        jPanelEdicionDatosFichasDeMedicamentos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEdicionDatosFichasDeMedicamentos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2), "Datos medicamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanelEdicionDatosFichasDeMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanelEdicionDatosFichasDeMedicamentos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Id:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Medicamento:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 65, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Veredicto:");
        jLabel4.setToolTipText("1, para hacer; 0, no se hace");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 155, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Observaciones veredicto:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 193, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Procedimiento:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 298, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Observaciones personales:");
        jLabel7.setToolTipText("No salen en la hoja de elaboración");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 1136, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Estabilidad:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 656, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Via de administración:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 780, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Datos organolepsis:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 847, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tipo:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 888, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("E.D.O.");
        jLabel12.setToolTipText("¿Tiene algún excipiente de declaración obligatoria?");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 944, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Para etiqueta:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 982, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Observaciones para elaboración:");
        jLabel14.setToolTipText("Para que salga en las observaciones de la hoja de elaboración");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 502, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Origen:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 1307, -1, -1));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextAreaMedicamento.setEditable(false);
        jTextAreaMedicamento.setColumns(20);
        jTextAreaMedicamento.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextAreaMedicamento.setLineWrap(true);
        jTextAreaMedicamento.setRows(2);
        jTextAreaMedicamento.setText("\n");
        jTextAreaMedicamento.setWrapStyleWord(true);
        jTextAreaMedicamento.setComponentPopupMenu(jPopupMenuCopiarPegar);
        jScrollPane4.setViewportView(jTextAreaMedicamento);

        jPanelEdicionDatosFichasDeMedicamentos.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 88, 354, -1));

        jLabelIdFichasDeMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelIdFichasDeMedicamentos.setText("jLabel1");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabelIdFichasDeMedicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 30, -1, -1));

        jComboBoxVeredicto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxVeredicto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));
        jComboBoxVeredicto.setToolTipText("1, para hacer; 0, no se hace");
        jComboBoxVeredicto.setEnabled(false);
        jPanelEdicionDatosFichasDeMedicamentos.add(jComboBoxVeredicto, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 152, 75, -1));

        jTextFieldObservacionesVeredicto.setEditable(false);
        jTextFieldObservacionesVeredicto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldObservacionesVeredicto.setText("jTextField1");
        jPanelEdicionDatosFichasDeMedicamentos.add(jTextFieldObservacionesVeredicto, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 216, 354, -1));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextAreaProcedimiento.setEditable(false);
        jTextAreaProcedimiento.setColumns(20);
        jTextAreaProcedimiento.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextAreaProcedimiento.setLineWrap(true);
        jTextAreaProcedimiento.setRows(7);
        jTextAreaProcedimiento.setText("  1.- Se pesa la cantidad correspondiente de ác. tánico.\n\n 2.- Se mezcla con 500 ml de B.\n\n 3.- Se agita con agitador mecánico.\n\n 4.- Se filtra.\n\n 5.- Se envasa con el resto de B.");
        jTextAreaProcedimiento.setWrapStyleWord(true);
        jTextAreaProcedimiento.setComponentPopupMenu(jPopupMenuCopiarPegar);
        jScrollPane3.setViewportView(jTextAreaProcedimiento);

        jPanelEdicionDatosFichasDeMedicamentos.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 321, 354, -1));

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextAreaObservaciones.setEditable(false);
        jTextAreaObservaciones.setColumns(20);
        jTextAreaObservaciones.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextAreaObservaciones.setLineWrap(true);
        jTextAreaObservaciones.setRows(6);
        jTextAreaObservaciones.setToolTipText("No salen en la hoja de elaboración");
        jTextAreaObservaciones.setWrapStyleWord(true);
        jTextAreaObservaciones.setComponentPopupMenu(jPopupMenuCopiarPegar);
        jScrollPane5.setViewportView(jTextAreaObservaciones);

        jPanelEdicionDatosFichasDeMedicamentos.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 1159, 364, -1));

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextAreaEstabilidad.setEditable(false);
        jTextAreaEstabilidad.setColumns(20);
        jTextAreaEstabilidad.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextAreaEstabilidad.setLineWrap(true);
        jTextAreaEstabilidad.setRows(3);
        jTextAreaEstabilidad.setWrapStyleWord(true);
        jTextAreaEstabilidad.setComponentPopupMenu(jPopupMenuCopiarPegar);
        jScrollPane6.setViewportView(jTextAreaEstabilidad);

        jPanelEdicionDatosFichasDeMedicamentos.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 679, 354, -1));

        jComboBoxViaAdministracion.setEditable(true);
        jComboBoxViaAdministracion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxViaAdministracion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "tópica", "oral", "bucal", "rectal", "oftalmica", "epidural", "subcutánea", "intradural", "inhalatoria", "vesical", "intracamerular", "intravítrea", "subconjuntival", "desoclusion catéter hemodiálisis", "intradérmica" }));
        jComboBoxViaAdministracion.setEnabled(false);
        jPanelEdicionDatosFichasDeMedicamentos.add(jComboBoxViaAdministracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 803, 354, -1));

        jTextFieldDatosOrganolepsis.setEditable(false);
        jTextFieldDatosOrganolepsis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldDatosOrganolepsis.setText("cuasi preparado oficinal");
        jPanelEdicionDatosFichasDeMedicamentos.add(jTextFieldDatosOrganolepsis, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 844, 226, -1));

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextAreaParaEtiqueta.setEditable(false);
        jTextAreaParaEtiqueta.setColumns(20);
        jTextAreaParaEtiqueta.setLineWrap(true);
        jTextAreaParaEtiqueta.setRows(5);
        jTextAreaParaEtiqueta.setWrapStyleWord(true);
        jTextAreaParaEtiqueta.setComponentPopupMenu(jPopupMenuCopiarPegar);
        jScrollPane8.setViewportView(jTextAreaParaEtiqueta);

        jPanelEdicionDatosFichasDeMedicamentos.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 1005, 364, -1));

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextAreaObservacionesElaboracion.setEditable(false);
        jTextAreaObservacionesElaboracion.setColumns(20);
        jTextAreaObservacionesElaboracion.setLineWrap(true);
        jTextAreaObservacionesElaboracion.setRows(5);
        jTextAreaObservacionesElaboracion.setToolTipText("Para que salga en las observaciones de la hoja de elaboración");
        jTextAreaObservacionesElaboracion.setWrapStyleWord(true);
        jTextAreaObservacionesElaboracion.setComponentPopupMenu(jPopupMenuCopiarPegar);
        jScrollPane7.setViewportView(jTextAreaObservacionesElaboracion);

        jPanelEdicionDatosFichasDeMedicamentos.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 525, 354, -1));

        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextAreaOrigen.setEditable(false);
        jTextAreaOrigen.setColumns(20);
        jTextAreaOrigen.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextAreaOrigen.setLineWrap(true);
        jTextAreaOrigen.setRows(5);
        jTextAreaOrigen.setWrapStyleWord(true);
        jTextAreaOrigen.setComponentPopupMenu(jPopupMenuCopiarPegar);
        jScrollPane9.setViewportView(jTextAreaOrigen);

        jPanelEdicionDatosFichasDeMedicamentos.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 1330, 364, -1));

        jComboBoxEDO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxEDO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "cloruro de benzalconio", "deriv del ac. Borico", "lidocaina", "glute", "butilhidroxianisol", "alcohol bencílico", "lactosa", "cremofor (ac ricino polietoxilado)", "almidón de trigo", "sacarosa", "butilhidroxitolueno", "sulfitos", "tartracina", "glucosa", "fructosa", "aspartamo", "alcohol etílico (etanol)", "sales de fenilmercurio", "deriv del ac. Benzoico" }));
        jComboBoxEDO.setToolTipText("¿Tiene algún excipiente de declaración obligatoria?");
        jComboBoxEDO.setEnabled(false);
        jPanelEdicionDatosFichasDeMedicamentos.add(jComboBoxEDO, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 941, 306, -1));

        jComboBoxTipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "preparado oficinal", "F.M Tipificada", "F.M", "cuasi FMT", "cuasi F.M", "solo para etiq", "para laboratorio", "antisepticos", "cuasi preparado oficinal" }));
        jComboBoxTipo.setEnabled(false);
        jPanelEdicionDatosFichasDeMedicamentos.add(jComboBoxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 885, 315, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Elaborado por:");
        jPanelEdicionDatosFichasDeMedicamentos.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 260, -1, -1));

        jComboBoxElaboradoPor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxElaboradoPor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "famaceutico", "auxiliar", "enfermera" }));
        jComboBoxElaboradoPor.setEnabled(false);
        jPanelEdicionDatosFichasDeMedicamentos.add(jComboBoxElaboradoPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 257, 265, -1));

        jPanel1.add(jPanelEdicionDatosFichasDeMedicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 77, 446, 1532));

        jPanelProductosBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jPanelProductosBuscar.setMaximumSize(new java.awt.Dimension(733, 48));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Buscar por:");

        jComboBoxBuscarFichasDeMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxBuscarFichasDeMedicamentos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "Producto que contiene" }));
        jComboBoxBuscarFichasDeMedicamentos.setToolTipText("Seleccionar por que criterio buscar");

        jTextFieldBuscarFichasDeMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldBuscarFichasDeMedicamentos.setToolTipText("Introducir el texto de guia para buscar");

        jButtonBuscarFichasDeMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonBuscarFichasDeMedicamentos.setMnemonic('B');
        jButtonBuscarFichasDeMedicamentos.setText("Buscar");
        jButtonBuscarFichasDeMedicamentos.setToolTipText("Buscar productos que coincidan con los criterios elegidos");

        jButtonQuitarBuscarFichasDeMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonQuitarBuscarFichasDeMedicamentos.setMnemonic('Q');
        jButtonQuitarBuscarFichasDeMedicamentos.setText("Quitar búsqueda");
        jButtonQuitarBuscarFichasDeMedicamentos.setToolTipText("Volver a mostrar todos los datos");

        jCheckBoxMostrarTodo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxMostrarTodo.setText("Mostrar todo");
        jCheckBoxMostrarTodo.setToolTipText("Si se selecciona muestra todas las fichas de medicamentos, sino sólo se muestran las que tengan veredicto 1");

        javax.swing.GroupLayout jPanelProductosBuscarLayout = new javax.swing.GroupLayout(jPanelProductosBuscar);
        jPanelProductosBuscar.setLayout(jPanelProductosBuscarLayout);
        jPanelProductosBuscarLayout.setHorizontalGroup(
            jPanelProductosBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductosBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxBuscarFichasDeMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldBuscarFichasDeMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonBuscarFichasDeMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonQuitarBuscarFichasDeMedicamentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxMostrarTodo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelProductosBuscarLayout.setVerticalGroup(
            jPanelProductosBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductosBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProductosBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jComboBoxBuscarFichasDeMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBuscarFichasDeMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarFichasDeMedicamentos)
                    .addComponent(jButtonQuitarBuscarFichasDeMedicamentos)
                    .addComponent(jCheckBoxMostrarTodo))
                .addContainerGap())
        );

        jPanel1.add(jPanelProductosBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 11, 871, -1));

        jPanelMaquinaria.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMaquinaria.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2), "Maquinaria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jTableMaquinaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableMaquinaria.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane10.setViewportView(jTableMaquinaria);

        jButtonAddMaquinaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAddMaquinaria.setText("Añadir");

        jButtonEliminarMaquinaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonEliminarMaquinaria.setText("Eliminar");

        javax.swing.GroupLayout jPanelMaquinariaLayout = new javax.swing.GroupLayout(jPanelMaquinaria);
        jPanelMaquinaria.setLayout(jPanelMaquinariaLayout);
        jPanelMaquinariaLayout.setHorizontalGroup(
            jPanelMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMaquinariaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMaquinariaLayout.createSequentialGroup()
                        .addComponent(jButtonAddMaquinaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEliminarMaquinaria)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMaquinariaLayout.setVerticalGroup(
            jPanelMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMaquinariaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanelMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddMaquinaria)
                    .addComponent(jButtonEliminarMaquinaria))
                .addGap(25, 25, 25))
        );

        jPanel1.add(jPanelMaquinaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 601, 398, -1));

        jPanelMaterialEnvasar.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMaterialEnvasar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2), "Material para envasar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanelMaterialEnvasar.setPreferredSize(new java.awt.Dimension(390, 279));

        jTableMaterialEnvasar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableMaterialEnvasar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane11.setViewportView(jTableMaterialEnvasar);

        jButtonAddMaterialEnvasar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAddMaterialEnvasar.setText("Añadir");

        jButtonEliminarMaterialEnvasar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonEliminarMaterialEnvasar.setText("Eliminar");

        javax.swing.GroupLayout jPanelMaterialEnvasarLayout = new javax.swing.GroupLayout(jPanelMaterialEnvasar);
        jPanelMaterialEnvasar.setLayout(jPanelMaterialEnvasarLayout);
        jPanelMaterialEnvasarLayout.setHorizontalGroup(
            jPanelMaterialEnvasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMaterialEnvasarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMaterialEnvasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMaterialEnvasarLayout.createSequentialGroup()
                        .addComponent(jButtonAddMaterialEnvasar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarMaterialEnvasar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMaterialEnvasarLayout.setVerticalGroup(
            jPanelMaterialEnvasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMaterialEnvasarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanelMaterialEnvasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddMaterialEnvasar)
                    .addComponent(jButtonEliminarMaterialEnvasar))
                .addGap(26, 26, 26))
        );

        jPanel1.add(jPanelMaterialEnvasar, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 947, 398, -1));

        jPanelMaterialElaborar.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMaterialElaborar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2), "Material para elaborar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanelMaterialElaborar.setPreferredSize(new java.awt.Dimension(390, 279));

        jTableMaterialElaborar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableMaterialElaborar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMaterialElaborar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane12.setViewportView(jTableMaterialElaborar);

        jButtonAddMaterialElaborar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAddMaterialElaborar.setText("Añadir");

        jButtonEliminarMaterialElaborar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonEliminarMaterialElaborar.setText("Eliminar");

        javax.swing.GroupLayout jPanelMaterialElaborarLayout = new javax.swing.GroupLayout(jPanelMaterialElaborar);
        jPanelMaterialElaborar.setLayout(jPanelMaterialElaborarLayout);
        jPanelMaterialElaborarLayout.setHorizontalGroup(
            jPanelMaterialElaborarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMaterialElaborarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMaterialElaborarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMaterialElaborarLayout.createSequentialGroup()
                        .addComponent(jButtonAddMaterialElaborar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarMaterialElaborar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMaterialElaborarLayout.setVerticalGroup(
            jPanelMaterialElaborarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMaterialElaborarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanelMaterialElaborarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddMaterialElaborar)
                    .addComponent(jButtonEliminarMaterialElaborar))
                .addGap(27, 27, 27))
        );

        jPanel1.add(jPanelMaterialElaborar, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 1300, 398, 309));

        jPanelProductos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2), "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jTableProductos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Productos", "Codigo referencia", "Cantidad", "Unidad", "Proveedor", "Lote", "Caducidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableProductosKeyReleased(evt);
            }
        });
        jScrollPane13.setViewportView(jTableProductos);
        if (jTableProductos.getColumnModel().getColumnCount() > 0) {
            jTableProductos.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTableProductos.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        jButtonAddProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAddProducto.setText("Añadir");

        jButtonModificarProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonModificarProducto.setText("Modificar");

        jButtonEliminarProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonEliminarProducto.setText("Eliminar");

        javax.swing.GroupLayout jPanelProductosLayout = new javax.swing.GroupLayout(jPanelProductos);
        jPanelProductos.setLayout(jPanelProductosLayout);
        jPanelProductosLayout.setHorizontalGroup(
            jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductosLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProductosLayout.createSequentialGroup()
                        .addComponent(jButtonAddProducto)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificarProducto)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarProducto))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelProductosLayout.setVerticalGroup(
            jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddProducto)
                    .addComponent(jButtonModificarProducto)
                    .addComponent(jButtonEliminarProducto))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 1673, 871, -1));

        jScrollPane1.setViewportView(jPanel1);

        jLayeredPane1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 953, 579));

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
        jMenu2.add(jSeparator15);

        jMenuItemAddMaquinaria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAddMaquinaria.setText("Añadir maquinaria");
        jMenu2.add(jMenuItemAddMaquinaria);

        jMenuItemAddMaterialElaborar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAddMaterialElaborar.setText("Añadir material para elaborar");
        jMenu2.add(jMenuItemAddMaterialElaborar);

        jMenuItemAddMaterialEnvasar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAddMaterialEnvasar.setText("Añadir material para envasar");
        jMenu2.add(jMenuItemAddMaterialEnvasar);

        jMenuItemAddProducto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAddProducto.setText("Añadir producto");
        jMenu2.add(jMenuItemAddProducto);

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

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBarProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableProductosKeyReleased
       // System.out.println("jj");
    }//GEN-LAST:event_jTableProductosKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        /*
        
         */
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaHojasElaboracion dialog = new VentanaHojasElaboracion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonAceptarFichasDeMedicamentos;
    public javax.swing.JButton jButtonAddMaquinaria;
    public javax.swing.JButton jButtonAddMaterialElaborar;
    public javax.swing.JButton jButtonAddMaterialEnvasar;
    public javax.swing.JButton jButtonAddProducto;
    public javax.swing.JButton jButtonAtrasFichasDeMedicamentos;
    public javax.swing.JButton jButtonBuscarFichasDeMedicamentos;
    public javax.swing.JButton jButtonCancelarFichasDeMedicamentos;
    public javax.swing.JButton jButtonDialogEtiquetasNuevaEtiqueta;
    public javax.swing.JButton jButtonDialogEtiquetasVerEtiquetas;
    public javax.swing.JButton jButtonDialogEtiquetasVolver;
    public javax.swing.JButton jButtonDialogModificarProductoAceptar;
    public javax.swing.JButton jButtonDialogModificarProductoCancelar;
    public javax.swing.JButton jButtonEliminarFichasDeMedicamentos;
    public javax.swing.JButton jButtonEliminarMaquinaria;
    public javax.swing.JButton jButtonEliminarMaterialElaborar;
    public javax.swing.JButton jButtonEliminarMaterialEnvasar;
    public javax.swing.JButton jButtonEliminarProducto;
    public javax.swing.JButton jButtonImprimir;
    public javax.swing.JButton jButtonModificarFichasDeMedicamentos;
    public javax.swing.JButton jButtonModificarProducto;
    public javax.swing.JButton jButtonMostrarEtiqueta;
    public javax.swing.JButton jButtonNuevoFichasDeMedicamentos;
    public javax.swing.JButton jButtonPrimeroFichasDeMedicamentos;
    public javax.swing.JButton jButtonQuitarBuscarFichasDeMedicamentos;
    public javax.swing.JButton jButtonSiguienteFichasDeMedicamentos;
    public javax.swing.JButton jButtonUltimoFichasDeMedicamentos;
    public javax.swing.JCheckBox jCheckBoxCSP;
    public javax.swing.JCheckBox jCheckBoxMostrarTodo;
    public javax.swing.JComboBox jComboBoxBuscarFichasDeMedicamentos;
    public javax.swing.JComboBox jComboBoxDialogModificarProductoUnidades;
    public javax.swing.JComboBox jComboBoxEDO;
    public javax.swing.JComboBox jComboBoxElaboradoPor;
    public javax.swing.JComboBox jComboBoxTipo;
    public javax.swing.JComboBox jComboBoxVeredicto;
    public javax.swing.JComboBox jComboBoxViaAdministracion;
    public javax.swing.JDialog jDialogAddMaquinaria;
    public javax.swing.JDialog jDialogAddMaterialElaborar;
    public javax.swing.JDialog jDialogAddMaterialEnvasar;
    public javax.swing.JDialog jDialogAddProducto;
    public javax.swing.JDialog jDialogEtiquetas;
    public javax.swing.JDialog jDialogModificarProducto;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelDialogModificarProductoProductos;
    public javax.swing.JLabel jLabelIdFichasDeMedicamentos;
    public javax.swing.JLabel jLabelPosicionFichasDeMedicamentos;
    public javax.swing.JLayeredPane jLayeredPane1;
    public javax.swing.JList jListFichasDeMedicamentos;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItemAddMaquinaria;
    public javax.swing.JMenuItem jMenuItemAddMaterialElaborar;
    public javax.swing.JMenuItem jMenuItemAddMaterialEnvasar;
    public javax.swing.JMenuItem jMenuItemAddProducto;
    public javax.swing.JMenuItem jMenuItemArchivoNuevo;
    public javax.swing.JMenuItem jMenuItemArchivoSalir;
    public javax.swing.JMenuItem jMenuItemCopiar;
    public javax.swing.JMenuItem jMenuItemEditarEliminar;
    public javax.swing.JMenuItem jMenuItemEditarModificar;
    public javax.swing.JMenuItem jMenuItemPegar;
    public javax.swing.JMenuItem jMenuItemVerAnterior;
    public javax.swing.JMenuItem jMenuItemVerPrimero;
    public javax.swing.JMenuItem jMenuItemVerSiguiente;
    public javax.swing.JMenuItem jMenuItemVerUltimo;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanelAceptarCancelar;
    public javax.swing.JPanel jPanelEdicionDatosFichasDeMedicamentos;
    public javax.swing.JPanel jPanelListaFichasDeMedicamentos;
    public javax.swing.JPanel jPanelMaquinaria;
    public javax.swing.JPanel jPanelMaterialElaborar;
    public javax.swing.JPanel jPanelMaterialEnvasar;
    public javax.swing.JPanel jPanelProductos;
    public javax.swing.JPanel jPanelProductosBuscar;
    public javax.swing.JPopupMenu jPopupMenuCopiarPegar;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane10;
    public javax.swing.JScrollPane jScrollPane11;
    public javax.swing.JScrollPane jScrollPane12;
    public javax.swing.JScrollPane jScrollPane13;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JScrollPane jScrollPane5;
    public javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JScrollPane jScrollPane8;
    public javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JScrollPane jScrollPaneListaMedicamentos;
    public javax.swing.JPopupMenu.Separator jSeparator1;
    public javax.swing.JPopupMenu.Separator jSeparator14;
    public javax.swing.JPopupMenu.Separator jSeparator15;
    public javax.swing.JToolBar.Separator jSeparator2;
    public javax.swing.JToolBar.Separator jSeparator3;
    public javax.swing.JToolBar.Separator jSeparator4;
    public javax.swing.JTable jTableMaquinaria;
    public javax.swing.JTable jTableMaterialElaborar;
    public javax.swing.JTable jTableMaterialEnvasar;
    public javax.swing.JTable jTableProductos;
    public javax.swing.JTextArea jTextAreaEstabilidad;
    public javax.swing.JTextArea jTextAreaMedicamento;
    public javax.swing.JTextArea jTextAreaObservaciones;
    public javax.swing.JTextArea jTextAreaObservacionesElaboracion;
    public javax.swing.JTextArea jTextAreaOrigen;
    public javax.swing.JTextArea jTextAreaParaEtiqueta;
    public javax.swing.JTextArea jTextAreaProcedimiento;
    public javax.swing.JTextField jTextFieldBuscarFichasDeMedicamentos;
    public javax.swing.JTextField jTextFieldDatosOrganolepsis;
    public javax.swing.JTextField jTextFieldDialogModificarProductoCantidad;
    public javax.swing.JTextField jTextFieldObservacionesVeredicto;
    public javax.swing.JToolBar jToolBarProductos;
    // End of variables declaration//GEN-END:variables
}
