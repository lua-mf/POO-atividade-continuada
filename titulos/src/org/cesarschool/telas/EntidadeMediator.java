package org.cesarschool.telas;

public class EntidadeMediator {
	private EntidadeDAO dao = new EntidadeDAO();  // Usa o DAO para manipular as entidades

	// Método para incluir uma entidade
	public String incluir(Entidade ent) {
		String msg = validar(ent);  // Valida a entidade antes de incluir
		if (msg == null) {
			dao.incluir(ent);  // Se não houver erros, inclui a entidade no DAO
		}
		return msg;
	}

	// Método para alterar uma entidade existente
	public String alterar(Entidade ent) {
		String msg = validar(ent);  // Valida a entidade antes de alterar
		if (msg == null) {
			dao.alterar(ent);  // Se não houver erros, altera a entidade no DAO
		}
		return msg;
	}

	// Valida se a entidade tem os campos corretos preenchidos
	private String validar(Entidade ent) {
		if (ent.getNome() == null || ent.getNome().trim().isEmpty()) {
			return "Nome deve ser preenchido";
		}
		return null;
	}

	// Método para buscar uma entidade por código, usando o DAO
	public Entidade buscar(String codigo) {
		if (codigo == null || codigo.trim().isEmpty()) {
			return null;  // Se o código for nulo ou vazio, retorna nulo
		}

		return dao.buscar(codigo);  // Busca a entidade no DAO
	}
}

