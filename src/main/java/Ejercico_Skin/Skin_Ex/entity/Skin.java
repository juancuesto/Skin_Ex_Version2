package Ejercico_Skin.Skin_Ex.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skins")
public class Skin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Configuracion")
    private Boolean configuracion;
    @Column(name = "Color")
    private String color;
    @Column(name = "Precio")
    private Double precio;

    public Skin() {
    }

    public Skin(Long id,String nombre, Boolean configuracion, String color, Double precio) {
        this.nombre = nombre;
        this.configuracion = configuracion;
        this.color = color;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Boolean configuracion) {
        this.configuracion = configuracion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Skin{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", configuracion='" + configuracion + '\'' +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                '}';
    }
}
