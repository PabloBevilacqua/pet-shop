package com.magicpet.petshop.Controller;

import com.magicpet.petshop.repositorios.ProductoRepositorio;
import com.magicpet.petshop.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ModelAttribute;
=======
import org.springframework.web.bind.annotation.PathVariable;
>>>>>>> 244e7359c7ab95104903d10a20d82d6beb7d13fe
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

<<<<<<< HEAD
    @GetMapping("/form")
    private String registrarProducto(Model model, String id, RedirectAttributes redirectAttributes, @ModelAttribute Producto producto) throws ErrorServicio {
        try {
            Optional<Producto> respuesta = productoRepositorio.findById(id);
            if (respuesta.isPresent()){
                productoServicio.registrarProducto(producto);
                return "templates.admin/producto-form";//cambiar si esta mal(/ o .)
            }
        } catch (ErrorServicio e) {
              model.addAttribute("error", e.getMessage());
              redirectAttributes.addFlashAttribute("error",e.getMessage());
        }

        return "templates.admin/producto/list";
    }
    @GetMapping("/delete")
    public String eliminarProducto(@RequestParam(required = true) String id) throws ErrorServicio {
        productoServicio.eliminarProducto(id);
        return "redirect:/producto/list";
=======
    @GetMapping("/{id_producto}")
    public String vistaIdProducto(@PathVariable String id_producto, Model model) {
        model.addAttribute("producto", productoRepositorio.getById(id_producto));
        return "producto";
>>>>>>> 244e7359c7ab95104903d10a20d82d6beb7d13fe
    }

}
