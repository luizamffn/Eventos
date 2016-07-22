package br.edu.ifpi.evento.exceptons;

public class AtividadeNaoEstaNoEventoException extends Exception {
	public AtividadeNaoEstaNoEventoException() {
		super("Atividade nao esta no evento desta inscricão!");
	}
}
