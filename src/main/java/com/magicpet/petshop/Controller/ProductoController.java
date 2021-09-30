package com.magicpet.petshop.Controller;

import com.magicpet.petshop.entidades.Producto;
import com.magicpet.petshop.repositorios.ProductoRepositorio;
import com.magicpet.petshop.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Enzo sosa
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @GetMapping("")
    private String listaProducto(Model model) {
        model.addAttribute("productos", productoServicio.findAll());
        return "catalogo";
    }

    @GetMapping("/{id_producto}")
    public String vistaIdProducto(@PathVariable String id_producto, Model model) {
        model.addAttribute("producto", productoRepositorio.getById(id_producto));
        return "producto";
    }

}
