package br.com.nillander.sigepe.autenticacao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.github.ksuid.Ksuid;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ksuid", length = 27, unique = true, nullable = false, updatable = false)
    private String ksuid;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "senha", length = 32, nullable = false)
    private String senha;

    @Column(name = "nivel")
    private Integer nivel;

    @Column(name = "telefone", length = 11)
    private String telefone;

    @Column(name = "acesso_limite")
    private LocalDateTime acessoLimite;

    @Column(name = "autenticado_em")
    private LocalDateTime autenticadoEm;

    @Column(name = "usos")
    private Integer usos;

    @Column(name = "status", length = 1)
    private String status;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by")
    private String deletedBy;

    // Construtor
    public Usuario() {
        this.ksuid = Ksuid.newKsuid().toString(); // Gera um novo KSUID
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKsuid() {
        return ksuid;
    }

    public void setKsuid(String ksuid) {
        this.ksuid = ksuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getAcessoLimite() {
        return acessoLimite;
    }

    public void setAcessoLimite(LocalDateTime acessoLimite) {
        this.acessoLimite = acessoLimite;
    }

    public LocalDateTime getAutenticadoEm() {
        return autenticadoEm;
    }

    public void setAutenticadoEm(LocalDateTime autenticadoEm) {
        this.autenticadoEm = autenticadoEm;
    }

    public Integer getUsos() {
        return usos;
    }

    public void setUsos(Integer usos) {
        this.usos = usos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

}
