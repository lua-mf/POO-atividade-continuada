package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdicao {

	private JFrame frame;  // Janela principal
	private JTextField txtPrimeiroNumero;  // Campo de texto para o primeiro número
	private JTextField txtSegundoNumero;   // Campo de texto para o segundo número
	private JTextField txtResultado;       // Campo de texto para o resultado

	/**
	 * Método principal para iniciar o programa.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaAdicao window = new TelaAdicao();
				window.frame.setVisible(true);  // Exibe a janela
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Construtor da classe que inicializa a aplicação.
	 */
	public TelaAdicao() {
		initialize();  // Inicializa os componentes da interface
	}

	/**
	 * Inicializa os componentes da janela.
	 */
	private void initialize() {
		frame = new JFrame();  // Criação da janela principal
		frame.setBounds(100, 100, 450, 300);  // Define o tamanho e posição da janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Define a ação ao fechar a janela
		frame.getContentPane().setLayout(null);  // Usando layout nulo para posicionamento absoluto

		// Label para o primeiro número
		JLabel lblPrimeiroNumero = new JLabel("Primeiro número");
		lblPrimeiroNumero.setBounds(41, 40, 121, 20);
		frame.getContentPane().add(lblPrimeiroNumero);

		// Label para o segundo número
		JLabel lblSegundoNumero = new JLabel("Segundo número");
		lblSegundoNumero.setBounds(41, 102, 121, 20);
		frame.getContentPane().add(lblSegundoNumero);

		// Campo de texto para o primeiro número
		txtPrimeiroNumero = new JTextField();
		txtPrimeiroNumero.setBounds(183, 40, 78, 26);
		frame.getContentPane().add(txtPrimeiroNumero);
		txtPrimeiroNumero.setColumns(10);

		// Campo de texto para o segundo número
		txtSegundoNumero = new JTextField();
		txtSegundoNumero.setBounds(183, 102, 78, 26);
		frame.getContentPane().add(txtSegundoNumero);
		txtSegundoNumero.setColumns(10);

		// Label para o resultado
		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setBounds(41, 163, 70, 20);
		frame.getContentPane().add(lblResultado);

		// Campo de texto para mostrar o resultado (não editável)
		txtResultado = new JTextField();
		txtResultado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtResultado.setBounds(183, 163, 78, 37);
		txtResultado.setEnabled(false);  // Desabilitado para edição
		txtResultado.setEditable(false);
		frame.getContentPane().add(txtResultado);
		txtResultado.setColumns(10);

		// Botão "Somar" para calcular a soma dos números
		JButton btnSomar = new JButton("Somar");
		btnSomar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtém os valores dos campos de texto e faz a soma
				double n1 = Double.parseDouble(txtPrimeiroNumero.getText());
				double n2 = Double.parseDouble(txtSegundoNumero.getText());
				double soma = n1 + n2;
				// Exibe o resultado da soma no campo de resultado
				txtResultado.setText(String.valueOf(soma));
			}
		});
		btnSomar.setBounds(130, 220, 90, 30);
		frame.getContentPane().add(btnSomar);

		// Botão "Limpar" para limpar todos os campos
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Limpa os campos de texto
				txtPrimeiroNumero.setText("");
				txtSegundoNumero.setText("");
				txtResultado.setText("");
			}
		});
		btnLimpar.setBounds(254, 220, 90, 30);
		frame.getContentPane().add(btnLimpar);
	}
}
