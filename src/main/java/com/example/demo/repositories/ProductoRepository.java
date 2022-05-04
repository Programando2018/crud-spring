package com.example.demo.repositories;

import java.util.ArrayList;
import com.example.demo.models.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    public abstract ArrayList<Producto> findById(Integer id);
}