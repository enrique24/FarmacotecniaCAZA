package es.sacyl.caza.farmacia.modelo;

import es.sacyl.caza.farmacia.modelo.clases.ProductosUnion;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Clase usada para pasar datos a los reportes
 *
 * @author Enrique Martín Arenal
 */
public class ProductosDataSource implements JRDataSource {

    private int indiceProductoActual = -1;
    private List<ProductosUnion> productos = new ArrayList<ProductosUnion>();

    public ProductosDataSource(List<ProductosUnion> productos) {
        this.productos = productos;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object valor = null;
        if ("orden".equals(jrField.getName())) {
            valor = productos.get(indiceProductoActual).getOrden();
        } else if ("producto".equals(jrField.getName())) {
            valor = productos.get(indiceProductoActual).getProductos().getComponente();
        } else if ("referencia".equals(jrField.getName())) {
            valor = productos.get(indiceProductoActual).getProductos().getReferencia();
        } else if ("cantidad".equals(jrField.getName())) {
            valor = productos.get(indiceProductoActual).getCantidad();
        } else if ("unidad".equals(jrField.getName())) {
            valor = productos.get(indiceProductoActual).getUnidades();
        }

        return valor;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceProductoActual < productos.size();
    }

}
