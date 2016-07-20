package br.edu.ifpi.evento.modelo;

import br.edu.ifpi.evento.enums.TipoAtividade;

public class Atividade {
	private Long id;
	private double valor = 0;
	private String nome;
	private Evento evento;
	private TipoAtividade tipoAtividade;
	
	
	public Atividade(Long id, double valor, String nome, Evento evento, TipoAtividade tipoAtividade) {
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		this.tipoAtividade = tipoAtividade;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
}
