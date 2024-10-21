package org.cesarschool.telas;

import java.util.ArrayList;
import java.util.List;

public class EntidadeDAO {

    private final List<Entidade> entidades = new ArrayList<>();

    public void incluir(Entidade entidade) {
        if (entidade == null) {
            System.out.println("Entidade nula. Não foi possível incluir.");
            return;
        }

        if (entidade.getCodigo() == null || entidade.getCodigo().trim().isEmpty()) {
            System.out.println("Código da entidade é inválido. Não foi possível incluir.");
            return;
        }

        if (buscar(entidade.getCodigo()) != null) {
            System.out.println("Entidade com o código " + entidade.getCodigo() + " já existe.");
            return;
        }

        entidades.add(entidade);
        System.out.println("Entidade incluída: " + entidade.getNome() + " com código: " + entidade.getCodigo());
    }


    public void alterar(Entidade entidade) {
        if (entidade == null || entidade.getCodigo() == null) {
            System.out.println("Dados da entidade inválidos. Não foi possível alterar.");
            return;
        }

        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).getCodigo().equals(entidade.getCodigo())) {
                entidades.set(i, entidade);
                System.out.println("Entidade alterada: " + entidade.getNome());
                return;
            }
        }
        System.out.println("Entidade não encontrada para alterar: " + entidade.getCodigo());
    }

    public Entidade buscar(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("Código nulo ou vazio. Não foi possível buscar.");
            return null;
        }

        codigo = codigo.trim();
        System.out.println("Buscando pelo código: " + codigo);  

        for (Entidade entidade : entidades) {
            System.out.println("Verificando entidade com código: " + entidade.getCodigo().trim());  
            if (entidade.getCodigo().trim().equalsIgnoreCase(codigo)) {
                System.out.println("Entidade encontrada: " + entidade.getNome());
                return entidade;
            }
        }
        System.out.println("Entidade não encontrada: " + codigo);
        return null;
    }

}
