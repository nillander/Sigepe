package br.com.nillander.sigepe.usuario.controller;

import br.com.nillander.sigepe.usuario.model.Usuario;
import br.com.nillander.sigepe.usuario.model.UsuarioRepository;
import br.com.nillander.sigepe.usuario.view.UsuarioForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioController {

    private UsuarioForm usuarioForm;
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioForm usuarioForm, UsuarioRepository usuarioRepository) {
        this.usuarioForm = usuarioForm;
        this.usuarioRepository = usuarioRepository;

        this.usuarioForm.getBotaoSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUsuario();
            }
        });
    }

    private void saveUsuario() {
        String username = usuarioForm.getUsernameField().getText();
        String password = new String(usuarioForm.getPasswordField().getPassword());
        String email = usuarioForm.getEmailField().getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(usuarioForm, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);

        usuarioRepository.salvar(usuario);
        JOptionPane.showMessageDialog(usuarioForm, "Usuário salvo com sucesso", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
