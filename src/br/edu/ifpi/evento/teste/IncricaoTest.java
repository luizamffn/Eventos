package br.edu.ifpi.evento.teste;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.enums.TipoAtividade;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.modelo.Atividade;
import br.edu.ifpi.evento.modelo.Cupom;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;

public class IncricaoTest {
	Calendar dataInicial;
	Calendar dataFinal;
	Evento evento;
	Inscricao inscricao;
	Cupom cupom;

	@Before
	public void init() throws Exception{
		dataInicial = new GregorianCalendar();
		dataFinal = Calendar.getInstance();
		dataInicial.set(2016, 07, 12, 20, 44, 11);
		dataFinal.set(2016, 8, 12, 22, 00);
		evento = new Evento("teste1", TipoEvento.PUBLICA, dataInicial, dataFinal);
		Atividade atividade = new Atividade(Long.valueOf(1), 20.0, "java pra web",evento, TipoAtividade.PALESTRA);
		evento.adicionarAtividade(atividade);
		
		inscricao = new Inscricao(evento);
		inscricao.adicionarAtividade(evento.getAtividades().get(0));
		
		cupom = new Cupom("3453PFZ", 0.5 , true);
		inscricao.adicionarCupom(cupom);
	}
	
	@Test
	public void nao_deve_aplicar_descontos_de_cupons_nao_ativos() throws Exception {
		Atividade atividade = new Atividade(Long.valueOf(2), 40.0, "html",evento, TipoAtividade.PALESTRA);
		evento.adicionarAtividade(atividade);
		inscricao.adicionarAtividade(atividade);
		System.out.println(inscricao.getValorTotal());
	}
	
	@Test
	public void valor_da_inscricao_eh_o_total_dos_seus_itens() {
		assertEquals(20.0, inscricao.getValorTotal(),0.0);
	}
	
	@Test
	public void deve_marcar_inscricao_como_paga_aoo_receber_pagamento() throws Exception {
		inscricao.pagarInscricao(20.0);
		assertEquals(true, inscricao.isPaga());
	}
	
	@Test(expected = Exception.class)
	public void nao_deve_icluir_atividades_repetidas() throws Exception {
		inscricao.adicionarAtividade(evento.getAtividades().get(0));
	}
	
	@Test
	public void inscricao_recem_criada_deve_ter_zero_atividades() {
		Inscricao inscricao2 = new Inscricao(evento);
		int tamanho  = inscricao2.getAtividades().size();
		assertEquals(0, tamanho);

	}
	
	@Test(expected = Exception.class)
	public void inscricoes_com_pagamentos_inferiores_ao_valor_a_pagar_devem_ser_invalidos() throws Exception {
		inscricao.pagarInscricao(19.0);
	}
	
	@Test
	public void deve_aceitar_incluir_atividades_que_estejam_no_seu_evento() throws Exception {
		Atividade palestra = new Atividade(Long.valueOf(2), 50.0, "Algoritmos", evento, TipoAtividade.PALESTRA);
		evento.adicionarAtividade(palestra);

		inscricao.adicionarAtividade(palestra);
	}
	
	@Test
	public void inscricao_sem_itens_deve_ter_valor_zero() {
		Inscricao inscricao3 = new Inscricao(evento);
		assertEquals(0.0, inscricao3.getValorTotal(),0.0);
	}
	
//	@Test
//	public void inscricao_deve_aplicar_descontos_ativos_no_evento() {
//		fail("Not yet implemented");
//	}
	
	@Test(expected = Exception.class)
	public void nao_deve_aceitar_incluir_atividades_de_outros_eventos() throws Exception {
		Atividade palestra = new Atividade(Long.valueOf(2), 50.0, "Algoritmos", evento, TipoAtividade.PALESTRA);

		inscricao.adicionarAtividade(palestra);
	}
	
	@Test(expected = Exception.class)
	public void incricao_paga_nao_deve_aceitar_novos_itens() throws Exception {
		inscricao.pagarInscricao(20.0);
		inscricao.adicionarAtividade(evento.getAtividades().get(0));
	}
}
