
package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.MaterialDeEnvasadoUnion;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Clase usada para pasar datos a los reportes
 * @author Enrique Martín Arenal
 */
public class MaterialEnvasarDataSource implements JRDataSource {

    private int indiceMaterialEnvasarActual = -1;
    private List<MaterialDeEnvasadoUnion> materialEnvasar = new ArrayList<MaterialDeEnvasadoUnion>();

    public MaterialEnvasarDataSource(List<MaterialDeEnvasadoUnion> materialEnvasar) {
        this.materialEnvasar= materialEnvasar;
    }
    

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object valor = null;
        if ("materialEnvasar".equals(jrField.getName())) {
            valor = materialEnvasar.get(indiceMaterialEnvasarActual).getMaterialDeEnvasado().getParaEnvasar();
        }
        return valor;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceMaterialEnvasarActual < materialEnvasar.size();
    }


}
