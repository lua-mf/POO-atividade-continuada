package org.cesarschool.telas;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import br.com.cesarschool.poo.titulos.entidades.Ativo;

public class AtivoCRUDService {
    private Map<Integer, Ativo> ativos = new HashMap<>();

    public String incluir(Ativo ativo) {
        if (ativos.containsKey(ativo.getIdentificador())) {
            return "Ativo já existente com o identificador: " + ativo.getIdentificador();
        }
        ativos.put(ativo.getIdentificador(), ativo);
        return "Ativo incluído com sucesso!";
    }

    public String alterar(int identificador, String nome, LocalDate dataDeValidade) {
        Ativo ativo = ativos.get(identificador);
        if (ativo == null) {
            return "Ativo não encontrado.";
        }
        ativo.setNome(nome);
        ativo.setDataDeValidade(dataDeValidade);
        return "Ativo alterado com sucesso!";
    }

    public String excluir(int identificador) {
        if (ativos.remove(identificador) != null) {
            return "Ativo excluído com sucesso!";
        }
        return "Ativo não encontrado.";
    }

    public Ativo buscar(int identificador) {
        return ativos.get(identificador);
    }
}
