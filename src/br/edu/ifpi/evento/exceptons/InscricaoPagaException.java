package br.edu.ifpi.evento.exceptons;

public class InscricaoPagaException extends Exception{
	public InscricaoPagaException(){
		super("Inscricao ja esta paga, nao pode mais adicionar atividades!");
	}
}
