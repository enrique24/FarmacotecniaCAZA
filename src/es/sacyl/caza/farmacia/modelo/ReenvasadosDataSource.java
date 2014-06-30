
package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.Reenvasados;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *Clase usada para pasar datos de un reenvasado a los reportes
 * @author Enrique Martín Arenal
 */
public class ReenvasadosDataSource implements JRDataSource {

    private int indiceReenvasadoActual = -1;
    private List<Reenvasados> reenvasado = new ArrayList<Reenvasados>();

    public ReenvasadosDataSource(List<Reenvasados> etiquetas) {
        this.reenvasado= etiquetas;
    }
    

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object valor = null;
        if ("descripcion".equals(jrField.getName())) {
            valor = reenvasado.get(indiceReenvasadoActual).getDescripcion();
        } else if ("principioActivo".equals(jrField.getName())) {
            if(reenvasado.get(indiceReenvasadoActual).getPrincipioActivo().length()>26)
                valor = reenvasado.get(indiceReenvasadoActual).getPrincipioActivo().substring(0, 26);
            else
                valor=reenvasado.get(indiceReenvasadoActual).getPrincipioActivo();
        } else if ("fechaCaducidad".equals(jrField.getName())) {
            valor = reenvasado.get(indiceReenvasadoActual).getCaduca();
        } else if ("lote".equals(jrField.getName())) {
            valor = reenvasado.get(indiceReenvasadoActual).getLot();
        }
        return valor;
    
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceReenvasadoActual < reenvasado.size();
    }

  

}
