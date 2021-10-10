package com.magicpet.petshop.servicios;

import com.magicpet.petshop.entidades.Usuario;
import com.magicpet.petshop.enums.Role;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    
    public List<Usuario> verTodos() {
        return usuarioRepositorio.findAll();
    }
    
    public Usuario buscarPorId(String id) {
        return usuarioRepositorio.getById(id);
    }
    
    @Transactional
    public Usuario nuevoRegistro(String username, String password, String confirmPassword, String mail) throws ErrorServicio {
        checkPasswordConfirmation(password, confirmPassword);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        Usuario usuario = new Usuario();
        usuario.setRol(Role.USER);
        usuario.setMail(mail);
        usuario.setUsername(username);
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        
        if (esValido(usuario)) {
            try {
                usuarioRepositorio.save(usuario);
            } catch (Exception e) {
                throw new ErrorServicio("Hubo un error al crear el usuario");
            }
        }
        return usuario;
    }
    
    @Transactional
    public Usuario nuevoRegistro(Usuario usuario, String password, String confirmPassword) throws ErrorServicio {
        checkPasswordConfirmation(password, confirmPassword);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(password));
        if (esValido(usuario)) {
            try {
                usuarioRepositorio.save(usuario);
            } catch (Exception e) {
                throw new ErrorServicio("Hubo un error al crear el usuario");
            }
        }
        return usuario;
    }
    
    @Transactional
    public Usuario modificar(Usuario usuario) throws ErrorServicio {
        try {
            if (esValido(usuario)) {
                return usuarioRepositorio.save(usuario);
            }
        } catch (Exception e) {
            throw new ErrorServicio(e.getMessage());
        }
        return usuario;
    }
    
    @Transactional
    public Usuario cambiarPassword(String id, String password, String confirmPassword) throws ErrorServicio {
        Usuario usuario;
        try {
            usuario = usuarioRepositorio.getById(id);
        } catch (Exception e) {
            throw new ErrorServicio("No se pudo encontrar al usuario");
        }
        checkPasswordConfirmation(password, confirmPassword);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
        usuario.setPassword(encoder.encode(password));
        
        usuarioRepositorio.save(usuario);
        return usuario;
    }

    @Transactional
    public Usuario cambiarRol(String id) throws ErrorServicio {
        Usuario usuario = usuarioRepositorio.getById(id);
        if (usuario.getRol() == Role.ADMIN) {
            usuario.setRol(Role.USER);
        } else {
            usuario.setRol(Role.ADMIN);
        }
        usuarioRepositorio.save(usuario);
        return usuario;
    }
    
    @Transactional
    public void eliminar(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }
    
    private boolean esValido(Usuario usuario) throws ErrorServicio {
        if (usuario.getUsername().isEmpty() || usuario.getUsername() == null) {
            throw new ErrorServicio("El campo usuario no puede estar vacío");
        } else if (usuario.getMail().isEmpty() || usuario.getMail() == null) {
            throw new ErrorServicio("El campo correo electrónico no puede estar vacío");
        } else if (usuario.getUsername().isEmpty() || usuario.getUsername() == null) {
            throw new ErrorServicio("El campo usuario no puede estar vacío");
        } else if (usuario.getUsername().isEmpty() || usuario.getUsername() == null) {
            throw new ErrorServicio("El campo usuario no puede estar vacío");
        }
        // TO DO: check if valid email
        return true;
    }
    
    private void checkPasswordConfirmation(String password1, String password2) throws ErrorServicio {
        if (password1.isEmpty() || password1 == null || password2.isEmpty() || password2 == null) {
            throw new ErrorServicio("Los campos de contraseña no pueden estar vacíos");
        } else if (!password1.equals(password2)) {
            throw new ErrorServicio("Las contraseñas no coinciden");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioRepositorio.findByUsername(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            return new User(username, usuario.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }
}
