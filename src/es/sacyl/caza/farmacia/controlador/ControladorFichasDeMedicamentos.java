package es.sacyl.caza.farmacia.controlador;

import es.sacyl.caza.farmacia.modelo.FichasDeMedicamentosDAO;
import es.sacyl.caza.farmacia.modelo.FichasDeMedicamentosDataSource;
import es.sacyl.caza.farmacia.modelo.clases.FichasDeMedicamentos;
import es.sacyl.caza.farmacia.modelo.clases.MaquinariaUnion;
import es.sacyl.caza.farmacia.modelo.clases.MaterialDeEnvasadoUnion;
import es.sacyl.caza.farmacia.modelo.clases.MaterialParaElaborarUnion;
import es.sacyl.caza.farmacia.modelo.clases.ProductosUnion;
import es.sacyl.caza.farmacia.vista.VentanaEtiquetas;
import es.sacyl.caza.farmacia.vista.VentanaHojasElaboracion;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;

/**
 *
 * @author Enrique Martín Arenal
 */
public class ControladorFichasDeMedicamentos {

    private VentanaHojasElaboracion vista;
    private ArrayList<FichasDeMedicamentos> listaFichasDeMedicamentos;
    private ArrayList<MaquinariaUnion> listaMaquinariaMedicamento;
    private ArrayList<MaterialDeEnvasadoUnion> listaMaterialEnvasadoMedicamento;
    private ArrayList<MaterialParaElaborarUnion> listaMaterialElaborarMedicamento;
    private ArrayList<ProductosUnion> listaProductosMedicamento;
    private String[] fichasDeMedicamentosJList;
    private FichasDeMedicamentosDAO modelo;
    private boolean nuevoPulsado = false;
    private boolean nuevoProducto = false;
    private boolean rellenando = false;

    public ControladorFichasDeMedicamentos(VentanaHojasElaboracion vista) {
        this.vista = vista;
        init();
        initListeners();
    }

    //Inicialización de los objetos necesarios para acceder y mostrar los datos
    private void init() {
        soloEscribirNumerosJTextField(vista.jTextFieldDialogModificarProductoCantidad);
        modelo = new FichasDeMedicamentosDAO();
        listaFichasDeMedicamentos = modelo.selectAllMedicamentosVeredicto();
        //Hay que rellenar las tablas con la sesion todavia abierta para evitar un LazyInitialitationError
        Session sesion = modelo.abrirSesion();
        listaMaterialElaborarMedicamento = modelo.selectMaterialElaborarDeMedicamento(listaFichasDeMedicamentos.get(0).getIdMedicamento(), sesion);
        listaMaquinariaMedicamento = modelo.selectMaquinariaDeMedicamento(listaFichasDeMedicamentos.get(0).getIdMedicamento(), sesion);
        listaMaterialEnvasadoMedicamento = modelo.selectMaterialEnvasarDeMedicamento(listaFichasDeMedicamentos.get(0).getIdMedicamento(), sesion);
        listaProductosMedicamento = modelo.selectProductosDeMedicamento(listaFichasDeMedicamentos.get(0).getIdMedicamento(), sesion);
        rellenarJListFichasDeMedicamentos();
        vista.jListFichasDeMedicamentos.setSelectedIndex(0);
        actualizarPosicion();
        mostrarDatosFichasDeMedicamentos();
        rellenarTablaMaterialElaborar();
        rellenarTablaMaquinaria();
        rellenarTablaMaterialEnvasar();
        rellenarTablaProductos();
        modelo.cerrarSesion(sesion);

    }

