package org.example.moody.Services;

import org.example.moody.Model.Usuario;
import org.example.moody.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario create(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario update(Integer id, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        if (!usuario.getEmail().equals(usuarioActualizado.getEmail()) &&
                usuarioRepository.existsByEmail(usuarioActualizado.getEmail())) {
            throw new IllegalArgumentException("Email inválido o duplicado");
        }

        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setEmail(usuarioActualizado.getEmail());
        if (usuarioActualizado.getContrasena() != null && !usuarioActualizado.getContrasena().isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(usuarioActualizado.getContrasena()));
        }
        usuario.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
