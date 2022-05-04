package com.example.demo.repositories;

import java.util.ArrayList;
import com.example.demo.models.Venta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long> {
    public abstract ArrayList<Venta> findById(Integer id);
}