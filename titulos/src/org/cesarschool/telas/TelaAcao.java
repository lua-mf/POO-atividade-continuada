package org.cesarschool.telas;

import javax.swing.*;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;
import java.awt.*;
import java.time.LocalDate;

public class TelaAcao extends JFrame {
    private MediatorAcao mediator = MediatorAcao.getInstancia();
    private static final long serialVersionUID = 1L;

    public TelaAcao() {
        setTitle("CRUD de Ações");
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
        incluirItem.addActionListener(e -> incluirAcao(statusArea));
        alterarItem.addActionListener(e -> alterarAcao(statusArea));
        excluirItem.addActionListener(e -> excluirAcao(statusArea));
        buscarItem.addActionListener(e -> buscarAcao(statusArea));
        sairItem.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void incluirAcao(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador da Ação:"));
            String nome = JOptionPane.showInputDialog("Informe o nome da Ação:");
            LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Informe a data de validade (YYYY-MM-DD):"));
            double valorUnitario = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor unitário da Ação:"));
            Acao acao = new Acao(id, nome, data, valorUnitario);
            String status = mediator.incluir(acao);
            statusArea.append((status == null ? "Ação incluída com sucesso!" : status) + "\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao incluir ação: " + ex.getMessage() + "\n");
        }
    }

    private void alterarAcao(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador da Ação para alterar:"));
            String nome = JOptionPane.showInputDialog("Informe o novo nome da Ação:");
            LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Informe a nova data de validade (YYYY-MM-DD):"));
            double valorUnitario = Double.parseDouble(JOptionPane.showInputDialog("Informe o novo valor unitário da Ação:"));
            Acao acao = new Acao(id, nome, data, valorUnitario);
            String status = mediator.alterar(acao);
            statusArea.append((status == null ? "Ação alterada com sucesso!" : status) + "\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao alterar ação: " + ex.getMessage() + "\n");
        }
    }

    private void excluirAcao(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador da Ação para excluir:"));
            String status = mediator.excluir(id);
            statusArea.append((status == null ? "Ação excluída com sucesso!" : status) + "\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao excluir ação: " + ex.getMessage() + "\n");
        }
    }

    private void buscarAcao(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador da Ação para buscar:"));
            Acao acao = mediator.buscar(id);
            if (acao != null) {
                statusArea.append("Ação encontrada: ID=" + acao.getIdentificador() +
                        ", Nome=" + acao.getNome() +
                        ", Data de Validade=" + acao.getDataDeValidade() +
                        ", Valor Unitário=" + acao.getValorUnitario() + "\n");
            } else {
                statusArea.append("Ação não encontrada.\n");
            }
        } catch (Exception ex) {
            statusArea.append("Erro ao buscar ação: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new TelaAcao();
    }
}
