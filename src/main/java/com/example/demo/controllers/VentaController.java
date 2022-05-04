package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.demo.Util.EndpointVentaFiltrada;
import com.example.demo.Util.EntityReporte;
import com.example.demo.Util.EntityVenta;
import com.example.demo.Util.RespuestaInforme;
import com.example.demo.Util.RespuestaReporte;
import com.example.demo.Util.RespuestaReporteFiltros;
import com.example.demo.Util.RespuestaVenta;
import com.example.demo.models.Producto;
import com.example.demo.models.Venta;
import com.example.demo.services.ProductoService;
import com.example.demo.services.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/venta")
public class VentaController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ProductoService productoService;

    @Autowired
    VentaService ventaService;
    RespuestaVenta respuesta;

    /**
     * Servicio que crea una venta de un producto
     *
     * @endpoint //http://localhost:8090/v1/venta/crear
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @PostMapping("/crear")
    public RespuestaVenta save(@RequestBody Venta venta) {
        respuesta = new RespuestaVenta();
        try {
            Optional<Producto> producto = productoService.getProductoById(venta.getId_producto());
            Producto pro = producto.get();
            Integer catidadDisponible = pro.getCantidad_disponible();
            Integer catidadVenta = venta.getCantidad();
            Integer valorProducto = pro.getValorUnitario();

            if (catidadVenta <= catidadDisponible) {
                Integer totalCompra = catidadVenta * valorProducto;
                venta.setTotal(totalCompra);
                if (this.ventaService.save(venta)) {
                    pro.setCantidad_disponible(catidadDisponible - venta.getCantidad());
                    productoService.editar(pro);
                    respuesta.setEstado(true);
                    respuesta.setMensaje("Venta Registrada correctamente");
                } else {
                    respuesta.setEstado(false);
                    respuesta.setMensaje("Error al registrar Venta");
                }
            } else {
                respuesta.setEstado(false);
                respuesta.setMensaje(
                        "Error , el stock es de " + catidadDisponible + " unidades inferior a lo solicitado");
            }
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al registrar Venta");
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Servicio que consulta todos las ventas realizadas con sus respectivos
     * productos
     * productos
     *
     * @endpoint //http://localhost:8090/v1/venta
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @GetMapping("/")
    public RespuestaVenta getAllVentas() {
        respuesta = new RespuestaVenta();
        try {
            ArrayList<EntityVenta> ventas = ventaService.getVentas();
            respuesta.setEstado(true);
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(ventas);
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al consultar ventas");
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Servicio que genera un reposte por fechas, nombre producto y precios
     *
     * @endpoint //http://localhost:8090/v1/venta/filtrar
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @PostMapping("/filtrar")
    public RespuestaInforme getVentasFiltradas(@RequestBody EndpointVentaFiltrada filtros) {
        List<RespuestaReporteFiltros> listaRespuesta = new ArrayList<>();
        RespuestaInforme resp = new RespuestaInforme();

        try {
            String query = "select  p.id as idProducto,p.nombre as NombreProducto,p.fecha_creacion as fechaRegistroProducto, p.valor_unitario as ValorUnitario,v.id as idVenta,v.cantidad as cantidadVendida,v.fecha_venta as fechaVenta,v.total as totalVenta from venta v inner join producto p on v.id_producto=p.id where ( (v.fecha_venta>to_date('"
                    + filtros.getFechaInicial() + "', 'YYYY-MM-DD') and v.fecha_venta<to_date('"
                    + filtros.getFechaFinal() + "', 'YYYY-MM-DD')) or (p.nombre LIKE '%" + filtros.getNombreProducto()
                    + "%') OR (p.valor_unitario>=" + filtros.getPrecioInicial() + " AND p.valor_unitario<="
                    + filtros.getPrecioFinal() + "))";
            List<Object[]> lista = em.createNativeQuery(query).getResultList();
            for (Object[] entity : lista) {
                RespuestaReporteFiltros respuesta = new RespuestaReporteFiltros();
                respuesta.setIdProducto(entity[0].toString());
                respuesta.setNombreProducto(entity[1].toString());
                respuesta.setFechaRegistroProducto(entity[2].toString());
                respuesta.setValorUnitario(entity[3].toString());
                respuesta.setIdVenta(entity[4].toString());
                respuesta.setCantidadVendida(entity[5].toString());
                respuesta.setFechaVenta(entity[6].toString());
                respuesta.setTotalVenta(entity[7].toString());
                listaRespuesta.add(respuesta);
            }
            resp.setEstado(true);
            resp.setMensaje("Consulta exitosa");
            resp.setData(listaRespuesta);
        } catch (Exception e) {
            resp.setEstado(false);
            resp.setMensaje("Error al registrar Venta");
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * Servicio que consulta la cantidad de item vendidos por producto y suma total
     *
     * @endpoint //http://localhost:8090/v1/venta/reporte
     * @autor ELKIN BARRERO
     * @return Respuesta
     */
    @GetMapping("/reporte")
    public RespuestaReporte reporteVentas() {
        RespuestaReporte resp = new RespuestaReporte();
        List<EntityReporte> listInforme = new ArrayList<>();

        try {
            String query = "select v.id_producto,p.nombre, count(*) as catidad_vendida, sum(v.total) as total_vendido from venta v inner join producto p on v.id_producto=p.id group by v.id_producto,p.nombre";
            List<Object[]> lista = em.createNativeQuery(query).getResultList();

            for (Object[] objecto : lista) {
                EntityReporte entity = new EntityReporte();
                entity.setId_producto(Integer.parseInt(objecto[0].toString()));
                entity.setNombre_producto(objecto[1].toString());
                entity.setCatidad_vendida(Integer.parseInt(objecto[2].toString()));
                entity.setTotal_vendido(Integer.parseInt(objecto[3].toString()));
                listInforme.add(entity);
            }
            resp.setEstado(true);
            resp.setMensaje("Consulta exitosa");
            resp.setData(listInforme);
        } catch (Exception e) {
            resp.setEstado(false);
            resp.setMensaje("Error al registrar Venta");
            e.printStackTrace();
        }
        return resp;
    }

}