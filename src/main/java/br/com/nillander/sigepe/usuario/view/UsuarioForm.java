package br.com.nillander.sigepe.usuario.view;

import javax.swing.*;
import java.awt.*;

public class UsuarioForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton botaoSalvar;

    public UsuarioForm() {
        setTitle("Cadastro de Usuário");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        botaoSalvar = new JButton("Salvar");
        add(botaoSalvar);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JButton getBotaoSalvar() {
        return botaoSalvar;
    }
}
