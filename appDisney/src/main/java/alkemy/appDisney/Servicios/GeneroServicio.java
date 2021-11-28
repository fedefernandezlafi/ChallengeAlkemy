/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.Servicios;

import alkemy.appDisney.Entidades.Genero;
import alkemy.appDisney.Entidades.Pelicula;
import alkemy.appDisney.Repositorios.GeneroRepositorio;
import alkemy.appDisney.Repositorios.PeliculaRepositorio;
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
public class GeneroServicio {

    @Autowired
    private GeneroRepositorio generoRepositorio;
    
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    
    public List <Genero> listarGeneros(){
        return generoRepositorio.listarGeneros();
    }

    public Optional<Genero> getOne(int id) {
        return generoRepositorio.findById(id);
    }

    public Optional<Genero> getByNombre(String nombre) {
        return generoRepositorio.findByNombre(nombre);
    }

    public void crearGenero(Genero genero) {      
        generoRepositorio.save(genero);
    }

    public void eliminarGeneroPorId(int id) {
        generoRepositorio.deleteById(id);
    }

    public boolean existsById(int id) {
        return generoRepositorio.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return generoRepositorio.existsByNombre(nombre);
    }
    
    public void setearPeliculas (int idPelicula, int idGenero) {
        List <Pelicula> peliculas = peliculaRepositorio.listarPorGenero(idGenero);
        Genero genero = generoRepositorio.getById(idGenero);
        genero.setPeliculas(peliculas);
        generoRepositorio.save(genero);
    }
}
