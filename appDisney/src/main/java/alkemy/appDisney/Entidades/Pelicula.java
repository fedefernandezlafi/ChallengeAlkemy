/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.Entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
@Entity
public class Pelicula {
    @Id    
    @GeneratedValue (strategy = GenerationType.IDENTITY)   
    private int id;
    private String foto;
    private String titulo;
    private int dia;
    private int mes;
    private int anio;    
    private LocalDateTime creacion;
    private int calificacion;
    
    @OneToMany (mappedBy = "pelicula")
    private List <Personaje> personajes;
    @ManyToOne
    private Genero genero;

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Pelicula() {
    }

    public Pelicula(int id, String foto, String titulo, int dia, int mes, int ano, int calificacion, List<Personaje> personajes, Genero genero) {
        this.id = id;
        this.foto = foto;
        this.titulo = titulo;
        this.dia = dia;
        this.mes = mes;
        this.anio = ano;
        this.calificacion = calificacion;
        this.personajes = personajes;
        this.genero = genero;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }   

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
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
}
