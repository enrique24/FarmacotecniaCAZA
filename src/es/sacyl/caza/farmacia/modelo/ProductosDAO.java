package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.Productos;
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
public class ProductosDAO extends DAO {

    //Añadir un nuevo producto a la tabla productos
    public void nuevoProducto(Productos producto) throws org.hibernate.exception.DataException{
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try{
            sesion.save(producto);
            tx.commit();
        }catch(ConstraintViolationException e){
            throw e;
        }finally{
           cerrarSesion(sesion); 
        }
      
   
       
        
    }

    //Modificar un producto ya existente de la tabla productos
    public void modificarProducto(Productos producto) throws org.hibernate.exception.DataException {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
           try{
            sesion.update(producto);
            tx.commit();
        }catch(ConstraintViolationException e){
            throw e;
        }finally{
           cerrarSesion(sesion); 
        }
    }
    
    //Eliminar un producto existente de la tabla productos
    public void eliminarProducto(Productos producto){
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        sesion.delete(producto);
        tx.commit();
        cerrarSesion(sesion);
    }
    
    //Obtener todos los productos de la tabla productos
    public ArrayList<Productos> selectAllProducto(){
        Session sesion = abrirSesion();
        ArrayList<Productos> listaProductos = new ArrayList<>();
        Query q = sesion.createQuery("from Productos order by componente");
        List<Productos> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Productos producto = (Productos) iter.next();
            listaProductos.add(producto);
        }
        cerrarSesion(sesion);
        return listaProductos;
    }

    //Obtener los productos que cumplan la condición
    public ArrayList<Productos> selectProductosPorComponente(String componente){
           Session sesion = abrirSesion();
        ArrayList<Productos> listaProductos = new ArrayList<>();
        Query q = sesion.createQuery("from Productos as p where p.componente like ? order by componente");
        componente=componente+"%";
        q.setString(0, componente);
        List<Productos> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Productos producto = (Productos) iter.next();
            listaProductos.add(producto);
        }
        cerrarSesion(sesion);
        return listaProductos;
    }
    
    //Obtener productos por referencia
    public ArrayList<Productos> selectProductosPorReferencia(String referencia){
           Session sesion = abrirSesion();
        ArrayList<Productos> listaProductos = new ArrayList<>();
        Query q = sesion.createQuery("from Productos as p where p.referencia like ? order by referencia");
        referencia=referencia+"%";
        q.setString(0, referencia);
        List<Productos> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Productos producto = (Productos) iter.next();
            listaProductos.add(producto);
        }
        cerrarSesion(sesion);
        return listaProductos;
    }
    
    //Obtener productos activos/no activos
    public ArrayList<Productos> selectProductosActivos(boolean activo){
           Session sesion = abrirSesion();
        ArrayList<Productos> listaProductos = new ArrayList<>();
        Query q = sesion.createQuery("from Productos as p where p.activo = ? order by componente");
        q.setBoolean(0, activo);
        List<Productos> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Productos producto = (Productos) iter.next();
            listaProductos.add(producto);
        }
        cerrarSesion(sesion);
        return listaProductos;
    }
}
