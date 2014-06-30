package es.sacyl.caza.farmacia.controlador;

import es.sacyl.caza.farmacia.modelo.ProductosDAO;
import es.sacyl.caza.farmacia.modelo.clases.Productos;
import es.sacyl.caza.farmacia.vista.PanelProductos;
import es.sacyl.caza.farmacia.vista.VentanaEdicionPMS;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Enrique Martín Arenal
 *
 * Maneja los eventos relacionados con la pestaña productos de la
 * VentanaEdicionPMS
 */
public class ControladorProductos {

    private PanelProductos vista;
    private ArrayList<Productos> listaProductos;
    private String[] productosJList;
    private ProductosDAO modelo;
    private boolean nuevoPulsado = false;
    private VentanaEdicionPMS ventanaPms;

    public ControladorProductos(PanelProductos vista) {
        this.vista = vista;
        init();
        initListeners();
    }

    public ControladorProductos(PanelProductos vista, VentanaEdicionPMS ventanaEdicionPMS) {
        this.vista = vista;
        this.ventanaPms = ventanaEdicionPMS;
        init();
        initListeners();
    }

    //Inicialización de los objetos necesarios para acceder y mostrar los datos
    private void init() {
        modelo = new ProductosDAO();
        listaProductos = modelo.selectAllProducto();
        rellenarJListProductos();
        vista.jListProductos.setSelectedIndex(0);
        actualizarPosicion();
        mostrarDatosProductos();
        acabarEdicion();
    }

