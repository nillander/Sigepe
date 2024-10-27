package br.com.nillander.sigepe.usuario.view;

import br.com.nillander.sigepe.App;
import br.com.nillander.sigepe.autenticacao.model.Usuario;
import br.com.nillander.sigepe.autenticacao.model.UsuarioRepository;
import br.com.nillander.sigepe.compartilhado.utils.DataFormatacao;
import br.com.nillander.sigepe.compartilhado.view.Principal;
import raven.toast.Notifications;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PanelUsuarios extends javax.swing.JPanel {

    UsuarioRepository usuarioRepository = App.getInstance().getContext().getBean(UsuarioRepository.class);

    public PanelUsuarios() {
        // Verifica o nível do usuário antes de inicializar o painel
        if (Principal.getInstance().getUsuario().getNivel() > 1) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Você não tem acesso");
            return; // Interrompe o restante do construtor
        }

        // Inicializa o painel normalmente se o nível for adequado
        initComponents();
        carregarUsuarios();
    }

    // Método para carregar os usuários e exibir na tabela
    private void carregarUsuarios() {
        // Obtém a lista de usuários do banco de dados
        List<Usuario> usuarios = usuarioRepository.findAll();

        // Define o modelo da tabela com todas as colunas necessárias
        DefaultTableModel model = new DefaultTableModel(new String[] {
                "ID", "Acesso Limite", "Autenticado Em", "Criado Em", "Criado Por",
                "Deletado Em", "Deletado Por", "Email", "KSUID", "Nível",
                "Nome", "Senha", "Status", "Telefone", "Atualizado Em",
                "Atualizado Por", "Usos"
        }, 0);

        // Adiciona uma linha para cada usuário com os valores dos atributos
        for (Usuario usuario : usuarios) {
            model.addRow(new Object[] {
                    usuario.getId(),
                    DataFormatacao.format(usuario.getAcessoLimite()),
                    DataFormatacao.format(usuario.getAutenticadoEm()),
                    DataFormatacao.format(usuario.getCreatedAt()),
                    usuario.getCreatedBy(),
                    DataFormatacao.format(usuario.getDeletedAt()),
                    usuario.getDeletedBy(),
                    usuario.getEmail(),
                    usuario.getKsuid(),
                    usuario.getNivel(),
                    usuario.getNome(),
                    usuario.getSenha(),
                    usuario.getStatus(),
                    usuario.getTelefone(),
                    DataFormatacao.format(usuario.getUpdatedAt()),
                    usuario.getUpdatedBy(),
                    usuario.getUsos()
            });
        }

        // Aplica o modelo preenchido à tabela
        tableConteudo.setModel(model);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlTitle = new javax.swing.JLabel();
        jbuttonNovo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableConteudo = new javax.swing.JTable();
        buttonAtualizar = new javax.swing.JButton();

        jlTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitle.setText("Usuários");

        jbuttonNovo.setText("Novo");
        jbuttonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonNovoActionPerformed(evt);
            }
        });

        tableConteudo.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jScrollPane1.setViewportView(tableConteudo);

        buttonAtualizar.setText("Atualizar");
        buttonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jlTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jbuttonNovo)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(buttonAtualizar))
                                                        .addComponent(jScrollPane1))
                                                .addGap(15, 15, 15)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jbuttonNovo)
                                        .addComponent(buttonAtualizar))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAtualizarActionPerformed
        carregarUsuarios();
    }//GEN-LAST:event_buttonAtualizarActionPerformed

    private void jbuttonNovoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAtualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbuttonNovo;
    private javax.swing.JLabel jlTitle;
    private javax.swing.JTable tableConteudo;
    // End of variables declaration//GEN-END:variables

}
