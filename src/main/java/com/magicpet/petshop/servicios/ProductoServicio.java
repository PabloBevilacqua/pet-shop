
package com.magicpet.petshop.servicios;

import com.magicpet.petshop.entidades.Producto;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.repositorios.ProductoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServicio {
    
    @Autowired
    private ProductoRepositorio productoRepositorio;
    
    @Transactional
    public void registrarProducto(Producto producto) throws ErrorServicio{
        validar(producto.getNombre(), producto.getCodigo(), producto.getDescripcion(), producto.getMarca().getNombre(), producto.getCategoria().getNombre());
        validar2(producto.getStock(), producto.getPrecioUnitario(), producto.getImagenURL());
        
        productoRepositorio.save(producto);
    }

    @Transactional
    public void eliminarProducto(String id) throws ErrorServicio {

        Optional<Producto> respuesta = productoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            productoRepositorio.delete(respuesta.get());
        }
        throw new ErrorServicio("No se encontró el producto");
    }

    @Transactional
    public List<Producto> listAll() {
        return productoRepositorio.findAll();
    }
    

    
    private void validar(String nombre, String codigo, String descripcion, String marca, String categoria) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        if (codigo == null || codigo.isEmpty()) {
            throw new ErrorServicio("El código no puede ser nulo");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new ErrorServicio("La descripción no puede ser nula");
        }
        if (marca == null || marca.isEmpty()) {
            throw new ErrorServicio("La marca no puede ser nula");
        }
        if (categoria == null || categoria.isEmpty()) {
            throw new ErrorServicio("La categorá no puede ser nula");
        }

    }

    private void validar2(Integer stock, Double precioUnitario, String imagenURL) throws ErrorServicio {

        if (stock == null || stock.toString().isEmpty()) {
            throw new ErrorServicio("El stock no puede ser nulo");
        }
        if (precioUnitario == null || precioUnitario.toString().isEmpty()) {
            throw new ErrorServicio("El precio unitario no puede ser nulo");
        }
        if (imagenURL == null || imagenURL.isEmpty()) {
            throw new ErrorServicio("La url no puede ser nula");
        }
    }

}
