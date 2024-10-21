package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaOperacao extends JFrame {
	private static final long serialVersionUID = 1L;

    public TelaOperacao() {
        // Configuração básica da tela
        setTitle("Escolha a Operação");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel para os botões de seleção de operação
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Botão para a operação com Ação
        JButton btnAcao = new JButton("Operação com Ação");
        btnAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando o usuário clica em "Operação com Ação", abrimos a tela da operação com Ação
                TelaAcao telaAcao = new TelaAcao();  // Instanciar a tela de operação de Ação
                telaAcao.setVisible(true);           // Tornar a nova tela visível
                dispose();                          // Fechar a tela atual (TelaOperacao)
            }
        });

        // Botão para a operação com Título de Dívida
        JButton btnTituloDivida = new JButton("Operação com Título de Dívida");
        btnTituloDivida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando o usuário clica em "Operação com Título de Dívida", abrimos a tela da operação com Título de Dívida
                TelaTituloDivida telaTituloDivida = new TelaTituloDivida();  // Instanciar a tela de operação de Título de Dívida
                telaTituloDivida.setVisible(true);                           // Tornar a nova tela visível
                dispose();                                                   // Fechar a tela atual (TelaOperacao)
            }
        });

        // Botão para a operação de Transação
        JButton btnTransacao = new JButton("Operação de Transação");
        btnTransacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando o usuário clica em "Operação de Transação", abrimos a tela da operação de Transação
                TelaTransacao telaTransacao = new TelaTransacao();  // Instanciar a tela de operação de Transação
                telaTransacao.setVisible(true);                     // Tornar a nova tela visível
                dispose();                                          // Fechar a tela atual (TelaOperacao)
            }
        });

        panel.add(btnAcao);
        panel.add(btnTituloDivida);
        panel.add(btnTransacao);

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaOperacao(); 
    }
}
