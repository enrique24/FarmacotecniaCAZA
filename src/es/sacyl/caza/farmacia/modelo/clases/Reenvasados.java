package es.sacyl.caza.farmacia.modelo.clases;
// Generated 30-abr-2014 13:00:04 by Hibernate Tools 3.2.1.GA



/**
 * Reenvasados generated by hbm2java
 */
public class Reenvasados  implements java.io.Serializable {


     private Integer idReenvasado;
     private String descripcion;
     private String principioActivo;
     private String lot;
     private String caduca;

    public Reenvasados() {
    }

	
    public Reenvasados(String descripcion) {
        this.descripcion = descripcion;
    }
    public Reenvasados(String descripcion, String principioActivo, String lot, String caduca) {
       this.descripcion = descripcion;
       this.principioActivo = principioActivo;
       this.lot = lot;
       this.caduca = caduca;
    }
   
    public Integer getIdReenvasado() {
        return this.idReenvasado;
    }
    
    public void setIdReenvasado(Integer idReenvasado) {
        this.idReenvasado = idReenvasado;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getPrincipioActivo() {
        return this.principioActivo;
    }
    
    public void setPrincipioActivo(String principioActivo) {
        this.principioActivo = principioActivo;
    }
    public String getLot() {
        return this.lot;
    }
    
    public void setLot(String lot) {
        this.lot = lot;
    }
    public String getCaduca() {
        return this.caduca;
    }
    
    public void setCaduca(String caduca) {
        this.caduca = caduca;
    }




}


