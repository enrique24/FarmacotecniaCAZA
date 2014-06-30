package es.sacyl.caza.farmacia.controlador;

import es.sacyl.caza.farmacia.modelo.MaterialEnvasarDAO;
import es.sacyl.caza.farmacia.modelo.clases.MaterialDeEnvasado;
import es.sacyl.caza.farmacia.vista.PanelMaterialEnvasar;
import es.sacyl.caza.farmacia.vista.VentanaEdicionPMS;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Enrique Martín Arenal
 *
 * Maneja los eventos de la pestaña material envasar de la VentanaEdicionPMS
 */
public class ControladorMaterialEnvasar {

    private VentanaEdicionPMS ventanaPms;
    private ArrayList<MaterialDeEnvasado> listaMaterialEnvasar;
    private String[] MaterialEnvasarJList;
    private MaterialEnvasarDAO modelo;
    private boolean nuevoPulsado = false;
    private PanelMaterialEnvasar vista;

    public ControladorMaterialEnvasar(PanelMaterialEnvasar vista) {
        this.vista = vista;
        init();
        initListeners();
    }

    public ControladorMaterialEnvasar(PanelMaterialEnvasar vista, VentanaEdicionPMS ventanaEdicionPMS) {
        this.vista = vista;
        this.ventanaPms = ventanaEdicionPMS;
        init();
        initListeners();
    }

    //Inicialización de los objetos necesarios para acceder y mostrar los datos
    private void init() {
        modelo = new MaterialEnvasarDAO();
        listaMaterialEnvasar = modelo.selectAllMaterialDeEnvasado();
        rellenarJListMaterialEnvasar();
        vista.jListMaterialEnvasar.setSelectedIndex(0);
        actualizarPosicion();
        mostrarDatosMaterialEnvasar();
        pulsarCheckboxTenemos();
        acabarEdicion();
    }

