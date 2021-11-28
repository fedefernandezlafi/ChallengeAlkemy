/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.Repositorios;

import alkemy.appDisney.Entidades.Genero;
import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, Integer> {
    Optional <Genero> findByNombre (String nombre);
    boolean existsById(int id);
    boolean existsByNombre(String nombre);
    
    @Query ("SELECT c FROM Genero c")
    public List <Genero> listarGeneros ();
}
