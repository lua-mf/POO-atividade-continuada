package br.com.cesarschool.poo.titulos.repositorios;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.LocalDate;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

/*
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5 
 * 
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir 
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com 
 * sucesso, retorno true.
 * 
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Altera��o com sucesso, retorno true.  
 *   
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Exclus�o com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.   
 */
public class RepositorioTituloDivida {
	private static final String FILE_NAME = "TituloDivida.txt";
	
	public boolean incluir(TituloDivida tituloDivida) {
		List<String> linhas = lerArquivo(); // cada string na array List representa uma linha do arquivo
        
        // Verifica se o identificador já existe
        for (String linha : linhas) {//
            
        	String[] dados = linha.split(";");
            int idExistente = Integer.parseInt(dados[0]);// Converte o primeiro elemento do array dados (que é o identificador) para um número inteiro.
            
            if (idExistente == tituloDivida.getIdentificador()) {
                return false; // Identificador já existe
            }
        }
        
        // Se não existir, adiciona a nova ação ao final do arquivo
        String novaLinha = tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {// new FileWriter(FILE_NAME, true): Abre o arquivo para escrita no modo append (adiciona ao final, em vez de sobrescrever).
            writer.write(novaLinha);// Escreve a novaLinha no arquivo.
            writer.newLine();// Adiciona uma nova linha para separar esta entrada das próximas.
        } catch (IOException e) {
            e.printStackTrace();// Imprime a pilha de erros no console para ajudar na depuração.
            return false;
        }
        
        return true;
	}
	
	public boolean alterar(TituloDivida tituloDivida) {
		List<String> linhas = lerArquivo();
        boolean alterado = false;
        
        // Percorre todas as linhas para encontrar e alterar a ação
        for (int i = 0; i < linhas.size(); i++) {// pecorre todas as linhas da List, linhas.size() é a quantidade de linhas.
            
        	String[] dados = linhas.get(i).split(";");// (linhas.get(i)) pega a string da linha atual e a divide usando split(";")
            int idExistente = Integer.parseInt(dados[0]);
            
            if (idExistente == tituloDivida.getIdentificador()) {
                linhas.set(i, tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros());// Substitui a linha atual (i) da lista linhas por uma nova String contendo os dados atualizados da ação.
                alterado = true;
                break;
            }
        }
        
        if (alterado) {
            escreverArquivo(linhas);
            return true;
        } else {
            return false; // Identificador não encontrado
        }
	}
	
	public boolean excluir(int identificador) {
		List<String> linhas = lerArquivo();
        boolean removido = false;
        
        for (int i = 0; i < linhas.size(); i++) { 
            
        	String[] dados = linhas.get(i).split(";");
            int idExistente = Integer.parseInt(dados[0]);
            
            if (idExistente == identificador) {
                linhas.remove(i);
                removido = true;
                break;
            }
        }
        
        if (removido) {
            escreverArquivo(linhas);
            return true;
        } else {
            return false; // Identificador não encontrado
        }
	}
	
	public TituloDivida buscar(int identificador) {
	    List<String> linhas = lerArquivo();
	    
	    for (String linha : linhas) {
	        String[] dados = linha.split(";");
	        int idExistente = Integer.parseInt(dados[0]);
	        
	        if (idExistente == identificador) {
	            String nome = dados[1];
	            LocalDate dataValidade = LocalDate.parse(dados[2]);
	            double taxaJuros = Double.parseDouble(dados[3]);
	            return new TituloDivida(idExistente, nome, dataValidade, taxaJuros);
	        }
	    }
	    
	    return null; // Identificador não encontrado
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
    
    private void escreverArquivo(List<String> linhas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
