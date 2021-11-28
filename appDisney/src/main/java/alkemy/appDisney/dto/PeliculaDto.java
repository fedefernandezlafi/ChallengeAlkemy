/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.dto;

import alkemy.appDisney.Entidades.Genero;
import alkemy.appDisney.Entidades.Personaje;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;


import java.util.List;

/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
public class PeliculaDto {
   
    private String titulo;        
    private String foto;
    private int calificacion;   
    private int dia;
    private int mes;
    private int anio;
    private LocalDateTime creacion;
    private List <Personaje> personajes;
    private Genero genero;
    private int idPelicula;
    private int idGenero;
    
    public PeliculaDto() {
    }

    public PeliculaDto(String titulo, String foto, int calificacion, int dia, int mes, int anio, LocalDateTime creacion, List<Personaje> personajes, Genero genero, int idPelicula, int idGenero) {
        this.titulo = titulo;
        this.foto = foto;
        this.calificacion = calificacion;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.creacion = creacion;
        this.personajes = personajes;
        this.genero = genero;
        this.idPelicula = idPelicula;
        this.idGenero = idGenero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
}
