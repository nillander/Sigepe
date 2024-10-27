package br.com.nillander.sigepe.autenticacao.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca o usuário pelo username e password
    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.deletedAt = CURRENT_TIMESTAMP, u.deletedBy = :deletedBy WHERE u.id = :id")
    int softDeleteById(@Param("id") Long id, @Param("deletedBy") String deletedBy);

    List<Usuario> findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(String nome, String email);

}
