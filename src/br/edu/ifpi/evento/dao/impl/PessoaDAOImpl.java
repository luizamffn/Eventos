package br.edu.ifpi.evento.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifpi.evento.dao.ConnectionFactory;
import br.edu.ifpi.evento.dao.PessoaDAO;
import br.edu.ifpi.evento.modelo.Pessoa;

public class PessoaDAOImpl implements PessoaDAO{
	private Connection conn;

	@Override
	public Pessoa salvar(Pessoa pessoa) {
		conn = ConnectionFactory.getConnection();
		
		try {
			Statement s = conn.createStatement();
			String sql = "INSERT INTO pessoa(nome, cpf,sexo) "
					+ "values('" + pessoa.getNome()+ "', '" + pessoa.getCpf()+"', '" + pessoa.getSexo().getDescricao()+"' )";
			
			System.out.println(sql);
			s.executeUpdate(sql);
			sql = "SELECT id FROM pessoa ORDER BY id DESC limit 1";
			ResultSet rs = s.executeQuery(sql);
			Long id = rs.getLong("id");
			pessoa.setId(id);
			System.out.println("IDpessoa :" + pessoa.getId());
			System.out.println("Pessoa inserida com sucesso!!");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir Pessoa!");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pessoa;
		
		
	}

}
