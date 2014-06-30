package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.Reenvasados;
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
public class ReenvasadosDAO extends DAO {

    //Añadir una nuevo reenvasado a la tabla reenvasados
    public void nuevoReenvasados(Reenvasados reenvasados) throws org.hibernate.exception.DataException {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(reenvasados);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }

    }

    //Modificar un reenvasado ya existente de la tabla reenvasados
    public void modificarReenvasados(Reenvasados reenvasados) throws org.hibernate.exception.DataException {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(reenvasados);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }

    //Eliminar un reenvasado existente de la tabla reenvasados
    public void eliminarReenvasados(Reenvasados reenvasados) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        sesion.delete(reenvasados);
        tx.commit();
        cerrarSesion(sesion);
    }

    //Obtener todos los reenvasados de la tabla reenvasados
    public ArrayList<Reenvasados> selectAllReenvasados() {
        Session sesion = abrirSesion();
        ArrayList<Reenvasados> listaReenvasados = new ArrayList<>();
        Query q = sesion.createQuery("from Reenvasados order by descripcion");
        List<Reenvasados> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Reenvasados reenvasados = (Reenvasados) iter.next();
            listaReenvasados.add(reenvasados);
        }
        cerrarSesion(sesion);
        return listaReenvasados;
    }

    //Obtener los reenvasados cuyo campo descripcion concuerda con el String que se le pasa al método
    public ArrayList<Reenvasados> selectReenvasadosPorDescripcion(String descripcion) {
        Session sesion = abrirSesion();
        ArrayList<Reenvasados> listaReenvasados = new ArrayList<>();
        Query q = sesion.createQuery("from Reenvasados as r where r.descripcion like ? order by descripcion");
        descripcion = descripcion + "%";
        q.setString(0, descripcion);
        List<Reenvasados> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Reenvasados reenvasados = (Reenvasados) iter.next();
            listaReenvasados.add(reenvasados);
        }
        cerrarSesion(sesion);
        return listaReenvasados;
    }

    //Obtener los reenvasados cuyo campo principio activo concuerda con el String que se le pasa al método
    public ArrayList<Reenvasados> selectReenvasadosPorPrincipioActivo(String principioActivo) {
        Session sesion = abrirSesion();
        ArrayList<Reenvasados> listaReenvasados = new ArrayList<>();
        Query q = sesion.createQuery("from Reenvasados as r where r.principioActivo like ? order by descripcion");
        principioActivo = "%" + principioActivo + "%";
        q.setString(0, principioActivo);
        List<Reenvasados> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Reenvasados reenvasados = (Reenvasados) iter.next();
            listaReenvasados.add(reenvasados);
        }
        cerrarSesion(sesion);
        return listaReenvasados;
    }

}
