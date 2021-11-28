/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alkemy.appDisney.Seguridad.Entidades;

import com.sun.istack.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


/**
 * 
 * @author Federico Fernandez Lafi <ffernandezlafi at gmail.com>
 */
@Entity
public class Usuario {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   private int id;
   @NotNull
   private String nombre;
   @NotNull
   @Column (unique = true)
   private String nombreusuario;
   @NotNull
   private String email;
   @NotNull
   private String password;
   @NotNull
   @ManyToMany
   @JoinTable (name = "usuario_rol",
           joinColumns = @JoinColumn (name = "usuario_id"),
           inverseJoinColumns = @JoinColumn (name = "rol_id"))
   private Set <Rol> roles = new HashSet <Rol> ();

    public Usuario() {
    }

    public Usuario(String nombre, String nombreusuario, String email, String password) {
        this.nombre = nombre;
        this.nombreusuario = nombreusuario;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    
    
          
   
}
