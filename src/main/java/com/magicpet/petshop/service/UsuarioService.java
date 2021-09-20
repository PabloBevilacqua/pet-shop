package com.magicpet.petshop.service;

import com.magicpet.petshop.entidades.Usuario;
import com.magicpet.petshop.enums.Role;
import com.magicpet.petshop.excepcions.WebException;
import java.util.ArrayList;
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
import repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario save(String username, String password, String password2, String mail, String ciudad, String nombre, String apellido, String dni, Integer edad) throws WebException {

        Usuario usuario = new Usuario();

        if (mail == null || mail.isEmpty()) {
            throw new WebException("El dni de usuario no puede estar vacio");
        }
        if (ciudad == null || ciudad.isEmpty()) {
            throw new WebException("La ciudad no puede estar Vacia");
        }
         if (nombre == null || nombre.isEmpty()) {
            throw new WebException("El nombre  no puede estar Vacio");
        }
         if (apellido == null || apellido.isEmpty()) {
            throw new WebException("El apellido  no puede estar Vacio");
        }
          if (dni == null || dni.isEmpty()) {
            throw new WebException("El dni  no puede estar Vacio");
        }
          if (edad == null || edad <=8) {
            throw new WebException("La Edad  no puede estar Vacia, o ser menor a 8");
        }
        if (username.isEmpty() || username == null) {
            throw new WebException("El nombre de usuario no puede estar vacio");
        }
        if (password == null || password2 == null || password.isEmpty() || password2.isEmpty()) {
            throw new WebException("La contraseña no puede estar vacia");
        }
        if (!password.equals(password2)) {
            throw new WebException("Las contaseñas deben ser iguales");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setEdad(edad);
        usuario.setMail(mail);
        usuario.setCiudad(ciudad);
        usuario.setUsername(username);
        usuario.setPassword(encoder.encode(password));
        usuario.setRol(Role.USER);
        return usuarioRepository.save(usuario);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public List<Usuario> listAll() {

        return usuarioRepository.findAll();
    }

    public List<Usuario> listAllByQ(String q) {

        return usuarioRepository.findAllByQ("%" + q + "%");
        //return usuarioRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Usuario usuario = usuarioRepository.findByUsername(username);
            User user;

            List<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            return new User(username,usuario.getPassword(),authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }
}
