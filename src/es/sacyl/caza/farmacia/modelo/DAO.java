
package es.sacyl.caza.farmacia.modelo;

import org.hibernate.Session;

/**
 *
 * @author Enrique Martín Arenal
 */
public class DAO {
    
    //Abrir sesion de hibernate
    public Session abrirSesion(){
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        return sesion;
    }
    //Cerrar una sesion de hibernate
    public void cerrarSesion(Session sesion){
        sesion.close();
    }
    
    //Primera sesion
    public void primeraSesion() throws Exception{
        Session sesion = abrirSesion();
        cerrarSesion(sesion);
    }


}
