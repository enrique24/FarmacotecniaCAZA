package es.sacyl.caza.farmacia.controlador;

import es.sacyl.caza.farmacia.vista.VentanaEdicionPMS;
import java.awt.Color;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrique Martín Arenal
 *
 * Controla los eventos de la VentanaEdicionPMS que no estén relacionados con
 * una pestaña en concreto
 */
public class ControladorVentanaEdicionPSM {
    VentanaEdicionPMS vista;
    ControladorMaquinaria controlMaquinaria;
    ControladorMaterialElaborar controlMatElab;
    ControladorMaterialEnvasar controlMatEnv;
    ControladorProductos controlProductos;

    public ControladorVentanaEdicionPSM(VentanaEdicionPMS vista, ControladorMaquinaria controlMaquinaria, ControladorMaterialElaborar controlMatElab, ControladorMaterialEnvasar controlMatEnv, ControladorProductos controlProductos) {
        this.vista = vista;
        this.controlMaquinaria = controlMaquinaria;
        this.controlMatElab = controlMatElab;
        this.controlMatEnv = controlMatEnv;
        this.controlProductos = controlProductos;
        cargarOpcion();

        initEventos();
    }

    //inicializa los listener de los eventos necesarios
    public void initEventos() {
        vista.jTabbedPaneEdicionPMS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cambiarDePestania();
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
                pulsarMenuItemNuevo();
            }
        });
        vista.jMenuItemEditarEliminar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarMenuItemEliminar();
            }
        });
        vista.jMenuItemEditarModificar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarMenuItemModificar();
            }
        });
        vista.jMenuItemVerAnterior.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarMenuItemAnterior();
            }
        });
        vista.jMenuItemVerPrimero.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarMenuItemPrimero();
            }
        });
        vista.jMenuItemVerSiguiente.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarMenuItemSiguiente();
            }
        });
        vista.jMenuItemVerUltimo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarMenuItemUltimo();
            }
        });
        vista.jRadioButtonMenuItemClasico.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarMenuEstilo();
            }
        });
        vista.jRadioButtonMenuItemColores.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsarMenuEstilo();
            }
        });

    }

    public void cerrarVentana() {
        vista.dispose();
    }

    //recarga los datos de la pesteña seleccionada por el usuario
    public void cambiarDePestania() {
        if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 0) {
            controlProductos.recargarDatosQuitarBuscar();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 1) {
            controlMaquinaria.recargarDatosQuitarBuscar();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 3) {
            controlMatEnv.recargarDatosQuitarBuscar();
        } else {
            controlMatElab.recargarDatosQuitarBuscar();
        }
    }

    //Crea un nuevo elemento dependiendo de en que pestaña nos encontremos
    public void pulsarMenuItemNuevo() {
        if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 0) {
            controlProductos.pulsarBotonNuevoProducto();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 1) {
            controlMaquinaria.pulsarBotonNuevaMaquinaria();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 3) {
            controlMatEnv.pulsarBotonNuevoMaterialEnvasar();
        } else {
            controlMatElab.pulsarBotonNuevoMaterialElaborar();
        }
    }

    //Elimina el elemento seleccionado de la pestaña en la que nos encontremos
    public void pulsarMenuItemEliminar() {
        if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 0) {
            controlProductos.pulsarBotonEliminarProducto();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 1) {
            controlMaquinaria.pulsarBotonEliminarMaquinaria();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 3) {
            controlMatEnv.pulsarBotonEliminarMaterialEnvasar();
        } else {
            controlMatElab.pulsarBotonElminiarMaterialElaborar();
        }
    }

    //Modifica el elemento seleccionado de la pestaña en la que nos encontremos
    public void pulsarMenuItemModificar() {
        if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 0) {
            controlProductos.pulsarBotonModificarProducto();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 1) {
            controlMaquinaria.pulsarBotonModificarMaquinaria();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 3) {
            controlMatEnv.pulsarBotonModificarMaterialEnvasar();
        } else {
            controlMatElab.pulsarBotonModificarMaterialElaborar();
        }
    }

    //Se posiciona en el primer elemento de la pestaña en la que nos encontremos
    public void pulsarMenuItemPrimero() {
        if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 0) {
            controlProductos.botonMoversePrimero();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 1) {
            controlMaquinaria.botonMoversePrimero();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 3) {
            controlMatEnv.botonMoversePrimero();
        } else {
            controlMatElab.botonMoversePrimero();
        }
    }

    //Se posiciona en el anterior elemento de la pestaña en la que nos encontremos
    public void pulsarMenuItemAnterior() {
        if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 0) {
            controlProductos.botonMoverseAnterior();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 1) {
            controlMaquinaria.botonMoverseAnterior();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 3) {
            controlMatEnv.botonMoverseAnterior();
        } else {
            controlMatElab.botonMoverseAnterior();
        }
    }

    //Se posiciona en el siguiente elemento de la pestaña en la que nos encontremos
    public void pulsarMenuItemSiguiente() {
        if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 0) {
            controlProductos.botonMoverseSiguiente();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 1) {
            controlMaquinaria.botonMoverseSiguiente();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 3) {
            controlMatEnv.botonMoverseSiguiente();
        } else {
            controlMatElab.botonMoverseSiguiente();
        }
    }

    //Se posiciona en el último elemento de la pestaña en la que nos encontremos
    public void pulsarMenuItemUltimo() {
        if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 0) {
            controlProductos.botonMoverseUltimo();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 1) {
            controlMaquinaria.botonMoverseUltimo();
        } else if (vista.jTabbedPaneEdicionPMS.getSelectedIndex() == 3) {
            controlMatEnv.botonMoverseUltimo();
        } else {
            controlMatElab.botonMoverseUltimo();
        }
    }

    //Cambiar el estilo de la ventana ( los colores de fondo)
    public void seleccionDeColor(String opcion) {
        if (opcion.equals("colores vivos")) {
            vista.panelMaquinaria.jToolBarMaquinaria.setBackground(new java.awt.Color(153, 153, 255));
            vista.panelProductos.jToolBarProductos.setBackground(new java.awt.Color(153, 153, 255));
            vista.panelMaterialElaborar.jToolBarMaterialElaborar.setBackground(new java.awt.Color(153, 153, 255));
            vista.panelMaterialEnvasar.jToolBarMaterialEnvasar.setBackground(new java.awt.Color(153, 153, 255));
            vista.panelProductos.jPanelProductos.setBackground(new java.awt.Color(204, 204, 204));
            vista.panelMaquinaria.jPanelMaquinaria.setBackground(new java.awt.Color(181, 181, 234));
            vista.panelMaterialEnvasar.jPanelMaterialEnvasado.setBackground(new java.awt.Color(222, 245, 245));
            vista.panelMaterialElaborar.jPanelMaterialElaborar.setBackground(new java.awt.Color(224, 215, 234));
            fuentePosicion(new java.awt.Color(255, 255, 255));

        } else {
            vista.panelMaterialEnvasar.jToolBarMaterialEnvasar.setBackground(new java.awt.Color(237, 255, 255));
            vista.panelMaquinaria.jToolBarMaquinaria.setBackground(new java.awt.Color(237, 255, 255));
            vista.panelMaterialElaborar.jToolBarMaterialElaborar.setBackground(new java.awt.Color(237, 255, 255));
            vista.panelProductos.jToolBarProductos.setBackground(new java.awt.Color(237, 255, 255));
            vista.panelProductos.jPanelProductos.setBackground(new java.awt.Color(255, 255, 255));
            vista.panelMaquinaria.jPanelMaquinaria.setBackground(new java.awt.Color(255, 255, 255));
            vista.panelMaterialEnvasar.jPanelMaterialEnvasado.setBackground(new java.awt.Color(255, 255, 255));
            vista.panelMaterialElaborar.jPanelMaterialElaborar.setBackground(new java.awt.Color(255, 255, 255));
            fuentePosicion(Color.black);

        }
    }
    
    //Cambiar color de la fuente de la etiqueta de la posicion
    public void fuentePosicion(java.awt.Color color) {
        vista.panelMaquinaria.jLabelPosicionMaquinaria.setForeground(color);
        vista.panelMaterialElaborar.jLabelPosicionMaterialElaborar.setForeground(color);
        vista.panelMaterialEnvasar.jLabelPosicionMaterialEnvasar.setForeground(color);
        vista.panelProductos.jLabelPosicionProducto.setForeground(color);
    }

    //Cambiar la apariencia de la ventana según la opción seleccionada y guardarlo en un archivo
    //TODO: ya no se usa nada de guardar el estilo
    public void pulsarMenuEstilo() {
        GuardarOpcion guardar;
        if (vista.jRadioButtonMenuItemClasico.isSelected()) {
            seleccionDeColor("clasico");
            guardar = new GuardarOpcion("clasico");
        } else {
            seleccionDeColor("colores vivos");
            guardar = new GuardarOpcion("colores vivos");
        }
        guardar.start();
    }

    //TODO: Ya no se usa
    public void cargarOpcion() {
      /*   String opcion = "";
        try {
            FileInputStream filein = null;
           
            File fichero = new File("estilo.dat");
            filein = new FileInputStream(fichero);
            DataInputStream dataIS = new DataInputStream(filein);
            opcion = dataIS.readUTF(); 
            dataIS.close(); // cerrar stream
            filein.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ControladorVentanaEdicionPSM.class.getName()).log(Level.SEVERE, null, ex);
        }
        seleccionDeColor(opcion);
        
        if(opcion.equals("colores vivos"))
            vista.jRadioButtonMenuItemColores.setSelected(true);
        else
            vista.jRadioButtonMenuItemClasico.setSelected(true);
     */
    }
}

//TODO: guardar la opcione del color en un archivo
class GuardarOpcion extends Thread {

    String opcion;

    public GuardarOpcion(String opcion) {
        this.opcion = opcion;
    }

    @Override
    public void run() {
        try {
            File fichero = new File("estilo.dat");
            FileOutputStream fileout = new FileOutputStream(fichero);
            DataOutputStream dataOS = new DataOutputStream(fileout);
            dataOS.writeUTF(opcion);
            dataOS.close(); // cerrar stream
            fileout.close();
        } catch (IOException ex) {
            Logger.getLogger(GuardarOpcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}


