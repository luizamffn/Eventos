package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.List;

public class Inscricao {
	private Long id;
	private boolean paga;
	private double valorTotal = 0;
	private Evento evento;
	private double desconto = 0;
	private double valorComDesconto = 0;
	private List<Cupom> cupons = new ArrayList<Cupom>();

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
			System.out.println(e.getMessage());
		}
	}
	
	public boolean adicionarAtividade(Atividade atividade) throws Exception {
		if(!paga){
			return verificarSeAtividadeEstaNoEvento(atividade);
		}else{
			Exception e = new Exception("incricao ja esta paga");
			System.out.println(e.getMessage());
			return false;
		}
	}

	protected boolean verificarSeAtividadeEstaNoEvento(Atividade atividade) throws Exception{
		if(evento.getAtividades().contains(atividade)){
			return adicionarAtividadeNaoRepetida(atividade);
		}else{
			Exception e = new Exception("atividade nao esta no evento da incricao");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	protected boolean adicionarAtividadeNaoRepetida(Atividade atividade){
		if(!atividades.contains(atividade)){
			atividades.add(atividade);
			return true;
		}else{
			Exception e = new Exception("atividade ja esta adicionada na inscricao");
			System.out.println(e.getMessage());
			return false;
		} 
	}

	public void AplicarDescontoNaInscricao(){
		for (Cupom cupom : cupons) {
			if (cupom.isAtivo()){
				desconto += valorTotal * cupom.getPorcentagemDoDesconto();
			}
		}
		valorComDesconto = valorTotal - desconto;
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

	public double getValorComDesconto() {
		return valorComDesconto;
	}

	public void setValorComDesconto(double valorComDesconto) {
		this.valorComDesconto = valorComDesconto;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public List<Cupom> getCupom() {
		return cupons;
	}

	public void setCupom(List<Cupom> cupom) {
		this.cupons = cupom;
	}

}
