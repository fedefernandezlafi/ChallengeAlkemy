/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alkemy.appDisney.Servicios;

import alkemy.appDisney.Entidades.Genero;
import alkemy.appDisney.Entidades.Pelicula;
import alkemy.appDisney.Entidades.Personaje;
import alkemy.appDisney.Repositorios.GeneroRepositorio;
import alkemy.appDisney.Repositorios.PeliculaRepositorio;
import alkemy.appDisney.Repositorios.PersonajeRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
@Service
@Transactional
public class PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;   
    
    @Autowired
    private PersonajeRepositorio personajeRepositorio;
    
    @Autowired
    private GeneroRepositorio generoRepositorio;

    public List <Pelicula> listarTodas() {
        return peliculaRepositorio.findAll();
    }

    public Optional <Pelicula> getOne(int id) {
        return peliculaRepositorio.findById(id);
    }

    public Optional<Pelicula> getByTitulo(String titulo) {
        return peliculaRepositorio.findByTituloContaining(titulo);
    }

    public void save(Pelicula pelicula) {      
        peliculaRepositorio.save(pelicula);
    }

    public void eliminarPeliculaPorId(int id) {
        peliculaRepositorio.deleteById(id);
    }

    public boolean existsById(int id) {
        return peliculaRepositorio.existsById(id);
    }

    public boolean existsByTitulo(String titulo) {
        return peliculaRepositorio.existsByTitulo(titulo);
    }

    public List<Pelicula> listarporGenero(int idGenero) {
        return peliculaRepositorio.listarPorGenero(idGenero);
    }

    public Pelicula buscarPorNombre (String titulo) {
        return peliculaRepositorio.buscarPorNombre(titulo);
    }
//    public List<Pelicula> listarCreacionASC() {
//        return peliculaRepositorio.listarCreacionASC();
//    }
//
//    public List<Pelicula> listarCreacionDESC() {
//        return peliculaRepositorio.listarCreacionDESC();
//    }
//    
     public Pelicula vincularPersonajesNuevoPers (Personaje personaje, int idPelicula) {
        
        Pelicula pelicula = peliculaRepositorio.getById(idPelicula);
        List <Personaje> peliculas = pelicula.getPersonajes();
        peliculas.add(personaje);
        peliculaRepositorio.save(pelicula);   
        return pelicula;
    }
  
    
    public Genero vincularGeneros (int idPelicula, int idGenero){ 
        Pelicula pelicula = peliculaRepositorio.getById(idPelicula);
        Genero genero = generoRepositorio.getById(idGenero);
        pelicula.setGenero(genero);
        peliculaRepositorio.save(pelicula);
        return genero;
    }
    
        public List<Personaje> vincularExistentes (int id){
       
        List <Personaje> personajes = personajeRepositorio.buscarPorPelicula(id);
        Pelicula pelicula = peliculaRepositorio.getById(id); 
        for (Personaje personaje : personajes) {
            if (!personajeRepositorio.existsByNombre(personaje.getNombre()))
           personajes.add(personaje);
        }        
        pelicula.setPersonajes(personajes);
        peliculaRepositorio.save(pelicula);
        return personajes;
    }
}
    
    


