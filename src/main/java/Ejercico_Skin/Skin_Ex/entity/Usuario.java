package Ejercico_Skin.Skin_Ex.entity;

import Ejercico_Skin.Skin_Ex.Excepciones.EcceptionSkinNotFound;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "Edad")
    private int edad;
    @Column(name = "Saldo")
    private double saldo;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id")
    private List<Skin> skins;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, int edad, double saldo, List<Skin> skins) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.saldo = saldo;
        this.skins = skins;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Skin> getSkins() {
        return skins;
    }

    public void setSkins(List<Skin> skins) {
        this.skins = skins;
    }

    public List<Skin> afegirSkin(Skin skin) {
        this.skins.add(skin);
        return skins;
    }


    public List<Skin> eliminarSkin(Skin skin) {
        skins.remove(skin);
        return skins;
    }

}
