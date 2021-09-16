package com.magicpet.petshop.Controller;

import com.magicpet.petshop.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Enzo sosa
 */
@Controller
@RequestMapping("/admin")
public class ControllerAdmin {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/admin")
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

    @GetMapping("/admin/producto/edit")
    public String adminEditProducto() {

        return "producto-form";
    }
}
