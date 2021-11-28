/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.Controladores;

import alkemy.appDisney.Entidades.Pelicula;
import alkemy.appDisney.Repositorios.PeliculaRepositorio;
import alkemy.appDisney.Repositorios.PersonajeRepositorio;
import alkemy.appDisney.Servicios.GeneroServicio;
import alkemy.appDisney.Servicios.PeliculaServicio;
import alkemy.appDisney.dto.Mensaje;
import alkemy.appDisney.dto.PeliculaDto;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
@RestController
@RequestMapping ("/movies")
@CrossOrigin
public class PeliculaController {
    @Autowired
    PeliculaServicio peliculaServicio;
    
    @Autowired
    PersonajeRepositorio personajeRepositorio; 
    
    @Autowired 
    PeliculaRepositorio peliculaRepositorio;
    
    @Autowired
    GeneroServicio generoServicio;
    
    @GetMapping ("/movies")
    @ResponseBody
    public ResponseEntity<Optional<Pelicula>> listarTodas (){
        List <Pelicula> lista = peliculaRepositorio.findAll();              
        return new ResponseEntity (lista, HttpStatus.OK);
        
    }
    
    @GetMapping ("/detail/{id}")
    public ResponseEntity <Pelicula> detallePorId (@PathVariable ("id") int id) {
        if (!peliculaServicio.existsById(id))
        return new ResponseEntity (new Mensaje ("Ese id de pelicula es inexistente"), HttpStatus.NOT_FOUND);
        Pelicula pelicula = peliculaServicio.getOne(id).get();
        return new ResponseEntity (pelicula, HttpStatus.OK);
    } 
    
    @GetMapping ("/search")
    public ResponseEntity <Pelicula> busquedaPorNombre (@RequestParam String titulo) {
        if (!peliculaServicio.existsByTitulo(titulo))
        return new ResponseEntity (new Mensaje ("El nombre de la pelicula es inexistente"), HttpStatus.BAD_REQUEST);
        Pelicula pelicula = peliculaServicio.buscarPorNombre(titulo);
        return new ResponseEntity (pelicula, HttpStatus.OK);
    }
    