    //Añade los listener de los eventos a los componentes
    private void initListeners() {
        vista.jListProductos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cambiarPosicionJListProductos();
            }
        });
        vista.jButtonSiguienteProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseSiguiente();
            }
        });
        vista.jButtonAtrasProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseAnterior();
            }
        });
        vista.jButtonPrimeroProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoversePrimero();
            }
        });
        vista.jButtonUltimoProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseUltimo();
            }
        });
        vista.jButtonModificarProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarProducto();
            }
        });
        vista.jButtonAceptarProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarPulsado();
            }
        });
        vista.jButtonCancelarProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarPulsado();
            }
        });
        vista.jButtonNuevoProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonNuevoProducto();
            }
        });
        vista.jButtonBuscarProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscar();
            }
        });
        vista.jButtonQuitarBuscarProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargarDatosQuitarBuscar();
            }
        });
        vista.jButtonEliminarProductos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarProducto();
            }
        });
        vista.jTextFieldBuscarProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    botonBuscar();
                }
            }
        });
    }

    //Llena el JListProductos con los nombres de los productos existentes en la base de datos
    private void rellenarJListProductos() {
        productosJList = new String[listaProductos.size()];
        for (int i = 0; i < productosJList.length; i++) {
            productosJList[i] = listaProductos.get(i).getReferencia() + " - " + listaProductos.get(i).getComponente();
        }
        vista.jListProductos.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return productosJList.length;
            }

            @Override
            public Object getElementAt(int i) {
                return productosJList[i];
            }
        });
    }

    //Posición del producto en el que estamos situados
    private void actualizarPosicion() {
        String pos = "Producto " + (vista.jListProductos.getSelectedIndex() + 1) + " de " + productosJList.length;
        vista.jLabelPosicionProducto.setText(pos);
    }

    //Mostrar los datos del elemento seleccionado del jListProductos 
    private void mostrarDatosProductos() {
        if (vista.jListProductos.getSelectedIndex() > -1) {
            vista.jTextAreaComponenteProductos.setText(listaProductos.get(vista.jListProductos.getSelectedIndex()).getComponente());
            vista.jLabelIdProductos.setText(listaProductos.get(vista.jListProductos.getSelectedIndex()).getIdComponente() + "");
            vista.jTextAreaObservacionesProductos.setText(listaProductos.get(vista.jListProductos.getSelectedIndex()).getObservaciones());
            vista.jTextFieldEstanteProductos.setText(listaProductos.get(vista.jListProductos.getSelectedIndex()).getEstante());
            vista.jTextFieldReferenciaProductos.setText(listaProductos.get(vista.jListProductos.getSelectedIndex()).getReferencia());
            if (!nuevoPulsado) {
                if (listaProductos.get(vista.jListProductos.getSelectedIndex()).getActivo()) {
                    vista.jComboBoxActivoProductos.setSelectedIndex(1);
                } else {
                    vista.jComboBoxActivoProductos.setSelectedIndex(0);
                }
            }

        }
    }

    //Recoger los datos de los controloes en el elemento seleccionado del jListProductos
    private void recogerDatosProductos() {
        listaProductos.get(vista.jListProductos.getSelectedIndex()).setComponente(vista.jTextAreaComponenteProductos.getText());
        listaProductos.get(vista.jListProductos.getSelectedIndex()).setEstante(vista.jTextFieldEstanteProductos.getText());
        listaProductos.get(vista.jListProductos.getSelectedIndex()).setObservaciones(vista.jTextAreaObservacionesProductos.getText());
        listaProductos.get(vista.jListProductos.getSelectedIndex()).setReferencia(vista.jTextFieldReferenciaProductos.getText());
        if (vista.jComboBoxActivoProductos.getSelectedIndex() == 0) {
            listaProductos.get(vista.jListProductos.getSelectedIndex()).setActivo(false);
        } else {
            listaProductos.get(vista.jListProductos.getSelectedIndex()).setActivo(true);
        }
    }

    // Activar/desactivar botones de siguiente,anterior,primero,último 
    private void controlarBotonesMoverse() {
        if (vista.jListProductos.getSelectedIndex() == 0) {
            vista.jButtonAtrasProductos.setEnabled(false);
            vista.jButtonPrimeroProductos.setEnabled(false);
        } else {
            vista.jButtonAtrasProductos.setEnabled(true);
            vista.jButtonPrimeroProductos.setEnabled(true);
        }
        if (vista.jListProductos.getSelectedIndex() == listaProductos.size() - 1) {
            vista.jButtonSiguienteProductos.setEnabled(false);
            vista.jButtonUltimoProductos.setEnabled(false);
        } else {
            vista.jButtonSiguienteProductos.setEnabled(true);
            vista.jButtonUltimoProductos.setEnabled(true);
        }
    }

    // Evento al cambiar de posición en el jListProductos
    private void cambiarPosicionJListProductos() {
        actualizarPosicion();
        controlarBotonesMoverse();
        mostrarDatosProductos();
    }

    //Moverse al siguiente producto de la lista
    protected void botonMoverseSiguiente() {
        vista.jListProductos.setSelectedIndex(vista.jListProductos.getSelectedIndex() + 1);
        if (vista.jScrollPaneListaProducto.getVerticalScrollBar().getValue() - (vista.jListProductos.getSelectedIndex() * 22) < -308) {
            vista.jScrollPaneListaProducto.getVerticalScrollBar().setValue(((vista.jListProductos.getSelectedIndex() * 22) - 308));
        }
    }

    //Moverse al anterior producto de la lista
    protected void botonMoverseAnterior() {
        vista.jListProductos.setSelectedIndex(vista.jListProductos.getSelectedIndex() - 1);
        if (vista.jScrollPaneListaProducto.getVerticalScrollBar().getValue() - (vista.jListProductos.getSelectedIndex() * 22) > 0) {
            vista.jScrollPaneListaProducto.getVerticalScrollBar().setValue(((vista.jListProductos.getSelectedIndex() * 22)));
        }
    }

    //Moverse al primer producto de la lista
    protected void botonMoversePrimero() {
        vista.jListProductos.setSelectedIndex(0);
        vista.jScrollPaneListaProducto.getVerticalScrollBar().setValue(0);

    }

    //Moverse al último producto de la lista
    protected void botonMoverseUltimo() {
        vista.jListProductos.setSelectedIndex(listaProductos.size() - 1);
        vista.jScrollPaneListaProducto.getVerticalScrollBar().setValue(((vista.jListProductos.getSelectedIndex() * 22) - 308));

    }

    //Modificar un producto
    protected void pulsarBotonModificarProducto() {
        comenzarEdicion();
        nuevoPulsado = false;
    }

    //Nuevo producto
    protected void pulsarBotonNuevoProducto() {

        nuevoPulsado = true;
        Productos producto = new Productos();
        listaProductos.add(producto);
        rellenarJListProductos();
        vista.jListProductos.setSelectedIndex(listaProductos.size() - 1);
        comenzarEdicion();
    }

    //Eliminar producto
    protected void pulsarBotonEliminarProducto() {
        if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el producto actual?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
            if (listaProductos.size() < 2) {
                JOptionPane.showMessageDialog(vista, "No se pueden eliminar todos los productos visibles. \nPruebe a quitar algún filtro de busqueda", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                modelo.eliminarProducto(listaProductos.get(vista.jListProductos.getSelectedIndex()));
                listaProductos.remove(listaProductos.get(vista.jListProductos.getSelectedIndex()));
                rellenarJListProductos();
                vista.jListProductos.setSelectedIndex(0);
            }
        }
    }

    // Habilitar controles para permitir la edición de los datos de un producto
    private void comenzarEdicion() {
        vista.jTextAreaComponenteProductos.setEditable(true);
        vista.jTextAreaObservacionesProductos.setEditable(true);
        vista.jTextFieldEstanteProductos.setEditable(true);
        vista.jTextFieldReferenciaProductos.setEditable(true);
        vista.jComboBoxActivoProductos.setEnabled(true);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListProductos.setEnabled(false);
        vista.jButtonAtrasProductos.setEnabled(false);
        vista.jButtonEliminarProductos.setEnabled(false);
        vista.jButtonNuevoProductos.setEnabled(false);
        vista.jButtonPrimeroProductos.setEnabled(false);
        vista.jButtonSiguienteProductos.setEnabled(false);
        vista.jButtonUltimoProductos.setEnabled(false);
        //deshabilita el resto de pestañas
        if (ventanaPms != null) {
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(1, false);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(2, false);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(3, false);
        }

        vista.jButtonBuscarProductos.setEnabled(false);
        vista.jButtonQuitarBuscarProductos.setEnabled(false);
        vista.jTextFieldBuscarProductos.setEnabled(false);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Edición de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        vista.jPanelListaProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Lista de productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); 
        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jButtonAceptarProductos.setVisible(true);
        vista.jButtonCancelarProductos.setVisible(true);

        //da el focus a la primera opción editable
        vista.jTextFieldReferenciaProductos.transferFocusBackward();
    }

    //Pasos contrarios a comenzarEdicion()
    private void acabarEdicion() {
        vista.jTextAreaComponenteProductos.setEditable(false);
        vista.jTextAreaObservacionesProductos.setEditable(false);
        vista.jTextFieldEstanteProductos.setEditable(false);
        vista.jTextFieldReferenciaProductos.setEditable(false);
        vista.jComboBoxActivoProductos.setEnabled(false);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListProductos.setEnabled(true);
        vista.jButtonAtrasProductos.setEnabled(true);
        vista.jButtonEliminarProductos.setEnabled(true);
        vista.jButtonNuevoProductos.setEnabled(true);
        vista.jButtonPrimeroProductos.setEnabled(true);
        vista.jButtonSiguienteProductos.setEnabled(true);
        vista.jButtonUltimoProductos.setEnabled(true);
        if (ventanaPms != null) {
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(1, true);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(2, true);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(3, true);
        }
        vista.jButtonBuscarProductos.setEnabled(true);
        vista.jButtonQuitarBuscarProductos.setEnabled(true);
        vista.jTextFieldBuscarProductos.setEnabled(true);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Edición de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        vista.jPanelListaProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Lista de productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); 
        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jButtonAceptarProductos.setVisible(false);
        vista.jButtonCancelarProductos.setVisible(false);
    }

    //Pulsar boton de aceptar
    public void botonAceptarPulsado() {
        if (comprobarCamposObligatorios()) {
            try {
                if (nuevoPulsado) {
                    recogerDatosProductos();
                    modelo.nuevoProducto(listaProductos.get(vista.jListProductos.getSelectedIndex()));
                    acabarEdicion();
                    rellenarJListProductos();
                    vista.jListProductos.setSelectedIndex(listaProductos.size() - 1);

                } else {
                    recogerDatosProductos();
                    modelo.modificarProducto(listaProductos.get(vista.jListProductos.getSelectedIndex()));
                    int posicion = vista.jListProductos.getSelectedIndex();
                    acabarEdicion();
                    rellenarJListProductos();
                    vista.jListProductos.setSelectedIndex(posicion);

                }
            } catch (org.hibernate.exception.DataException e) {
                JOptionPane.showMessageDialog(vista, "Uno de los campos es demasiado largo, por favor hágalo más corto.", "Error", JOptionPane.WARNING_MESSAGE);
            } catch (ConstraintViolationException e) {
                JOptionPane.showMessageDialog(vista, "La referencia introducida ya existe, introduza otra", "Error", JOptionPane.WARNING_MESSAGE);
            }

        }

    }

    //Pulsar boton de cancelar
    public void botonCancelarPulsado() {
        if (nuevoPulsado) {
            listaProductos.remove(listaProductos.size() - 1);
            acabarEdicion();
            rellenarJListProductos();
            vista.jListProductos.setSelectedIndex(0);

        } else {
            acabarEdicion();
            controlarBotonesMoverse();
            mostrarDatosProductos();
        }
    }

    //Comprobar que los campos obligatorios no están vacios y que la referencia sea válida
    public boolean comprobarCamposObligatorios() {
        if (vista.jTextAreaComponenteProductos.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Los siguiente campos son obligatorios \n\t-Componente\n\t-Referencia", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (vista.jTextFieldReferenciaProductos.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Los siguiente campos son obligatorios \n\t-Componente\n\t-Referencia", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!(vista.jTextFieldReferenciaProductos.getText().startsWith("s") || vista.jTextFieldReferenciaProductos.getText().startsWith("S")
                || vista.jTextFieldReferenciaProductos.getText().startsWith("l") || vista.jTextFieldReferenciaProductos.getText().startsWith("L")
                || vista.jTextFieldReferenciaProductos.getText().startsWith("c") || vista.jTextFieldReferenciaProductos.getText().startsWith("C")
                || vista.jTextFieldReferenciaProductos.getText().startsWith("#"))) {
            JOptionPane.showMessageDialog(vista, "El campo referencia debe empezar por 'S','L','C' o '#' ", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    //Realizar búsquedas
    public void botonBuscar() {
        if (vista.jComboBoxBuscarProductos.getSelectedIndex() == 0) {
            listaProductos = modelo.selectProductosPorComponente(vista.jTextFieldBuscarProductos.getText());
        } else if (vista.jComboBoxBuscarProductos.getSelectedIndex() == 1) {
            listaProductos = modelo.selectProductosPorReferencia(vista.jTextFieldBuscarProductos.getText());
        } else if (vista.jComboBoxBuscarProductos.getSelectedIndex() == 2) {
            if (vista.jTextFieldBuscarProductos.getText().equals("1")) {
                listaProductos = modelo.selectProductosActivos(true);
            } else if (vista.jTextFieldBuscarProductos.getText().equals("0")) {
                listaProductos = modelo.selectProductosActivos(false);
            }
        }
        rellenarJListProductos();
        vista.jListProductos.setSelectedIndex(0);
        if (listaProductos.size() < 1) {
            JOptionPane.showMessageDialog(vista, "No hay productos que cumplan las condiciones de la búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(vista, "Se han reestablecido las condiciones de búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            recargarDatosQuitarBuscar();
        } else {
            JOptionPane.showMessageDialog(vista, "Se han encontrado " + listaProductos.size() + " productos", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    //Quitar el filtro de las busqudas
    public void recargarDatosQuitarBuscar() {
        vista.jTextFieldBuscarProductos.setText("");
        recargarDatos();
    }

    // Recarga los datos de la base de datos
    public void recargarDatos() {
        listaProductos = modelo.selectAllProducto();
        rellenarJListProductos();
        vista.jListProductos.setSelectedIndex(0);
    }

    //Devuelve el producto seleccionado actualmente
    public Productos getProducto() {
        return listaProductos.get(vista.jListProductos.getSelectedIndex());
    }

}
