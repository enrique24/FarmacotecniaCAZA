
package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.FichasDeMedicamentos;
import es.sacyl.caza.farmacia.modelo.clases.MaquinariaUnion;
import es.sacyl.caza.farmacia.modelo.clases.MaterialDeEnvasadoUnion;
import es.sacyl.caza.farmacia.modelo.clases.MaterialParaElaborarUnion;
import es.sacyl.caza.farmacia.modelo.clases.ProductosUnion;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *Clase usada para pasar datos a los reportes
 * 
 * @author Enrique Martín Arenal
 */
public class FichasDeMedicamentosDataSource implements JRDataSource {

    private int indiceMedicamentoActual = -1;
    private List<FichasDeMedicamentos> medicamentos = new ArrayList<FichasDeMedicamentos>();
    private MaquinariaDataSource maquinaria;
    private MaterialElaborarDataSource materialELaborar;
    private MaterialEnvasarDataSource materialEnvasar;
    private ProductosDataSource productos;
    private ProductosDataSource productosEtiquetas;

    public FichasDeMedicamentosDataSource(List<FichasDeMedicamentos> medicamentos, List<MaquinariaUnion> maquinaria,List<MaterialParaElaborarUnion> materialElaborar, List<MaterialDeEnvasadoUnion> materialEnvasar, List<ProductosUnion> productos) {
        this.medicamentos= medicamentos;
        this.maquinaria= new MaquinariaDataSource(maquinaria);
        this.materialELaborar= new MaterialElaborarDataSource(materialElaborar);
        this.materialEnvasar= new MaterialEnvasarDataSource(materialEnvasar);
        this.productos= new ProductosDataSource(productos);
        this.productosEtiquetas= new ProductosDataSource(productos);
    }
    

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object valor = null;
        if ("medicamento".equals(jrField.getName())) {
            valor = medicamentos.get(indiceMedicamentoActual).getMedicamento();
        } else if ("productos".equals(jrField.getName())) {
            valor = productos;
        } else if ("maquinaria".equals(jrField.getName())) {
            valor = maquinaria;
        } else if ("materialEnvasar".equals(jrField.getName())) {
            valor = materialEnvasar;
        } else if ("materialElaborar".equals(jrField.getName())) {
            valor = materialELaborar;
        } else if ("procedimiento".equals(jrField.getName())) {
            valor = medicamentos.get(indiceMedicamentoActual).getProcedimiento();
        } else if ("Observaciones".equals(jrField.getName())) {
            valor = medicamentos.get(indiceMedicamentoActual).getObservaciones();
        } else if ("ObservacionesElaboracion".equals(jrField.getName())) {
             valor = medicamentos.get(indiceMedicamentoActual).getObservacParaElaboracion();
        }else if ("viaAdministracion".equals(jrField.getName())) {
             valor = medicamentos.get(indiceMedicamentoActual).getViaDeAdmon();
        }else if ("organolepsisDatos".equals(jrField.getName())) {
             valor = medicamentos.get(indiceMedicamentoActual).getOrganolepsisDatos();
        }else if ("estabilidad".equals(jrField.getName())) {
             valor = medicamentos.get(indiceMedicamentoActual).getEstabilidad();
        }
        else if ("productosEtiquetas".equals(jrField.getName())) {
             valor = productosEtiquetas;
        }
        
        return valor;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceMedicamentoActual < medicamentos.size();
    }

}
