package com.example.demo.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Util.RespuestProductos;
import com.example.demo.models.Producto;
import com.example.demo.services.ProductoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;
    RespuestProductos respuesta;

    /**
     * Servicio que consulta todos los productos
     * 
     * @endpoint //http://localhost:8090/producto/
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @GetMapping("/")
    public RespuestProductos getAllProductos() {
        respuesta = new RespuestProductos();
        try {
            ArrayList<Producto> productos = productoService.getProductos();
            respuesta.setEstado(true);
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(productos);
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al consultar productos");
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Servicio que consulta un producto por id
     * 
     * @endpoint //http://localhost:8090/producto/1
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @GetMapping(path = "/{id}")
    public RespuestProductos getProductoById(@PathVariable("id") Long id) {
        respuesta = new RespuestProductos();
        try {
            ArrayList<Producto> producto = this.productoService.getById(id);
            respuesta.setEstado(true);
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(producto);
        } catch (Exception e) {
            respuesta.setEstado(true);
            respuesta.setMensaje("Consulta exitosa");
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Servicio que crea un producto
     * 
     * @endpoint //http://localhost:8090/producto/save
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @PostMapping("/save")
    public RespuestProductos save(@RequestBody Producto producto) {
        respuesta = new RespuestProductos();
        try {
            this.productoService.save(producto);
            respuesta.setEstado(true);
            respuesta.setMensaje("Producto Registrado correctamente");
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al registrar Producto");
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Servicio que edita un producto
     * 
     * @endpoint //http://localhost:8090/producto/editar
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @PutMapping("/editar")
    public RespuestProductos editar(@RequestBody Producto producto) {
        respuesta = new RespuestProductos();
        try {
            this.productoService.save(producto);
            respuesta.setEstado(true);
            respuesta.setMensaje("Producto editado correctamente");
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al editar Producto");
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Servicio que elimina un producto por id
     * 
     * @endpoint //http://localhost:8090/producto/editar
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @DeleteMapping(path = "/{id}")
    public RespuestProductos deleteById(@PathVariable("id") Long id) {
        respuesta = new RespuestProductos();
        boolean ok = this.productoService.deleteUsuario(id);
        try {
            if (ok) {
                respuesta.setEstado(true);
                respuesta.setMensaje("Se eliminó el producto");
            } else {
                respuesta.setEstado(false);
                respuesta.setMensaje("Error al eliminar producto");
            }
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al eliminar producto");
            e.printStackTrace();
        }
        return respuesta;
    }

}