package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;
import com.example.demo.models.Producto;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public ArrayList<Producto> getById(Long id) {
        ArrayList<Producto> lista = new ArrayList<>();
        Optional<Producto> usuario = productoRepository.findById(id);
        lista.add(usuario.get());
        return lista;
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public ArrayList<Producto> getProductos() {
        return (ArrayList<Producto>) productoRepository.findAll();
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto editar(Producto producto) {
        return productoRepository.save(producto);
    }

    public boolean deleteUsuarioById(Long id) {
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public boolean deleteUsuario(Long id) {
        try {
            this.deleteUsuarioById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
