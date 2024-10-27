package br.com.nillander.sigepe.autenticacao.view;

import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.time.LocalDateTime;

import com.formdev.flatlaf.FlatClientProperties;

import br.com.nillander.sigepe.App;
import br.com.nillander.sigepe.autenticacao.model.Usuario;
import br.com.nillander.sigepe.autenticacao.model.UsuarioRepository;
import br.com.nillander.sigepe.compartilhado.services.ImageService;
import br.com.nillander.sigepe.compartilhado.utils.Md5;
import br.com.nillander.sigepe.compartilhado.view.Principal;
import java.awt.event.KeyEvent;

public class Autenticacao extends javax.swing.JFrame {

    public Autenticacao() {
        initComponents();
        init();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jbuttonEntrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jlLogotipo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autenticar");

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        jLabel3.setText("Senha");

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("E-mail");

        jLabel1.setText("Informe suas credenciais de acesso");

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setText("Sistema de Laço");

        jCheckBox1.setText("Lembrar-me");

        jbuttonEntrar.setText("Entrar");
        jbuttonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonEntrarActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Não possui uma conta? Criar conta");

        jlLogotipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sgpld_logo_b.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jCheckBox1)
                                        .addComponent(jbuttonEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPassword)
                                        .addComponent(jLabel3)
                                        .addComponent(lbTitle)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(txtUsername)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(jlLogotipo, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlLogotipo, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lbTitle)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jCheckBox1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jbuttonEntrar)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)))
                                .addContainerGap(15, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            acionarBotaoEntrar();
        }
    }// GEN-LAST:event_txtUsernameKeyPressed

    private void jbuttonEntrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbuttonEntrarActionPerformed
        acionarBotaoEntrar();
    }// GEN-LAST:event_jbuttonEntrarActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            acionarBotaoEntrar();
        }
    }// GEN-LAST:event_txtPasswordKeyPressed

    private static Autenticacao instance = null;

    public static Autenticacao getInstance() {
        if (instance == null) {
            instance = new Autenticacao();
        }
        return instance;
    }

    private void init() {
        jlLogotipo.setIcon(ImageService.getInstance().resizeImageIcon((ImageIcon) jlLogotipo.getIcon(), 278));
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite seu e-mail");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite sua senha");
        jPanel1.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");

        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");

        configurarFrame();

    }

    private void configurarFrame() {
        setLocationRelativeTo(null);
        txtUsername.requestFocus();
    }

    private void acionarBotaoEntrar() {
        boolean encontrado = false;
        if (txtUsername.getText().isEmpty()) {
            txtUsername.setText("nillander@live.com");
            txtPassword.setText("teste123");
        }

        String email = txtUsername.getText();
        String senha = new String(txtPassword.getPassword()); // Converte o array de char em String
        senha = Md5.hash(senha); // Gera o hash MD5 da senha

        // Valida se os campos estão preenchidos
        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Busca o usuário no banco de dados usando o repositório
        UsuarioRepository usuarioRepository = App.getInstance().getContext().getBean(UsuarioRepository.class);
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndSenha(email, senha);

        // Verifica se o usuário foi encontrado
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            usuario.setAutenticadoEm(LocalDateTime.now());
            usuarioRepository.save(usuario);
            // JOptionPane.showMessageDialog(this, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            encontrado = true;
            // Abre a janela principal
            dispose();
            Principal.getInstance().setUsuario(usuario).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Credenciais inválidas. Tente novamente.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (encontrado == false) {
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Usuário não encontrado. Deseja criar um novo usuário?",
                    "Usuário não encontrado",
                    JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                // Coleta de dados do novo usuário
                String nome = JOptionPane.showInputDialog(this, "Digite o nome do novo usuário:");
                String novoEmail = JOptionPane.showInputDialog(this, "Digite o email do novo usuário:",
                        email);
                String novaSenha = JOptionPane.showInputDialog(this, "Digite a senha do novo usuário:");
                String telefone = JOptionPane.showInputDialog(this,
                        "Digite o telefone do novo usuário:");

                // Verifica se os dados essenciais foram preenchidos
                if (nome == null || novoEmail == null || novaSenha == null || nome.isEmpty()
                        || novoEmail.isEmpty() || novaSenha.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Cadastro cancelado. Preencha todos os campos obrigatórios.",
                            "Cadastro Incompleto", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Cria e salva o novo usuário
                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome(nome);
                novoUsuario.setEmail(novoEmail);
                novoUsuario.setSenha(Md5.hash(novaSenha)); // Hash da senha
                novoUsuario.setTelefone(telefone);
                novoUsuario.setCreatedAt(LocalDateTime.now());
                novoUsuario.setStatus("A"); // Exemplo de status ativo

                usuarioRepository.save(novoUsuario);

                JOptionPane.showMessageDialog(this, "Novo usuário criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbuttonEntrar;
    private javax.swing.JLabel jlLogotipo;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
