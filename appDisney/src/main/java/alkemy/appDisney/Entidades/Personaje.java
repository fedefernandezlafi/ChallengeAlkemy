/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;


/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
@Entity
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int id;
    private String foto;
    private String nombre;
    private int edad;
    private Float Peso;
    private String historia;   
    @JsonIgnore
    @ManyToOne
    private Pelicula pelicula;

    public Personaje() {
    }

    public Personaje(int id, String foto, String nombre, int edad, Float Peso, String historia, Pelicula pelicula) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.edad = edad;
        this.Peso = Peso;
        this.historia = historia;
        this.pelicula = pelicula;
    }    
    
    public String getFoto() {
        return foto;
    }

    public int getEdad() {
        return edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Float getPeso() {
        return Peso;
    }

    public void setPeso(Float Peso) {
        this.Peso = Peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    
    public void setFoto(String foto) {
        this.foto = foto;
    }

   
    public void setEdad(int edad) {
        this.edad = edad;
    }
}
