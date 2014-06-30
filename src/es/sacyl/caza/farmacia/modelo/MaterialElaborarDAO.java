
package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.MaterialParaElaborar;
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
public class MaterialElaborarDAO extends DAO{
      //Añadir un nuevo materialElaborar a la tabla materialElaborar
    public void nuevoMaterialElaborar(MaterialParaElaborar materialElaborar) throws org.hibernate.exception.DataException{
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try{
            sesion.save(materialElaborar);
            tx.commit();
        }catch(ConstraintViolationException e){
            throw e;
        }finally{
           cerrarSesion(sesion); 
        }
      
   
       
        
    }

    //Modificar un materialElaborar ya existente de la tabla materialElaborar
    public void modificarMaterialElaborar(MaterialParaElaborar materialElaborar) throws org.hibernate.exception.DataException{
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
           try{
            sesion.update(materialElaborar);
            tx.commit();
        }catch(ConstraintViolationException e){
            throw e;
        }finally{
           cerrarSesion(sesion); 
        }
    }
    
    //Eliminar un materialElaborar existente de la tabla materialElaborars
    public void eliminarMaterialElaborar(MaterialParaElaborar materialElaborar){
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        sesion.delete(materialElaborar);
        tx.commit();
        cerrarSesion(sesion);
    }
    
    //Obtener todos los material para elaborar de la tabla materialElaborars
    public ArrayList<MaterialParaElaborar> selectAllMaterialElaborar(){
        Session sesion = abrirSesion();
        ArrayList<MaterialParaElaborar> listaMaterialElaborar = new ArrayList<>();
        Query q = sesion.createQuery("from MaterialParaElaborar order by material");
        List<MaterialParaElaborar> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            MaterialParaElaborar materialElaborar =(MaterialParaElaborar) iter.next();
            listaMaterialElaborar.add(materialElaborar);
        }
        cerrarSesion(sesion);
        return listaMaterialElaborar;
    }

    //Obtener los materialElaborars que cumplan la condición
    public ArrayList<MaterialParaElaborar> selectMaterialElaborarPorNombre(String nombreMaterialElaborar){
           Session sesion = abrirSesion();
        ArrayList<MaterialParaElaborar> listaMaterialElaborar = new ArrayList<>();
        Query q = sesion.createQuery("from MaterialParaElaborar as m where m.material like ? order by material");
        nombreMaterialElaborar=nombreMaterialElaborar+"%";
        q.setString(0, nombreMaterialElaborar);
        List<MaterialParaElaborar> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            MaterialParaElaborar materialElaborar =(MaterialParaElaborar) iter.next();
            listaMaterialElaborar.add(materialElaborar);
        }
        cerrarSesion(sesion);
        return listaMaterialElaborar;
    }
    
    //Obtener materialElaborar que se tiene o no se tiene
    public ArrayList<MaterialParaElaborar> selectMaterialElaborarTenemos(boolean tenemos){
           Session sesion = abrirSesion();
        ArrayList<MaterialParaElaborar> listaMaterialElaborar = new ArrayList<>();
        Query q = sesion.createQuery("from MaterialParaElaborar as m where m.tenemos = ? order by material");
        q.setBoolean(0, tenemos);
        List<MaterialParaElaborar> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            MaterialParaElaborar materialElaborar =(MaterialParaElaborar) iter.next();
            listaMaterialElaborar.add(materialElaborar);
        }
        cerrarSesion(sesion);
        return listaMaterialElaborar;
    }

}
