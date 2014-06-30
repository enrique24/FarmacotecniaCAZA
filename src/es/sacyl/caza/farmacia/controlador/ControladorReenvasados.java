package es.sacyl.caza.farmacia.controlador;

import es.sacyl.caza.farmacia.modelo.ReenvasadosDAO;
import es.sacyl.caza.farmacia.modelo.ReenvasadosDataSource;
import es.sacyl.caza.farmacia.modelo.clases.Reenvasados;
import es.sacyl.caza.farmacia.vista.VentanaReenvasados;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Maneja los eventos de la ventanaReenvasados
 *
 * @author Enrique Martín Arenal
 */
public class ControladorReenvasados {

    private VentanaReenvasados vista;
    private ArrayList<Reenvasados> listaReenvasados;
    private String[] reenvasadosJList;
    private ReenvasadosDAO modelo;
    private boolean nuevoPulsado = false;

    public ControladorReenvasados(VentanaReenvasados vista) {
        this.vista = vista;
        init();
        initListeners();
    }

    //Inicialización de los objetos necesarios para acceder y mostrar los datos
    private void init() {
        modelo = new ReenvasadosDAO();
        listaReenvasados = modelo.selectAllReenvasados();
        rellenarJListReenvasados();
        vista.jListReenvasados.setSelectedIndex(0);
        actualizarPosicion();
        mostrarDatosReenvasados();
        acabarEdicion();
    }

