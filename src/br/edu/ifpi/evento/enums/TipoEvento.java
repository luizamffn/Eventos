package br.edu.ifpi.evento.enums;

public enum TipoEvento {
	PUBLICA				("P�BLICA"),
	PRIVADA				("PRIVADA");
	
	private String descricao;

	private TipoEvento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
