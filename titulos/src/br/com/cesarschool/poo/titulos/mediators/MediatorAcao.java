package br.com.cesarschool.poo.titulos.mediators;

import java.time.LocalDate;

/*
 * Deve ser um singleton.
 *
 * Deve ter um atributo repositorioAcao, do tipo RepositorioAcao, que deve
 * ser inicializado na sua declaração. Este atributo será usado exclusivamente
 * pela classe, não tendo, portanto, métodos set e get.
 *
 * Métodos:
 *
 * pivate String validar(Acao acao): deve validar os dados do objeto recebido nos seguintes termos:
 * identificador: deve ser maior que zero e menor que 100000 (1)
 * nome: deve ser preenchido, diferente de branco e de null (2). deve ter entre 10 e 100 caracteres (3).
 * data de validade: deve ser maior do que a data atual mais 30 dias (4).
 * valorUnitario: deve ser maior que zero (5).
 * O método validar deve retornar null se o objeto estiver válido, e uma mensagem pertinente (ver abaixo)
 * se algum valor de atributo estiver inválido.
 *
 * (1) - Identificador deve estar entre 1 e 99999.
 * (2) - Nome deve ser preenchido.
 * (3) - Nome deve ter entre 10 e 100 caracteres.
 * (4) - Data de validade deve ter pelo menos 30 dias na frente da data atual.
 * (5) - Valor unitário deve ser maior que zero.
 *
 * public String incluir(Acao acao): deve chamar o método validar. Se ele
 * retornar null, deve incluir acao no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do incluir do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Ação já existente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String alterar(Acao acao): deve chamar o método validar. Se ele
 * retornar null, deve alterar acao no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do alterar do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Ação inexistente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String excluir(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o excluir do repositório. Retornos possíveis:
 * (1) null, se o retorno do excluir do repositório for true.
 * (2) A mensagem "Ação inexistente", se o retorno do repositório for false
 * ou se o identificador for inválido.
 *
 * public Acao buscar(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o buscar do repositório, retornando o
 * que ele retornar. Se o identificador for inválido, retornar null.
 */
public class MediatorAcao {

    // Atributo repositorioAcao do tipo RepositorioAcao
    private final RepositorioAcao repositorioAcao = new RepositorioAcao();

    // Singleton: Instância única da classe
    private static MediatorAcao instanciaUnica;

    // Construtor privado para o padrão Singleton
    private MediatorAcao() {}

    // Método para obter a instância única (Singleton)
    public static MediatorAcao getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new MediatorAcao();
        }
        return instanciaUnica;
    }

    // Método para validar a ação de acordo com as regras estabelecidas
    private String validar(Acao acao) {
        // (1) Validar o identificador
        if (acao.getIdentificador() <= 0 || acao.getIdentificador() >= 100000) {
            return "Identificador deve estar entre 1 e 99999.";
        }

        // (2) Validar se o nome está preenchido (não nulo ou branco)
        String nome = acao.getNome();
        if (nome == null || nome.trim().isEmpty()) {
            return "Nome deve ser preenchido.";
        }

        // (3) Validar o comprimento do nome
        if (nome.length() < 10 || nome.length() > 100) {
            return "Nome deve ter entre 10 e 100 caracteres.";
        }

        // (4) Validar a data de validade (deve ser pelo menos 30 dias maior que a data atual)
        LocalDate dataAtual = LocalDate.now();
        if (acao.getDataValidade().isBefore(dataAtual.plusDays(30))) {
            return "Data de validade deve ter pelo menos 30 dias à frente da data atual.";
        }

        // (5) Validar se o valor unitário é maior que zero
        if (acao.getValorUnitario() <= 0) {
            return "Valor unitário deve ser maior que zero.";
        }

        // Se todas as validações passarem, retornar null
        return null;
    }

    // Método incluir: Adiciona uma nova ação ao repositório
    public String incluir(Acao acao) {
        String resultadoValidacao = validar(acao);
        if (resultadoValidacao == null) {
            boolean incluido = repositorioAcao.incluir(acao);
            if (incluido) {
                return null; // Sucesso
            } else {
                return "Ação já existente.";
            }
        } else {
            return resultadoValidacao;
        }
    }

    // Método alterar: Altera uma ação existente no repositório
    public String alterar(Acao acao) {
        String resultadoValidacao = validar(acao);
        if (resultadoValidacao == null) {
            boolean alterado = repositorioAcao.alterar(acao);
            if (alterado) {
                return null; // Sucesso
            } else {
                return "Ação inexistente.";
            }
        } else {
            return resultadoValidacao;
        }
    }

    // Método excluir: Remove uma ação do repositório com base no identificador
    public String excluir(int identificador) {
        if (identificador <= 0 || identificador >= 100000) {
            return "Identificador inválido.";
        }
        boolean excluido = repositorioAcao.excluir(identificador);
        if (excluido) {
            return null; // Sucesso
        } else {
            return "Ação inexistente.";
        }
    }

    // Método buscar: Busca uma ação no repositório com base no identificador
    public Acao buscar(int identificador) {
        if (identificador <= 0 || identificador >= 100000) {
            return null; // Identificador inválido
        }
        return repositorioAcao.buscar(identificador);
    }
}