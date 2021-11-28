/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
@Entity
public class Genero {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)   
    private int id;
    private String nombre;
    private String foto;
    @JsonIgnore
    @OneToMany (mappedBy = "genero")
    private List <Pelicula> peliculas;
    
    public Genero() {
        
    }

    public Genero(int id, String nombre, String foto, List<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.peliculas = peliculas;
    }
    
    

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
    
           
}
