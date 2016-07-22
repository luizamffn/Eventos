package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.evento.exceptons.AtividadeException;
import br.edu.ifpi.evento.exceptons.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptons.CupomException;
import br.edu.ifpi.evento.exceptons.InscricaoPagaException;
import br.edu.ifpi.evento.exceptons.PagamentoInferiorException;

public class Inscricao {
	private Long id;
	private boolean paga;
	private double valorTotal = 0;
	private Evento evento;
	private double desconto = 0;
	private List<Cupom> cupons = new ArrayList<Cupom>();

	private List<Atividade> atividades = new ArrayList<Atividade>();

	public Inscricao(Evento evento) {
		this.evento = evento;
		this.evento.adicionarIncricao(this);
	}

	public void pagarInscricao(double valor) throws PagamentoInferiorException {
		if (valor >= valorTotal) {
			paga = true;
		} else {
			throw new PagamentoInferiorException();
		}
	}

	public void adicionarAtividade(Atividade atividade)
			throws InscricaoPagaException, AtividadeNaoEstaNoEventoException, AtividadeException {
		if (!paga) {
			verificarSeAtividadeEstaNoEvento(atividade);
			calcularValorTotal();
		} else {
			throw new InscricaoPagaException();
		}
	}

	protected void verificarSeAtividadeEstaNoEvento(Atividade atividade)
			throws AtividadeNaoEstaNoEventoException, AtividadeException {
		if (evento.getAtividades().contains(atividade)) {
			adicionarAtividadeNaoRepetida(atividade);
		} else {
			throw new AtividadeNaoEstaNoEventoException();
		}
	}

	protected void adicionarAtividadeNaoRepetida(Atividade atividade) throws AtividadeException {
		if (!atividades.contains(atividade)) {
			atividades.add(atividade);
		} else {
			throw new AtividadeException();
		}
	}

	protected double calcularValorTotal() {
		for (Atividade atividade : atividades) {
			valorTotal = atividade.getValor();
		}
		return AplicarDescontoNaInscricao();
	}

	protected double AplicarDescontoNaInscricao() {
		for (Cupom cupom : cupons) {
			if (cupom.isAtivo()) {
				desconto += valorTotal * cupom.getPorcentagemDoDesconto();
			}
		}
		return valorTotal -= desconto;
	}
	
	public void adicionarCupom(Cupom cupom) throws CupomException{
		if (!cupons.contains(cupom)) {
			cupons.add(cupom);
		} else {
			throw new CupomException();
		}
	}
	
	public double getValorTotal() {
		return valorTotal;
	}

	public boolean isPaga() {
		return paga;
	}

	public List<Atividade> getAtividades() {
		return Collections.unmodifiableList(atividades);
	}

	public Evento getEvento() {
		return evento;
	}

	public List<Cupom> getCupons() {
		return Collections.unmodifiableList(cupons);
	}

}
