package br.edu.ifpi.evento.teste;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;

public class TestesEvento {
	
	Calendar dataInicial;
	Calendar dataFinal;
	Evento evento;
	Inscricao inscricao;

	@Before
	public void init(){
		dataInicial = new GregorianCalendar();
//		dataInicial.set(2016, 7, 12, 20, 44, 11);
		dataFinal = Calendar.getInstance();
//		evento = new Evento("teste", TipoEventoEnum.PUBLICA,dataInicial , dataFinal);
	}
	
	@Test
	public void nao_deve_aceitar_eventos_data_passada() throws Exception {
		dataInicial.set(2016, 05, 12, 20, 44, 11);
		dataFinal.set(2016, 8, 12, 22, 00);
		evento = new Evento("teste", TipoEvento.PUBLICA, dataInicial, dataFinal);
		assertEquals(false, evento.verificarDataInicio(dataInicial));
	}
	
	@Test
	public void deve_settar_automaticamente_em_inscrição_este_evento() throws Exception {
		dataInicial.set(2016, 07, 12, 20, 44, 11);
		dataFinal.set(2016, 8, 12, 22, 00);
		evento = new Evento("teste", TipoEvento.PUBLICA, dataInicial, dataFinal);
		inscricao = new Inscricao(evento);
		assertEquals(inscricao.getEvento(), evento);
	}
	
	@Test
	public void evento_recem_criado_deve_ter_zero_atividades() throws Exception {
		dataInicial.set(2016, 07, 12, 20, 44, 11);
		dataFinal.set(2016, 8, 12, 22, 00);
		evento = new Evento("teste", TipoEvento.PUBLICA, dataInicial, dataFinal);
		assertEquals(0, evento.getAtividades().size());
	}
	
//	@Test
//	public void deve_criar_UmEvento_ComNome_EDescricao_NaoPublicado() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void deve_aceitar_eventos_com_data_hoje_ou_futura() throws Exception {
		dataInicial.set(2016, 07, 12, 20, 44, 11);
		dataFinal.set(2016, 8, 12, 22, 00);
		evento = new Evento("teste", TipoEvento.PUBLICA, dataInicial, dataFinal);
		assertEquals(true, evento.verificarDataInicio(dataInicial));
	}
	

}
