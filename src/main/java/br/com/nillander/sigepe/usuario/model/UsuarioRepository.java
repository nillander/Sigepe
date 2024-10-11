package br.com.nillander.sigepe.usuario.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class UsuarioRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");

    // Salvar novo usuário (CREATE)
    public void salvar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    // Listar todos os usuários (READ - Listar todos)
    public List<Usuario> listarTodos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        List<Usuario> usuarios = query.getResultList();
        em.close();
        return usuarios;
    }

    // Encontrar usuário por ID (READ - Encontrar por ID)
    public Usuario encontrarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;
    }

    // Encontrar usuário por username (READ - Encontrar por nome de usuário)
    public Usuario encontrarPorNomeUsuario(String username) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username",
                Usuario.class);
        query.setParameter("username", username);
        Usuario usuario = query.getSingleResult();
        em.close();
        return usuario;
    }

    // Encontrar usuário por username e password (READ - Encontrar por nome de
    // usuário e senha)
    public Usuario encontrarPorNomeUsuarioESenha(String username, String senha) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password", Usuario.class);
        query.setParameter("username", username);
        query.setParameter("password", senha);
        Usuario usuario = query.getSingleResult();
        em.close();
        return usuario;
    }

    // Atualizar um usuário (UPDATE)
    public void atualizar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
        em.close();
    }

    // Remover um usuário (DELETE)
    public void remover(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.remove(usuario);
        }
        em.getTransaction().commit();
        em.close();
    }
}
