package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.Etiquetas;
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
public class EtiquetasDAO extends DAO {

    //Añadir una nueva etiqueta a la tabla etiquetas
    public void nuevoEtiquetas(Etiquetas etiquetas) throws org.hibernate.exception.DataException {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(etiquetas);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }

    }

    //Modificar una etiqueta ya existente de la tabla etiquetas
    public void modificarEtiquetas(Etiquetas etiquetas) throws org.hibernate.exception.DataException {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(etiquetas);
            tx.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } finally {
            cerrarSesion(sesion);
        }
    }

    //Eliminar una etiqueta existente de la tabla etiquetas
    public void eliminarEtiquetas(Etiquetas etiquetas) {
        Session sesion = abrirSesion();
        Transaction tx = sesion.beginTransaction();
        sesion.delete(etiquetas);
        tx.commit();
        cerrarSesion(sesion);
    }

    //Obtener todas las etiquetas de la tabla etiquetas
    public ArrayList<Etiquetas> selectAllEtiquetas() {
        Session sesion = abrirSesion();
        ArrayList<Etiquetas> listaEtiquetas = new ArrayList<>();
        Query q = sesion.createQuery("from Etiquetas order by descripcion");
        List<Etiquetas> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Etiquetas etiquetas = (Etiquetas) iter.next();
            listaEtiquetas.add(etiquetas);
        }
        cerrarSesion(sesion);
        return listaEtiquetas;
    }

    //Obtener las etiquetas que cumplan la condición
    public ArrayList<Etiquetas> selectEtiquetasPorNombre(String nombreEtiquetas) {
        Session sesion = abrirSesion();
        ArrayList<Etiquetas> listaEtiquetas = new ArrayList<>();
        Query q = sesion.createQuery("from Etiquetas as m where m.descripcion like ? order by descripcion");
        nombreEtiquetas = nombreEtiquetas + "%";
        q.setString(0, nombreEtiquetas);
        List<Etiquetas> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Etiquetas etiquetas = (Etiquetas) iter.next();
            listaEtiquetas.add(etiquetas);
        }
        cerrarSesion(sesion);
        return listaEtiquetas;
    }

    //Obtener etiquetas dependiendo del tipo de etiqueta
    public ArrayList<Etiquetas> selectEtiquetasPorTipoEtiqueta(String tipoEti) {
        Session sesion = abrirSesion();
        ArrayList<Etiquetas> listaEtiquetas = new ArrayList<>();
        Query q = sesion.createQuery("from Etiquetas as m where m.tipoEtiqueta = ? order by descripcion");
        q.setString(0, tipoEti);
        List<Etiquetas> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Etiquetas etiquetas = (Etiquetas) iter.next();
            listaEtiquetas.add(etiquetas);
        }
        cerrarSesion(sesion);
        return listaEtiquetas;
    }

    //Obtener etiqueta de una fichaDeMedicamento
    public ArrayList<Etiquetas> selectEtiquetasPorFichaDeMedicamento(String nombreMedicamento) {
        Session sesion = abrirSesion();
        ArrayList<Etiquetas> listaEtiquetas = new ArrayList<>();
        Query q = sesion.createQuery("from Etiquetas as m where m.fichasDeMedicamentos.medicamento = ? order by descripcion");
        q.setString(0, nombreMedicamento);
        List<Etiquetas> lista = q.list();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            // extraer el objeto
            Etiquetas etiquetas = (Etiquetas) iter.next();
            listaEtiquetas.add(etiquetas);
        }
        cerrarSesion(sesion);
        return listaEtiquetas;
    }

    //Obtiene el nombre del medicamento relacionado con una etiqueta
    public String selectNombreFichaDeMedicamento(Etiquetas etiqueta) {
        Session sesion = abrirSesion();

        Query q = sesion.createQuery("from Etiquetas as m where m.idEtiqueta = ?");
        q.setInteger(0, etiqueta.getIdEtiqueta());
        List<Etiquetas> lista = q.list();
        Iterator iter = lista.iterator();
        Etiquetas etiquetas = (Etiquetas) iter.next();
        if (etiquetas.getFichasDeMedicamentos() != null) {
            String nombreMedicamento = etiquetas.getFichasDeMedicamentos().getMedicamento();
            cerrarSesion(sesion);
            return nombreMedicamento;

        }else
            return "?";

    }
    
    //Devuelve si el medicamento con el que está relacionado la etiqueta es una fórmula magistral o no
    public boolean esFormulaMagistral(Etiquetas etiqueta){
        Session sesion = abrirSesion();

        Query q = sesion.createQuery("from Etiquetas as m where m.idEtiqueta = ?");
        q.setInteger(0, etiqueta.getIdEtiqueta());
        List<Etiquetas> lista = q.list();
        Iterator iter = lista.iterator();
        Etiquetas etiquetas = (Etiquetas) iter.next();
        if(etiquetas.getFichasDeMedicamentos()!= null)
            return etiquetas.getFichasDeMedicamentos().getTipo().equals("F.M");
        else
            return false;
        
    }

}
