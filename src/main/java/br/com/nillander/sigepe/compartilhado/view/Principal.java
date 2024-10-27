package br.com.nillander.sigepe.compartilhado.view;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import br.com.nillander.sigepe.autenticacao.model.Usuario;
import br.com.nillander.sigepe.lacadores.view.PanelLacadores;
import br.com.nillander.sigepe.usuario.view.InternalConteudo;
import br.com.nillander.sigepe.usuario.view.PanelUsuarios;

public class Principal extends javax.swing.JFrame {

    private Usuario usuario;

    private static Principal instance = null;

    public Principal() {
        initComponents();
        ativarFuncoesDoFrame();
    }

    public static Principal getInstance() {
        if (instance == null) {
            instance = new Principal();
        }
        return instance;
    }

    public Principal setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiLacadores = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiUsuarios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
                desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1936, Short.MAX_VALUE));
        desktopPaneLayout.setVerticalGroup(
                desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1025, Short.MAX_VALUE));

        jMenu1.setText("Laço em Dupla");

        jmiLacadores.setText("Laçadores");
        jmiLacadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLacadoresActionPerformed(evt);
            }
        });
        jMenu1.add(jmiLacadores);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sistema");

        jmiUsuarios.setText("Usuários");
        jmiUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUsuariosActionPerformed(evt);
            }
        });
        jMenu2.add(jmiUsuarios);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(desktopPane));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(desktopPane));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void exibirPainel(JPanel painel) {
        // Remove todos os componentes e adiciona o novo painel
        InternalConteudo.getInstance().getPanel().removeAll();
        InternalConteudo.getInstance().getPanel().setLayout(new java.awt.BorderLayout()); // Configura layout adequado
        InternalConteudo.getInstance().getPanel().add(painel, BorderLayout.CENTER); // Adiciona o painel ao centro
        InternalConteudo.getInstance().getPanel().revalidate();
        InternalConteudo.getInstance().getPanel().repaint();
        InternalConteudo.getInstance().setVisible(true);
    }

    private void jmiUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUsuariosActionPerformed
        exibirPainel(new PanelUsuarios());
    }//GEN-LAST:event_jmiUsuariosActionPerformed

    private void jmiLacadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLacadoresActionPerformed
        exibirPainel(new PanelLacadores());
    }//GEN-LAST:event_jmiLacadoresActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jmiLacadores;
    private javax.swing.JMenuItem jmiUsuarios;
    // End of variables declaration//GEN-END:variables

    public void ativarFuncoesDoFrame() {
        setLocationRelativeTo(null); // Centralizar janela no centro da tela
        setExtendedState(MAXIMIZED_BOTH); // Iniciar Maximizado

        // Adiciona o listener para confirmar ao sair
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Evita que a janela feche imediatamente
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmarSaida();
            }
        });
    }

    private void confirmarSaida() {
        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza de que deseja sair?", "Confirmação de Saída",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0); // Fecha a aplicação
        } else {
            repaint();
        }
    }

}
