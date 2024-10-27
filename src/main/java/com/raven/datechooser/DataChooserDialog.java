package com.raven.datechooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataChooserDialog extends JDialog {
    private final DateChooser dateChooser;
    private Date selectedDate;

    public DataChooserDialog(Frame parent) {
        super(parent, "Escolha uma Data", true);
        dateChooser = new DateChooser();
        initDialog();
    }

    private void initDialog() {
        // Configurar layout do JDialog
        setLayout(new BorderLayout());
        add(dateChooser, BorderLayout.CENTER);

        // Botões de Confirmação e Cancelamento
        JPanel buttonPanel = new JPanel();
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");
        
        // Adicionar funcionalidade aos botões
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recupera a data selecionada e define a hora para 23:59:59
                SelectedDate selected = dateChooser.getSelectedDate();
                try {
                    String dateStr = selected.getDay() + "-" + selected.getMonth() + "-" + selected.getYear() + " 23:59:59";
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    selectedDate = sdf.parse(dateStr);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDate = null; // Cancelar sem definir uma data
                dispose();
            }
        });

        buttonPanel.add(btnConfirmar);
        buttonPanel.add(btnCancelar);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }

    public Date getSelectedDate() {
        return selectedDate;
    }
}
