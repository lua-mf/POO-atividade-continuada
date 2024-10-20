package org.cesarschool.telas;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class TelaTituloDivida extends JFrame {
    private MediatorTituloDivida mediator = MediatorTituloDivida.getInstancia();
    private static final long serialVersionUID = 1L;

    private JTextField campoIdentificador;
    private JTextField campoNome;
    private JTextField campoDataValidade;
    private JTextField campoTaxaJuros;

    private JTextArea resultadoOperacao;

    public TelaTituloDivida() {
        setTitle("Cadastro de Título de Dívida");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel principal para os campos de entrada
        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new GridLayout(4, 2));

        // Labels e campos de texto
        JLabel labelIdentificador = new JLabel("Identificador:");
        campoIdentificador = new JTextField();

        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField();

        JLabel labelDataValidade = new JLabel("Data de Validade (YYYY-MM-DD):");
        campoDataValidade = new JTextField();

        JLabel labelTaxaJuros = new JLabel("Taxa de Juros (%):");
        campoTaxaJuros = new JTextField();

        // Adiciona os componentes ao painel de campos
        painelCampos.add(labelIdentificador);
        painelCampos.add(campoIdentificador);
        painelCampos.add(labelNome);
        painelCampos.add(campoNome);
        painelCampos.add(labelDataValidade);
        painelCampos.add(campoDataValidade);
        painelCampos.add(labelTaxaJuros);
        painelCampos.add(campoTaxaJuros);

        // Painel para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        JButton botaoIncluir = new JButton("Incluir");
        JButton botaoAlterar = new JButton("Alterar");
        JButton botaoExcluir = new JButton("Excluir");
        JButton botaoBuscar = new JButton("Buscar");

        painelBotoes.add(botaoIncluir);
        painelBotoes.add(botaoAlterar);
        painelBotoes.add(botaoExcluir);
        painelBotoes.add(botaoBuscar);

        // Área de texto para exibir o resultado das operações
        resultadoOperacao = new JTextArea(5, 20);
        resultadoOperacao.setEditable(false);
        JScrollPane scrollResultado = new JScrollPane(resultadoOperacao);

        // Adiciona os painéis ao layout principal
        add(painelCampos, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scrollResultado, BorderLayout.SOUTH);

        // Ações dos botões
        botaoIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluirTituloDivida();
            }
        });

        botaoAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarTituloDivida();
            }
        });

        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirTituloDivida();
            }
        });

        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTituloDivida();
            }
        });

        setVisible(true);
    }

    private void incluirTituloDivida() {
        try {
            int identificador = Integer.parseInt(campoIdentificador.getText());
            String nome = campoNome.getText();
            LocalDate dataValidade = LocalDate.parse(campoDataValidade.getText());
            double taxaJuros = Double.parseDouble(campoTaxaJuros.getText());

            TituloDivida titulo = new TituloDivida(identificador, nome, dataValidade, taxaJuros);
            String resultado = mediator.incluir(titulo);
            resultadoOperacao.setText(resultado == null ? "Título incluído com sucesso!" : resultado);
        } catch (Exception ex) {
            resultadoOperacao.setText("Erro ao incluir título: " + ex.getMessage());
        }
    }

    private void alterarTituloDivida() {
        try {
            int identificador = Integer.parseInt(campoIdentificador.getText());
            String nome = campoNome.getText();
            LocalDate dataValidade = LocalDate.parse(campoDataValidade.getText());
            double taxaJuros = Double.parseDouble(campoTaxaJuros.getText());

            TituloDivida titulo = new TituloDivida(identificador, nome, dataValidade, taxaJuros);
            String resultado = mediator.alterar(titulo);
            resultadoOperacao.setText(resultado == null ? "Título alterado com sucesso!" : resultado);
        } catch (Exception ex) {
            resultadoOperacao.setText("Erro ao alterar título: " + ex.getMessage());
        }
    }

    private void excluirTituloDivida() {
        try {
            int identificador = Integer.parseInt(campoIdentificador.getText());
            String resultado = mediator.excluir(identificador);
            resultadoOperacao.setText(resultado == null ? "Título excluído com sucesso!" : resultado);
        } catch (Exception ex) {
            resultadoOperacao.setText("Erro ao excluir título: " + ex.getMessage());
        }
    }

    private void buscarTituloDivida() {
        try {
            int identificador = Integer.parseInt(campoIdentificador.getText());
            TituloDivida titulo = mediator.buscar(identificador);
            if (titulo != null) {
                campoNome.setText(titulo.getNome());
                campoDataValidade.setText(titulo.getDataDeValidade().toString());
                campoTaxaJuros.setText(String.valueOf(titulo.getTaxaJuros()));
                resultadoOperacao.setText("Título encontrado.");
            } else {
                resultadoOperacao.setText("Título não encontrado.");
            }
        } catch (Exception ex) {
            resultadoOperacao.setText("Erro ao buscar título: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new TelaTituloDivida();
    }
}
