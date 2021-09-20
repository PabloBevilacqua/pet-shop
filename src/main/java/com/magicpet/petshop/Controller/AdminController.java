package com.magicpet.petshop.Controller;

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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/")
    public String index() {

        return "templates.admin/index";
    }

    @GetMapping("/admin/producto")
    public String adminListProducto() {

        return "producto-list";
    }

    @GetMapping("/admin/producto/save")
    public String adminSaveProducto() {

        return "producto-form";
    }

    @GetMapping("/productos/edit/{id_producto}")
    public String vistaEditar(@PathVariable String id_producto, Model model) {
        return null;
        // Buscar producto por id
        // Agregar el producto al Modle
        // Devolver el template de formulario agregar (con el objeto)
        
    }

    @GetMapping("/admin/producto/edit")
    public String adminEditProducto() {

        return "producto-form";
    }
}
