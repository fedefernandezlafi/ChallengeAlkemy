/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.dto;

import alkemy.appDisney.Entidades.Pelicula;
import java.util.List;

/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
public class GeneroDto {
    private String nombre;
    private String foto;    
    private List <Pelicula> peliculas;

    public GeneroDto() {
    }

    public GeneroDto(String nombre, String foto, List<Pelicula> peliculas) {
        this.nombre = nombre;
        this.foto = foto;
        this.peliculas = peliculas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
    

}
