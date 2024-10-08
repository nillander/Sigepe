package br.com.nillander.sigepe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton saveButton;
    private UserRepository userRepository;

    public MainFrame() {
        userRepository = new UserRepository();

        setTitle("Cadastro de Usuário");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        usernameField = new JTextField();
        usernameField.setBounds(100, 30, 150, 25);
        usernameField.setText("Cadastro de Usuário");
        add(usernameField);

        passwordField = new JTextField();
        passwordField.setBounds(100, 70, 150, 25);
        add(passwordField);

        saveButton = new JButton("Salvar");
        saveButton.setBounds(100, 110, 150, 25);
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setUsername(usernameField.getText());
                user.setPassword(passwordField.getText());
                userRepository.saveUser(user);
                JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso!");
            }
        });
    }

}
