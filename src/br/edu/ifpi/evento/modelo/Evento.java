package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.edu.ifpi.evento.enums.TipoEvento;

public class Evento {
	private Long id;

	private String nome;
	private List<Atividade> atividades = new ArrayList<Atividade>();
	private TipoEvento tipoEvento;
	private List<Inscricao> inscricoes = new ArrayList<Inscricao>();
	private boolean ativo;
	private Calendar dataInicio;
	private Calendar dataFim;

	public Evento(String nome, TipoEvento tipoEvento, Calendar dataInicio, Calendar dataFim) {
		if ((verificarDataInicio(dataInicio))) {
			this.nome = nome;
			this.tipoEvento = tipoEvento;
			this.ativo = true;
			this.dataInicio = dataInicio;
			this.dataFim = dataFim;
		}
	}

	public boolean verificarDataInicio(Calendar dataInicio) {
		Calendar now = new GregorianCalendar();
		// System.out.println(dataInicio.getTimeInMillis() -
		// now.getTimeInMillis());
		if (dataInicio.getTimeInMillis() < now.getTimeInMillis()) {
			// System.out.println("A data nao pode ser menor que a de hoje");
			return false;
		} else {
			return true;
		}

	}
	
	public boolean adicionarAtividade(Atividade atividade) throws Exception {
		if (!atividades.contains(atividade)) {
			atividades.add(atividade);
			return true;
		} else {
			Exception e = new Exception("Atividade ja adicionado");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verificarValidade() {
		Calendar now = new GregorianCalendar();
		if (dataFim.getTimeInMillis() < now.getTimeInMillis()) {
			setAtivo(false);
		}
		return isAtivo();
	}

	public void adicionarIncricao(Inscricao inscricao) {
		inscricoes.add(inscricao);
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

}
