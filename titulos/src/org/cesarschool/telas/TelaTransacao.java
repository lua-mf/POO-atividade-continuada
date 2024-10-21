package org.cesarschool.telas;

import br.com.cesarschool.poo.titulos.entidades.*;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TelaTransacao extends JFrame {
    private static final long serialVersionUID = 1L;
    private RepositorioTransacao repositorioTransacao = new RepositorioTransacao();
    private JTextArea statusArea;

    public TelaTransacao() {
        setTitle("CRUD de Transações");
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
        statusArea = new JTextArea();
        statusArea.setEditable(false);
        add(new JScrollPane(statusArea), BorderLayout.CENTER);

        // Listeners dos itens do menu
        incluirItem.addActionListener(e -> incluirTransacao());
        alterarItem.addActionListener(e -> alterarTransacao());
        excluirItem.addActionListener(e -> excluirTransacao());
        buscarItem.addActionListener(e -> buscarTransacao());
        sairItem.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void incluirTransacao() {
        try {
            long idCredito = Long.parseLong(JOptionPane.showInputDialog("Informe o identificador da Entidade Crédito:"));
            EntidadeOperadora entidadeCredito = new EntidadeOperadora(idCredito, "Entidade Crédito", 0);

            long idDebito = Long.parseLong(JOptionPane.showInputDialog("Informe o identificador da Entidade Débito:"));
            EntidadeOperadora entidadeDebito = new EntidadeOperadora(idDebito, "Entidade Débito", 0);

            String acaoInput = JOptionPane.showInputDialog("Informe o identificador da Ação (ou 'null' se não houver):");
            Acao acao = null;
            if (!acaoInput.isEmpty() && !acaoInput.equals("null")) {
                acao = new Acao(Integer.parseInt(acaoInput), "Ação", LocalDateTime.now().toLocalDate(), 0);
            }

            String tituloInput = JOptionPane.showInputDialog("Informe o identificador do Título Dívida (ou 'null' se não houver):");
            TituloDivida tituloDivida = null;
            if (!tituloInput.isEmpty() && !tituloInput.equals("null")) {
                tituloDivida = new TituloDivida(Integer.parseInt(tituloInput), "Título Dívida", LocalDateTime.now().toLocalDate(), 0);
            }

            double valorOperacao = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor da operação:"));
            LocalDateTime dataHoraOperacao = LocalDateTime.parse(
                JOptionPane.showInputDialog("Informe a data e hora da operação (yyyy-MM-dd HH:mm:ss):"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            );

            Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
            repositorioTransacao.incluir(transacao);

            statusArea.append("Transação incluída com sucesso!\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao incluir transação: " + ex.getMessage() + "\n");
        }
    }

    private void alterarTransacao() {
        statusArea.append("Função de alteração ainda não implementada.\n");
    }

    private void excluirTransacao() {
        statusArea.append("Função de exclusão ainda não implementada.\n");
    }

    private void buscarTransacao() {
        statusArea.append("Função de busca ainda não implementada.\n");
    }

    public static void main(String[] args) {
        new TelaTransacao();
    }
}
