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
    private boolean configuracion;
    @Column(name = "Color")
    private String color;
    @Column(name = "Precio")
    private double precio;

    public Skin() {
    }

    public Skin(String nombre, boolean configuracion, String color, double precio) {
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

    public boolean getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(boolean configuracion) {
        this.configuracion = configuracion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
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
