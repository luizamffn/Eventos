package br.edu.ifpi.evento.jframe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.ifpi.evento.dao.PessoaDAO;
import br.edu.ifpi.evento.dao.UsuarioDAO;
import br.edu.ifpi.evento.dao.impl.PessoaDAOImpl;
import br.edu.ifpi.evento.dao.impl.UsuarioDaoImpl;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.modelo.Evento;

public class TelaCadastroEvento extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	UsuarioDAO usuarioDAO = new UsuarioDaoImpl();
	PessoaDAO pessoaDAO = new PessoaDAOImpl();
	
	private JLabel campoNome, campoTitulo, campoTipoEvento;
	private JTextField textNome;
	private JComboBox cmbNiv;
	private JFormattedTextField texCPF;
//	private Evento evento = new Evento();
	
	public TelaCadastroEvento() {
		setSize(500, 400);
	    getContentPane().setBackground(Color.WHITE);
		setTitle("Evento");
		setLocationRelativeTo(null);
		setLayout(null);
		
		campoTitulo = new JLabel("Cadastro de Evento");
		campoTitulo.setBounds(200,5,100,20); //coluna, linha, largura, altura
		add(campoTitulo);
		
		campoNome = new JLabel("Nome:");
	    campoNome.setBounds(68 ,35,100,20);
		add(campoNome);

		textNome = new JTextField(30);
	    textNome.setBounds(110,35,300,20);
		add(textNome);
		
		//Label Nível
	    campoTipoEvento = new JLabel("Tipo Evento: ");
	    campoTipoEvento.setBounds(10,155,200,20);
	    add(campoTipoEvento);
	  
	    //Combo Nível
	    cmbNiv = new JComboBox();
	    cmbNiv.setBackground(Color.WHITE);
	    cmbNiv.addItem(TipoEvento.PRIVADA.getDescricao());
	    cmbNiv.addItem("Médio Completo");
	    cmbNiv.addItem("Superior Completo");
	    cmbNiv.addItem("Doutorado e/ou Mestrado Completo");
	    cmbNiv.setBounds(10,185,300,20);
	    add(cmbNiv);
		
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		pessoa.setNome(textNome.getText());
////		pessoa.setCpf(Integer.valueOf(texCPF.getText()));
//		if(radFem.isSelected())
//			pessoa.setSexo(Sexo.F);
//	    if(radMasc.isSelected())
//			pessoa.setSexo(Sexo.M);
//	    
//	   usuario.setPessoa(pessoaDAO.salvar(pessoa));
//	    
//		System.out.println(usuario.getPessoa().getId());
//		usuario.setUsuario(textUsuario.getText());
//		usuario.setSenha(senha.getText());		
//		
//		usuarioDAO.salvar(usuario);
//		
	}
//	
	public static void main(String[] args) {
		new TelaCadastroEvento();
	}

	
}
