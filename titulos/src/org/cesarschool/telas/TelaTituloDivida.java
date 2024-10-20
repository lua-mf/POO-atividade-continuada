package org.cesarschool.telas;

import javax.swing.*;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

import java.awt.*;
import java.time.LocalDate;

public class TelaTituloDivida extends JFrame {
    private final MediatorTituloDivida service = MediatorTituloDivida.getInstancia();
    private static final long serialVersionUID = 1L;

    public TelaTituloDivida() {
        setTitle("CRUD de Títulos de Dívida");
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
        incluirItem.addActionListener(e -> incluirTituloDivida(statusArea));
        alterarItem.addActionListener(e -> alterarTituloDivida(statusArea));
        excluirItem.addActionListener(e -> excluirTituloDivida(statusArea));
        buscarItem.addActionListener(e -> buscarTituloDivida(statusArea));
        sairItem.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void incluirTituloDivida(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador do Título de Dívida:"));
            String nome = JOptionPane.showInputDialog("Informe o nome do Título de Dívida:");
            LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Informe a data de validade (YYYY-MM-DD):"));
            double taxaJuros = Double.parseDouble(JOptionPane.showInputDialog("Informe a taxa de juros:"));

            TituloDivida titulo = new TituloDivida(id, nome, data, taxaJuros);
            String status = service.incluir(titulo);
            statusArea.append(status != null ? status + "\n" : "Título de Dívida incluído com sucesso!\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao incluir título de dívida: " + ex.getMessage() + "\n");
        }
    }

    private void alterarTituloDivida(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador do Título de Dívida para alterar:"));
            String nome = JOptionPane.showInputDialog("Informe o novo nome do Título de Dívida:");
            LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Informe a nova data de validade (YYYY-MM-DD):"));
            double taxaJuros = Double.parseDouble(JOptionPane.showInputDialog("Informe a nova taxa de juros:"));

            TituloDivida titulo = new TituloDivida(id, nome, data, taxaJuros);
            String status = service.alterar(titulo);
            statusArea.append(status != null ? status + "\n" : "Título de Dívida alterado com sucesso!\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao alterar título de dívida: " + ex.getMessage() + "\n");
        }
    }

    private void excluirTituloDivida(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador do Título de Dívida para excluir:"));
            String status = service.excluir(id);
            statusArea.append(status != null ? status + "\n" : "Título de Dívida excluído com sucesso!\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao excluir título de dívida: " + ex.getMessage() + "\n");
        }
    }

    private void buscarTituloDivida(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador do Título de Dívida para buscar:"));
            TituloDivida titulo = service.buscar(id);
            if (titulo != null) {
                statusArea.append("Título de Dívida encontrado: ID=" + titulo.getIdentificador() +
                        ", Nome=" + titulo.getNome() +
                        ", Data de Validade=" + titulo.getDataDeValidade() +
                        ", Taxa de Juros=" + titulo.getTaxaJuros() + "\n");
            } else {
                statusArea.append("Título de Dívida não encontrado.\n");
            }
        } catch (Exception ex) {
            statusArea.append("Erro ao buscar título de dívida: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new TelaTituloDivida();
    }
}
