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
		if (ent.getNome() == null || ent.getNome().trim().equals("")) {
			return "Nome deve ser preenchido";
		} else {
			return null;
		}
	}
	public Entidade buscar(String codigo) {
		if (codigo == null || !codigo.equals("001")) {
			return null;
		} else {
			return new Entidade("001", "Carlos", 1000.00);
		}
	}
}
