package es.sacyl.caza.farmacia.controlador;

import es.sacyl.caza.farmacia.modelo.MaterialElaborarDAO;
import es.sacyl.caza.farmacia.modelo.clases.MaterialParaElaborar;
import es.sacyl.caza.farmacia.vista.PanelMaterialElaboracion;
import es.sacyl.caza.farmacia.vista.VentanaEdicionPMS;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Enrique Martín Arenal
 *
 * Maneja los eventos relacionados con la pestaña material elaborar de la
 * VentanaEdicionPMS
 */
public class ControladorMaterialElaborar {

    private PanelMaterialElaboracion vista;
    private ArrayList<MaterialParaElaborar> listaMaterialElaborar;
    private String[] MaterialElaborarJList;
    private MaterialElaborarDAO modelo;
    private boolean nuevoPulsado = false;
    private VentanaEdicionPMS ventanaPms;

    public ControladorMaterialElaborar(PanelMaterialElaboracion vista) {
        this.vista = vista;
        init();
        initListeners();
    }

    public ControladorMaterialElaborar(PanelMaterialElaboracion vista, VentanaEdicionPMS ventanaPms) {
        this.vista = vista;
        this.ventanaPms = ventanaPms;
        init();
        initListeners();
    }

    //Inicialización de los objetos necesarios para acceder y mostrar los datos
    private void init() {
        modelo = new MaterialElaborarDAO();
        listaMaterialElaborar = modelo.selectAllMaterialElaborar();
        rellenarJListMaterialElaborar();
        vista.jListMaterialElaborar.setSelectedIndex(0);
        actualizarPosicion();
        mostrarDatosMaterialElaborar();
        pulsarCheckboxTenemos();
        acabarEdicion();
    }

