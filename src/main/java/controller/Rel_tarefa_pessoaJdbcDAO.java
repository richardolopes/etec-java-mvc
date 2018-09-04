package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Rel_tarefa_pessoa;

public class Rel_tarefa_pessoaJdbcDAO {
	private Connection conn;
	
	public Rel_tarefa_pessoaJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void salvar(Rel_tarefa_pessoa c) throws SQLException {
		String sql = "insert into rel_tarefa_pessoa (id_tarefa, id_pessoa) values ('"+c.getId_tarefa()+"','"+c.getId_pessoa()+"')";
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
        prepareStatement.close();
	}
	
	public void alterar(Rel_tarefa_pessoa c) throws SQLException {
		String sql = "update rel_tarefa_pessoa set id_tarefa='"+c.getId_tarefa()+",id_pessoa='"+c.getId_pessoa()+"' where id = "+c.getId()+";";
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
        prepareStatement.close();	
	}
	
	public void deletar(int id_tarefa, int id_pessoa) throws SQLException {
		String sql = "delete from rel_tarefa_pessoa where id_tarefa = " + id_tarefa + " and id_pessoa = " + id_pessoa;
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
		prepareStatement.close();            		
	}
	
	public int verificarTarefaPessoa(int tarefa, int pessoa) throws SQLException {
		String sql = "select * from rel_tarefa_pessoa where id_tarefa = " + tarefa + " and id_pessoa = " + pessoa + ";";
        System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		rs.next();
		int resultado = rs.getRow();
		rs.close();
        prepareStatement.close();
		return resultado;
	}
	
	public List<String> listarPessoas(int id) throws SQLException {
		String sql = "select p.id, p.nome, p.email from pessoa as p inner join rel_tarefa_pessoa as r on r.id_pessoa = p.id where r.id_tarefa = " + id;
        System.out.println(sql);		
        List<String> Tarefas = new ArrayList<String>();
        
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		while(rs.next()) {
			String email = rs.getString("email");
			Tarefas.add(email);
		}
		rs.close();
		prepareStatement.close();
		
		return Tarefas;
	}
}