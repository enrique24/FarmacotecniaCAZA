package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.Etiquetas;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Clase usada para pasar datos de una etiqueta a los reportes
 *
 * @author Enrique Martín Arenal
 */
public class EtiquetasDataSource implements JRDataSource {

    private int indiceEtiquetaActual = -1;
    private List<Etiquetas> etiquetas = new ArrayList<Etiquetas>();

    public EtiquetasDataSource(List<Etiquetas> etiquetas) {
        this.etiquetas= etiquetas;
    }
    

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object valor = null;
        if ("descripcion".equals(jrField.getName())) {
            valor = etiquetas.get(indiceEtiquetaActual).getDescripcion();
        } else if ("caducidad".equals(jrField.getName())) {
            valor = etiquetas.get(indiceEtiquetaActual).getCaducidad();
        } else if ("fecha_elaboracion".equals(jrField.getName())) {
            valor = etiquetas.get(indiceEtiquetaActual).getFechaDeElaboracion();
        } else if ("conservacion".equals(jrField.getName())) {
            valor = etiquetas.get(indiceEtiquetaActual).getConservacion();
        } else if ("concentracion".equals(jrField.getName())) {
            valor = etiquetas.get(indiceEtiquetaActual).getConcentracion();
        } else if("utilizacion".equals(jrField.getName())){
            valor=etiquetas.get(indiceEtiquetaActual).getUtilizacion();
        } else if("paciente".equals(jrField.getName())){
            valor=etiquetas.get(indiceEtiquetaActual).getNombreDelPaciente();
        } else if("fecha".equals(jrField.getName())){
            valor=etiquetas.get(indiceEtiquetaActual).getFechaDeElaboracion();
        }
        return valor;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceEtiquetaActual < etiquetas.size();
    }

  

}
