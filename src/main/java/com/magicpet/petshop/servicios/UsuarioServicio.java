package com.magicpet.petshop.servicios;

import com.magicpet.petshop.entidades.Usuario;
import com.magicpet.petshop.enums.Role;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Usuario save(String username, String password, String password2, String mail, String ciudad, String nombre, String apellido, String dni, Date fechaDeNacimiento) throws ErrorServicio {

        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El dni de usuario no puede estar vacio");
        }
        if (ciudad == null || ciudad.isEmpty()) {
            throw new ErrorServicio("La ciudad no puede estar Vacia");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre  no puede estar Vacio");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido  no puede estar Vacio");
        }
        if (dni == null || dni.isEmpty()) {
            throw new ErrorServicio("El dni  no puede estar Vacio");
        }
        if (fechaDeNacimiento == null) {
            throw new ErrorServicio("La fecha de nacimiento no puede estar vacia");
        }
       
        if (username.isEmpty() || username == null) {
            throw new ErrorServicio("El nombre de usuario no puede estar vacio");
        }
        if (password == null || password2 == null || password.isEmpty() || password2.isEmpty()) {
            throw new ErrorServicio("La contraseña no puede estar vacia");
        }
        if (!password.equals(password2)) {
            throw new ErrorServicio("Las contaseñas deben ser iguales");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setMail(mail);
        usuario.setCiudad(ciudad);
        usuario.setUsername(username);
        usuario.setPassword(encoder.encode(password));
        usuario.setRol(Role.USER);
        return usuarioRepositorio.save(usuario);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepositorio.findByUsername(username);
    }

    public List<Usuario> listAll() {

        return usuarioRepositorio.findAll();
    }

    public List<Usuario> listAllByQ(String q) {

        return usuarioRepositorio.findAllByQ("%" + q + "%");
     }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Usuario usuario = usuarioRepositorio.findByUsername(username);
            User user;

            List<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            return new User(username, usuario.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }
}
