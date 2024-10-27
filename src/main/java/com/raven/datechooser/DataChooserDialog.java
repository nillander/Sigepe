package com.raven.datechooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataChooserDialog extends JDialog {
    private final DateChooser dateChooser;
    private Date selectedDate; // A data selecionada ou nula, dependendo da ação
    private final Date initialDate; // Armazena o valor inicial

    public DataChooserDialog(Frame parent, Date initialDate) {
        super(parent, "Escolha uma Data", true);
        this.initialDate = initialDate; // Recebe a data inicial da célula
        this.selectedDate = initialDate; // Define como inicial
        dateChooser = new DateChooser();
        initDialog();
    }

    private void initDialog() {
        setLayout(new BorderLayout());
        add(dateChooser, BorderLayout.CENTER);

        // Se inicializar com uma data, exibi-la no DateChooser
        if (initialDate != null) {
            dateChooser.setSelectedDate(initialDate);
        }

        // Botões de Confirmação, Cancelamento e Limpar
        JPanel buttonPanel = new JPanel();
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnLimpar = new JButton("Limpar");

        // Ação do botão Confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectedDate selected = dateChooser.getSelectedDate();
                try {
                    String dateStr = selected.getDay() + "-" + selected.getMonth() + "-" + selected.getYear() + " 23:59:59";
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    selectedDate = sdf.parse(dateStr); // Define a data confirmada
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });

        // Ação do botão Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDate = initialDate; // Restaura a data original ao cancelar
                dispose();
            }
        });

        // Ação do botão Limpar
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDate = null; // Define como null para limpar a data
                dispose();
            }
        });

        // Adicionar os botões ao painel
        buttonPanel.add(btnConfirmar);
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnLimpar);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public Date getSelectedDate() {
        return selectedDate;
    }
}
