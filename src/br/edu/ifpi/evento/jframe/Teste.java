package br.edu.ifpi.evento.jframe;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.ifpi.evento.dao.PessoaDAO;
import br.edu.ifpi.evento.dao.UsuarioDAO;
import br.edu.ifpi.evento.dao.impl.PessoaDAOImpl;
import br.edu.ifpi.evento.dao.impl.UsuarioDaoImpl;
import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.modelo.Pessoa;
import br.edu.ifpi.evento.modelo.Usuario;

public class Teste extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	UsuarioDAO usuarioDAO = new UsuarioDaoImpl();
	PessoaDAO pessoaDAO = new PessoaDAOImpl();
	
	private JLabel campoNome, campoCpf,campoTitulo,lblSex, lblEmail, lblUsuario, lblSenha;
	private JRadioButton radMasc, radFem;
	private ButtonGroup gruSex;
	private JTextField textNome, textEmail, textUsuario;
	private JButton botaoCadastrar;
	private JFormattedTextField texCPF;
	private JPasswordField senha;
	private Usuario usuario = new Usuario();
	private Pessoa pessoa = new Pessoa();
	
	public Teste() {
		setSize(500, 400);
	    getContentPane().setBackground(Color.WHITE);
		setTitle("Cadastro de Usuario");
		setLocationRelativeTo(null);
		setLayout(null);
		
		campoTitulo = new JLabel("Cadastro");
		campoTitulo.setBounds(200,5,100,20); //coluna, linha, largura, altura
		add(campoTitulo);
		
		campoNome = new JLabel("Digite seu nome:");
	    campoNome.setBounds(10,35,100,20);
		add(campoNome);

		textNome = new JTextField(30);
	    textNome.setBounds(110,35,300,20);
		add(textNome);
		
		campoCpf = new JLabel("CPF:");
	    campoCpf.setBounds(78,65,100,20);
		add(campoCpf);

		//Campo com máscara - CPF
	    try {
	      MaskFormatter mk= new MaskFormatter("###.###.###-##");       
	      mk.setPlaceholderCharacter('_');
	      /*  ou, pode setar os caracteres que irá receber 
	        MaskFormatter mk= new MaskFormatter("***.***.***-**");
	        mk.setPlaceholderCharacter('_');
	        mk.setValidCharacters("ABC0123456.");
	      */
	      texCPF = new JFormattedTextField(mk); 
	      texCPF.setBounds(110,65,100,20);
	      add(texCPF);
	    }
	    catch(Exception e){}
	    
	  //Label Sexo
	    lblSex = new JLabel("Sexo: ");
	    lblSex.setBounds(72,95,100,20);
	    add(lblSex);
	  
	    //Radio Masculino
	    radMasc = new JRadioButton("Masculino");
	    radMasc.setBounds(110,95,100,20);
	    radMasc.setForeground(Color.BLUE);
	    radMasc.setBackground(Color.WHITE);
	    radMasc.setSelected(true);
	  
	    //Radio Feminino
	    radFem = new JRadioButton("Feminino");
	    radFem.setBounds(220,95,120,20);
	    radFem.setForeground(Color.BLUE);
	    radFem.setBackground(Color.WHITE);
	    
	  //Grupo de Botões
	    gruSex = new ButtonGroup();
	    gruSex.add(radMasc);
	    gruSex.add(radFem);
	    add(radMasc);
	    add(radFem);
	    
	    lblEmail = new JLabel("Email:");
	    lblEmail.setBounds(70,125,100,20);
		add(lblEmail);

		textEmail = new JTextField(30);
	    textEmail.setBounds(110,125,200,20);
		add(textEmail);
		
		lblUsuario = new JLabel("Usuário:");
	    lblUsuario.setBounds(58,155,100,20);
		add(lblUsuario);

		textUsuario = new JTextField(30);
	    textUsuario.setBounds(110,155,200,20);
		add(textUsuario);
		
		lblSenha = new JLabel("Senha:");
	    lblSenha.setBounds(66,185,100,20);
		add(lblSenha);

		senha = new JPasswordField(30);
	    senha.setBounds(110,185,200,20);
		add(senha);
	    
	    
		botaoCadastrar = new JButton("Cadastrar");
	    botaoCadastrar.setBounds(150,215,150,20);
	    botaoCadastrar.addActionListener(this);
		add(botaoCadastrar);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		pessoa.setNome(textNome.getText());
//		pessoa.setCpf(Integer.valueOf(texCPF.getText()));
		if(radFem.isSelected())
			pessoa.setSexo(Sexo.F);
	    if(radMasc.isSelected())
			pessoa.setSexo(Sexo.M);
	    
	   usuario.setPessoa(pessoaDAO.salvar(pessoa));
	    
		System.out.println(usuario.getPessoa().getId());
		usuario.setUsuario(textUsuario.getText());
		usuario.setSenha(senha.getText());		
		
		usuarioDAO.salvar(usuario);
		
	}
	
	public static void main(String[] args) {
		new Teste();
	}

	
}
