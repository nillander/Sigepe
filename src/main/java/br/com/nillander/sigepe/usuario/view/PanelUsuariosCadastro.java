package br.com.nillander.sigepe.usuario.view;

import br.com.nillander.sigepe.App;
import br.com.nillander.sigepe.autenticacao.model.Usuario;
import br.com.nillander.sigepe.autenticacao.model.UsuarioRepository;
import br.com.nillander.sigepe.compartilhado.utils.Md5;
import br.com.nillander.sigepe.compartilhado.view.Principal;
import raven.toast.Notifications;

import javax.swing.*;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class PanelUsuariosCadastro extends JPanel {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JComboBox<String> cmbNivel;
    private JButton btnSalvar;
    private JButton btnVoltar;
    private JComboBox<String> cmbStatus;
    private JSpinner spnUsos;
    private JPasswordField txtSenha;

    public PanelUsuariosCadastro() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Define o tamanho da fonte para todos os componentes
        Font fontePadrao = new Font("Segoe UI", Font.PLAIN, 14);

        // Painel de formulário
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(fontePadrao);
        panelForm.add(labelNome, gbc);
        txtNome = new JTextField();
        txtNome.setFont(fontePadrao);
        gbc.gridx = 1;
        panelForm.add(txtNome, gbc);

        // Campo Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(fontePadrao);
        panelForm.add(labelEmail, gbc);
        txtEmail = new JTextField();
        txtEmail.setFont(fontePadrao);
        gbc.gridx = 1;
        panelForm.add(txtEmail, gbc);

        // Campo Telefone
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(fontePadrao);
        panelForm.add(labelTelefone, gbc);
        txtTelefone = new JTextField(15);
        txtTelefone.setFont(fontePadrao);
        gbc.gridx = 1;
        panelForm.add(txtTelefone, gbc);

        // Campo Senha
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(fontePadrao);
        panelForm.add(labelSenha, gbc);
        txtSenha = new JPasswordField();
        txtSenha.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        txtSenha.setFont(fontePadrao);
        gbc.gridx = 1;
        panelForm.add(txtSenha, gbc);

        // Campo Nível
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel labelNivel = new JLabel("Nível:");
        labelNivel.setFont(fontePadrao);
        panelForm.add(labelNivel, gbc);
        cmbNivel = new JComboBox<>(new String[] { "1", "2", "3" });
        cmbNivel.setFont(fontePadrao);
        gbc.gridx = 1;
        panelForm.add(cmbNivel, gbc);

        // Campo Status
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel labelStatus = new JLabel("Status:");
        labelStatus.setFont(fontePadrao);
        panelForm.add(labelStatus, gbc);
        cmbStatus = new JComboBox<>(new String[] { "A", "D", "P" });
        cmbStatus.setFont(fontePadrao);
        gbc.gridx = 1;
        panelForm.add(cmbStatus, gbc);

        // Campo Usos
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel labelUsos = new JLabel("Usos:");
        labelUsos.setFont(fontePadrao);
        panelForm.add(labelUsos, gbc);
        spnUsos = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        spnUsos.setFont(fontePadrao);
        gbc.gridx = 1;
        panelForm.add(spnUsos, gbc);

        // Configura o botão Salvar e sua ação
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(fontePadrao);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarUsuario();
            }
        });

        // Botão Voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(fontePadrao);
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

        // Validação de campos
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();
        Integer usos = null;
        try {
            usos = (Integer) spnUsos.getValue();
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "O campo Usos deve ser um número inteiro.");
            return;
        }

        if (nome.length() < 3) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "O nome deve conter pelo menos 3 caracteres.");
            return;
        }
        if (email.length() < 3) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "O email deve conter pelo menos 3 caracteres.");
            return;
        }
        if (telefone.length() < 3) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "O telefone deve conter pelo menos 3 caracteres.");
            return;
        }
        if (senha.length() < 3) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "A senha deve conter pelo menos 3 caracteres.");
            return;
        }

        // Verifica se já existe um usuário com o mesmo email
        if (usuarioRepository.findByEmail(email).isPresent()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Já existe um usuário com este email.");
            return;
        }

        // Criação e salvamento do novo usuário
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setTelefone(telefone);
        novoUsuario.setSenha(Md5.hash(new String(txtSenha.getPassword())));
        novoUsuario.setNivel(Integer.parseInt((String) cmbNivel.getSelectedItem()));
        novoUsuario.setStatus((String) cmbStatus.getSelectedItem());
        novoUsuario.setUsos(usos == 0 ? null : usos);

        // Define Acesso Limite para a data atual mais 1 mês
        novoUsuario.setAcessoLimite(LocalDateTime.now().plusMonths(1));

        usuarioRepository.save(novoUsuario);
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Usuário salvo com sucesso.");

        // Limpeza dos campos após salvar
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtSenha.setText("");
        cmbNivel.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
        spnUsos.setValue(0);
    }
}
