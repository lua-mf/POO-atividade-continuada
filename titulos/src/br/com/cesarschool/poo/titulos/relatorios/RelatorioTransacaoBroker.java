package br.com.cesarschool.poo.titulos.relatorios;

import br.com.cesarschool.poo.titulos.utils.Ordenador;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import br.com.cesarschool.poo.titulos.utils.Comparador;
import br.com.cesarschool.poo.titulos.utils.ComparadorTransacaoPorNomeCredora;
import br.com.cesarschool.poo.titulos.utils.Comparavel;

public class RelatorioTransacaoBroker {

    private RepositorioTransacao repositorio;

    public RelatorioTransacaoBroker() {
        this.repositorio = new RepositorioTransacao();
    }

    public Transacao[] relatorioTransacaoPorNomeEntidadeCredora() {
        // Busca todas as transações
        Transacao[] transacoes = repositorio.buscarPorEntidadeCredora(-1);
        if (transacoes == null || transacoes.length == 0) {
            return new Transacao[0];
        }

        // Ordena usando o ComparadorTransacaoPorNomeCredora
        Ordenador.ordenar(transacoes, new ComparadorTransacaoPorNomeCredora());

        return transacoes;
    }

    public Transacao[] relatorioTransacaoPorDataHora() {
        // Busca todas as transações do repositório
        Transacao[] transacoes = repositorio.buscarTodasTransacoes();

        // Ordena usando a classe Ordenador e um comparador por data/hora
        Ordenador.ordenar(transacoes, new Comparador() {
            @Override
            public int comparar(Comparavel c1, Comparavel c2) {
                Transacao t1 = (Transacao) c1;
                Transacao t2 = (Transacao) c2;
                return t2.getDataHoraOperacao().compareTo(t1.getDataHoraOperacao()); // Ordem decrescente
            }
        });

        return transacoes; // Retorna o array ordenado
    }
}




