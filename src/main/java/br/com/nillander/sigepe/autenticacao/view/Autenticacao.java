package br.com.nillander.sigepe.autenticacao.view;

import com.formdev.flatlaf.FlatClientProperties;

import br.com.nillander.sigepe.usuario.controller.UsuarioController;
import br.com.nillander.sigepe.usuario.model.UsuarioRepository;
import br.com.nillander.sigepe.usuario.view.UsuarioForm;

public class Autenticacao extends javax.swing.JFrame {

        public Autenticacao() {
                initComponents();
                init();
        }

        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
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

                jLabel3.setText("Senha");

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

                jlLogotipo.setText("jlLogotipo");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(jCheckBox1)
                                                                                .addComponent(jbuttonEntrar,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(txtPassword)
                                                                                .addComponent(jLabel3)
                                                                                .addComponent(lbTitle)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(txtUsername)
                                                                                .addComponent(jLabel4,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                280, Short.MAX_VALUE))
                                                                .addGap(32, 32, 32)
                                                                .addComponent(jlLogotipo,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                392, Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(19, 19, 19)
                                                                .addComponent(lbTitle)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtUsername,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtPassword,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jCheckBox1)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jbuttonEntrar)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel4)
                                                                .addContainerGap(123, Short.MAX_VALUE))
                                                .addComponent(jlLogotipo, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(25, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(25, Short.MAX_VALUE)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void jbuttonEntrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbuttonEntrarActionPerformed
                UsuarioForm usuarioForm = new UsuarioForm();
                usuarioForm.setVisible(true);
        }// GEN-LAST:event_jbuttonEntrarActionPerformed

        private static Autenticacao instance = null;

        public static Autenticacao getInstance() {
                if (instance == null) {
                        instance = new Autenticacao();
                }
                return instance;
        }

        private UsuarioRepository usuarioRepository = new UsuarioRepository();
        private UsuarioForm usuarioForm = new UsuarioForm();

        private void init() {
                txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite seu e-mail");
                txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite sua senha");
                jPanel1.putClientProperty(FlatClientProperties.STYLE, "" +
                                "arc:20;" +
                                "[light]background:darken(@background,3%);" +
                                "[dark]background:lighten(@background,3%)");

                txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                                "showRevealButton:true");

                configurarFrame();

                UsuarioController usuarioController = new UsuarioController(usuarioForm, usuarioRepository);
                usuarioForm.setVisible(true);
        }

        private void configurarFrame() {
                setLocationRelativeTo(null);
                txtUsername.requestFocus();
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
