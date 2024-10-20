package org.cesarschool.telas;
import javax.swing.*;

import br.com.cesarschool.poo.titulos.entidades.Ativo;

import java.awt.*;
import java.time.LocalDate;

public class TelaAtivo extends JFrame {
    private AtivoCRUDService service = new AtivoCRUDService();
    private static final long serialVersionUID = 1L; // Adicione esta linha
    public TelaAtivo() {
        setTitle("CRUD de Ativos");
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
        incluirItem.addActionListener(e -> incluirAtivo(statusArea));
        alterarItem.addActionListener(e -> alterarAtivo(statusArea));
        excluirItem.addActionListener(e -> excluirAtivo(statusArea));
        buscarItem.addActionListener(e -> buscarAtivo(statusArea));
        sairItem.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void incluirAtivo(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador do Ativo:"));
            String nome = JOptionPane.showInputDialog("Informe o nome do Ativo:");
            LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Informe a data de validade (YYYY-MM-DD):"));
            Ativo ativo = new Ativo(id, nome, data);
            String status = service.incluir(ativo);
            statusArea.append(status + "\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao incluir ativo: " + ex.getMessage() + "\n");
        }
    }

    private void alterarAtivo(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador do Ativo para alterar:"));
            String nome = JOptionPane.showInputDialog("Informe o novo nome do Ativo:");
            LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Informe a nova data de validade (YYYY-MM-DD):"));
            String status = service.alterar(id, nome, data);
            statusArea.append(status + "\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao alterar ativo: " + ex.getMessage() + "\n");
        }
    }

    private void excluirAtivo(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador do Ativo para excluir:"));
            String status = service.excluir(id);
            statusArea.append(status + "\n");
        } catch (Exception ex) {
            statusArea.append("Erro ao excluir ativo: " + ex.getMessage() + "\n");
        }
    }

    private void buscarAtivo(JTextArea statusArea) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador do Ativo para buscar:"));
            Ativo ativo = service.buscar(id);
            if (ativo != null) {
                statusArea.append("Ativo encontrado: ID=" + ativo.getIdentificador() +
                        ", Nome=" + ativo.getNome() +
                        ", Data de Validade=" + ativo.getDataDeValidade() + "\n");
            } else {
                statusArea.append("Ativo não encontrado.\n");
            }
        } catch (Exception ex) {
            statusArea.append("Erro ao buscar ativo: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new TelaAtivo();
    }
}
