package com.example.demo.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Util.Respuesta;
import com.example.demo.models.Usuario;
import com.example.demo.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    Respuesta respuesta;

    @GetMapping()
    public String inicio() {
        return "Hola esta funcionando";
    }

    /**
     * Servicio que consulta todos los usuarios
     * 
     * @endpoint //http://localhost:8090/usuario/
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @GetMapping("/")
    public Respuesta getAllUsuarios() {
        respuesta = new Respuesta();
        try {
            ArrayList<Usuario> usuarios = usuarioService.obtenerUsuarios();
            respuesta.setEstado(true);
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(usuarios);
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al consultar usuarios");
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Servicio que consulta un usuario por id
     * 
     * @endpoint //http://localhost:8090/usuario/1
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @GetMapping(path = "/{id}")
    public Respuesta getUsusarioById(@PathVariable("id") Long id) {
        respuesta = new Respuesta();
        try {
            ArrayList<Usuario> usuario = this.usuarioService.getById(id);
            respuesta.setEstado(true);
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(usuario);
        } catch (Exception e) {
            respuesta.setEstado(true);
            respuesta.setMensaje("Consulta exitosa");
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Servicio que crea un usuario
     * 
     * @endpoint //http://localhost:8090/usuario/save
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @PostMapping("/save")
    public Respuesta save(@RequestBody Usuario usuario) {
        respuesta = new Respuesta();
        try {
            this.usuarioService.save(usuario);
            respuesta.setEstado(true);
            respuesta.setMensaje("Usuario Registrado correctamente");
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al registrar usuario");
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Servicio que edita un usuario
     * 
     * @endpoint //http://localhost:8090/usuario/editar
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @PutMapping("/editar")
    public Respuesta editar(@RequestBody Usuario usuario) {
        respuesta = new Respuesta();
        try {
            this.usuarioService.save(usuario);
            respuesta.setEstado(true);
            respuesta.setMensaje("Usuario editado correctamente");
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al editar usuario");
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Servicio que elimina un usuario por id
     * 
     * @endpoint //http://localhost:8090/usuario/1
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @DeleteMapping(path = "/{id}")
    public Respuesta deleteById(@PathVariable("id") Long id) {
        respuesta = new Respuesta();
        boolean ok = this.usuarioService.deleteUsuario(id);
        try {
            if (ok) {
                respuesta.setEstado(true);
                respuesta.setMensaje("Se eliminó el usuario");
            } else {
                respuesta.setEstado(false);
                respuesta.setMensaje("Error al eliminar usuario");
            }
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al eliminar usuario");
            e.printStackTrace();
        }
        return respuesta;
    }

}