package com.magicpet.petshop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Enzo sosa
 */
@Controller
@RequestMapping("/producto")
public class ControllerProducto {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/list")
    private String listaProducto(Model model) {
        model.addAttribute("productos", productoServicio.ListAll());
        return "producto-list";
    }

    @GetMapping("/form")
    private String crearProducto(Model model,@RequestParam(required = true) String id) {

        return "producto-form";
    }

    @PostMapping("/save")
    public String guardarProducto(Model model,RedirectAttributes redirectAttributes,ModelAttribute modelAttribute,Producto producto){
    
        return "redirect:/producto/list";
    }
    
    
    @GetMapping("/delete")
    public String eliminarProducto(@RequestParam(required = true) String id){
    
        return "redirect:/producto/list";
    }
    

}
