package br.edu.ifpi.evento.teste;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.enums.TipoAtividade;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.modelo.Atividade;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;

public class IncricaoTest {
	Calendar dataInicial;
	Calendar dataFinal;
	Evento evento;
	Inscricao inscricao;

	@Before
	public void init() throws Exception{
		dataInicial = new GregorianCalendar();
		dataFinal = Calendar.getInstance();
		dataInicial.set(2016, 07, 12, 20, 44, 11);
		dataFinal.set(2016, 8, 12, 22, 00);
		evento = new Evento("teste1", TipoEvento.PUBLICA, dataInicial, dataFinal);
		Atividade atividade = new Atividade(20.0, "java pra web", TipoAtividade.PALESTRA);
		evento.adicionarAtividade(atividade);
		
		inscricao = new Inscricao(evento);
		inscricao.adicionarAtividade(evento.getAtividades().get(0));
		inscricao.calcularValorTotal();
	}
	
//	@Test
//	public void nao_deve_aplicar_descontos_de_cupons_nao_ativos() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void valor_da_inscricao_eh_o_total_dos_seus_itens() {
		assertEquals(20.0, inscricao.getValorTotal(),0.0);
	}
	
	@Test
	public void deve_marcar_inscricao_como_paga_aoo_receber_pagamento() throws Exception {
		inscricao.pagarInscricao(20.0);
		assertEquals(true, inscricao.isPaga());
	}
	
	@Test
	public void nao_deve_icluir_atividades_repetidas() throws Exception {
		boolean resultado =inscricao.adicionarAtividade(evento.getAtividades().get(0));
		assertEquals(false, resultado);
	}
	
	@Test
	public void inscricao_recem_criada_deve_ter_zero_atividades() {
		Inscricao inscricao2 = new Inscricao(evento);
		int tamanho  = inscricao2.getAtividades().size();
		assertEquals(0, tamanho);

	}
	
	@Test
	public void inscricoes_com_pagamentos_inferiores_ao_valor_a_pagar_devem_ser_invalidos() throws Exception {
		inscricao.pagarInscricao(19.0);
		assertEquals(false, inscricao.isPaga());
	}
	
//	@Test
//	public void deve_aceitar_incluir_atividades_que_estejam_no_seu_evento() throws Exception {
////		Evento evento2 = new Evento("teste1", TipoEventoEnum.PUBLICA, dataInicial, dataFinal);
////		Palestra p = new Palestra(Long.valueOf(1), 30.00);
////		evento2.setAtividades(new Atividade());
////		evento2.getAtividades().getPalestras().add(p);
////		inscricao.getAtividade().adicionarPaletra(evento2.getAtividades().getPalestras().get(0));
////		boolean r = evento.getAtividades().getPalestras().contains(inscricao.getAtividade().getPalestras().get(0));
////		assertEquals(false, r);
//
//		
////		boolean r = inscricao.adicionarPalestra(inscricao.atividadeDoEvento().getPalestras().get(0));
//////		boolean r = evento.getAtividades().getPalestras().contains(inscricao.getAtividade().getPalestras().get(0));
////		assertEquals(true, r);
//
//	}
	
	@Test
	public void inscricao_sem_itens_deve_ter_valor_zero() {
		Inscricao inscricao3 = new Inscricao(evento);
		assertEquals(0.0, inscricao3.getValorTotal(),0.0);
	}
	
////	@Test
////	public void inscricao_deve_aplicar_descontos_ativos_no_evento() {
////		fail("Not yet implemented");
////	}
//	
////	@Test
////	public void nao_deve_aceitar_incluir_atividades_de_outros_eventos() throws Exception {
////		Evento evento2 = new Evento("teste1", TipoEvento.PUBLICA, dataInicial, dataFinal);
////		Palestra p = new Palestra(Long.valueOf(1), 30.00);
////		evento2.setAtividades(new Atividade());
////		evento2.getAtividades().getPalestras().add(p);
////		boolean r  = inscricao.adicionarPalestra(evento2.getAtividades().getPalestras().get(0));
////		assertEquals(false, r);
////
////	}
	
	@Test
	public void incricao_paga_nao_deve_aceitar_novos_itens() throws Exception {
		inscricao.pagarInscricao(20.0);
		boolean r = inscricao.adicionarAtividade(evento.getAtividades().get(0));
		assertEquals(false, r);
	}


}
