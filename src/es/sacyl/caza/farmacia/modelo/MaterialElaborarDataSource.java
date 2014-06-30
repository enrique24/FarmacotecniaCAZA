
package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.MaterialParaElaborarUnion;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *Clase usada para pasar datos a los reportes
 * @author Enrique Martín Arenal
 */
public class MaterialElaborarDataSource implements JRDataSource {

    private int indiceMaterialElaborarActual = -1;
    private List<MaterialParaElaborarUnion> materialElaborar = new ArrayList<MaterialParaElaborarUnion>();

    public MaterialElaborarDataSource(List<MaterialParaElaborarUnion> materialElaborar) {
        this.materialElaborar= materialElaborar;
    }
    

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object valor = null;
        if ("materialElaborar".equals(jrField.getName())) {
            valor = materialElaborar.get(indiceMaterialElaborarActual).getMaterialParaElaborar().getMaterial();
        }
        return valor;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceMaterialElaborarActual < materialElaborar.size();
    }

}
