package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import java.io.*;
import java.nio.file.*;
import java.util.*;
/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
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
public class RepositorioAcao {
    private static final String FILE_NAME = "Acao.txt";
    
    public boolean incluir(Acao acao) {
        List<String> linhas = lerArquivo();
        
        // Verifica se o identificador já existe
        for (String linha : linhas) {
            String[] dados = linha.split(";");
            int idExistente = Integer.parseInt(dados[0]);
            if (idExistente == acao.getIdentificador()) {
                return false; // Identificador já existe
            }
        }
        
        // Se não existir, adiciona a nova ação ao final do arquivo
        String novaLinha = acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataValidade() + ";" + acao.getValorUnitario();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(novaLinha);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public boolean alterar(Acao acao) {
        List<String> linhas = lerArquivo();
        boolean alterado = false;
        
        // Percorre todas as linhas para encontrar e alterar a ação
        for (int i = 0; i < linhas.size(); i++) {
            String[] dados = linhas.get(i).split(";");
            int idExistente = Integer.parseInt(dados[0]);
            if (idExistente == acao.getIdentificador()) {
                linhas.set(i, acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataValidade() + ";" + acao.getValorUnitario());
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
    
    public Acao buscar(int identificador) {
        List<String> linhas = lerArquivo();
        
        for (String linha : linhas) {
            String[] dados = linha.split(";");
            int idExistente = Integer.parseInt(dados[0]);
            if (idExistente == identificador) {
                String nome = dados[1];
                String dataValidade = dados[2];
                double valorUnitario = Double.parseDouble(dados[3]);
                return new Acao(idExistente, nome, dataValidade, valorUnitario);
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
