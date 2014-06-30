package es.sacyl.caza.farmacia.controlador;

import es.sacyl.caza.farmacia.modelo.MaquinariaDAO;
import es.sacyl.caza.farmacia.modelo.clases.Maquinaria;
import es.sacyl.caza.farmacia.vista.PanelMaquinaria;
import es.sacyl.caza.farmacia.vista.VentanaEdicionPMS;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Enrique Martín Arenal
 *
 * Controla los eventos relacionados con el panel maquinaria
 *
 */
public class ControladorMaquinaria {

    private PanelMaquinaria vista;
    private ArrayList<Maquinaria> listaMaquinaria;
    private String[] maquinariaJList;
    private MaquinariaDAO modelo;
    private boolean nuevoPulsado = false;
    private VentanaEdicionPMS ventanaPms;

    public ControladorMaquinaria(PanelMaquinaria vista) {
        this.vista = vista;
        init();
        initListeners();
    }

    public ControladorMaquinaria(PanelMaquinaria vista, VentanaEdicionPMS ventanaPms) {
        this.vista = vista;
        this.ventanaPms = ventanaPms;
        init();
        initListeners();
    }

    //Inicialización de los objetos necesarios para acceder y mostrar los datos
    private void init() {
        modelo = new MaquinariaDAO();
        listaMaquinaria = modelo.selectAllMaquinaria();
        rellenarJListMaquinaria();
        vista.jListMaquinaria.setSelectedIndex(0);
        actualizarPosicion();
        mostrarDatosMaquinaria();
        pulsarCheckboxTenemos();
        acabarEdicion();
    }

