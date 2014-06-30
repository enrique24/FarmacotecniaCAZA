
package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.Maquinaria;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Enrique Martín Arenal
 */
public class MaquinariaDAO extends DAO{

     //Añadir una nueva maquinaria a la tabla maquinaria
    public void nuevoMaquinaria(Maquinaria maquinaria) throws org.hibernate.exception.DataException{
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try{
            sesion.save(maquinaria);
            tx.commit();
        }catch(ConstraintViolationException e){
            throw e;
        }finally{
           cerrarSesion(sesion); 
        }
      
   
       
        
    }

    //Modificar una maquinaria ya existente de la tabla maquinaria
    public void modificarMaquinaria(Maquinaria maquinaria) throws org.hibernate.exception.DataException{
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
           try{
            sesion.update(maquinaria);
            tx.commit();
        }catch(ConstraintViolationException e){
            throw e;
        }finally{
           cerrarSesion(sesion); 
        }
    }
    
    //Eliminar un maquinaria existente de la tabla maquinarias
    public void eliminarMaquinaria(Maquinaria maquinaria){
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        sesion.delete(maquinaria);
        tx.commit();
        cerrarSesion(sesion);
    }
    
    //Obtener todos los maquinarias de la tabla maquinarias
    public ArrayList<Maquinaria> selectAllMaquinaria(){
        Session sesion = abrirSesion();
        ArrayList<Maquinaria> listaMaquinaria = new ArrayList<>();
        Query q = sesion.createQuery("from Maquinaria order by maquinaria");
        List<Maquinaria> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Maquinaria maquinaria =(Maquinaria) iter.next();
            listaMaquinaria.add(maquinaria);
        }
        cerrarSesion(sesion);
        return listaMaquinaria;
    }

    //Obtener los maquinarias que cumplan la condición
    public ArrayList<Maquinaria> selectMaquinariaPorNombre(String nombreMaquinaria){
           Session sesion = abrirSesion();
        ArrayList<Maquinaria> listaMaquinaria = new ArrayList<>();
        Query q = sesion.createQuery("from Maquinaria as m where m.maquinaria like ? order by maquinaria");
        nombreMaquinaria=nombreMaquinaria+"%";
        q.setString(0, nombreMaquinaria);
        List<Maquinaria> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Maquinaria maquinaria =(Maquinaria) iter.next();
            listaMaquinaria.add(maquinaria);
        }
        cerrarSesion(sesion);
        return listaMaquinaria;
    }
    
    //Obtener maquinaria que se tiene o no se tiene
    public ArrayList<Maquinaria> selectMaquinariaTenemos(boolean tenemos){
           Session sesion = abrirSesion();
        ArrayList<Maquinaria> listaMaquinaria = new ArrayList<>();
        Query q = sesion.createQuery("from Maquinaria as m where m.tenemos = ? order by maquinaria");
        q.setBoolean(0, tenemos);
        List<Maquinaria> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Maquinaria maquinaria =(Maquinaria) iter.next();
            listaMaquinaria.add(maquinaria);
        }
        cerrarSesion(sesion);
        return listaMaquinaria;
    }
}
