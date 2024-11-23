package br.com.cesarschool.poo.titulos.repositorios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

/*
 * Deve gravar em e ler de um arquivo texto chamado EntidadeOperadora.txt os dados dos objetos do tipo
 * EntidadeOperadora. Seguem abaixo exemplos de linhas.
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
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
public class RepositorioEntidadeOperadora extends RepositorioGeral {
	private static final String FILE_NAME = "EntidadeOperadora.txt";
    
    public boolean incluir(EntidadeOperadora entidadeOperadora) {
        List<String> linhas = lerArquivo();
        
        for (String linha : linhas) {//
            
        	String[] dados = linha.split(";");
            int idExistente = Integer.parseInt(dados[0]);
            
            if (idExistente == entidadeOperadora.getIdentificador()) {
                return false; 
            }
        }
        
        String novaLinha = entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" + entidadeOperadora.getAutorizadoAcao();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(novaLinha);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public boolean alterar(EntidadeOperadora EntidadeOperadora) {
        List<String> linhas = lerArquivo();
        boolean alterado = false;
        
        // Percorre todas as linhas para encontrar e alterar a ação
        for (int i = 0; i < linhas.size(); i++) {// pecorre todas as linhas da List, linhas.size() é a quantidade de linhas.
            
        	String[] dados = linhas.get(i).split(";");// (linhas.get(i)) pega a string da linha atual e a divide usando split(";")
            int idExistente = Integer.parseInt(dados[0]);
            
            if (idExistente == EntidadeOperadora.getIdentificador()) {
                linhas.set(i, EntidadeOperadora.getIdentificador() + ";" + EntidadeOperadora.getNome() + ";" + EntidadeOperadora.getAutorizadoAcao());// Substitui a linha atual (i) da lista linhas por uma nova String contendo os dados atualizados da ação.
                alterado = true;
                break;
            }
        }
        
        if (alterado) {
            escreverArquivo(linhas);
            return true;
        } else {
            return false; 
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
            return false; 
        }
    }
    
    public EntidadeOperadora buscar(int identificador) {
        List<String> linhas = lerArquivo();
        
        for (String linha : linhas) {
        	
            String[] dados = linha.split(";");
            int idExistente = Integer.parseInt(dados[0]);
            
            if (idExistente == identificador) {
                String nome = dados[1];
                double autorizadoAcao = Double.parseDouble(dados[3]);
                return new EntidadeOperadora(idExistente, nome, autorizadoAcao);
            }
        }
        
        return null; 
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

	public Class<?> getClasseEntidade() {
		return EntidadeOperadora.class;
	}
}
