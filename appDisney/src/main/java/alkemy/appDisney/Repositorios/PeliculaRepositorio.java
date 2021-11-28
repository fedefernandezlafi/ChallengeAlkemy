/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.Repositorios;

import alkemy.appDisney.Entidades.Pelicula;
import alkemy.appDisney.Entidades.Personaje;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
@Repository
public interface PeliculaRepositorio extends JpaRepository< Pelicula, Integer> {
    Optional <Pelicula> findByTituloContaining (String titulo);
    
    boolean existsById(int id);
    
    boolean existsByTitulo(String titulo);
    
    public Optional <Pelicula> findById(int id);
    
    //falta creacion
    @Query ("SELECT c.titulo, c.foto FROM Pelicula c")
    public List <Pelicula> listarTodas ();
  
    @Query ("SELECT c FROM Pelicula c WHERE genero_id = :id")
    public List <Pelicula> listarPorGenero (int id);
    
    
//    
//    @Query ("Select * FROM Pelicula c ORDER BY c.creacion")
//    public List <Pelicula> listarCreacionASC ();
//    
//    @Query ("Select * FROM Pelicula c ORDER BY c.creacion DESC")
//    public List <Pelicula> listarCreacionDESC ();
//    falta id genero
    @Query ("Select c FROM Pelicula c WHERE genero_id = :id")
    public List <Pelicula> vincularGeneros(int id);
    
    @Query ("SELECT c FROM Pelicula c WHERE c.titulo = :titulo")
    public Pelicula buscarPorNombre (@Param ("titulo") String titulo);        
    }
    
    
    
//   