    //Añade los listener de los eventos a los componentes
    private void initListeners() {
        vista.jListMaterialEnvasar.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cambiarPosicionJListMaterialEnvasar();
            }
        });
        vista.jButtonSiguienteMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseSiguiente();
            }
        });
        vista.jButtonAtrasMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseAnterior();
            }
        });
        vista.jButtonPrimeroMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoversePrimero();
            }
        });
        vista.jButtonUltimoMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseUltimo();
            }
        });
        vista.jButtonModificarMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarMaterialEnvasar();
            }
        });
        vista.jButtonAceptarMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarPulsado();
            }
        });
        vista.jButtonCancelarMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarPulsado();
            }
        });
        vista.jButtonNuevoMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonNuevoMaterialEnvasar();
            }
        });
        vista.jButtonBuscarMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscar();
            }
        });
        vista.jButtonQuitarBuscarMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargarDatosQuitarBuscar();
            }
        });
        vista.jButtonEliminarMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarMaterialEnvasar();
            }
        });
        vista.jCheckBoxTenemosMaterialEnvasar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarCheckboxTenemos();
            }
        });
        vista.jTextFieldBuscarMaterialEnvasar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    botonBuscar();
                }
            }
        });
    }

    //Llena el JListMaterialEnvasar con los nombres de los materiales para envasar existentes en la base de datos
    private void rellenarJListMaterialEnvasar() {
        MaterialEnvasarJList = new String[listaMaterialEnvasar.size()];
        for (int i = 0; i < MaterialEnvasarJList.length; i++) {
            if (listaMaterialEnvasar.get(i).isTenemos()) {
                MaterialEnvasarJList[i] = "Sí - " + listaMaterialEnvasar.get(i).getParaEnvasar();
            } else {
                MaterialEnvasarJList[i] = "No - " + listaMaterialEnvasar.get(i).getParaEnvasar();
            }
        }
        vista.jListMaterialEnvasar.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return MaterialEnvasarJList.length;
            }

            @Override
            public Object getElementAt(int i) {
                return MaterialEnvasarJList[i];
            }
        });
    }

    //Posición del  material para envasar en el que estamos situados
    private void actualizarPosicion() {
        String pos = "Material para envasar " + (vista.jListMaterialEnvasar.getSelectedIndex() + 1) + " de " + MaterialEnvasarJList.length;
        vista.jLabelPosicionMaterialEnvasar.setText(pos);
    }

    //Mostrar los datos del elemento seleccionado del jListMaterialEnvasar
    private void mostrarDatosMaterialEnvasar() {
        if (vista.jListMaterialEnvasar.getSelectedIndex() > -1) {
            vista.jTextAreaMaterialEnvasar.setText(listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex()).getParaEnvasar());
            vista.jLabelIdMaterialEnvasar.setText(listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex()).getIdParaEnvasar() + "");
            if (!nuevoPulsado) {
                if (listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex()).isTenemos()) {
                    vista.jCheckBoxTenemosMaterialEnvasar.setSelected(true);
                } else {
                    vista.jCheckBoxTenemosMaterialEnvasar.setSelected(false);
                }
            }

        }
    }

    //Recoger los datos de los controloes en el elemento seleccionado del jListMaterialEnvasar
    private void recogerDatosMaterialEnvasar() {
        listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex()).setParaEnvasar(vista.jTextAreaMaterialEnvasar.getText());
        if (vista.jCheckBoxTenemosMaterialEnvasar.isSelected()) {
            listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex()).setTenemos(true);
        } else {
            listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex()).setTenemos(false);
        }
    }

    // Evento al cambiar de posición en el jListMaterialEnvasar
    private void cambiarPosicionJListMaterialEnvasar() {
        actualizarPosicion();
        controlarBotonesMoverse();
        mostrarDatosMaterialEnvasar();
        pulsarCheckboxTenemos();
    }

    // Activar/desactivar botones de siguiente,anterior,primero,último 
    private void controlarBotonesMoverse() {
        if (vista.jListMaterialEnvasar.getSelectedIndex() == 0) {
            vista.jButtonAtrasMaterialEnvasar.setEnabled(false);
            vista.jButtonPrimeroMaterialEnvasar.setEnabled(false);
        } else {
            vista.jButtonAtrasMaterialEnvasar.setEnabled(true);
            vista.jButtonPrimeroMaterialEnvasar.setEnabled(true);
        }
        if (vista.jListMaterialEnvasar.getSelectedIndex() == listaMaterialEnvasar.size() - 1) {
            vista.jButtonSiguienteMaterialEnvasar.setEnabled(false);
            vista.jButtonUltimoMaterialEnvasar.setEnabled(false);
        } else {
            vista.jButtonSiguienteMaterialEnvasar.setEnabled(true);
            vista.jButtonUltimoMaterialEnvasar.setEnabled(true);
        }
    }

    //Poner si/no en el checkbox de tenemos dependiendo de si está o no seleccionado
    private void pulsarCheckboxTenemos() {
        if (vista.jCheckBoxTenemosMaterialEnvasar.isSelected()) {
            vista.jCheckBoxTenemosMaterialEnvasar.setText("Sí");
        } else {
            vista.jCheckBoxTenemosMaterialEnvasar.setText("No");
        }
    }

    //Moverse al siguiente material para envasar de la lista
    protected void botonMoverseSiguiente() {
        vista.jListMaterialEnvasar.setSelectedIndex(vista.jListMaterialEnvasar.getSelectedIndex() + 1);
        if (vista.jScrollPaneListaMaterial.getVerticalScrollBar().getValue() - (vista.jListMaterialEnvasar.getSelectedIndex() * 22) < -308) {
            vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue(((vista.jListMaterialEnvasar.getSelectedIndex() * 22) - 308));
        }
    }

    //Moverse al anterior material para envasar de la lista
    protected void botonMoverseAnterior() {
        vista.jListMaterialEnvasar.setSelectedIndex(vista.jListMaterialEnvasar.getSelectedIndex() - 1);
        if (vista.jScrollPaneListaMaterial.getVerticalScrollBar().getValue() - (vista.jListMaterialEnvasar.getSelectedIndex() * 22) > 0) {
            vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue(((vista.jListMaterialEnvasar.getSelectedIndex() * 22)));
        }
    }

    //Moverse al primer material para envasar de la lista
    protected void botonMoversePrimero() {
        vista.jListMaterialEnvasar.setSelectedIndex(0);
        vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue(0);

    }

    //Moverse al última material para envasar de la lista
    protected void botonMoverseUltimo() {
        vista.jListMaterialEnvasar.setSelectedIndex(listaMaterialEnvasar.size() - 1);
        vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue(((vista.jListMaterialEnvasar.getSelectedIndex() * 22) - 308));

    }

    //Modificar un material para envasar
    protected void pulsarBotonModificarMaterialEnvasar() {
        comenzarEdicion();
        nuevoPulsado = false;
    }

    //Nuevo material para envasar
    protected void pulsarBotonNuevoMaterialEnvasar() {

        nuevoPulsado = true;
        MaterialDeEnvasado maq = new MaterialDeEnvasado();
        listaMaterialEnvasar.add(maq);
        rellenarJListMaterialEnvasar();
        vista.jListMaterialEnvasar.setSelectedIndex(listaMaterialEnvasar.size() - 1);
        comenzarEdicion();
    }

    //Eliminar material para envasar
    protected void pulsarBotonEliminarMaterialEnvasar() {
        if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el material para envasar actual?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
            if (listaMaterialEnvasar.size() < 2) {
                JOptionPane.showMessageDialog(vista, "No se pueden eliminar todos los materiales visibles. \nPruebe a quitar algún filtro de busqueda", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                modelo.eliminarMaterialDeEnvasado(listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex()));
                listaMaterialEnvasar.remove(listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex()));
                rellenarJListMaterialEnvasar();
                vista.jListMaterialEnvasar.setSelectedIndex(0);
            }
        }
    }

    // Habilitar controles para permitir la edición de los datos de un material para envasar
    private void comenzarEdicion() {
        vista.jTextAreaMaterialEnvasar.setEditable(true);
        vista.jCheckBoxTenemosMaterialEnvasar.setEnabled(true);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListMaterialEnvasar.setEnabled(false);
        vista.jButtonAtrasMaterialEnvasar.setEnabled(false);
        vista.jButtonEliminarMaterialEnvasar.setEnabled(false);
        vista.jButtonNuevoMaterialEnvasar.setEnabled(false);
        vista.jButtonPrimeroMaterialEnvasar.setEnabled(false);
        vista.jButtonSiguienteMaterialEnvasar.setEnabled(false);
        vista.jButtonUltimoMaterialEnvasar.setEnabled(false);
        //deshabilita el resto de pestañas
        if (ventanaPms != null) {
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(0, false);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(1, false);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(3, false);
        }

        vista.jButtonBuscarMaterialEnvasar.setEnabled(false);
        vista.jButtonQuitarBuscarMaterialEnvasar.setEnabled(false);
        vista.jTextFieldBuscarMaterialEnvasar.setEnabled(false);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosMaterialEnvasar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Edición de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        vista.jPanelListaMaterialEnvasar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Lista de materiales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));

        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jButtonAceptarMaterialEnvasar.setVisible(true);
        vista.jButtonCancelarMaterialEnvasar.setVisible(true);

        //Da el foco al primer componente editable
        vista.jCheckBoxTenemosMaterialEnvasar.transferFocusBackward();
    }

    // lo contrario de comenzarEdicion()
    private void acabarEdicion() {
        vista.jTextAreaMaterialEnvasar.setEditable(false);
        vista.jCheckBoxTenemosMaterialEnvasar.setEnabled(false);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListMaterialEnvasar.setEnabled(true);
        vista.jButtonAtrasMaterialEnvasar.setEnabled(true);
        vista.jButtonEliminarMaterialEnvasar.setEnabled(true);
        vista.jButtonNuevoMaterialEnvasar.setEnabled(true);
        vista.jButtonPrimeroMaterialEnvasar.setEnabled(true);
        vista.jButtonSiguienteMaterialEnvasar.setEnabled(true);
        vista.jButtonUltimoMaterialEnvasar.setEnabled(true);
        //deshabilita el resto de pestañas
        if (ventanaPms != null) {
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(0, true);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(1, true);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(3, true);
        }

        vista.jButtonBuscarMaterialEnvasar.setEnabled(true);
        vista.jButtonQuitarBuscarMaterialEnvasar.setEnabled(true);
        vista.jTextFieldBuscarMaterialEnvasar.setEnabled(true);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosMaterialEnvasar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Edición de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        vista.jPanelListaMaterialEnvasar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Lista de materiales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jButtonAceptarMaterialEnvasar.setVisible(false);
        vista.jButtonCancelarMaterialEnvasar.setVisible(false);

    }

    //Pulsar boton de aceptar
    public void botonAceptarPulsado() {
        if (comprobarCamposObligatorios()) {
            try {
                if (nuevoPulsado) {
                    recogerDatosMaterialEnvasar();
                    modelo.nuevoMaterialDeEnvasado(listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex()));
                    acabarEdicion();
                    rellenarJListMaterialEnvasar();
                    vista.jListMaterialEnvasar.setSelectedIndex(listaMaterialEnvasar.size() - 1);

                } else {
                    recogerDatosMaterialEnvasar();
                    modelo.modificarMaterialDeEnvasado(listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex()));
                    int posicion = vista.jListMaterialEnvasar.getSelectedIndex();
                    acabarEdicion();
                    rellenarJListMaterialEnvasar();
                    vista.jListMaterialEnvasar.setSelectedIndex(posicion);

                }
            } catch (org.hibernate.exception.DataException e) {
                JOptionPane.showMessageDialog(vista, "El nombre del material para envasar es demasiado largo, por favor hágalo más corto.", "Error", JOptionPane.WARNING_MESSAGE);

            } catch (ConstraintViolationException e) {
                JOptionPane.showMessageDialog(vista, "El material para envasar introducido ya existe, introduza otro", "Error", JOptionPane.WARNING_MESSAGE);
            }

        }

    }

    //Comprobar que los campos obligatorios no están vacios
    public boolean comprobarCamposObligatorios() {
        if (vista.jTextAreaMaterialEnvasar.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Los siguiente campos son obligatorios \n\t-Material envasar\n\t-Tenemos", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Pulsar boton de cancelar
    public void botonCancelarPulsado() {
        if (nuevoPulsado) {
            listaMaterialEnvasar.remove(listaMaterialEnvasar.size() - 1);
            acabarEdicion();
            rellenarJListMaterialEnvasar();
            vista.jListMaterialEnvasar.setSelectedIndex(0);

        } else {
            acabarEdicion();
            controlarBotonesMoverse();
            mostrarDatosMaterialEnvasar();
        }
    }

    //Realizar búsquedas
    public void botonBuscar() {
        if (vista.jComboBoxBuscarMaterialEnvasar.getSelectedIndex() == 0) {
            listaMaterialEnvasar = modelo.selectMaterialDeEnvasadoPorNombre(vista.jTextFieldBuscarMaterialEnvasar.getText());
        } else if (vista.jComboBoxBuscarMaterialEnvasar.getSelectedIndex() == 1) {
            if (vista.jTextFieldBuscarMaterialEnvasar.getText().toLowerCase().equals("sí") || vista.jTextFieldBuscarMaterialEnvasar.getText().toLowerCase().equals("si")) {
                listaMaterialEnvasar = modelo.selectMaterialDeEnvasadoTenemos(true);
            } else if (vista.jTextFieldBuscarMaterialEnvasar.getText().toLowerCase().equals("no")) {
                listaMaterialEnvasar = modelo.selectMaterialDeEnvasadoTenemos(false);
            }
        }
        rellenarJListMaterialEnvasar();
        vista.jListMaterialEnvasar.setSelectedIndex(0);
        if (listaMaterialEnvasar.size() < 1) {
            JOptionPane.showMessageDialog(vista, "No hay materiales para envasar que cumplan las condiciones de la búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(vista, "Se han reestablecido las condiciones de búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            recargarDatosQuitarBuscar();
        } else {
            JOptionPane.showMessageDialog(vista, "Se han encontrado " + listaMaterialEnvasar.size() + " materiales para envasar", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    //Quitar el filtro de las búsquedas
    public void recargarDatosQuitarBuscar() {
        vista.jTextFieldBuscarMaterialEnvasar.setText("");
        recargarDatos();
    }

    // Recarga los datos de la base de datos
    public void recargarDatos() {
        listaMaterialEnvasar = modelo.selectAllMaterialDeEnvasado();
        rellenarJListMaterialEnvasar();
        vista.jListMaterialEnvasar.setSelectedIndex(0);
    }

    //Devuelve el material de envasar seleccionado actualmente
    public MaterialDeEnvasado getMaterialEnvasado() {
        return listaMaterialEnvasar.get(vista.jListMaterialEnvasar.getSelectedIndex());
    }
}
