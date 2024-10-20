package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaExemploCadastro {

	private JFrame frame;  // Janela principal
	private JTextField txtCodigo;  // Campo de texto para o código
	private JTextField txtNome;    // Campo de texto para o nome
	private JTextField txtRenda;   // Campo de texto para a renda
	private JButton btnNovo, btnBuscar, btnIncluirAlterar, btnCancelar, btnLimpar;  // Botões para ações
	private static EntidadeMediator mediator = new EntidadeMediator();  // Instância do mediator

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaExemploCadastro window = new TelaExemploCadastro();
				window.frame.setVisible(true);  // Tornando a janela visível
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaExemploCadastro() {
		initialize();  // Inicializa os componentes
	}

	private void initialize() {
		frame = new JFrame();  // Criação da janela principal
		frame.setBounds(100, 100, 600, 400);  // Define o tamanho da janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Fecha o programa ao fechar a janela
		frame.getContentPane().setLayout(null);  // Layout absoluto

		// Definição e posicionamento dos elementos
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(36, 46, 70, 20);
		frame.getContentPane().add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(129, 46, 113, 26);
		frame.getContentPane().add(txtCodigo);

		btnNovo = new JButton("Novo");
		btnNovo.setBounds(264, 42, 90, 30);
		frame.getContentPane().add(btnNovo);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(360, 42, 90, 30);
		frame.getContentPane().add(btnBuscar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(36, 109, 70, 20);
		frame.getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setEnabled(false);  // Desabilitado até que seja necessário
		txtNome.setBounds(129, 109, 225, 26);
		frame.getContentPane().add(txtNome);

		JLabel lblRenda = new JLabel("Renda:");
		lblRenda.setBounds(36, 167, 70, 20);
		frame.getContentPane().add(lblRenda);

		txtRenda = new JTextField();
		txtRenda.setEnabled(false);  // Desabilitado até que seja necessário
		txtRenda.setBounds(129, 164, 113, 26);
		frame.getContentPane().add(txtRenda);

		btnIncluirAlterar = new JButton("Incluir");
		btnIncluirAlterar.setEnabled(false);  // Inicialmente desabilitado
		btnIncluirAlterar.setBounds(131, 258, 90, 30);
		frame.getContentPane().add(btnIncluirAlterar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(239, 258, 90, 30);
		frame.getContentPane().add(btnCancelar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(347, 258, 90, 30);
		frame.getContentPane().add(btnLimpar);

		// Ações dos botões
		btnNovo.addActionListener(e -> {
			Entidade ent = mediator.buscar(txtCodigo.getText());
			if (ent != null) {
				JOptionPane.showMessageDialog(frame, "Entidade já existente!");
			} else {
				habilitarCampos(true);
				btnIncluirAlterar.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnNovo.setEnabled(false);
				btnBuscar.setEnabled(false);
				txtCodigo.setEnabled(false);
			}
		});

		btnBuscar.addActionListener(e -> {
			Entidade ent = mediator.buscar(txtCodigo.getText());
			if (ent == null) {
				JOptionPane.showMessageDialog(frame, "Entidade não existente!");
			} else {
				txtNome.setText(ent.getNome());
				txtRenda.setText(String.valueOf(ent.getRenda()));
				btnIncluirAlterar.setText("Alterar");
				habilitarCampos(true);
				btnIncluirAlterar.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnNovo.setEnabled(false);
				btnBuscar.setEnabled(false);
				txtCodigo.setEnabled(false);
			}
		});

		btnIncluirAlterar.addActionListener(e -> {
			Entidade ent = new Entidade(txtCodigo.getText(), txtNome.getText(), Double.parseDouble(txtRenda.getText()));
			String msg;
			if (btnIncluirAlterar.getText().equals("Incluir")) {
				msg = mediator.incluir(ent);
			} else {
				msg = mediator.alterar(ent);
			}
			if (msg != null) {
				JOptionPane.showMessageDialog(frame, msg);
			} else {
				resetarCampos();
				habilitarCampos(false);
				btnNovo.setEnabled(true);
				btnBuscar.setEnabled(true);
				txtCodigo.setEnabled(true);
				btnIncluirAlterar.setText("Incluir");
			}
		});

		btnCancelar.addActionListener(e -> {
			resetarCampos();
			habilitarCampos(false);
			btnNovo.setEnabled(true);
			btnBuscar.setEnabled(true);
			txtCodigo.setEnabled(true);
			btnIncluirAlterar.setText("Incluir");
		});

		btnLimpar.addActionListener(e -> {
			if (txtCodigo.isEnabled()) {
				txtCodigo.setText("");
			}
			txtNome.setText("");
			txtRenda.setText("");
		});
	}

	// Habilita ou desabilita os campos de texto
	private void habilitarCampos(boolean habilitar) {
		txtNome.setEnabled(habilitar);
		txtRenda.setEnabled(habilitar);
	}

	// Limpa todos os campos de texto
	private void resetarCampos() {
		txtNome.setText("");
		txtRenda.setText("");
		txtCodigo.setText("");
	}
}
