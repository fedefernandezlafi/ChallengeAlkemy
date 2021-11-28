/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.Controladores;

import alkemy.appDisney.Entidades.Pelicula;
import alkemy.appDisney.Entidades.Personaje;
import alkemy.appDisney.Repositorios.PeliculaRepositorio;
import alkemy.appDisney.Repositorios.PersonajeRepositorio;
import alkemy.appDisney.Servicios.PeliculaServicio;
import alkemy.appDisney.Servicios.PersonajeServicio;
import alkemy.appDisney.dto.Mensaje;
import alkemy.appDisney.dto.PeliculaDto;
import alkemy.appDisney.dto.PersonajeDto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
@RestController
@RequestMapping ("/characters")
@CrossOrigin
public class PersonajeController {
    
    @Autowired
    PersonajeServicio personajeServicio;
  
    @Autowired
    PersonajeRepositorio personajeRepositorio;

    @Autowired
    PeliculaServicio peliculaServicio;
    
    @Autowired
    PeliculaRepositorio peliculaRepositorio;
    
    @GetMapping ("/characters")
    public ResponseEntity<Optional<String>> listarTodos (){
        List<String> lista = personajeServicio.listarTodas();        
        return new ResponseEntity (lista, HttpStatus.OK);
    }
    
    @GetMapping ("/detail/{id}")
    public ResponseEntity <Personaje> detallePorId (@PathVariable ("id") int id) {
        if (!personajeServicio.existsById(id))
        return new ResponseEntity (new Mensaje ("Ese id de personaje es inexistente"), HttpStatus.NOT_FOUND);
        Personaje personaje = personajeServicio.getOne(id).get();      
        return new ResponseEntity (personaje, HttpStatus.OK);
        
    } 
    
     
    @GetMapping ("/characters?name={nombre}")
    public ResponseEntity <Personaje> busquedaPorNombre (@PathVariable ("nombre") String nombre) {
        if (!personajeServicio.existsByNombre(nombre))
        return new ResponseEntity (new Mensaje ("El nombre del personaje es inexistente"), HttpStatus.BAD_REQUEST);
        Personaje personaje = personajeServicio.getByNombre(nombre).get();
        return new ResponseEntity (personaje, HttpStatus.OK);
    }
 
    @GetMapping ("/characters/characters?age={edad}")
    public ResponseEntity <List<Personaje>> buscarPorEdad (@PathVariable ("edad") int edad) {    
        List<Personaje> lista = personajeServicio.buscarPorEdad(edad);
        return new ResponseEntity (lista, HttpStatus.OK);
    }
//    
//    @GetMapping ("/characters/characters?orderASC")
//    public ResponseEntity <List<Personaje>> buscarPorPelicula (int id)  {    
//        List<Personaje> lista = personajeServicio.listarPorPelicula(id);
//        return new ResponseEntity (lista, HttpStatus.OK);
//    }    
   
    @PostMapping ("/create")    
    public ResponseEntity<?> create (@RequestBody PersonajeDto personajeDto) {
        if (personajeDto.getNombre()== null || personajeDto.getNombre().isEmpty())
            return new ResponseEntity (new Mensaje("El nombre no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        if (personajeDto.getEdad() <= 0)
            return new ResponseEntity (new Mensaje("La edad no puede ser 0 o menor a 0"), HttpStatus.BAD_REQUEST);
        if (personajeDto.getHistoria()== null || personajeDto.getHistoria().isEmpty())
            return new ResponseEntity (new Mensaje("La historia no puede ser nula o estar vacia"),HttpStatus.BAD_REQUEST);
        if (personajeDto.getPeso() <= 0)
            return new ResponseEntity (new Mensaje("El peso no puede ser 0 o menor a 0"),HttpStatus.BAD_REQUEST);
        if (personajeDto.getIdPelicula() == 0)
            return new ResponseEntity (new Mensaje("La pelicula no puede ser nula"),HttpStatus.BAD_REQUEST);
        if (personajeServicio.existsByNombre(personajeDto.getNombre()))
            return new ResponseEntity (new Mensaje ("Ese personaje ya existe"), HttpStatus.BAD_REQUEST);
        Personaje personaje = new Personaje ();
        personaje.setNombre(personajeDto.getNombre());
        personaje.setEdad(personajeDto.getEdad());
        personaje.setFoto(personaje.getFoto());
        personaje.setHistoria(personajeDto.getHistoria());
        personaje.setPelicula(peliculaServicio.vincularPersonajesNuevoPers(personaje, personajeDto.getIdPelicula()));
        personaje.setPeso(personajeDto.getPeso()); 
        personajeServicio.crearPersonaje(personaje);
        return new ResponseEntity ( new Mensaje ("Personaje creado exitosamente."), HttpStatus.OK);
    } 
    @PutMapping ("/update/{id}")
    public ResponseEntity<?> upadte (@PathVariable ("id") int id, @RequestBody PersonajeDto personajeDto) {
        if (!personajeServicio.existsById(id))
            return new ResponseEntity (new Mensaje ("Ese id de personaje es inexistente"), HttpStatus.NOT_FOUND);
        if (personajeServicio.existsByNombre(personajeDto.getNombre()) && personajeServicio.getByNombre(personajeDto.getNombre()).get().getId() != id)
            return new ResponseEntity (new Mensaje ("Esa pelicula ya existe"), HttpStatus.BAD_REQUEST);
        if (personajeDto.getNombre()== null || personajeDto.getNombre().isEmpty())
            return new ResponseEntity (new Mensaje("El nombre no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        if (personajeDto.getEdad() <= 0)
            return new ResponseEntity (new Mensaje("La edad no puede ser 0 o menor a 0"), HttpStatus.BAD_REQUEST);
        if (personajeDto.getHistoria()== null || personajeDto.getHistoria().isEmpty())
            return new ResponseEntity (new Mensaje("La historia no puede ser nula o estar vacia"),HttpStatus.BAD_REQUEST);
        if (personajeDto.getPeso() <= 0)
            return new ResponseEntity (new Mensaje("El peso no puede ser 0 o menor a 0"),HttpStatus.BAD_REQUEST);
        if (personajeDto.getIdPelicula()== 0)
            return new ResponseEntity (new Mensaje("La id de pelicula no puede ser nula o 0"),HttpStatus.BAD_REQUEST);
        
        Personaje personaje = personajeServicio.getOne(id).get();         
        personaje.setNombre(personajeDto.getNombre());
        personaje.setEdad(personajeDto.getEdad());
        personaje.setFoto(personaje.getFoto());
        personaje.setHistoria(personajeDto.getHistoria());
        personaje.setPelicula(personajeDto.getPelicula());
        personaje.setPeso(personajeDto.getPeso());
        personajeRepositorio.save(personaje);
        return new ResponseEntity ( new Mensaje ("Personaje Modificado"), HttpStatus.OK);
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable ("id") int id) {
        if (!personajeServicio.existsById(id))
            return new ResponseEntity (new Mensaje ("Ese id de pelicula es inexistente"), HttpStatus.NOT_FOUND);
        personajeServicio.eliminarPersonajePorId(id);
        return new ResponseEntity ( new Mensaje ("Personaje Eliminado"), HttpStatus.OK);
    }
}