    //Añade los listener de los eventos a los componentes
    private void initListeners() {
        vista.jListFichasDeMedicamentos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cambiarPosicionJListFichasDeMedicamentos();
            }
        });
        vista.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cerrarVentana();
            }
        });
        vista.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cerrarVentana();
            }
        });

        vista.jMenuItemArchivoSalir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarVentana();
            }
        });
        vista.jMenuItemArchivoNuevo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonNuevoMedicamento();
            }
        });
        vista.jMenuItemEditarEliminar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarMedicamento();
            }
        });
        vista.jMenuItemEditarModificar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarMedicamento();
            }
        });
        vista.jMenuItemVerAnterior.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseAnterior();
            }
        });
        vista.jMenuItemVerPrimero.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoversePrimero();
            }
        });
        vista.jMenuItemVerSiguiente.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseSiguiente();
            }
        });
        vista.jMenuItemVerUltimo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseUltimo();
            }
        });
        vista.jMenuItemAddMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarAddMaquinaria();
            }
        });
        vista.jMenuItemAddMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarAddMaterialElaborar();
            }
        });
        vista.jMenuItemAddMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarAddMaterialEnvasar();
            }
        });
        vista.jMenuItemAddProducto.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarAddProductos();
            }
        });

        vista.jButtonSiguienteFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseSiguiente();
            }
        });
        vista.jButtonAtrasFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseAnterior();
            }
        });
        vista.jButtonPrimeroFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoversePrimero();
            }
        });
        vista.jButtonUltimoFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseUltimo();
            }
        });
        vista.jButtonModificarFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarMedicamento();
            }
        });
        vista.jButtonAceptarFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarPulsado();
            }
        });
        vista.jButtonCancelarFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarPulsado();
            }
        });
        vista.jButtonNuevoFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonNuevoMedicamento();
            }
        });
        vista.jButtonBuscarFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscar();
            }
        });
        vista.jButtonQuitarBuscarFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargarDatosQuitarBuscar();
            }
        });
        vista.jButtonEliminarFichasDeMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarMedicamento();
            }
        });
        vista.jButtonAddMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarAddMaquinaria();
            }
        });
        vista.panelAddMaquinaria.jButtonAddMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaquinariaMedicamento();
            }
        });
        vista.panelAddMaquinaria.jButtonCancelarAddMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vista.jDialogAddMaquinaria.setVisible(false);
            }
        });
        vista.jButtonAddMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarAddMaterialElaborar();
            }
        });
        vista.panelAddMaterialElaboracion.jButtonAddMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaterialElaborarMedicamento();
            }
        });
        vista.panelAddMaterialElaboracion.jButtonCancelarAddMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vista.jDialogAddMaterialElaborar.setVisible(false);
            }
        });
        vista.jButtonAddMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarAddMaterialEnvasar();
            }
        });
        vista.panelAddMaterialEnvasar.jButtonAddMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaterialEnvasarMedicamento();
            }
        });
        vista.panelAddMaterialEnvasar.jButtonCancelarAddMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vista.jDialogAddMaterialEnvasar.setVisible(false);
            }
        });
        vista.jButtonAddProducto.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarAddProductos();
            }
        });
        vista.panelAddProductos.jButtonAddProducto.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductosMedicamento();
            }
        });
        vista.panelAddProductos.jButtonCancelarAddProducto.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vista.jDialogAddProducto.setVisible(false);
            }
        });
        vista.jButtonDialogModificarProductoCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vista.jDialogModificarProducto.setVisible(false);
            }
        });
        vista.jButtonDialogModificarProductoAceptar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarProductoMedicamento();
            }
        });
        vista.jButtonModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarProducto();
            }
        });
        vista.jButtonEliminarMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarMaquinaria();
            }
        });
        vista.jButtonEliminarMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarMaterialElaborar();
            }
        });
        vista.jButtonEliminarMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarMaterialEnvasar();
            }
        });
        vista.jButtonEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarProducto();
            }
        });
        vista.jTableProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                    pulsarBotonEliminarProducto();
                }

            }
        });
        vista.jTableMaquinaria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                    pulsarBotonEliminarMaquinaria();
                }

            }
        });
        vista.jTableMaterialElaborar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                    pulsarBotonEliminarMaterialElaborar();
                }

            }
        });
        vista.jTableMaterialEnvasar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                    pulsarBotonEliminarMaterialEnvasar();
                }

            }
        });
        vista.jButtonMostrarEtiqueta.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEtiquetas();
            }
        });
        vista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cerrarVentana();
            }
        });
        vista.jButtonDialogEtiquetasNuevaEtiqueta.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonDialogEtiquetasNuevaEtiqueta();
            }
        });
        vista.jButtonDialogEtiquetasVerEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonDialogEtiquetasVerEtiqueta();
            }
        });
        vista.jButtonDialogEtiquetasVolver.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vista.jDialogEtiquetas.setVisible(false);
            }
        });
        vista.jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonImprimir();
            }
        });
        vista.jCheckBoxMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarCheckMostrarTodo();
            }
        });
        vista.jTextFieldBuscarFichasDeMedicamentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    botonBuscar();
                }
            }
        });
        vista.jMenuItemCopiar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiar();
            }
        });
        vista.jMenuItemPegar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pegar();
            }
        });

    }

    public void copiar() {
        if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEstabilidad)) {
            vista.jTextAreaEstabilidad.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaMedicamento)) {
            vista.jTextAreaMedicamento.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaObservaciones)) {
            vista.jTextAreaObservaciones.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaObservacionesElaboracion)) {
            vista.jTextAreaObservacionesElaboracion.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaOrigen)) {
            vista.jTextAreaOrigen.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaParaEtiqueta)) {
            vista.jTextAreaParaEtiqueta.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaProcedimiento)) {
            vista.jTextAreaProcedimiento.copy();
        }
    }

    //Acción de pegar
    public void pegar() {
        //solo se puede pegar si se estan editando los datos
        if (vista.jButtonAceptarFichasDeMedicamentos.isVisible()) {
            //Para pegar hacer un switch con todos los componentes en donde se puede pegar

            if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEstabilidad)) {
                vista.jTextAreaEstabilidad.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaMedicamento)) {
                vista.jTextAreaMedicamento.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaObservaciones)) {
                vista.jTextAreaObservaciones.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaObservacionesElaboracion)) {
                vista.jTextAreaObservacionesElaboracion.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaOrigen)) {
                vista.jTextAreaOrigen.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaParaEtiqueta)) {
                vista.jTextAreaParaEtiqueta.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaProcedimiento)) {
                vista.jTextAreaProcedimiento.paste();
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Para poder pegar hay que pulsar antes el botón de modificar los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Vacia la memoria antes de cerrar la ventana
    public void cerrarVentana() {
        vista.dispose();
    }

    //Se usa para permitir únicamente la escritura de número en el jTextField que se requiera
    public void soloEscribirNumerosJTextField(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();

                if (!Character.isDigit(caracter)) {
                    e.consume();
                }
            }
        });
    }

    //usado para rellenar la tabla del material para elaborar
    public void rellenarTablaMaterialElaborar() {
        String[][] lista = new String[listaMaterialElaborarMedicamento.size()][1];
        for (int i = 0; i < listaMaterialElaborarMedicamento.size(); i++) {
            lista[i][0] = listaMaterialElaborarMedicamento.get(i).getMaterialParaElaborar().getMaterial();
        }
        vista.jTableMaterialElaborar.setModel(new javax.swing.table.DefaultTableModel(
                lista,
                new String[]{
                    "Material para elaborar"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    //usado para rellenar la tabla del material para envasar
    public void rellenarTablaMaterialEnvasar() {
        String[][] lista = new String[listaMaterialEnvasadoMedicamento.size()][1];
        for (int i = 0; i < listaMaterialEnvasadoMedicamento.size(); i++) {
            lista[i][0] = listaMaterialEnvasadoMedicamento.get(i).getMaterialDeEnvasado().getParaEnvasar();
        }
        vista.jTableMaterialEnvasar.setModel(new javax.swing.table.DefaultTableModel(
                lista,
                new String[]{
                    "Material para envasar"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    //usado para rellenar la tabla de la maquinaria
    public void rellenarTablaMaquinaria() {
        String[][] lista = new String[listaMaquinariaMedicamento.size()][1];
        for (int i = 0; i < listaMaquinariaMedicamento.size(); i++) {
            lista[i][0] = listaMaquinariaMedicamento.get(i).getMaquinaria().getMaquinaria();
        }
        vista.jTableMaquinaria.setModel(new javax.swing.table.DefaultTableModel(
                lista,
                new String[]{
                    "Maquinaria"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    //Usado para rellenar la tabla de productos
    private void rellenarTablaProductos() {
        String[][] lista = new String[listaProductosMedicamento.size()][7];
        for (int i = 0; i < listaProductosMedicamento.size(); i++) {
            lista[i][0] = listaProductosMedicamento.get(i).getOrden();
            lista[i][1] = listaProductosMedicamento.get(i).getProductos().getComponente();
            lista[i][2] = listaProductosMedicamento.get(i).getProductos().getReferencia();
            lista[i][3] = listaProductosMedicamento.get(i).getCantidad();
            lista[i][4] = listaProductosMedicamento.get(i).getUnidades();

        }
        vista.jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
                lista,
                new String[]{
                    "Orden", "Productos", "Código referencia", "Cantidad", "Unidad", "Proveedor", "Lote", "Caducidad"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        if (vista.jTableProductos.getColumnModel().getColumnCount() > 0) {
            vista.jTableProductos.getColumnModel().getColumn(0).setPreferredWidth(50);
            vista.jTableProductos.getColumnModel().getColumn(1).setPreferredWidth(225);
            vista.jTableProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

    }

    //Llena el JListFichasDeMedicamentos con los nombres de los FichasDeMedicamentos existentes en la base de datos
    private void rellenarJListFichasDeMedicamentos() {
        rellenando = true;
        fichasDeMedicamentosJList = new String[listaFichasDeMedicamentos.size()];
        for (int i = 0; i < fichasDeMedicamentosJList.length; i++) {
            fichasDeMedicamentosJList[i] = listaFichasDeMedicamentos.get(i).getMedicamento();
        }
        vista.jListFichasDeMedicamentos.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return fichasDeMedicamentosJList.length;
            }

            @Override
            public Object getElementAt(int i) {
                return fichasDeMedicamentosJList[i];
            }
        });
        rellenando = false;
    }

    //Posición del medicamentos en el que estamos situados
    private void actualizarPosicion() {
        String pos = "Medicamento " + (vista.jListFichasDeMedicamentos.getSelectedIndex() + 1) + " de " + fichasDeMedicamentosJList.length;
        vista.jLabelPosicionFichasDeMedicamentos.setText(pos);
    }

    //Mostrar los datos del elemento seleccionado del jListFichasDeMedicamentos 
    private void mostrarDatosFichasDeMedicamentos() {
        if (vista.jListFichasDeMedicamentos.getSelectedIndex() > -1) {
            vista.jLabelIdFichasDeMedicamentos.setText(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento() + "");
            vista.jTextAreaMedicamento.setText(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getMedicamento());
            vista.jTextAreaEstabilidad.setText(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getEstabilidad());
            vista.jTextAreaObservacionesElaboracion.setText(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getObservacParaElaboracion());
            vista.jTextAreaObservaciones.setText(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getObservaciones());
            vista.jTextFieldDatosOrganolepsis.setText(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getOrganolepsisDatos());
            vista.jTextAreaOrigen.setText(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getOrigen());
            vista.jTextAreaParaEtiqueta.setText(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getParaEtiqueta());
            vista.jTextAreaProcedimiento.setText(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getProcedimiento());
            vista.jTextFieldObservacionesVeredicto.setText(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getVeredObservac());
            if (!nuevoPulsado) {
                if (listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getVeredicto() == 1) {
                    vista.jComboBoxVeredicto.setSelectedIndex(1);
                } else {
                    vista.jComboBoxVeredicto.setSelectedIndex(0);
                }
                //Los campos de los combobox que no tienen datos necesitan un tratamiento especial para poder mostrarse adecuadamente
                if (listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getAlgunEdo().length() > 1) {
                    vista.jComboBoxEDO.setSelectedItem(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getAlgunEdo());
                } else {
                    vista.jComboBoxEDO.setSelectedIndex(0);
                }
                if (listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getElaboradoPor().length() > 1) {
                    vista.jComboBoxElaboradoPor.setSelectedItem(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getElaboradoPor());
                } else {
                    vista.jComboBoxElaboradoPor.setSelectedIndex(0);
                }
                if (listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getTipo().length() > 1) {
                    vista.jComboBoxTipo.setSelectedItem(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getTipo());
                } else {
                    vista.jComboBoxTipo.setSelectedIndex(0);
                }
                if (listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getViaDeAdmon().length() > 1) {
                    vista.jComboBoxViaAdministracion.setSelectedItem(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getViaDeAdmon());
                } else {
                    vista.jComboBoxViaAdministracion.setSelectedIndex(0);
                }
            } else {
                //poner los combos sin nada seleccionado
                vista.jComboBoxVeredicto.setSelectedIndex(1);
                vista.jComboBoxEDO.setSelectedIndex(0);
                vista.jComboBoxElaboradoPor.setSelectedIndex(0);
                vista.jComboBoxTipo.setSelectedIndex(0);
                vista.jComboBoxViaAdministracion.setSelectedIndex(0);
            }

        }
    }

    //Recoger los datos de los controloes en el elemento seleccionado del jListFichasDeMedicamentos
    private void recogerDatosFichasDeMedicamentos() {
        if (vista.jComboBoxEDO.getSelectedItem() != null) {
            listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setAlgunEdo(vista.jComboBoxEDO.getSelectedItem().toString());
        } else {
            listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setAlgunEdo(" ");
        }
        if (vista.jComboBoxElaboradoPor.getSelectedItem() != null) {
            listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setElaboradoPor(vista.jComboBoxElaboradoPor.getSelectedItem().toString());
        } else {
            listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setElaboradoPor(" ");
        }
        if (vista.jComboBoxTipo.getSelectedItem() != null) {
            listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setTipo(vista.jComboBoxTipo.getSelectedItem().toString());
        } else {
            listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setTipo(" ");
        }
        if (vista.jComboBoxViaAdministracion.getSelectedItem() != null) {
            listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setViaDeAdmon(vista.jComboBoxViaAdministracion.getSelectedItem().toString());
        } else {
            listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setViaDeAdmon(" ");
        }

        listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setEstabilidad(vista.jTextAreaEstabilidad.getText());
        listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setMedicamento(vista.jTextAreaMedicamento.getText());
        listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setObservacParaElaboracion(vista.jTextAreaObservacionesElaboracion.getText());
        listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setObservaciones(vista.jTextAreaObservaciones.getText());
        listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setOrganolepsisDatos(vista.jTextFieldDatosOrganolepsis.getText());
        listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setOrigen(vista.jTextAreaOrigen.getText());
        listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setParaEtiqueta(vista.jTextAreaParaEtiqueta.getText());
        listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setProcedimiento(vista.jTextAreaProcedimiento.getText());

        listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setVeredObservac(vista.jTextFieldObservacionesVeredicto.getText());

        if (vista.jComboBoxVeredicto.getSelectedIndex() == 0) {
            listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setVeredicto(0);
        } else {
            listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).setVeredicto(1);
        }
    }

    // Activar/desactivar botones de siguiente,anterior,primero,último 
    private void controlarBotonesMoverse() {
        if (vista.jListFichasDeMedicamentos.getSelectedIndex() == 0) {
            vista.jButtonAtrasFichasDeMedicamentos.setEnabled(false);
            vista.jButtonPrimeroFichasDeMedicamentos.setEnabled(false);
        } else {
            vista.jButtonAtrasFichasDeMedicamentos.setEnabled(true);
            vista.jButtonPrimeroFichasDeMedicamentos.setEnabled(true);
        }
        if (vista.jListFichasDeMedicamentos.getSelectedIndex() == listaFichasDeMedicamentos.size() - 1) {
            vista.jButtonSiguienteFichasDeMedicamentos.setEnabled(false);
            vista.jButtonUltimoFichasDeMedicamentos.setEnabled(false);
        } else {
            vista.jButtonSiguienteFichasDeMedicamentos.setEnabled(true);
            vista.jButtonUltimoFichasDeMedicamentos.setEnabled(true);
        }
    }

    // Evento al cambiar de posición en el jListFichasDeMedicamentos
    private void cambiarPosicionJListFichasDeMedicamentos() {
        actualizarPosicion();
        controlarBotonesMoverse();
        mostrarDatosFichasDeMedicamentos();
        //Hay que rellenar las tablas con la sesion todavia abierta para evitar un LazyInitialitationError
        if (!rellenando) {
            Session sesion = modelo.abrirSesion();
            listaMaterialElaborarMedicamento = modelo.selectMaterialElaborarDeMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento(), sesion);
            listaMaquinariaMedicamento = modelo.selectMaquinariaDeMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento(), sesion);
            listaMaterialEnvasadoMedicamento = modelo.selectMaterialEnvasarDeMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento(), sesion);
            listaProductosMedicamento = modelo.selectProductosDeMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento(), sesion);
            rellenarTablaMaterialElaborar();
            rellenarTablaMaquinaria();
            rellenarTablaMaterialEnvasar();
            rellenarTablaProductos();
            modelo.cerrarSesion(sesion);
        }

    }

    //Moverse al siguiente medicamento de la lista
    protected void botonMoverseSiguiente() {
        vista.jListFichasDeMedicamentos.setSelectedIndex(vista.jListFichasDeMedicamentos.getSelectedIndex() + 1);
        if (vista.jScrollPaneListaMedicamentos.getVerticalScrollBar().getValue() - (vista.jListFichasDeMedicamentos.getSelectedIndex() * 19) < -380) {
            vista.jScrollPaneListaMedicamentos.getVerticalScrollBar().setValue(((vista.jListFichasDeMedicamentos.getSelectedIndex() * 19) - 380));
        }
    }

    //Moverse al anterior medicamento de la lista
    protected void botonMoverseAnterior() {
        vista.jListFichasDeMedicamentos.setSelectedIndex(vista.jListFichasDeMedicamentos.getSelectedIndex() - 1);
        if (vista.jScrollPaneListaMedicamentos.getVerticalScrollBar().getValue() - (vista.jListFichasDeMedicamentos.getSelectedIndex() * 19) > 0) {
            vista.jScrollPaneListaMedicamentos.getVerticalScrollBar().setValue(((vista.jListFichasDeMedicamentos.getSelectedIndex() * 19)));
        }
    }

    //Moverse al primer medicamento de la lista
    protected void botonMoversePrimero() {
        vista.jListFichasDeMedicamentos.setSelectedIndex(0);
        vista.jScrollPaneListaMedicamentos.getVerticalScrollBar().setValue(0);

    }

    //Moverse al último medicamento de la lista
    protected void botonMoverseUltimo() {
        vista.jListFichasDeMedicamentos.setSelectedIndex(listaFichasDeMedicamentos.size() - 1);
        vista.jScrollPaneListaMedicamentos.getVerticalScrollBar().setValue(vista.jListFichasDeMedicamentos.getSelectedIndex() * 19);

    }

    //Modificar un medicamento
    protected void pulsarBotonModificarMedicamento() {
        comenzarEdicion();
        nuevoPulsado = false;
    }

    //Nuevo medicamento
    protected void pulsarBotonNuevoMedicamento() {
        nuevoPulsado = true;

        FichasDeMedicamentos producto = new FichasDeMedicamentos();
        //ya se encarga el auto_increment de asignarle el id correcto
        producto.setIdMedicamento(0);
        listaFichasDeMedicamentos.add(producto);
        rellenarJListFichasDeMedicamentos();
        vista.jListFichasDeMedicamentos.setSelectedIndex(listaFichasDeMedicamentos.size() - 1);
        comenzarEdicion();
    }

    //Eliminar medicamento
    protected void pulsarBotonEliminarMedicamento() {
        if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el medicamento actual?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
            if (listaFichasDeMedicamentos.size() < 2) {
                JOptionPane.showMessageDialog(vista, "No se pueden eliminar todos los medicamentos visibles. \nPruebe a quitar algún filtro de busqueda", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                modelo.eliminarMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()));
                listaFichasDeMedicamentos.remove(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()));
                rellenarJListFichasDeMedicamentos();
                vista.jListFichasDeMedicamentos.setSelectedIndex(0);
            }
        }
    }

    // Habilitar controles para permitir la edición de los datos de un medicamento
    private void comenzarEdicion() {
        vista.jTextAreaMedicamento.setEditable(true);
        vista.jTextAreaEstabilidad.setEditable(true);
        vista.jTextAreaObservacionesElaboracion.setEditable(true);
        vista.jTextAreaObservaciones.setEditable(true);
        vista.jTextFieldDatosOrganolepsis.setEditable(true);
        vista.jTextAreaOrigen.setEditable(true);
        vista.jTextAreaParaEtiqueta.setEditable(true);
        vista.jTextAreaProcedimiento.setEditable(true);
        vista.jTextFieldObservacionesVeredicto.setEditable(true);
        vista.jComboBoxViaAdministracion.setEnabled(true);
        vista.jComboBoxVeredicto.setEnabled(true);
        vista.jComboBoxEDO.setEnabled(true);
        vista.jComboBoxElaboradoPor.setEnabled(true);
        vista.jComboBoxTipo.setEnabled(true);
        //No dejar manipular las cosas de otras tablas hasta que se termine de crear el nuevo medicamento o de modificar el actual
        vista.jButtonAddMaquinaria.setEnabled(false);
        vista.jButtonAddMaterialElaborar.setEnabled(false);
        vista.jButtonAddMaterialEnvasar.setEnabled(false);
        vista.jButtonAddProducto.setEnabled(false);
        vista.jButtonEliminarMaquinaria.setEnabled(false);
        vista.jButtonEliminarMaterialElaborar.setEnabled(false);
        vista.jButtonEliminarMaterialEnvasar.setEnabled(false);
        vista.jButtonEliminarProducto.setEnabled(false);
        vista.jButtonModificarProducto.setEnabled(false);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListFichasDeMedicamentos.setEnabled(false);
        vista.jButtonAtrasFichasDeMedicamentos.setEnabled(false);
        vista.jButtonEliminarFichasDeMedicamentos.setEnabled(false);
        vista.jButtonNuevoFichasDeMedicamentos.setEnabled(false);
        vista.jButtonPrimeroFichasDeMedicamentos.setEnabled(false);
        vista.jButtonSiguienteFichasDeMedicamentos.setEnabled(false);
        vista.jButtonUltimoFichasDeMedicamentos.setEnabled(false);
        vista.jButtonBuscarFichasDeMedicamentos.setEnabled(false);
        vista.jButtonQuitarBuscarFichasDeMedicamentos.setEnabled(false);
        vista.jTextFieldBuscarFichasDeMedicamentos.setEnabled(false);
        vista.jButtonImprimir.setEnabled(false);
        vista.jButtonMostrarEtiqueta.setEnabled(false);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosFichasDeMedicamentos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true), "Datos medicamento"));
        vista.jPanelListaFichasDeMedicamentos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 2, true), "Lista de medicamentos"));
        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jPanelAceptarCancelar.setVisible(true);

        //da el focus a la primera opción editable
        vista.jComboBoxVeredicto.transferFocusBackward();
    }

    //Pasos contrarios a comenzarEdicion()
    private void acabarEdicion() {
        vista.jTextAreaMedicamento.setEditable(false);
        vista.jTextAreaEstabilidad.setEditable(false);
        vista.jTextAreaObservacionesElaboracion.setEditable(false);
        vista.jTextAreaObservaciones.setEditable(false);
        vista.jTextFieldDatosOrganolepsis.setEditable(false);
        vista.jTextAreaOrigen.setEditable(false);
        vista.jTextAreaParaEtiqueta.setEditable(false);
        vista.jTextAreaProcedimiento.setEditable(false);
        vista.jTextFieldObservacionesVeredicto.setEditable(false);
        vista.jComboBoxViaAdministracion.setEnabled(false);
        vista.jComboBoxVeredicto.setEnabled(false);
        vista.jComboBoxEDO.setEnabled(false);
        vista.jComboBoxElaboradoPor.setEnabled(false);
        vista.jComboBoxTipo.setEnabled(false);
        vista.jButtonAddMaquinaria.setEnabled(true);
        vista.jButtonAddMaterialElaborar.setEnabled(true);
        vista.jButtonAddMaterialEnvasar.setEnabled(true);
        vista.jButtonAddProducto.setEnabled(true);
        vista.jButtonEliminarMaquinaria.setEnabled(true);
        vista.jButtonEliminarMaterialElaborar.setEnabled(true);
        vista.jButtonEliminarMaterialEnvasar.setEnabled(true);
        vista.jButtonEliminarProducto.setEnabled(true);
        vista.jButtonModificarProducto.setEnabled(true);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListFichasDeMedicamentos.setEnabled(true);
        vista.jButtonAtrasFichasDeMedicamentos.setEnabled(true);
        vista.jButtonEliminarFichasDeMedicamentos.setEnabled(true);
        vista.jButtonNuevoFichasDeMedicamentos.setEnabled(true);
        vista.jButtonPrimeroFichasDeMedicamentos.setEnabled(true);
        vista.jButtonSiguienteFichasDeMedicamentos.setEnabled(true);
        vista.jButtonUltimoFichasDeMedicamentos.setEnabled(true);
        vista.jButtonBuscarFichasDeMedicamentos.setEnabled(true);
        vista.jButtonQuitarBuscarFichasDeMedicamentos.setEnabled(true);
        vista.jTextFieldBuscarFichasDeMedicamentos.setEnabled(true);
        vista.jButtonImprimir.setEnabled(true);
        vista.jButtonMostrarEtiqueta.setEnabled(true);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosFichasDeMedicamentos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 2, true), "Datos medicamento"));
        vista.jPanelListaFichasDeMedicamentos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true), "Lista de medicamentos"));

        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jPanelAceptarCancelar.setVisible(false);
    }

    //Pulsar boton de aceptar
    public void botonAceptarPulsado() {
        if (comprobarCamposObligatorios()) {
            try {
                if (nuevoPulsado) {
                    recogerDatosFichasDeMedicamentos();
                    modelo.nuevoMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()));
                    acabarEdicion();
                    rellenarJListFichasDeMedicamentos();
                    vista.jListFichasDeMedicamentos.setSelectedIndex(listaFichasDeMedicamentos.size() - 1);

                } else {

                    recogerDatosFichasDeMedicamentos();
                    modelo.modificarMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()));
                    acabarEdicion();
                    int posicion = vista.jListFichasDeMedicamentos.getSelectedIndex();
                    rellenarJListFichasDeMedicamentos();
                    vista.jListFichasDeMedicamentos.setSelectedIndex(posicion);

                }
            } catch (org.hibernate.exception.DataException e) {
                JOptionPane.showMessageDialog(vista, "Uno de los campos es demasiado largo, por favor hágalo más corto.", "Error", JOptionPane.WARNING_MESSAGE);
            } catch (ConstraintViolationException e) {
                JOptionPane.showMessageDialog(vista, "El medicamento introducido ya existe, introduza otro", "Error", JOptionPane.WARNING_MESSAGE);
            }
            nuevoPulsado = false;
        }

    }

    //Pulsar boton de cancelar
    public void botonCancelarPulsado() {
        if (nuevoPulsado) {
            listaFichasDeMedicamentos.remove(listaFichasDeMedicamentos.size() - 1);
            acabarEdicion();
            rellenarJListFichasDeMedicamentos();
            vista.jListFichasDeMedicamentos.setSelectedIndex(0);

        } else {
            acabarEdicion();
            controlarBotonesMoverse();
            mostrarDatosFichasDeMedicamentos();
        }
        nuevoPulsado = false;
    }

    //Comprobar que los campos obligatorios no están vacios
    public boolean comprobarCamposObligatorios() {
        if (vista.jTextAreaMedicamento.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Los siguiente campos son obligatorios \n\t-Medicamento", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Realizar búsquedas
    public void botonBuscar() {
        if (vista.jComboBoxBuscarFichasDeMedicamentos.getSelectedIndex() == 0) {
            if (vista.jCheckBoxMostrarTodo.isSelected()) {
                listaFichasDeMedicamentos = modelo.selectMedicamentoPorNombre(vista.jTextFieldBuscarFichasDeMedicamentos.getText());
            } else {
                listaFichasDeMedicamentos = modelo.selectMedicamentoPorNombreVeredicto(vista.jTextFieldBuscarFichasDeMedicamentos.getText());
            }
        } else if (vista.jComboBoxBuscarFichasDeMedicamentos.getSelectedIndex() == 1) {
            if (vista.jCheckBoxMostrarTodo.isSelected()) {
                listaFichasDeMedicamentos = modelo.selectMedicamentoPorNombreProducto(vista.jTextFieldBuscarFichasDeMedicamentos.getText());
            } else {
                listaFichasDeMedicamentos = modelo.selectMedicamentoPorNombreProductoVeredicto(vista.jTextFieldBuscarFichasDeMedicamentos.getText());
            }
        }
        rellenarJListFichasDeMedicamentos();
        vista.jListFichasDeMedicamentos.setSelectedIndex(0);

        if (listaFichasDeMedicamentos.size() < 1) {
            JOptionPane.showMessageDialog(vista, "No hay medicamentos que cumplan las condiciones de la búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(vista, "Se han reestablecido las condiciones de búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            recargarDatosQuitarBuscar();
        } else {
            JOptionPane.showMessageDialog(vista, "Se han encontrado " + listaFichasDeMedicamentos.size() + " medicamentos", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    //Quitar el filtro de las busqudas
    public void recargarDatosQuitarBuscar() {
        vista.jTextFieldBuscarFichasDeMedicamentos.setText("");
        recargarDatos();
    }

    // Recarga los datos de la base de datos
    public void recargarDatos() {
        if (vista.jCheckBoxMostrarTodo.isSelected()) {
            listaFichasDeMedicamentos = modelo.selectAllMedicamentos();
        } else {
            listaFichasDeMedicamentos = modelo.selectAllMedicamentosVeredicto();
        }
        rellenarJListFichasDeMedicamentos();
        vista.jListFichasDeMedicamentos.setSelectedIndex(0);
    }

    // Pulsar el boton de añadir maquinaria
    public void pulsarAddMaquinaria() {
        vista.jDialogAddMaquinaria.setSize(975, 640);
        vista.jDialogAddMaquinaria.setLocationRelativeTo(null);
        vista.jDialogAddMaquinaria.setVisible(true);

    }

    //Añadir una maquinaria a un medicamento
    public void addMaquinariaMedicamento() {
        MaquinariaUnion maquinaria = new MaquinariaUnion();
        maquinaria.setMaquinaria(vista.controlMaquinaria.getMaquinariaElegida());
        maquinaria.setFichasDeMedicamentos(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()));
        try {
            modelo.nuevoMaquinariaUnion(maquinaria);
            vista.jDialogAddMaquinaria.setVisible(false);
            int id = listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento();
            //recargarDatos         
            listaFichasDeMedicamentos.set(vista.jListFichasDeMedicamentos.getSelectedIndex(), modelo.selectMedicamentoPorID(id));
            cambiarPosicionJListFichasDeMedicamentos();
        } catch (ConstraintViolationException e) {
            JOptionPane.showMessageDialog(vista.jDialogAddMaquinaria, "Esa maquinaria ya esta añadida, no se pueden añadir dos maquinarias iguales", "Error", JOptionPane.WARNING_MESSAGE);
        }

    }

    // Pulsar el boton de añadir material para elaborar
    public void pulsarAddMaterialElaborar() {
        vista.jDialogAddMaterialElaborar.setSize(975, 640);
        vista.jDialogAddMaterialElaborar.setLocationRelativeTo(null);
        vista.jDialogAddMaterialElaborar.setVisible(true);

    }

    //Añadir un material para elaborar a un medicamento
    public void addMaterialElaborarMedicamento() {
        MaterialParaElaborarUnion material = new MaterialParaElaborarUnion();
        material.setMaterialParaElaborar(vista.controlMaterialElaborar.getMaterialParaElaborar());
        material.setFichasDeMedicamentos(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()));
        modelo.nuevoMaterialElaborarUnion(material);
        vista.jDialogAddMaterialElaborar.setVisible(false);
        int id = listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento();
        //recargarDatos         
        listaFichasDeMedicamentos.set(vista.jListFichasDeMedicamentos.getSelectedIndex(), modelo.selectMedicamentoPorID(id));
        cambiarPosicionJListFichasDeMedicamentos();

    }

    // Pulsar el boton de añadir material para envasar
    public void pulsarAddMaterialEnvasar() {
        vista.jDialogAddMaterialEnvasar.setSize(975, 640);
        vista.jDialogAddMaterialEnvasar.setLocationRelativeTo(null);
        vista.jDialogAddMaterialEnvasar.setVisible(true);

    }

    //Añadir un material para envasar a un medicamento
    public void addMaterialEnvasarMedicamento() {
        MaterialDeEnvasadoUnion material = new MaterialDeEnvasadoUnion();
        material.setMaterialDeEnvasado(vista.controlMaterialEnvasar.getMaterialEnvasado());
        material.setFichasDeMedicamentos(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()));
        modelo.nuevoMaterialEnvasarUnion(material);
        vista.jDialogAddMaterialEnvasar.setVisible(false);
        int id = listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento();
        //recargarDatos         
        listaFichasDeMedicamentos.set(vista.jListFichasDeMedicamentos.getSelectedIndex(), modelo.selectMedicamentoPorID(id));
        cambiarPosicionJListFichasDeMedicamentos();
    }

    // Pulsar el boton de añadir producto
    public void pulsarAddProductos() {
        vista.jDialogAddProducto.setSize(975, 640);
        vista.jDialogAddProducto.setLocationRelativeTo(null);
        vista.jDialogAddProducto.setVisible(true);

    }

    //Añadir un producto a un medicamento
    public void addProductosMedicamento() {
        ProductosUnion producto = new ProductosUnion();
        producto.setProductos(vista.controlProductos.getProducto());
        producto.setFichasDeMedicamentos(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()));
        asignarOrdenProducto(producto);
        modelo.nuevoProductoUnion(producto);
        vista.jDialogAddProducto.setVisible(false);
        int id = listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento();
        //recargarDatos         
        listaFichasDeMedicamentos.set(vista.jListFichasDeMedicamentos.getSelectedIndex(), modelo.selectMedicamentoPorID(id));
        cambiarPosicionJListFichasDeMedicamentos();
        nuevoProducto = true;
        mostrarDatosProducto(listaProductosMedicamento.size() - 1);
        vista.jDialogModificarProducto.setSize(450, 330);
        vista.jDialogModificarProducto.setLocationRelativeTo(null);
        vista.jDialogModificarProducto.setVisible(true);

    }

    //pulsar el boton de modificar los datos de un producto perteneciente a un medicamento
    public void pulsarBotonModificarProducto() {
        if (vista.jTableProductos.getSelectedRow() != -1) {
            mostrarDatosProducto(vista.jTableProductos.getSelectedRow());
            vista.jDialogModificarProducto.setSize(450, 330);
            vista.jDialogModificarProducto.setLocationRelativeTo(null);
            vista.jDialogModificarProducto.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un producto de la tabla para poder modificarlo", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    //mostrar los datos de un producto en el dialogo de modificar productos
    public void mostrarDatosProducto(int posicion) {
        vista.jLabelDialogModificarProductoProductos.setText(vista.jTableProductos.getValueAt(posicion, 1).toString());
        if (!nuevoProducto && listaProductosMedicamento.get(posicion).getCantidad() != null) {

            if (listaProductosMedicamento.get(posicion).getCantidad().contains("C.S.P.")) {
                vista.jTextFieldDialogModificarProductoCantidad.setText(listaProductosMedicamento.get(posicion).getCantidad().substring(7));
                vista.jCheckBoxCSP.setSelected(true);
            } else {
                vista.jTextFieldDialogModificarProductoCantidad.setText(listaProductosMedicamento.get(posicion).getCantidad());
                vista.jCheckBoxCSP.setSelected(false);
            }
            vista.jComboBoxDialogModificarProductoUnidades.setSelectedItem(listaProductosMedicamento.get(posicion).getUnidades());

        } else {
            vista.jTextFieldDialogModificarProductoCantidad.setText("");
            vista.jComboBoxDialogModificarProductoUnidades.setSelectedItem("");
            vista.jCheckBoxCSP.setSelected(false);
        }

    }

    //modificar los datos de un medicamento perteneciente a un medicamento
    public void modificarProductoMedicamento() {
        try {
            if (!vista.jTextFieldDialogModificarProductoCantidad.getText().equals("") && vista.jComboBoxDialogModificarProductoUnidades.getSelectedIndex() != -1) {
                if (!nuevoProducto) {
                    listaProductosMedicamento.get(vista.jTableProductos.getSelectedRow()).setUnidades(vista.jComboBoxDialogModificarProductoUnidades.getSelectedItem().toString());
                    if (vista.jCheckBoxCSP.isSelected()) {
                        listaProductosMedicamento.get(vista.jTableProductos.getSelectedRow()).setCantidad("C.S.P. " + vista.jTextFieldDialogModificarProductoCantidad.getText());
                    } else {
                        listaProductosMedicamento.get(vista.jTableProductos.getSelectedRow()).setCantidad(vista.jTextFieldDialogModificarProductoCantidad.getText());
                    }
                    modelo.modificarProductoUnion(listaProductosMedicamento.get(vista.jTableProductos.getSelectedRow()));

                } else {
                    listaProductosMedicamento.get(listaProductosMedicamento.size() - 1).setUnidades(vista.jComboBoxDialogModificarProductoUnidades.getSelectedItem().toString());
                    if (vista.jCheckBoxCSP.isSelected()) {
                        listaProductosMedicamento.get(listaProductosMedicamento.size() - 1).setCantidad("C.S.P. " + vista.jTextFieldDialogModificarProductoCantidad.getText());
                    } else {
                        listaProductosMedicamento.get(listaProductosMedicamento.size() - 1).setCantidad(vista.jTextFieldDialogModificarProductoCantidad.getText());
                    }
                    modelo.modificarProductoUnion(listaProductosMedicamento.get(listaProductosMedicamento.size() - 1));
                }
                nuevoProducto = false;
                int id = listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento();
                //recargarDatos         
                listaFichasDeMedicamentos.set(vista.jListFichasDeMedicamentos.getSelectedIndex(), modelo.selectMedicamentoPorID(id));
                cambiarPosicionJListFichasDeMedicamentos();
                vista.jDialogModificarProducto.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(vista.jDialogModificarProducto, "La cantidad y las unidades tienen que tener algún valor para poder modificar los datos", "Error", JOptionPane.WARNING_MESSAGE);
            }

        } catch (DataException e) {
            JOptionPane.showMessageDialog(vista.jDialogModificarProducto, "El producto no se ha podido modificar debido a que la cantidad introducida es demasiado alta", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    //da el siguiente orden a un producto nuevo
    public void asignarOrdenProducto(ProductosUnion producto) {
        String anterior = modelo.maxOrden(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento());
        if (anterior != null) {
            char siguiente = (char) ((int) anterior.charAt(0) + 1);
            producto.setOrden(String.valueOf(siguiente));
        } else {
            producto.setOrden(String.valueOf("a"));
        }

    }

    //Eliminar una maquinaria
    public void pulsarBotonEliminarMaquinaria() {
        if (vista.jTableMaquinaria.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar la maquinaria seleccionada?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
                modelo.eliminarMaquinariaUnion(listaMaquinariaMedicamento.get(vista.jTableMaquinaria.getSelectedRow()));
                int id = listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento();
                //recargarDatos         
                listaFichasDeMedicamentos.set(vista.jListFichasDeMedicamentos.getSelectedIndex(), modelo.selectMedicamentoPorID(id));
                cambiarPosicionJListFichasDeMedicamentos();

            }
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una maquinaria de la tabla para poder eliminarla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }

    //Eliminar una maquinaria
    public void pulsarBotonEliminarMaterialElaborar() {
        if (vista.jTableMaterialElaborar.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el material para elaborar seleccionado?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
                modelo.eliminarMaterialElaborarUnion(listaMaterialElaborarMedicamento.get(vista.jTableMaterialElaborar.getSelectedRow()));
                int id = listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento();
                //recargarDatos         
                listaFichasDeMedicamentos.set(vista.jListFichasDeMedicamentos.getSelectedIndex(), modelo.selectMedicamentoPorID(id));
                cambiarPosicionJListFichasDeMedicamentos();

            }
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un material para elaborar de la tabla para poder eliminarlo", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }

    //Eliminar una maquinaria
    public void pulsarBotonEliminarMaterialEnvasar() {
        if (vista.jTableMaterialEnvasar.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el material para envasar seleccionado?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
                modelo.eliminarMaterialEnvasarUnion(listaMaterialEnvasadoMedicamento.get(vista.jTableMaterialEnvasar.getSelectedRow()));
                int id = listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento();
                //recargarDatos         
                listaFichasDeMedicamentos.set(vista.jListFichasDeMedicamentos.getSelectedIndex(), modelo.selectMedicamentoPorID(id));
                cambiarPosicionJListFichasDeMedicamentos();

            }
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un material para envasar de la tabla para poder eliminarlo", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }

    //Eliminar una maquinaria
    public void pulsarBotonEliminarProducto() {
        if (vista.jTableProductos.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el producto seleccionado?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
                modelo.eliminarProductoUnion(listaProductosMedicamento.get(vista.jTableProductos.getSelectedRow()));
                int id = listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento();
                //recargarDatos         
                listaFichasDeMedicamentos.set(vista.jListFichasDeMedicamentos.getSelectedIndex(), modelo.selectMedicamentoPorID(id));
                cambiarPosicionJListFichasDeMedicamentos();

            }
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un producto de la tabla para poder eliminarlo", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }

    //Pulsar botón de mostrar etiquetas
    public void pulsarBotonDialogEtiquetasVerEtiqueta() {
        vista.jDialogEtiquetas.setVisible(false);
        VentanaEtiquetas.instance.mostrarEtiquetaDeMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getMedicamento(), false);
        VentanaEtiquetas.instance.setLocationRelativeTo(null);
        VentanaEtiquetas.instance.setVisible(true);

    }

    //Pulsar botón de nueva etiqueta
    public void pulsarBotonDialogEtiquetasNuevaEtiqueta() {
        vista.jDialogEtiquetas.setVisible(false);
        VentanaEtiquetas.instance.mostrarEtiquetaDeMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getMedicamento(), true);
        VentanaEtiquetas.instance.setLocationRelativeTo(null);
        VentanaEtiquetas.instance.setVisible(true);

    }

    //Pulsar el botón que abre el diálogo de las etiquetas
    public void pulsarBotonEtiquetas() {
        vista.jDialogEtiquetas.setSize(580, 300);
        vista.jDialogEtiquetas.setLocationRelativeTo(null);
        vista.jDialogEtiquetas.setVisible(true);
    }

    //Pulsar el botón de imprimir
    public void pulsarBotonImprimir() {
        Session sesion = modelo.abrirSesion();
        listaMaterialElaborarMedicamento = modelo.selectMaterialElaborarDeMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento(), sesion);
        listaMaquinariaMedicamento = modelo.selectMaquinariaDeMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento(), sesion);
        listaMaterialEnvasadoMedicamento = modelo.selectMaterialEnvasarDeMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento(), sesion);
        listaProductosMedicamento = modelo.selectProductosDeMedicamento(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()).getIdMedicamento(), sesion);
        ArrayList<FichasDeMedicamentos> fichaMedicamentoImprimir = new ArrayList<>();
        fichaMedicamentoImprimir.add(listaFichasDeMedicamentos.get(vista.jListFichasDeMedicamentos.getSelectedIndex()));
        FichasDeMedicamentosDataSource datasource = new FichasDeMedicamentosDataSource(fichaMedicamentoImprimir, listaMaquinariaMedicamento, listaMaterialElaborarMedicamento, listaMaterialEnvasadoMedicamento, listaProductosMedicamento);
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile("reportSub.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            //  jviewer.setAlwaysOnTop(true);
            jviewer.setTitle("Imprimir");
            jviewer.setVisible(true);
            jviewer.setExtendedState(Frame.MAXIMIZED_BOTH);
        } catch (JRException e1) {
            JOptionPane.showMessageDialog(vista, "Error al inicializar el reporte", "Fallo", JOptionPane.ERROR_MESSAGE);
        }
        modelo.cerrarSesion(sesion);
    }

    public void pulsarCheckMostrarTodo() {
        if (vista.jCheckBoxMostrarTodo.isSelected()) {
            listaFichasDeMedicamentos = modelo.selectAllMedicamentos();
        } else {
            listaFichasDeMedicamentos = modelo.selectAllMedicamentosVeredicto();
        }
        rellenarJListFichasDeMedicamentos();
        vista.jListFichasDeMedicamentos.setSelectedIndex(0);
    }

}
