package br.com.nillander.sigepe.usuario.view;

import br.com.nillander.sigepe.App;
import br.com.nillander.sigepe.autenticacao.model.Usuario;
import br.com.nillander.sigepe.autenticacao.model.UsuarioRepository;
import br.com.nillander.sigepe.compartilhado.view.Principal;
import raven.toast.Notifications;

import javax.swing.*;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelUsuariosCadastro extends JPanel {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JComboBox<String> cmbNivel;
    private JButton btnSalvar;
    private JButton btnVoltar;
    private JComboBox<String> cmbStatus;
    private JSpinner spnUsos;
    private JPasswordField txtPassword;

    public PanelUsuariosCadastro() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(new JLabel("Nome:"), gbc);
        txtNome = new JTextField(20);
        gbc.gridx = 1;
        panelForm.add(txtNome, gbc);

        // Campo Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField(20);
        gbc.gridx = 1;
        panelForm.add(txtEmail, gbc);

        // Campo Telefone
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(new JLabel("Telefone:"), gbc);
        txtTelefone = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtTelefone, gbc);

        // Campo Senha
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelForm.add(new JLabel("Senha:"), gbc);
        txtSenha = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtSenha, gbc);

        // Campo Nível
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelForm.add(new JLabel("Nível:"), gbc);
        cmbNivel = new JComboBox<>(new String[] { "1", "2", "3" });
        gbc.gridx = 1;
        panelForm.add(cmbNivel, gbc);

        // Campo Status
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelForm.add(new JLabel("Status:"), gbc);
        cmbStatus = new JComboBox<>(new String[] { "A", "D", "P" });
        gbc.gridx = 1;
        panelForm.add(cmbStatus, gbc);

        // Campo Usos
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelForm.add(new JLabel("Usos:"), gbc);
        spnUsos = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        gbc.gridx = 1;
        panelForm.add(spnUsos, gbc);

        // Configura o botão Salvar e sua ação
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarUsuario();
            }
        });

        // Botão Voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Principal.getInstance().exibirPainel(new PanelUsuarios());
            }
        });

        JPanel panelButtons = new JPanel();
        panelButtons.add(btnSalvar);
        panelButtons.add(btnVoltar);

        add(panelForm, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private void salvarUsuario() {
        UsuarioRepository usuarioRepository = App.getInstance().getContext().getBean(UsuarioRepository.class);

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(txtNome.getText().trim());
        novoUsuario.setEmail(txtEmail.getText().trim());
        novoUsuario.setTelefone(txtTelefone.getText().trim());
        novoUsuario.setSenha(new String(txtPassword.getPassword()));
        novoUsuario.setNivel(Integer.parseInt((String) cmbNivel.getSelectedItem()));
        novoUsuario.setStatus((String) cmbStatus.getSelectedItem());
        novoUsuario.setUsos((Integer) spnUsos.getValue());

        usuarioRepository.save(novoUsuario);
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Usuário salvo com sucesso.");

        // Limpeza dos campos após salvar
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtPassword.setText("");
        cmbNivel.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
        spnUsos.setValue(0);
    }
}