    //Añade los listener de los eventos a los componentes
    private void initListeners() {
        vista.jListMaterialElaborar.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cambiarPosicionJListMaterialElaborar();
            }
        });
        vista.jButtonSiguienteMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseSiguiente();
            }
        });
        vista.jButtonAtrasMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseAnterior();
            }
        });
        vista.jButtonPrimeroMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoversePrimero();
            }
        });
        vista.jButtonUltimoMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseUltimo();
            }
        });
        vista.jButtonModificarMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarMaterialElaborar();
            }
        });
        vista.jButtonAceptarMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarPulsado();
            }
        });
        vista.jButtonCancelarMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarPulsado();
            }
        });
        vista.jButtonNuevoMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonNuevoMaterialElaborar();
            }
        });
        vista.jButtonBuscarMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscar();
            }
        });
        vista.jButtonQuitarBuscarMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargarDatosQuitarBuscar();
            }
        });
        vista.jButtonEliminarMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonElminiarMaterialElaborar();
            }
        });
        vista.jCheckBoxTenemosMaterialElaborar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarCheckboxTenemos();
            }
        });
       vista.jTextFieldBuscarMaterialElaborar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    botonBuscar();
                }
            }
        });
    }

    //Llena el JListMaterialElaborar con los nombres de los materiales para elaborar existentes en la base de datos
    private void rellenarJListMaterialElaborar() {
        MaterialElaborarJList = new String[listaMaterialElaborar.size()];
        for (int i = 0; i < MaterialElaborarJList.length; i++) {
            if (listaMaterialElaborar.get(i).isTenemos()) {
                MaterialElaborarJList[i] = "Sí - " + listaMaterialElaborar.get(i).getMaterial();
            } else {
                MaterialElaborarJList[i] = "No - " + listaMaterialElaborar.get(i).getMaterial();
            }
        }
        vista.jListMaterialElaborar.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return MaterialElaborarJList.length;
            }

            @Override
            public Object getElementAt(int i) {
                return MaterialElaborarJList[i];
            }
        });
    }

    //Posición del material para elaborar en el que estamos situados
    private void actualizarPosicion() {
        String pos = "Material para elaborar " + (vista.jListMaterialElaborar.getSelectedIndex() + 1) + " de " + MaterialElaborarJList.length;
        vista.jLabelPosicionMaterialElaborar.setText(pos);
    }

    //Mostrar los datos del elemento seleccionado del jListMaterialElaborar
    private void mostrarDatosMaterialElaborar() {
        if (vista.jListMaterialElaborar.getSelectedIndex() > -1) {
            vista.jTextAreaMaterialElaborar.setText(listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex()).getMaterial());
            vista.jLabelIdMaterialElaborar.setText(listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex()).getIdMaterial() + "");
            if (!nuevoPulsado) {
                if (listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex()).isTenemos()) {
                    vista.jCheckBoxTenemosMaterialElaborar.setSelected(true);
                } else {
                    vista.jCheckBoxTenemosMaterialElaborar.setSelected(false);
                }
            }

        }
    }

    //Recoger los datos de los controloes en el elemento seleccionado del jListMaterialElaborar
    private void recogerDatosMaterialElaborar() {
        listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex()).setMaterial(vista.jTextAreaMaterialElaborar.getText());
        if (vista.jCheckBoxTenemosMaterialElaborar.isSelected()) {
            listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex()).setTenemos(true);
        } else {
            listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex()).setTenemos(false);
        }
    }

    // Evento al cambiar de posición en el jListMaterialElaborar
    private void cambiarPosicionJListMaterialElaborar() {
        actualizarPosicion();
        controlarBotonesMoverse();
        mostrarDatosMaterialElaborar();
        pulsarCheckboxTenemos();
    }

    // Activar/desactivar botones de siguiente,anterior,primero,último 
    private void controlarBotonesMoverse() {
        if (vista.jListMaterialElaborar.getSelectedIndex() == 0) {
            vista.jButtonAtrasMaterialElaborar.setEnabled(false);
            vista.jButtonPrimeroMaterialElaborar.setEnabled(false);
        } else {
            vista.jButtonAtrasMaterialElaborar.setEnabled(true);
            vista.jButtonPrimeroMaterialElaborar.setEnabled(true);
        }
        if (vista.jListMaterialElaborar.getSelectedIndex() == listaMaterialElaborar.size() - 1) {
            vista.jButtonSiguienteMaterialElaborar.setEnabled(false);
            vista.jButtonUltimoMaterialElaborar.setEnabled(false);
        } else {
            vista.jButtonSiguienteMaterialElaborar.setEnabled(true);
            vista.jButtonUltimoMaterialElaborar.setEnabled(true);
        }
    }

    //Poner si/no en el checkbox de tenemos dependiendo de si está o no seleccionado
    private void pulsarCheckboxTenemos() {
        if (vista.jCheckBoxTenemosMaterialElaborar.isSelected()) {
            vista.jCheckBoxTenemosMaterialElaborar.setText("Sí");
        } else {
            vista.jCheckBoxTenemosMaterialElaborar.setText("No");
        }
    }

    //Moverse al siguiente material para elaborar de la lista
    protected void botonMoverseSiguiente() {
        vista.jListMaterialElaborar.setSelectedIndex(vista.jListMaterialElaborar.getSelectedIndex() + 1);
        if (vista.jScrollPaneListaMaterial.getVerticalScrollBar().getValue() - (vista.jListMaterialElaborar.getSelectedIndex() * 22) < -308) {
            vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue(((vista.jListMaterialElaborar.getSelectedIndex() * 22) - 308));
        }

    }

    //Moverse al anterior material para elaborar de la lista
    protected void botonMoverseAnterior() {
        vista.jListMaterialElaborar.setSelectedIndex(vista.jListMaterialElaborar.getSelectedIndex() - 1);
        if (vista.jScrollPaneListaMaterial.getVerticalScrollBar().getValue() - (vista.jListMaterialElaborar.getSelectedIndex() * 22) > 0) {
            vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue((vista.jListMaterialElaborar.getSelectedIndex() * 22));
        }

    }

    //Moverse al primer material para elaborar de la lista
    protected void botonMoversePrimero() {
        vista.jListMaterialElaborar.setSelectedIndex(0);
        vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue(0);

    }

    //Moverse al última material para elaborar de la lista
    protected void botonMoverseUltimo() {
        vista.jListMaterialElaborar.setSelectedIndex(listaMaterialElaborar.size() - 1);
        vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue((vista.jListMaterialElaborar.getSelectedIndex() * 22) - 308);

    }

    //Modificar un material para elaborar
    protected void pulsarBotonModificarMaterialElaborar() {
        comenzarEdicion();
        nuevoPulsado = false;
    }

    //Nuevo material para elaborar
    protected void pulsarBotonNuevoMaterialElaborar() {

        nuevoPulsado = true;
        MaterialParaElaborar maq = new MaterialParaElaborar();
        listaMaterialElaborar.add(maq);
        rellenarJListMaterialElaborar();
        vista.jListMaterialElaborar.setSelectedIndex(listaMaterialElaborar.size() - 1);
        comenzarEdicion();
    }

    //Eliminar el material para elaborar seleccionado
    protected void pulsarBotonElminiarMaterialElaborar() {
        if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el material para elaborar actual?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
            if (listaMaterialElaborar.size() < 2) {
                JOptionPane.showMessageDialog(vista, "No se pueden eliminar todos los materiales para elaborar visibles. \nPruebe a quitar algún filtro de busqueda", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                modelo.eliminarMaterialElaborar(listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex()));
                listaMaterialElaborar.remove(listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex()));
                rellenarJListMaterialElaborar();
                vista.jListMaterialElaborar.setSelectedIndex(0);
            }
        }
    }

    // Habilitar controles para permitir la edición de los datos de un material para elaborar
    private void comenzarEdicion() {
        vista.jTextAreaMaterialElaborar.setEditable(true);
        vista.jCheckBoxTenemosMaterialElaborar.setEnabled(true);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListMaterialElaborar.setEnabled(false);
        vista.jButtonAtrasMaterialElaborar.setEnabled(false);
        vista.jButtonEliminarMaterialElaborar.setEnabled(false);
        vista.jButtonNuevoMaterialElaborar.setEnabled(false);
        vista.jButtonPrimeroMaterialElaborar.setEnabled(false);
        vista.jButtonSiguienteMaterialElaborar.setEnabled(false);
        vista.jButtonUltimoMaterialElaborar.setEnabled(false);

        //deshabilita el resto de pestañas
        if (ventanaPms != null) {
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(0, false);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(1, false);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(2, false);
        }
        vista.jButtonBuscarMaterialElaborar.setEnabled(false);
        vista.jButtonQuitarBuscarMaterialElaborar.setEnabled(false);
        vista.jTextFieldBuscarMaterialElaborar.setEnabled(false);
        
        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosMaterialElaborar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Edición de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        vista.jPanelListaMaterialElaborar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Lista de materiales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));

        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jButtonAceptarMaterialElaborar.setVisible(true);
        vista.jButtonCancelarMaterialElaborar.setVisible(true);

        //Da el foco al primer componente editable
        vista.jCheckBoxTenemosMaterialElaborar.transferFocusBackward();
    }

    // lo contrario de comenzarEdicion()
    private void acabarEdicion() {
        vista.jTextAreaMaterialElaborar.setEditable(false);
        vista.jCheckBoxTenemosMaterialElaborar.setEnabled(false);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListMaterialElaborar.setEnabled(true);
        vista.jButtonAtrasMaterialElaborar.setEnabled(true);
        vista.jButtonEliminarMaterialElaborar.setEnabled(true);
        vista.jButtonNuevoMaterialElaborar.setEnabled(true);
        vista.jButtonPrimeroMaterialElaborar.setEnabled(true);
        vista.jButtonSiguienteMaterialElaborar.setEnabled(true);
        vista.jButtonUltimoMaterialElaborar.setEnabled(true);
        //deshabilita el resto de pestañas
        if (ventanaPms != null) {
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(0, true);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(1, true);
            ventanaPms.jTabbedPaneEdicionPMS.setEnabledAt(2, true);
        }
        vista.jButtonBuscarMaterialElaborar.setEnabled(true);
        vista.jButtonQuitarBuscarMaterialElaborar.setEnabled(true);
        vista.jTextFieldBuscarMaterialElaborar.setEnabled(true);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosMaterialElaborar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204,204,204), 2, true), "Edición de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        vista.jPanelListaMaterialElaborar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Lista de materiales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));

        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jButtonAceptarMaterialElaborar.setVisible(false);
        vista.jButtonCancelarMaterialElaborar.setVisible(false);

    }

    //Pulsar boton de aceptar
    public void botonAceptarPulsado() {
        if (comprobarCamposObligatorios()) {
            try {
                if (nuevoPulsado) {
                    recogerDatosMaterialElaborar();
                    modelo.nuevoMaterialElaborar(listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex()));
                    acabarEdicion();
                    rellenarJListMaterialElaborar();
                    vista.jListMaterialElaborar.setSelectedIndex(listaMaterialElaborar.size() - 1);

                } else {
                    recogerDatosMaterialElaborar();
                    modelo.modificarMaterialElaborar(listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex()));
                    int posicion = vista.jListMaterialElaborar.getSelectedIndex();
                    acabarEdicion();
                    rellenarJListMaterialElaborar();
                    vista.jListMaterialElaborar.setSelectedIndex(posicion);

                }
            } catch (org.hibernate.exception.DataException e) {
                JOptionPane.showMessageDialog(vista, "El nombre del material para elaborar es demasiado largo, por favor hágalo más corto.", "Error", JOptionPane.WARNING_MESSAGE);

            } catch (ConstraintViolationException e) {
                JOptionPane.showMessageDialog(vista, "El material para elaborar introducido ya existe, introduza otro", "Error", JOptionPane.WARNING_MESSAGE);
            }

        }

    }

    //Comprobar que los campos obligatorios no están vacios
    public boolean comprobarCamposObligatorios() {
        if (vista.jTextAreaMaterialElaborar.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Los siguiente campos son obligatorios \n\t-Material elaborar\n\t-Tenemos", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Pulsar boton de cancelar
    public void botonCancelarPulsado() {
        if (nuevoPulsado) {
            listaMaterialElaborar.remove(listaMaterialElaborar.size() - 1);
            acabarEdicion();
            rellenarJListMaterialElaborar();
            vista.jListMaterialElaborar.setSelectedIndex(0);

        } else {
            acabarEdicion();
            controlarBotonesMoverse();
            mostrarDatosMaterialElaborar();
        }
    }

    //Realizar búsquedas
    public void botonBuscar() {
        if (vista.jComboBoxBuscarMaterialElaborar.getSelectedIndex() == 0) {
            listaMaterialElaborar = modelo.selectMaterialElaborarPorNombre(vista.jTextFieldBuscarMaterialElaborar.getText());
        } else if (vista.jComboBoxBuscarMaterialElaborar.getSelectedIndex() == 1) {
            if (vista.jTextFieldBuscarMaterialElaborar.getText().toLowerCase().equals("sí") || vista.jTextFieldBuscarMaterialElaborar.getText().toLowerCase().equals("si")) {
                listaMaterialElaborar = modelo.selectMaterialElaborarTenemos(true);
            } else if (vista.jTextFieldBuscarMaterialElaborar.getText().toLowerCase().equals("no")) {
                listaMaterialElaborar = modelo.selectMaterialElaborarTenemos(false);
            }
        }
        rellenarJListMaterialElaborar();
        vista.jListMaterialElaborar.setSelectedIndex(0);
        if (listaMaterialElaborar.size() < 1) {
            JOptionPane.showMessageDialog(vista, "No hay materiales para elaborar que cumplan las condiciones de la búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(vista, "Se han reestablecido las condiciones de búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            recargarDatosQuitarBuscar();
        } else {
            JOptionPane.showMessageDialog(vista, "Se han encontrado " + listaMaterialElaborar.size() + " materiales para elaborar", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    //Quitar el filtro de las búsquedas
    public void recargarDatosQuitarBuscar() {
        vista.jTextFieldBuscarMaterialElaborar.setText("");
        recargarDatos();
    }

    // Recarga los datos de la base de datos
    public void recargarDatos() {
        listaMaterialElaborar = modelo.selectAllMaterialElaborar();
        rellenarJListMaterialElaborar();
        vista.jListMaterialElaborar.setSelectedIndex(0);
    }

    //Duelve el material para elaborar seleccionado actualmente
    public MaterialParaElaborar getMaterialParaElaborar() {
        return listaMaterialElaborar.get(vista.jListMaterialElaborar.getSelectedIndex());
    }

}
