package model;
// Generated 26-feb-2024 4:40:59 by Hibernate Tools 4.3.1

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Compra generated by hbm2java
 */
public class Compra  implements java.io.Serializable {


     private int id;
     private String idUsuario;
     private String descripcion;
     private double precio;
     private Date fecha;
     private String imagen;

    public Compra() {
    }

	
    public Compra(int id) {
        this.id = id;
    }
    public Compra(int id, String idUsuario, String descripcion, double precio, Date fecha, String imagen) {
       this.id = id;
       this.idUsuario = idUsuario;
       this.descripcion = descripcion;
       this.precio = precio;
       this.fecha = fecha;
       this.imagen = imagen;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public String getFechaFormateada() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(this.fecha);
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getImagen() {
        return this.imagen;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }




}

