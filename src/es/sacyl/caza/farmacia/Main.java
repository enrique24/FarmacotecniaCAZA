package es.sacyl.caza.farmacia;

import es.sacyl.caza.farmacia.vista.VentanaPrincipal;

/**
 *
 * @author Enrique Martín Arenal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        //TODO: poner pantalla de carga para la inicialización del hibernate
        VentanaPrincipal vista= new VentanaPrincipal();
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        HiloInicializacionHibernate ini= new HiloInicializacionHibernate(vista);
        ini.start();
        
        
       
    }
    
}
