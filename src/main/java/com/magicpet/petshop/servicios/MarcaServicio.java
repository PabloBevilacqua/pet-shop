package com.magicpet.petshop.servicios;

import com.magicpet.petshop.entidades.Marca;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.repositorios.MarcaRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarcaServicio {

    @Autowired
    private MarcaRepositorio marcaRepositorio;

    @Transactional
    public void registrarMarca(Marca marca) throws ErrorServicio {
        validar(marca.getNombre());
        marcaRepositorio.save(marca);
    }
<<<<<<< HEAD
    @Transactional
    public void modificarMarca(Marca marca) throws ErrorServicio{
=======

    @Transactional
    public void modificarMarca(Marca marca) throws ErrorServicio {
>>>>>>> 244e7359c7ab95104903d10a20d82d6beb7d13fe
        Optional<Marca> respuesta = marcaRepositorio.findById(marca.getId());
        if (respuesta.isPresent()) {
            validar(marca.getNombre());
            marcaRepositorio.save(marca);
        } else {
            throw new ErrorServicio("La marca no existe");
        }
    }
<<<<<<< HEAD
      @Transactional
=======

    @Transactional
>>>>>>> 244e7359c7ab95104903d10a20d82d6beb7d13fe
    public void eliminarMarca(String id) throws ErrorServicio {

        Optional<Marca> respuesta = marcaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            marcaRepositorio.delete(respuesta.get());
        } else {
            throw new ErrorServicio("No se encontró la marca");
        }

    }

    @Transactional
    public List<Marca> listAll() {
        return marcaRepositorio.findAll();
    }

    public void validar(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
    }
}