    //Añade los listener de los eventos a los componentes
    private void initListeners() {
        vista.jListReenvasados.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cambiarPosicionJListReenvasados();
            }
        });
        vista.jButtonSiguienteReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseSiguiente();
            }
        });
        vista.jButtonAtrasReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseAnterior();
            }
        });
        vista.jButtonPrimeroReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoversePrimero();
            }
        });
        vista.jButtonUltimoReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseUltimo();
            }
        });
        vista.jButtonModificarReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarReenvasados();
            }
        });
        vista.jButtonAceptarReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarPulsado();
            }
        });
        vista.jButtonCancelarReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarPulsado();
            }
        });
        vista.jButtonNuevoReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonNuevoReenvasados();
            }
        });
        vista.jButtonBuscarReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscar();
            }
        });
        vista.jButtonQuitarBuscarReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargarDatosQuitarBuscar();
            }
        });
        vista.jButtonEliminarReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarReenvasados();
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
                pulsarBotonNuevoReenvasados();
            }
        });
        vista.jMenuItemEditarEliminar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarReenvasados();
            }
        });
        vista.jMenuItemEditarModificar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarReenvasados();
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
        vista.jButtonImprimirReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonImprimir();
            }
        });
        vista.jButtonVistaPreviaReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonVistaPrevia();
            }
        });
        vista.jButtonVolverReenvasados.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vista.jDialogImprimirReenvasados.setVisible(false);
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
        vista.jTextFieldBuscarReenvasados.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    botonBuscar();
                }
            }
        });
    }

    //Acción de copiar
    public void copiar() {
        if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaDescripcionReenvasados)) {
            vista.jTextAreaDescripcionReenvasados.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaPrincipioActivoReenvasados)) {
            vista.jTextAreaPrincipioActivoReenvasados.copy();
        }
    }

    //Acción de pegar
    public void pegar() {
        //solo se puede pegar si se estan editando los datos
        if (vista.jButtonAceptarReenvasados.isVisible()) {
            //Para pegar hacer un switch con todos los componentes en donde se puede pegar

            if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaDescripcionReenvasados)) {
                vista.jTextAreaDescripcionReenvasados.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaPrincipioActivoReenvasados)) {
                vista.jTextAreaPrincipioActivoReenvasados.paste();
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Para poder pegar hay que pulsar antes el botón de modificar los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cerrarVentana() {
        vista.dispose();
    }

    //Llena el JListReenvasados con los nombres de los materiales para envasar existentes en la base de datos
    private void rellenarJListReenvasados() {
        reenvasadosJList = new String[listaReenvasados.size()];
        for (int i = 0; i < reenvasadosJList.length; i++) {
            reenvasadosJList[i] = listaReenvasados.get(i).getDescripcion();
        }
        vista.jListReenvasados.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return reenvasadosJList.length;
            }

            @Override
            public Object getElementAt(int i) {
                return reenvasadosJList[i];
            }
        });
    }

    //Posición del  material para envasar en el que estamos situados
    private void actualizarPosicion() {
        String pos = "Reenvasado " + (vista.jListReenvasados.getSelectedIndex() + 1) + " de " + reenvasadosJList.length;
        vista.jLabelPosicionReenvasados.setText(pos);
    }

    //Mostrar los datos del elemento seleccionado del jListReenvasados
    private void mostrarDatosReenvasados() {
        if (vista.jListReenvasados.getSelectedIndex() > -1) {
            vista.jTextAreaDescripcionReenvasados.setText(listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()).getDescripcion());
            vista.jLabelIdReenvasados.setText(listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()).getIdReenvasado() + "");
            vista.jTextAreaPrincipioActivoReenvasados.setText(listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()).getPrincipioActivo());
        }

    }

    //Recoger los datos de los controloes en el elemento seleccionado del jListReenvasados
    private void recogerDatosReenvasados() {
        listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()).setDescripcion(vista.jTextAreaDescripcionReenvasados.getText());
        listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()).setPrincipioActivo(vista.jTextAreaPrincipioActivoReenvasados.getText());
    }

    // Evento al cambiar de posición en el jListReenvasados
    private void cambiarPosicionJListReenvasados() {
        actualizarPosicion();
        controlarBotonesMoverse();
        mostrarDatosReenvasados();
    }

    // Activar/desactivar botones de siguiente,anterior,primero,último 
    private void controlarBotonesMoverse() {
        if (vista.jListReenvasados.getSelectedIndex() == 0) {
            vista.jButtonAtrasReenvasados.setEnabled(false);
            vista.jButtonPrimeroReenvasados.setEnabled(false);
        } else {
            vista.jButtonAtrasReenvasados.setEnabled(true);
            vista.jButtonPrimeroReenvasados.setEnabled(true);
        }
        if (vista.jListReenvasados.getSelectedIndex() == listaReenvasados.size() - 1) {
            vista.jButtonSiguienteReenvasados.setEnabled(false);
            vista.jButtonUltimoReenvasados.setEnabled(false);
        } else {
            vista.jButtonSiguienteReenvasados.setEnabled(true);
            vista.jButtonUltimoReenvasados.setEnabled(true);
        }
    }

    //Moverse al siguiente material para envasar de la lista
    protected void botonMoverseSiguiente() {
        vista.jListReenvasados.setSelectedIndex(vista.jListReenvasados.getSelectedIndex() + 1);
        if (vista.jScrollPaneListaMaterial.getVerticalScrollBar().getValue() - (vista.jListReenvasados.getSelectedIndex() * 22) < -308) {
            vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue(((vista.jListReenvasados.getSelectedIndex() * 22) - 308));
        }
    }

    //Moverse al anterior material para envasar de la lista
    protected void botonMoverseAnterior() {
        vista.jListReenvasados.setSelectedIndex(vista.jListReenvasados.getSelectedIndex() - 1);
        if (vista.jScrollPaneListaMaterial.getVerticalScrollBar().getValue() - (vista.jListReenvasados.getSelectedIndex() * 22) > 0) {
            vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue(((vista.jListReenvasados.getSelectedIndex() * 22)));
        }
    }

    //Moverse al primer material para envasar de la lista
    protected void botonMoversePrimero() {
        vista.jListReenvasados.setSelectedIndex(0);
        vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue(0);

    }

    //Moverse al última material para envasar de la lista
    protected void botonMoverseUltimo() {
        vista.jListReenvasados.setSelectedIndex(listaReenvasados.size() - 1);
        vista.jScrollPaneListaMaterial.getVerticalScrollBar().setValue(((vista.jListReenvasados.getSelectedIndex() * 22) - 308));

    }

    //Modificar un material para envasar
    protected void pulsarBotonModificarReenvasados() {
        comenzarEdicion();
        nuevoPulsado = false;
    }

    //Nuevo material para envasar
    protected void pulsarBotonNuevoReenvasados() {

        nuevoPulsado = true;
        Reenvasados reenv = new Reenvasados();
        listaReenvasados.add(reenv);
        rellenarJListReenvasados();
        vista.jListReenvasados.setSelectedIndex(listaReenvasados.size() - 1);
        comenzarEdicion();
    }

    //Eliminar material para envasar
    protected void pulsarBotonEliminarReenvasados() {
        if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el reenvasado actual?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
            if (listaReenvasados.size() < 2) {
                JOptionPane.showMessageDialog(vista, "No se pueden eliminar todos los reenvasados visibles. \nPruebe a quitar algún filtro de busqueda", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                modelo.eliminarReenvasados(listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()));
                listaReenvasados.remove(listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()));
                rellenarJListReenvasados();
                vista.jListReenvasados.setSelectedIndex(0);
            }
        }
    }

    // Habilitar controles para permitir la edición de los datos de un material para envasar
    private void comenzarEdicion() {
        vista.jTextAreaDescripcionReenvasados.setEditable(true);
        vista.jTextAreaPrincipioActivoReenvasados.setEditable(true);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListReenvasados.setEnabled(false);
        vista.jButtonAtrasReenvasados.setEnabled(false);
        vista.jButtonEliminarReenvasados.setEnabled(false);
        vista.jButtonNuevoReenvasados.setEnabled(false);
        vista.jButtonPrimeroReenvasados.setEnabled(false);
        vista.jButtonSiguienteReenvasados.setEnabled(false);
        vista.jButtonUltimoReenvasados.setEnabled(false);
        vista.jButtonBuscarReenvasados.setEnabled(false);
        vista.jButtonQuitarBuscarReenvasados.setEnabled(false);
        vista.jTextFieldBuscarReenvasados.setEnabled(false);
        vista.jButtonImprimirReenvasados.setEnabled(false);
        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosReenvasados.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Edición de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        vista.jPanelListaReenvasados.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Lista de materiales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));

        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jButtonAceptarReenvasados.setVisible(true);
        vista.jButtonCancelarReenvasados.setVisible(true);

        //Da el foco al primer componente editable
        vista.jTextAreaPrincipioActivoReenvasados.transferFocusBackward();
    }

    // lo contrario de comenzarEdicion()
    private void acabarEdicion() {
        vista.jTextAreaDescripcionReenvasados.setEditable(false);
        vista.jTextAreaPrincipioActivoReenvasados.setEditable(false);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListReenvasados.setEnabled(true);
        vista.jButtonAtrasReenvasados.setEnabled(true);
        vista.jButtonEliminarReenvasados.setEnabled(true);
        vista.jButtonNuevoReenvasados.setEnabled(true);
        vista.jButtonPrimeroReenvasados.setEnabled(true);
        vista.jButtonSiguienteReenvasados.setEnabled(true);
        vista.jButtonUltimoReenvasados.setEnabled(true);
        vista.jButtonBuscarReenvasados.setEnabled(true);
        vista.jButtonQuitarBuscarReenvasados.setEnabled(true);
        vista.jTextFieldBuscarReenvasados.setEnabled(true);
        vista.jButtonImprimirReenvasados.setEnabled(true);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosReenvasados.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Edición de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        vista.jPanelListaReenvasados.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Lista de materiales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)));
        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jButtonAceptarReenvasados.setVisible(false);
        vista.jButtonCancelarReenvasados.setVisible(false);

    }

    //Pulsar boton de aceptar
    public void botonAceptarPulsado() {
        if (comprobarCamposObligatorios()) {
            try {
                if (nuevoPulsado) {
                    recogerDatosReenvasados();
                    modelo.nuevoReenvasados(listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()));
                    acabarEdicion();
                    rellenarJListReenvasados();
                    vista.jListReenvasados.setSelectedIndex(listaReenvasados.size() - 1);

                } else {
                    recogerDatosReenvasados();
                    modelo.modificarReenvasados(listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()));
                    int posicion = vista.jListReenvasados.getSelectedIndex();
                    acabarEdicion();
                    rellenarJListReenvasados();
                    vista.jListReenvasados.setSelectedIndex(posicion);

                }
            } catch (org.hibernate.exception.DataException e) {
                if (e.getCause().toString().contains("DESCRIPCION")) {
                    JOptionPane.showMessageDialog(vista, "La descripción del reenvasado es demasiado larga, el máximo son 25 caracteres para que quepa en la etiqueta", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(vista, "Los datos extra del reenvasado son demasiado largos, el máximo son 25 caracteres para que quepan en la etiqueta", "Error", JOptionPane.WARNING_MESSAGE);
                }

            } catch (ConstraintViolationException e) {
                JOptionPane.showMessageDialog(vista, "El reenvasado introducido ya existe, introduzca otro", "Error", JOptionPane.WARNING_MESSAGE);
            }

        }

    }

    //Comprobar que los campos obligatorios no están vacios
    public boolean comprobarCamposObligatorios() {
        if (vista.jTextAreaDescripcionReenvasados.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Los siguiente campos son obligatorios \n\t-Descripción", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Pulsar boton de cancelar
    public void botonCancelarPulsado() {
        if (nuevoPulsado) {
            listaReenvasados.remove(listaReenvasados.size() - 1);
            acabarEdicion();
            rellenarJListReenvasados();
            vista.jListReenvasados.setSelectedIndex(0);

        } else {
            acabarEdicion();
            controlarBotonesMoverse();
            mostrarDatosReenvasados();
        }
    }

    //Realizar búsquedas
    public void botonBuscar() {
        if (vista.jComboBoxBuscarReenvasados.getSelectedIndex() == 0) {
            listaReenvasados = modelo.selectReenvasadosPorDescripcion(vista.jTextFieldBuscarReenvasados.getText());
        } else if (vista.jComboBoxBuscarReenvasados.getSelectedIndex() == 1) {
            listaReenvasados = modelo.selectReenvasadosPorPrincipioActivo(vista.jTextFieldBuscarReenvasados.getText());
        }
        rellenarJListReenvasados();
        vista.jListReenvasados.setSelectedIndex(0);
        if (listaReenvasados.size() < 1) {
            JOptionPane.showMessageDialog(vista, "No hay reenvasados que cumplan las condiciones de la búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(vista, "Se han reestablecido las condiciones de búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            recargarDatosQuitarBuscar();
        } else {
            JOptionPane.showMessageDialog(vista, "Se han encontrado " + listaReenvasados.size() + " reenvasados", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    //Quitar el filtro de las búsquedas
    public void recargarDatosQuitarBuscar() {
        vista.jTextFieldBuscarReenvasados.setText("");
        recargarDatos();
    }

    // Recarga los datos de la base de datos
    public void recargarDatos() {
        listaReenvasados = modelo.selectAllReenvasados();
        rellenarJListReenvasados();
        vista.jListReenvasados.setSelectedIndex(0);
    }

    //Muestra un diálogo en el que se pide el lote y la fecha de caducidad del reenvasado
    public void pulsarBotonImprimir() {
        //En principio se le da una caducidad de un año desde el día que se reenvasa
        java.util.Date hoy = new java.util.Date();
        Date fecha = addDaysToDate(hoy, 365);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");
        vista.jFormattedTextFieldFechaCaducidadReenvasados.setText(formatter.format(fecha));
        vista.jTextFieldLoteReenvasados.setText("");
        vista.jDialogImprimirReenvasados.setSize(510, 350);
        vista.jDialogImprimirReenvasados.setLocationRelativeTo(null);
        vista.jDialogImprimirReenvasados.setVisible(true);
    }

    //Añade a la fecha que se le pase el número de dias que se le pase
    public Date addDaysToDate(final Date date, int noOfDays) {
        Date newDate = new Date(date.getTime());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(newDate);
        calendar.add(Calendar.DATE, noOfDays);
        newDate.setTime(calendar.getTime().getTime());

        return newDate;
    }

    //Comprueba los datos introducidos y muestra un reporte del reenvasado seleccionado
    public void pulsarBotonVistaPrevia() {

        //Mostrar el reporte solo si los campos de lote y fecha han sido rellenados
        if (!vista.jTextFieldLoteReenvasados.getText().trim().equals("") && !vista.jFormattedTextFieldFechaCaducidadReenvasados.getText().equals("")) {
            listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()).setCaduca(vista.jFormattedTextFieldFechaCaducidadReenvasados.getText());
            listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()).setLot(vista.jTextFieldLoteReenvasados.getText());
            if (vista.jCheckBoxDatosExtra.isSelected()) {
                mostrarReporte(14, "reportReenvasadosPrincipioActivo.jasper");
            } else {
                mostrarReporte(14, "reportReenvasados.jasper");
            }
        } else {
            JOptionPane.showMessageDialog(vista.jDialogImprimirReenvasados, "Han de rellenarse los datos del lote y de la fecha de caducidad", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    //muestra el reporte eligiendo el nombre y el número de filas que va a tener
    public void mostrarReporte(int filas, String nombreReporte) {
        ArrayList<Reenvasados> reenvImprimir = new ArrayList<>();
        //Llenar la hoja de etiquetas
        for (int i = 0; i < filas; i++) {
            reenvImprimir.add(listaReenvasados.get(vista.jListReenvasados.getSelectedIndex()));
        }

        ReenvasadosDataSource datasource = new ReenvasadosDataSource(reenvImprimir);
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(nombreReporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            //  jviewer.setAlwaysOnTop(true);
            jviewer.setTitle("Imprimir");
            vista.jDialogImprimirReenvasados.setVisible(false);
            jviewer.setVisible(true);
            jviewer.setExtendedState(Frame.MAXIMIZED_BOTH);

        } catch (JRException e1) {
            JOptionPane.showMessageDialog(vista.jDialogImprimirReenvasados, "Error al inicializar el reporte", "Fallo", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }

}
