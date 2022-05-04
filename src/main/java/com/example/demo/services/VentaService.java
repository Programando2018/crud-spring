package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;
import com.example.demo.Util.EntityVenta;
import com.example.demo.models.Producto;
import com.example.demo.models.Venta;
import com.example.demo.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    ProductoService productoService;

    public boolean save(Venta producto) {
        try {
            ventaRepository.save(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<EntityVenta> getVentas() {
        ArrayList<EntityVenta> lista = new ArrayList<>();
        for (Venta venta : ventaRepository.findAll()) {
            EntityVenta respVenta = new EntityVenta();
            respVenta.setId(venta.getId());
            respVenta.setCantidadVendida(venta.getCantidad());
            respVenta.setFechaVenta(venta.getFecha_venta());
            respVenta.setUsuarioVenta(venta.getUsuario_venta());
            respVenta.setTotalVenta(venta.getTotal());
            Optional<Producto> producto = productoService.getProductoById(venta.getId_producto());
            Producto pro = producto.get();
            respVenta.setProducto(pro);
            lista.add(respVenta);
        }

        return lista;
    }

}
