package br.com.nillander.sigepe.autenticacao.view;

import javax.swing.*;

import br.com.nillander.sigepe.App;
import br.com.nillander.sigepe.autenticacao.model.UsuarioRepository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioCad extends JFrame {

    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton salvarButton;

    private UsuarioRepository usuarioRepository;

    public UsuarioCad(String titulo) {
        this.usuarioRepository = App.getInstance().getContext().getBean(UsuarioRepository.class);

        setTitle("Cadastro de Usuário");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 20, 80, 25);
        add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(100, 20, 165, 25);
        nomeField.setText("Usuário ASDF");
        add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 80, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setText(titulo);
        emailField.setBounds(100, 50, 165, 25);
        add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 80, 80, 25);
        add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setBounds(100, 80, 165, 25);
        add(senhaField);

        salvarButton = new JButton("Salvar");
        salvarButton.setBounds(10, 110, 150, 25);
        add(salvarButton);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = new Usuario();
                usuario.setNome(nomeField.getText());
                usuario.setEmail(emailField.getText());
                usuario.setSenha(new String(senhaField.getPassword()));
                usuarioRepository.save(usuario);
                JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso!");
            }
        });
    }

}
