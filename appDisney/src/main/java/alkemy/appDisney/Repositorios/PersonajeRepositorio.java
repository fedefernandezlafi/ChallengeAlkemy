/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.Repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import alkemy.appDisney.Entidades.Personaje;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, Integer> {
    Optional <Personaje> findByNombre (String nombre);
    boolean existsById(int id);
    boolean existsByNombre(String nombre);
    
    @Query ("SELECT c.nombre, c.foto FROM Personaje c")
    public List <String> listarTodos ();
  
    @Query ("SELECT c FROM Personaje c WHERE c.nombre = :nombre")
    public List <Personaje> buscarPorNombre(String nombre);
    
    @Query ("SELECT c FROM Personaje c WHERE c.edad = :edad")
    public List <Personaje> buscarPorEdad (int edad);
    
    @Query ( value = "SELECT c.nombre, c.edad, c.peso, c.historia, c.foto FROM Personaje c WHERE pelicula_id = :id", nativeQuery = true)            
    public List <Personaje> buscarPorPelicula (int id);
}
