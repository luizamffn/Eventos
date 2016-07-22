package br.edu.ifpi.evento.exceptons;

public class PagamentoInferiorException extends Exception{
	public PagamentoInferiorException() {
		super("Valor inferior ao total!");
	}
}
