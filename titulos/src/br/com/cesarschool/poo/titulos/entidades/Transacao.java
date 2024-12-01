package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.cesarschool.poo.titulos.utils.Comparavel;
import br.gov.cesarschool.poo.daogenerico.Entidade;

/*
 * Esta classe deve ter os seguintes atributos:
 * entidadeCredito, do tipo EntidadeOperadora
 * entidadeDebito, do tipo EntidadeOperadora
 * acao, do tipo Acao
 * tituloDivida, do tipo TituloDivida
 * valorOperacao, do tipo double
 * dataHoraOperacao, do tipo LocalDateTime
 *
 * Deve ter um construtor p�blico que inicializa os atributos.
 * Deve ter m�todos get/set p�blicos para todos os atributos, que 
 * s�o read-only fora da classe.
 * 
 *  
 */ 
public class Transacao extends Entidade implements Comparavel {
	private static final long serialVersionUID = 1L;
	
    private EntidadeOperadora entidadeCredito;
    private EntidadeOperadora entidadeDebito;
    private Acao acao;
    private TituloDivida tituloDivida;
    private double valorOperacao;
    private LocalDateTime dataHoraOperacao;

    public Transacao(EntidadeOperadora entidadeCredito, EntidadeOperadora entidadeDebito, Acao acao, TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao){
        this.entidadeCredito = entidadeCredito;
        this.entidadeDebito = entidadeDebito;
        this.acao = acao;
        this.tituloDivida = tituloDivida;
        this.valorOperacao = valorOperacao;
        this.dataHoraOperacao = dataHoraOperacao;
    }

    public EntidadeOperadora getEntidadeCredito() {
        return entidadeCredito;
    }

    public EntidadeOperadora getEntidadeDebito() {
        return entidadeDebito;
    }

    public Acao getAcao() {
        return acao;
    }

    public TituloDivida getTituloDivida() {
        return tituloDivida;
    }

    public double getValorOperacao() {
        return valorOperacao;
    }

    public LocalDateTime getDataHoraOperacao() {
        return dataHoraOperacao;
    }

	public String getIdUnico() {
		String idAtivo;
	    
	    if (acao != null) {
	        idAtivo = acao.getIdUnico();
	    } else if (tituloDivida != null) {
	        idAtivo = tituloDivida.getIdUnico();
	    } else {
	        throw new IllegalStateException("Transação deve ter uma ação ou um título de dívida associado.");
	    }

	    String dataHoraFormatada = dataHoraOperacao.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	    
	    return entidadeCredito.getIdUnico() + "_" + entidadeDebito.getIdUnico() + "_" + idAtivo + "_" + dataHoraFormatada;
	}

    public int comparar(Comparavel c) {
        if (!(c instanceof Transacao)) {
            throw new IllegalArgumentException("O objeto a ser comparado deve ser do tipo Transacao.");
        }
        Transacao outra = (Transacao) c;
        // Inverta a lógica para se adequar ao teste
        return outra.getDataHoraOperacao().compareTo(this.dataHoraOperacao);
    }
}
