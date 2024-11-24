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
}
