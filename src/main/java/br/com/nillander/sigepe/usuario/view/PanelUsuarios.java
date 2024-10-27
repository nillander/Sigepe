package br.com.nillander.sigepe.usuario.view;

import br.com.nillander.sigepe.App;
import br.com.nillander.sigepe.autenticacao.model.Usuario;
import br.com.nillander.sigepe.autenticacao.model.UsuarioRepository;
import br.com.nillander.sigepe.compartilhado.utils.DataFormatacao;
import br.com.nillander.sigepe.compartilhado.utils.Md5;
import br.com.nillander.sigepe.compartilhado.view.Principal;
import raven.toast.Notifications;

import javax.swing.table.DefaultTableModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class PanelUsuarios extends javax.swing.JPanel {

    UsuarioRepository usuarioRepository = App.getInstance().getContext().getBean(UsuarioRepository.class);

    private int currentPage = 0;
    private final int PAGE_SIZE = 200; // Número de registros por página
    private long totalPages = 0; // Número total de páginas

    public PanelUsuarios() {
        // Verifica o nível do usuário antes de inicializar o painel
        if (Principal.getInstance().getUsuario().getNivel() > 1) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Você não tem acesso");
            return; // Interrompe o restante do construtor
        }

        // Inicializa o painel normalmente se o nível for adequado
        initComponents();
        carregarUsuarios();
        configurarListenerSelecaoTabela(); // Configura o listener de seleção
    }

    private void carregarUsuarios() {
        Page<Usuario> page = usuarioRepository.findAll(PageRequest.of(currentPage, PAGE_SIZE));
        List<Usuario> usuarios = page.getContent();
        totalPages = page.getTotalPages();
        atualizarTabela(usuarios);
        atualizarIndicePagina();
    }

    private void atualizarTabela(List<Usuario> usuarios) {
        DefaultTableModel model = new DefaultTableModel(new String[] {
                "ID", "Acesso Limite", "Autenticado Em", "Criado Em", "Criado Por",
                "Deletado Em", "Deletado Por", "Email", "KSUID", "Nível",
                "Nome", "Senha", "Status", "Telefone", "Atualizado Em",
                "Atualizado Por", "Usos"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Obter o nome da coluna para verificação
                String columnName = getColumnName(column);

                // Permitir edição apenas nas colunas especificadas
                return "Email".equals(columnName) || "Nome".equals(columnName) || "Nível".equals(columnName) ||
                        "Status".equals(columnName) || "Telefone".equals(columnName) || "Usos".equals(columnName) ||
                        "Senha".equals(columnName); // Inclui a coluna Senha para permitir edição
            }
        };

        for (Usuario usuario : usuarios) {
            model.addRow(new Object[] {
                    usuario.getId(),
                    DataFormatacao.apenasData(usuario.getAcessoLimite()),
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

        tabelaConteudo.setModel(model);

        // Atualiza o labelResultados com a quantidade de usuários
        labelValueResultados.setText(String.valueOf(usuarios.size()));

        // Adiciona um listener para monitorar edições na tabela
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();

                // Captura o novo valor e o ID do usuário modificado
                Object newValue = model.getValueAt(row, column);
                Long usuarioId = (Long) model.getValueAt(row, 0); // Coluna "ID"

                // Busca o usuário pelo ID
                Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
                if (usuario == null) {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Usuário não encontrado para atualização");
                    return;
                }

                // Obter o nome da coluna para o switch
                String columnName = model.getColumnName(column);

                // Atualiza o campo correspondente no objeto Usuario baseado no nome da coluna
                switch (columnName) {
                    case "Email":
                        String novoEmail = (String) newValue;

                        // Verificar se o email já está em uso por outro usuário
                        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findByEmail(novoEmail);
                        if (usuarioExistenteOpt.isPresent() && !usuarioExistenteOpt.get().getId().equals(usuarioId)) {
                            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Já existe um usuário com este email.");

                            // Remover temporariamente o listener antes de restaurar o valor
                            model.removeTableModelListener(this);

                            // Restaurar o valor original na tabela
                            model.setValueAt(usuario.getEmail(), row, column);

                            // Re-adicionar o listener
                            model.addTableModelListener(this);
                            return; // Interrompe a atualização
                        }

                        usuario.setEmail(novoEmail);
                        break;
                    case "Nível":
                        usuario.setNivel((Integer) newValue);
                        break;
                    case "Nome":
                        usuario.setNome((String) newValue);
                        break;
                    case "Status":
                        usuario.setStatus((String) newValue);
                        break;
                    case "Telefone":
                        usuario.setTelefone((String) newValue);
                        break;
                    case "Usos":
                        // Permitir valores nulos para a coluna Usos
                        if (newValue == null || ((String) newValue).trim().isEmpty()) {
                            usuario.setUsos(null); // Define o campo como null
                        } else {
                            try {
                            // Converter o valor para Integer
                                usuario.setUsos(Integer.parseInt((String) newValue));
                            } catch (NumberFormatException ex) {
                                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Valor inválido para o campo Usos. Insira um número.");

                                // Restaurar o valor original se a conversão falhar
                                model.removeTableModelListener(this);
                                model.setValueAt(usuario.getUsos(), row, column);
                                model.addTableModelListener(this);
                                return;
                            }
                        }
                        break;
                    case "Senha":
                        // Criptografa a senha com MD5 e atualiza o objeto Usuario
                        String senhaHash = Md5.hash((String) newValue);
                        usuario.setSenha(senhaHash);

                        // Remove temporariamente o listener antes de atualizar a célula
                        model.removeTableModelListener(this);
                        model.setValueAt(senhaHash, row, column); // Atualiza a célula com o hash criptografado
                        model.addTableModelListener(this); // Re-adiciona o listener
                        break;
                    default:
                        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Coluna não editável");
                        return;
                }

                // Salva a atualização no banco de dados
                usuarioRepository.save(usuario);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Usuário " + usuario.getNome() + " atualizado com sucesso.");
            }
        });
    }

    // Adicione um método para configurar o listener de seleção
    private void configurarListenerSelecaoTabela() {
        tabelaConteudo.getSelectionModel().addListSelectionListener(event -> {
            int linhasSelecionadas = tabelaConteudo.getSelectedRowCount();
            labelValueSelecionados.setText(String.valueOf(linhasSelecionadas));
        });
    }

    private void proximaPagina() {
        if (currentPage < totalPages - 1) {
            currentPage++;
            carregarUsuarios();
        }
    }

    private void paginaAnterior() {
        if (currentPage > 0) {
            currentPage--;
            carregarUsuarios();
        }
    }

    private void atualizarIndicePagina() {
        labelIndicePagina.setText("Página " + (currentPage + 1) + " de " + totalPages);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContainer = new javax.swing.JPanel();
        labelTextTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        splitContainer = new javax.swing.JSplitPane();
        panelMenu = new javax.swing.JPanel();
        buttonAtualizar = new javax.swing.JButton();
        buttonNovo = new javax.swing.JButton();
        buttonEditar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        buttonImprimir = new javax.swing.JButton();
        buttonDetalhes = new javax.swing.JButton();
        panelContent = new javax.swing.JPanel();
        labelTextPesquisar = new javax.swing.JLabel();
        textFieldPesquisa = new javax.swing.JTextField();
        buttonPesquisar = new javax.swing.JButton();
        labelIndicePagina = new javax.swing.JLabel();
        buttonAnterior = new javax.swing.JButton();
        buttonProximo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConteudo = new javax.swing.JTable();
        labelTextResultados = new javax.swing.JLabel();
        labelValueResultados = new javax.swing.JLabel();
        labelTextSelecionados = new javax.swing.JLabel();
        labelValueSelecionados = new javax.swing.JLabel();

        labelTextTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTextTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTextTitulo.setText("Usuários");

        splitContainer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        buttonAtualizar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonAtualizar.setText("Atualizar");
        buttonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAtualizarActionPerformed(evt);
            }
        });

        buttonNovo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonNovo.setText("Novo");

        buttonEditar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonEditar.setText("Editar");

        buttonExcluir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonExcluir.setText("Excluir");

        buttonImprimir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonImprimir.setText("Imprimir");

        buttonDetalhes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonDetalhes.setText("Detalhes");

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
                panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelMenuLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                        .addComponent(buttonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                        .addComponent(buttonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                        .addComponent(buttonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, 0)));
        panelMenuLayout.setVerticalGroup(
                panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelMenuLayout.createSequentialGroup()
                                .addComponent(buttonAtualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonImprimir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonDetalhes)
                                .addContainerGap(279, Short.MAX_VALUE)));

        splitContainer.setLeftComponent(panelMenu);

        labelTextPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTextPesquisar.setText("Pesquisar:");

        textFieldPesquisa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textFieldPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldPesquisaKeyReleased(evt);
            }
        });

        buttonPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonPesquisar.setText("X");

        labelIndicePagina.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelIndicePagina.setText("Página x de x");

        buttonAnterior.setText("<");
        buttonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnteriorActionPerformed(evt);
            }
        });

        buttonProximo.setText(">");
        buttonProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProximoActionPerformed(evt);
            }
        });

        tabelaConteudo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabelaConteudo.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jScrollPane1.setViewportView(tabelaConteudo);

        labelTextResultados.setText("Resultados:");

        labelValueResultados.setText("00.000");

        labelTextSelecionados.setText("Selecionados:");

        labelValueSelecionados.setText("00.000");

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
                panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelContentLayout.createSequentialGroup()
                                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelContentLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(labelTextPesquisar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textFieldPesquisa)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonPesquisar))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                                                .addComponent(labelTextResultados)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelValueResultados)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelTextSelecionados)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelValueSelecionados)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(labelIndicePagina)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap()));
        panelContentLayout.setVerticalGroup(
                panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelContentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelTextPesquisar)
                                        .addComponent(buttonPesquisar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelIndicePagina)
                                        .addComponent(labelTextResultados)
                                        .addComponent(labelValueResultados)
                                        .addComponent(labelTextSelecionados)
                                        .addComponent(labelValueSelecionados)
                                        .addComponent(buttonAnterior)
                                        .addComponent(buttonProximo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)));

        splitContainer.setRightComponent(panelContent);

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
                panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(splitContainer)
                        .addComponent(labelTextTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1));
        panelContainerLayout.setVerticalGroup(
                panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelContainerLayout.createSequentialGroup()
                                .addComponent(labelTextTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(splitContainer)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(25, 25, 25)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(25, 25, 25)));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAtualizarActionPerformed
        currentPage = 0;
        carregarUsuarios();
    }//GEN-LAST:event_buttonAtualizarActionPerformed

    private void buttonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnteriorActionPerformed
        paginaAnterior();
    }//GEN-LAST:event_buttonAnteriorActionPerformed

    private void buttonProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProximoActionPerformed
        proximaPagina();
    }//GEN-LAST:event_buttonProximoActionPerformed

    private void textFieldPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldPesquisaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String termoPesquisa = textFieldPesquisa.getText().trim();
            if (termoPesquisa.isEmpty()) {
                carregarUsuarios(); // Recarrega a tabela com todos os dados se a pesquisa estiver vazia
            } else {
                pesquisarUsuarios(termoPesquisa);
            }
        } else if (textFieldPesquisa.getText().trim().isEmpty()) {
            carregarUsuarios(); // Recarrega a tabela com todos os dados se a pesquisa estiver vazia
        }
    }//GEN-LAST:event_textFieldPesquisaKeyReleased

    private void pesquisarUsuarios(String termo) {
        List<Usuario> usuarios = usuarioRepository.findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(termo, termo);
        atualizarTabela(usuarios);
    }

    private void jbuttonNovoActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAnterior;
    private javax.swing.JButton buttonAtualizar;
    private javax.swing.JButton buttonDetalhes;
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonImprimir;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JButton buttonPesquisar;
    private javax.swing.JButton buttonProximo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelIndicePagina;
    private javax.swing.JLabel labelTextPesquisar;
    private javax.swing.JLabel labelTextResultados;
    private javax.swing.JLabel labelTextSelecionados;
    private javax.swing.JLabel labelTextTitulo;
    private javax.swing.JLabel labelValueResultados;
    private javax.swing.JLabel labelValueSelecionados;
    private javax.swing.JPanel panelContainer;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JSplitPane splitContainer;
    private javax.swing.JTable tabelaConteudo;
    private javax.swing.JTextField textFieldPesquisa;
    // End of variables declaration//GEN-END:variables

}