    //Añade los listener de los eventos a los componentes
    private void initListeners() {
        vista.jListMaquinaria.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cambiarPosicionJListMaquinaria();
            }
        });
        vista.jButtonSiguienteMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseSiguiente();
            }
        });
        vista.jButtonAtrasMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseAnterior();
            }
        });
        vista.jButtonPrimeroMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoversePrimero();
            }
        });
        vista.jButtonUltimoMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseUltimo();
            }
        });
        vista.jButtonModificarMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarMaquinaria();
            }
        });
        vista.jButtonAceptarMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarPulsado();
            }
        });
        vista.jButtonCancelarMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarPulsado();
            }
        });
        vista.jButtonNuevoMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonNuevaMaquinaria();
            }
        });
        vista.jButtonBuscarMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscar();
            }
        });
        vista.jButtonQuitarBuscarMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargarDatosQuitarBuscar();
            }
        });
        vista.jButtonEliminarMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarMaquinaria();
            }
        });
        vista.jCheckBoxTenemosMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarCheckboxTenemos();
            }
        });
        vista.jTextFieldBuscarMaquinaria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    botonBuscar();
                }
            }
        });
    }

    //Llena el JListMaquinaria con los nombres de la maquinaria existentes en la base de datos
    private void rellenarJListMaquinaria() {
        maquinariaJList = new String[listaMaquinaria.size()];
        for (int i = 0; i < maquinariaJList.length; i++) {
            if (listaMaquinaria.get(i).isTenemos()) {
                maquinariaJList[i] = "Sí - " + listaMaquinaria.get(i).getMaquinaria();
            } else {
                maquinariaJList[i] = "No - " + listaMaquinaria.get(i).getMaquinaria();
            }
        }
        vista.jListMaquinaria.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return maquinariaJList.length;
            }

            @Override
            public Object getElementAt(int i) {
                return maquinariaJList[i];
            }
        });
    }

    //Posición de la maquinaria en el que estamos situados
    private void actualizarPosicion() {
        String pos = "Maquinaria " + (vista.jListMaquinaria.getSelectedIndex() + 1) + " de " + maquinariaJList.length;
        vista.jLabelPosicionMaquinaria.setText(pos);
    }

    //Mostrar los datos del elemento seleccionado del jListMaquinaria
    private void mostrarDatosMaquinaria() {
        if (vista.jListMaquinaria.getSelectedIndex() > -1) {
            vista.jTextAreaMaquinaria.setText(listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex()).getMaquinaria());
            vista.jLabelIdMaquinaria.setText(listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex()).getIdMaquinaria() + "");
            if (!nuevoPulsado) {
                if (listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex()).isTenemos()) {
                    vista.jCheckBoxTenemosMaquinaria.setSelected(true);
                } else {
                    vista.jCheckBoxTenemosMaquinaria.setSelected(false);
                }
            }

        }
    }

    //Recoger los datos de los controloes en el elemento seleccionado del jListMaquinaria
    private void recogerDatosMaquinaria() {
        listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex()).setMaquinaria(vista.jTextAreaMaquinaria.getText());
        if (vista.jCheckBoxTenemosMaquinaria.isSelected()) {
            listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex()).setTenemos(true);
        } else {
            listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex()).setTenemos(false);
        }
    }

    // Evento al cambiar de posición en el jListMaquinaria
    private void cambiarPosicionJListMaquinaria() {
        actualizarPosicion();
        controlarBotonesMoverse();
        mostrarDatosMaquinaria();
        pulsarCheckboxTenemos();
    }

    // Activar/desactivar botones de siguiente,anterior,primero,último 
    private void controlarBotonesMoverse() {
        if (vista.jListMaquinaria.getSelectedIndex() == 0) {
            vista.jButtonAtrasMaquinaria.setEnabled(false);
            vista.jButtonPrimeroMaquinaria.setEnabled(false);
        } else {
            vista.jButtonAtrasMaquinaria.setEnabled(true);
            vista.jButtonPrimeroMaquinaria.setEnabled(true);
        }
        if (vista.jListMaquinaria.getSelectedIndex() == listaMaquinaria.size() - 1) {
            vista.jButtonSiguienteMaquinaria.setEnabled(false);
            vista.jButtonUltimoMaquinaria.setEnabled(false);
        } else {
            vista.jButtonSiguienteMaquinaria.setEnabled(true);
            vista.jButtonUltimoMaquinaria.setEnabled(true);
        }
    }

    //Poner si/no en el checkbox de tenemos dependiendo de si está o no seleccionado
    private void pulsarCheckboxTenemos() {
        if (vista.jCheckBoxTenemosMaquinaria.isSelected()) {
            vista.jCheckBoxTenemosMaquinaria.setText("Sí");
        } else {
            vista.jCheckBoxTenemosMaquinaria.setText("No");
        }
    }

    //Moverse a la siguiente maquinaria de la lista
    protected void botonMoverseSiguiente() {
        vista.jListMaquinaria.setSelectedIndex(vista.jListMaquinaria.getSelectedIndex() + 1);
        if (vista.jScrollPaneListaMaquinaria.getVerticalScrollBar().getValue() - (vista.jListMaquinaria.getSelectedIndex() * 22) < -308) {
            vista.jScrollPaneListaMaquinaria.getVerticalScrollBar().setValue(((vista.jListMaquinaria.getSelectedIndex() * 22) - 308));
        }
    }

    //Moverse a la anterior maquinaria de la lista
    protected void botonMoverseAnterior() {
        vista.jListMaquinaria.setSelectedIndex(vista.jListMaquinaria.getSelectedIndex() - 1);
        if (vista.jScrollPaneListaMaquinaria.getVerticalScrollBar().getValue() - (vista.jListMaquinaria.getSelectedIndex() * 22) > 0) {
            vista.jScrollPaneListaMaquinaria.getVerticalScrollBar().setValue(((vista.jListMaquinaria.getSelectedIndex() * 22)));
        }
    }

    //Moverse a la primer maquinaria de la lista
    protected void botonMoversePrimero() {
        vista.jListMaquinaria.setSelectedIndex(0);
        vista.jScrollPaneListaMaquinaria.getVerticalScrollBar().setValue(0);

    }

    //Moverse a la última maquinaria de la lista
    protected void botonMoverseUltimo() {
        vista.jListMaquinaria.setSelectedIndex(listaMaquinaria.size() - 1);
        vista.jScrollPaneListaMaquinaria.getVerticalScrollBar().setValue(((vista.jListMaquinaria.getSelectedIndex() * 22) - 308));

    }

    //Modificar una maquinaria
    protected void pulsarBotonModificarMaquinaria() {
        comenzarEdicion();
        nuevoPulsado = false;
    }

    //Nuevo maquinaria
    protected void pulsarBotonNuevaMaquinaria() {

        nuevoPulsado = true;
        Maquinaria maq = new Maquinaria();
        listaMaquinaria.add(maq);
        rellenarJListMaquinaria();
        vista.jListMaquinaria.setSelectedIndex(listaMaquinaria.size() - 1);
        comenzarEdicion();
    }

    //Eliminar maquinaria
    protected void pulsarBotonEliminarMaquinaria() {
        if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar la maquinaria actual?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
            if (listaMaquinaria.size() < 2) {
                JOptionPane.showMessageDialog(vista, "No se pueden eliminar todas las maquinas visibles. \nPruebe a quitar algún filtro de busqueda", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                modelo.eliminarMaquinaria(listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex()));
                listaMaquinaria.remove(listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex()));
                rellenarJListMaquinaria();
                vista.jListMaquinaria.setSelectedIndex(0);
            }
        }
    }

    // Habilitar controles para permitir la edición de los datos de una maquinaria
    private void comenzarEdicion() {
        vista.jTextAreaMaquinaria.setEditable(true);
        vista.jCheckBoxTenemosMaquinaria.setEnabled(true);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListMaquinaria.setEnabled(false);
        vista.jButtonAtrasMaquinaria.setEnabled(false);
        vista.jButtonEliminarMaquinaria.setEnabled(false);
        vista.jButtonNuevoMaquinaria.setEnabled(false);
        vista.jButtonPrimeroMaquinaria.setEnabled(false);
        vista.jButtonSiguienteMaquinaria.setEnabled(false);
        vista.jButtonUltimoMaquinaria.setEnabled(false);
        //deshabilita el resto de pestañas
        if (ventanaPms != null) {
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(0, false);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(1, false);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(2, false);
        }

        vista.jButtonBuscarMaquinaria.setEnabled(false);
        vista.jButtonQuitarBuscarMaquinaria.setEnabled(false);
        vista.jTextFieldBuscarMaquinaria.setEnabled(false);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosMaquinaria.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Edición de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        vista.jPanelListaMaquinaria.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Lista de maquinaria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));

        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jButtonAceptarMaquinaria.setVisible(true);
        vista.jButtonCancelarMaquinaria.setVisible(true);

        //Da el foco al primer componente editable
        vista.jCheckBoxTenemosMaquinaria.transferFocusBackward();
    }

    // lo contrario de comenzarEdicion()
    private void acabarEdicion() {
        vista.jTextAreaMaquinaria.setEditable(false);
        vista.jCheckBoxTenemosMaquinaria.setEnabled(false);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListMaquinaria.setEnabled(true);
        vista.jButtonAtrasMaquinaria.setEnabled(true);
        vista.jButtonEliminarMaquinaria.setEnabled(true);
        vista.jButtonNuevoMaquinaria.setEnabled(true);
        vista.jButtonPrimeroMaquinaria.setEnabled(true);
        vista.jButtonSiguienteMaquinaria.setEnabled(true);
        vista.jButtonUltimoMaquinaria.setEnabled(true);
        //deshabilita el resto de pestañas
        if (ventanaPms != null) {
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(0, true);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(1, true);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(2, true);
        }
        vista.jButtonBuscarMaquinaria.setEnabled(true);
        vista.jButtonQuitarBuscarMaquinaria.setEnabled(true);
        vista.jTextFieldBuscarMaquinaria.setEnabled(true);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosMaquinaria.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Edición de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        vista.jPanelListaMaquinaria.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Lista de maquinaria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));

        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jButtonAceptarMaquinaria.setVisible(false);
        vista.jButtonCancelarMaquinaria.setVisible(false);

    }

    //Pulsar boton de aceptar
    public void botonAceptarPulsado() {
        if (comprobarCamposObligatorios()) {
            try {
                if (nuevoPulsado) {
                    recogerDatosMaquinaria();
                    modelo.nuevoMaquinaria(listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex()));
                    acabarEdicion();
                    rellenarJListMaquinaria();
                    vista.jListMaquinaria.setSelectedIndex(listaMaquinaria.size() - 1);

                } else {
                    recogerDatosMaquinaria();
                    modelo.modificarMaquinaria(listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex()));
                    int posicion = vista.jListMaquinaria.getSelectedIndex();
                    acabarEdicion();
                    rellenarJListMaquinaria();
                    vista.jListMaquinaria.setSelectedIndex(posicion);

                }
            } catch (org.hibernate.exception.DataException e) {
                JOptionPane.showMessageDialog(vista, "El nombre de la maquinaria es demasiado larga, por favor hágalo más corto.", "Error", JOptionPane.WARNING_MESSAGE);

            } catch (ConstraintViolationException e) {
                JOptionPane.showMessageDialog(vista, "La maquinaria introducida ya existe, introduza otra", "Error", JOptionPane.WARNING_MESSAGE);
            }

        }

    }

    //Comprobar que los campos obligatorios no están vacios
    public boolean comprobarCamposObligatorios() {
        if (vista.jTextAreaMaquinaria.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Los siguiente campos son obligatorios \n\t-Maquinaria\n\t-Tenemos", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Pulsar boton de cancelar
    public void botonCancelarPulsado() {
        if (nuevoPulsado) {
            listaMaquinaria.remove(listaMaquinaria.size() - 1);
            acabarEdicion();
            rellenarJListMaquinaria();
            vista.jListMaquinaria.setSelectedIndex(0);

        } else {
            acabarEdicion();
            controlarBotonesMoverse();
            mostrarDatosMaquinaria();
        }
    }

    //Realizar búsquedas
    public void botonBuscar() {
        if (vista.jComboBoxBuscarMaquinaria.getSelectedIndex() == 0) {
            listaMaquinaria = modelo.selectMaquinariaPorNombre(vista.jTextFieldBuscarMaquinaria.getText());
        } else if (vista.jComboBoxBuscarMaquinaria.getSelectedIndex() == 1) {
            if (vista.jTextFieldBuscarMaquinaria.getText().toLowerCase().equals("sí") || vista.jTextFieldBuscarMaquinaria.getText().toLowerCase().equals("si")) {
                listaMaquinaria = modelo.selectMaquinariaTenemos(true);
            } else if (vista.jTextFieldBuscarMaquinaria.getText().toLowerCase().equals("no")) {
                listaMaquinaria = modelo.selectMaquinariaTenemos(false);
            }
        }
        rellenarJListMaquinaria();
        vista.jListMaquinaria.setSelectedIndex(0);
        if (listaMaquinaria.size() < 1) {
            JOptionPane.showMessageDialog(vista, "No hay Maquinaria que cumplan las condiciones de la búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(vista, "Se han reestablecido las condiciones de búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            recargarDatosQuitarBuscar();
        } else {
            JOptionPane.showMessageDialog(vista, "Se han encontrado " + listaMaquinaria.size() + " Maquinarias", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    //Quitar el filtro de las búsquedas también usado al entrar en esta pestaña
    public void recargarDatosQuitarBuscar() {
        vista.jTextFieldBuscarMaquinaria.setText("");
        recargarDatos();
    }

    // Recarga los datos de la base de datos
    public void recargarDatos() {
        listaMaquinaria = modelo.selectAllMaquinaria();
        rellenarJListMaquinaria();
        vista.jListMaquinaria.setSelectedIndex(0);
    }

    //Devuelve la maquinaria escogida
    public Maquinaria getMaquinariaElegida() {
        return listaMaquinaria.get(vista.jListMaquinaria.getSelectedIndex());
    }

}
