package br.com.nillander.sigepe.usuario.service;

import br.com.nillander.sigepe.usuario.model.Usuario;
import br.com.nillander.sigepe.usuario.model.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void registerUsuario(String username, String password, String email) {
        // Lógica de negócios
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);
        usuarioRepository.salvar(usuario);
    }
}
