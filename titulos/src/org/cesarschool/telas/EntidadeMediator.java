package org.cesarschool.telas;

public class EntidadeMediator {
	private EntidadeDAO dao = new EntidadeDAO();  

	public String incluir(Entidade ent) {
		String msg = validar(ent);  
		if (msg == null) {
			dao.incluir(ent);  
		}
		return msg;
	}

	public String alterar(Entidade ent) {
		String msg = validar(ent);  
		if (msg == null) {
			dao.alterar(ent);  
		}
		return msg;
	}

	private String validar(Entidade ent) {
		if (ent.getNome() == null || ent.getNome().trim().isEmpty()) {
			return "Nome deve ser preenchido";
		}
		return null;
	}

	public Entidade buscar(String codigo) {
		if (codigo == null || codigo.trim().isEmpty()) {
			return null;  
		}
		return dao.buscar(codigo);  
	}
}

