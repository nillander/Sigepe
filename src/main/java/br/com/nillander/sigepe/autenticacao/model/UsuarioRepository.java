package br.com.nillander.sigepe.autenticacao.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Busca o usuário pelo username e password
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
