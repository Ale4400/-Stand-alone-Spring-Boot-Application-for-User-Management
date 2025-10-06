package org.example.moody.Repository;

import org.example.moody.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    java.util.List<Usuario> findByNombreContainingIgnoreCase(String nombreParcial);
}