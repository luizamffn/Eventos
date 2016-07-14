package br.edu.ifpi.evento.jframe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import br.edu.ifpi.evento.dao.UsuarioDAO;
import br.edu.ifpi.evento.dao.impl.UsuarioDaoImpl;

public class TelaEvento extends JFrame implements ActionListener{

	private static final long serialVersionUID = -6351124473322574089L;

	private JLabel lblIcon;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuCadastros;
	
	public TelaEvento() {
		setSize(500, 400);
	    getContentPane().setBackground(Color.white);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setJMenuBar(menuBar);
		setTitle("Eventos");
		setLocationRelativeTo(null);
		setLayout(null);
		
		menuCadastros = new JMenu("Cadastrar");
		menuBar.add(menuCadastros);
		
        JMenuItem openAction = new JMenuItem("Participante");
		openAction.addActionListener(this);
        menuCadastros.add(openAction);
		
		lblIcon = new JLabel(new ImageIcon("eventos.jpg"),SwingConstants.CENTER);
		lblIcon.setBounds(20,35,400,300);
		add(lblIcon);

		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TelaEvento();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Teste form2 = new Teste();
//	    form2.setVisible(true);
	    dispose();
		
	}

}
