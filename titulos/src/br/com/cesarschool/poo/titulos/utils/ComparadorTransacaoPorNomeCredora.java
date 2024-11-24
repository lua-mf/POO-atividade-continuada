package br.com.cesarschool.poo.titulos.utils;

import br.com.cesarschool.poo.titulos.entidades.Transacao;

public class ComparadorTransacaoPorNomeCredora extends ComparadorPadrao implements Comparador {
	public int comparar(Comparavel c1, Comparavel c2) {
        if (!(c1 instanceof Transacao) || !(c2 instanceof Transacao)) {
            throw new IllegalArgumentException("Os objetos devem ser do tipo Transacao.");
        }

        Transacao t1 = (Transacao) c1;
        Transacao t2 = (Transacao) c2;

        return t1.getEntidadeCredito().getNome().compareTo(t2.getEntidadeCredito().getNome());
    }

}
