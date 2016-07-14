package br.edu.ifpi.evento.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifpi.evento.dao.ConnectionFactory;
import br.edu.ifpi.evento.dao.UsuarioDAO;
import br.edu.ifpi.evento.modelo.Usuario;

public class UsuarioDaoImpl implements UsuarioDAO {

	private Connection conn;
	
	@Override
	public void salvar(Usuario usuario) {
		conn = ConnectionFactory.getConnection();
		
		try {
			Statement s = conn.createStatement();
			String sql = "INSERT INTO usuario(usuario, senha, id_pessoa) "
					+ "values('" + usuario.getUsuario() +"', '" + usuario.getSenha()+"', "+ usuario.getPessoa().getId() + " )";
			
			System.out.println(sql);
			s.executeUpdate(sql);
			System.out.println("Usuario inserido com sucesso!!");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir Usuario!");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//		return gestao;
		
	}

}
