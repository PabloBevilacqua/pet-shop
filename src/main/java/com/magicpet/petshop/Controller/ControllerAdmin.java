package com.magicpet.petshop.Controller;

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

    //panel=index de admin
    @GetMapping("/admin")
    public String panel() {

        return "panel";
    }

    @GetMapping("/admin/producto")
    public String panelListProducto() {

        return "producto-list";
    }

    @GetMapping("/admin/producto/save")
    public String panelSaveProducto() {

        return "producto-form";
    }
 
    @GetMapping("/admin/producto/edit")
    public String panelEditProducto() {
        
        return "producto-form";
    }
}
