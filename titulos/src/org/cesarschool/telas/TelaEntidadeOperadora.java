package org.cesarschool.telas;

import javax.swing.*;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

import java.awt.*;

public class TelaEntidadeOperadora extends JFrame {
    private MediatorEntidadeOperadora mediator = MediatorEntidadeOperadora.getInstancia();
    private static final long serialVersionUID = 1L;

    public TelaEntidadeOperadora() {
        setTitle("CRUD de Entidade Operadora");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opções");
        JMenuItem incluirItem = new JMenuItem("Incluir");
        JMenuItem alterarItem = new JMenuItem("Alterar");
        JMenuItem excluirItem = new JMenuItem("Excluir");
        JMenuItem buscarItem = new JMenuItem("Buscar");
        JMenuItem sairItem = new JMenuItem("Sair");

        menu.add(incluirItem);
        menu.add(alterarItem);
        menu.add(excluirItem);
        menu.add(buscarItem);
        menu.addSeparator();
        menu.add(sairItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Painel de status
        JTextArea statusArea = new JTextArea();
        statusArea.setEditable(false);
        add(new JScrollPane(statusArea), BorderLayout.CENTER);

        // Listeners dos itens do menu
        incluirItem.addActionListener(e -> incluirEntidadeOperadora(statusArea));
        alterarItem.addActionListener(e -> alterarEntidadeOperadora(statusArea));
        excluirItem.addActionListener(e -> excluirEntidadeOperadora(statusArea));
        buscarItem.addActionListener(e -> buscarEntidadeOperadora(statusArea));
        sairItem.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void incluirEntidadeOperadora(JTextArea statusArea) {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("Informe o identificador da Entidade Operadora:"));
            String nome = JOptionPane.showInputDialog("Informe o nome da Entidade Operadora:");
            double autorizadoAcao = Double.parseDouble(JOptionPane.showInputDialog("Informe o autorizado em ação:"));
            EntidadeOperadora entidade = new EntidadeOperadora(id, nome, autorizadoAcao);
            String status = mediator.incluir(entidade);
            statusArea.append(status + "\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao incluir entidade operadora: " + ex.getMessage() + "\n");
        }
    }

    private void alterarEntidadeOperadora(JTextArea statusArea) {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("Informe o identificador da Entidade Operadora para alterar:"));
            String nome = JOptionPane.showInputDialog("Informe o novo nome da Entidade Operadora:");
            double autorizadoAcao = Double.parseDouble(JOptionPane.showInputDialog("Informe o novo autorizado em ação:"));
            EntidadeOperadora entidade = new EntidadeOperadora(id, nome, autorizadoAcao);
            String status = mediator.alterar(entidade);
            statusArea.append(status + "\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao alterar entidade operadora: " + ex.getMessage() + "\n");
        }
    }

    private void excluirEntidadeOperadora(JTextArea statusArea) {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("Informe o identificador da Entidade Operadora para excluir:"));
            String status = mediator.excluir((int) id);
            statusArea.append(status + "\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao excluir entidade operadora: " + ex.getMessage() + "\n");
        }
    }

    private void buscarEntidadeOperadora(JTextArea statusArea) {
        try {
            long id = Long.parseLong(JOptionPane.showInputDialog("Informe o identificador da Entidade Operadora para buscar:"));
            EntidadeOperadora entidade = mediator.buscar((int) id);
            if (entidade != null) {
                statusArea.append("Entidade Operadora encontrada: ID=" + entidade.getIdentificador() +
                        ", Nome=" + entidade.getNome() +
                        ", Autorizado Ação=" + entidade.getAutorizadoAcao() +
                        ", Saldo Ação=" + entidade.getSaldoAcao() +
                        ", Saldo Título Dívida=" + entidade.getSaldoTituloDivida() + "\n");
            } else {
                statusArea.append("Entidade Operadora não encontrada.\n");
            }
        } catch (Exception ex) {
            statusArea.append("Erro ao buscar entidade operadora: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new TelaEntidadeOperadora();
    }
}
