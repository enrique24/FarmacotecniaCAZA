package es.sacyl.caza.farmacia.modelo.clases;
// Generated 30-abr-2014 13:00:04 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Productos generated by hbm2java
 */
public class Productos  implements java.io.Serializable {


     private Integer idComponente;
     private String componente;
     private String referencia;
     private Boolean activo;
     private String observaciones;
     private String estante;
     private Set productosUnions = new HashSet(0);

    public Productos() {
    }

    public Productos(String componente, String referencia, Boolean activo, String observaciones, String estante, Set productosUnions) {
       this.componente = componente;
       this.referencia = referencia;
       this.activo = activo;
       this.observaciones = observaciones;
       this.estante = estante;
       this.productosUnions = productosUnions;
    }
   
    public Integer getIdComponente() {
        return this.idComponente;
    }
    
    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }
    public String getComponente() {
        return this.componente;
    }
    
    public void setComponente(String componente) {
        this.componente = componente;
    }
    public String getReferencia() {
        return this.referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public Boolean getActivo() {
        return this.activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getEstante() {
        return this.estante;
    }
    
    public void setEstante(String estante) {
        this.estante = estante;
    }
    public Set getProductosUnions() {
        return this.productosUnions;
    }
    
    public void setProductosUnions(Set productosUnions) {
        this.productosUnions = productosUnions;
    }




}


