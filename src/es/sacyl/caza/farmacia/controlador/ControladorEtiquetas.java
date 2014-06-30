package es.sacyl.caza.farmacia.controlador;

import es.sacyl.caza.farmacia.modelo.EtiquetasDAO;
import es.sacyl.caza.farmacia.modelo.EtiquetasDataSource;
import es.sacyl.caza.farmacia.modelo.FichasDeMedicamentosDAO;
import es.sacyl.caza.farmacia.modelo.clases.Etiquetas;
import es.sacyl.caza.farmacia.modelo.clases.FichasDeMedicamentos;
import es.sacyl.caza.farmacia.vista.VentanaEtiquetas;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Enrique Martín Arenal
 */
public class ControladorEtiquetas {

    private VentanaEtiquetas vista;
    private ArrayList<Etiquetas> listaEtiquetas;
    private ArrayList<FichasDeMedicamentos> listaFichaMedicamentos;
    private String[] maquinariaJList;
    private EtiquetasDAO modelo;
    private boolean nuevoPulsado = false;
    //TODO: modificar los reportes para mostrar o no el lote

    public ControladorEtiquetas(VentanaEtiquetas vista) {
        this.vista = vista;
        init();
        initListeners();
    }

    //muestra la ventana con los datos de las etiquetas relacionadas con un medicamento o crea una nueva etiqueta relacionada con ese medicamento
    public ControladorEtiquetas(VentanaEtiquetas vista, String nombreMedicamento, boolean nuevaEtiqueta) {
        this.vista = vista;
        if (!nuevaEtiqueta) {
            initConMedicamento(nombreMedicamento);
        } else {
            init();
        }
        initListeners();
        if (nuevaEtiqueta) {
            pulsarBotonNuevaEtiquetas();
            vista.jButtonAceptarEtiquetas.setVisible(true);
            vista.jButtonCancelarEtiquetas.setVisible(true);
            vista.jComboBoxMedicamento.setSelectedItem(nombreMedicamento);
            vista.jComboBoxMedicamento.transferFocusBackward();
        }
    }

    public void cargarTodasEtiquetas() {
        init();
        initListeners();
    }

    public void cargarEtiquetaDeMedicamento(String nombreMedicamento, boolean nuevaEtiqueta) {
        if (!nuevaEtiqueta) {
            initConMedicamento(nombreMedicamento);
        } else {
            init();
        }
        initListeners();
        if (nuevaEtiqueta) {
            pulsarBotonNuevaEtiquetas();
            vista.jButtonAceptarEtiquetas.setVisible(true);
            vista.jButtonCancelarEtiquetas.setVisible(true);
            vista.jComboBoxMedicamento.setSelectedItem(nombreMedicamento);
            vista.jComboBoxMedicamento.transferFocusBackward();
        }
    }

    //Inicialización de los objetos necesarios para acceder y mostrar los datos
    private void init() {
        modelo = new EtiquetasDAO();
        listaEtiquetas = modelo.selectAllEtiquetas();
        rellenarJListEtiquetas();
        rellenarComboBoxMedicamentos();
        vista.jListEtiquetas.setSelectedIndex(0);
        actualizarPosicion();
        mostrarDatosEtiquetas();
        soloEscribirNumerosJTextField(vista.jTextFieldEtiquetasLibroRecetario);
    }

