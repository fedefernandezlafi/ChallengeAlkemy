/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.dto;

import alkemy.appDisney.Entidades.Pelicula;

/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
public class PersonajeDto {
    private String foto;
    private String nombre;
    private int edad;
    private Float Peso;
    private String historia;
    private Pelicula pelicula;
    private int idPelicula;
    

    public PersonajeDto(String foto, String nombre, int edad, Float Peso, String historia, Pelicula pelicula, int idPelicula, int idGenero) {
        this.foto = foto;
        this.nombre = nombre;
        this.edad = edad;
        this.Peso = Peso;
        this.historia = historia;
        this.pelicula = pelicula;
        this.idPelicula = idPelicula;
     
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

   

    public PersonajeDto() {
    }
    
    
}