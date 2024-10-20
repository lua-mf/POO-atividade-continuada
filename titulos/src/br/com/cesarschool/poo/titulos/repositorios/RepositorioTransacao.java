package br.com.cesarschool.poo.titulos.repositorios;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

/*
 * Deve gravar em e ler de um arquivo texto chamado Transacao.txt os dados dos objetos do tipo
 * Transacao. Seguem abaixo exemplos de linhas 
 * De entidadeCredito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De entidadeDebito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De acao: identificador, nome, dataValidade, valorUnitario OU null
 * De tituloDivida: identificador, nome, dataValidade, taxaJuros OU null. 
 * valorOperacao, dataHoraOperacao
 * 
 *   002192;BCB;true;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;1;PETROBRAS;2024-12-12;30.33;null;100000.0;2024-01-01 12:22:21 
 *   002192;BCB;false;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;null;3;FRANCA;2027-11-11;2.5;100000.0;2024-01-01 12:22:21
 *
 * A inclus�o deve adicionar uma nova linha ao arquivo. 
 * 
 * A busca deve retornar um array de transa��es cuja entidadeCredito tenha identificador igual ao
 * recebido como par�metro.  
 */
public class RepositorioTransacao {
	private static final String FILE_NAME = "Transacao.txt";
	
	public void incluir(Transacao transacao) {
		String novaLinha = transacao.toString(); // Assumindo que você tem o método toString() na classe Transacao para formatar a linha
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(novaLinha);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
	}
	

	public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
	    List<String> linhas = lerArquivo();
	    List<Transacao> transacoesEncontradas = new ArrayList<>();
	    
	    for (String linha : linhas) {
	        String[] dados = linha.split(";");
	        
	        // Verifica se o identificador da entidadeCredito (convertido para int) bate
	        if (Integer.parseInt(dados[0]) == identificadorEntidadeCredito) { 
	            Transacao transacao = parseTransacao(dados);
	            transacoesEncontradas.add(transacao);
	        }
	    }
	    
	    // Converte a lista em um array de Transacao e retorna
	    return transacoesEncontradas.toArray(new Transacao[0]);
	}

	
	private List<String> lerArquivo() {
        List<String> linhas = new ArrayList<>();
        try {
            linhas = Files.readAllLines(Paths.get(FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }
     
 // Método para converter uma linha de texto em um objeto Transacao
	private Transacao parseTransacao(String[] dados) {
	    // Parseia os dados da transação, que inclui entidadeCredito, entidadeDebito, Acao, TituloDivida, valor e dataHoraOperacao.
	    EntidadeOperadora entidadeCredito = new EntidadeOperadora( Long.parseLong(dados[0]), dados[1], Double.parseDouble(dados[2]));
	    
	    EntidadeOperadora entidadeDebito = new EntidadeOperadora( Long.parseLong(dados[5]), dados[6], Double.parseDouble(dados[7]));

	    Acao acao = null;
	    if (!dados[10].equals("null")) {
	        acao = new Acao( Integer.parseInt(dados[10]), dados[11], LocalDate.parse(dados[12]), Double.parseDouble(dados[13]));
	    }

	    TituloDivida tituloDivida = null;
	    if (!dados[14].equals("null")) {
	        tituloDivida = new TituloDivida( Integer.parseInt(dados[15]), dados[16], LocalDate.parse(dados[17]), Double.parseDouble(dados[18]));
	    }

	    double valorOperacao = Double.parseDouble(dados[19]);
	    LocalDateTime dataHoraOperacao = LocalDateTime.parse(dados[20], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    
	    return new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
	}


}