    //Inicializacion de los objetos necesarios para acceder y mostrar los datos de un medicamento en concreto
    private void initConMedicamento(String nombreMedicamento) {
        modelo = new EtiquetasDAO();
        listaEtiquetas = modelo.selectEtiquetasPorFichaDeMedicamento(nombreMedicamento);
        rellenarJListEtiquetas();
        rellenarComboBoxMedicamentos();
        vista.jListEtiquetas.setSelectedIndex(0);
        if (listaEtiquetas.size() < 1) {
            JOptionPane.showMessageDialog(vista, "No hay etiquetas relacionadas con el medicamento seleccionado", "Información", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(vista, "Se mostrarán todas las etiquetas que existen", "Información", JOptionPane.INFORMATION_MESSAGE);
            recargarDatosQuitarBuscar();
        } else {
            JOptionPane.showMessageDialog(vista, "Se han encontrado " + listaEtiquetas.size() + " etiquetas", "Información", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(vista, "Púlse el botón de quitar búsqueda si quiere ver todas las etiquetas", "Información", JOptionPane.INFORMATION_MESSAGE);

        }
        actualizarPosicion();
        mostrarDatosEtiquetas();
        soloEscribirNumerosJTextField(vista.jTextFieldEtiquetasLibroRecetario);
    }

    //Rellena un comboBox con los medicamentos
    private void rellenarComboBoxMedicamentos() {
        FichasDeMedicamentosDAO fichasDao = new FichasDeMedicamentosDAO();
        listaFichaMedicamentos = fichasDao.selectAllMedicamentos();
        String[] nombresMedicamentos = new String[listaFichaMedicamentos.size()];
        nombresMedicamentos[0] = "";
        for (int i = 1; i < listaFichaMedicamentos.size(); i++) {
            nombresMedicamentos[i] = listaFichaMedicamentos.get(i).getMedicamento();
        }
        vista.jComboBoxMedicamento.setModel(new DefaultComboBoxModel(nombresMedicamentos));
    }

    //Añade los listener de los eventos a los componentes
    private void initListeners() {
        vista.jListEtiquetas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cambiarPosicionJListEtiquetas();
            }
        });
        vista.jButtonSiguienteEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseSiguiente();
            }
        });
        vista.jButtonAtrasEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseAnterior();
            }
        });
        vista.jButtonPrimeroEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoversePrimero();
            }
        });
        vista.jButtonUltimoEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverseUltimo();
            }
        });
        vista.jButtonModificarEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarEtiquetas();
            }
        });
        vista.jButtonAceptarEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarPulsado();
            }
        });
        vista.jButtonCancelarEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarPulsado();
            }
        });
        vista.jButtonNuevoEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonNuevaEtiquetas();
            }
        });
        vista.jButtonBuscarEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscar();
            }
        });
        vista.jButtonQuitarBuscarEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargarDatosQuitarBuscar();
            }
        });
        vista.jButtonEliminarEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarEtiquetas();
            }
        });
        vista.jMenuItemArchivoSalir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vista.dispose();
            }
        });
        vista.jMenuItemArchivoNuevo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonNuevaEtiquetas();
            }
        });
        vista.jMenuItemEditarEliminar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonEliminarEtiquetas();
            }
        });
        vista.jMenuItemEditarModificar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonModificarEtiquetas();
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
        vista.jButtonImprimirEtiqueta.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonDialogImprimir();
            }
        });
        vista.jButtonDialogImprimirPreview.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarBotonDialogImprimirPreview();
            }
        });
        vista.jButtonDialogImprimirVolver.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vista.jDialogImprimirEtiquetas.setVisible(false);
            }
        });
        vista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                vista.dispose();
            }
        });
        vista.jTextFieldBuscarEtiquetas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    botonBuscar();
                }
            }
        });
        vista.jCheckBoxNombrePaciente.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarCheckBoxDatosPaciente();
            }
        });
        vista.jButtonDialogImprimirVolverPaciente.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vista.jDialogImprimirEtiquetasPaciente.setVisible(false);
            }
        });
        vista.jButtonDialogImprimirPreviewPaciente.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarVistaPreviaPaciente();
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
        if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEtiquetasConservacion)) {
            vista.jTextAreaEtiquetasConservacion.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEtiquetasMedicamento)) {
            vista.jTextAreaEtiquetasMedicamento.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEtiquetasReferenciaBibliografica)) {
            vista.jTextAreaEtiquetasReferenciaBibliografica.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEtiquetasReferenciaCaducidad)) {
            vista.jTextAreaEtiquetasReferenciaCaducidad.copy();
        } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEtiquetasUtilizacion)) {
            vista.jTextAreaEtiquetasUtilizacion.copy();
        }

    }

    //Acción de pegar
    public void pegar() {
        //solo se puede pegar si se estan editando los datos
        if (vista.jButtonAceptarEtiquetas.isVisible()) {
            //Para pegar hacer un switch con todos los componentes en donde se puede pegar

            if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEtiquetasConservacion)) {
                vista.jTextAreaEtiquetasConservacion.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEtiquetasMedicamento)) {
                vista.jTextAreaEtiquetasMedicamento.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEtiquetasReferenciaBibliografica)) {
                vista.jTextAreaEtiquetasReferenciaBibliografica.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEtiquetasReferenciaCaducidad)) {
                vista.jTextAreaEtiquetasReferenciaCaducidad.paste();
            } else if (vista.jPopupMenuCopiarPegar.getInvoker().equals(vista.jTextAreaEtiquetasUtilizacion)) {
                vista.jTextAreaEtiquetasUtilizacion.paste();
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Para poder pegar hay que pulsar antes el botón de modificar los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    //Llena el JListEtiquetas con la descripcion de la etiquetas existentes en la base de datos
    private void rellenarJListEtiquetas() {
        maquinariaJList = new String[listaEtiquetas.size()];
        for (int i = 0; i < maquinariaJList.length; i++) {
            maquinariaJList[i] = listaEtiquetas.get(i).getTipoEtiqueta() + " - " + listaEtiquetas.get(i).getDescripcion();
        }
        vista.jListEtiquetas.setModel(new javax.swing.AbstractListModel() {
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

    //Posición de la etiqueta en el que estamos situados
    private void actualizarPosicion() {
        String pos = "Etiquetas " + (vista.jListEtiquetas.getSelectedIndex() + 1) + " de " + maquinariaJList.length;
        vista.jLabelPosicionEtiquetas.setText(pos);
    }

    //Mostrar los datos del elemento seleccionado del jListEtiquetas
    private void mostrarDatosEtiquetas() {
        if (vista.jListEtiquetas.getSelectedIndex() > -1) {
            vista.jLabelIdFichasDeMedicamentos.setText("" + listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getIdEtiqueta());
            vista.jTextAreaEtiquetasConservacion.setText(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConservacion());
            vista.jTextAreaEtiquetasMedicamento.setText(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getDescripcion());
            vista.jTextAreaEtiquetasReferenciaBibliografica.setText(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getReferenciaBiblio());
            vista.jTextAreaEtiquetasReferenciaCaducidad.setText(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getReferenciaCaducidad());
            vista.jTextAreaEtiquetasUtilizacion.setText(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getUtilizacion());
            vista.jTextFieldEtiquetasCaducidad.setText(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getCaducidad());
            vista.jTextFieldEtiquetasConcentracion.setText(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion());
            if (listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getNumLibroRecetario() != null) {
                vista.jTextFieldEtiquetasLibroRecetario.setText("" + listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getNumLibroRecetario());
            } else {
                vista.jTextFieldEtiquetasLibroRecetario.setText("");
            }

            vista.jTextFieldEtiquetasStockMinimo.setText(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getStockMinimo());
            if (!nuevoPulsado) {
                if (!modelo.selectNombreFichaDeMedicamento(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())).equals("?")) {
                    vista.jComboBoxMedicamento.setSelectedItem(modelo.selectNombreFichaDeMedicamento(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())));
                } else {
                    vista.jComboBoxMedicamento.setSelectedIndex(0);
                }
                if (listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getQuienLoElabora() != null) {
                    //si no es A E o F entonces se cuenta como si no tubiera nada
                    if (!listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getQuienLoElabora().equals("A") || !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getQuienLoElabora().equals("E") || !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getQuienLoElabora().equals("F")) {
                        vista.jComboBoxEtiquetasElaboradoPor.setSelectedItem(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getQuienLoElabora());
                    } else {
                        vista.jComboBoxEtiquetasElaboradoPor.setSelectedIndex(0);
                    }
                } else {
                    vista.jComboBoxEtiquetasElaboradoPor.setSelectedIndex(0);
                }
                if (listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getTipoEtiqueta() != null) {
                    vista.jComboBoxEtiquetasTipoEtiqueta.setSelectedItem(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getTipoEtiqueta());
                } else {
                    vista.jComboBoxEtiquetasTipoEtiqueta.setSelectedIndex(0);
                }

            } else {
                vista.jComboBoxEtiquetasElaboradoPor.setSelectedIndex(0);
                vista.jComboBoxEtiquetasTipoEtiqueta.setSelectedIndex(0);
                vista.jComboBoxMedicamento.setSelectedIndex(0);
            }

        }
    }

    //Recoger los datos de los controloes en el elemento seleccionado del jListEtiquetas
    private void recogerDatosEtiquetas() {
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setConservacion(vista.jTextAreaEtiquetasConservacion.getText());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setDescripcion(vista.jTextAreaEtiquetasMedicamento.getText());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setReferenciaBiblio(vista.jTextAreaEtiquetasReferenciaBibliografica.getText());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setReferenciaCaducidad(vista.jTextAreaEtiquetasReferenciaCaducidad.getText());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setUtilizacion(vista.jTextAreaEtiquetasUtilizacion.getText());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setCaducidad(vista.jTextFieldEtiquetasCaducidad.getText());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setConcentracion(vista.jTextFieldEtiquetasConcentracion.getText());
        if (!vista.jTextFieldEtiquetasLibroRecetario.getText().equals("")) {
            listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setNumLibroRecetario(Integer.valueOf(vista.jTextFieldEtiquetasLibroRecetario.getText()));
        } else {
            listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setNumLibroRecetario(null);
        }
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setStockMinimo(vista.jTextFieldEtiquetasStockMinimo.getText());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setQuienLoElabora(vista.jComboBoxEtiquetasElaboradoPor.getSelectedItem().toString());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setTipoEtiqueta(vista.jComboBoxEtiquetasTipoEtiqueta.getSelectedItem().toString());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setFichasDeMedicamentos(listaFichaMedicamentos.get(vista.jComboBoxMedicamento.getSelectedIndex()));
    }

    // Evento al cambiar de posición en el jListEtiquetas
    private void cambiarPosicionJListEtiquetas() {
        actualizarPosicion();
        controlarBotonesMoverse();
        mostrarDatosEtiquetas();
    }

    // Activar/desactivar botones de siguiente,anterior,primero,último 
    private void controlarBotonesMoverse() {
        if (vista.jListEtiquetas.getSelectedIndex() == 0) {
            vista.jButtonAtrasEtiquetas.setEnabled(false);
            vista.jButtonPrimeroEtiquetas.setEnabled(false);
        } else {
            vista.jButtonAtrasEtiquetas.setEnabled(true);
            vista.jButtonPrimeroEtiquetas.setEnabled(true);
        }
        if (vista.jListEtiquetas.getSelectedIndex() == listaEtiquetas.size() - 1) {
            vista.jButtonSiguienteEtiquetas.setEnabled(false);
            vista.jButtonUltimoEtiquetas.setEnabled(false);
        } else {
            vista.jButtonSiguienteEtiquetas.setEnabled(true);
            vista.jButtonUltimoEtiquetas.setEnabled(true);
        }
    }

    //Moverse a la siguiente etiqueta de la lista
    protected void botonMoverseSiguiente() {
        vista.jListEtiquetas.setSelectedIndex(vista.jListEtiquetas.getSelectedIndex() + 1);
        if (vista.jScrollPaneListaEtiquetas.getVerticalScrollBar().getValue() - (vista.jListEtiquetas.getSelectedIndex() * 19) < -361) {
            vista.jScrollPaneListaEtiquetas.getVerticalScrollBar().setValue(((vista.jListEtiquetas.getSelectedIndex() * 19) - 361));
        }
    }

    //Moverse a la anterior etiqueta de la lista
    protected void botonMoverseAnterior() {
        vista.jListEtiquetas.setSelectedIndex(vista.jListEtiquetas.getSelectedIndex() - 1);
        if (vista.jScrollPaneListaEtiquetas.getVerticalScrollBar().getValue() - (vista.jListEtiquetas.getSelectedIndex() * 19) > 0) {
            vista.jScrollPaneListaEtiquetas.getVerticalScrollBar().setValue(((vista.jListEtiquetas.getSelectedIndex() * 19)));
        }
    }

    //Moverse a la primer etiqueta de la lista
    protected void botonMoversePrimero() {
        vista.jListEtiquetas.setSelectedIndex(0);
        vista.jScrollPaneListaEtiquetas.getVerticalScrollBar().setValue(0);

    }

    //Moverse a la última etiqueta de la lista
    protected void botonMoverseUltimo() {
        vista.jListEtiquetas.setSelectedIndex(listaEtiquetas.size() - 1);
        vista.jScrollPaneListaEtiquetas.getVerticalScrollBar().setValue(((vista.jListEtiquetas.getSelectedIndex() * 22) - 308));

    }

    //Modificar una etiqueta
    protected void pulsarBotonModificarEtiquetas() {
        comenzarEdicion();
        nuevoPulsado = false;
    }

    //Nueva etiqueta
    protected void pulsarBotonNuevaEtiquetas() {

        nuevoPulsado = true;
        Etiquetas maq = new Etiquetas();
        listaEtiquetas.add(maq);
        rellenarJListEtiquetas();
        vista.jListEtiquetas.setSelectedIndex(listaEtiquetas.size() - 1);
        comenzarEdicion();
    }

    //Eliminar etiqueta
    protected void pulsarBotonEliminarEtiquetas() {
        if (JOptionPane.showConfirmDialog(vista, "¿Desea eliminar la etiqueta actual?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
            if (listaEtiquetas.size() < 2) {
                JOptionPane.showMessageDialog(vista, "No se pueden eliminar todas las etiqueta visibles. \nPruebe a quitar algún filtro de busqueda", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                modelo.eliminarEtiquetas(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()));
                listaEtiquetas.remove(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()));
                rellenarJListEtiquetas();
                vista.jListEtiquetas.setSelectedIndex(0);
            }
        }
    }

    // Habilitar controles para permitir la edición de los datos de una etiqueta
    private void comenzarEdicion() {

        vista.jTextAreaEtiquetasConservacion.setEditable(true);
        vista.jTextAreaEtiquetasMedicamento.setEditable(true);
        vista.jTextAreaEtiquetasReferenciaBibliografica.setEditable(true);
        vista.jTextAreaEtiquetasReferenciaCaducidad.setEditable(true);
        vista.jTextAreaEtiquetasUtilizacion.setEditable(true);
        vista.jTextFieldEtiquetasCaducidad.setEditable(true);
        vista.jTextFieldEtiquetasConcentracion.setEditable(true);
        vista.jTextFieldEtiquetasLibroRecetario.setEditable(true);
        vista.jTextFieldEtiquetasStockMinimo.setEditable(true);
        vista.jComboBoxEtiquetasElaboradoPor.setEnabled(true);
        vista.jComboBoxEtiquetasTipoEtiqueta.setEnabled(true);
        vista.jComboBoxMedicamento.setEnabled(true);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListEtiquetas.setEnabled(false);
        vista.jButtonAtrasEtiquetas.setEnabled(false);
        vista.jButtonEliminarEtiquetas.setEnabled(false);
        vista.jButtonNuevoEtiquetas.setEnabled(false);
        vista.jButtonPrimeroEtiquetas.setEnabled(false);
        vista.jButtonSiguienteEtiquetas.setEnabled(false);
        vista.jButtonUltimoEtiquetas.setEnabled(false);
        vista.jButtonBuscarEtiquetas.setEnabled(false);
        vista.jButtonQuitarBuscarEtiquetas.setEnabled(false);
        vista.jTextFieldBuscarEtiquetas.setEnabled(false);
        vista.jButtonImprimirEtiqueta.setEnabled(false);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosEtiquetas.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true), "Edición de datos"));
        vista.jPanelListaEtiquetas.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Lista de etiquetas"));

        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jPanelAceptarCancelar.setVisible(true);

        //Da el foco al primer componente editable
        vista.jComboBoxMedicamento.transferFocusBackward();
    }

    // lo contrario de comenzarEdicion()
    private void acabarEdicion() {
        vista.jTextAreaEtiquetasConservacion.setEditable(false);
        vista.jTextAreaEtiquetasMedicamento.setEditable(false);
        vista.jTextAreaEtiquetasReferenciaBibliografica.setEditable(false);
        vista.jTextAreaEtiquetasReferenciaCaducidad.setEditable(false);
        vista.jTextAreaEtiquetasUtilizacion.setEditable(false);
        vista.jTextFieldEtiquetasCaducidad.setEditable(false);
        vista.jTextFieldEtiquetasConcentracion.setEditable(false);
        vista.jTextFieldEtiquetasLibroRecetario.setEditable(false);
        vista.jTextFieldEtiquetasStockMinimo.setEditable(false);
        vista.jComboBoxEtiquetasElaboradoPor.setEnabled(false);
        vista.jComboBoxEtiquetasTipoEtiqueta.setEnabled(false);
        vista.jComboBoxMedicamento.setEnabled(false);

        //deshabilitar el JList y los botones de la barra de herramientas para evitar errores
        vista.jListEtiquetas.setEnabled(true);
        vista.jButtonAtrasEtiquetas.setEnabled(true);
        vista.jButtonEliminarEtiquetas.setEnabled(true);
        vista.jButtonNuevoEtiquetas.setEnabled(true);
        vista.jButtonPrimeroEtiquetas.setEnabled(true);
        vista.jButtonSiguienteEtiquetas.setEnabled(true);
        vista.jButtonUltimoEtiquetas.setEnabled(true);
        vista.jButtonBuscarEtiquetas.setEnabled(true);
        vista.jButtonQuitarBuscarEtiquetas.setEnabled(true);
        vista.jTextFieldBuscarEtiquetas.setEnabled(true);
        vista.jButtonImprimirEtiqueta.setEnabled(true);

        //cambiar el color de los paneles para resaltar
        vista.jPanelEdicionDatosEtiquetas.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Edición de datos"));
        vista.jPanelListaEtiquetas.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true), "Lista de etiquetas"));

        //mostrar botones de aceptar-cancelar para terminar la edición
        vista.jPanelAceptarCancelar.setVisible(false);

    }

    //Pulsar boton de aceptar
    public void botonAceptarPulsado() {
        if (comprobarCamposObligatorios()) {
            try {
                if (nuevoPulsado) {
                    recogerDatosEtiquetas();
                    modelo.nuevoEtiquetas(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()));
                    acabarEdicion();
                    rellenarJListEtiquetas();
                    vista.jListEtiquetas.setSelectedIndex(listaEtiquetas.size() - 1);

                } else {
                    recogerDatosEtiquetas();
                    modelo.modificarEtiquetas(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()));
                    int posicion = vista.jListEtiquetas.getSelectedIndex();
                    acabarEdicion();
                    rellenarJListEtiquetas();
                    vista.jListEtiquetas.setSelectedIndex(posicion);

                }
            } catch (org.hibernate.exception.DataException e) {
                JOptionPane.showMessageDialog(vista, "El nombre de la etiqueta es demasiado larga, por favor hágalo más corto.", "Error", JOptionPane.WARNING_MESSAGE);

            } catch (ConstraintViolationException e) {
                JOptionPane.showMessageDialog(vista, "La etiqueta introducida ya existe, introduza otra", "Error", JOptionPane.WARNING_MESSAGE);
            }

        }

    }

    //Comprobar que los campos obligatorios no están vacios
    public boolean comprobarCamposObligatorios() {
        if (vista.jTextAreaEtiquetasMedicamento.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Los siguiente campos son obligatorios \n\t-Descripción", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Pulsar boton de cancelar
    public void botonCancelarPulsado() {
        if (nuevoPulsado) {
            listaEtiquetas.remove(listaEtiquetas.size() - 1);
            acabarEdicion();
            rellenarJListEtiquetas();
            vista.jListEtiquetas.setSelectedIndex(0);

        } else {
            acabarEdicion();
            controlarBotonesMoverse();
            mostrarDatosEtiquetas();
        }
    }

    //Realizar búsquedas
    public void botonBuscar() {
        if (vista.jComboBoxBuscarEtiquetas.getSelectedIndex() == 0) {
            listaEtiquetas = modelo.selectEtiquetasPorNombre(vista.jTextFieldBuscarEtiquetas.getText());
        } else if (vista.jComboBoxBuscarEtiquetas.getSelectedIndex() == 1) {
            listaEtiquetas = modelo.selectEtiquetasPorTipoEtiqueta(vista.jTextFieldBuscarEtiquetas.getText());
        }
        rellenarJListEtiquetas();
        vista.jListEtiquetas.setSelectedIndex(0);
        if (listaEtiquetas.size() < 1) {
            JOptionPane.showMessageDialog(vista, "No hay etiquetas que cumplan las condiciones de la búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(vista, "Se han reestablecido las condiciones de búsqueda", "Información", JOptionPane.INFORMATION_MESSAGE);
            recargarDatosQuitarBuscar();
        } else {
            JOptionPane.showMessageDialog(vista, "Se han encontrado " + listaEtiquetas.size() + " etiquetas", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    //Quitar el filtro de las búsquedas también usado al entrar en esta pestaña
    public void recargarDatosQuitarBuscar() {
        vista.jTextFieldBuscarEtiquetas.setText("");
        recargarDatos();
    }

    // Recarga los datos de la base de datos
    public void recargarDatos() {
        listaEtiquetas = modelo.selectAllEtiquetas();
        rellenarJListEtiquetas();
        vista.jListEtiquetas.setSelectedIndex(0);
    }

    //Devuelve la etiqueta escogida
    public Etiquetas getEtiquetasElegida() {
        return listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex());
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

    //Muestra un diálogo que permite elegir el formato de etiqueta que queremos mostrar
    public void pulsarBotonDialogImprimir() {
        java.util.Date hoy = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        if (modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()))) {
            vista.jDialogImprimirEtiquetasPaciente.setSize(580, 470);
            vista.jDialogImprimirEtiquetasPaciente.setLocationRelativeTo(null);
            vista.jTextFieldNombrePaciente.setText("");
            vista.jRadioButton3.setSelected(true);
            vista.jFormattedTextFieldFechaElaboracionPaciente.setText(formatter.format(hoy));
            if (listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getTipoEtiqueta().equalsIgnoreCase("A")) {
                vista.jRadioButtonDialogImprimirTipoAPaciente.setSelected(true);
            } else if (listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getTipoEtiqueta().equalsIgnoreCase("B")) {
                vista.jRadioButtonDialogImprimirTipoBPaciente.setSelected(true);
            } else {
                vista.jRadioButtonDialogImprimirTipoCPaciente.setSelected(true);
            }
            vista.jDialogImprimirEtiquetasPaciente.setVisible(true);

        } else {
            vista.jDialogImprimirEtiquetas.setSize(580, 350);
            vista.jDialogImprimirEtiquetas.setLocationRelativeTo(null);
            if (listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getTipoEtiqueta().equalsIgnoreCase("A")) {
                vista.jRadioButtonDialogImprimirTipoA.setSelected(true);
            } else if (listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getTipoEtiqueta().equalsIgnoreCase("B")) {
                vista.jRadioButtonDialogImprimirTipoB.setSelected(true);
            } else {
                vista.jRadioButtonDialogImprimirTipoC.setSelected(true);
            }
            vista.jRadioButton2.setSelected(true);
            vista.jFormattedTextFieldFechaElaboracion.setText(formatter.format(hoy));
            vista.jDialogImprimirEtiquetas.setVisible(true);
        }

    }

    //habilita y deshabilita la opción de introducir el nombre del cliente y un número específico de etiquetas
    public void pulsarCheckBoxDatosPaciente() {
        if (vista.jCheckBoxNombrePaciente.isSelected()) {
            vista.jTextFieldNombrePaciente.setEnabled(true);
            vista.jSpinnerNumeroEtiquetas.setEnabled(true);
        } else {
            vista.jTextFieldNombrePaciente.setEnabled(false);
            vista.jSpinnerNumeroEtiquetas.setEnabled(false);
        }
    }

    //Determina cual es el formato de la etiqueta elegido por el usuario cuando se pulsa el boton de vista previa 
    //y muestra un reporte de acuerdo con ese formato
    public void pulsarBotonDialogImprimirPreview() {
        String nombreReporte = "";
        //Puede que haya una forma más eficiente de comprobar todas estas cosas
        if (vista.jRadioButtonDialogImprimirTipoA.isSelected()) {
            if (!vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqADpe.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqAUtilizacion.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqADpeNoConcentracion.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqAUtilizacionNoConcentracion.jasper";
            } else if (vista.jRadioButtonDialogImprimirFormatoApli.isSelected()) {
                nombreReporte = "reportEtiquetaApli.jasper";
                mostrarReporte(nombreReporte, 14);
                return;
            }
        } else if (vista.jRadioButtonDialogImprimirTipoB.isSelected()) {
            if (!vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqBDpe.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqBUtilizacion.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqBDpeNoConcentracion.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqBUtilizacionNoConcentracion.jasper";
            } else if (vista.jRadioButtonDialogImprimirFormatoApli.isSelected()) {
                nombreReporte = "reportEtiquetaApli.jasper";
                mostrarReporte(nombreReporte, 14);
                return;
            }

        } else if (vista.jRadioButtonDialogImprimirTipoC.isSelected()) {
            if (!vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqCDpe.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqCUtilizacion.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqCDpeNoConcentracion.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && !vista.jRadioButtonDialogImprimirFormatoApli.isSelected()
                    && !modelo.esFormulaMagistral(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex())) && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("")) {
                nombreReporte = "reportEtiqCUtilizacionNoConcentracion.jasper";
            } else if (vista.jRadioButtonDialogImprimirFormatoApli.isSelected()) {
                nombreReporte = "reportEtiquetaApli.jasper";
                mostrarReporte(nombreReporte, 14);
                return;
            }

        }

        mostrarReporte(nombreReporte);
    }

    public void pulsarVistaPreviaPaciente() {
        String nombreReporte = "";
        if (vista.jRadioButtonDialogImprimirTipoAPaciente.isSelected()) {
            if (!vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqAFM.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqAFMUtilizacion.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqAFMNoConcentracion.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqAFMUtilizacionNoConcentracion.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqAFMPaciente.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqAFMUtilizacionPaciente.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqAFMNoConcentracionPaciente.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqAFMUtilizacionNoConcentracionPaciente.jasper";
            }
        } else if (vista.jRadioButtonDialogImprimirTipoBPaciente.isSelected()) {
            if (!vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqBFM.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "report4EtiqbFMUtilizacion.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "report4EtiqbFMUtilizacionNoConcentracion.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqBFMNoConcentracion.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqBFMPaciente.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "report4EtiqbFMUtilizacionPaciente.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "report4EtiqbFMUtilizacionNoConcentracionPaciente.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacion.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqBFmNoConcentracionPaciente.jasper";
            }
        } else if (vista.jRadioButtonDialogImprimirTipoCPaciente.isSelected()) {
            if (!vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqCFM.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqCFMUtilizacion.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqCFMNoConcentracion.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && !vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqCFMUtilizacionNoConcentracion.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqCFMPaciente.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && !listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqCFMUtilizacionPaciente.jasper";
            } else if (!vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqCFMNoConcentracionPaciente.jasper";
            } else if (vista.jCheckBoxDialogImprimirUtilizacionPaciente.isSelected()
                    && listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).getConcentracion().equals("") && vista.jCheckBoxNombrePaciente.isSelected()) {
                nombreReporte = "reportEtiqCFMUtilizacionNoCocentracionPaciente.jasper";
            }

        }
        if (vista.jRadioButtonDialogImprimirFormatoApliPaciente.isSelected()) {
            nombreReporte = "reportEtiquetaApli.jasper";
            mostrarReporteFormulaMagistral(nombreReporte, 14);
            return;
        }
        mostrarReporteFormulaMagistral(nombreReporte);
    }

    //Muestra el tipo de reporte elegido de la etiqueta seleccionada 
    public void mostrarReporte(String nombreReporte) {
        ArrayList<Etiquetas> etiqImprimir = new ArrayList<>();
        //Llenar la hoja de etiquetas
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setFechaDeElaboracion(vista.jFormattedTextFieldFechaElaboracion.getText());
        etiqImprimir.add(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()));

        EtiquetasDataSource datasource = new EtiquetasDataSource(etiqImprimir);
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(nombreReporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            //  jviewer.setAlwaysOnTop(true);
            vista.jDialogImprimirEtiquetas.setVisible(false);
            jviewer.setTitle("Imprimir");
            jviewer.setVisible(true);
            jviewer.setExtendedState(Frame.MAXIMIZED_BOTH);
            listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setFechaDeElaboracion("");

        } catch (JRException e1) {
            JOptionPane.showMessageDialog(vista, "Error al inicializar el reporte", "Fallo", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }

    //Muestra el tipo de reporte elegido de la etiqueta seleccionada 
    public void mostrarReporte(String nombreReporte, int repeticion) {
        ArrayList<Etiquetas> etiqImprimir = new ArrayList<>();
        //Llenar la hoja de etiquetas
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setFechaDeElaboracion(vista.jFormattedTextFieldFechaElaboracion.getText());
        for (int i = 0; i < repeticion; i++) {
            etiqImprimir.add(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()));
        }
        EtiquetasDataSource datasource = new EtiquetasDataSource(etiqImprimir);
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(nombreReporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            //  jviewer.setAlwaysOnTop(true);
            vista.jDialogImprimirEtiquetas.setVisible(false);
            jviewer.setTitle("Imprimir");
            jviewer.setVisible(true);
            jviewer.setExtendedState(Frame.MAXIMIZED_BOTH);
            listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setFechaDeElaboracion("");

        } catch (JRException e1) {
            JOptionPane.showMessageDialog(vista, "Error al inicializar el reporte", "Fallo", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }

    //Muestra el tipo de reporte de formulas magistrales elegido de la etiqueta seleccionada 
    public void mostrarReporteFormulaMagistral(String nombreReporte) {
        ArrayList<Etiquetas> etiqImprimir = new ArrayList<>();
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setNombreDelPaciente(vista.jTextFieldNombrePaciente.getText());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setFechaDeElaboracion(vista.jFormattedTextFieldFechaElaboracionPaciente.getText());
        //Llenar la hoja de etiquetas
        for (int i = 0; i < Integer.valueOf(vista.jSpinnerNumeroEtiquetas.getValue().toString()); i++) {
            etiqImprimir.add(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()));
        }

        EtiquetasDataSource datasource = new EtiquetasDataSource(etiqImprimir);
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(nombreReporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            //  jviewer.setAlwaysOnTop(true);
            vista.jDialogImprimirEtiquetasPaciente.setVisible(false);
            jviewer.setTitle("Imprimir");
            jviewer.setVisible(true);
            jviewer.setExtendedState(Frame.MAXIMIZED_BOTH);
            //no interesa guardar el nombre del paciente en la base de datos
            listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setNombreDelPaciente("");
            listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setFechaDeElaboracion("");

        } catch (JRException e1) {
            JOptionPane.showMessageDialog(vista, "Error al inicializar el reporte", "Fallo", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }

    //Muestra el tipo de reporte de formulas magistrales elegido de la etiqueta seleccionada 
    public void mostrarReporteFormulaMagistral(String nombreReporte, int repeticion) {
        ArrayList<Etiquetas> etiqImprimir = new ArrayList<>();
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setNombreDelPaciente(vista.jTextFieldNombrePaciente.getText());
        listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setFechaDeElaboracion(vista.jFormattedTextFieldFechaElaboracionPaciente.getText());
        //Llenar la hoja de etiquetas
        for (int i = 0; i < repeticion; i++) {
            etiqImprimir.add(listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()));
        }

        EtiquetasDataSource datasource = new EtiquetasDataSource(etiqImprimir);
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(nombreReporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            //  jviewer.setAlwaysOnTop(true);
            vista.jDialogImprimirEtiquetasPaciente.setVisible(false);
            jviewer.setTitle("Imprimir");
            jviewer.setVisible(true);
            jviewer.setExtendedState(Frame.MAXIMIZED_BOTH);
            //no interesa guardar el nombre del paciente en la base de datos
            listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setNombreDelPaciente("");
            listaEtiquetas.get(vista.jListEtiquetas.getSelectedIndex()).setFechaDeElaboracion("");

        } catch (JRException e1) {
            JOptionPane.showMessageDialog(vista, "Error al inicializar el reporte", "Fallo", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }

}
