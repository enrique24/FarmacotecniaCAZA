
package es.sacyl.caza.farmacia;

import es.sacyl.caza.farmacia.modelo.DAO;
import es.sacyl.caza.farmacia.vista.VentanaPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrique Martín Arenal
 */
public class HiloInicializacionHibernate extends Thread{
    
    private VentanaPrincipal vista;

    public HiloInicializacionHibernate(VentanaPrincipal vista) {
        this.vista = vista;
    }
    
    

    @Override
    public void run() {
        DAO dao= new DAO();
        try {
            dao.primeraSesion();
            vista.sesionCargada();
        } catch (Exception ex) {
            Logger.getLogger(HiloInicializacionHibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

}
