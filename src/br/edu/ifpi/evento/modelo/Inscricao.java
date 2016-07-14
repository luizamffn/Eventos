package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.List;

public class Inscricao {
	private Long id;
	private boolean paga;
	private double valorTotal = 0;
	private Evento evento;

	private List<Atividade> atividades = new ArrayList<Atividade>();
	
	public Inscricao(Evento evento) {
		this.evento = evento;
		this.evento.adicionarIncricao(this);
	}
	
	public void pagarInscricao(double valor) throws Exception {
		if(valor >= valorTotal){
			paga = true;
		}else{
			Exception e  = new Exception("Valor inferior ao total");
			System.out.println(e);
		}
	}
	
	public boolean adicionarAtividade(Atividade atividade) throws Exception {
		if(!paga && evento.getAtividades().contains(atividade)){
			if(!atividades.contains(atividade)){
				atividades.add(atividade);
				return true;
			}else{
				return false;
			} 
		}else{
			Exception e = new Exception("incricao ja esta paga");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public double calcularValorTotal(){
		for (Atividade atividade : atividades) {
			valorTotal = atividade.getValor();
		}
		return valorTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPaga() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Evento getEvento() {
		return evento;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

}
