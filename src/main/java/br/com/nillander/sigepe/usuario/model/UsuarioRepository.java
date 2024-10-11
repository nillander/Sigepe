package br.com.nillander.sigepe.usuario.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");

    public void save(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public Usuario findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;
    }
}
