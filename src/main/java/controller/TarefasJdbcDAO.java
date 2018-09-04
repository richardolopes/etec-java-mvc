package controller;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pessoas;
import model.Tarefas;

public class TarefasJdbcDAO {
	private Connection conn;
	
	public TarefasJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void salvar(Tarefas c) throws SQLException {
		String sql = "insert into tarefa (titulo, prazo_estimado, descricao, data_inicio, data_termino) values ('"+c.getTitulo()+"','"+c.getPrazo_estimado()+"','"+c.getDescricao()+"','"+c.getData_inicio()+"','"+c.getData_termino()+"')";
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
        prepareStatement.close();
	}
	
	public void alterar(Tarefas c) throws SQLException {
		String sql = "update tarefa set titulo='"+c.getTitulo()+"',prazo_estimado='"+c.getPrazo_estimado()+"',descricao='"+c.getDescricao()+"',data_inicio='"+c.getData_inicio()+"',data_termino='"+c.getData_termino()+" where id = "+c.getId()+";";
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
        prepareStatement.close();		
	}
	
	public void deletar(int id) throws SQLException {
		String sql = "delete from tarefa where id = " + id;
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
		prepareStatement.close();       		
	}
	
	public int ultimaTarefa() throws SQLException {
		String sql = "select max(id) as id from tarefa";
        System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		rs.next();
		int lastId = rs.getInt("id");
		rs.close();
		prepareStatement.close();
		return lastId;
	}
	
	public List<String> listar() throws SQLException {
		String sql = "select * from tarefa";
        System.out.println(sql);		
        List<String> Tarefas = new ArrayList<String>();
		
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		while(rs.next()) {
			String id = rs.getString("id");
			Tarefas.add(id);
		}
		rs.close();
		prepareStatement.close();
		
		return Tarefas;
	}
	
	public String[] retornarInfTarefa(int id_tarefa) throws SQLException {
		String sql = "select * from tarefa where id = '" + id_tarefa + "';";
        System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		rs.next();
		String titulo = rs.getString("titulo");
		String prazo_estimado = rs.getString("prazo_estimado");
		String descricao = rs.getString("descricao");
		String data_inicio = rs.getString("data_inicio");
		String data_termino = rs.getString("data_termino");
		rs.close();
		
		String[] informacoes = {titulo, prazo_estimado, descricao, data_inicio, data_termino};

		prepareStatement.close();
		
		return informacoes;
	}
}