    @GetMapping ("/movies/movies?genre={idGenero}")
    public ResponseEntity <List<Pelicula>> busquedaPorGenero (@PathVariable ("idGenero") int idGenero) {    
        List<Pelicula> buscarGenero = peliculaServicio.listarporGenero(idGenero);
        return new ResponseEntity (buscarGenero, HttpStatus.OK);
    }
//     @GetMapping ("/movies/movies?orderASC")
//    public ResponseEntity <List<Pelicula>> ordenarCreacionASC ()  {    
//        List<Pelicula> ordenASC = peliculaServicio.listarCreacionASC();
//        return new ResponseEntity (ordenASC, HttpStatus.OK);
//    }
//        
//    @GetMapping ("/movies/movies?orderDESC")
//    public ResponseEntity <List<Pelicula>> ordenarCreacionDESC ()  {    
//        List<Pelicula> ordenDESC = peliculaServicio.listarCreacionDESC();
//        return new ResponseEntity (ordenDESC, HttpStatus.OK);
//    }
    @PostMapping ("/create")    
    public ResponseEntity<?> create (@RequestBody PeliculaDto peliculaDto) {
        if (peliculaDto.getTitulo() == null || peliculaDto.getTitulo().isEmpty())
            return new ResponseEntity (new Mensaje("El titulo no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
//        if (peliculaDto.getGenero()== null)
//            return new ResponseEntity (new Mensaje("El Genero no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        if (peliculaDto.getCalificacion()< 1 || peliculaDto.getCalificacion()>5)
            return new ResponseEntity (new Mensaje("La calificacion debe ser entre 1 y 5"),HttpStatus.BAD_REQUEST);
        if (peliculaDto.getDia() < 1 || peliculaDto.getDia()>31)
            return new ResponseEntity (new Mensaje("El dia es incorrecto"),HttpStatus.BAD_REQUEST);
        if (peliculaDto.getMes()< 1 || peliculaDto.getMes()>12)
            return new ResponseEntity (new Mensaje("El mes es incorrecto"),HttpStatus.BAD_REQUEST);
        if (peliculaServicio.existsByTitulo(peliculaDto.getTitulo()))
            return new ResponseEntity (new Mensaje ("Esa pelicula ya existe"), HttpStatus.BAD_REQUEST);
        Pelicula pelicula = new Pelicula ();
        pelicula.setTitulo(peliculaDto.getTitulo());
        pelicula.setCalificacion(peliculaDto.getCalificacion());
        pelicula.setDia(peliculaDto.getDia());
        pelicula.setMes(peliculaDto.getMes());
        pelicula.setAnio(peliculaDto.getAnio());
        pelicula.setCreacion(LocalDateTime.of(peliculaDto.getAnio(), peliculaDto.getMes() ,peliculaDto.getDia(), 0, 0));
        pelicula.setFoto(peliculaDto.getFoto());
        peliculaServicio.save(pelicula);
        pelicula.setGenero(peliculaServicio.vincularGeneros(pelicula.getId(), peliculaDto.getIdGenero()));
        generoServicio.setearPeliculas(pelicula.getId(),pelicula.getGenero().getId());
        peliculaServicio.save(pelicula);
       
       return new ResponseEntity ( new Mensaje ("Pelicula creada exitosamente."), HttpStatus.OK);
    } 
    @PutMapping ("/update/{id}")
    public ResponseEntity<?> upadte (@PathVariable ("id") int id, @RequestBody PeliculaDto peliculaDto) {
        if (!peliculaServicio.existsById(id))
            return new ResponseEntity (new Mensaje ("Ese id de pelicula es inexistente"), HttpStatus.NOT_FOUND);
        if (peliculaServicio.existsByTitulo(peliculaDto.getTitulo()) && peliculaServicio.getByTitulo(peliculaDto.getTitulo()).get().getId() != id)
            return new ResponseEntity (new Mensaje ("Esa pelicula ya existe"), HttpStatus.BAD_REQUEST);
        if (peliculaDto.getTitulo() == null || peliculaDto.getTitulo().isEmpty())
            return new ResponseEntity (new Mensaje("El titulo no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        if (peliculaDto.getCalificacion()< 1 || peliculaDto.getCalificacion()>5)
            return new ResponseEntity (new Mensaje("La calificacion debe ser entre 1 y 5"),HttpStatus.BAD_REQUEST);
        if (peliculaDto.getDia() < 1 || peliculaDto.getDia()>31)
            return new ResponseEntity (new Mensaje("El dia es incorrecto"),HttpStatus.BAD_REQUEST);
        if (peliculaDto.getMes()< 1 || peliculaDto.getMes()>12)
            return new ResponseEntity (new Mensaje("El mes es incorrecto"),HttpStatus.BAD_REQUEST);
        if (peliculaDto.getIdGenero() == 0)
            return new ResponseEntity (new Mensaje("Debe ingresar un id de Genero y este no puede ser el numero 0"), HttpStatus.BAD_REQUEST);
        
        Pelicula pelicula = peliculaServicio.getOne(id).get();
        pelicula.setTitulo(peliculaDto.getTitulo());
        pelicula.setCalificacion(peliculaDto.getCalificacion());
        pelicula.setDia(peliculaDto.getDia());
        pelicula.setMes(peliculaDto.getMes());
        pelicula.setAnio(peliculaDto.getAnio());
        pelicula.setCreacion(LocalDateTime.of(peliculaDto.getAnio(), peliculaDto.getMes() ,peliculaDto.getDia(), 0, 0));
        pelicula.setFoto(peliculaDto.getFoto());
        pelicula.setPersonajes(peliculaServicio.vincularExistentes(pelicula.getId()));
        pelicula.setGenero(peliculaServicio.vincularGeneros(id, peliculaDto.getIdGenero()));
        generoServicio.setearPeliculas(id, peliculaDto.getIdGenero());
        peliculaServicio.save(pelicula);
        return new ResponseEntity ( new Mensaje ("Pelicula Modificada"), HttpStatus.OK);
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable ("id") int id) {
        if (!peliculaServicio.existsById(id))
            return new ResponseEntity (new Mensaje ("Ese id de pelicula es inexistente"), HttpStatus.NOT_FOUND);
        peliculaServicio.eliminarPeliculaPorId(id);
        return new ResponseEntity ( new Mensaje ("Pelicula Eliminada"), HttpStatus.OK);
    }
}
