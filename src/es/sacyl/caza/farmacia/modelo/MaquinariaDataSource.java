
package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.MaquinariaUnion;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *Clase usada para pasar datos a los reportes
 * @author Enrique Martín Arenal
 */
public class MaquinariaDataSource implements JRDataSource {

    private int indiceMaquinariaActual = -1;
    private List<MaquinariaUnion> maquinaria = new ArrayList<MaquinariaUnion>();

    public MaquinariaDataSource(List<MaquinariaUnion> maquinaria) {
        this.maquinaria= maquinaria;
    }
    

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object valor = null;
        if ("maquinaria".equals(jrField.getName())) {
            valor = maquinaria.get(indiceMaquinariaActual).getMaquinaria().getMaquinaria();
        }
        return valor;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceMaquinariaActual < maquinaria.size();
    }

}
