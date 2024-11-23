package br.com.cesarschool.poo.relatorio;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public abstract class RepositorioGeral {
	DAOSerializadorObjetos dao;
	
	RepositorioGeral(){
		this.dao = new DAOSerializadorObjetos(getClasseEntidade());
	}
	
	public abstract Class<?> getClasseEntidade();

}
