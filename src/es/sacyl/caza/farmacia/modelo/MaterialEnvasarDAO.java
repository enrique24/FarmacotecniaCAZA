
package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.MaterialDeEnvasado;
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
public class MaterialEnvasarDAO extends DAO{

    //Añadir un nuevo material para envasar a la tabla material envasar
    public void nuevoMaterialDeEnvasado(MaterialDeEnvasado materialEnv) throws org.hibernate.exception.DataException{
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try{
            sesion.save(materialEnv);
            tx.commit();
        }catch(ConstraintViolationException e){
            throw e;
        }finally{
           cerrarSesion(sesion); 
        }
      
   
       
        
    }

    //Modificar un material de envasado ya existente de la tabla material envasar
    public void modificarMaterialDeEnvasado(MaterialDeEnvasado materialEnv) throws org.hibernate.exception.DataException{
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
           try{
            sesion.update(materialEnv);
            tx.commit();
        }catch(ConstraintViolationException e){
            throw e;
        }finally{
           cerrarSesion(sesion); 
        }
    }
    
    //Eliminar un material de envasado existente de la tabla material envasar
    public void eliminarMaterialDeEnvasado(MaterialDeEnvasado materialEnv){
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        sesion.delete(materialEnv);
        tx.commit();
        cerrarSesion(sesion);
    }
    
    //Obtener todos los materiales para envasado de la tabla material envasar
    public ArrayList<MaterialDeEnvasado> selectAllMaterialDeEnvasado(){
        Session sesion = abrirSesion();
        ArrayList<MaterialDeEnvasado> listaMaterialDeEnvasado = new ArrayList<>();
        Query q = sesion.createQuery("from MaterialDeEnvasado order by paraEnvasar");
        List<MaterialDeEnvasado> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            MaterialDeEnvasado materialEnv =(MaterialDeEnvasado) iter.next();
            listaMaterialDeEnvasado.add(materialEnv);
        }
        cerrarSesion(sesion);
        return listaMaterialDeEnvasado;
    }

    //Obtener los materialEnvs que cumplan la condición
    public ArrayList<MaterialDeEnvasado> selectMaterialDeEnvasadoPorNombre(String nombreMaterialDeEnvasado){
           Session sesion = abrirSesion();
        ArrayList<MaterialDeEnvasado> listaMaterialDeEnvasado = new ArrayList<>();
        Query q = sesion.createQuery("from MaterialDeEnvasado as m where m.paraEnvasar like ? order by paraEnvasar");
        nombreMaterialDeEnvasado=nombreMaterialDeEnvasado+"%";
        q.setString(0, nombreMaterialDeEnvasado);
        List<MaterialDeEnvasado> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            MaterialDeEnvasado materialEnv =(MaterialDeEnvasado) iter.next();
            listaMaterialDeEnvasado.add(materialEnv);
        }
        cerrarSesion(sesion);
        return listaMaterialDeEnvasado;
    }
    
    //Obtener materialEnv que se tiene o no se tiene
    public ArrayList<MaterialDeEnvasado> selectMaterialDeEnvasadoTenemos(boolean tenemos){
           Session sesion = abrirSesion();
        ArrayList<MaterialDeEnvasado> listaMaterialDeEnvasado = new ArrayList<>();
        Query q = sesion.createQuery("from MaterialDeEnvasado as m where m.tenemos = ? order by paraEnvasar");
        q.setBoolean(0, tenemos);
        List<MaterialDeEnvasado> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            MaterialDeEnvasado materialEnv =(MaterialDeEnvasado) iter.next();
            listaMaterialDeEnvasado.add(materialEnv);
        }
        cerrarSesion(sesion);
        return listaMaterialDeEnvasado;
    }
}
