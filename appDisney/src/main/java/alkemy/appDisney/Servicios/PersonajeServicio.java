package alkemy.appDisney.Servicios;

import alkemy.appDisney.Entidades.Personaje;
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
public class PersonajeServicio {
    @Autowired
    private PersonajeRepositorio personajeRepositorio; 
    
    public List<String> listarTodas() {
        return personajeRepositorio.listarTodos();
    }

    public Optional<Personaje> getOne(int id) {
        return personajeRepositorio.findById(id);
    }

    public Optional<Personaje> getByNombre(String nombre) {
        return personajeRepositorio.findByNombre(nombre);
    }

    public void crearPersonaje(Personaje personaje) {      
        personajeRepositorio.save(personaje);
    }

    public void eliminarPersonajePorId(int id) {
        personajeRepositorio.deleteById(id);
    }

    public boolean existsById(int id) {
        return personajeRepositorio.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return personajeRepositorio.existsByNombre(nombre);
    }

    public List<Personaje> buscarPorNombre(String nombre) {
        return personajeRepositorio.buscarPorNombre(nombre);
    }

    public List<Personaje> buscarPorEdad(int edad) {
        return personajeRepositorio.buscarPorEdad(edad);
    }
//
//    public List<Personaje> listarPorPelicula(int id) {
//        return personajeRepositorio.buscarPorPelicula(id);
//    }
//    
//     public List<Personaje> vincularPersonajes(int id) {
//        return personajeRepositorio.vincularPersonajes(id);
//    }
}
