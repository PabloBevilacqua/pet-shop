package com.magicpet.petshop.Controller;

import com.magicpet.petshop.entidades.Categoria;
import com.magicpet.petshop.entidades.Marca;
import com.magicpet.petshop.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.magicpet.petshop.repositorios.ProductoRepositorio;
import com.magicpet.petshop.entidades.Producto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Enzo sosa
 */
@Controller
@PreAuthorize("hasRole('ADMIN')") // Si son varios hasAnyRole('ROLE1','ROLE2')
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @GetMapping("")
    public String index() {

        return "admin/index";
    }

    @GetMapping("/productos")
    public String adminListProducto() {

        return "admin/producto-list";
    }

    @GetMapping("/productos/add")
    public String adminFormProducto() {

        return "admin/producto-form";
    }

    @PostMapping("/productos/add")
    public String adminSaveProducto(@PathVariable String id_producto, Model model, RedirectAttributes redirectAttributes) {

        try {
            Producto producto = productoRepositorio.getById(id_producto);
            if (producto == null) {
                productoServicio.registrarProducto(producto);
                return "redirect:";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "admin/producto-form";
    }

    @GetMapping("/productos/edit/{id_producto}")
    public String adminEditProducto(@PathVariable String id_producto, Model model, RedirectAttributes redirectAttributes) {
        // Manejar si no encuentra producto enviar error
        try {
            Producto producto = productoRepositorio.getById(id_producto);
            if (producto == null) {
                redirectAttributes.addFlashAttribute("error", "El producto solicitado no existe");

                return "redirect:/admin/productos";
            }
            model.addAttribute("producto", producto);

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "admin/producto-form";
    }

    @PostMapping("/productos/edit/{id_producto}")
    public String adminEditProducto(@PathVariable String id_producto, Model model, RedirectAttributes redirectAttributes,
            @RequestParam String nombre,
            @RequestParam String codigo,
            @RequestParam String descripcion,
            @RequestParam Marca marca,
            @RequestParam Categoria categoria,
            @RequestParam Integer stock,
            @RequestParam Double precioUnitario,
            @RequestParam String imagenURL) {//abre metodo
        //@ModelAttribute Producto producto

        try {
            Producto producto = productoRepositorio.getById(id_producto);
            if (producto != null) {
                producto.setNombre(nombre);
                producto.setCodigo(codigo);
                producto.setDescripcion(descripcion);
                producto.setMarca(marca);
                producto.setCategoria(categoria);
                producto.setStock(stock);
                producto.setPrecioUnitario(precioUnitario);
                producto.setImagenURL(imagenURL);
                productoServicio.modificarProducto(producto);
                return "redirect:/admin/productos";

            }
        } catch (Exception e) {

//                model.addAttribute("nombre",nombre );
//                model.addAttribute("codigo",codigo );
//                model.addAttribute("descripcion",descripcion );
//                model.addAttribute("marca", marca );
//                model.addAttribute("categoria",categoria );
//                model.addAttribute("stock",stock );
//                model.addAttribute("precioUnitario", precioUnitario );
//                model.addAttribute("stock",stock );
//                model.addAttribute("imagenURL",imagenURL);
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/productos";
        
    }

    @PostMapping("/productos/delete/{id_producto}")
    public String adminDeleteProducto(@PathVariable String id_producto, Model model, RedirectAttributes redirectAttributes) {
        try {
            Producto respuesta = productoRepositorio.getById(id_producto);
            if (respuesta != null) {
                productoServicio.eliminarProducto(id_producto);
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/productos";//deberia ir a la lista despues de eliminar
    }

}
