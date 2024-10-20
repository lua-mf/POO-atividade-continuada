package org.cesarschool.telas;

import java.util.ArrayList;
import java.util.List;

public class EntidadeDAO {
	private List<Entidade> entidades = new ArrayList<>();

    // Método para incluir uma nova entidade
    public void incluir(Entidade entidade) {
        entidades.add(entidade);
        System.out.println("Entidade incluída: " + entidade.getNome());
    }

    // Método para alterar uma entidade existente
    public void alterar(Entidade entidade) {
        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).getCodigo().equals(entidade.getCodigo())) {
                entidades.set(i, entidade);
                System.out.println("Entidade alterada: " + entidade.getNome());
                return;
            }
        }
        System.out.println("Entidade não encontrada para alterar: " + entidade.getCodigo());
    }

    // Método para buscar uma entidade por código
    public Entidade buscar(String codigo) {
        for (Entidade entidade : entidades) {
            if (entidade.getCodigo().equals(codigo)) {
                return entidade;
            }
        }
        System.out.println("Entidade não encontrada: " + codigo);
        return null;
    }

    // Método para listar todas as entidades (opcional)
    public List<Entidade> listar() {
        return new ArrayList<>(entidades);
    }
}
