package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.FichasDeMedicamentos;
import es.sacyl.caza.farmacia.modelo.clases.MaquinariaUnion;
import es.sacyl.caza.farmacia.modelo.clases.MaterialDeEnvasadoUnion;
import es.sacyl.caza.farmacia.modelo.clases.MaterialParaElaborarUnion;
import es.sacyl.caza.farmacia.modelo.clases.ProductosUnion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;

/**
 *
 * @author Enrique Martín Arenal
 */
public class FichasDeMedicamentosDAO extends DAO {

    //Añadir una nueva ficha de medicamento
    public void nuevoMedicamento(FichasDeMedicamentos medicamento) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(medicamento);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }

    //Modfica los datos de un medicamento
    public void modificarMedicamento(FichasDeMedicamentos medicamento) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(medicamento);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
            
        }
    }

    //Elimina los datos de un medicamento
    public void eliminarMedicamento(FichasDeMedicamentos medicamento) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.delete(medicamento);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }

    //Obtiene todos los medicamentos de la tabla fichas de medicamentos
    public ArrayList<FichasDeMedicamentos> selectAllMedicamentos() {
        Session sesion = abrirSesion();
        ArrayList<FichasDeMedicamentos> listaMedicamentos = new ArrayList<>();
        Query q = sesion.createQuery("from FichasDeMedicamentos order by medicamento");
        List<FichasDeMedicamentos> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            FichasDeMedicamentos medicamento = (FichasDeMedicamentos) iter.next();
            listaMedicamentos.add(medicamento);
        }
        cerrarSesion(sesion);
        return listaMedicamentos;
    }

    //Obtener los medicamentos dependiendo del nombre del medicamento
    public ArrayList<FichasDeMedicamentos> selectMedicamentoPorNombre(String nombreMedicamento) {
        Session sesion = abrirSesion();
        ArrayList<FichasDeMedicamentos> listaMedicamentos = new ArrayList<>();
        Query q = sesion.createQuery("from FichasDeMedicamentos as m where m.medicamento like ? order by medicamento");
        nombreMedicamento = "%"+ nombreMedicamento + "%";
        q.setString(0, nombreMedicamento);
        List<FichasDeMedicamentos> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            FichasDeMedicamentos medicamento = (FichasDeMedicamentos) iter.next();
            listaMedicamentos.add(medicamento);
        }
        cerrarSesion(sesion);
        return listaMedicamentos;
    }
   
    //Obtiene el medicamento que corresponda con el id que se introduce de parámetro
    public FichasDeMedicamentos selectMedicamentoPorID(int idMedicamento){
        Session sesion = abrirSesion();
        Query q = sesion.createQuery("from FichasDeMedicamentos as m where m.idMedicamento= ? ");
        q.setInteger(0, idMedicamento);
        List<FichasDeMedicamentos> lista = q.list();
        Iterator iter = lista.iterator();
        if (iter.hasNext()) {
            // extraer el objeto
            FichasDeMedicamentos medicamento = (FichasDeMedicamentos) iter.next();
            cerrarSesion(sesion);
            return medicamento;
        }else{
            return null;
        } 
        
        
    }
    
    //Obtener los medicamentos dependiendo del nombre de un producto del medicamento
    public ArrayList<FichasDeMedicamentos> selectMedicamentoPorNombreProducto(String nombreMedicamento) {
        Session sesion = abrirSesion();
        ArrayList<FichasDeMedicamentos> listaMedicamentos = new ArrayList<>();
        Query q = sesion.createQuery("from FichasDeMedicamentos as m where m.idMedicamento in(select fichasDeMedicamentos.idMedicamento from ProductosUnion as p where p.productos.componente like ?) order by medicamento");
        nombreMedicamento = "%"+ nombreMedicamento + "%";
        q.setString(0, nombreMedicamento);
        List<FichasDeMedicamentos> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            FichasDeMedicamentos medicamento = (FichasDeMedicamentos) iter.next();
            listaMedicamentos.add(medicamento);
        }
        cerrarSesion(sesion);
        return listaMedicamentos;
    }
    
    //Obtener los medicamentos dependiendo del nombre de un producto del medicamento
    public ArrayList<FichasDeMedicamentos> selectMedicamentoPorNombreProductoVeredicto(String nombreMedicamento) {
        Session sesion = abrirSesion();
        ArrayList<FichasDeMedicamentos> listaMedicamentos = new ArrayList<>();
        Query q = sesion.createQuery("from FichasDeMedicamentos as m where m.idMedicamento in(select fichasDeMedicamentos.idMedicamento from ProductosUnion as p where p.productos.componente like ?) and m.veredicto=1 order by medicamento");
        nombreMedicamento = "%"+ nombreMedicamento + "%";
        q.setString(0, nombreMedicamento);
        List<FichasDeMedicamentos> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            FichasDeMedicamentos medicamento = (FichasDeMedicamentos) iter.next();
            listaMedicamentos.add(medicamento);
        }
        cerrarSesion(sesion);
        return listaMedicamentos;
    }
    //Obtiene todos los medicamentos de la tabla fichas de medicamentos
    public ArrayList<FichasDeMedicamentos> selectAllMedicamentosVeredicto() {
        Session sesion = abrirSesion();
        ArrayList<FichasDeMedicamentos> listaMedicamentos = new ArrayList<>();
        Query q = sesion.createQuery("from FichasDeMedicamentos as m where m.veredicto=1 order by medicamento ");
        List<FichasDeMedicamentos> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            FichasDeMedicamentos medicamento = (FichasDeMedicamentos) iter.next();
            listaMedicamentos.add(medicamento);
        }
        cerrarSesion(sesion);
        return listaMedicamentos;
    }

    //Obtener los medicamentos dependiendo del nombre del medicamento
    public ArrayList<FichasDeMedicamentos> selectMedicamentoPorNombreVeredicto(String nombreMedicamento) {
        Session sesion = abrirSesion();
        ArrayList<FichasDeMedicamentos> listaMedicamentos = new ArrayList<>();
        Query q = sesion.createQuery("from FichasDeMedicamentos as m where m.medicamento like ? and m.veredicto=1 order by medicamento");
        nombreMedicamento = "%"+ nombreMedicamento + "%";
        q.setString(0, nombreMedicamento);
        List<FichasDeMedicamentos> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            FichasDeMedicamentos medicamento = (FichasDeMedicamentos) iter.next();
            listaMedicamentos.add(medicamento);
        }
        cerrarSesion(sesion);
        return listaMedicamentos;
    }
    

    //Obtener los productos de un medicamento
    public ArrayList<ProductosUnion> selectProductosDeMedicamento(int idMedicamento, Session sesion) {

        ArrayList<ProductosUnion> listaMedicamentos = new ArrayList<>();
        Query q = sesion.createQuery("from ProductosUnion as p where p.fichasDeMedicamentos.idMedicamento = ? order by orden");
        q.setInteger(0, idMedicamento);
        List<ProductosUnion> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            ProductosUnion productoUnion = (ProductosUnion) iter.next();
            listaMedicamentos.add(productoUnion);
        }
        return listaMedicamentos;
    }

    //Obtener la maquinaria de un medicamento
    public ArrayList<MaquinariaUnion> selectMaquinariaDeMedicamento(int idMedicamento, Session sesion) {

        ArrayList<MaquinariaUnion> listaMaquinaria = new ArrayList<>();
        Query q = sesion.createQuery("from MaquinariaUnion as p where p.fichasDeMedicamentos.idMedicamento = ? ");
        q.setInteger(0, idMedicamento);
        List<MaquinariaUnion> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            MaquinariaUnion maquinariaUnion = (MaquinariaUnion) iter.next();
            listaMaquinaria.add(maquinariaUnion);
        }
        return listaMaquinaria;
    }

    //Obtener los materiales para envasar de un medicamento
    public ArrayList<MaterialDeEnvasadoUnion> selectMaterialEnvasarDeMedicamento(int idMedicamento, Session sesion) {

        ArrayList<MaterialDeEnvasadoUnion> listaMaterialEnvasar = new ArrayList<>();
        Query q = sesion.createQuery("from MaterialDeEnvasadoUnion as p where p.fichasDeMedicamentos.idMedicamento = ? ");
        q.setInteger(0, idMedicamento);
        List<MaterialDeEnvasadoUnion> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            MaterialDeEnvasadoUnion materialUnion = (MaterialDeEnvasadoUnion) iter.next();
            listaMaterialEnvasar.add(materialUnion);
        }
        return listaMaterialEnvasar;
    }

    //Obtener los materiales para elaborar de un medicamento
    public ArrayList<MaterialParaElaborarUnion> selectMaterialElaborarDeMedicamento(int idMedicamento, Session sesion) {

        ArrayList<MaterialParaElaborarUnion> listaMaterialElaborar = new ArrayList<>();
        Query q = sesion.createQuery("from MaterialParaElaborarUnion as p where p.fichasDeMedicamentos.idMedicamento = ? ");
        q.setInteger(0, idMedicamento);
        List<MaterialParaElaborarUnion> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            MaterialParaElaborarUnion materialUnion = (MaterialParaElaborarUnion) iter.next();
            listaMaterialElaborar.add(materialUnion);
        }
        return listaMaterialElaborar;
    }

    //Añadir una maquinaria a un medicamento
    public void nuevoMaquinariaUnion(MaquinariaUnion maquinaria) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(maquinaria);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }

    //Añadir un material para elaborar a un medicamento
    public void nuevoMaterialElaborarUnion(MaterialParaElaborarUnion material) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(material);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }

    //Añadir un material de envasar a un medicamento
    public void nuevoMaterialEnvasarUnion(MaterialDeEnvasadoUnion material) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(material);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }
    
    //Añadir un producto a un medicamento
    public void nuevoProductoUnion(ProductosUnion producto) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(producto);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }
     //Eliminar una maquinaria de un medicamento
    public void eliminarMaquinariaUnion(MaquinariaUnion maquinaria) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.delete(maquinaria);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }

    //Eliminar un material para elaborar de un medicamento
    public void eliminarMaterialElaborarUnion(MaterialParaElaborarUnion material) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.delete(material);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }

    //Eliminar un material de envasar de un medicamento
    public void eliminarMaterialEnvasarUnion(MaterialDeEnvasadoUnion material) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.delete(material);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }
    
    //Eliminar un producto de un medicamento
    public void eliminarProductoUnion(ProductosUnion producto) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.delete(producto);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }
    //Añadir un producto a un medicamento
    public void modificarProductoUnion(ProductosUnion producto) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(producto);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        }catch(DataException ex){
            throw ex;
        } finally {
            cerrarSesion(sesion);
        }
    }
    
    //Obtener el último orden de un producto perteneciente a un medicamento en concreto
    public String maxOrden(int idMedicamento){
        Session sesion = abrirSesion();
        ArrayList<ProductosUnion> listaMedicamentos = new ArrayList<>();
        Query q = sesion.createQuery("from ProductosUnion as p where p.fichasDeMedicamentos.idMedicamento = ? order by orden");
        q.setInteger(0, idMedicamento);
        List<ProductosUnion> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            ProductosUnion productoUnion = (ProductosUnion) iter.next();
            listaMedicamentos.add(productoUnion);
        }
        cerrarSesion(sesion);
        if(listaMedicamentos.size()>0)
            return listaMedicamentos.get(listaMedicamentos.size()-1).getOrden();
        else
            return "`";//El anterior a la 'a'
    }

}
