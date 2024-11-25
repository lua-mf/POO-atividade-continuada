package br.gov.cesarschool.poo.daogenerico;

/*
 * Esta classe representa um DAO genérico, que inclui, exclui, altera, busca por identificador 
 * único e busca todos, qualquer objeto(s) cujo tipo é subtipo de Entidade.
 * 
 * Sugerimos o uso da API de serialização do JAVA, que grava e lê objetos em arquvos. 
 * Lembrar sempre de fechar (em qualquer circunstância) streams JAVA abertas.
 * 
 * As nuances mais detalhadas do funcionamento desta classe está especificada na classe de testes
 * automatizados br.gov.cesarschool.poo.testes.TestesDAOSerializador.    
 * 
 * A classe deve ter a estrutura (métodos e construtores) dada abaixo.
 */
import java.io.*;
import java.util.*;

public class DAOSerializadorObjetos {
	private String nomeDiretorio;

	public DAOSerializadorObjetos(Class<?> tipoEntidade) {
		this.nomeDiretorio = "dados/" + tipoEntidade.getSimpleName();
		File diretorio = new File(nomeDiretorio);
		if (!diretorio.exists()) {
			diretorio.mkdirs(); // Cria o diretório se não existir
		}
	}

	public boolean incluir(Entidade entidade) {
		String nomeArquivo = getNomeArquivo(entidade.getIdUnico());
		File arquivo = new File(nomeArquivo);

		// Verifica se o arquivo já existe
		if (arquivo.exists()) {
			return false;
		}

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
			oos.writeObject(entidade);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterar(Entidade entidade) {
		String nomeArquivo = getNomeArquivo(entidade.getIdUnico());
		File arquivo = new File(nomeArquivo);

		// Verifica se o arquivo existe
		if (!arquivo.exists()) {
			return false;
		}

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
			oos.writeObject(entidade);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(String idUnico) {
		String nomeArquivo = getNomeArquivo(idUnico);
		File arquivo = new File(nomeArquivo);

		// Verifica se o arquivo existe e tenta excluí-lo
		return arquivo.exists() && arquivo.delete();
	}

	public Entidade buscar(String idUnico) {
		String nomeArquivo = getNomeArquivo(idUnico);
		File arquivo = new File(nomeArquivo);

		if (!arquivo.exists()) {
			return null;
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
			return (Entidade) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Entidade[] buscarTodos() {
		File diretorio = new File(nomeDiretorio);
		File[] arquivos = diretorio.listFiles();

		if (arquivos == null || arquivos.length == 0) {
			return new Entidade[0];
		}

		List<Entidade> entidades = new ArrayList<>();
		for (File arquivo : arquivos) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
				Entidade entidade = (Entidade) ois.readObject();
				entidades.add(entidade);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return entidades.toArray(new Entidade[0]);
	}

	private String getNomeArquivo(String idUnico) {
		return nomeDiretorio + "/" + idUnico + ".dat";
	}
}
