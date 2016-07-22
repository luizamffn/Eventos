package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.exceptons.AtividadeException;
import br.edu.ifpi.evento.exceptons.DataMenorQueAtualException;

public class Evento {
	private Long id;

	private String nome;
	private List<Atividade> atividades = new ArrayList<Atividade>();
	private TipoEvento tipoEvento;
	private List<Inscricao> inscricoes = new ArrayList<Inscricao>();
	private List<Cupom> Cupons = new ArrayList<Cupom>();
	private Calendar dataInicio;
	private Calendar dataFim;

	public Evento(String nome, TipoEvento tipoEvento, Calendar dataInicio, Calendar dataFim)
			throws DataMenorQueAtualException {
		verificarDataInicio(dataInicio);
		this.nome = nome;
		this.tipoEvento = tipoEvento;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public void verificarDataInicio(Calendar dataInicio) throws DataMenorQueAtualException {
		Calendar now = new GregorianCalendar();
		if (dataInicio.getTimeInMillis() < now.getTimeInMillis()) {
			throw new DataMenorQueAtualException();
		}
	}

	public void adicionarAtividade(Atividade atividade) throws AtividadeException {
		if (!atividades.contains(atividade)) {
			atividades.add(atividade);
		} else {
			throw new AtividadeException();
		}
	}

	public void adicionarIncricao(Inscricao inscricao) {
		inscricoes.add(inscricao);
	}

	public boolean isAtivo() {
		Calendar now = new GregorianCalendar();
		return dataFim.getTimeInMillis() < now.getTimeInMillis();
	}

	public List<Atividade> getAtividades() {
		return Collections.unmodifiableList(atividades);
	}

	public List<Cupom> getCupons() {
		return Collections.unmodifiableList(Cupons);
	}

}
